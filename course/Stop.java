package course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Stop implements Serializable {

	private static final long serialVersionUID = -4285967733695047959L;
	public long getSerialversionuid() {
		return serialVersionUID;
	}

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
		}
		return null;
	}
	
	private static List<Stop> SortToId(List<Stop> tmp) throws Exception
	{
		return tmp
				.stream()
				.sorted(Comparator.comparingInt(Stop::getId))
				.collect(Collectors.toList()); 
	}


	@SuppressWarnings("unchecked")
	public static List<Stop> input() throws IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Stops.bin"))) {
			List<Stop> tmp = (List<Stop>) ois.readObject();
			return SortToId(tmp);
		}
		catch (Exception e) {
			List<Stop> s = new ArrayList<Stop>();
			out(s);
			return s;
		}
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
