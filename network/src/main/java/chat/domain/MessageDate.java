package chat.domain;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
public class MessageDate {
    private MessageDate() {
    }

    public static String getDateStringFrom(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

    public static Date getDateFrom(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.parse(dateString, new ParsePosition(0));
    }
}
