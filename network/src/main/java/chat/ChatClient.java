package chat;

import java.util.Scanner;

import chat.client.ChatClientEntity;

public class ChatClient {
    public static void main(String[] args) {
        ChatClientEntity client = ChatClientEntity.getClient();

        Scanner sc = new Scanner(System.in);

        System.out.println("닉네임을 입력해 주세요.");
        System.out.print(">> ");
        String nickname = sc.nextLine();

        client.connectTo("127.0.0.1", 8765, nickname, null);

        sc.close();
    }
}
