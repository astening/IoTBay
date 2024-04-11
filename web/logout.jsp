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
        <link rel="stylesheet" href="css/style.css">
        <title>Logout Page</title>
    </head>
    <body>
        <p>You have logged out click <a href="index.html">here</a> to go back to the home page.<p>
        <% session.invalidate();%>
    </body>
</html>
