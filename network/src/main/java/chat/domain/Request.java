package chat.domain;

import chat.util.Logger;
import chat.util.MessageCodec;
public class Request {
    public final Protocol protocol;
    public final String body;

    public Request(Protocol protocol, String body) {
        this.protocol = protocol;
        this.body = body;
    }

    public String toStringEncoded() {
        return switch (this.protocol) {
            case JOIN, MESSAGE -> this.protocol + ":" +
                MessageCodec.encode(this.body);
            case QUIT -> this.protocol.toString();
            default -> null;
        };
    }

    public static Request from(String rawRequest) {
        if (rawRequest == null || Protocol.QUIT.name().equals(rawRequest)) {
            return new Request(Protocol.QUIT, null);
        }

        String[] tokens = rawRequest.split(":");
        Protocol p;

        try {
            p = Protocol.valueOf(tokens[0]);
        } catch (IllegalArgumentException e) {
            Logger.error(e);
            return null;
        }

        if (p == Protocol.QUIT) {
            return new Request(Protocol.QUIT, null);
        } else if (p == Protocol.MESSAGE) {
            if (tokens.length != 2) {
                return null;
            }

            String message = MessageCodec.decode(tokens[1]);
            return new Request(Protocol.MESSAGE, message);
        } else if (p == Protocol.JOIN) {
            String nickname = MessageCodec.decode(tokens[1]);
            return new Request(Protocol.JOIN, nickname);
        } else {
            return null;
        }
    }
}
