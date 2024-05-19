
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

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <a class="node" href="account.jsp">Account</a>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <a class="node" href="payment.jsp">Payments</a>
        <div class="navBar-right">
            <a class="node" href="register.jsp">Register</a>
            <a class="node" href="login.jsp">Login</a>
        </div>
    </div>
        
        <h1>You have logged out.</h1> 
        <% session.invalidate();%>
    </body>
</html>
