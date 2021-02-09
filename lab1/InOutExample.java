package lab1;

import java.util.*;

public class InOutExample {

	private static Scanner s;

	public static void main(String[] args) {
		s = new Scanner(System.in);
		int i = s.nextInt();
		double x = s.nextDouble();
		System.out.println("i = "+i);
		System.out.println("x = "+x);
	}
}
