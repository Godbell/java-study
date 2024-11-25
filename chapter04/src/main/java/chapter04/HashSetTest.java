package chapter04;

import java.util.*;

public class HashSetTest {
	public static void main(String[] args) {
		Set<Rect> set = new HashSet<>();
		
		Rect r1 = new Rect(10, 20);
		Rect r2 = new Rect(10, 20);
		
		set.add(r1);
		set.add(r2);
		
		for (final Rect rect : set) {
			System.out.println(rect.hashCode());
		}
	}
}
