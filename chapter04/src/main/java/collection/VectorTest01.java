package collection;

import java.util.Enumeration;
import java.util.Vector;

public class VectorTest01 {
	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		
		v.addElement("a");
		v.addElement("b");
		v.addElement("c");
		
		for (int i = 0; i < v.size(); ++i) {
			System.out.println(v.elementAt(i));
		}
		
		v.removeElement(2);
		
		// iter
		Enumeration<String> e = v.elements();
		
		while (e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
		}
	}
}
