package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReaderTest {
	public static void main(String[] args) {
		Reader in = null;
		FileInputStream is = null;
		
		try {
			in = new FileReader("test.txt");
			is = new FileInputStream("test.txt");
			
			int count = 0;
			int data = -1;
			
			while ((data = in.read()) != -1) {
				System.out.print((char)data);
				//++count; 
			}
			
			System.out.println();
			
			while ((data = is.read()) != -1) {
				System.out.print((char)data);
				++count; 
			}
			
			System.out.println();
			System.out.println("\ncount: " + count);
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e);
		} catch (IOException e) {
			System.out.println("file not found: " + e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				System.out.println("couldn't close file: " + e);
			}
		}
	}
}
