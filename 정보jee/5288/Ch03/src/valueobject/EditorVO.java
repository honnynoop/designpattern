package valueobject;

import java.util.HashMap;

public class EditorVO extends ValueObject{
  private long   editorId;
  private String firstName;
  private String middleName;
  private String lastName;


  public EditorVO(){
    editorId     = 0;
    firstName    = "";
    middleName   = "";
    lastName     = "";
  }


  public long getEditorId(){
    return editorId;
  }

  public String getFirstName(){
    return firstName;
  }

  public String getMiddleName(){
    return middleName;
  }

  public String getLastName(){
    return lastName;
  }

  public void setEditorId(long pEditorId){
    setUpdateFlag(true);
    editorId = pEditorId;
  }

  public void setFirstName(String pFirstName){
    setUpdateFlag(true);
    firstName = pFirstName;
  }

  public void setMiddleName(String pMiddleName){
    setUpdateFlag(true);
    middleName = pMiddleName;
  }

  public void setLastName(String pLastName){
    setUpdateFlag(true);
    lastName = pLastName;
  }
}
