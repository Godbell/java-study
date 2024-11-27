package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			
			// binding
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 8765));
			
			Socket socket = serverSocket.accept(); //blocking
			
			System.out.println("연결 성공");
			
			InputStream is = socket.getInputStream();
			
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			
			if (readByteCount == -1) {
				System.out.println("[server] closed by client");
				return;
			}
			
			System.out.println("[server] raw bytes: " + Arrays.toString(Arrays.copyOf(buffer, readByteCount)));
			String data = new String(buffer, 0, readByteCount, StandardCharsets.UTF_8);
			System.out.println("[server] receive: " + data);
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