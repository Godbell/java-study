package httpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	private ServerSocket serverSocket;
	public final String bindAddress;
	public final int port;

	public HttpServer(String bindAddress, int port) {
		this.bindAddress = bindAddress;
		this.port = port;

		init();
	}
	
	private void init() {
		try {
			this.serverSocket = new ServerSocket();
		} catch (IOException e) {
			logError(e);
			return;
		}

		try {
			this.serverSocket.bind(new InetSocketAddress("0.0.0.0", this.port));
		} catch (IOException e) {
			logError(e);
			return;
		}

		log("starts... [port:" + this.port + "]");
	}

	public void listen() {
		if (!isSocketOpen()) {
			log("socket is closed.");
			return;
		}

		try {
			while (true) {
				Socket socket = this.serverSocket.accept();
				new RequestHandler(socket).start();
			}
		} catch (IOException e) {
			logError(e);
		} finally {
			if (isSocketOpen()) {
				closeSocket();
			}
		}
	}

	private boolean isSocketOpen() {
		return this.serverSocket != null && !serverSocket.isClosed();
	}

	private void closeSocket() {
		if (!isSocketOpen()) {
			log("socket is already closed.");
			return;
		}
		
		try {
			if (isSocketOpen()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			logError(e);
		}
	}

	private void log(String message) {
		System.out.println("[HttpServer#" + Thread.currentThread().getName() + "] " + message);
	}

	private void logError(Exception e) {
		log("error: " + e);
	}
}
