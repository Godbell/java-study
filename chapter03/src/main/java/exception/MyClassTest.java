package exception;

import java.io.IOException;

public class MyClassTest {
	public static void main(String[] args) {
		try {
			MyClass.throwIOException();
		} catch (IOException e) {
			System.out.println("error: " + e);
		}
		
		try {
			MyClass.throwMyException();
		} catch (MyException e) {
			System.out.println("error: " + e.getMessage());
		}
		
		try {
			MyClass.throwMyException("Custom Exception message");
		} catch (MyException e) {
			System.out.println("error: " + e.getMessage());
		}
	}
}
