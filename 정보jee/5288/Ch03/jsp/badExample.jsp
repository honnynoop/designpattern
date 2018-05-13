<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="dataaccess.*"%>

<!--  
   The following JSP page demonstrates a few of the more common mistakes
   made by application developers. 
-->

<table border="1" align="center">
  <tr>
    <td align="center" nowrap>
      Id 
    </td>
    <td align="center" wrap>
      Description
    </td>
    <td align="center" nowrap>
      Cost 
    </td>
  </tr>
<%
   Connection conn = null;
   ResultSet  rs   = null;
   Statement statement= null;

   try{
     /* Problem # 1 */
     Class.forName("org.gjt.mm.mysql.Driver");

     /* Problem #2 */
     conn =  DriverManager.getConnection
          ("jdbc:mysql://127.0.0.1:3306/test/");
     
     StringBuffer sql = new StringBuffer(64);
     sql.append("SELECT                    ");
     sql.append("  title_id    ,           ");
     sql.append("  title_descr ,           ");
     sql.append("  title_cost              ");
     sql.append("FROM                      ");
     sql.append("  title                   ");
     

      statement = conn.createStatement();

     rs   = statement.executeQuery(sql.toString());

     while (rs.next()){
       out.println("<tr>");
       out.println("<td>");
       out.println(  new Long( rs.getLong("title_id") ).toString() );
       out.println("</td>");

       out.println("<td>");
       out.println(   rs.getString("title_descr")  );
       out.println("</td>");

       out.println("<td>");
       out.println(   new Float (rs.getFloat("title_cost") ).toString() );
       out.println("</td>");
       out.println("</tr>");
     }
   }
   catch(SQLException e){
      out.println("SQL Exception Error--> " + e.getMessage());
   }
   finally{
     if (statement!=null) try{ statement.close(); } catch(SQLException e){}
     if (rs!=null) try{ rs.close(); } catch(SQLException e){}
     if (conn!=null) try{ conn.close();} catch(SQLException e){}
   }
%>
</table>
