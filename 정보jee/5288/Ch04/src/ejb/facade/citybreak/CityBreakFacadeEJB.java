package facade.citybreak;

import javax.ejb.*;
import common.*;
import entity.city.*;
import entity.hotel.*;
import entity.flight.*;
import vo.*;
import java.sql.*;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;
import javax.rmi.PortableRemoteObject;

public class CityBreakFacadeEJB implements SessionBean {

	public void ejbCreate() throws CreateException {}
	public void ejbRemove() {}
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void setSessionContext(SessionContext ctx) {}
	public void unsetSessionContext() {}

	//Return a list of cities in database
	public Hashtable getCityList() {
		
		Hashtable tbl = new Hashtable();		

		try	{
			//Get connection to db
			ServiceLocator locator = ServiceLocator.getInstance();
			Connection conn = locator.getDBConn(ServiceLocator.CITYDB);			
			String sql = "SELECT * FROM CityEJBTable";

			/*Executing the sql statement*/
			Statement statement = conn.createStatement();
		    ResultSet rsCities = statement.executeQuery(sql);
		   
			while (rsCities.next()) {
				Long id = new Long(rsCities.getLong("id"));
				tbl.put((Object)id, (Object)rsCities.getString("name"));  
		    }
			
		} catch (Exception e) {
			throw new EJBException(e);
		}
		
		return tbl;
	}

	//Return a CityBreak Value object for a specific city
	public CityBreakVO getInfoOnCity(Long cityId) {
		
		CityBreakVO cbvo = new CityBreakVO();
		try	{
			//First get info on the city 
			ServiceLocator locator = ServiceLocator.getInstance();
			CityLocalHome chome = (CityLocalHome) locator.getEJBLocalHome(ServiceLocator.CITY);
			CityLocal city = chome.findByPrimaryKey(cityId);
			cbvo.cityName = city.getName();
			cbvo.cityDesc = city.getDescription();
			cbvo.airport = city.getAirport();

			//Then get hotels for this city
			HotelLocalHome hhome = (HotelLocalHome) locator.getEJBLocalHome(ServiceLocator.HOTEL);
			Collection hotels = hhome.findByLocation(cbvo.cityName);

			//For each hotel create a Hotel value object
			Iterator iter = hotels.iterator();
			while (iter.hasNext()) {
				Object ref = iter.next();
				HotelLocal hotel = (HotelLocal) ref;
				HotelVO hvo = hotel.getHotelInfo();
				cbvo.addHotel(hvo);
			}
		} catch (Exception e) {
			throw new EJBException(e);
		}
		
		return cbvo;

	}

	//Get flights that match a specific date between one airport and another
	public ArrayList getFlightInfoForFlight(String date, String from, String to) {
		
		ArrayList flights = new ArrayList();

		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			FlightLocalHome fhome = (FlightLocalHome) locator.getEJBLocalHome(ServiceLocator.FLIGHT);
			Collection flightmatchs = fhome.findByFlightDateToFrom(date, from, to);

			Iterator iter = flightmatchs.iterator();
			while (iter.hasNext()) {
				Object ref = iter.next();
				FlightLocal flight = (FlightLocal) ref;
				flights.add(flight.getFlightInfo());
			}

		} catch (Exception e) {
			throw new EJBException(e);
		}

		return flights;
	}
}