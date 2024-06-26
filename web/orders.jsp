<%-- 
    Document   : orders
    Created on : 4 May 2024, 4:20:36 pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.Order"%>
<%@page import="isdmodel.User" %>
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
            User user = (User) session.getAttribute("user") ;
            String statusValidated = (String) session.getAttribute("statusValidated") ;
            String updated = (String) session.getAttribute("updated") ;
            String IDvalidated = (String) session.getAttribute("IDValidated") ;
            session.getAttribute("manager") ;
            session.getAttribute("resultOrder") ;
        %> 

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <% if (user != null) { %>
            <a class="node" href="account.jsp">Account</a>
        <% } %>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="active" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <% if (user != null && (user.getPosition().equals("Individual") || user.getPosition().equals("Company"))){%>
            <a class="node" href="PaymentMethodServlet?userID=<%=user.getUserID()%>">Payments</a>
        <% } %>
        <% if (user != null && user.getPosition().equals("Systems Admin")) { %>
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
        
        <h1>Orders Page</h1>
        <h2>Cancel Order</h2>
        <%-- form to cancel order --%>    
        <%-- display error messages --%>
        <% if(updated==null ) { //do not display initial values on start-up %>
        <% } else { // display relevant response message %>
        <p><%= updated %><p>
        <% } %>
       
        <form action="UpdateOrderStatus" method="post">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID" placeholder="<%=session.getAttribute("IDValidated")%>"></td>
                </tr>
                <tr>
                    <td><input type="hidden" id="status" name="status" value="Cancelled"></td>
                </tr>
                <br>
                <tr>
                    <td><input class="button" type="submit" value="Update"></td>
                </tr>
            </table>
        </form>
        
        
        <% if(user!=null) { // check if staff %>
            <% if(user.getPosition()!="Customer" && user.getPosition()!="Supplier") { // check if staff %>       
            <%-- form to update status --%>  
            <%-- display error messages --%>
            <% if(updated==null ) { //|| updated.equals("No change made yet") //do not display initial values on start-up %>
            <% } else { // display relevant response message %>
                <p><%= updated %><p>

            <% } %>            
            <h2>Update order status - staff only</h2>
            <form action="UpdateOrderStatus" method="post">
                <table>
                    <tr>
                        <td><label for="orderID">Order ID: </label></td>
                        <td><input type="int" id="orderID" name="orderID" placeholder="<%=session.getAttribute("IDValidated")%>"></td>
                    </tr>
                    <tr>
                        <td><label for="status">Status: </label></td>
                        <td><input type="text" id="status" name="status" placeholder="<%=session.getAttribute("statusValidated")%>"></td>
                    </tr>
                    <br>
                    <tr>
                        <td><input class="button" type="submit" value="Update"></td>
                    </tr>
                </table>
            </form>            
            <% } %> 
        <% } %>
             
        <!--Button to retrieve all orders, ideally staff only-->   
        <form action="OrderFetchAll" method="post">
            <button>Fetch all</button>
        </form>        
                
        <br>
        <h2>Search for orders here:</h2>
        
        <%-- display error messages --%> 
        <% if(session.getAttribute("searched")==null && session.getAttribute("dateValidated")==null) { %>
        <% } else if (session.getAttribute("searched")==null) { %>
            <p><%= session.getAttribute("searched") %><p>
        <% } else if (session.getAttribute("dateValidated")==null) { %>
            <p><%= session.getAttribute("searched") %><p>
        <% } else { %>
            <p><%= session.getAttribute("searched") %><p>
            <p><%= session.getAttribute("dateValidated") %><p>
        <% } %>
        
        <%-- form to search for an order --%>     
            
        <form action="OrderSearchServlet" method="post">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID" placeholder="<%=session.getAttribute("IDValidated")%>"></td>
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
            <button>View All</button>
        </form>        
    </body>
</html>
