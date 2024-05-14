<%-- 
    Document   : orders
    Created on : 4 May 2024, 4:20:36 pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.Order"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Orders Page</title>
    </head>
    <body>
        <%
            String statusValidated = (String) session.getAttribute("statusValidated") ;
            String updated = (String) session.getAttribute("updated") ;
            String IDvalidated = (String) session.getAttribute("IDValidated") ;
            session.getAttribute("manager") ;
        %>        
        <h1>Orders Page</h1>
        <h2>Update order status - staff only</h2>
                    
        <% if(updated==null ) { //|| updated.equals("No change made yet") //do not display initial values on start-up %>
        <% } else { // display change unsucccessful %>
            <p><%= updated %><p>
            <% if(statusValidated.equals("Provide a status")) { // display specific error messages%>
            <p><%= statusValidated %><p>
            <% } else if(IDvalidated.equals("Provide an order ID")) { %>
            <p><%= IDvalidated %><p>
            <% } else { // what do I do here %>
            <% }%>
        <% } %>
            
            
        <form action="UpdateStatus" method="post">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID"></td>
                </tr>
                <tr>
                    <td><label for="status">Status: </label></td>
                    <td><input type="text" id="status" name="status"></td>
                </tr>
                <br>
                <tr>
                    <td><input class="button" type="submit" value="Update"></td>
                </tr>
            </table>
        </form>
        <br>
        <h2>Search for orders here:</h2>
        <form action="OrderSearchServlet" method="post">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID" required="true"></td>
                </tr>
                    <td><label for="orderDate">Order Date: </label></td>
                    <td><input type="date" id="orderDate" name="orderDate" required="true"></td>
                <tr>
                    <td><input class="button" type="submit" value="Search"></td>
                </tr>
            </table>
        </form>
        <h2>Results from search:</h2>
        <table id="table" >
            <tr>
                <th>Order ID:</th>
                <th>Order Date</th>
                <th>View Details:</th>
            </tr>
            <tr>
                <td>id</td>
                <td>date</td>
                <td><a href="/IoTBay/orderForm.jsp">View details</a></td>
            </tr>
        </table>
        
    </body>
</html>
