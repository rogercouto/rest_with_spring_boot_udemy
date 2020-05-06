package br.com.erudio.util;

import br.com.erudio.exception.UnsupportedConversionException;

public class NumberUtil {

	public static Long strToLong(String strNumber) throws UnsupportedConversionException {
		if (strNumber.contains(",") || strNumber.contains(".") || !strIsNumeric(strNumber))
			throw new UnsupportedConversionException(String.format("%s is not a number", strNumber));
		return Long.parseLong(strNumber);
	}
	
	public static Double strToDouble(String strNumber) throws UnsupportedConversionException {
		String number = strNumber.replaceAll(",", ".");
		if (!strIsNumeric(number))
			throw new UnsupportedConversionException(String.format("%s is not a number", strNumber));
		return Double.parseDouble(number);
	}
	
	public static boolean strIsNumeric(String strNumber) {
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
	public static boolean isPair(int i) {
		return (i & 1) == 0;
	}
	
	public static boolean isPair(long l) {
		return (l & 1) == 0;
	}
	
}
