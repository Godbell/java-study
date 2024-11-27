package collection;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		
		int i = map.get("a");
		int j = map.get(new String("two"));
		System.out.println(i + ":" + j);
		
		map.put("three", 3333);
		System.out.println(map.get("three"));
		
		Set<String> s = map.keySet();
		
		for (String key : s) {
			System.out.println(map.get(key));
		}
	}
}
