package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class CurrencyUtilities {

	/**
	 * utility method to round a BigDecimal at the nearest five cents going up
	 * @param bd
	 * @return
	 */
	public static BigDecimal roundUpToFiveCents(BigDecimal bd) {
		return bd.signum() == 0 ? bd : (bd.divide(new BigDecimal(0.05), 0, RoundingMode.UP)).multiply(new BigDecimal(0.05));
	}
	
	/**
	 * utility method to format BigDecimal as a currency, 
	 * based on the default locale
	 * @param bd
	 * @return
	 */
	public static String formatCurrency(BigDecimal bd) {
		return NumberFormat.getCurrencyInstance().format(bd);
	}

}
