package chat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * A <i>Logger</i> prints given message to console.
 * */
public class Logger {
	public static void info(String message) {
		System.out.println("[INFO] " + Logger.getDateString() + " | " + message);
	}

	public static void warn(String message) {
		System.out.println("[WARN] " + Logger.getDateString() + " | " + message);
	}

	public static void error(Exception e) {
		System.out.println("[ERROR] " + Logger.getDateString() + " | " + e.getMessage());
		e.printStackTrace();
	}

	private static String getDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		return formatter.format(new Date());
	}
}
