package lab3;

import java.util.Scanner;

public class Task_3_1 {
	private static Scanner scanner;

	public static void main(String[] args) {
		Task_3_1 task_3_1 = new Task_3_1();

		System.out.println("������ 3. � ����� ���� ����� �, ���� ���� �� ������� � ����, ��������� ����������� ����� � ������ �. \n������ ����������� � �����\n");
		try {
			scanner = new Scanner(System.in);
			
			System.out.print("������ �����: ");
			String str = scanner.nextLine();

			System.out.println("\n������� �����: " + str + "\n");
			str = task_3_1.test(str);

			System.out.println("\n���������: " + str + "\n");
		} catch (Exception e) {
			System.out.println("�������: " + e.getMessage());
		}
	}

	public String test(String str) {
		String[] arrStr = str.split(" ");
		char[] tmp;
		for (int i = 0; i < arrStr.length; i++) {
			tmp = arrStr[i].toCharArray();
			
			for (int j = 0; j < tmp.length - 1; j++) {
				if ((tmp[j] == '�' || tmp[j] == '�') && tmp[1+j] == '�') {
//					System.out.println(arrStr[i]);
//					System.out.println("i = " + i);
//					System.out.println("j = " + j );
					tmp[1+j] = '�';
					
					int num = 0;
					scanner = new Scanner(System.in);
					do {
						System.out.println("\n������� �� ��������� �������� �����: " + arrStr[i]);
						System.out.println("1) " + String.valueOf(tmp));
						System.out.println("2) " + arrStr[i]);
						System.out.print(">");
						num = scanner.nextInt();
						if (num != 1 && num != 2) {
							System.out.println("������ ����� ����");
						}
					}while(num != 1 && num != 2);

					if (num == 1) {
						arrStr[i] = String.valueOf(tmp);
					}
					else if (num == 2) {}
				}
			}
		}
		return String.join(" ", arrStr);
	}
}
