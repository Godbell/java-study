package chat.client;

import chat.domain.Response;
public interface Client {
    void receive(Response data);
}
