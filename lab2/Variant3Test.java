package lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class Variant3Test {
	public static final double EPS = 1e-6;
	
	@Test
	public void testCalcY0() {
		System.out.println("-------testCalcY0");
		double x = 0;
		Variant3 instance = new Variant3();
		double expResult = 4;
		double result = instance.calcY(x);
		System.out.println("expResult = " + expResult);
		System.out.println("result = " + result);
		System.out.println();
		assertEquals(expResult, result, EPS);
		}
	
	@Test
	public void testCalcY700() {
		System.out.println("-------testCalcY700");
		double x = 1.4;
		Variant3 instance = new Variant3();
		double expResult = 3.720465053;
		double result = instance.calcY(x);
		System.out.println("expResult = " + expResult);
		System.out.println("result = " + result);
		System.out.println();
		assertEquals(expResult, result, EPS);
		}
	
	@Test
	public void testCalcY1000() {
		System.out.println("-------testCalcY1000");
		double x = 2;
		Variant3 instance = new Variant3();
		double expResult = 0.98387;
		double result = instance.calcY(x);
		System.out.println("expResult = " + expResult);
		System.out.println("result = " + result);
		System.out.println();
		assertEquals(expResult, result, EPS);
		}
	
	@Test
	public void testSizeArr() {
		System.out.println("-------testSizeArr");
		double[] xTest = {1, 2};
		double[] yTest = {1, 2, 3};
		Variant3 instance = new Variant3();

		int result = instance.calcSizeArrayDX(1, 1, 2);
		int result2 = instance.calcSizeArrayDX(1, 1, 3);
		
		System.out.println("expResult1 = " + xTest.length);
		System.out.println("result1 = " + result);
		System.out.println("expResult2 = " + yTest.length);
		System.out.println("result2 = " + result2);
		System.out.println();
		
		assertEquals(xTest.length, result, EPS);
		assertEquals(yTest.length, result2, EPS);
		}
	
	@Test
	public void genArray() {
		System.out.println("-------genArray");
		Variant3 instance = new Variant3();
		
		double[] x = instance.genArrayX(1, 1, 5);
		double[] y = instance.genArrayY(1, -1, 5);

		System.out.println("expResult1 = " + 5.0);
		System.out.println("result1 = " + x[4]);
		System.out.println("expResult2 = " + 4.0);
		System.out.println("result2 = " + y[1]);
		System.out.println();
		
		assertEquals(5.0, x[4], EPS);
		assertEquals(4.0, y[1], EPS);
		}
	
	@Test
	public void showArrayIndex() {
		System.out.println("-------showArrayIndex");
		Variant3 instance = new Variant3();

		instance.genArrayX(1, 1, 5);
		instance.genArrayY(1, -1, 5);
		double res1 = instance.showArrayIndexX(0);
		double res2 = instance.showArrayIndexY(1);
		
		System.out.println("expResult1 = " + 1.0);
		System.out.println("result1 = " + res1);
		System.out.println("expResult2 = " + 4.0);
		System.out.println("result2 = " + res2);
		System.out.println();
		
		assertEquals(1.0, res1, EPS);
		assertEquals(4.0, res2, EPS);
		}
	
	@Test
	public void arrayMaxValueIndexY() {
		System.out.println("-------arrayMaxValueIndexY");
		Variant3 instance = new Variant3();
		double dx = 1;
		double a = -1;
		double b = 1;
		
		instance.genArrayY(dx, a, b);

		int res = instance.arrayMaxValueIndexY();
		for (int i = 0; i < instance.calcSizeArrayDX(dx, a, b); i++) {
			System.out.println(instance.showArrayIndexY(i));
		}
		System.out.println("expResult = " + 0);
		System.out.println("result = " + res);
		System.out.println();
		
		assertEquals(0, res, EPS);
		}
	
	@Test
	public void arrayMinValueIndexY() {
		System.out.println("-------arrayMinValueIndexY");
		Variant3 instance = new Variant3();
		double dx = 1;
		double a = -1;
		double b = 1;
		
		instance.genArrayY(dx, a, b);

		int res = instance.arrayMinValueIndexY();
		for (int i = 0; i < instance.calcSizeArrayDX(dx, a, b); i++) {
			System.out.println(instance.showArrayIndexY(i));
		}
		System.out.println("expResult = " + 1);
		System.out.println("result = " + res);
		System.out.println();
		
		assertEquals(1, res, EPS);
		}
	
	@Test
	public void arraySummValueY() {
		System.out.println("-------arraySummValueY");
		Variant3 instance = new Variant3();
		double dx = 0.002;
		double a = 0;
		double b = 2;
		
		instance.genArrayY(dx, a, b);

		double res = instance.arraySummValueY();
		double expResult = 4285.095778541380;
		
		System.out.println("expResult = " + expResult);
		System.out.println("result = " + res);
		System.out.println();
		
		assertEquals(expResult, res, EPS);
		}
	
	@Test
	public void arraySerAgValueY() {
		System.out.println("-------arraySerAgValueY()");
		Variant3 instance = new Variant3();
		double dx = 0.002;
		double a = 0;
		double b = 2;
		
		instance.genArrayY(dx, a, b);
		double res = instance.arraySerAgValueY();
		double expResult = 4.280814964;
		System.out.println("expResult = " + expResult);
		System.out.println("result = " + res);
		System.out.println();
		
		assertEquals(expResult, res, EPS);
		}
}
