package chat.domain;

import java.util.Date;

import chat.util.Logger;
import chat.util.MessageCodec;
public class Response {
    public final Protocol protocol;
    public final String dateString;
    public final String nickname;
    public final String message;

    public Response(Date date, String nickname, String message) {
        this.protocol = Protocol.MESSAGE;
        this.dateString = MessageDate.getDateStringFrom(date);
        this.nickname = nickname;
        this.message = message;
    }

    public Response(Date date, String message) {
        this.protocol = Protocol.SYSTEM;
        this.dateString = MessageDate.getDateStringFrom(date);
        this.nickname = null;
        this.message = message;
    }

    public String toStringEncoded() {
        return switch (this.protocol) {
            case SYSTEM -> Protocol.SYSTEM.name() + ":" +
                MessageCodec.encode(this.dateString) + ":" +
                MessageCodec.encode(this.message);
            case MESSAGE -> Protocol.MESSAGE.name() + ":" +
                MessageCodec.encode(this.dateString) + ":" +
                MessageCodec.encode(this.nickname) + ":" +
                MessageCodec.encode(this.message);
            default -> null;
        };
    }

    public static Response from(String rawResponse) {
        if (rawResponse == null) {
            return null;
        }

        String[] tokens = rawResponse.split(":");
        Protocol p = null;

        try {
            p = Protocol.valueOf(tokens[0]);
        } catch (IllegalArgumentException e) {
            Logger.error(e);
            return null;
        }

        String messageDate = MessageCodec.decode(tokens[1]);
        Logger.info("converting message date from: " + messageDate);

        if (p == Protocol.SYSTEM) {
            String message = MessageCodec.decode(tokens[2]);

            return new Response(MessageDate.getDateFrom(messageDate), message);
        } else if (p == Protocol.MESSAGE) {
            String nickname = MessageCodec.decode(tokens[2]);
            String message = MessageCodec.decode(tokens[3]);

            return new Response(MessageDate.getDateFrom(messageDate), nickname, message);
        } else {
            return null;
        }
    }
}
