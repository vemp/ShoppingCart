package taxes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import products.TaxableProduct;

public class SalesTaxTest {

	@Test
	public void testCalculateTaxValue() {

		// by default, upon creation an instance of TaxableProduct has two SalesTax-es in the map,
		// so let's test them first with their default behavior.
		TaxableProduct p = new TaxableProduct();
		
		BigDecimal expected = BigDecimal.ZERO;
		p.setBasicSalesTaxExempt(true);
		assertEquals("An exempt product should return 0 for basic sales tax", expected, p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(p));
		p.setImported(false);
		assertEquals("A non-imported product should return 0 for import duties", expected, p.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(p));
		
		// create a new product each time with different combinations
		p = new TaxableProduct();
		p.setNetPrice(new BigDecimal(14.99));
		expected = new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 12,49 should return 1,50 for basic sales tax", expected, p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(p));
		
		p = new TaxableProduct();
		p.setNetPrice(new BigDecimal(10));
		p.setBasicSalesTaxExempt(true);
		p.setImported(true);
		expected = new BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An exempt product with net price 10,00 should return 0 for basic sales tax", BigDecimal.ZERO, p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(p));
		assertEquals("An imported product with net price 10,00 should return 0,50 for import duties", expected, p.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(p));

		p = new TaxableProduct();
		p.setNetPrice(new BigDecimal(47.50));
		p.setImported(true);
		expected = new BigDecimal(4.75).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 47,50 should return 4,75 for basic sales tax", expected, p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(p));
		expected = new BigDecimal(2.40).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 47,50 should return 2,40 for import duties", expected, p.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(p));
		
		p = new TaxableProduct();
		p.setNetPrice(new BigDecimal(27.99));
		p.setImported(true);
		expected = new BigDecimal(2.80).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 27,99 should return 2,80 for basic sales tax", expected, p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(p));
		expected = new BigDecimal(1.40).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 27,99 should return 1,40 for import duties", expected, p.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(p));
		
		// let's try changing the rates and see if everything still works as expected
		p = new TaxableProduct();
		p.setNetPrice(new BigDecimal(100.00));
		p.setImported(true);
		p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).setRate(new BigDecimal(22));
		p.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).setRate(new BigDecimal(29));
		expected = new BigDecimal(22).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 100,00 should return 22,00 for basic sales tax with the rate set to 22 percent", expected, p.getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(p));
		expected = new BigDecimal(29).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 100,00 should return 29,00 for import duties with the rate set to 29 percent", expected, p.getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(p));
		
	}

}
