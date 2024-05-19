<%-- 
    Document   : ErrorRegister
    Created on : 14 May 2024, 11:07:51 am
    Author     : aneir
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="isdmodel.*" %>
<html>
<head>
    <title>Error Registering</title>
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
    <h1>Error Registering</h1>
    <a href="register.jsp">Please try again</a>
</body>
</html>
