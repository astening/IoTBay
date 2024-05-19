<%-- 
    Document   : welcome
    Created on : 16 May 2024, 11:32:28 pm
    Author     : phoen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css">
        <%
            int userID = Integer.parseInt(request.getParameter("userID"));
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            int phoneNo = Integer.parseInt(request.getParameter("phoneNo"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            int postcode = Integer.parseInt(request.getParameter("postcode"));
            boolean activation = Boolean.parseBoolean(request.getParameter("activation"));
            // Assuming registrationDate is a Date parameter
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date registrationDate = null;
            try {
                registrationDate = dateFormat.parse(request.getParameter("registrationDate"));
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle parsing exception
            }
            String position = request.getParameter("position");
        %>
    </head>
    <body>
        <%if (fname!=null) {%>
            <h1>Welcome, <%=fname%> <%=lname%>!</h1>
        <%}else{%>
            <h1>Welcome!</h1>
        <%}%>
        <h3>Your email is <%=user.getEmail() %></h3>
        <p>Your password is <%=user.getPassword() %></p>
        <div>
           <br>
            <a class="button" href="index.jsp.html">Cancel</a>
            
            <a class="button" href="main.jsp">Main</a>
            
        </div>
        <%
            User user = new User(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
            session.setAttribute("user", user);
        %>
    </body>
</html>