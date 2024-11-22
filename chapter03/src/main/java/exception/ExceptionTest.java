package exception;

public class ExceptionTest {
	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;
		
		System.out.println("some code1...");
		
		try {
			System.out.println("some code2...");
			System.out.println("some code3...");
			
			int result = (1 & 2 + 3) / b; // dbz
			System.out.println(result);
			
			System.out.println("some code4...");
			System.out.println("some code5...");
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return;
		} finally {
			System.out.println("safe exit");
		}
		
		System.out.println("some code6...");
	}
}
