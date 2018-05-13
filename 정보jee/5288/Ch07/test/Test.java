package com.wrox.test;

import com.wrox.reuse.*;

public class Test {

  public static void main( String[] args ) throws Exception {
    Facade facade = new FacadeImpl();
    ValueObject valueObject = null;
    try {
      valueObject = facade.getValueObject( "ValueObjectA" );
      valueObject.setProperty( "Test_1", "test 1" );
      valueObject.setProperty( "Test_2", "test 2" );
      valueObject.setProperty( null, "test 2" );
    } catch( ValidateException e1 ) {
      System.out.println("Error: " + e1.toString() );
    }
    System.out.println( valueObject.toString() );    
    try {
      valueObject = facade.getValueObject( "ValueObjectB" );
      valueObject.setProperty( "Test_1", "test 1" );
      valueObject.setProperty( "Test_2", "test 2" );
      valueObject.setProperty( "Test_3", null );
    } catch( ValidateException e2 ) {
      System.out.println("Error: " + e2.toString() );
    }
    System.out.println( valueObject.toString() ); 
    Query query = facade.getQuery( "A",  valueObject );
    System.out.println( query.toString() ); 
    query = facade.getQuery( "B",  valueObject );
    System.out.println( query.toString() ); 
  }
}
