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
    <h1>User Account Details</h1>
    
    <!-- Display user details -->
    <% User user = (User) session.getAttribute("user") ; %>
    <p>First Name: <%= user.getFname() %></p>
    <p>Last Name: <%= user.getLname() %></p>
    <p>Email: <%= user.getEmail() %></p>
    <p>Phone Number: <%= user.getPhoneNo() %></p>
    <!-- Add more fields as needed -->

    <!-- Edit user details form -->
    <h2>Edit Details</h2>
    <form action="EditUserServlet" method="post">
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
</body>
</html>
