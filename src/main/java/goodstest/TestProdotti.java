package goodstest;

import java.util.ArrayList;

public class TestProdotti {

	public static void main(String[] args) {
		
		ArrayList<Prodotto> carrello = new ArrayList<Prodotto>();		
		
		Prodotto libro = new Prodotto();
		libro.setNetValue(20.0);
		libro.setDescription("book");
		libro.setSalesTaxRate(0.0);
		carrello.add(libro);
		
		Prodotto profumo = new Prodotto();
		profumo.setNetValue(50.0);
		profumo.setDescription("bottle of perfume");
		profumo.setSalesTaxRate(10.0);
		carrello.add(profumo);
		
		Prodotto boxOfChocolates = new Prodotto();
		boxOfChocolates.setNetValue(10.0);
		boxOfChocolates.setDescription("box of chocolates");
		boxOfChocolates.setSalesTaxRate(0.0);
		boxOfChocolates.setImported(true);
		carrello.add(boxOfChocolates);
		
		for(Prodotto p: carrello) {
			System.out.println(p.toString());
		}
		
	}
	
}
