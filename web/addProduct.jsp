<%@ page import="isdmodel.User"%>
<%@ page import="java.sql.*" %>
<%@ page import="isdmodeldao.*"%>
<%@ page import="isdmodel.Product"%>
<%@ page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Add Product</title>
    </head>
    <body>
    <% User user = (User) session.getAttribute("user") ; %>
        <% 
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager db = new DBManager(conn);
            
            String prodNameErr = (String) session.getAttribute("prodNameErr");
            String prodPriceErr = (String) session.getAttribute("prodPriceErr");
            String prodTypeErr = (String) session.getAttribute("prodTypeErr");
            String prodStockErr = (String) session.getAttribute("prodStockErr"); 
        %>
        <h1>Add New Product</h1>
        <form action="AddProductServlet" method="post">
            <table>
                <tr>
                    <td><label for="name">Product Name: </label></td>
                    <td><input type="text" id="name" name="name" required="true"></td>
                    <td><label for="name"><%=(prodNameErr != null ? prodNameErr : " ")%></label></td>
                </tr>
                <tr>
                    <td><label for="price">Price: </label></td>
                    <td><input type="text" id="price" name="price" required="true"></td>
                    <td><label for="price"><%=(prodPriceErr != null ? prodPriceErr : " ")%></label></td>
                </tr>
                <tr>
                    <td><label for="type">Type: </label></td>
                    <td><input type="text" id="type" name="type" required="true"></td>
                    <td><label for="type"><%=(prodTypeErr != null ? prodTypeErr : " ")%></label></td>
                </tr>
                <tr>
                    <td><label for="stock">Stock Level: </label></td>
                    <td><input type="text" id="stock" name="stock" required="true"></td>
                    <td><label for="stock"><%=(prodStockErr != null ? prodStockErr : " ")%></label></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <table>
                            <tr>
                                <td><input class="button" type="submit" value="Add"></td>
                                <td><a class ="button" href="DeviceCatalogueServlet">Cancel</a></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
