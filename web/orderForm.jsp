<%-- 
    Document   : orderForm
    Created on : 6 May 2024, 7:12:27 pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="isdmodel.Order" %>
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
        %>
        <h1>Submit an order here:</h1>
        <form action="OrderFormServlet" method="POST">
            <table>
                <tr>
                    <td><label for="productID">Product ID: </label></td>
                    <td><input type="int" id="productID" name="productID" required="true"></td>
                <tr>
                    <td><label for="noItems">Quantity: </label></td>
                    <td><input type="int" id="noItems" name="noItems" required="true"></td>
                </tr>
            </table>
            <br>
            <button>Cancel</button>
            <button>Save</button>
            <button>Submit my order</button>
        </form>
    </body>
</html>

<!--
i need to add a hidden field that will add an order ID and also the date

?-->
