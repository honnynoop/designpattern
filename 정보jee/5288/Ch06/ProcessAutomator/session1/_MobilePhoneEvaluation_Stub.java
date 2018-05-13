// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package session1;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.util.Date;
import javax.rmi.CORBA.Stub;
import javax.rmi.CORBA.Util;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.RemarshalException;
import org.omg.CORBA.portable.ResponseHandler;
import org.omg.CORBA.portable.ServantObject;


public class _MobilePhoneEvaluation_Stub extends Stub implements MobilePhoneEvaluation {
    
    private static final String[] _type_ids = {
        "RMI:session1.MobilePhoneEvaluation:0000000000000000"
    };
    
    public String[] _ids() { 
        return _type_ids;
    }
    
    public double evaluateConsumption(Date arg0, Date arg1) throws RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("evaluateConsumption", true);
                    out.write_value(arg0,Date.class);
                    out.write_value(arg1,Date.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return in.read_double();
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return evaluateConsumption(arg0,arg1);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("evaluateConsumption",MobilePhoneEvaluation.class);
            if (so == null) {
                return evaluateConsumption(arg0, arg1);
            }
            try {
                Object[] copies = Util.copyObjects(new Object[]{arg0,arg1},_orb());
                Date arg0Copy = (Date) copies[0];
                Date arg1Copy = (Date) copies[1];
                return ((MobilePhoneEvaluation)so.servant).evaluateConsumption(arg0Copy, arg1Copy);
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
}
