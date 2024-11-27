package collection;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		String str = new String("a");
		
		set.add("a");
		set.add("b");
		set.add("c");
		
		System.out.println(set.size());
		System.out.println(set.contains(str));
		
		for (String str2 : set) {
			System.out.println(str2);
		}
	}
}
