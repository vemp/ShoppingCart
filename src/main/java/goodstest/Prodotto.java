package goodstest;

public class Prodotto extends ProdottoGenerico {

	private double salesTaxRate;
	private double importDutiesRate = 5.0;
	
	
	// abstract superclass implementations
	public double calcSalesTax() {
		return getNetValue() * (salesTaxRate / 100.0);
	}
	
	public double calcImportDuties() {
		return isImported() ? (getNetValue() * (importDutiesRate / 100.0)) : 0.0;
	}

	public double calcGrossValue() {
		return getNetValue() + calcSalesTax() + calcImportDuties();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(isImported() ? "imported " : "");
		sb.append(getDescription() + "\n");
		sb.append("Price before taxes and duties: " + getNetValue() + "\n");
		sb.append("Sales taxes: " + calcSalesTax() + "\n");
		sb.append(isImported() ? ("Import duties: " + calcImportDuties() + "\n") : "");
		sb.append("Final price: " + calcGrossValue() + "\n");
		return sb.toString();
	}
	
	// getters and setters begin here
	public double getSalesTaxRate() {
		return salesTaxRate;
	}

	public void setSalesTaxRate(double salesTaxRate) {
		this.salesTaxRate = salesTaxRate;
	}

	public double getImportDutiesRate() {
		return importDutiesRate;
	}

	public void setImportDutiesRate(double importDutiesRate) {
		this.importDutiesRate = importDutiesRate;
	}
	
	
}
