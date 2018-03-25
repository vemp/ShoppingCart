package utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import products.TaxableProduct;

public class TestProdotti {

	public static void main(String[] args) {
		
		ArrayList<TaxableProduct> carrello1 = new ArrayList<TaxableProduct>();		
		
		TaxableProduct p1 = new TaxableProduct();
		p1.setNetPrice(new BigDecimal(12.49));
		p1.setDescription("book");
		p1.setBasicSalesTaxExempt(true);
		carrello1.add(p1);
		
		TaxableProduct p2 = new TaxableProduct();
		p2.setNetPrice(new BigDecimal(14.99));
		p2.setDescription("music CD");
		carrello1.add(p2);
		
		TaxableProduct p3 = new TaxableProduct();
		p3.setNetPrice(new BigDecimal(0.85));
		p3.setDescription("chocolate bar");
		p3.setBasicSalesTaxExempt(true);
		carrello1.add(p3);
		
		for(TaxableProduct p: carrello1) {
			System.out.println(p.toString());
		}
		
		ArrayList<TaxableProduct> carrello2 = new ArrayList<TaxableProduct>();		
		
		TaxableProduct p4 = new TaxableProduct();
		p4.setNetPrice(new BigDecimal(10));
		p4.setDescription("box of chocolates");
		p4.setBasicSalesTaxExempt(true);
		p4.setImported(true);
		carrello2.add(p4);
		
		TaxableProduct p5 = new TaxableProduct();
		p5.setNetPrice(new BigDecimal(47.50));
		p5.setDescription("bottle of perfume");
		p5.setImported(true);
		carrello2.add(p5);
		
		for(TaxableProduct p: carrello2) {
			System.out.println(p.toString());
		}
		
		ArrayList<TaxableProduct> carrello3 = new ArrayList<TaxableProduct>();		
		
		TaxableProduct p6 = new TaxableProduct();
		p6.setNetPrice(new BigDecimal(27.99));
		p6.setDescription("bottle of perfume");
		p6.setImported(true);
		carrello3.add(p6);
		
		TaxableProduct p7 = new TaxableProduct();
		p7.setNetPrice(new BigDecimal(18.99));
		p7.setDescription("bottle of perfume");
		carrello3.add(p7);
		
		TaxableProduct p8 = new TaxableProduct();
		p8.setNetPrice(new BigDecimal(9.75));
		p8.setDescription("packet of headache pills");
		p8.setBasicSalesTaxExempt(true);
		carrello3.add(p8);
		
		TaxableProduct p9 = new TaxableProduct();
		p9.setNetPrice(new BigDecimal(11.25));
		p9.setDescription("box of chocolates");
		p9.setBasicSalesTaxExempt(true);
		p9.setImported(true);
		carrello3.add(p9);
		
		for(TaxableProduct p: carrello3) {
			System.out.println(p.toString());
		}
		
		
	}
	
}
