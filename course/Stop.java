package course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Stop implements Serializable {

	private static final long serialVersionUID = -4285967733695047959L;
	private int id;
	private String title;

	public Stop(int id, String title) {
		this.setId(id);
		this.title = title;
	}

	public static void out(List<Stop> r) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Stops.bin"))) {
			oos.writeObject(r);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static List<Stop> input(File file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (List<Stop>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public static List<Stop> input() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Stops.bin"))) {
			return (List<Stop>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return title;
	}
}
