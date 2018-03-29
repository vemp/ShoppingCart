package products;

import java.math.BigDecimal;

/**
 * The Book class is provided as a quick example of how the {@link TaxableProduct }
 * class can be extended to create more specific product types, which can add 
 * properties or alter the default values of existing ones (e.g. a Book is always
 * exempt from basic sales tax). 
 * @author Vemp
 *
 */
public class Book extends TaxableProduct {

	private String title;
	private String author;
	private int pages = 0;
	private String isbnCode;
	
	
	public Book() {
		this.setBasicSalesTaxExempt(true);
	};
	
	public Book(BigDecimal netPrice, String title, String author, int pages, String isbnCode) {
		this.setNetPrice(netPrice);
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.isbnCode = isbnCode;
		this.setDescription(author + " - " + title);
		this.setBasicSalesTaxExempt(true);
	}

	
	// getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getIsbnCode() {
		return isbnCode;
	}

	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
	}
	
}
