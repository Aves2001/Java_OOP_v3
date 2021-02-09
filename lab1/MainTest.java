package lab1;

public class MainTest {

	public static void main(String[] args) {
		MainTest prog = new MainTest();
		prog.run();
	}

	private int calcSquare(int x) {
		return x*x;
	}
	
	private void print(int x, int y) {
		System.out.println("x="+x);
		System.out.println("x^2="+y);
	}
	
	private void run() {
		int x = 5;
		int y = calcSquare(x);
		
		print(x, y);
	}
}
