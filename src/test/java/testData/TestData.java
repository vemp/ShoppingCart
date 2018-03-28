package testData;

import java.math.BigDecimal;

import cart.CartItem;
import cart.ShoppingCart;
import products.TaxableProduct;

public class TestData {

	public TaxableProduct[] prod = new TaxableProduct[6];
	public CartItem[] items = new CartItem[6];
	public ShoppingCart[] carts = new ShoppingCart[3];
	
	public TestData() {
		// an exempt, non imported, zero net price product for testing zero return values
		prod[0] = new TaxableProduct();
		prod[0].setNetPrice(BigDecimal.ZERO);
		prod[0].setBasicSalesTaxExempt(true);
		prod[0].setImported(false);
		
		// non exempt, non imported, 14.99 net price
		prod[1] = new TaxableProduct();
		prod[1].setNetPrice(new BigDecimal(14.99));
		
		// exempt, imported, 10.00 net price
		prod[2] = new TaxableProduct();
		prod[2].setNetPrice(new BigDecimal(10));
		prod[2].setBasicSalesTaxExempt(true);
		prod[2].setImported(true);
		
		// non exempt, imported, 47.50 net price
		prod[3] = new TaxableProduct();
		prod[3].setNetPrice(new BigDecimal(47.50));
		prod[3].setImported(true);

		// non exempt, imported, 27.99 net price
		prod[4] = new TaxableProduct();
		prod[4].setNetPrice(new BigDecimal(27.99));
		prod[4].setImported(true);

		// non exempt, imported, 100.00 net price with basic sales tax rate at 22 percent and import rate at 29 percent
		prod[5] = new TaxableProduct();
		prod[5].setNetPrice(new BigDecimal(100.00));
		prod[5].setImported(true);
		prod[5].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).setRate(new BigDecimal(22));
		prod[5].getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).setRate(new BigDecimal(29));
		
		
		items[0] = new CartItem();
		items[0].setProduct(prod[0]);

		items[1] = new CartItem();
		items[1].setProduct(prod[1]);
		
		items[2] = new CartItem();
		items[2].setProduct(prod[2]);
		
		items[3] = new CartItem();
		items[3].setProduct(prod[3]);
		
		items[4] = new CartItem();
		items[4].setProduct(prod[4]);
		
		items[5] = new CartItem();
		items[5].setProduct(prod[5]);
		
	}

	public TaxableProduct[] getProd() {
		return prod;
	}

	public CartItem[] getItems() {
		return items;
	}
	
}
