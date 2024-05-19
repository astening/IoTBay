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
    
    <%User user = (User) session.getAttribute("user"); %>

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <% if (user != null) { %>
            <a class="node" href="account.jsp">Account</a>
        <% } %>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        
        <% if (user != null && (user.getPosition().equals("Individual") || user.getPosition().equals("Company"))){%>
            <a class="node" href="PaymentMethodServlet?userID=<%=user.getUserID()%>">Payments</a>
        <% } %>
        <a class="node" href="customerInformationManagement.jsp">Manage Customers</a>
        <a class="active" href="StaffInformationManagement.jsp">Manage Staff</a>
        <div class="navBar-right">
            <a class="node" href="logout.jsp">Logout</a>
        </div>     
    </div>
    
    <h1>Staff Information Management</h1>
    
    <!-- Display staff information -->
    <table id="table">
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
        
        <!--Create Validations and staffList of Users -->
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
            String positionErr = (String) session.getAttribute("positionErr");
                   
            List<User> staffList = (List<User>) request.getAttribute("staffList");
            User foundStaff = (User) request.getAttribute("foundStaff");
            
            if (staffList != null && !staffList.isEmpty()) {
                for (User u : staffList) {
        %>
        
        <!-- Get the values of the users attributes and put them in the table columns-->
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
                    <input class="button" type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% 
                }
            } else if (foundStaff != null) { // Display single staff member
        %>
         <!-- Get the values of the single users attributes and put them in the table columns-->
        <tr>
            <td><%= foundStaff.getUserID() %></td>
            <td><%= foundStaff.getFname() %></td>
            <td><%= foundStaff.getLname() %></td>
            <td><%= foundStaff.getPhoneNo() %></td>
            <td><%= foundStaff.getEmail() %></td>
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
                    <input class="button" type="submit" value="Delete">
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
    <h2> Find Staff Member <span class="message"> <%=(existErr != null ? existErr : "")%> </span> </h2>
    <form action="FindStaffServlet" method="get">
        First Name: <input type="text" placeholder="<%=(fnameErr != null ? fnameErr : "Enter First Name:")%>" name="fname"></td>
        Last Name: <input type="text" placeholder="<%=(lnameErr != null ? lnameErr : "Enter Last Name:")%>" name="lname">
        Position: <input type="text" placeholder="<%=(positionErr != null ? positionErr : "Enter Position:")%>" name="position">
        <input class="button" type="submit" value="Search">
    </form>

    <!-- Add form for showing all staff -->
    <h2>Show All Staff </h2>
    <form action="ShowAllStaffServlet" method="get">
    <input class="button" type="submit" value="Show All Staff">
    </form>
    
    <!-- Add form for adding new staff -->
    <h2>Add New Staff</h2>
    <form action="AddStaffServlet" method="post">
        <table>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><input type="text" placeholder="<%=(fnameErr != null ? fnameErr : "Enter First Name:")%>" id="fName" name="fName" required></td>
                <tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><input type="text" placeholder="<%=(lnameErr != null ? lnameErr : "Enter Last Name:")%>" id="lName" name="lName" required></td>
                <tr>
                <tr>
                    <td><label for="phoneNo">Phone Number:</label></td>
                    <td><input type="number" placeholder="<%=(phoneErr != null ? phoneErr : "Enter Phone Number:")%>" id="phoneNo" name="phoneNo" required></td>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email:")%>" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password:")%>" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" placeholder="<%=(addressErr != null ? addressErr : "Enter Address:")%>" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" placeholder="<%=(cityErr != null ? cityErr : "Enter City:")%>" id="city" name="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" placeholder="<%=(stateErr != null ? stateErr : "Enter State:")%>" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="postcode">Postcode:</label></td>
                    <td><input type="number" placeholder="<%=(postErr != null ? postErr : "Enter Postcode:")%>" id="postcode" name="postcode" required></td>
                </tr>
                <tr>
                    <td><label for="position">Position:</label></td>
                    <td><input type="text" placeholder="<%=(positionErr != null ? positionErr : "Enter Position:")%>" id="position" name="position" required></td>
                </tr>
                <tr><tr><td>
                    <td>
                        <input class="button" type="submit" value="Add">
                    </td>
                </tr>
            </table>
            <br>
    </form>
    
    <!-- Add form for Updating existing staff -->
    <h2> Update Staff</h2>
    <form action="UpdateStaffServlet" method="post">
        Enter the existing users User ID:<br>
                    User ID: <input type="number" name="userID">
                    <br>
                    <br>
        Update their details:            
                    <table>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><input type="text" placeholder="<%=(fnameErr != null ? fnameErr : "Enter First Name:")%>" id="fName" name="fName" required></td>
                <tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><input type="text" placeholder="<%=(lnameErr != null ? lnameErr : "Enter Last Name:")%>" id="lName" name="lName" required></td>
                <tr>
                <tr>
                    <td><label for="phoneNo">Phone Number:</label></td>
                    <td><input type="number" placeholder="<%=(phoneErr != null ? phoneErr : "Enter Phone Number:")%>" id="phoneNo" name="phoneNo" required></td>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email:")%>" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password:")%>" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" placeholder="<%=(addressErr != null ? addressErr : "Enter Address:")%>" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" placeholder="<%=(cityErr != null ? cityErr : "Enter City:")%>" id="city" name="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" placeholder="<%=(stateErr != null ? stateErr : "Enter State:")%>" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="postcode">Postcode:</label></td>
                    <td><input type="number" placeholder="<%=(postErr != null ? postErr : "Enter Postcode:")%>" id="postcode" name="postcode" required></td>
                </tr>
                <tr>
                    <td><label for="activation">Activation:</label></td>
                    <td><select name="activation">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select></td>
                </tr>
                <tr>
                    <td><label for="position">Position:</label></td>
                    <td><input type="text" placeholder="<%=(positionErr != null ? positionErr : "Enter Position:")%>" id="position" name="position" required></td>
                </tr>
                <tr><tr><td>
                    <td>
                        <input class="button" type="submit" value="Update">
                    </td>
                </tr>
            </table>
           <br>
        </form>
        <br>
        <br>  
</body>
</html>