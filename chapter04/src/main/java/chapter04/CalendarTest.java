package chapter04;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {
	public static void main(String[] args) {
		System.out.println(Locale.getDefault(Locale.Category.FORMAT));
		System.out.println(TimeZone.getDefault());
		Calendar cal = Calendar.getInstance();
		
		printDate(cal);
		
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2000, 1, 29);
		printDate(cal);
	}
	
	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		
		System.out.println(year + "-" + month + "-" + date + " " + hour + ":" + minute);
	}
}
