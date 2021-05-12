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
	private String flightName1;

	private int[] id_flight2;
	private String flightName2;

	public Flights(int[] flight1, String flightName1) {
		this.id_flight1 = flight1;
		this.flightName1 = flightName1;
	}

	public Flights(int[] flight1, String flightName1, int[] flight2, String flightName2) {
		this.id_flight1 = flight1;
		this.flightName1 = flightName1;
		this.id_flight2 = flight2;
		this.flightName2 = flightName2;
	}

	public String getFlightName1() {
		return flightName1 != null ? flightName1 : "";
	}

	public String getFlightName2() {
		return flightName2 != null ? flightName2 : "";
	}

	public int[] getId_flight1() {
		return id_flight1;
	}

	public int[] getId_flight2() {
		return id_flight2;
	}

	public Object[] getFlight(int num, List<Stop> stops) {
		List<String> tmp = new ArrayList<String>();
		String x;
		try {
			if (num == 1) {
				for (i = 0; i < id_flight1.length; i++) {
					x = stops.stream().filter(s -> s.getId() == id_flight1[i]).map(s -> s.getTitle()).toArray()[0]
							.toString();
					tmp.add(x);
				}
				return tmp.toArray();
			} else if (num == 2) {
				for (i = 0; i < id_flight2.length; i++) {
					x = stops.stream().filter(s -> s.getId() == id_flight2[i]).map(s -> s.getTitle()).toArray()[0]
							.toString();
					tmp.add(x);
				}
				return tmp.toArray();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("error------------- class Flights ------------- getFlight");
		}
		return null;
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
