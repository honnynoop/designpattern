package facade.bookingmsg;

public class NotEnoughRoomsException extends Exception {
  public NotEnoughRoomsException(String id) {
	super("Not enough rooms in hotel " + id);
  }   
}
