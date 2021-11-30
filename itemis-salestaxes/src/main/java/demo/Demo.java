package demo;

import logic.ShoppingBasket;

public class Demo {

	public static void main(String[] args) {
		ShoppingBasket firstBasket = new ShoppingBasket();
		firstBasket.addPurchase("1 book at 12.49");
		firstBasket.addPurchase("1 music CD at 14.99");
		firstBasket.addPurchase("1 chocolate bar at 0.85");
		
		ShoppingBasket secondBasket = new ShoppingBasket();
		secondBasket.addPurchase("1 imported box of chocolates at 10.00");
		secondBasket.addPurchase("1 imported bottle of perfume at 47.50");
		
		ShoppingBasket thirdBasket = new ShoppingBasket();
		thirdBasket.addPurchase("1 imported bottle of perfume at 27.99");
		thirdBasket.addPurchase("1 bottle of perfume at 18.99");
		thirdBasket.addPurchase("1 packet of headache pills at 9.75");
		thirdBasket.addPurchase("1 box of imported chocolates at 11.25");
		
		System.out.println(firstBasket.getReceipt());
		System.out.println();
		System.out.println(secondBasket.getReceipt());
		System.out.println();
		System.out.println(thirdBasket.getReceipt());
		System.out.println();
	}

}
