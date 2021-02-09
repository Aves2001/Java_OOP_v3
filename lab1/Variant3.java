package lab1;

import static java.lang.Math.*;
import java.util.Date;
import java.util.Scanner;

public class Variant3 {

	private static Scanner scanner;

	public static void main(String[] args) {
		Variant3 prog = new Variant3();
		prog.run();
	}
	
	private void run() {
//		double a = 3.2;
//		double b = 17.5;
//		double x = -4.8;
		
		System.out.printf("a = ");
			double a = input();
		System.out.printf("b = ");
			double b = input();
		System.out.printf("x = ");
			double x = input();
		
		double y = formulaY(a, b, x);
		double d = formulaD(a, b, x);
		print(y, d);
	}
	
	private static double input() {
		scanner = new Scanner(System.in);
		return scanner.nextDouble();
	}

	private double formulaY(double a, double b, double x) {
		return b*pow(tan(x),2)-a/pow(sin(x/a),2);
	}
	
	private double formulaD(double a, double b, double x) {
		return a*exp(-sqrt(a))*cos(b*x/a);
	}
	
	private void print(double y, double d) {
		System.out.printf("\ny = %.3f\n", y);
		System.out.printf("d = %.3f", d);
		showDate();
	}
	
	private void showDate() {
		Date d = new Date();
		System.out.printf("\nДата: %1$tB, %1$td %1$tY %1$tA", d);
	}

}
