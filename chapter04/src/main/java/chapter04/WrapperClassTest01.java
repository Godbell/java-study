package chapter04;

public class WrapperClassTest01 {
	public static void main(String[] args) {
		Integer i = new Integer(1);
		
		Integer j1 = 10;
		Integer j2 = 20;
		
		System.out.println(j1 != j2);
		System.out.println(j1.equals(j2));
	}
}
