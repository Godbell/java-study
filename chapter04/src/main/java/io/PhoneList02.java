package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PhoneList02 {
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
		Scanner sc = null;

		try {
			sc = new Scanner(new FileInputStream(file));

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\t ");

				int index = 0;
				final int ROW_LENGTH = 3;

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
			System.out.println("Error while I/O: " + e);
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}