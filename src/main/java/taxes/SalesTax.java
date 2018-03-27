package taxes;

import java.math.BigDecimal;

import products.TaxableProduct;
import utils.CurrencyUtilities;

/**
 * The SalesTax class represents a single applicable sales tax, identified by its rate
 * (expressed as a percentile value, i.e. "10" means 10%), its description and a boolean
 * telling if the item it applies to is exempt from it.
 * @author Francesco Venturini - 27.03.2018
 *
 */
public class SalesTax {
	
	private BigDecimal rate = new BigDecimal(0);
	private String description;
	private boolean exemption = false;
	
	public SalesTax() {}
	
	public SalesTax(BigDecimal rate) {
		this.rate = rate;
	}
	
	/**
	 * Create a new SalesTax instance with given rate, description and exemption
	 * @param rate - the percentile value of the tax rate, i.e. 10 for 10%
	 * @param description - the description of the tax, i.e. "basic sales tax"
	 * @param exemption - whether the tax value should be effectively applied or not
	 */
	public SalesTax(BigDecimal rate, String description, boolean exemption) {
		this.rate = rate;
		this.description = description;
		this.exemption = exemption;
	}
	
	/**
	 * Calculate the tax value for a given TaxableProduct.<br>
	 * Rounding rule: (net * rate/100 rounded up to the nearest 0.05)
	 * @param p
	 * @return the rounded BigDecimal value
	 */
	public BigDecimal calculateTaxValue(TaxableProduct p) {
		if (exemption) {
			return BigDecimal.ZERO;
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isExemption() {
		return exemption;
	}

	public void setExemption(boolean exemption) {
		this.exemption = exemption;
	}

}
