<%-- 
    Document   : main
    Created on : 2 Apr 2024, 1:37 pm
    Author     : William
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IOT Bay Main Page</title>
    </head>
    <body>
        <% 
            User user = (User).session.getAttribute("user");
        %>
        <h1>User Profile</h1>
        <table id="profile_table"> 
            <thead><th>Name</th><th>Email</th><th>Password</th><th>Date of Birth</th></thead>
        <tr><td>${user.name}</td><td>${user.email}</td><td>${user.password}</td><td>${user.dob}</td></tr>            
        </table>
        <div class="panel_div"> 
            <a class ="button" href="logout.jsp"> Logout </a>
        </div>
    </body>
</html>
