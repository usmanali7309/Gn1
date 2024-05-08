<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>SCB Splitter</title>    
</head>
<body>
    <%
        String redirectURL = request.getContextPath()+"/main?UID="+session.getAttribute("uName");
        response.sendRedirect(redirectURL);
    %>
</body>
</html>