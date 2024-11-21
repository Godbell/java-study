package prob06;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print(">> ");
			String expression = scanner.nextLine();
			
			if("quit".equals(expression)) {
				break;
			}
			
			String[] tokens = expression.split(" ");
			
			if(tokens.length != 3) {
				System.out.println(">> 알 수 없는 식입니다.");
				continue;
			}
			
			int lValue = 0;
			int rValue = 0; 
			
			try {
				lValue = Integer.parseInt(tokens[0]);
				rValue = Integer.parseInt(tokens[2]);
			} catch (NumberFormatException numberFormatException) {
				System.out.println(
					String.format(
						"잘못된 입력입니다: %s", 
						numberFormatException.getMessage()
					)
				);
				continue;
			}
			
			Operator operator = null;
			
			switch(tokens[1]) {
				case "+": {
					operator = new Add();
					break;
				}
				case "-": {
					operator = new Sub();
					break;
				}
				case "*": {
					operator = new Mul();
					break;					
				}
				case "/": {
					operator = new Div();
					break;
				}
				default:  {
					System.out.println(">> 알 수 없는 연산입니다.");
				}
			}
			
			try {
				int result = operator.run(lValue, rValue);
				System.out.println(">> " + result);
			} catch (ArithmeticException arithmeticException) {
				System.out.println(
					String.format(
						">> 잘못된 연산입니다: %s",
						arithmeticException.getMessage()
					)
				);
			}
			
		}
		
		scanner.close();
	}

}
