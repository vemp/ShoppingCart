package main;

import cart.ShoppingCart;
import testData.TestData;

/**
 * The PrintReceiptMain contains a simple main function which, starting from the given
 * test input data, prints receipts for the three shopping carts.
 * @author Francesco Venturini - 29.03.2018
 *
 */
public class PrintReceiptMain {

	public static void main(String[] args) {
		
		TestData data = new TestData();
		
		for (ShoppingCart c: data.getShoppingCarts()) {
			System.out.println(c);
		}
		
	}
}
