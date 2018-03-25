package products;

import java.math.BigDecimal;


/**
 * The GenericProduct class represents an abstract, generic Product,
 * which, as a minimal common set of features, has a net price before
 * all taxes are applied and a description.
 * <p>It declares a calcShelfPrice() function which will be implemented
 * by subclasses in order to calculate the shelf price of the product
 * after all taxes and other factors are considered.
 * @author Francesco Venturini - 24.03.2018
 *
 */
public abstract class GenericProduct {

	private BigDecimal netPrice;
	private String description;
	
	
	// functions to be implemented in subclasses
	// TODO: remove calcSalesTax() and calcImportDuties() from here?
	// the GenericProduct should not know about specific taxes...
	public abstract BigDecimal calcSalesTax();
	
	public abstract BigDecimal calcImportDuties();
	
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
		
}
