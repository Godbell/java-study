package prob05;

public class Account {
	public final String accountNo;
	private int balance;
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
		this.balance = 0;
		
		System.out.println(
			String.format("%s 계좌가 개설 되었습니다.", accountNo)
		);
	}
	
	public void save(int money) {
		this.balance += money;
		
		System.out.println(
			String.format("%s 계좌에 %d만원이 입금 되었습니다.", accountNo, money)
		);
	}
	
	public void deposit(int money) {
		if (money > balance) {
			System.out.println("잔액이 부족합니다.");
			return;
		}
		
		this.balance -= money;
		
		System.out.println(
			String.format("%s 계좌에 %d만원이 출금 되었습니다.", accountNo, money)
		);
	}
	
	public int getBalance() {
		return this.balance;
	}
}
