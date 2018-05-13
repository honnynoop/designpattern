<%@page contentType="text/html" isErrorPage="true" import="web.BeanNames" %>
<html>
<head><title>JSP Page</title></head>
<body>

    <%= request.getAttribute(BeanNames.ERROR) %>

</body>
</html>
