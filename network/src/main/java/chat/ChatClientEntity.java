package chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * A <i>ChatServerEntity</i> is an implementation of a simple client for
 * {@linkplain #ChatServerEntity}. There can only be a single client per
 * process, so this is implemented as a singleton object using Java style
 * singleton pattern.
 * 
 * <h2>Connect to a Server</h2> Get connection to server by:<br>
 * {@code ChatClientEntity client = ChatClientEntity.getClient();}<br>
 * {@code client.connectTo("host address", PORT_NUMBER);}<br>
 * {@linkplain #connectTo(String, int)} is a blocking method, and will make
 * additional thread to get data from server. Main Thread will get interrupted
 * by Standard I/O.
 **/
public class ChatClientEntity {
	public static ChatClientEntity getClient() {
		return SingletonHolder.instance;
	}

	public void connectTo(String host, int port) {
		Scanner scanner = new Scanner(System.in);

		Socket socket = new Socket();
		PrintWriter printWriter = null;

		try {
			System.out.println("닉네임을 입력해 주세요.");
			System.out.print(">> ");
			String nickname = scanner.nextLine();

			socket.connect(new InetSocketAddress(host, port));
			System.out.println("서버에 연결되었습니다.");

			(new ChatClientThread(socket)).start();

			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			printWriter.println("JOIN:" + nickname);

			while (true) {
				System.out.print(">> ");
				String input = scanner.nextLine();

				if ("quit".equals(input)) {
					printWriter.println("QUIT:");
					printWriter.flush();
					break;
				} else {
					printWriter.println("MESSAGE:" + input);
					printWriter.flush();
				}
			}
		} catch (IOException e) {
			Logger.error(e);
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}

			scanner.close();

			try {
				socket.close();
			} catch (IOException e) {
				Logger.error(e);
			}
		}
	}

	private ChatClientEntity() {
	}

	private static class SingletonHolder {
		private static final ChatClientEntity instance = new ChatClientEntity();
	}
}
