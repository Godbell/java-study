package chapter03;

public class StaticMethod {
	int n;
	static int m;
	
	void f1() {
		n = 10;
	}
	
	void f2() {
		StaticMethod.m = 10;
		m = 20;
	}
	
	void f3() {
		f2();
	}
	
	void f4() {
		StaticMethod.s1();
		s1();
	}
	
	static void s1() {
		// error : non static
		// n = 10;
	}
	
	static void s2() {
		// error : non static
		// f1();
	}
	
	static void s3() {
		StaticMethod.m = 10;
		m = 20;
	}
	
	static void s4() {
		StaticMethod.s1();

		// static
		s1();
	}
}