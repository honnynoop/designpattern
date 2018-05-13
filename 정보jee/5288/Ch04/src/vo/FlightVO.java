package vo;

import java.io.Serializable;

public class FlightVO implements Serializable {

	public Long flightId;
	public String from;
	public String to;
	public String departure;
	public String arrival;
	public String airline;
	public int availableSeats;
	public String date;
	public double price;
}
