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
        <title>IoT Bay Device Catalogue</title>
    </head>
    <body>
        <% 
            User user = (User) session.getAttribute("user");
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager db = new DBManager(conn);
        %>
       
        <div class="navBar">
            <a class="navBarTitle">IoT Bay</a>
            <a class="node" href="main.jsp">Home</a>
            <% if (user != null) { %>
                <a class="node" href="account.jsp">Account</a>
            <% } %>
            <a class="active" href="DeviceCatalogueServlet">Device Catalogue</a>
            <a class="node" href="orders.jsp">Orders</a>
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
        
        <div class="main">
        <h1>Device Catalogue</h1>
        <hr>
        <div class="searchBar">
            <input class="searchInput" type="text" id="searchInput" onkeyup="searchProducts()" placeholder="Search for products..">
            <%if(user != null && !user.getPosition().equals("Individual") && !user.getPosition().equals("Company")) {%>
            <a class ="searchButton" href="addProduct.jsp"> Add Product </a>
            <%}%>
            </tr>
        </div>
        
        <table id="deviceCatalogueTable">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Product Type</th>
                <th>Unit Price</th>
                <th>Stock Level</th>
                <%if(user != null && !user.getPosition().equals("Individual") && !user.getPosition().equals("Company")) {%>
                    <th>Update</th>
                    <th>Delete</th>
                <%}%>
            </tr>
        <%
        try {   
            ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("productList");
            for (Product p : productList) {
        %>
            <tr>
                <td> <%out.println(p.getProductID());%> </td>
                <td> <%out.println(p.getName()); %> </td>
                <td> <%out.println(p.getType()); %> </td>
                <td> $<%out.println(p.getPrice()); %> </td>
                <td> <%out.println(p.getStockLvl()); %> </td>
                <%if(user != null && !user.getPosition().equals("Individual") && !user.getPosition().equals("Company")) {%>
                <td> <a class ="button" href="UpdateProductServlet?productID=<%out.println(p.getProductID());%>"> Update </a> </td>
                <td> <a class ="button" href="DeleteProductServlet?productID=<%out.println(p.getProductID());%>"> Delete </a> </td>
                <%}%>
            </tr>
        <% 
            }
        %>
        </table>
        <%  
        }
        catch(Exception e){
            e.printStackTrace();
        }
        %>
        </form>
    </div>
        <script>
        function searchProducts() {
            var input, filter, table, tr;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("table");
            tr = table.getElementsByTagName("tr");
            
            for (var i = 1; i < tr.length; i++) { //doesn't search 0 as 0 is the header row
                var tds = tr[i].getElementsByTagName("td");
                var found = false;
                
                for (var j = 1; j < 3 && !found; j++) {
                    if (tds[j].textContent.toUpperCase().indexOf(filter) > -1) {
                        found = true;
                        break;
                    }
                }
                tr[i].style.display = found?"":"none";
            }
        }

        </script>
    </body>
</html>
