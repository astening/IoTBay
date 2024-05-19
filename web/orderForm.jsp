
<%-- 
    Document   : orderForm
    Created on : 6 May 2024, 7:12:27 pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.Order" %>
<%@page import="isdmodel.User" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Order Form</title>
    </head>
    <body>
        <%
            // retrieve error message, user and order object
            Order order = (Order) session.getAttribute("order") ;
            User user = (User) session.getAttribute("user") ;
            String quantityValidated = (String) session.getAttribute("quantityValidated") ;
            String IDValidated = (String) session.getAttribute("IDValidated") ;
            String updated = (String) session.getAttribute("updated") ;
        %>

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <% if (user != null) { %>
            <a class="node" href="account.jsp">Account</a>
        <% } %>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="active" href="orderForm.jsp">Order Form</a>
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
        
        <h1>Submit an order here:</h1>
        
        <% if(session.getAttribute("updated")==null) { %>
        <% } else if (session.getAttribute("dateValidated")!=null) { %>
            <p><%= session.getAttribute("updated") %><p>
            <p><%= session.getAttribute("quantityValidated") %><p>    
            <p><%= session.getAttribute("dateValidated") %><p>
            <p>Your ID is: <%= session.getAttribute("returnID") %></p>
        <% } else { %>
            <p><%= session.getAttribute("updated") %><p>
            <p><%= session.getAttribute("dateValidated") %><p>
            <p><%= session.getAttribute("quantityValidated") %><p>
            <p>Your ID is: <%= session.getAttribute("returnID") %></p>
        <% } %>        
        
        <form action="OrderFormServlet" method="POST">
            <table>
                <tr> 
                    <td><label for="productID">Product ID: </label></td>
                    <td><input type="int" id="productID" name="productID" placeholder="<%=session.getAttribute("IDValidated")%>"></td>
                </tr>
                <tr>
                    <td><label for="noItems">Quantity: </label></td>
                    <td><input type="int" id="noItems" name="noItems" placeholder="<%=session.getAttribute("quantityValidated")%>"></td>
                </tr>
            </table>
            <br>
            <button>Cancel</button>
            <button>Save</button>
            <button>Submit my order</button>
        </form>         
        
        <%-- display order form to update --%>
        <h1>Update Order</h1>
        <% if(updated==null) {%>
        <% } else { %>
        <%= IDValidated %>
        <%= updated %>  
        <% } %>
    
        <%-- attempt to iterate through list --%>
        <%
                
            ArrayList<Order> list = (ArrayList<Order>) session.getAttribute("resultList") ;
            int noItems = 0 ;
            if (list!=null) {
                for (Order i: list) {
//                    order.getProductID() ; // in order line, not order
                    noItems = i.getNoItems() ;
                }
            }
        %>
        <form action="UpdateOrderForm" method="POST">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID"></td>
                </tr>
                <tr>
                    <td><label for="orderDate">Order Date: </label></td>
                    <td><input type="date" id="orderDate" name="orderDate"></td>
                </tr>
                <tr> 
                    <td><label for="productID">Product ID: </label></td>
                    <td><input type="int" id="productID" name="productID"></td>
                </tr>
                <tr>
                    <td><label for="noItems">Quantity: </label></td>
                    <td><input type="int" id="noItems" name="noItems" value="${noItems}" placeholder="<%=session.getAttribute("quantityValidated")%>"></td>
                </tr>
            </table>
            <br>
            <button>Cancel</button>
            <button><a href="RetrieveOrder">Retrieve details</a></button>
            <button>Update my order</button>
        </form>         
    </body>
</html>
