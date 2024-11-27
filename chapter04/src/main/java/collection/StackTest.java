package collection;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Stack<String> s = new Stack<>();
		
		s.push("a");
		s.push("b");
		s.push("c");
		
		while (!s.empty()) {
			String str = s.pop();
			System.out.println(str);
		}
		
		s.push("a");
		s.push("b");
		s.push("c");
		
		System.out.println(s.pop());
		System.out.println(s.peek());
		System.out.println(s.pop());
	}
}
