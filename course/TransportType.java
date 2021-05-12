package course;

public enum TransportType {
	bus("Автобус"), Trolleybus("Тролейбус");
	private String name;
	
	TransportType(String string) {
		this.name = string;
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return (String) name.toString();
}
}
