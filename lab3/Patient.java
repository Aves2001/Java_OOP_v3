package lab3;

import java.util.Scanner;

public class Patient {
	private static Scanner scanner;
	
	private int id;
	private String lastName;
	private String name;
	private String surname;
	private String Address;
	private String Phone;
	private int medicalCardNumber;
	private String diagnosis;
	
	public Patient() {
		setId(0);
		setLastName("NULL");
		setName("NULL");
		setSurname("NULL");
		setAddress("NULL");
		setPhone("NULL");
		setMedicalCardNumber(0);
		setDiagnosis("NULL");
	}
	
	public Patient(String str) {
		String[] subStr = str.split("; ");

        setId(Integer.parseInt(subStr[0]));
		setLastName(subStr[1]);
		setName(subStr[2]);
		setSurname(subStr[3]);
		setAddress(subStr[4]);
		setPhone(subStr[5]);
		setMedicalCardNumber(Integer.parseInt(subStr[6]));
		setDiagnosis(subStr[7]);
	}
	
	public static int FilterDiagnosis(Patient[] PatientArray, String Diagnosis) {	
		int num = 0;
		for (int i = 0; i < PatientArray.length; i++) {
			if (PatientArray[i] == null) {
				break;
			}
			if(PatientArray[i].getDiagnosis().equals(Diagnosis)) {
				System.out.println(PatientArray[i].toString());
				++num;
			}
		}
		return num;
	}
	
	public static void FilterDiagnosis(Patient[] PatientArray) {
		scanner = new Scanner(System.in);
		String Diagnosis;
		do {
			System.out.print("\nДіагноз: ");
			Diagnosis = scanner.nextLine();
			int num = Patient.FilterDiagnosis(PatientArray, Diagnosis);
			if (num == 0 && Diagnosis != "") {
				System.out.println("За вказаним діагнозом нічого не знайдено");
			}
			else if(Diagnosis != "") {
				System.out.println("\nКількість знайдених записів:  " + num);
			}
		}while(Diagnosis != "");
	}
	
	public static int FilterMedicalCardNumber(Patient[] str, int start, int end) {
		int num = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null) {
				break;
			}
			if(str[i].getMedicalCardNumber() >= start && str[i].getMedicalCardNumber() <= end) {
				System.out.println(str[i].toString());
				++num;
			}
		}
		return num;
	}
	
	public static void FilterMedicalCardNumber(Patient[] str) {
		scanner = new Scanner(System.in);
		int start, end;
		String inputStr;
		do {
		System.out.print("\nВід: ");
		
		inputStr = scanner.nextLine();
		if (inputStr == "") { break; }
		else { start = Integer.parseInt(inputStr); }
		
		System.out.print("До: ");
		inputStr = scanner.nextLine();
		if (inputStr == "") { break; }
		else { end = Integer.parseInt(inputStr); }
		
		int num = FilterMedicalCardNumber(str, start, end);
		if (num == 0 && inputStr != "") {
			System.out.println("\nЗа вказаним діагнозом нічого не знайдено");
		}
		else if(inputStr != "") {
			System.out.println("\nКількість знайдених записів:  " + num);
		}
		}while(inputStr != "");
	}
	
	public static void Menu(Patient[] arrPatient) {
		scanner = new Scanner(System.in);
		String input;
		do {
			System.out.print("-----------------------------------------------------------------------------------");
			System.out.println("\nВиберіть, що потрібно вивести на екран:");
			System.out.println("0) Вихід, або Enter");
			System.out.println("1) список пацієнтів, які мають вказаний діагноз");
			System.out.println("2) список пацієнтів, номер медичної карти у яких знаходиться в заданому інтервалі");
			System.out.print("\nВибрати: ");

			input = scanner.nextLine();
			System.out.print("-----------------------------------------------------------------------------------");

			if (input.equals("1") ) {
				Patient.FilterDiagnosis(arrPatient);
			}
			else if (input.equals("2")) {
				Patient.FilterMedicalCardNumber(arrPatient);
			}
			else if(input != "") {
				System.out.println("Такого пункта немає");
			}
		}while(input != "");
	}
	
	public String toString() {
		return String.format("\n############################"
				+ "\n# ID: %-20s"
				+ "\n# Прізвище: %-20s"
				+ "\n# Ім'я: %-20s"
				+ "\n# По батькові: %-20s"
				+ "\n# Адреса: %-20s"
				+ "\n# Телефон: %-20s"
				+ "\n# № медичної карти: %-20s"
				+ "\n# Діагноз: %-20s",
				getId(),
				getLastName(),
				getName(),
				getSurname(),
				getAddress(),
				getPhone(),
				getMedicalCardNumber(),
				getDiagnosis()
				);
	}
	
	
	
	
	

/////////////////////////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
	
	public int getMedicalCardNumber() {
		return medicalCardNumber;
	}

	public void setMedicalCardNumber(int medicalCardNumber) {
		this.medicalCardNumber = medicalCardNumber;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
}
