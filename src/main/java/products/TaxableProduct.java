package products;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import taxes.SalesTax;
import utils.CurrencyUtilities;

/**
 * The TaxableProduct class represents a generic product, which can
 * have two different tax types applied: a basic sales tax and an import
 * duty tax. A product can be exempt from basic sales tax, and is exempt
 * from import duties if it is not imported.
 * <p>
 * It also provides a toString method to print a product's description,
 * net price, basic sales tax and duty tax values.
 * @author Francesco Venturini - 24.03.2018
 *
 */
public class TaxableProduct extends GenericProduct {
	
	// constants
	public static final String BASIC_SALES_TAX_KEY = "SALES";
	public static final String IMPORT_DUTIES_TAX_KEY = "IMPORT";
	public static final BigDecimal DEFAULT_SALES_TAX_RATE = new BigDecimal(10);
	public static final BigDecimal DEFAULT_IMPORT_DUTY_RATE = new BigDecimal(5);
	public static final String BASIC_SALES_TAX_DESCRIPTION = "Basic sales tax";
	public static final String IMPORT_DUTY_TAX_DESCRIPTION = "Import duties tax";
	
	
	// private members
	private boolean basicSalesTaxExempt = false;
	private Map<String, SalesTax> taxes = new HashMap<String, SalesTax>();
	
	
	public TaxableProduct() {
		initializeTaxes();
	}
	
	public TaxableProduct(BigDecimal netPrice, String description, boolean basicSalesTaxExempt, boolean imported) {
		this.setNetPrice(netPrice);
		this.setDescription(description);
		this.basicSalesTaxExempt = basicSalesTaxExempt;
		this.setImported(imported);
		initializeTaxes();
	}


	/**
	 * Calculate a single tax value for the tax identified by the given key
	 * @param taxKey the key for searching the desired tax in the map
	 * @return BigDecimal the tax value
	 */
	public BigDecimal calcTax(String taxKey) {
		if (taxes.containsKey(taxKey)) {
			return taxes.get(taxKey).calculateTaxValue(this);
		} else {
			return CurrencyUtilities.roundTwoDecimals(BigDecimal.ZERO);
		}
	}

	/**
	 * Calculate the total value of all taxes applied to the product
	 * @return a BigDecimal representing the total tax value
	 */
	public BigDecimal calcTotalTaxes() {
		BigDecimal total = BigDecimal.ZERO;
		for (SalesTax t: taxes.values()) {
			total = total.add(t.calculateTaxValue(this));
		}
		return CurrencyUtilities.roundTwoDecimals(total);
	}

	// abstract superclass implementations
	/**
	 * Calculate the shelf price of the product as the sum of its net price
	 * and all appliable taxes
	 * @return a BigDecimal representing the shelf price
	 */
	public BigDecimal calcShelfPrice() {
		return CurrencyUtilities.roundTwoDecimals(getNetPrice().add(calcTotalTaxes()));
	}
	

	
	// private methods
	private void initializeTaxes() {
		taxes.clear();
		taxes.put(BASIC_SALES_TAX_KEY, new SalesTax(DEFAULT_SALES_TAX_RATE, BASIC_SALES_TAX_DESCRIPTION, basicSalesTaxExempt));
		taxes.put(IMPORT_DUTIES_TAX_KEY, new SalesTax(DEFAULT_IMPORT_DUTY_RATE, IMPORT_DUTY_TAX_DESCRIPTION, !isImported()));
	}

	
	// getters and setters begin here
	public boolean isBasicSalesTaxExempt() {
		return basicSalesTaxExempt;
	}

	public void setBasicSalesTaxExempt(boolean basicSalesTaxExempt) {
		this.basicSalesTaxExempt = basicSalesTaxExempt;
		this.taxes.get(BASIC_SALES_TAX_KEY).setExemption(basicSalesTaxExempt);
	}
	
	public Map<String, SalesTax> getTaxes() {
		return taxes;
	}

	public void setTaxes(Map<String, SalesTax> taxes) {
		if (taxes == null) {
			this.taxes = new HashMap<String, SalesTax>();
		} else {
			this.taxes = taxes;
		}
	}

	// override setImported method in order to update the related SalesTax object
	@Override
	public void setImported(boolean imported) {
		super.setImported(imported);
		this.taxes.get(IMPORT_DUTIES_TAX_KEY).setExemption(!imported);
	}

	
	
	// toString method override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(isImported() ? "imported " : "");
		sb.append(getDescription() + "\n");
		sb.append("Price before taxes and duties: " + CurrencyUtilities.formatCurrency(getNetPrice()) + "\n");
		sb.append("Sales taxes: " + CurrencyUtilities.formatCurrency(taxes.get(BASIC_SALES_TAX_KEY).calculateTaxValue(this)) + "\n");
		sb.append(isImported() ? ("Import duties: " + CurrencyUtilities.formatCurrency(taxes.get(IMPORT_DUTIES_TAX_KEY).calculateTaxValue(this)) + "\n") : "");
		sb.append("Final price: " + CurrencyUtilities.formatCurrency(calcShelfPrice()) + "\n");
		return sb.toString();
	}
	
}
