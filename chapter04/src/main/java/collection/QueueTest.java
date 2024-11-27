package collection;

import java.util.Queue;
import java.util.LinkedList;

public class QueueTest {
	public static void main(String[] args) {
		Queue<String> q = new LinkedList<>();
		
		q.offer("a");
		q.offer("b");
		q.offer("c");
		
		while (!q.isEmpty()) {
			String str = q.poll();
			System.out.println(str);
		}
		
		q.offer("a");
		q.offer("b");
		q.offer("c");
		
		System.out.println(q.poll());
		System.out.println(q.peek());
		System.out.println(q.poll());
	}
}
