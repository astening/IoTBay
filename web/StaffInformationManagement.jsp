<%-- 
    Document   : StaffInformationManagement
    Created on : 15 May 2024, 11:49:59 am
    Author     : William Sinclair
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="isdmodel.User"%>
<%@page import="isdcontroller.ShowAllStaffServlet"%>
<%@page import="isdcontroller.DeleteStaffServlet"%>
<%@page import="isdcontroller.FindStaffServlet"%>
<%@page import="isdcontroller.AddStaffServlet"%>

<%
    // Check if the user is logged in and has the position of System Admin
    User user = (User) session.getAttribute("user");
    if (user == null || !user.getPosition().equals("System Admin")) {
        response.sendRedirect("login.jsp"); // Redirect unauthorized users to login page
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Information Management</title>
</head>
<body>
    <h1>Staff Information Management</h1>
    <form action="ShowAllStaffServlet" method="get">
        <!-- Search bar -->
        <input type="text" name="search" placeholder="Search...">
        <input type="submit" value="Search">
    </form>

    <!-- Display staff information -->
    <table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Position</th>
            <!-- Add more columns if needed -->
            <th>Action</th>
        </tr>
        <% 
            List<User> staffList = (List<User>) request.getAttribute("staffList");
            if (staffList != null) {
                for (Staff staff : staffList) {
        %>
        <tr>
            <td><%= staff.getFirstName() %></td>
            <td><%= staff.getLastName() %></td>
            <td><%= staff.getPosition() %></td>
            <!-- Add more cells with staff information if needed -->
            <td>
                <form action="UpdateStaffServlet" method="post">
                    <input type="hidden" name="staffId" value="<%= staff.getId() %>">
                    <input type="submit" value="Update">
                </form>
                <form action="DeleteStaffServlet" method="post">
                    <input type="hidden" name="staffId" value="<%= staff.getId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="4">No staff found</td>
        </tr>
        <% } %>
    </table>

    <!-- Return button -->
    <form action="mainPage.jsp" method="get">
        <input type="submit" value="Return">
    </form>
</body>
</html>
