package lab1;
import java.util.Date;
import static java.lang.Math.*;

public class OurPrimaryClass {

	public static void main(String[] args) {
		System.out.println("Hello Java!");
		Date d = new Date();
		System.out.println("Date:" +d.toString());
		
		double x;
		x = sin(PI/6);
		System.out.println(x);
	}
}