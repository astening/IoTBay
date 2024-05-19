<%-- 
    Document   : welcomeRegister
    Created on : 15 May 2024, 5:51:37 pm
    Author     : aneir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>

    <% User user = (User) session.getAttribute("user") ; %>
    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <a class="node" href="account.jsp">Account</a>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <a class="node" href="payment.jsp">Payments</a>
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
        
        <h1>Hello World!</h1>
    </body>
</html>
