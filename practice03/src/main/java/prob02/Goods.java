package prob02;

public class Goods {
	private String name;
	private int price;
	private int stockCount;
	
	public Goods() {}
	
	public Goods(String name, int price, int stockCount) {
		this.name = name;
		this.price = price;
		this.stockCount = stockCount;
	}
	
	public void printInfo() {
		System.out.println(
			String.format(
				"%s(가격:%d원)이 %d개 입고 되었습니다.",
				name,
				price,
				stockCount
			)
		);
	}
}