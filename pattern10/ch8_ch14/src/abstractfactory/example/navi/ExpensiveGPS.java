package abstractfactory.example.navi;

public class ExpensiveGPS extends GPS {
	public Location findCurrentLocation() {
		System.out.println("find current location with Expensive GPS") ;
		return null;
	}
}
