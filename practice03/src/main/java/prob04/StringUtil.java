package prob04;

public class StringUtil {
	public static String concatenate(String[] arr) {
		String result = "";
		
		for (int i = 0; i < arr.length; ++i) {
			result += arr[i];
		}
		
		return result;
	}
}
