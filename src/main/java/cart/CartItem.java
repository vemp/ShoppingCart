package cart;

import products.TaxableProduct;
import utils.CurrencyUtilities;

/**
 * 
 * @author Francesco Venturini - 24.03.2018
 *
 */
public class CartItem {

	private int quantity = 1;
	private TaxableProduct product;
	
	public CartItem() {};
	
	public CartItem(TaxableProduct product) {
		this.product = product;
	}
	
	public CartItem(TaxableProduct product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(quantity);
		sb.append(" " + product.getDescription());
		sb.append(": " + CurrencyUtilities.formatCurrency(product.calcShelfPrice()));
		return sb.toString();
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TaxableProduct getProduct() {
		return product;
	}
	public void setProduct(TaxableProduct product) {
		this.product = product;
	}
	
	
}
