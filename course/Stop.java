package course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
			List<Stop> tmp = (List<Stop>) ois.readObject(); 
			return SortToId(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static List<Stop> SortToId(List<Stop> tmp)
	{
		return tmp
				.stream()
				.sorted(Comparator.comparingInt(Stop::getId))
				.collect(Collectors.toList()); 
	}

	@SuppressWarnings("unchecked")
	public static List<Stop> input() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Stops.bin"))) {
			List<Stop> tmp = (List<Stop>) ois.readObject(); 
			return SortToId(tmp);
		} catch (Exception e) {
			Dialog_error.main(null);
		}
		return null;
	}

	public Object[] toArray() {
		Object[] data = new Object[2];
		data[0] = id;
		data[1] = title;
		return data;
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
