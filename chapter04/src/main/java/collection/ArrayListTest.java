package collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		
		for (int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i));
		}
		
		list.remove(2);
		
		// iter
		Iterator<String> e = list.iterator();
		
		while (e.hasNext()) {
			String s = e.next();
			System.out.println(s);
		}
		
		for (String s : list) {
			System.out.println(s);
		}
	}
}
