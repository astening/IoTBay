<%@page import="isdmodel.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="isdmodel.dao.DBManager" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>IoT Device Catalogue</title>
    </head>
    <body>
        <% 
            User user = (User)session.getAttribute("user");
        %>
        <h1>Device Catalogue</h1>
        <table id="table">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
            </tr>
        </table>
        <div> 
        
        <br>
        <br>
            <a class ="button" href="logout.jsp"> Logout </a>
            <br>
            <br>
            <a class ="button" href="DeviceCatalogueServlet"> Device Catalogue </a>
        </div>
        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>
