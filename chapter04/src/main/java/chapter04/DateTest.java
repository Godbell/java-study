package chapter04;

import java.util.*;
import java.text.SimpleDateFormat;

public class DateTest {
	public static void main(String[] args) {
		Date now = new Date();
		
		System.out.println(now);
		printDate01(now);
		printDate02(now);
	}
	
	private static void printDate01(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));
	}
	
	@SuppressWarnings("deprecation")
	private static void printDate02(Date date) {
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int dateNum = date.getDate();
		
		System.out.println(year + "-" + month + "-" + dateNum);
	}
}
 