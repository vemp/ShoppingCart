package products;

import java.math.BigDecimal;
import java.text.NumberFormat;

import taxes.SalesTax;

public class TaxableProduct extends GenericProduct {
	
	// constants
	private static final BigDecimal DEFAULT_SALES_TAX_RATE = new BigDecimal(10);
	private static final BigDecimal DEFAULT_IMPORT_DUTY_RATE = new BigDecimal(5);
	
	
	private boolean basicSalesTaxExempt = false;
//	private BigDecimal basicSalesTaxRate = DEFAULT_SALES_TAX_RATE;
	private SalesTax basicTax;
	
	private boolean imported = false;	
//	private BigDecimal importDutiesRate = DEFAULT_IMPORT_DUTY_RATE;
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
		return getBasicTax().calculateTaxValue(this);
	}
	
	public BigDecimal calcImportDuties() {
		return getImportTax().calculateTaxValue(this);
	}

	public BigDecimal calcShelfPrice() {
		return getNetPrice().add(calcSalesTax()).add(calcImportDuties());
	}
	
	
	// toString method for all derived subclasses
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(isImported() ? "imported " : "");
		sb.append(getDescription() + "\n");
		sb.append("Price before taxes and duties: " + formatCurrency(getNetPrice()) + "\n");
		sb.append("Sales taxes: " + formatCurrency(calcSalesTax()) + "\n");
		sb.append(isImported() ? ("Import duties: " + formatCurrency(calcImportDuties()) + "\n") : "");
		sb.append("Final price: " + formatCurrency(calcShelfPrice()) + "\n");
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
