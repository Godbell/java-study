package prob06;

public class Div implements Operator {
	public int run(int operandLeft, int operandRight) throws ArithmeticException {
		return operandLeft / operandRight;
	}
}