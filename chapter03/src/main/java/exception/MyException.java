package exception;

@SuppressWarnings("serial")
public class MyException extends Exception {
	public MyException() {
		super("Default Exception Message");
	}
	
	public MyException(String message) {
		super(message);
	}
}
