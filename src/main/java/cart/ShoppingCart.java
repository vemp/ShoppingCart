package cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import taxes.SalesTax;
import utils.CurrencyUtilities;

/**
 * The ShoppingCart class represents a shopping cart, which can contain
 * different GenericProducts. Each product added contributes to the total
 * tax value of the cart, and to the total shelf price of the cart.
 * <p>
 * The class provides a toString() method to print out the cart.
 * @author Francesco Venturini - 24.03.2018
 *
 */
public class ShoppingCart {

	private List<CartItem> items = new ArrayList<CartItem>();

	public BigDecimal calculateTotalTaxes() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem c: items) {
			Map<String, SalesTax> taxes = c.getProduct().getTaxes();
			for (SalesTax t: taxes.values()) {
				total  = total.add(t.calculateTaxValue(c.getProduct()));
			}
//			total = total.add(c.getProduct().calcSalesTax().multiply(new BigDecimal(c.getQuantity())));
//			total = total.add(c.getProduct().calcImportDuties().multiply(new BigDecimal(c.getQuantity())));
		}
		return total;
	}
	
	public BigDecimal calculateTotalShelfPrice() {
		BigDecimal total = calculateTotalTaxes();
		for (CartItem c: items) {
			total = total.add(c.getProduct().getNetPrice().multiply(new BigDecimal(c.getQuantity())));
		}
		return total;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (CartItem c: items) {
			sb.append(c.toString() + "\n");
		}
		sb.append("Sales taxes: " + CurrencyUtilities.formatCurrency(calculateTotalTaxes()) + "\n");
		sb.append("Total: " + CurrencyUtilities.formatCurrency(calculateTotalShelfPrice()) + "\n");
		return sb.toString();
	}



	public List<CartItem> getItems() {
		return items;
	}
 
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
}
