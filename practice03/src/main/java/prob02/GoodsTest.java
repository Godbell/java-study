package prob02;

import java.util.Scanner;

public class GoodsTest {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		for (int i = 0; i < COUNT_GOODS; ++i) {
			String name = sc.next();
			int price = sc.nextInt();
			int stockCount = sc.nextInt();
			
			goods[i] = new Goods(name, price, stockCount);
		}

		// 상품 출력
		for (int i = 0; i < COUNT_GOODS; ++i) {
			goods[i].printInfo();
		}
		
		sc.close();
	}
}