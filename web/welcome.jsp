<!-- 
    Document   : test
    Created on : 19 Mar 2024, 1:35:29 pm
    Author     : anna

    Welcome page – Implement the welcome page to retrieve 
    the posted form-data from register and login. 
    The welcome page must use JavaBeans and session to transport 
    the user (registered or logged-in user) data to the main page.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <%
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String gender = request.getParameter("gender");
            String favcol = request.getParameter("favcol");
            String tos = request.getParameter("tos");
        %>
    </head>
    <body style="background-color:<%=favcol%>;">
        <% if (tos == null){ %>
            <p>Sorry, you must agree to the Terms of Service.</p>
            <a href="javascript:history.back()">Click here to go back</a>
        <% } else if (tos.equals("on")){  %>
            <h1>Welcome, <%=name%>!</h1>
            <h3>Your email is <%=email%></h3>
            <p>Your password is <%=password%></p>
            <p>Your gender is <%=gender%></p>
            <p>Your favourite colour is <%=favcol%></p>
        <% } %>
    </body>
</html>