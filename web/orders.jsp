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
            // get variables
            String statusValidated = (String) session.getAttribute("statusValidated") ;
            String updated = (String) session.getAttribute("updated") ;
            String IDvalidated = (String) session.getAttribute("IDValidated") ;
            session.getAttribute("manager") ;
            session.getAttribute("resultOrder") ;
        %>        
        <h1>Orders Page</h1>
        <h2>Update order status - staff only</h2>
        
        <%-- display error messages --%>
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
            
        <%-- form to update status --%>    
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
        
        <%-- display error messages --%> 
        
        <p><%= session.getAttribute("searched") %><p>
        <p><%= IDvalidated %><p>
        <p><%= session.getAttribute("dateValidated") %><p>
        
        <%-- form to search for an order --%>     
            
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
                // for each order in the list, display the details in the table row
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
                
                <form action="UpdateOrderForm.jsp" method="post">
                    <input type='hidden' id='orderID' name='orderID' value=<%= i.getOrderID() %>>
                    <input type='hidden' id='status' name='status' value=<%= i.getNoItems() %>>
                    <input type='hidden' id='totalPrice' name='totalPrice' value=<%= i.getTotalPrice() %>>
                    <td><a class ="button" href="orderForm.jsp" method="post"> Update </a></td>
                </form>
                
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
