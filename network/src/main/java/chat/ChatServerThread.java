package chat;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Writer;

public class ChatServerThread extends Thread {
	private String nickname = null;
	private final String socketName;
	private RemoteServer server;

	// streams
	private BufferedReader bufferedReader = null;
	private Writer writer = null;

	public ChatServerThread(RemoteServer server, Writer writer, BufferedReader bufferedReader, String socketName) {
		this.server = server;
		this.writer = writer;
		this.bufferedReader = bufferedReader;
		this.socketName = socketName;
	}

	@Override()
	public void run() {
		while (true) {
			Request request = getRequest();

			if (request == null) {
				continue;
			}

			switch (request.protocol()) {
			case Protocol.JOIN:
				doJoin(request.body());
				break;
			case Protocol.MESSAGE:
				doMessage(request.body());
				break;
			case Protocol.QUIT:
				doQuit();
				return;
			}
		}
	}

	public void sendText(String text) {
		PrintWriter printWriter = (PrintWriter) this.writer;
		printWriter.println(text);
		printWriter.flush();
	}

	private Request getRequest() {
		try {
			String request = this.bufferedReader.readLine();
			Logger.info("got a request: " + request);

			if (request == null || "QUIT".equals(request)) {
				return new Request(Protocol.QUIT, null);
			}

			String[] tokens = request.split(":");

			if (tokens.length != 2) {
				Logger.info("that was an invalid request.");
				return null;
			}

			return new Request(Protocol.valueOf(tokens[0]), tokens[1]);
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
	}

	private void doJoin(String nickname) {
		this.nickname = nickname;
		server.broadcast("[SYSTEM] " + this.nickname + "님께서 입장하였습니다.");
	}

	private void doMessage(String message) {
		Chat chat = new Chat(this.nickname, message);
		server.broadcast("[" + chat.getCreatedAt() + "] " + this.nickname + ": " + message);
	}

	private void doQuit() {
		server.disconnectUser(this.socketName);
		
		if (this.nickname != null) {
			server.broadcast("[SYSTEM] " + this.nickname + "님께서 퇴장하였습니다.");
		}
	}
}
