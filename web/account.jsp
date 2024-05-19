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
    
            <%
            String existErr = (String) session.getAttribute("existErr");
            String fnameErr = (String) session.getAttribute("fnameErr");
            String lnameErr = (String) session.getAttribute("lnameErr");
            String phoneErr = (String) session.getAttribute("phoneErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String addressErr = (String) session.getAttribute("addressErr");
            String cityErr = (String) session.getAttribute("cityErr");
            String stateErr = (String) session.getAttribute("stateErr");
            String postErr = (String) session.getAttribute("postErr");
            %>
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
    <p>Password: <%= user.getPassword() %></p>
    <p>Address: <%= user.getAddress() %></p>
    <p>City: <%= user.getCity() %></p>
    <p>State: <%= user.getState() %></p>
    <p>Postcode: <%= user.getPostcode() %></p>
    <!-- Add more fields as needed -->

    <!-- Edit user details form -->
    <h2>Edit Details</h2>
    <form action="UpdateUserServlet" method="post">
        <input type="text" placeholder="<%=(fnameErr != null ? fnameErr : "Enter First Name:")%>" id="fName" name="fName" required>
        <input type="text" placeholder="<%=(lnameErr != null ? lnameErr : "Enter Last Name:")%>" id="lName" name="lName" required>
        <input type="number" placeholder="<%=(phoneErr != null ? phoneErr : "Enter Phone Number:")%>" id="phoneNo" name="phoneNo" required>
        <input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email:")%>" id="email" name="email" required>
        <input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password:")%>" id="password" name="password" required>
        <input type="text" placeholder="<%=(addressErr != null ? addressErr : "Enter Address:")%>" id="address" name="address" required>
        <input type="text" placeholder="<%=(cityErr != null ? cityErr : "Enter City:")%>" id="city" name="city">
        <input type="text" placeholder="<%=(stateErr != null ? stateErr : "Enter State:")%>" id="state" name="state" required>
        <input type="number" placeholder="<%=(postErr != null ? postErr : "Enter Postcode:")%>" id="postcode" name="postcode" required>
        
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
