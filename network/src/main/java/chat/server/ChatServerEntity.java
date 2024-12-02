package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import chat.util.Logger;

/**
 * <p>
 * A <i>ChatServerEntity</i> is an implementation of a simple chat server, for
 * an 9th Posco DX Expert Academy assignment. Using this you can run a simple
 * chat server.<br>
 * </p>
 * 
 * <h2>Start Server</h2>
 * 
 * <p>
 * Start server by:<br>
 * <br>
 * {@code ChatServerEntity server = new ChatServerEntity();}<br>
 * {@code server.listen();}<br><br>
 * This is a blocking method, and will make additional threads per connected
 * user.<br>
 * </p>
 * 
 * <h2>Configuration</h2>
 * <p>
 * All configuration must be done before calling {@linkplain #listen()} Trying
 * to run setters while server is listening will do nothing.
 * </p>
 * 
 * <h3>Bind Address</h3>
 * <p>
 * It allows every source address by default by binding address to
 * <i>0.0.0.0<i>. To configure, run {@linkplain #setBindAddress(String)} with
 * created server instance.
 * <h3>Port</h3>
 * <p>
 * It will listen to port 8080 by default. To configure, specify when declaraing
 * or run {@linkplain #setPort(int)} with created server instance.
 * </p>
 */
public class ChatServerEntity implements RemoteServer {
	private String bindAddress;
	private int port = 8080;
	private ServerSocket serverSocket;
	private boolean isListening = false;
	private final Map<String, ChatServerThread> connections;

	public ChatServerEntity() {
		this.connections = new HashMap<>();

		try {
			this.bindAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			this.bindAddress = "0.0.0.0";
		}
	}

	public void setBindAddress(String bindAddress) {
		if (isListening) {
			warnConfigWhileServerRunning();
			return;
		}

		this.bindAddress = bindAddress;
	}

	public void setPort(int port) {
		if (isListening) {
			warnConfigWhileServerRunning();
			return;
		}

		this.port = port;
	}

	public void listen() {
		Logger.info("연결 기다림 " + this.bindAddress + ":" + this.port);

		try {
			init();

			isListening = true;

			while (true) {
				Socket socket = this.serverSocket.accept();

				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"),
						true);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), "utf-8"));

				ChatServerThread thread = new ChatServerThread(this, printWriter, bufferedReader, socket.toString());
				this.connections.put(socket.toString(), thread);

				Logger.info("connection established: " + socket.toString());

				thread.start();
			}
		} catch (IOException e) {
			Logger.error(e);
		} finally {
			if (isSocketOpen()) {
				closeSocket();
			}

			isListening = false;
		}
	}

	private void warnConfigWhileServerRunning() {
		Logger.warn("Server is Running. This will make no effects. Is this intended?");
	}

	private void init() throws IOException {
		this.serverSocket = new ServerSocket();
		this.serverSocket.bind(new InetSocketAddress(this.bindAddress, this.port));
	}

	private boolean isSocketOpen() {
		return this.serverSocket != null && !serverSocket.isClosed();
	}

	private final void closeSocket() {
		if (!isSocketOpen()) {
			Logger.info("socket is already closed.");
			return;
		}

		try {
			if (isSocketOpen()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			Logger.error(e);
		}
	}

	@Override
	public void broadcast(String text) {
		synchronized (this.connections) {
            this.connections.keySet().forEach(key -> this.connections.get(key).sendText(text));
		}
	}

	@Override
	public void disconnectUser(String socketName) {
		synchronized (this.connections) {
			this.connections.remove(socketName);
		}

		Logger.info("Disconnected " + socketName + "\nwe now have " + this.connections.size() + " in this server.");
	}
}