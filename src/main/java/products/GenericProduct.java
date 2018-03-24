package products;

import java.math.BigDecimal;

public abstract class GenericProduct {

	private BigDecimal netPrice;
	private String description;
	
	
	// functions to be implemented in subclasses
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
