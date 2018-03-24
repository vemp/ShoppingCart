package goodstest;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

public class TestProdotti {

	public static void main(String[] args) {
		
		ArrayList<Prodotto> carrello1 = new ArrayList<Prodotto>();		
		
		Prodotto p1 = new Prodotto();
		p1.setNetValue(new BigDecimal(12.49));
		p1.setDescription("book");
		p1.setSalesTaxRate(BigDecimal.ZERO);
		carrello1.add(p1);
		
		Prodotto p2 = new Prodotto();
		p2.setNetValue(new BigDecimal(14.99));
		p2.setDescription("music CD");
		carrello1.add(p2);
		
		Prodotto p3 = new Prodotto();
		p3.setNetValue(new BigDecimal(0.85));
		p3.setDescription("chocolate bar");
		p3.setSalesTaxRate(BigDecimal.ZERO);
		carrello1.add(p3);
		
		for(Prodotto p: carrello1) {
			System.out.println(p.toString());
		}
		
		ArrayList<Prodotto> carrello2 = new ArrayList<Prodotto>();		
		
		Prodotto p4 = new Prodotto();
		p4.setNetValue(new BigDecimal(10));
		p4.setDescription("box of chocolates");
		p4.setSalesTaxRate(BigDecimal.ZERO);
		p4.setImported(true);
		carrello2.add(p4);
		
		Prodotto p5 = new Prodotto();
		p5.setNetValue(new BigDecimal(47.50));
		p5.setDescription("bottle of perfume");
		p5.setImported(true);
		carrello2.add(p5);
		
		for(Prodotto p: carrello2) {
			System.out.println(p.toString());
		}
		
		ArrayList<Prodotto> carrello3 = new ArrayList<Prodotto>();		
		
		Prodotto p6 = new Prodotto();
		p6.setNetValue(new BigDecimal(27.99));
		p6.setDescription("bottle of perfume");
		p6.setImported(true);
		carrello3.add(p6);
		
		Prodotto p7 = new Prodotto();
		p7.setNetValue(new BigDecimal(18.99));
		p7.setDescription("bottle of perfume");
		carrello3.add(p7);
		
		Prodotto p8 = new Prodotto();
		p8.setNetValue(new BigDecimal(9.75));
		p8.setDescription("packet of headache pills");
		p8.setSalesTaxRate(BigDecimal.ZERO);
		carrello3.add(p8);
		
		Prodotto p9 = new Prodotto();
		p9.setNetValue(new BigDecimal(11.25));
		p9.setDescription("box of chocolates");
		p9.setSalesTaxRate(BigDecimal.ZERO);
		p9.setImported(true);
		carrello3.add(p9);
		
		for(Prodotto p: carrello3) {
			System.out.println(p.toString());
		}
		
		
	}
	
}
