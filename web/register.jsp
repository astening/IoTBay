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
        <form action="RegistrationServlet" method="POST">
            <table>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><input type="text" id="fname" name="fname" required></td>
                <tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><input type="text" id="lname" name="lname" required></td>
                <tr>
                <tr>
                    <td><label for="phoneNo">Phone Number:</label></td>
                    <td><input type="number" id="phoneNo" name="phoneNo" required></td>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" id="city" name="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="postcode">Postcode:</label></td>
                    <td><input type="number" id="postcode" name="postcode" required></td>
                </tr>
                <tr>
                    <td><label for="position">Position:</label></td>
                    <td><input type="text" id="position" name="position" required></td>
                </tr>
                <tr><td><td>
                    <td><a href="CancelServlet" class="button">Cancel</a>
                        <input class="button" type="submit" value="Register">
                    </td>
                </tr>
            </table>
            <br>
        </form>
    </body>
</html>
