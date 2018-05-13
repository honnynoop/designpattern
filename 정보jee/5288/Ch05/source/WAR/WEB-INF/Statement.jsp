<%@page contentType="text/html" import="java.util.*,ejb.AccountDetailVO"%>
<html>
    <head>
        <title>Account Statement</title>
    </head>
    <body>

        <jsp:useBean id="statement" scope="request" type="java.util.ArrayList" />
        <table>
            <tr>
                <th>Date</th>
                <th>Amount</th>
                <th>Type</th>
                <th>Description</th>
                <th>Reference</th>
            </tr>
            <%
                Iterator it = statement.iterator();
                while(it.hasNext()) {
                    AccountDetailVO vo = (AccountDetailVO)it.next();
            %>
            <tr>
                <td><%= vo.transactionDate %></td>
                <td><%= vo.amount %></td>
                <td><%= vo.transactionType %></td>
                <td><%= vo.description %></td>
                <td><%= vo.refNum %></td>
            </tr>
            <%
                }
            %>
        </table>
        

        <a href="balance.do">View Balance</a>
    </body>
</html>