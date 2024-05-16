<%-- 
    Document   : logout
    Created on : 1 Apr 2024, 4:51:23 pm
    Author     : anna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Logout Page</title>
    </head>
    <body>
        <h1>You have logged out.</h1> 
        <h3>Click  <a href="index.jsp"> here</a>  to go back to the home page.</h3>
        <% session.invalidate();%>
    </body>
</html>
