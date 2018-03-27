package cart;

import java.math.BigDecimal;

import products.TaxableProduct;
import utils.CurrencyUtilities;

/**
 * The CartItem class represents a single entry in a typical shopping cart.
 * The entry is identified by a {@link TaxableProduct } object and its quantity.
 * <p> It provides a calcTaxes method which returns the total tax value,
 * and a calcShelfPrice method which returns the total shelf price.
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
	
	
	/**
	 * Calculate total taxes for the {@link TaxableProduct}, taking into
	 * account the product quantity.
	 * @return a BigDecimal representing the total tax value for the product.
	 */
	public BigDecimal calcTaxes() {
		return product.calcTotalTaxes().multiply(new BigDecimal(quantity));
	}
	
	/**
	 * Calculate the total shelf price of the {@link TaxableProduct}, taking
	 * into account its quantity.
	 * @return a BigDecimal representing the total tax value for the product.
	 */
	public BigDecimal calcShelfPrice() {
		return product.calcShelfPrice().multiply(new BigDecimal(quantity));
	}
	
	
	
	// getters and setters
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
	

	// toString method override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(quantity);
		sb.append(" " + product.getDescription());
		sb.append(": " + CurrencyUtilities.formatCurrency(calcShelfPrice()));
		return sb.toString();
	}
	
}
