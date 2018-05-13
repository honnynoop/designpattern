<html>
  <head>
    <title>Admin Hotel Result Page</title>
  </head>
  <body link="#FFFFFF" vlink="#FFFFFF" alink="#FFFFFF" bgcolor="#FFFFFF" text="#000000"> 
  <H3><center> "<%=request.getAttribute( "result" )%>"</center></H3> 
  <p>
  <jsp:useBean id="hotel" scope="request" class="hotel.util.HotelDetails" />
  <center>
  <h2> Hotel Details for <jsp:getProperty name="hotel" property="name"/> </h2>
  <table border=3>
  <tr>
  <td>
  Hotel Purpose :
  </td>
  <td>
  <jsp:getProperty name="hotel" property="purpose" />
  </td>
  </tr>
  
  <tr>
  <td>
  Hotel Region or Town :
  </td>
  <td>
  <jsp:getProperty name="hotel" property="regionOrTown" />
  </td>
  </tr> 
  
  <tr>
  <td>
  Hotel Type :
  </td>
  <td>
  <jsp:getProperty name="hotel" property="type" />
  </td>
  </tr> 

  <tr>
  <td>
  Hotel Chain :
  </td>
  <td>
  <jsp:getProperty name="hotel" property="chain" />
  </td>
  </tr>
  </table> 
  </center>
  </body>
</html>
