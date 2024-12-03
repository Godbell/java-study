package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import chat.util.Logger;
import chat.domain.Request;

public class ChatServerThread extends Thread {
    private String nickname = null;
    private final String socketName;
    private final RemoteServer server;

    // streams
    private final BufferedReader bufferedReader;
    private final Writer writer;

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

            switch (request.protocol) {
                case JOIN:
                    doJoin(request.body);
                    break;
                case MESSAGE:
                    doMessage(request.body);
                    break;
                case QUIT:
                    doQuit();
                    return;
                default:
            }
        }
    }

    public void sendText(String text) {
        PrintWriter printWriter = (PrintWriter)this.writer;
        printWriter.println(text);
        printWriter.flush();
    }

    private Request getRequest() {
        String request = null;

        try {
            request = this.bufferedReader.readLine();
        } catch (IOException e) {
            Logger.error(e);
            return null;
        }

        Logger.info("got a request [" + request + "] from " + this.socketName);
        return Request.from(request);
    }

    private void doJoin(String nickname) {
        this.nickname = nickname;
        server.broadcastSystemNotification(this.nickname + "님께서 입장하였습니다.");
    }

    private void doMessage(String message) {
        server.broadcastMessage(this.nickname, message);
    }

    private void doQuit() {
        server.disconnectUser(this.socketName);

        if (this.nickname != null) {
            server.broadcastSystemNotification(this.nickname + "님께서 퇴장하셨습니다.");
        }
    }
}
