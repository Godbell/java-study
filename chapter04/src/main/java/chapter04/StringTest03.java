package chapter04;

public class StringTest03 {
	public static void main(String[] args) {
//		String s1 = new StringBuilder("Hello ")
//			.append("World ")
//			.append("Java ")
//			.append(21)
//			.toString();
		
		String s1 = new StringBuffer("Hello ")
				.append("World ")
				.append("Java ")
				.append(21)
				.toString();
		
		System.out.println(s1);
		
		StringBuffer sb = new StringBuffer("");
		
		for (int i = 0; i < 1000000; ++i) {
			//s2 = new StringBuffer(s2).append("h").toString();
			sb.append("h");
		}
		
		System.out.println(sb.toString());
		
		String s4 = "abcABCabcAbc";
		System.out.println(s4.length());
		System.out.println(s4.charAt(2));
		System.out.println(s4.indexOf("abc"));
		System.out.println(s4.indexOf("abc", 7));
		System.out.println(s4.substring(3));
		System.out.println(s4.substring(3, 5));
		
		String s5 = "    ab   cd  ";
		
		System.out.println("--" + s5.trim() + "--");
		System.out.println("--" + s5.replaceAll(" ", "") + "--");
	
		String[] tokens2 = sb.toString().split(" ");
	}
}
