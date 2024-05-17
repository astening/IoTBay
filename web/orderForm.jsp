<%-- 
    Document   : orderForm
    Created on : 6 May 2024, 7:12:27 pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.Order" %>
<%@page import="isdmodel.User" %>
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
        <h1>Submit an order here:</h1>
        <p>
            <%= quantityValidated %>
            <%= IDValidated %>
            <%= updated %>
            <%= request.getParameter("submittedOrderID") %>
            Your ID is: <%= session.getAttribute("returnID") %>
        </p>
        
        <%-- display order form to submit, or to update --%>
        <% if(request.getParameter("orderID")==null) { // this part doesnt work %>
        <form action="OrderFormServlet" method="POST">
            <table>
                <tr>
                    <td><label for="productID">Product ID: </label></td>
                    <td><input type="int" id="productID" name="productID" value="${orderID}"></td>
                </tr>
                <tr>
                    <td><label for="noItems">Quantity: </label></td>
                    <td><input type="int" id="noItems" name="noItems"></td>
                </tr>
                <tr>
                    <% //add hidden fields here for user ID %>
                </tr>
            </table>
            <br>
            <button>Cancel</button>
            <button>Save</button>
            <button>Submit my order</button>
        </form>          
        <% } else { %>
        <p>Update order tbc</p>
        <% } %>
        
        <%-- display order form to update --%>
        <h1>Update Order</h1>
        <%= quantityValidated %>
        <%= IDValidated %>
        <%= updated %>        
        
        <form action="UpdateOrderForm" method="POST">
            <table>
                <tr>
                    <td><label for="orderID">Order ID: </label></td>
                    <td><input type="int" id="orderID" name="orderID"></td>
                </tr>
                <tr> 
                    <td><label for="productID">Product ID: </label></td>
                    <td><input type="int" id="productID" name="productID"></td>
                </tr>
                <tr>
                    <td><label for="noItems">Quantity: </label></td>
                    <td><input type="int" id="noItems" name="noItems"></td>
                </tr>
            </table>
            <br>
            <button>Cancel</button>
            <button>Save</button>
            <button>Update my order</button>
        </form>         
        <a href="main.jsp">Main Page</a>
    </body>
</html>
