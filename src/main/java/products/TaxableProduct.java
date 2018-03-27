package products;

import java.math.BigDecimal;
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
	private static final String BASIC_SALES_TAX_KEY = "SALES";
	private static final String IMPORT_DUTIES_TAX_KEY = "IMPORT";
	private static final BigDecimal DEFAULT_SALES_TAX_RATE = new BigDecimal(10);
	private static final BigDecimal DEFAULT_IMPORT_DUTY_RATE = new BigDecimal(5);
	private static final String BASIC_SALES_TAX_DESCRIPTION = "Basic sales tax";
	private static final String IMPORT_DUTY_TAX_DESCRIPTION = "Import duties tax";
	
	
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

	public BigDecimal calcSalesTax() {
		return taxes.get(BASIC_SALES_TAX_KEY).calculateTaxValue(this);
	}
	
	public BigDecimal calcImportDuties() {
		return taxes.get(IMPORT_DUTIES_TAX_KEY).calculateTaxValue(this);
	}

	

	// abstract superclass implementations
	public BigDecimal calcShelfPrice() {
		return getNetPrice().add(calcSalesTax()).add(calcImportDuties());
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
		sb.append("Sales taxes: " + CurrencyUtilities.formatCurrency(calcSalesTax()) + "\n");
		sb.append(isImported() ? ("Import duties: " + CurrencyUtilities.formatCurrency(calcImportDuties()) + "\n") : "");
		sb.append("Final price: " + CurrencyUtilities.formatCurrency(calcShelfPrice()) + "\n");
		return sb.toString();
	}
	
}
