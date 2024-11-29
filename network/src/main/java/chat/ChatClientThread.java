package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	// streams
	private BufferedReader bufferedReader = null;

	public ChatClientThread(Socket socket) throws UnsupportedEncodingException, IOException {
		this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
	}

	@Override()
	public void run() {
		while (true) {
			String line = null;

			try {
				line = bufferedReader.readLine();

				if (line == null) {
					Logger.info("서버에서 연결을 종료하였습니다.");
					return;
				}

				System.out.println(line);
			} catch (SocketException e) {
				return;
			} catch (IOException e) {
				Logger.error(e);
			}
		}
	}
}
