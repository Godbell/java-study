package chat.domain;

import chat.util.Logger;
public class Request {
    public final Protocol protocol;
    public final String body;

    private Request(Protocol protocol, String body) {
        this.protocol = protocol;
        this.body = body;
    }

    public static Request from(String rawRequest) {
        if (rawRequest == null || Protocol.QUIT.name().equals(rawRequest)) {
            return new Request(Protocol.QUIT, null);
        }

        String[] tokens = rawRequest.split(":");

        if (tokens.length != 2) {
            Logger.info("that was an invalid request.");
            return null;
        }

        try {
            return new Request(Protocol.valueOf(tokens[0]), tokens[1]);
        } catch (IllegalArgumentException e) {
            Logger.error(e);
            return null;
        }
    }
}
