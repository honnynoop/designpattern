package valueobject;

import java.util.HashMap;

/*
 * The TitleVO class represents data retrieved from the ValueObject table.
 * It is a ValueObject that extends the ValueObject class.
 *
 */
public class TitleVO extends ValueObject{
  private long   titleId;
  private String titleDescr;
  private float  titleCost;
  private HashMap editors;

  /*Constructor:  Initializes the properties of the class to be empty*/
  public TitleVO(){
    titleId     = 0;
    titleDescr  = "";
    titleCost   = 0; 
    editors     = new HashMap();
  }

  /*Retrieves the title id*/
  public long getTitleId(){
    return titleId;
  }

  /*Retrieves the title descr*/
  public String getTitleDescr(){
    return titleDescr;
  }

  /*Retrieves the title cost*/
  public float getTitleCost(){
    return titleCost;
  }

  /*Returns all the editors associated with a title*/
  public HashMap getEditors(){
    return editors;
  }

  /*Sets the title id*/
  public void setTitleId(long pTitleId){
    setUpdateFlag(true);
    titleId = pTitleId;
  }
  
  /*Sets the title descr*/
  public void setTitleDescr(String pTitleDescr){
    setUpdateFlag(true);
    titleDescr = pTitleDescr;
  }

  /*Sets the title cost*/
  public void setTitleCost(float pTitleCost){
    setUpdateFlag(true);
    titleCost = pTitleCost;
  }

  /*Sets a hashmap with all of the editors*/
  public void setEditors(HashMap pEditors){
    setUpdateFlag(true);
    editors = pEditors;
  }
}
