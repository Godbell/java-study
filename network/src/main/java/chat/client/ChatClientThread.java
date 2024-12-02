package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import chat.gui.ChatGui;
import chat.util.Logger;

public class ChatClientThread extends Thread {
    // streams
    private BufferedReader bufferedReader = null;

    public ChatClientThread(Socket socket) throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
    }

    @Override()
    public void run() {
        while (true) {
            String line;

            try {
                line = bufferedReader.readLine();

                if (line == null) {
                    Logger.info("서버에서 연결을 종료하였습니다.");
                    return;
                }

                ChatClientEntity.getClient().receive(line);
            } catch (SocketException e) {
                return;
            } catch (IOException e) {
                Logger.error(e);
            }
        }
    }
}
