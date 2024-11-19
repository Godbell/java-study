package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.print("수를 입력하세요 : ");
			Integer number = sc.nextInt();
			
			if (number == 0) {
				System.out.println(0);
				continue;
			}
			
			Integer result = 0;
			
			if (number < 0) {
				sc.close();
				return;
			} 
			
			if (number % 2 == 0) {
				Double countDouble = Math.floor(number / 2);
				Integer count = countDouble.intValue();
				result = count * count + count;
			} else {
				Double countDouble = Math.floor((number + 1) / 2);
				Integer count = countDouble.intValue();
				result = count * count;
			}
			
			System.out.println("결과값: " + result);
		}
	
	}
}
