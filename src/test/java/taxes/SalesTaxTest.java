package taxes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import products.TaxableProduct;
import testData.TestData;

public class SalesTaxTest {

	public TaxableProduct[] prod = new TaxableProduct[6];
	public BigDecimal exp;
	public BigDecimal expZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

	@Before
	public void prepareTestObjects() {
		prod = new TestData().getProd();
	}
	
	@Test
	public void testCalculateTaxValue() {

		// by default, upon creation an instance of TaxableProduct has two SalesTax-es in the map,
		// so let's test them first with their default behavior.
		assertEquals("An exempt product should return 0 for basic sales tax", expZero, prod[0].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(prod[0]));
		assertEquals("A non-imported product should return 0 for import duties", expZero, prod[0].getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(prod[0]));
		
		// create a new product each time with different combinations
		exp = new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 12,49 should return 1,50 for basic sales tax", exp, prod[1].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(prod[1]));
		
		exp = new BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An exempt product with net price 10,00 should return 0 for basic sales tax", expZero, prod[2].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(prod[2]));
		assertEquals("An imported product with net price 10,00 should return 0,50 for import duties", exp, prod[2].getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(prod[2]));

		exp = new BigDecimal(4.75).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 47,50 should return 4,75 for basic sales tax", exp, prod[3].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(prod[3]));
		exp = new BigDecimal(2.40).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 47,50 should return 2,40 for import duties", exp, prod[3].getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(prod[3]));
		
		exp = new BigDecimal(2.80).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 27,99 should return 2,80 for basic sales tax", exp, prod[4].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(prod[4]));
		exp = new BigDecimal(1.40).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 27,99 should return 1,40 for import duties", exp, prod[4].getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(prod[4]));
		
		// let's try changing the rates and see if everything still works as expected
		exp = new BigDecimal(22).setScale(2, RoundingMode.HALF_UP);
		assertEquals("A product with net price 100,00 should return 22,00 for basic sales tax with the rate set to 22 percent", exp, prod[5].getTaxes().get(TaxableProduct.BASIC_SALES_TAX_KEY).calculateTaxValue(prod[5]));
		exp = new BigDecimal(29).setScale(2, RoundingMode.HALF_UP);
		assertEquals("An imported product with net price 100,00 should return 29,00 for import duties with the rate set to 29 percent", exp, prod[5].getTaxes().get(TaxableProduct.IMPORT_DUTIES_TAX_KEY).calculateTaxValue(prod[5]));
		
	}

}
