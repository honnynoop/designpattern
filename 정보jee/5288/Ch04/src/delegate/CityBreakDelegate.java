package delegate;

import common.*;
import facade.citybreak.*;
import javax.jms.*;
import vo.*;
import java.util.Hashtable;
import java.util.ArrayList;

public class CityBreakDelegate {

	private static CityBreakFacade cbFacade = null;
	
	//Get the remote interface to the CityBreakFacade session bean
	private static void setCityBreakFacade() {

		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			CityBreakFacadeHome cbfhome = (CityBreakFacadeHome) locator.getEJBHome(ServiceLocator.CITYBREAKFACADE);
			cbFacade = cbfhome.create();
		} catch (ServiceLocatorException sle) {
			System.out.println("ServiceLocator exception thrown: " + sle);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Return the cities available in the database
	public Hashtable getListOfCities() {

		Hashtable htble = null;

		try {
			if (cbFacade == null) {
				setCityBreakFacade();
			}		
			htble = cbFacade.getCityList();

		} catch (Exception e) {
			System.out.println(e);
		}

		return htble;
	}

	//Get a CityBreak Value Object for a specfic city
	public CityBreakVO getInfoOnCity(Long cityId) {

		CityBreakVO vo = null;

		try {		
			if (cbFacade == null) {
				setCityBreakFacade();
			}

			vo = cbFacade.getInfoOnCity(cityId);
				
		} catch (Exception e) {
			System.out.println(e);
		}

		return vo;
	}

	//Get all flights for a specific date from one airport to another
	public ArrayList getFlightInfoForFlight(String date, String from, String to) {
    
		ArrayList flights = null;
		try	{
			if (cbFacade == null) {
				setCityBreakFacade();
	}

			flights = cbFacade.getFlightInfoForFlight(date, from, to);

		} catch (Exception e) {
			System.out.println(e);
		}

		return flights;
	}

	//Send a Booking Value Object to book a city break
	public void bookCityBreak(Long hotelId, Long flightIdOut, Long flightIdIn, Long customerId, 
							  String fromDate, String toDate, int noSeats, int noRooms, double price) {

		//Create value object and load with parameters
		BookingVO vo = new BookingVO();
		vo.hotelId = hotelId;
		vo.flightIdOut = flightIdOut;
		vo.flightIdIn = flightIdIn;
		vo.customerId = customerId;
		vo.fromDate = fromDate;
		vo.toDate = toDate;
		vo.noSeats = noSeats;
		vo.noRooms = noRooms;
		vo.price = price;

		QueueConnection qc = null;

		//Create a connection to the booking queue and send the value object 
		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			qc = locator.getJMSQueueConn(ServiceLocator.QUEUECONNFACTORY);
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue q = locator.getJMSQueue(ServiceLocator.NEW_BOOKINGS);
			QueueSender qSender = qs.createSender(q);
			ObjectMessage msg = qs.createObjectMessage(vo);
			qSender.send(msg);

		} catch (ServiceLocatorException sle) {
			System.out.println("ServiceLocator exception thrown: " + sle);
		} catch (JMSException jmse) {
			System.out.println("JMS Exception thrown: " + jmse);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (qc != null)	{
				try	{
					qc.close();
				} catch (JMSException jmse)	{}
			}
		}
	}
}
