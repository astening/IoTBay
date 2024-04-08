<%-- 
    Document   : main
    Created on : 2 Apr 2024, 1:37 pm
    Author     : William
--%>
<%@page import="isdmodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>IOT Bay Main Page</title>
    </head>
    <body>
        <% 
            User user = (User)session.getAttribute("user");
        %>
        <h1>User Profile</h1>
        <table id="profile_table"> 
            <thead><th>Name</th><th>UserName</th><th>Password</th></thead>
        <tr><td>${user.name}</td><td>${user.username}</td><td>${user.password}</td></tr>            
        </table>
        <div class="panel_div"> 
            <a class ="button" href="logout.jsp"> Logout </a>
        </div>
    </body>
</html>
