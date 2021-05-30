package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Toy {

	private static String FileName = "dbase.txt";

	private TypeToys typeToys;
	private String name;
	private Integer price;
	private AgeLimits ageLimits;
	private String parameter;

	public Toy() {
		this.typeToys = TypeToys.NULL;
		this.name = "";
		this.price = 0;
		this.ageLimits = new AgeLimits(0, 0);
		this.parameter = "";
	}

	public Toy(TypeToys typeToys, String name, Integer price, AgeLimits ageLimits, String parameter) {
		this.typeToys = typeToys;
		this.name = name;
		this.price = price;
		this.ageLimits = ageLimits;
		this.parameter = parameter;
	}

	public void setAll(Toy toy) {
		this.typeToys = toy.typeToys;
		this.name = toy.name;
		this.price = toy.price;
		this.ageLimits = toy.ageLimits;
		this.parameter = toy.parameter;
	}

	public TypeToys getTypeToys() {
		return typeToys;
	}

	public void setTypeToys(TypeToys typeToys) {
		this.typeToys = typeToys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public AgeLimits getAgeLimits() {
		return ageLimits;
	}

	public void setAgeLimits(AgeLimits ageLimits) {
		this.ageLimits = ageLimits;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return String.format("\n%s, %s, %s, %s, %s, %s", typeToys, name, price, ageLimits.getFrom(), ageLimits.getTo(),
				parameter);
	}

	public static void out(List<Toy> toys) throws Exception {
		FileWriter out = new FileWriter(FileName, StandardCharsets.UTF_8);
		try {
			out.write(Arrays.toString(toys.toArray()).replace("[", "").replace("]", "").trim().toString());
		} finally {
			out.close();
		}
	}

	public static Toy convert(String line) throws Exception {
		TypeToys typeToys = null;
		String[] tmp = null;
		tmp = line.split(",");
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = tmp[i].trim();
		}
		for (TypeToys a : TypeToys.values()) {
			if (a.toString().equals(tmp[0])) {
				typeToys = a;
				break;
			}
			typeToys = TypeToys.NULL;
		}
		String tmp5;
		if (tmp.length == 5) {
			tmp5 = "";
		} else {
			tmp5 = tmp[5];
		}
		return new Toy(typeToys, tmp[1], Integer.parseInt(tmp[2]), new AgeLimits(tmp[3], tmp[4]), tmp5);
	}

	public static List<Toy> input(File file) throws Exception {
		BufferedReader in = null;
		try {
			if (file.length() == 0) {
				throw new Exception("Файл порожній");
			}
			in = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
			List<Toy> Toys = new ArrayList<Toy>();
			String line = null;
			List<String> error = new ArrayList<>();
			while ((line = in.readLine()) != null) {
				try {
					Toys.add(convert(line));
				} catch (Exception e) {
					error.add(line);
					e.printStackTrace();
				}
			}
			in.close();
			if (error.size() > 0) {
				new Dialog(Arrays.toString(error.toArray()));
			}
			return Toys;
		} catch (Exception e) {
			new Dialog(e.getMessage());
			List<Toy> toys = new ArrayList<Toy>();
			out(toys);
			return toys;
		}
	}

	public static List<Toy> input() throws Exception {
		return input(new File(FileName));
	}
}

class AgeLimits {
	private Integer from;
	private Integer to;

	public AgeLimits(Object from, Object to) {
		this.from = Integer.parseInt(from.toString());
		this.to = Integer.parseInt(to.toString());
	}

	public AgeLimits(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getTo() {
		return to;
	}
}