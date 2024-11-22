package exception;

import java.io.*;

public class FileTest {
	public static void main(String[] args) {
//		throws and must handle FileNotFoundException
//		FileInputStream fis = new FileInputStream("s");
		FileInputStream fis = null;
				
		try {
			fis = new FileInputStream("s");
			
			int data = fis.read();
			System.out.println(data);
		} catch (FileNotFoundException e) {
			System.out.println("Failed to open file: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Failed: " + e.getMessage());
		} finally {
			System.out.println("end try-catch block");
			
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("I/O Failed: " + e.getMessage());
				}
			}
		}
	}
}
