package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private Socket socket;

	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// setup streams
		OutputStream outputStream = null;
		BufferedReader br = null;

		try {
			outputStream = socket.getOutputStream();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		} catch (IOException e) {
			logError(e);
			return;
		}

		// log connection sinfo
		InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		log("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort());

		// parse request
		String request = null;

		while (true) {
			String line = null;

			try {
				line = br.readLine();
			} catch (IOException e) {
				logError(e);
				return;
			}

			if (line == null || "".equals(line)) {
				break;
			}

			if (request == null) {
				request = line;
				break;
			}
		}

		log(request);

		String[] tokens = request.split(" ");
		String url = tokens[1];
		String protocol = tokens[2];

		try {
			if ("GET".equals(tokens[0])) {
				responseStaticResources(outputStream, url, protocol);
			} else {
				// deals with GET only
				throw new BadRequestException();
			}
		} catch (HttpException e) {
			responseErrorPage(outputStream, protocol, e);
		} catch (Exception e) {
			logError(e);
		} finally {
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException ex) {
				log("error:" + ex);
			}
		}
	}

	private void responseStaticResources(OutputStream os, String url, String protocol)
			throws IOException, NotFoundException {
		// default(welcome) file
		if ("/".equals(url)) {
			url = "/index.html";
		}

		File file = new File("./webapp" + url);

		if (!file.exists()) {
			throw new NotFoundException();
		}

		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());

		sendResponse(os, 200, "OK", protocol, contentType, body);
	}

	private void responseErrorPage(OutputStream os, String protocol, HttpException httpException) {
		try {
			File file = new File("./webapp/error/" + httpException.statusCode + ".html");

			byte[] body = Files.readAllBytes(file.toPath());
			String contentType = Files.probeContentType(file.toPath());

			sendResponse(os, httpException.statusCode, httpException.status, protocol, contentType, body);
		} catch (IOException e) {
			logError(e);
		}
	}

	private void sendResponse(OutputStream os, int statusCode, String status, String protocol, String contentType,
			byte[] body) throws IOException {
		os.write((protocol + " " + statusCode + " " + status + "\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes());
		os.write(body);
	}

	private void log(String message) {
		System.out.println("[RequestHandler#" + this.getName() + "] " + message);
	}

	private void logError(Exception e) {
		log("error: " + e);
	}
}