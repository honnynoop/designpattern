<%@page import="java.util.*"%>
<%@page import="javax.naming.*"%>
<%@page import="dataaccess.*"%>
<%@page import="valueobject.*"%>
<%@page import="session.title.*"%>

<!--
    Remember this JSP is for example purposes only.  In a real-world application
    you should never allow a JSP to directly access the DAO.
-->
<H1>Example 1 - Using a DAO to retrieve a title</H1>
<BR/>
<BR/>
<P>
   This JSP page will retrieve a single record from the Title table using a TitleDAO <BR/>
   and TitleVO object.  It will also display all of the editors associated with this <BR/>
   Title by calling the getEditors() method on the TitleVO.
</P>
<BR/>
<%
System.out.println("In Select Example jsp page \n");
     try{
 	 /*Getting a TitleDAO and Retrieving a Value*/
	 ServiceLocator serviceLocator = ServiceLocator.getInstance();

     TitleDAOHome titleDAOHome = 
       (TitleDAOHome) serviceLocator.getEJBHome(ServiceLocator.TITLEDAO);

		TitleDAO     titleDAO     = titleDAOHome.create();

     /*Calling the find method and retrieving a Title by title id.*/
     TitleVO  titleVO  = (TitleVO) titleDAO.findByPrimaryKey("1");

     out.println("<P>Title id    : " + titleVO.getTitleId()    + "<br/>");
     out.println("<P>Title descr : " + titleVO.getTitleDescr() + "<br/>");
     out.println("<P>Title cost  : " + titleVO.getTitleCost()  + "<br/>");

     out.println("<P>Editor(s) Working on the Title  : <br/>");

     /*
        Retrieving the editors that the user currently has.  We will then grab an
	iterator off of the HashMap and then display the names of each editor.
     */
     HashMap hashMap = titleVO.getEditors();

     Collection col = hashMap.values();

     Iterator iterator = col.iterator();

     while (iterator.hasNext()){
       EditorVO editorVO = (EditorVO) iterator.next();

       out.println(editorVO.getFirstName() + " " +
                                  editorVO.getMiddleName() + " " +
				  editorVO.getLastName() + "<br/>"); 
     }
	}
   catch(Exception e){
      out.println("error--> " + e.getMessage());
   }
%>
