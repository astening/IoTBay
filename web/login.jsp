<%-- 
    Document   : login
    Created on : 1 Apr 2024, 4:50:54 pm
    Author     : anna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Login</title>
        <% String email = request.getParameter("email");%>
        <% String password = request.getParameter("password");%>
    </head>

    <body>
        <h1>Login</h1>
        
        <form action="welcome.jsp" method="post">
        <table>
            <tr>
                <th>
                    <label for="Email">Email: </label><br>
                </th>
                <th>
                    <input type="text" id="email" name="email"><br>
                </th>
            </tr>
        <table>
        
        <table>
            <tr>
                <th>
                    <label for="password">Password: </label><br>
                </th>
                <th>
                    <input type="password" id="password" name="password"><br>
                </th>
            </tr>
        <table>

        <table>
            <tr>
                <th>
                    <input type="submit" value="Register">
                </th>
            </tr>
        <table>
        </form>

    </body>
</html>
