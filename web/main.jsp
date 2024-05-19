<%-- 
    Document   : main
    Created on : 16 May 2024, 11:31:54 pm
    Author     : phoen
--%>
<%@page import="isdmodel.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="isdmodel.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>IOT Bay Main Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body onload="startTime(); resetSearch();">
     <div><span class="time" id="time"></span></div>
        <% 

            User user = (User)session.getAttribute("user");
            
            // Check if the user is a System Admin
            boolean isSystemsAdmin = false;
            if (user != null && user.getPosition().equals("Systems Admin")) {
                isSystemsAdmin = true;
            }
        %>

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="active" href="main.jsp">Home</a>
        <a class="node" href="account.jsp">Account</a>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <% if (user != null && (user.getPosition().equals("Individual") || user.getPosition().equals("Company"))){%>
            <a class="node" href="PaymentMethodServlet?userID=<%=user.getUserID()%>">Payments</a>
        <% } %>
        <% if (user != null && user.getPosition().equals("Systems Admin")) { %>
            <a class="node" href="customerInformationManagement.jsp">Manage Customers</a>
            <a class="node" href="StaffInformationManagement.jsp">Manage Staff</a>
        <% } %>
        <div class="navBar-right">
            <% if (user==null){ %>
                <a class="node" href="register.jsp">Register</a>
                <a class="node" href="login.jsp">Login</a>
            <% } else {%>
                <a class="node" href="logout.jsp">Logout</a>
            <% } %>
        </div>
    </div>     
     
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
            <% if (!isSystemsAdmin && user!=null) { %>
                <br><a class ="button" href="PaymentMethodServlet?userID=<%=user.getUserID()%>"> Edit Payment Details </a><br>
            <% } %>
            <br>
            <br>
        <br><a href="index.jsp">Home Page</a><br>
        <br><a href="orders.jsp">Order Page</a><br>
        <br><a href="orderForm.jsp">Order Form</a><br>
        <br><a href="DeviceCatalogueServlet">Devices</a><br>
         <form action="LogoutServlet" method="post">
            <input class="button" type="submit" value="Log out">
         </form>
             <!-- Check if the user is a System Admin, then display the button -->
             <br>
             <br>
             <br>
            <% if (isSystemsAdmin) { %>
              <a class="button" href="StaffInformationManagement.jsp">Manage Staff</a>
              <a class="button" href="customerInformationManagement.jsp">Manage Customer</a>
           <% } %>
    </body>
</html>