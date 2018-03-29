package cart;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import testData.TestData;

public class ShoppingCartTest {

	private BigDecimal exp;
	private BigDecimal expZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

	private TestData data;

	@Before
	public void prepareTestObjects() {
		data = new TestData();
	}

	@Test
	public void testCalcTotalTaxes() {
		// empty cart should return 0
		assertEquals("an empty cart should return 0 for total tax value", expZero, data.getNullCart().calcTotalTaxes());

		exp = new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cart #1 should return 1,50 for total tax value", exp, data.getShoppingCarts().get(0).calcTotalTaxes());

		exp = new BigDecimal(7.65).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cart #2 should return 7,65 for total tax value", exp, data.getShoppingCarts().get(1).calcTotalTaxes());

		exp = new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cart #3 should return 6,70 for total tax value", exp, data.getShoppingCarts().get(2).calcTotalTaxes());
	}

	@Test
	public void testCalcTotalShelfPrice() {
		assertEquals("an empty cart should return 0 for total shelf price", expZero, data.getNullCart().calcTotalShelfPrice());

		exp = new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cart #1 should return 29,83 for total shelf price", exp, data.getShoppingCarts().get(0).calcTotalShelfPrice());

		exp = new BigDecimal(65.15).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cart #2 should return 65,15 for total shelf price", exp, data.getShoppingCarts().get(1).calcTotalShelfPrice());

		exp = new BigDecimal(74.68).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cart #3 should return 74,68 for total shelf price", exp, data.getShoppingCarts().get(2).calcTotalShelfPrice());

	}

}
