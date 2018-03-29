package cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import utils.CurrencyUtilities;

/**
 * The ShoppingCart class represents a shopping cart, which can contain
 * different products. Each product is added as a {@link CartItem } object
 * with its quantity, and contributes to the total tax value of the cart
 * and to the total shelf price of the cart.
 * <p>
 * The class provides a calcTotalTaxes method which returns the total tax
 * value and a calcShelfPrice method which returns the total shelf price
 * value for all items in the cart.
 * <p>
 * It also provides a toString() method to print out the cart.
 * @author Francesco Venturini - 24.03.2018
 *
 */
public class ShoppingCart {

	private List<CartItem> items = new ArrayList<CartItem>();

	/**
	 * Calculate total taxes for all the {@link CartItem}s in the shopping cart.
	 * @return a BigDecimal representing the total tax value for the cart.
	 */
	public BigDecimal calcTotalTaxes() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem c: items) {
			total = total.add(c.calcTaxes());
		}
		return CurrencyUtilities.roundTwoDecimals(total);
	}
	
	/**
	 * Calculate total shelf price for all the {@link CartItem}s in the shopping cart.
	 * @return a BigDecimal representing the total shelf price value for the cart.
	 */
	public BigDecimal calcTotalShelfPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem c: items) {
			total = total.add(c.calcShelfPrice());
		}
		return CurrencyUtilities.roundTwoDecimals(total);
	}
	
	
	
	// getters and setters
	public List<CartItem> getItems() {
		return items;
	}
 
	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	
	
	// toString method override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (CartItem c: items) {
			sb.append(c.toString() + "\n");
		}
		sb.append("Sales taxes: " + CurrencyUtilities.formatCurrency(calcTotalTaxes()) + "\n");
		sb.append("Total: " + CurrencyUtilities.formatCurrency(calcTotalShelfPrice()) + "\n");
		return sb.toString();
	}

}
