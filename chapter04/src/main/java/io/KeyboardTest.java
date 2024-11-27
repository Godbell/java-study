package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {
	public static void main(String[] args) {
		// primary stream: stdin
		// sub stream: byte -> char
		InputStreamReader isr = null;
		
		try {
			isr = new InputStreamReader(System.in, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(isr);
		
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				if ("quit".equals(line)) {
					break;
				}
				
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
