package test;

public class Logger {
	public static void log(String info) {
		System.out.println(info);
	}

	public static void logError(Exception e) {
		System.out.println("error: " + e.getMessage());
	}
}
