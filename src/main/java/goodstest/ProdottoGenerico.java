package goodstest;

public abstract class ProdottoGenerico {
	private double netValue;
	private String description;
	private boolean imported = false;
	
	
	// functions to be implemented in subclasses
	public abstract double calcSalesTax();
	
	public abstract double calcImportDuties();
	
	public abstract double calcGrossValue();


	
	// getters and setters begin here
	public double getNetValue() {
		return netValue;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setNetValue(double netValue) {
		this.netValue = netValue;
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
