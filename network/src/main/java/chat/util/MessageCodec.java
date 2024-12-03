package chat.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class MessageCodec {
    private MessageCodec() {
    }

    public static String encode(String rawText) {
        return Base64.getEncoder().encodeToString(rawText.getBytes());
    }

    public static String decode(String encodedText) {
        return new String(Base64.getDecoder().decode(encodedText), StandardCharsets.UTF_8);
    }
}
