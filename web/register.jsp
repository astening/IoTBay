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
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="/labs/welcome.jsp" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required="true"><br>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required="true"><br>
            <label for="gender">Gender:</label>
            <input type="text" id="gender" name="gender"><br>
            <label for="favcol">Favourite Colour:</label>
            <input type="text" id="favcol" name="favcol"><br>
            <label for="tos">Accept TOS:</label>
            <input type="checkbox" id="tos" name="tos"><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
