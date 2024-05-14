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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
        %>
    </head>
    <body>
        <%if (name!=null) {%>
            <h1>Welcome, <%=name%>!</h1>
        <%}else{%>
            <h1>Welcome!</h1>
        <%}%>
        <h3>Your email is <%=email%></h3>
        <p>Your password is <%=password%></p>
        <div>
           <br>
            <a class="button" href="index.jsp.html">Cancel</a>
            
            <a class="button" href="main.jsp">Main</a>
            
        </div>
        <%
            User user = new User(name, email, password);
            session.setAttribute("user", user);
        %>
    </body>
</html>
