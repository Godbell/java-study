package chat;

import chat.server.ChatServerEntity;
public class ChatServer {
	public static void main(String[] args) {
		ChatServerEntity server = new ChatServerEntity();
		
		server.setBindAddress("0.0.0.0");
		server.setPort(8765);
		server.listen();
	}
}
