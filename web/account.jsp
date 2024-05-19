<%-- 
    Document   : account
    Created on : 13 May 2024, 2:48:53 pm
    Author     : aneir
--%>
<%@page import="isdmodel.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Account</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <% User user = (User) session.getAttribute("user") ; %>
    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <a class="active" href="account.jsp">Account</a>
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
                <a class="node" href="logout.jsp">Logout</a>
        </div>
    </div>
    
    <h1>User Account Details</h1>
    
    <!-- Display user details -->
    <%if(user!=null) {%>
    <p>First Name: <%= user.getFname() %></p>
    <p>Last Name: <%= user.getLname() %></p>
    <p>Email: <%= user.getEmail() %></p>
    <p>Phone Number: <%= user.getPhoneNo() %></p>
    <!-- Add more fields as needed -->

    <!-- Edit user details form -->
    <h2>Edit Details</h2>
    <form action="UpdateUserServlet" method="post">
        <input type="text" name="firstName" value="<%= user.getFname() %>" required>
        <input type="text" name="lastName" value="<%= user.getLname() %>" required>
        <input type="email" name="email" value="<%= user.getEmail() %>" required>
        <!-- Add more input fields for other details -->
        <input type="submit" value="Save Changes">
    </form>

    <!-- Delete account button -->
    <h2>Delete Account</h2>
    <form action="DeleteUserServlet" method="post">
        <input type="submit" value="Delete Account">
    </form>
    <% } %>
</body>
</html>
