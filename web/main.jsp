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
            User user = new User(123, "James Potter", "james@gmail.com", "password");
            session.setAttribute("user", user);
        %>
        <h1>User Profile</h1>
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
        </div>
            
        <div> 
        <br>
        <br>
            <a class ="button" href="PaymentMethodServlet?userID=<%=user.getUserID()%>"> Edit Payment Details </a>
        </div>
        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>
