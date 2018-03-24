package taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import products.GenericProduct;

public class SalesTax {
	
	private BigDecimal rate;
	
	public SalesTax() {}
	
	public SalesTax(BigDecimal rate) {
		this.rate = rate;
	}
	
	/**
	 * Calculate the tax value for a given GenericProduct.<br>
	 * Rounding rule: (net * rate/100 rounded up to the nearest 0.05)
	 * @param p
	 * @return
	 */
	public BigDecimal calculateTaxValue(GenericProduct p) {
		BigDecimal tax =  p.getNetPrice().multiply(rate.divide(new BigDecimal(100)));
		return tax.signum() == 0 ? tax : (tax.divide(new BigDecimal(0.05), 0, RoundingMode.HALF_UP)).multiply(new BigDecimal(0.05));
	}

	
	// setters and getters begin here
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
