package chat.server;

public interface RemoteServer {
    public void broadcastMessage(String name, String message);

    public void broadcastSystemNotification(String message);

    public void disconnectUser(String socketName);
}
