<%@page import="java.util.*"%>
<%@page import="javax.naming.*"%>
<%@page import="javax.transaction.*"%>
<%@page import="dataaccess.ServiceLocator"%>
<%@page import="valueobject.*"%>
<%@page import="session.title.*"%>
<%@page import="session.editor.*"%>

<!--
    Remember this JSP is for example purposes only.  In a real-world application
    you should never allow a JSP to directly access the DAO.  You would instead
    access the DAO from a session EJB.
-->

<H1>Example 2 - Using a DAO to insert a title</H1>
<BR/>
<BR/>
<P>
   This JSP page will insert a single record from the Title table using a TitleDAO <BR/>
   and TitleVO object.  It will also establish relationships between the title <BR/>
   and the editor by retrieving 3 editorVO's and then adding them to the HashMap returned <BR/>
   by the getEditors() method on the TitleVO.<BR/>
</P>
<BR/>

<%
     try{
     /*Getting a TitleDAO for manipulating title information*/
 	 ServiceLocator serviceLocator = ServiceLocator.getInstance();

     TitleDAOHome titleDAOHome = (TitleDAOHome) serviceLocator.getEJBHome(ServiceLocator.TITLEDAO);
     TitleDAO     titleDAO     = titleDAOHome.create();

     /*Getting an EditorDAO to retrieve Editor information*/
	 EditorDAOHome editorDAOHome = (EditorDAOHome) serviceLocator.getEJBHome(ServiceLocator.EDITORDAO);
     EditorDAO     editorDAO     = editorDAOHome.create();

     /*Looking up three editors.  I am going to associate these editors to the new title record*/
     EditorVO editorVO1 = (EditorVO) editorDAO.findByPrimaryKey("1");
	 EditorVO editorVO2 = (EditorVO) editorDAO.findByPrimaryKey("2");
     EditorVO editorVO3 = (EditorVO) editorDAO.findByPrimaryKey("3");

     /*Creating a new title vo by calling the TitleVO's createValueObjectMethod*/
       TitleVO newTitleVO = (TitleVO) titleDAO.createValueObject();

     /*Populate the new TitleVO with data*/
       newTitleVO.setTitleDescr("Geek World");
     newTitleVO.setTitleCost(100);

     /*Add the editors I retrieved from the EditorDAO to the TitleVO*/
     newTitleVO.getEditors().put(new Long(editorVO1.getEditorId()), editorVO1);
     newTitleVO.getEditors().put(new Long(editorVO2.getEditorId()), editorVO2);
     newTitleVO.getEditors().put(new Long(editorVO3.getEditorId()), editorVO3);

     /*Insert the record into the DAO*/
     titleDAO.insert(newTitleVO);

     /*Retrieving the record I just inserted into the database*/
     TitleVO  titleVO  = (TitleVO) titleDAO.findByPrimaryKey(new Long(newTitleVO.getTitleId()).toString());

     /*Printing out the TitleVO data just inserted*/
      out.println("<B><P>Record just inserted:</P></B><BR/>");

     out.println("<P>Title id    : " + titleVO.getTitleId()    + "<br/>");
     out.println("<P>Title descr : " + titleVO.getTitleDescr() + "<br/>");
     out.println("<P>Title cost  : " + titleVO.getTitleCost()  + "<br/>");

     out.println("<P>Editor(s) Working on the Title  : <br/>");
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
     out.println("Error ---> " + e.getMessage());
  }
%>
