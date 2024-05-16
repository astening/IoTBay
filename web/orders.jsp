<%-- 
    Document   : orders
    Created on : 4 May 2024, 4:20:36 pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.Order"%>
<%@page import="java.util.ArrayList"%>

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
            session.getAttribute("resultOrder") ;
        %>        
        <h1>Orders Page</h1>
        <h2>Update order status - staff only</h2>
                    
        <% if(updated==null ) { //|| updated.equals("No change made yet") //do not display initial values on start-up %>
        <% } else { // display relevant response message %>
            <p><%= updated %><p>
            <% if(statusValidated.equals("Provide a status")) { // display specific error messages %>
            <p><%= statusValidated %><p>
            <% } else if(IDvalidated.equals("Provide an order ID")) { %>
            <p><%= IDvalidated %><p>
            <% } else { // what do I do here %>
            <% }%>
        <% } %>
            
            
        <form action="UpdateOrderStatus" method="post">
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
            
        <!--Button to retrieve all orders-->   
        <form action="OrderFetchAll" method="post">
            <button>Fetch all</button>
        </form>        
            
            
        <br>
        <h2>Search for orders here:</h2>
        
        <p><%= session.getAttribute("searched") %><p>
        <p><%= IDvalidated %><p>
        <p><%= session.getAttribute("dateValidated") %><p>
        
        <form action="OrderSearchServlet" method="post">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID"></td>
                </tr>
                    <td><label for="orderDate">Order Date: </label></td>
                    <td><input type="date" id="orderDate" name="orderDate"></td>
                <tr>
                    <td><input class="button" type="submit" value="Search"></td>
                </tr>
            </table>
        </form>
        
        
        <h2>Results from search:</h2>
        <p>

        </p>
        <table id="table" >
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Order Status</th>
                <th>Number of Items</th>
                <th>Total Price</th>
                <th>View Details</th>
            </tr>
            <%
                ArrayList<Order> list = (ArrayList<Order>) session.getAttribute("resultList") ;
                if (list!=null) {
                for (Order i: list) {
            %>          
            <tr>
                <td><%= i.getOrderID() %></td>
                <td><%= i.getOrderDate() %></td>
                <td><%= i.getStatus() %></td>
                <td><%= i.getNoItems() %></td>
                <td><%= i.getTotalPrice() %></td>
                <% if(i.getStatus().equals("Saved")) { %>
                <td><a class ="button" href="orderForm.jsp" method="post"> Update </a></td>
                <% } else { %>
                <td><a class ="button" href="orderForm.jsp" method="post"> View </a></td>
                <% } %>             
            </tr>
            <% } } else { %>
            <tr>
                <td>No results to display</td>
            </tr>        
            <% }  %>
        </table>
        
        <!--Button to retrieve all orders--> 
        <form action="OrderFetchAll" method="post">
            <button>Fetch all</button>
        </form>
        <a href="main.jsp">Main Page</a>
        
    </body>
</html>
