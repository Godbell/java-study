package prob05;

public class Sol05 {
	public static void main(String[] args) {
		for (int i = 1; i <= 100; ++i) {
			int currentI = i;
			
			int clap = 0;
			
			while (currentI > 0) {
				int digit = currentI % 10;
				if (digit != 0 && digit % 3 == 0) {
					clap++;
				}
				currentI = currentI / 10;
			}
			
			if (clap > 0) {
				System.out.print(i);
				System.out.print(' ');
				for (int j = 0; j < clap; ++j) {
					System.out.print('짝');
				}
				System.out.println();
			}
		}
	}
}
