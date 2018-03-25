package products;

import java.math.BigDecimal;

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
	private static final BigDecimal DEFAULT_SALES_TAX_RATE = new BigDecimal(10);
	private static final BigDecimal DEFAULT_IMPORT_DUTY_RATE = new BigDecimal(5);
	
	
	// private members
	private boolean basicSalesTaxExempt = false;
	private SalesTax basicTax;
	
	private boolean imported = false;	
	private SalesTax importTax;


	public TaxableProduct() {
		initializeTaxes();
	}
	
	public TaxableProduct(BigDecimal netPrice, String description, boolean basicSalesTaxExempt, boolean imported) {
		this.setNetPrice(netPrice);
		this.setDescription(description);
		this.basicSalesTaxExempt = basicSalesTaxExempt;
		this.imported = imported;
		initializeTaxes();
	}
	
	private void initializeTaxes() {
		this.setBasicTax(new SalesTax(basicSalesTaxExempt ? BigDecimal.ZERO : DEFAULT_SALES_TAX_RATE));
		this.setImportTax(new SalesTax(!imported ? BigDecimal.ZERO : DEFAULT_IMPORT_DUTY_RATE));
	}
	
	// abstract superclass implementations
	public BigDecimal calcSalesTax() {
		return basicTax.calculateTaxValue(this);
	}
	
	public BigDecimal calcImportDuties() {
		return importTax.calculateTaxValue(this);
	}

	public BigDecimal calcShelfPrice() {
		return getNetPrice().add(calcSalesTax()).add(calcImportDuties());
	}
	
	
	// toString method for all derived subclasses
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
	
	
	// getters and setters begin here
	public boolean isBasicSalesTaxExempt() {
		return basicSalesTaxExempt;
	}

	public void setBasicSalesTaxExempt(boolean basicSalesTaxExempt) {
		this.basicSalesTaxExempt = basicSalesTaxExempt;
		initializeTaxes();
	}

	public SalesTax getBasicTax() {
		return basicTax;
	}

	public void setBasicTax(SalesTax basicTax) {
		this.basicTax = basicTax;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
		initializeTaxes();
	}

	public SalesTax getImportTax() {
		return importTax;
	}

	public void setImportTax(SalesTax importTax) {
		this.importTax = importTax;
	}

}
