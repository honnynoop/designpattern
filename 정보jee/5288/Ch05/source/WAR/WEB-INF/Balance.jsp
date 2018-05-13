<%@page contentType="text/html" %>
<html>
    <head>
        <title>Account Balance</title>
    </head>
    <body>

        <jsp:useBean id="balance" scope="request" type="java.lang.Double" />
        Your balance is:<b><%= balance %></b>
        <br>
        <a href="statement.do">View Statement</a>

    </body>
</html>
