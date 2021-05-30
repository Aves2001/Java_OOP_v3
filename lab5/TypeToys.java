package lab5;

public enum TypeToys {

	NULL("Невідомо", "Параметр"), doll("Лялька", "Розмір у сантиметрах"), cubes("Кубики", "Кількість у наборі"), ball("М'яч", "Вага у грамах"), constructor("Конструктор", "Кількість конструкцій");

	private String name;
	private String parametr;
	
	TypeToys(String name) {
		this.name = name;
	}
	
	TypeToys(String name, String parametr) {
		this.name = name;
		this.parametr = parametr;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getParametr() {
		return parametr;
	}
}
