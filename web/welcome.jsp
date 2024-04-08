<%-- 
    Document   : test
    Created on : 19 Mar 2024, 1:35:29 pm
    Author     : anna
--%>
<%@page import="isdmodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Welcome Page</title>
        <%
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
        %>
    </head>
    <body>
        
        <h1>Welcome, <%=name%>!</h1>
        <h3>Your username is <%=username%></h3>
        <p>Your password is <%=password%></p>
        <div class ="panel_div">
            <a class="button" href="index.jsp">Cancel</a>
            <a class="button" href="main.jsp">Main</a>
        </div>
        <%
            User user = new User(name, username, password);
            session.setAttribute("user", user);
        %>
    </body>
</html>