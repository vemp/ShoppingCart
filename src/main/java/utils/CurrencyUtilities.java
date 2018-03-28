package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * The CurrencyUtilities class provides some utility methods to round BigDecimal numbers
 * and return them as strings formatted with the current locale's currency symbol.
 * @author Francesco Venturini - 28.03.2018
 *
 */
public class CurrencyUtilities {

	/**
	 * utility method to round a BigDecimal at the nearest five cents going up
	 * @param bd the BigDecimal to round
	 * @return a BigDecimal rounded up to the nearest five cents
	 */
	public static BigDecimal roundUpToFiveCents(BigDecimal bd) {
		bd = bd.signum() == 0 ? bd : (bd.divide(new BigDecimal(0.05), 0, RoundingMode.UP)).multiply(new BigDecimal(0.05));
		return bd.setScale(2, RoundingMode.HALF_UP);
	}
	
	
	/**
	 * utility method to round up to two decimal places, useful for BigDecimal comparisons
	 * @param bd the BigDecimal to round
	 * @return a BigDecimal rounded up to two decimal places
	 */
	public static BigDecimal roundTwoDecimals(BigDecimal bd) {
		return bd.setScale(2, RoundingMode.HALF_UP);
	}
	
	
	/**
	 * utility method to format BigDecimal as a currency, 
	 * based on the default locale
	 * @param bd the BigDecimal to format
	 * @return a String representing the input using current locale
	 */
	public static String formatCurrency(BigDecimal bd) {
		return NumberFormat.getCurrencyInstance().format(bd);
	}

}
