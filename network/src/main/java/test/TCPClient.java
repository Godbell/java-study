package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {
	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress("127.0.0.1", 8765));
			
			// buffer
			int recvBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println(recvBufferSize + ":" + sendBufferSize);
			
			// size mod
			socket.setReceiveBufferSize(1024 * 10);
			socket.setSendBufferSize(1024 * 10);
			
			recvBufferSize = socket.getReceiveBufferSize();
			sendBufferSize = socket.getSendBufferSize();
			System.out.println(recvBufferSize + ":" + sendBufferSize);
			
			// so nodelay
			socket.setTcpNoDelay(true);
			
			// io stream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			String data = "Hello world";
			os.write(data.getBytes("utf-8"));
			
			// read
			byte[] buffer = new byte [256];
			int readByteCount = is.read(buffer);
			
			if (readByteCount == -1) {
				System.out.println("[client] closed by server");
				return;
			}
			
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received: " + data);
		} catch (SocketException e) {
			System.out.println("[server] closed by client: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("error: " + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
