package chat.server;

public interface RemoteServer {
	public void broadcast(String message);
	
	public void disconnectUser(String socketName);
}
