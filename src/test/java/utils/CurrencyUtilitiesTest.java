package utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class CurrencyUtilitiesTest extends CurrencyUtilities {

	@Test
	public void testRoundUpToFiveCents() {
		BigDecimal expected = new BigDecimal(1.05).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1.0374 should round up to 1.05", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(1.0374)));
		expected = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		assertEquals("0 should round up to 0", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(0)));
		expected = new BigDecimal(1).setScale(2, RoundingMode.HALF_UP);
		assertEquals("1 should round up to 1", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(1)));
		expected = new BigDecimal(6.75).setScale(2, RoundingMode.HALF_UP);
		assertEquals("6.71 should round up to 6.75", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(6.71)));
		expected = new BigDecimal(9.05).setScale(2, RoundingMode.HALF_UP);
		assertEquals("9.0001 should round up to 9.05", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(9.0001)));
		expected = new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP);
		assertEquals("14.99 should round up to 15.00", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(14.99)));
		expected = new BigDecimal(5.05).setScale(2, RoundingMode.HALF_UP);
		assertEquals("5.05 should round up to 5.05", expected, CurrencyUtilities.roundUpToFiveCents(new BigDecimal(5.05)));
	}

	@Test
	public void testFormatCurrency() {
		assertEquals("9.05 should display as '€ 9,05'", "€ 9,05", CurrencyUtilities.formatCurrency(new BigDecimal(9.05)));
		assertEquals("0 should display as '€ 0,00'", "€ 0,00", CurrencyUtilities.formatCurrency(new BigDecimal(0)));
		assertEquals("10.001 should display as '€ 10,00'", "€ 10,00", CurrencyUtilities.formatCurrency(new BigDecimal(10.001)));
	}

}
