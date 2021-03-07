package lab2;
import static java.lang.Math.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Variant3 {
	private static double[] x;
	private static double[] y;
	private static Scanner scaner;
	
	public static int minIndex; // індекс мінімального елемента в масиві y
	public static int maxIndex; // індекс максимального елемента в масиві y
	public static final int EPS = 12;
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public static void main(String[] args) {
		Variant3 prog = new Variant3();
		prog.run();
	}

	public void run() {
		double dx, a, b;
		scaner = new Scanner(System.in);
		try {
			System.out.printf("dx = ");
				dx = scaner.nextDouble();
				if (dx <= 0) {
					throw new Exception("Крок не може бути менше або дорівнювати 0");
				}
			System.out.printf("a = ");
				a = scaner.nextDouble();
			System.out.printf("b = ");
				b = scaner.nextDouble();
				if (b - a < 0) {
					throw new Exception("\"а\" має бути менше або дорівнювати \"b\"");
				}
			System.out.println();
			genArray(dx, a, b);
			showMinMaxValue();
			showSummSerAg();
		}
		catch(InputMismatchException e) {
			System.out.println("Помилка: Потрібно ввести число");
		}
		catch (Exception e) {
			System.out.println("Помилка: " + e.getMessage());
		}
	}
	
	public void showSummSerAg() {
		
		System.out.printf("Сума значень функції: %.3f\n", arraySummValueY());
		System.out.printf("Середнє арифметичне значень функції: %.3f\n", arraySerAgValueY());
	}
	
	public void showMinMaxValue() {
		System.out.printf("Min: %d елемент по y = %.3f\n", minIndex, y[minIndex]);
		System.out.printf("     %d елемент по x = %.3f\n\n", minIndex, x[minIndex]);
		
		System.out.printf("Max: %d елемент по y = %.3f\n",  maxIndex, y[maxIndex]);
		System.out.printf("     %d елемент по x = %.3f\n\n",  maxIndex, x[maxIndex]);
	}
	
	public double arraySerAgValueY() {
		return (arraySummValueY() / y.length);
	}
	
	public double arraySummValueY() {
		double summ = 0;
		for (double d : y) {
			summ += d;
		}
		return round(summ, EPS);
	}
	
	public int arrayMinValueIndexY() {
		double min = y[0];
		int min_index = 0;
		for (int i = 0; i < y.length; i++) {
			if (y[i] < min) {
				min = y[i];
				min_index = i;
			}
		}
		return min_index;
	}
	
	public int arrayMaxValueIndexY() {
		double max = y[0];
		int max_index = 0;
		for (int i = 0; i < y.length; i++) {
			if (y[i] >= max) {
				max = y[i];
				max_index = i;
			}
		}
		return max_index;
	}
	
	public double showArrayIndexX(int index) {
		return x[index];
	}
	
	public double showArrayIndexY(int index) {
		return y[index];
	}
	
	public void genArray(double dx, double a, double b) {
		genArrayX(dx, a, b);
		genArrayY(dx, a, b);
		minIndex = arrayMinValueIndexY();
		maxIndex = arrayMaxValueIndexY();
	}

	public double[] genArrayX(double dx, double a, double b) {
		x = new double[calcSizeArrayDX(dx, a, b)];
		int i = 0;
		while (a <= b) {
			x[i] = a;
			a = round(a += dx, EPS);
			i++;
		}
		return x;
	}
	
	public double[] genArrayY(double dx, double a, double b) {
		y = new double[calcSizeArrayDX(dx, a, b)];
		int i = 0;
		while (a <= b) {
			y[i] = calcY(a);
			a = round(a += dx, EPS);
			i++;
		}
		return y;
	}
	
	public int calcSizeArrayDX(double dx, double a, double b) {
		int k = 0;
		while (a <= b) {
			a = round(a += dx, EPS);
			k++;
		}
		return k;
	}
	
	public double calcY(double x) {
		double a = 2.8;
		double b = -0.3;
		int c = 4;
		double y = 0;
		
		if (x < 1.4) {
			y = (a * pow(x, 2)) + (b * x) + c;
		}
		else if (x == 1.4) {
			y = a / x + sqrt((pow(x, 2) + 1));
		}
		else if (x > 1.4) {
			y = (a + (b * x)) / sqrt(pow(x, 2) + 1);
		}
		y = round(y, EPS);
		return y;
	}
}
