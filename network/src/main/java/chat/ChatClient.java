package chat;

public class ChatClient {
	public static void main(String[] args) {
		ChatClientEntity client = ChatClientEntity.getClient();

		client.connectTo("127.0.0.1", 8765);
	}
}
