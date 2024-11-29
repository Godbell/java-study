package chat;

public interface RemoteServer {
	public void broadcast(String message);
	
	public void disconnectUser(String socketName);
}
