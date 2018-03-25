package utils;

import java.math.BigDecimal;

import cart.CartItem;
import cart.ShoppingCart;
import products.TaxableProduct;

public class TestCarrello {

	public static void main(String[] args) {
		
		ShoppingCart cart1 = new ShoppingCart();

		TaxableProduct p1 = new TaxableProduct();
		p1.setNetPrice(new BigDecimal(12.49));
		p1.setDescription("book");
		p1.setBasicSalesTaxExempt(true);

		TaxableProduct p2 = new TaxableProduct();
		p2.setNetPrice(new BigDecimal(14.99));
		p2.setDescription("music CD");
		
		TaxableProduct p3 = new TaxableProduct();
		p3.setNetPrice(new BigDecimal(0.85));
		p3.setDescription("chocolate bar");
		p3.setBasicSalesTaxExempt(true);

		cart1.getItems().add(new CartItem(p1));
		cart1.getItems().add(new CartItem(p2));
		cart1.getItems().add(new CartItem(p3));
		
		ShoppingCart cart2 = new ShoppingCart();
		
		TaxableProduct p4 = new TaxableProduct();
		p4.setNetPrice(new BigDecimal(10));
		p4.setDescription("box of chocolates");
		p4.setBasicSalesTaxExempt(true);
		p4.setImported(true);

		TaxableProduct p5 = new TaxableProduct();
		p5.setNetPrice(new BigDecimal(47.50));
		p5.setDescription("bottle of perfume");
		p5.setImported(true);
		
		cart2.getItems().add(new CartItem(p4));
		cart2.getItems().add(new CartItem(p5));

		
		ShoppingCart cart3 = new ShoppingCart();
		
		TaxableProduct p6 = new TaxableProduct();
		p6.setNetPrice(new BigDecimal(27.99));
		p6.setDescription("bottle of perfume");
		p6.setImported(true);

		TaxableProduct p7 = new TaxableProduct();
		p7.setNetPrice(new BigDecimal(18.99));
		p7.setDescription("bottle of perfume");

		TaxableProduct p8 = new TaxableProduct();
		p8.setNetPrice(new BigDecimal(9.75));
		p8.setDescription("packet of headache pills");
		p8.setBasicSalesTaxExempt(true);
		
		TaxableProduct p9 = new TaxableProduct();
		p9.setNetPrice(new BigDecimal(11.25));
		p9.setDescription("box of chocolates");
		p9.setBasicSalesTaxExempt(true);
		p9.setImported(true);

		cart3.getItems().add(new CartItem(p6));
		cart3.getItems().add(new CartItem(p7));
		cart3.getItems().add(new CartItem(p8));
		cart3.getItems().add(new CartItem(p9));
		
		
		System.out.println(cart1);
		System.out.println(cart2);
		System.out.println(cart3);
		
		
		
	}
}
