package prob06;

public class Money extends Object {
	public final int amount;
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public Money add(Money operand) {
		return new Money(amount + operand.amount);
	}
	
	public Money minus(Money operand) {
		return new Money(amount - operand.amount);
	}

	public Money multiply(Money operand) {
		return new Money(amount * operand.amount);
	}
	
	public Money divide(Money operand) {
		if (operand.amount == 0) {
			System.out.println("Cannot devide by 0");
			return null;
		}
		
		return new Money(amount / operand.amount);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Money)) {
			return false;
		}
		
		Money target = (Money)obj;
		return target.amount == this.amount;
	}
}
