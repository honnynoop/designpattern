package facade.bookingmsg;

import javax.ejb.*;
import javax.jms.*;
import vo.BookingVO;
import common.*;
import entity.booking.*;
import entity.flight.*;
import entity.hotel.*;
import entity.customer.*;

public class BookingMsgFacadeEJB implements MessageDrivenBean, MessageListener {

	public void ejbCreate() {}
	public void ejbRemove() {}
	public void setMessageDrivenContext(MessageDrivenContext ctx) {}

	public void onMessage(Message msg) {
	
		QueueConnection qc = null;

		try	{
			ObjectMessage omsg = (ObjectMessage) msg;
			BookingVO booking = (BookingVO) omsg.getObject();
			ServiceLocator locator = ServiceLocator.getInstance();

			//Make booking			
			BookingLocalHome bhome = (BookingLocalHome) locator.getEJBLocalHome(ServiceLocator.BOOKING);
			BookingLocal bookingejb = bhome.create(booking);
			
			//set available seats on flight Out
			FlightLocalHome fhome = (FlightLocalHome) locator.getEJBLocalHome(ServiceLocator.FLIGHT);
			FlightLocal flightOut = fhome.findByPrimaryKey(booking.flightIdOut);
			if ((flightOut.getAvailableSeats() - booking.noSeats) > 0) {
				flightOut.bookSeats(booking.noSeats);
			} else {
				throw new NotEnoughSeatsException(booking.flightIdOut.toString());
			}

			//set available seats on flight in
			FlightLocal flightIn = fhome.findByPrimaryKey(booking.flightIdIn);
			if ((flightIn.getAvailableSeats() - booking.noSeats) > 0) {
				flightIn.bookSeats(booking.noSeats);
			} else {
				throw new NotEnoughSeatsException(booking.flightIdIn.toString());
			}

			//set available rooms in hotel
			HotelLocalHome hhome = (HotelLocalHome) locator.getEJBLocalHome(ServiceLocator.HOTEL);
			HotelLocal hotel = hhome.findByPrimaryKey(booking.hotelId);
			if ((hotel.getAvailableRooms() - booking.noRooms) > 0) {
				hotel.bookRooms(booking.noRooms);
			} else {
				throw new NotEnoughRoomsException(booking.hotelId.toString());
			}

			//Add booking id to customer
			CustomerLocalHome chome = (CustomerLocalHome) locator.getEJBLocalHome(ServiceLocator.CUSTOMER);
			CustomerLocal customer = chome.findByPrimaryKey(booking.customerId);
			customer.addBooking(bookingejb.getId());

			//Send booking id 
			qc = locator.getJMSQueueConn(ServiceLocator.QUEUECONNFACTORY);
			Queue q = locator.getJMSQueue(ServiceLocator.BOOKING_COMPLETE);
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);			
			QueueSender qSender = qs.createSender(q);
			TextMessage txtMsg = qs.createTextMessage("Booking complete: id = " + bookingejb.getId());
			qSender.send(txtMsg);

		} catch (Exception e) {
			throw new EJBException(e);
		} finally {
			if (qc != null)	{
				try	{
					qc.close();
				} catch (JMSException jmse)	{}
			}
		}

	}
}