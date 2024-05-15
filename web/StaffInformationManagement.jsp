<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="isdmodel.User" %>
<%@ page import="isdmodeldao.DBManager" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Staff Information Management</title>
</head>
<body>
    <h1>Staff Information Management</h1>
    
    <!-- Display staff information -->
    <table border="1" id="staff_table">
        <tr>
            <th>User ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Password</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Postcode</th>
            <th>Activation</th>
            <th>Registration Date</th>
            <th>Position</th>
            <th>Action</th>
        </tr>
        
        <%
            List<User> staffList = (List<User>) request.getAttribute("staffList");
            User foundStaff = (User) request.getAttribute("foundStaff");
            
            if (staffList != null && !staffList.isEmpty()) {
                for (User user : staffList) {
        %>
        <tr>
            <td><%= user.getUserID() %></td>
            <td><%= user.getFname() %></td>
            <td><%= user.getLname() %></td>
            <td><%= user.getPhoneNo() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getPassword() %></td>
            <td><%= user.getAddress() %></td>
            <td><%= user.getCity() %></td>
            <td><%= user.getState() %></td>
            <td><%= user.getPostcode() %></td>
            <td><%= user.isActivation() %></td>
            <td><%= user.getRegistrationDate() %></td>
            <td><%= user.getPosition() %></td>
            <td>
                <!-- Add buttons for delete actions -->
                <form action="DeleteStaffServlet" method="post">
                    <input type="hidden" name="userID" value="<%= user.getUserID() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% 
                }
            } else if (foundStaff != null) { // Display single staff member
        %>
        <tr>
            <td><%= foundStaff.getUserID() %></td>
            <td><%= foundStaff.getFname() %></td>
            <td><%= foundStaff.getLname() %></td>
            <td><%= foundStaff.getPhoneNo() %></td>
            <td><%= foundStaff.getPassword() %></td>
            <td><%= foundStaff.getAddress() %></td>
            <td><%= foundStaff.getCity() %></td>
            <td><%= foundStaff.getState() %></td>
            <td><%= foundStaff.getPostcode() %></td>
            <td><%= foundStaff.isActivation() %></td>
            <td><%= foundStaff.getRegistrationDate() %></td>
            <td><%= foundStaff.getPosition() %></td>
            <td>
                <!-- Add buttons for delete actions -->
                <form action="DeleteStaffServlet" method="post">
                    <input type="hidden" name="userID" value="<%= foundStaff.getUserID() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% } else { %>
        <tr>
            <td colspan="13">No staff found</td>
        </tr>
        <% } %>
    </table>

    <!-- Add form for searching staff -->
    <h2> Find Staff Member </h2>
    <form action="FindStaffServlet" method="get">
        First Name: <input type="text" name="fname">
        Last Name: <input type="text" name="lname">
        Position: <input type="text" name="position">
        <input type="submit" value="Search">
    </form>

    <!-- Add form for showing all staff -->
    <h2>Show All Staff </h2>
    <form action="ShowAllStaffServlet" method="get">
    <input type="submit" value="Show All Staff">
    </form>
    
    <!-- Add form for adding new staff -->
    <h2>Add New Staff</h2>
    <form action="AddStaffServlet" method="post">
        First Name: <input type="text" name="fName">
        Last Name: <input type="text" name="lName">
        Phone Number:<input type="number" name="phoneNo">
        Email: <input type="text" name="email">
        Password:<input type="password" name="password">
        Address:<input type="text" name="address">
        City:<input type="text" name="city">
        State:<input type="text" name="state">
        Postcode:<input type="number" name="postcode">
        Position:<input type="text" name="position">
        <input type="submit" value="Add">
    </form>
    
    <!-- Add form for Updating existing staff -->
    <h2> Update Staff</h2>
    <form action="UpdateStaffServlet" method="post">
        Enter the existing users User ID:<br>
                    User ID: <input type="number" name="userID">
                    <br>
                    <br>
        Update their details:            
                    First Name: <input type="text" name="fName">
                    Last Name: <input type="text" name="lName">
                    Phone Number:<input type="number" name="phoneNo">
                    Email: <input type="text" name="email">
                    Password:<input type="password" name="password">
                    Address:<input type="text" name="address">
                    City:<input type="text" name="city">
                    State:<input type="text" name="state">
                    Postcode:<input type="number" name="postcode">
                    Position:<input type="text" name="position">
                    <input type="submit" value="Update">
                </form>
                    
    <!-- Add a link to return to the main page -->
    <a href="main.jsp">Return to Main Page</a>
</body>
</html>