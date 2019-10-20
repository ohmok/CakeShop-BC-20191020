package com.sikiedu.util;

import java.math.BigDecimal;

public class PriceUtil {

	public static float add(float a, float b) {

		BigDecimal bigA = new BigDecimal(Float.toString(a));
		BigDecimal bigB = new BigDecimal(Float.toString(b));

		return bigA.add(bigB).floatValue();
	}

	public static double add(Double a, Double b) {

		BigDecimal bigA = new BigDecimal(Double.toString(a));
		BigDecimal bigB = new BigDecimal(Double.toString(b));

		return bigA.add(bigB).doubleValue();
	}

	public static float substract(float a, float b) {

		BigDecimal bigA = new BigDecimal(Float.toString(a));
		BigDecimal bigB = new BigDecimal(Float.toString(b));

		return bigA.subtract(bigB).floatValue();
	}

	public static double substract(double a, double b) {

		BigDecimal bigA = new BigDecimal(Double.toString(a));
		BigDecimal bigB = new BigDecimal(Double.toString(b));

		return bigA.subtract(bigB).doubleValue();
	}
	
}
