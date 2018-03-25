package cart;

import products.GenericProduct;
import utils.CurrencyUtilities;

/**
 * 
 * @author Francesco Venturini - 24.03.2018
 *
 */
public class CartItem {

	private int quantity = 1;
	private GenericProduct product;
	
	public CartItem() {};
	
	public CartItem(GenericProduct product) {
		this.product = product;
	}
	
	public CartItem(GenericProduct product, int quantity) {
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
	public GenericProduct getProduct() {
		return product;
	}
	public void setProduct(GenericProduct product) {
		this.product = product;
	}
	
	
}
