<!-- 
    Document   : register.jsp
    Created on : 22 Mar 2024, 1:30:05 pm
    Author     : anna

    Register page – Implement the register page screen 
    using web form (e.g. JSP). The register page must post 
    the form-data to the welcome page.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="/IoTBay/welcome.jsp" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required="true"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required="true"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
