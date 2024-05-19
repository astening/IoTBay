

<%@page contentType="text/html" pageEncoding = "UTF-8"%>
<%@page import="isdmodel.*" %>
<!DOCTYPE html>
<!-- 
    Document   : index
    Created on : 1 Apr 2024, 4:51:23 pm
    Author     : taylah-->

<html>
    <head>
        <title>IoT Bay Application</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="js.script.js"> </script>
    </head>
     <!-- Establish DB connection through Conn Servlet and display buttons -->
    <body onload="startTime()">

    <% User user = (User) session.getAttribute("user") ; %>
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
        <% if (user != null && (user.getPosition().equals("Systems Admin"))) { %>
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
        
        <h1>Welcome to IoTBay!</h1>
        <div> <span class="time" id="time"> </span> </div>
        <h3>Please choose from the following:</h3>
        <div>
            <br>
                <a class="button" href="register.jsp">Register</a> 
                <a class="button" href="login.jsp">Login</a>
                <a class="button" href="main.jsp">Browse The Application</a>
                <a class="button" href="DeviceCatalogueServlet">Devices</a>
        </div>
        <%-- include conn servlet at the start of the session --%>
        <jsp:include page="/ConnServlet" flush="true"/>
        <br>
        <img src="https://www.lifewire.com/thmb/9t-gZY20dDkgCJkjoeVisVOlttE=/7680x4320/filters:no_upscale():max_bytes(150000):strip_icc()/hands-using-computer-to-config-system-vector-illustration-setting-personal-computer-concept-918493164-5ae8e89204d1cf003c57846f-a79a8815d23947d489c122d8cf9f06d1.jpg" width="600" height="400" />
    </body>
</html>