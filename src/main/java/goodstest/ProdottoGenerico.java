package goodstest;

import java.math.BigDecimal;
import java.text.NumberFormat;

public abstract class ProdottoGenerico {
	private BigDecimal netValue;
	private String description;
	private boolean imported = false;
	
	
	// functions to be implemented in subclasses
	public abstract BigDecimal calcSalesTax();
	
	public abstract BigDecimal calcImportDuties();
	
	public abstract BigDecimal calcGrossValue();

	
	// toString method for all derived subclasses
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(isImported() ? "imported " : "");
		sb.append(getDescription() + "\n");
		sb.append("Price before taxes and duties: " + formatCurrency(getNetValue()) + "\n");
		sb.append("Sales taxes: " + formatCurrency(calcSalesTax()) + "\n");
		sb.append(isImported() ? ("Import duties: " + formatCurrency(calcImportDuties()) + "\n") : "");
		sb.append("Final price: " + formatCurrency(calcGrossValue()) + "\n");
		return sb.toString();
	}
	
	/**
	 * utility method to format BigDecimal as a currency
	 * based on the default locale
	 * @param bd
	 * @return
	 */
	private String formatCurrency(BigDecimal bd) {
		return NumberFormat.getCurrencyInstance().format(bd);
	}

	
	// getters and setters begin here
	public BigDecimal getNetValue() {
		return netValue;
	}
	
	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
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
