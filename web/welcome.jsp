<%-- 
    Document   : welcome
    Created on : 16 May 2024, 11:32:28 pm
    Author     : phoen
--%>

<!DOCTYPE html>
<%@page import="isdmodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Welcome Page</title>
        <%
            User user = (User)session.getAttribute("user");
        %>
    </head>
    <body>
        <%if (user.getFname() !=null) {%>
            <h1>Welcome, <%=user.getFname() %> <%=user.getLname() %>!</h1>
        <%}else{%>
            <h1>Welcome!</h1>
        <%}%>
        <h3>Your email is <%=user.getEmail() %></h3>
        <p>Your password is <%=user.getPassword() %></p>
        <div>
           <br>
            <a class="button" href="index.jsp">Cancel</a>
            
            <a class="button" href="main.jsp">Main</a>
            
        </div>
    </body>
</html>
