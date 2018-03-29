package products;

import java.math.BigDecimal;


/**
 * The GenericProduct class represents an abstract, generic Product,
 * which, as a minimal common set of features, has a net price before
 * all taxes are applied, a description, and knows if it has been imported.
 * <p>It declares a calcShelfPrice() function which will be implemented
 * by subclasses in order to calculate the shelf price of the product
 * after all taxes and other factors are considered.
 * @author Francesco Venturini - 24.03.2018
 *
 */
public abstract class GenericProduct {

	private BigDecimal netPrice = BigDecimal.ZERO;
	private String description;
	private boolean imported = false;

	
	
	// functions to be implemented in subclasses
	public abstract BigDecimal calcShelfPrice();

	
	
	// getters and setters begin here
	public BigDecimal getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}
		
}
