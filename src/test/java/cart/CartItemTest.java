package cart;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import testData.TestData;

public class CartItemTest {

	private BigDecimal exp;
	private BigDecimal expZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

	private TestData data;

	@Before
	public void prepareTestObjects() {
		data = new TestData();
	}

	@Test
	public void testCalcTaxes() {
		assertEquals("1 exempt, non-imported product with net price 0 should return 0 for tax value", expZero, data.getNullItem().calcTaxes());
		data.getNullItem().setQuantity(3);
		assertEquals("3 exempt, non-imported product with net price 0 should return 0 for tax value", expZero, data.getNullItem().calcTaxes());
		

		// create a new cartitem each time with different combinations
		exp = new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non exempt, non-imported product with net price 14,99 should return 1,50 for tax value", exp, data.getCartItems().get(1).calcTaxes());
		data.getCartItems().get(1).setQuantity(5);
		exp = new BigDecimal(7.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("5 non exempt, non-imported product with net price 14,99 should return 7,50 for tax value", exp, data.getCartItems().get(1).calcTaxes());

		
		exp = new BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 exempt, imported product with net price 10,00 should return 0,50 for tax value", exp, data.getCartItems().get(3).calcTaxes());
		data.getCartItems().get(3).setQuantity(7);
		exp = new BigDecimal(3.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("7 exempt, imported product with net price 10,00 should return 3,50 for tax value", exp, data.getCartItems().get(3).calcTaxes());

		
		exp = new BigDecimal(7.15).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 47,50 should return 7,15 for tax value", exp, data.getCartItems().get(4).calcTaxes());
		data.getCartItems().get(4).setQuantity(2);
		exp = new BigDecimal(14.30).setScale(2, RoundingMode.HALF_UP);
		assertEquals("2 non-exempt, imported product with net price 47,50 should return 14,30 for tax value", exp, data.getCartItems().get(4).calcTaxes());

		
		exp = new BigDecimal(4.20).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 27,99 should return 4,20 for tax value", exp, data.getCartItems().get(5).calcTaxes());
		data.getCartItems().get(5).setQuantity(0);
		exp = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("0 non-exempt, imported product with net price 47,50 should return 0 for tax value", exp, data.getCartItems().get(5).calcTaxes());
		
		// let's try changing the rates and see if everything still works as expected
		CartItem expensiveItem = new CartItem(data.getExpensiveProduct());
		exp = new BigDecimal(51).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 100,00 should return 51,00 for tax value with rates set to 22 and 29 percent", exp, expensiveItem.calcTaxes());
		expensiveItem.setQuantity(-3);
		exp = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("a negative quantity of a non-exempt, imported product with net price 100,00 should return 0 for tax value with rates set to 22 and 29 percent", exp, expensiveItem.calcTaxes());
		
	}

	@Test
	public void testCalcShelfPrice() {
		assertEquals("1 exempt, non-imported product with net price 0 should return 0 for shelf price", expZero, data.getNullItem().calcShelfPrice());
		data.getNullItem().setQuantity(3);
		assertEquals("3 exempt, non-imported product with net price 0 should return 0 for shelf price", expZero, data.getNullItem().calcShelfPrice());
		
		
		// create a new cartitem each time with different combinations
		exp = new BigDecimal(16.49).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non exempt, non-imported product with net price 14,99 should return 16,49 for shelf price", exp, data.getCartItems().get(1).calcShelfPrice());
		data.getCartItems().get(1).setQuantity(5);
		exp = new BigDecimal(82.45).setScale(2, RoundingMode.HALF_UP);
		assertEquals("5 non exempt, non-imported product with net price 14,99 should return 82,45 for shelf price", exp, data.getCartItems().get(1).calcShelfPrice());

		
		exp = new BigDecimal(10.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 exempt, imported product with net price 10,00 should return 10,50 for shelf price", exp, data.getCartItems().get(3).calcShelfPrice());
		data.getCartItems().get(3).setQuantity(7);
		exp = new BigDecimal(73.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("7 exempt, imported product with net price 10,00 should return 73,50 for shelf price", exp, data.getCartItems().get(3).calcShelfPrice());

		
		exp = new BigDecimal(54.65).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 47,50 should return 54,65 for shelf price", exp, data.getCartItems().get(4).calcShelfPrice());
		data.getCartItems().get(4).setQuantity(2);
		exp = new BigDecimal(109.30).setScale(2, RoundingMode.HALF_UP);
		assertEquals("2 non-exempt, imported product with net price 47,50 should return 109,30 for shelf price", exp, data.getCartItems().get(4).calcShelfPrice());

		
		exp = new BigDecimal(32.19).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 27,99 should return 32,19 for shelf price", exp, data.getCartItems().get(5).calcShelfPrice());
		data.getCartItems().get(5).setQuantity(0);
		exp = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("0 non-exempt, imported product with net price 47,50 should return 0 for shelf price", exp, data.getCartItems().get(5).calcShelfPrice());
		
		// let's try changing the rates and see if everything still works as expected
		CartItem expensiveItem = new CartItem(data.getExpensiveProduct());
		exp = new BigDecimal(151).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 non-exempt, imported product with net price 100,00 should return 151,00 for shelf price with rates set to 22 and 29 percent", exp, expensiveItem.calcShelfPrice());
		expensiveItem.setQuantity(-3);
		exp = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("a negative quantity of a non-exempt, imported product with net price 100,00 should return 0 for shelf price with rates set to 22 and 29 percent", exp, expensiveItem.calcShelfPrice());
	}

}
