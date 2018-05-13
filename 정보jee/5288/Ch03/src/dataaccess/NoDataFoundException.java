package dataaccess;

public class NoDataFoundException extends DataAccessException{
  public NoDataFoundException(String pExceptionMsg){
    super(pExceptionMsg);
  } 

  public NoDataFoundException(String pExceptionMsg, Throwable pException){
    super(pExceptionMsg, pException);
  } 
}
