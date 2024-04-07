<%-- 
    Document   : register.jsp
    Created on : 22 Mar 2024, 1:30:05 pm
    Author     : anna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Register</title>
    </head>
    <body>
        <h1>Register Account</h1>
        <form action="welcome.jsp" method="post">
            <label for="email">Username:</label>
            <input type="text" id="usernmae" name="username" required="true"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required="true"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
