package prob06;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return amount == other.amount;
	}
}
