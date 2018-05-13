package clients;

import javax.jms.*;
import vo.BookingVO;
import javax.naming.*;

public class Step5 implements MessageListener {

	public static void main(String[] args) 
	{
		Step5 me = new Step5();
		try	{
			Long hid = new Long(args[0]);
			Long foid = new Long(args[1]);
			Long fiid = new Long(args[2]);
			Long cid = new Long(args[3]);
		
			InitialContext ctx = new InitialContext();		
			QueueConnectionFactory qf = (QueueConnectionFactory) ctx.lookup("jms/QueueConnectionFactory");
			QueueConnection qc = qf.createQueueConnection();
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue q = (Queue) ctx.lookup("jms/bookingCompleteQ");
			QueueReceiver qr = qs.createReceiver(q);
			qr.setMessageListener(me);
			qc.start();
	
			me.makeBooking(hid, foid, fiid, cid);

   	    } catch(ArrayIndexOutOfBoundsException ae) {
			System.out.println("Usage: hotelId, flightIdOut, flightIdIn, customerId");
		} catch (Exception e)	{
			System.out.println(e);
		}		
	}

	public Step5() {

	}	
	
	private void makeBooking(Long hotelId, Long flightIdOut, Long flightIdIn, Long customerId) {
		
		BookingVO vo = new BookingVO();
		vo.hotelId = hotelId;
		vo.flightIdOut = flightIdOut;
		vo.flightIdIn = flightIdIn;
		vo.customerId = customerId;
		vo.fromDate = "01-01-02";
		vo.toDate = "03-01-02";
		vo.noSeats = 2;
		vo.noRooms = 1;
		vo.price = 200;

		try	{
			InitialContext ctx = new InitialContext();
			QueueConnectionFactory qf = (QueueConnectionFactory) ctx.lookup("jms/QueueConnectionFactory");
			QueueConnection qc = qf.createQueueConnection();
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue q = (Queue) ctx.lookup("jms/newBookingQ");
			QueueSender qSender = qs.createSender(q);
			ObjectMessage msg = qs.createObjectMessage(vo);
			qSender.send(msg);	
		} catch (Exception e) {
			System.out.println(e);
		}	
		System.out.println("Booking sent..");

	}

	public void onMessage(Message msg) {

		TextMessage txtMsg = (TextMessage)msg;
		System.out.println("msg" + txtMsg);

	}
}
