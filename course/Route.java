package course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable  {

	private static final long serialVersionUID = -6621220687991176570L;
	private int id;
	private String routeName;
	private TransportType transportType;
	private int price;
	private int interval;
	private String workTime;
	private Flights flights;
	
	private int num = 1;

	public Route(int id, String routeName, TransportType transportType, int price, int interval, String workTime, Flights flights) {
		this.id = id;
		this.routeName = routeName;
		this.transportType = transportType;
		this.price = price;
		this.interval = interval;
		this.workTime = workTime;
		this.flights = flights;
	}
	
	public int getId() {
		return id;
	}

	public long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPrice() {
		return price;
	}

	public int getInterval() {
		return interval;
	}

	public String getWorkTime() {
		return workTime;
	}

	public int getNum() {
		return num;
	}

	public String getRouteName() {
		return routeName;
	}
	
	public TransportType getTransportType() {
		return transportType;
	}
	public Flights getFlights() {
		return flights;
	}

	public static void out(List<Route> r) throws IOException
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Route.bin"))) {
			oos.writeObject(r);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Route> input(File file)
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			List<Route> tmp = (List<Route>) ois.readObject();
			if (tmp.get(0).getSerialversionuid() == serialVersionUID) {				
				return tmp; 
			}
			} catch (Exception e) {
				return null;
			}
//		catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public static List<Route> input() throws IOException, ClassNotFoundException
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Route.bin"))) {
			return (List<Route>) ois.readObject();
			} catch (IOException e) {
				List<Route> r = new ArrayList<Route>();
				out(r);
				return r;
			}
	}
	
	public String getInfo() {
		return String.format("\nНомер маршруту: %s \n"
				+ "Рейси: %s"
				+ "Тип транспорту: %s \n"
				+ "Вартість проїзду: %s гривень\n"
				+ "Інтервал руху: %s хвилин\n"
				+ "Час роботи: %s \n",
				
				routeName,
				"\n" + "Прямий: " + flights.getFlightName1() + "\nЗворотний: " + flights.getFlightName2() + "\n",
				transportType,
				price,
				interval,
				workTime);
	}

	@Override
	public String toString() {
		// TODO Автоматически созданная заглушка метода
		return String.format("%s", routeName);
	}
}
