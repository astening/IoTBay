<%-- 
    Document   : main
    Created on : 16 May 2024, 11:31:54 pm
    Author     : phoen
--%>

<%@page import="isdmodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>IOT Bay Main Page</title>
    </head>
    <body>
     <div><span class="time" id="time"></span></div>
        <% 
            User user = (User)session.getAttribute("user");
        %>
        <h1>User Profile</h1>
        <table id="table">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Activation Status</th>
                <th>Registration Date</th>
                <th>Type</th>
            </tr>
            <tr>
                <td>${user.fname} ${user.lname}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.phoneNo}</td>
                <td>${user.address} ${user.city} ${user.state} ${user.postcode}</td>
                <td>${user.activation}</td>
                <td>${user.registrationDate}</td>
                <td>${user.position}</td>
            </tr>
        </table>
            <br>
            <br>
                <a class ="button" href="logout.jsp"> Logout </a>
                
                 <br>
                 <br>
                 <br>
                 <% if (user != null && user.getPosition().equals("Systems Admin")) { %>
                 <p>You are a Systems Admin</p>
                  <a class="button" href="customerInformationManagement.jsp">Customer Management List</a>
                  <%   } %>
                  
    </body>
</html>
