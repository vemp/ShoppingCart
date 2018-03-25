package taxes;

import java.math.BigDecimal;

import products.GenericProduct;
import utils.CurrencyUtilities;

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
		return CurrencyUtilities.roundUpToFiveCents(tax);
	}

	
	// setters and getters begin here
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
