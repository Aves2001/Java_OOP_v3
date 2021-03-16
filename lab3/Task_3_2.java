package lab3;

public class Task_3_2 {

	public static void main(String[] args) {
		System.out.println("Варіант 3. Patient: id, Прізвище, Ім'я, По батькові, Адреса, Телефон, Номер медичної карти, Діагноз.");
		System.out.println("Скласти масив об'єктів. Вивести:");
		System.out.println("a) список пацієнтів, які мають вказаний діагноз;");
		System.out.println("b) список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі.\n");
		
		try {
			Patient[] arrPatient = new Patient[4];
			arrPatient[0] = new Patient("1; Прізвище1; Ім'я1; По батькові1; Адреса1; 380000000001; 00000001; d1");
			arrPatient[1] = new Patient("2; Прізвище2; Ім'я2; По батькові2; Адреса2; 380000000002; 00000002; d2");
			arrPatient[2] = new Patient("3; Прізвище3; Ім'я3; По батькові3; Адреса3; 380000000003; 00000003; d1");
			arrPatient[3] = new Patient("4; Прізвище4; Ім'я4; По батькові4; Адреса4; 380000000004; 00000004; d2");
			
			Patient.Menu(arrPatient);
			
		} catch (Exception e) {
			System.out.println("Помилка: " + e.getMessage());
		}
	}
}
