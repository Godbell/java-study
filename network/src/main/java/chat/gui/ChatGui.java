package chat.gui;

import chat.domain.Response;
public interface ChatGui {
    void show();

    void receiveMessage(Response data);

    void receiveSystemNotification(Response data);
}
