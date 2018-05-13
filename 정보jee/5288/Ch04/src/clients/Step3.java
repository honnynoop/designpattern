package clients;

import delegate.CityBreakDelegate;
import vo.*;
import java.util.Iterator;
import java.util.ArrayList;

public class Step3 {

	public static void main(String[] args) 	{
		Step3 me = new Step3();
		System.out.println("Main of Step 3..\n");
		me.getFlightInfoForOutboundFlight();
		//me.getFlightInfoForInboundFlight();
	}

	private void getFlightInfoForOutboundFlight() {
		
		CityBreakDelegate cbdel = new CityBreakDelegate();
        System.out.println("Created an instance of CityBreakDelegate..\n");
		ArrayList flights = cbdel.getFlightInfoForFlight("02-01-02", "Heathrow", "Charles de Gaulle");		
        System.out.println("Getting the flight details .....\n");
		Iterator iter = flights.iterator();
		while (iter.hasNext()) {
			FlightVO vo = (FlightVO) iter.next();
			System.out.println("Airline: " + vo.airline);
			System.out.println("From " + vo.from + " to " + vo.to);
			System.out.println("Departure time: " + vo.departure);
			System.out.println("Arrival time: " + vo.arrival);
		}
		
	}
	private void getFlightInfoForInboundFlight() {
		
		CityBreakDelegate cbdel = new CityBreakDelegate();
		ArrayList flights = cbdel.getFlightInfoForFlight("02-01-02", "Charles de Gaulle", "Heathrow");		

		Iterator iter = flights.iterator();
		while (iter.hasNext()) {
			FlightVO vo = (FlightVO) iter.next();
			System.out.println("Airline: " + vo.airline);
			System.out.println("From " + vo.from + " to "  + vo.to);
			System.out.println("Departure time: " + vo.departure);
			System.out.println("Arrival time: " + vo.arrival);
		}
		
	}

}
