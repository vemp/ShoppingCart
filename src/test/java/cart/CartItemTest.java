package cart;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import products.TaxableProduct;
import testData.TestData;

public class CartItemTest {

	public CartItem[] items = new CartItem[6];
	public BigDecimal exp;
	public BigDecimal expZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

	@Before
	public void prepareTestObjects() {
		items = new TestData().getItems();
	}

	@Test
	public void testCalcTaxes() {
	}

	@Test
	public void testCalcShelfPrice() {
		assertEquals("1 exempt, non-imported product with net price 0 should return 0 for shelf price", expZero, items[0].calcShelfPrice());
		items[0].setQuantity(3);
		assertEquals("3 exempt, non-imported product with net price 0 should return 0 for shelf price", expZero, items[0].calcShelfPrice());
		
		
		// create a new cartitem each time with different combinations
		exp = new BigDecimal(16.49).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non exempt, non-imported product with net price 14,99 should return 16,49 for shelf price", exp, items[1].calcShelfPrice());
		items[1].setQuantity(5);
		exp = new BigDecimal(82.45).setScale(2, RoundingMode.HALF_UP);
		assertEquals("5 non exempt, non-imported product with net price 14,99 should return 82,45 for shelf price", exp, items[1].calcShelfPrice());

		
		exp = new BigDecimal(10.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 exempt, imported product with net price 10,00 should return 10,50 for shelf price", exp, items[2].calcShelfPrice());
		items[2].setQuantity(7);
		exp = new BigDecimal(73.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("7 exempt, imported product with net price 10,00 should return 73,50 for shelf price", exp, items[2].calcShelfPrice());

		
		exp = new BigDecimal(54.65).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 47,50 should return 54,65 for shelf price", exp, items[3].calcShelfPrice());
		items[3].setQuantity(2);
		exp = new BigDecimal(109.30).setScale(2, RoundingMode.HALF_UP);
		assertEquals("2 non-exempt, imported product with net price 47,50 should return 109,30 for shelf price", exp, items[3].calcShelfPrice());

		
		exp = new BigDecimal(32.19).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 27,99 should return 32,19 for shelf price", exp, items[4].calcShelfPrice());
		items[4].setQuantity(0);
		exp = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("0 non-exempt, imported product with net price 47,50 should return 0 for shelf price", exp, items[4].calcShelfPrice());
		
		// let's try changing the rates and see if everything still works as expected
		exp = new BigDecimal(151).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 100,00 should return 151,00 for shelf price with rates set to 22 and 29 percent", exp, items[5].calcShelfPrice());
		items[5].setQuantity(-3);
		exp = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("a negative quantity of a non-exempt, imported product with net price 100,00 should return 0 for shelf price with rates set to 22 and 29 percent", exp, items[5].calcShelfPrice());
	}

}
