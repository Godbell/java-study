package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {
	public static void main(String[] args) {
		File file = new File("input.txt");

		if (!file.exists()) {
			System.out.println("no such file: " + file.getPath());
			return;
		}

		System.out.println("File Data");
		printFileMetaData(file);

		System.out.println("\nContent");
		printFileContent(file);
	}

	private static void printFileMetaData(File file) {
		System.out.println("* Path: " + file.getAbsolutePath());
		System.out.println("* Length: " + file.length() + " bytes");
		System.out.println("* Last Modified: " + getDateString(file.lastModified()));
	}

	private static String getDateString(Long timestamp) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timestamp);

		return dateFormat.format(date);
	}

	private static void printFileContent(File file) {
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			// primary stream, sub stream 1
			isr = new InputStreamReader(new FileInputStream(file));

			// sub stream 2
			br = new BufferedReader(isr);

			int index = 0;
			final int ROW_LENGTH = 3;
			String line = null;
			while ((line = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, "\t ");

				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();

					if (index == 1) {
						System.out.print('\t');
					} else if (index > 1) {
						System.out.print(' ');
					}

					System.out.print(token);

					if (index == ROW_LENGTH - 1) {
						System.out.println();
					}

					index = (index + 1) % ROW_LENGTH;
				}
			}
		} catch (IOException e) {
			System.out.println("error while I/O: " + e);
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					System.out.print("error closing input stream reader: " + e);
				}
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.print("error closing buffered reader: " + e);
				}
			}
		}
	}
}