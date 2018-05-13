package clients;

import delegate.CityBreakDelegate;
import java.util.Hashtable;
import java.util.Iterator;

public class Step1 {

	public static void main(String[] args) 	{
		Step1 me = new Step1();
		me.getCities();
	}

	private void getCities() {

		CityBreakDelegate cbdel = new CityBreakDelegate();
		Hashtable cities = cbdel.getListOfCities();
		Iterator iter = cities.values().iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}


	}

}

