package br.com.erudio.util;

import br.com.erudio.exception.UnsupportedMathOperationException;

public class SimpleMath {

	public static double sum(double ... values) {
		double sum = 0.0;
		for (double d : values) {
			sum += d;
		}
		return sum;
	}
	
	public static double sub(double d1, double d2) {
		return d1 - d2;
	}
	
	public static double mult(double d1, double d2) {
		return d1 * d2;
	}
	
	public static double div(double d1, double d2) throws UnsupportedMathOperationException{
		if (d2 == 0.0)
			throw new UnsupportedMathOperationException("division by zero");
		return d1 / d2;
	}
	
	public static double mean(double ...values) {
		return sum(values) / values.length;
	}
	
	public static double pow(double value, double p) {
		return Math.pow(value, p);
	}
	
	public static double sqrt(double value) {
		return Math.sqrt(value);
	}
	
	
}
