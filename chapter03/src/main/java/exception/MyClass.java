package exception;

import java.io.IOException;

public class MyClass {
	public static void throwIOException() throws IOException {
		throw new IOException();
	}
	
	public static void throwMyException() throws MyException {
		throw new MyException();
	}
	
	public static void throwMyException(String message) throws MyException {
		throw new MyException(message);
	}
}
