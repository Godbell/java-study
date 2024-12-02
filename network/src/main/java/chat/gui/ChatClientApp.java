package chat.gui;

import java.util.Scanner;

import chat.client.ChatClientEntity;

public class ChatClientApp {
    public static void main(String[] args) {
        String name = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("대화명을 입력하세요.");
            System.out.print(">>> ");
            name = scanner.nextLine();

            if (!name.isEmpty()) {
                break;
            }

            System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
        }

        ChatGui gui = new ChatWindow(name);
        gui.show();
        ChatClientEntity.getClient().connectTo("127.0.0.1", 8765, name, gui);

        scanner.close();
    }
}
