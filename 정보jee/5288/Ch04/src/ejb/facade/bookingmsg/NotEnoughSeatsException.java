package facade.bookingmsg;

public class NotEnoughSeatsException extends Exception {
  public NotEnoughSeatsException(String id) {
	super("Not enough seats for flight " + id);
  }   
}
