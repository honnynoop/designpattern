package dataaccess;

public class OptimisticLockException extends DataAccessException{
  public OptimisticLockException(String pExceptionMsg){
    super(pExceptionMsg);
  } 

  public OptimisticLockException(String pExceptionMsg, Throwable pException){
    super(pExceptionMsg, pException);
  } 
}
