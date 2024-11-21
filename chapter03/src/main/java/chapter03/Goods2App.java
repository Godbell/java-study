package chapter03;

import mypackage.Goods2;

public class Goods2App {

	public static void main(String[] args) {
		Goods2 goods2 = new Goods2();
		goods2.name = "camera";
		
		// private
		// goods2.price = 10000;
		
		// default (package)
		// goods2.countStock = 10;
		
		// private 
		// goods2.countSold = 20;
	}
}