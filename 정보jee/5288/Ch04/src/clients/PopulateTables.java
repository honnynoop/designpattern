package clients;

import entity.city.*;
import entity.flight.*;
import entity.hotel.*;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;

public class PopulateTables {

	public static void main(String[] args) {
		PopulateTables me = new PopulateTables();
		me.addCityData();
		me.addFlightData();
		me.addHotelData();
	}

	private void addCityData() {

		try	{
			InitialContext ctx = new InitialContext();
			Object ref = ctx.lookup("ejb/City");
			Object obj =  PortableRemoteObject.narrow(ref, CityHome.class);
			CityHome home = (CityHome) PortableRemoteObject.narrow(ref, CityHome.class);
            home.create("London", "Capital of UK", "Heathrow");
  		    home.create("Paris", "Capital of France", "Charles de Gaulle");
			home.create("Rome", "Capital of Italy", "Rome Airport");
		System.out.println("Cities added");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

	private void addFlightData() {
		try	{
			InitialContext ctx = new InitialContext();
			Object ref = ctx.lookup("ejb/Flight");
			FlightHome home = (FlightHome) PortableRemoteObject.narrow(ref, FlightHome.class);

			home.create("Heathrow", "Charles de Gaulle", "10:00", "10:30", "Air France", 120, "01-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "14:00", "14:30", "Air France", 120, "01-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "10:00", "10:30", "Air France", 120, "02-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "14:00", "14:30", "Air France", 120, "02-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "10:00", "10:30", "Air France", 120, "03-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "14:00", "14:30", "Air France", 120, "03-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "10:00", "10:30", "Air France", 120, "04-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "14:00", "14:30", "Air France", 120, "04-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "10:00", "10:30", "Air France", 120, "05-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "14:00", "14:30", "Air France", 120, "05-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "10:00", "10:30", "Air France", 120, "06-01-02", 69.99);
			home.create("Heathrow", "Charles de Gaulle", "14:00", "14:30", "Air France", 120, "06-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "9:30", "11:30", "Air France", 120, "01-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "15:15", "17:30", "Air France", 120, "01-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "9:30", "11:30", "Air France", 120, "02-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "15:15", "17:30", "Air France", 120, "02-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "9:30", "11:30", "Air France", 120, "03-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "15:15", "17:30", "Air France", 120, "03-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "9:30", "11:30", "Air France", 120, "04-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "15:15", "17:30", "Air France", 120, "04-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "9:30", "11:30", "Air France", 120, "05-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "15:15", "17:30", "Air France", 120, "05-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "9:30", "11:30", "Air France", 120, "06-01-02", 69.99);
			home.create("Charles de Gaulle", "Heathrow", "15:15", "17:30", "Air France", 120, "06-01-02", 69.99);

		System.out.println("flights added");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	private void addHotelData() {
		try	{
			InitialContext ctx = new InitialContext();
			Object ref = ctx.lookup("ejb/Hotel");
			HotelHome home = (HotelHome) PortableRemoteObject.narrow(ref, HotelHome.class);
			home.create("Hotel du Paris", "Paris", 15, "Hotel on the Champs Elysee, with views of the Eiffel Tower", 100);
			home.create("Hotel de la France", "Paris", 5, "5 Star hotel with easy access to the Louvre", 250);
		System.out.println("hotels added");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}

