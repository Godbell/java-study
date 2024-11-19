package prob02;

public class Sol02 {
	public static void main(String[] args) {
		for (Integer i = 10; i <= 18; ++i) {
			for (Integer j = 1; j <= i; ++j) {
				System.out.print(j.toString() + ' ');
			}
			System.out.println();
		}
	}
}