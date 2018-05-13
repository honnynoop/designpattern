package clients;

import delegate.CityBreakDelegate;
import vo.*;
import java.util.Iterator;

public class Step2 {

	public static void main(String[] args) 	{
		Step2 me = new Step2();
		try	{
			me.getCityInfo(args[0]);
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: cityId");
		}
	}

	private void getCityInfo(String id) {
		
		CityBreakDelegate cbdel = new CityBreakDelegate();
			System.out.println("CityBreakDelegate() object created..\n");
		Long lngId = new Long(id);
		CityBreakVO vo = cbdel.getInfoOnCity(lngId);
	System.out.println("CityBreakVO object created..\n");
		
		System.out.println("City name: " + vo.cityName);
		System.out.println("City Description: " + vo.cityDesc);

		Iterator iter = vo.getHotels().iterator();
		while (iter.hasNext()) {
			HotelVO hvo = (HotelVO) iter.next();
			System.out.println(hvo.name);
		}
	}
}
