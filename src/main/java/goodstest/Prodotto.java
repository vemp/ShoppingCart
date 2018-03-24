package goodstest;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Prodotto extends ProdottoGenerico {

	private BigDecimal salesTaxRate = new BigDecimal(10.0);
	private BigDecimal importDutiesRate = new BigDecimal(5.0);
	
	
	// abstract superclass implementations
	public BigDecimal calcSalesTax() {
		BigDecimal tax =  getNetValue().multiply(salesTaxRate.divide(new BigDecimal(100)));
		return tax.signum() == 0 ? tax : (tax.divide(new BigDecimal(0.05), 0, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.05));
	}
	
	public BigDecimal calcImportDuties() {
		return isImported() ? (getNetValue().multiply(importDutiesRate.divide(new BigDecimal(100)))) : BigDecimal.ZERO;
	}

	public BigDecimal calcGrossValue() {
		return getNetValue().add(calcSalesTax()).add(calcImportDuties());
	}
	
	
	// getters and setters begin here
	public BigDecimal getSalesTaxRate() {
		return salesTaxRate;
	}

	public void setSalesTaxRate(BigDecimal salesTaxRate) {
		this.salesTaxRate = salesTaxRate;
	}

	public BigDecimal getImportDutiesRate() {
		return importDutiesRate;
	}

	public void setImportDutiesRate(BigDecimal importDutiesRate) {
		this.importDutiesRate = importDutiesRate;
	}
	
	
}
