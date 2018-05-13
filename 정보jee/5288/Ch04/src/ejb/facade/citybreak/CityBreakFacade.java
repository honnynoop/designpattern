package facade.citybreak;

import javax.ejb.*;
import java.util.Hashtable;
import java.util.ArrayList;
import vo.CityBreakVO;
import java.rmi.RemoteException;

public interface CityBreakFacade extends EJBObject {

	public Hashtable getCityList() throws RemoteException;
	public CityBreakVO getInfoOnCity(Long cityId) throws RemoteException;
	public ArrayList getFlightInfoForFlight(String date, String from, String to) throws RemoteException;

}