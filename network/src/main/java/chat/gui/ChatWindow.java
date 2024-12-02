package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import chat.client.ChatClientEntity;

public class ChatWindow implements ChatGui {
    private Frame frame;
    private Panel panel;
    private Button buttonSend;
    private TextField textField;
    private TextArea textArea;

    public ChatWindow(String name) {
        frame = new Frame(name);
        panel = new Panel();
        buttonSend = new Button("Send");
        textField = new TextField();
        textArea = new TextArea(30, 80);
    }

    public void show() {
        // Button
        buttonSend.setBackground(Color.GRAY);
        buttonSend.setForeground(Color.WHITE);
        buttonSend.addActionListener(actionEvent -> sendMessage());

        // Textfield
        textField.setColumns(80);

        // Pannel
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(textField);
        panel.add(buttonSend);
        frame.add(BorderLayout.SOUTH, panel);

        // TextArea
        textArea.setEditable(false);
        frame.add(BorderLayout.CENTER, textArea);

        // Frame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void receive(String data) {
        this.textArea.append(data + "\n");
    }

    private void sendMessage() {
        String message = textField.getText();
        ChatClientEntity.getClient().send(message);
        this.textField.setText("");
    }
}
