package chapter04;

public class WrapperClassTest02 {
	public static void main(String[] args) {
		String s = "1234";
		Integer i = Integer.parseInt(s);
		
		String s2 = String.valueOf(i);
		String s3 = "" + i;
		
		System.out.println(s + ":" + s2 + ":" + s3);
		System.out.println(Character.getNumericValue('A'));
		
		String s4 = Integer.toBinaryString(15);
		System.out.println(s4);
	}
}
