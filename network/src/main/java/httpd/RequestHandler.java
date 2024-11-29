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
				HttpResponse res = getStaticResourceResponse(url, protocol);
				sendResponse(outputStream, res);
			} else {
				// deals with GET only
				throw new BadRequestException();
			}
		} catch (HttpException e) {
			HttpResponse res = getErrorPageResponse(e, protocol);
			sendResponse(outputStream, res);
		} catch (Exception e) {
			logError(e);
		} finally {
			closeSocket();
		}
	}

	private HttpResponse getStaticResourceResponse(String url, String protocol) throws NotFoundException {
		// default(welcome) file
		if ("/".equals(url)) {
			url = "/index.html";
		}

		File file = new File("./webapp" + url);

		if (!file.exists()) {
			throw new NotFoundException();
		}

		HttpResponse res = new HttpResponse();

		try {
			byte[] body = Files.readAllBytes(file.toPath());
			res.setBody(body);

			String contentType = Files.probeContentType(file.toPath());
			res.setContentType(contentType);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	private HttpResponse getErrorPageResponse(final HttpException httpException, String protocol) {
		HttpResponse res = new HttpResponse();

		try {
			File file = new File("./webapp/error/" + httpException.statusCode + ".html");

			byte[] body = Files.readAllBytes(file.toPath());
			res.setBody(body);

			String contentType = Files.probeContentType(file.toPath());
			res.setContentType(contentType);

			res.setStatusCode(httpException.statusCode);
			res.setProtocol(protocol);
		} catch (IOException e) {
			logError(e);
		}

		return res;
	}

	private void sendResponse(OutputStream os, final HttpResponse res) {
		try {
			os.write((res.getProtocol() + " " + res.getStatusCode() + " " + HttpResponse.getName(res.getStatusCode())
					+ "\n").getBytes("UTF-8"));

			os.write(("Content-Type:" + res.getContentType() + "; charset=utf-8\n").getBytes("UTF-8"));
			os.write("\n".getBytes());
			os.write(res.getBody());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeSocket() {
		try {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		} catch (IOException e) {
			logError(e);
		}
	}

	private void log(String message) {
		System.out.println("[RequestHandler#" + this.getName() + "] " + message);
	}

	private void logError(Exception e) {
		log("error: " + e);
	}
}