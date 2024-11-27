package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {
	public static void main(String[] args) {
		BufferedOutputStream bos = null;
		
		try {
			FileOutputStream fos = new FileOutputStream("test.txt");
			
			bos = new BufferedOutputStream(fos);
			
			for (int i = 65; i <= 90; ++i) {
				bos.write(i);
			}
			
			bos.flush();
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e);
		} catch (IOException e) {
			System.out.println("error: " + e);
		}
	}
}
