package course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flights implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -204990551418375223L;
	private static int i = 0;
	private int[] id_flight1;
//	private String flightName1;

	private int[] id_flight2;
//	private String flightName2;

	public Flights(int[] flight1) {
		this.id_flight1 = flight1;
//		this.flightName1 = flightName1;
	}

	public Flights(int[] flight1, int[] flight2) {
		this.id_flight1 = flight1;
//		this.flightName1 = flightName1;
		this.id_flight2 = flight2;
//		this.flightName2 = flightName2;
	}

	public String getFlightName1() {
		return getFlightName(id_flight1);
	}
	
	public String getFlightName2() {
		return getFlightName(id_flight2);
	}
	
	private String getFlightName(int[] id_flight)
	{
		String str1 = null, str2 = null;
		try {
			str1= Main.stops.stream().filter(s -> s.getId() == id_flight[0]).map(s -> s.getTitle()).toArray()[0].toString();			
		} catch (Exception e) {
			// TODO: handle exception
			if (id_flight != null && id_flight.length > 0) {				
				str1 = "[" + id_flight[0] + "]" + " [Інформація відсутня]";
			}
			else {
				str1 = "[Інформація відсутня]";
			}
		}
		
		try {			
			str2 = " -- " + Main.stops.stream().filter(s -> s.getId() == id_flight[id_flight.length - 1]).map(s -> s.getTitle()).toArray()[0].toString();
		} catch (Exception e) {
			if (id_flight != null && id_flight.length > 0) {				
				str2 = " -- " + "[" + id_flight[id_flight.length - 1] + "]" + " [Інформація відсутня]";
			}
			else {
				str2 = " -- [Інформація відсутня]";
			}
		}
		return str1 + str2;
	}

	public int[] getId_flight1() {
		return id_flight1;
	}

	public int[] getId_flight2() {
		return id_flight2;
	}

	public Object[] getFlight(int num, List<Stop> stops) {
		try {
			if (num == 1) {
				return tmp_add(id_flight1, stops);
			} else if (num == 2) {
				return tmp_add(id_flight2, stops);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("error------------- class Flights ------------- getFlight");
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] tmp_add (int[] id_flight, List<Stop> stops)
	{
		List<String> tmp = new ArrayList<String>();
		String x;
		for (i = 0; i < id_flight.length; i++) {
			try {						
				x = stops.stream().filter(s -> s.getId() == id_flight[i]).map(s -> s.getTitle()).toArray()[0].toString();
			} catch (Exception e) {
				x = "        [" + id_flight[i] + "]" + " [Інформація відсутня]";
			}
			tmp.add(x);
		}
		return tmp.toArray();
	}

	@Override
	public String toString() {
		if (id_flight1 == null && id_flight2 == null) {
			return "0";
		}
		if (id_flight1 == null || id_flight2 == null) {
			return "1";
		} else {
			return "2";
		}
	}
}
