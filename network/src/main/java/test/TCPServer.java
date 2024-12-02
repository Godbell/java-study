package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();

			// binding
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 8765));

			Socket socket = serverSocket.accept(); // blocking

			try {
				InetSocketAddress addr = (InetSocketAddress) socket.getRemoteSocketAddress();
				String rAddr = addr.getAddress().toString();
				String port = "" + addr.getPort();
				System.out.println("connection with " + rAddr + ":" + port + " has been established");
			} catch (Exception e) {
				e.printStackTrace();
			}

			InputStream is = socket.getInputStream();

			byte[] buffer = new byte[256];
			while (true) {
				int readByteCount = is.read(buffer);

				if (readByteCount == -1) {
					System.out.println("[server] closed by client");
					break;
				}

				// System.out.println("[server] raw bytes: " + Arrays.toString(buffer));

				String data = new String(buffer, 0, readByteCount, StandardCharsets.UTF_8);

				if ("quit".equals(data) || "exit".equals(data)) {
					break;
				}

				System.out.println("[server] receive: " + data);
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
		} catch (IOException e) {
			System.out.println("[server] error: " + e.getMessage());
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}