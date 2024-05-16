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
            Your ID is: <%= session.getAttribute("returnID") %>
        </p>
        <form action="OrderFormServlet" method="POST">
            <table>
                <tr>
                    <td><label for="productID">Product ID: </label></td>
                    <td><input type="int" id="productID" name="productID"></td>
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
        <a href="main.jsp">Main Page</a>
    </body>
</html>
