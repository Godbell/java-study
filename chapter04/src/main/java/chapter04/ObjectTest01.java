package chapter04;

public class ObjectTest01 {
	public static void main(String[] args) {
		Point point = new Point();
		Class klass = point.getClass(); // reflection
		
		System.out.println(klass.hashCode()); // address-based hash
		
		System.out.println(point.toString());
		System.out.println(point);
	}
}
