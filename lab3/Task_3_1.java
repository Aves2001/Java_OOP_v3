package lab3;

import java.util.Scanner;

public class Task_3_1 {
	private static Scanner scanner;

	public static void main(String[] args) {
		System.out.println("������ 3. � ����� ���� ����� �, ���� ���� �� ������� � ����, ��������� ����������� ����� � ������ �. \n������ ����������� � �����\n");
		try {
			scanner = new Scanner(System.in);
			
			System.out.print("������ �����: ");
			String str = scanner.nextLine();
			
			System.out.println("\n������� �����: " + str + "\n");
			str = test(str);

			System.out.println("���������: " + str + "\n");
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}
	}

	public static String test(String str) {
		String[] arrStr = str.split(" ", 0);
		char[] tmp;
		for (int i = 0; i < arrStr.length; i++) {
			tmp = arrStr[i].toCharArray();
			
			for (int j = 0; j < tmp.length; j++) {
				if ((tmp[j] == '�' || tmp[j] == '�') &&  1+j != tmp.length && tmp[1+j] == '�') {
//					System.out.println(arrStr[i]);
//					System.out.println("i = " + i);
//					System.out.println("j = " + j );
					tmp[1+j] = '�';
					
					int num = 0;
					scanner = new Scanner(System.in);
					do {
						System.out.println("������� �� ��������� �������� �����: " + arrStr[i]);
						System.out.println("1) " + new String(tmp));
						System.out.println("2) " + arrStr[i]);
						num = scanner.nextInt();
					}while(num != 1 && num != 2);
					
					if (num == 1) {
						arrStr[i] = new String(tmp);
					}
					else if (num == 2) {}
					else { new Exception("����"); }
				}
			}
		}
		return String.join(" ", arrStr);
	}
}
