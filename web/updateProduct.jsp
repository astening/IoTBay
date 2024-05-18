<%@ page import="isdmodel.User"%>
<%@ page import="java.sql.*" %>
<%@ page import="isdmodel.dao.*"%>
<%@ page import="isdmodel.Product"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Update Product</title>
    </head>
    <body>
        <% 
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager db = new DBManager(conn);
            int productID = Integer.parseInt(request.getParameter("productID"));
            Product currentProduct = db.findProduct(productID);
            
            String prodName = currentProduct.getName();
            String prodPrice = Float.toString(currentProduct.getPrice());
            String prodType = currentProduct.getType();
            String prodStockLvl = Integer.toString(currentProduct.getStockLvl());
            
            String prodNameErr = (String) session.getAttribute("prodNameErr");
            String prodPriceErr = (String) session.getAttribute("prodPriceErr");
            String prodTypeErr = (String) session.getAttribute("prodTypeErr");
            String prodStockErr = (String) session.getAttribute("prodStockErr"); 
        %>
        <h1>Update Product</h1>
        <form action="UpdateProductServlet" method="post">
            <input type="hidden" name="productID" value="<%= productID %>">
            <table>
                <tr>
                    <td><label for="name">Product Name: </label></td>
                    <td><input type="text" id="name" name="name" required="true" placeholder="<%=(prodName != null ? prodName : " ")%>"></td>
                    <td><label for="name"><%=(prodNameErr != null ? prodNameErr : " ")%></label></td>
                </tr>
                <tr>
                    <td><label for="price">Price: </label></td>
                    <td><input type="text" id="price" name="price" required="true" placeholder="$<%=(prodPrice != null ? prodPrice : " ")%>"></td>
                    <td><label for="price"><%=(prodPriceErr != null ? prodPriceErr : " ")%></label></td>
                </tr>
                <tr>
                    <td><label for="type">Type: </label></td>
                    <td><input type="text" id="type" name="type" required="true" placeholder="<%=(prodType != null ? prodType : " ")%>"></td>
                    <td><label for="type"><%=(prodTypeErr != null ? prodTypeErr : " ")%></label></td>
                </tr>
                <tr>
                    <td><label for="stock">Stock Level: </label></td>
                    <td><input type="text" id="stock" name="stock" required="true" placeholder="<%=(prodStockLvl != null ? prodStockLvl : " ")%>"></td>
                    <td><label for="stock"><%=(prodStockErr != null ? prodStockErr : " ")%></label></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <table>
                            <tr>
                                <td><input class="button" type="submit" value="Update"></td>
                                <td><a class ="button" href="DeviceCatalogueServlet">Cancel</a></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
