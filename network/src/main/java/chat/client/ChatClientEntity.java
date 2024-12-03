package chat.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import chat.domain.Protocol;
import chat.domain.Request;
import chat.domain.Response;
import chat.gui.ChatGui;
import chat.server.ChatServerEntity;
import chat.util.Logger;

/**
 * A <i>ChatServerEntity</i> is an implementation of a simple client for
 * {@linkplain ChatServerEntity}. There can only be a single client per
 * process, so this is implemented as a singleton object using Java style
 * singleton pattern.
 * <h2>Connect to a Server</h2> Get connection to server by:<br><br>
 * {@code ChatClientEntity client = ChatClientEntity.getClient();}<br>
 * {@code client.connectTo("host address", PORT_NUMBER);}<br><br>
 * {@linkplain #connectTo(String, int, String, ChatGui)} is a blocking method, and will make
 * additional thread to get data from server. 
 **/
public class ChatClientEntity implements Client {
    private PrintWriter printWriter = null;
    private ChatGui gui = null;

    public static ChatClientEntity getClient() {
        return SingletonHolder.instance;
    }

    public void connectTo(String host, int port, String nickname, ChatGui gui) {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket();
        this.gui = gui;

        try {
            socket.connect(new InetSocketAddress(host, port));
            System.out.println("서버에 연결되었습니다.");

            ChatClientThread thread = new ChatClientThread(socket);
            thread.start();

            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
            Request joinRequest = new Request(Protocol.JOIN, nickname);
            printWriter.println(joinRequest.toStringEncoded());

            while (true) {
                if (gui != null) {
                    continue;
                }

                System.out.print(">> ");
                String input = scanner.nextLine();

                send(input);
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

    public void send(String input) {
        if ("quit".equals(input)) {
            sendQuitSign();
        } else {
            sendMessage(input);
        }
    }

    @Override
    public void receive(Response response) {
        if (this.gui == null) {
            System.out.println(response);
        } else {
            if (response.protocol == Protocol.SYSTEM) {
                this.gui.receiveSystemNotification(response);
            } else {
                this.gui.receiveMessage(response);
            }
        }
    }

    private void sendMessage(String message) {
        Request request = new Request(Protocol.MESSAGE, message);
        this.printWriter.println(request.toStringEncoded());
        this.printWriter.flush();
    }

    private void sendQuitSign() {
        Request request = new Request(Protocol.QUIT, null);
        this.printWriter.println(request.toStringEncoded());
        this.printWriter.flush();
    }

    private ChatClientEntity() {
    }

    private static class SingletonHolder {
        private static final ChatClientEntity instance = new ChatClientEntity();
    }
}
