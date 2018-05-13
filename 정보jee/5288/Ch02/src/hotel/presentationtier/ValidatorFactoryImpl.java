package hotel.presentationtier;

import hotel.util.ValidatorFactory;
import hotel.util.Validator;
import javax.servlet.http.HttpServletRequest;

class ValidatorFactoryImpl implements ValidatorFactory {

  /** Creates a Validator instance 
  */
    public Validator createValidator( ) {
      return new Validator() {
        public boolean validate( Object object ) {
         HttpServletRequest request = ( HttpServletRequest )object;
         //validate request fields here if data is not valid return false         
          return true;
        } 
      };
    }
}