package prob01;

public class Printer {
//	public void println(Object obj) {
//		System.out.println(obj);
//	}
	
	public <T> void println(T p) {
		System.out.println(p);
	}
	
	public int sum(Integer... args) {
		int result = 0;
		
		for (Integer num : args) {
			result += num;
		}
		
		return result;
	}
}
