package lab3;

public class Task_3_2 {

	public static void main(String[] args) {
		System.out.println("������ 3. Patient: id, �������, ��'�, �� �������, ������, �������, ����� ������� �����, ĳ�����.");
		System.out.println("������� ����� ��'����. �������:");
		System.out.println("a) ������ ��������, �� ����� �������� ������;");
		System.out.println("b) ������ ��������, ����� ������� ����� � ���� ����������� � �������� ��������.\n");
		
		try {
			Patient[] arrPatient = new Patient[4];
			arrPatient[0] = new Patient("1; �������1; ��'�1; �� �������1; ������1; 380000000001; 00000001; d1");
			arrPatient[1] = new Patient("2; �������2; ��'�2; �� �������2; ������2; 380000000002; 00000002; d2");
			arrPatient[2] = new Patient("3; �������3; ��'�3; �� �������3; ������3; 380000000003; 00000003; d1");
			arrPatient[3] = new Patient("4; �������4; ��'�4; �� �������4; ������4; 380000000004; 00000004; d2");
			
			Patient.Menu(arrPatient);
			
		} catch (Exception e) {
			System.out.println("�������: " + e.getMessage());
		}
	}
}
