package taxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import products.TaxableProduct;
import testData.TestData;

public class SalesTaxTest {

	private BigDecimal exp;
	private BigDecimal expZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

	private TestData data;

	@Before
	public void prepareTestObjects() {
		data = new TestData();
	}
	
	@Test
	public void testCalculateTaxValue() {

		// by default, upon creation an instance of TaxableProduct has two SalesTax-es in the map,
		// so let's test them first with their default behavior.
		assertEquals("An exempt product should return 0 for basic sales tax", expZero, data.getNullProduct().getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(data.getNullProduct()));
		assertEquals("A non-imported product should return 0 for import duties", expZero, data.getNullProduct().getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(data.getNullProduct()));
		
		assertEquals("An exempt product with net price 12,49 should return 0 for basic sales tax", expZero, data.getProducts().get(0).getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(data.getProducts().get(0)));
		
		exp = new BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An exempt product with net price 10,00 should return 0 for basic sales tax", expZero, data.getProducts().get(3).getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(data.getProducts().get(3)));
		assertEquals("An imported product with net price 10,00 should return 0,50 for import duties", exp, data.getProducts().get(3).getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(data.getProducts().get(3)));

		exp = new BigDecimal(4.75).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 47,50 should return 4,75 for basic sales tax", exp, data.getProducts().get(4).getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(data.getProducts().get(4)));
		exp = new BigDecimal(2.40).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 47,50 should return 2,40 for import duties", exp, data.getProducts().get(4).getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(data.getProducts().get(4)));
		
		exp = new BigDecimal(2.80).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 27,99 should return 2,80 for basic sales tax", exp, data.getProducts().get(5).getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(data.getProducts().get(5)));
		exp = new BigDecimal(1.40).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 27,99 should return 1,40 for import duties", exp, data.getProducts().get(5).getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(data.getProducts().get(5)));
		
		// let's try changing the rates and see if everything still works as expected
		exp = new BigDecimal(22).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 100,00 should return 22,00 for basic sales tax with the rate set to 22 percent", exp, data.getExpensiveProduct().getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(data.getExpensiveProduct()));
		exp = new BigDecimal(29).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 100,00 should return 29,00 for import duties with the rate set to 29 percent", exp, data.getExpensiveProduct().getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(data.getExpensiveProduct()));
		
	}

}
