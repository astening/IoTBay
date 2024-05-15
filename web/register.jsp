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
    <body onload="startTime();">
                <%
                   String fnameErr = (String) session.getAttribute("fnameErr");
                   String lnameErr = (String) session.getAttribute("lnameErr");
                   String phoneErr = (String) session.getAttribute("phoneErr");
                   String emailErr = (String) session.getAttribute("emailErr");
                   String passErr = (String) session.getAttribute("passErr");
                   String addressErr = (String) session.getAttribute("addressErr");
                   String cityErr = (String) session.getAttribute("cityErr");
                   String stateErr = (String) session.getAttribute("stateErr");
                   String postErr = (String) session.getAttribute("postErr");
                   String positionErr = (String) session.getAttribute("positionErr");
                %>
        <h1>Register Account</h1>
        <form action="RegistrationServlet" method="POST">
            <table>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><input type="text" placeholder="<%=(fnameErr != null ? fnameErr : "Enter First Name:")%>" id="fname" name="fname" required></td>
                <tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><input type="text" placeholder="<%=(lnameErr != null ? lnameErr : "Enter Last Name:")%>" id="lname" name="lname" required></td>
                <tr>
                <tr>
                    <td><label for="phoneNo">Phone Number:</label></td>
                    <td><input type="number" placeholder="<%=(phoneErr != null ? phoneErr : "Enter Phone Number:")%>" id="phoneNo" name="phoneNo" required></td>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email:")%>" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password:")%>" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" placeholder="<%=(addressErr != null ? addressErr : "Enter Address:")%>" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" placeholder="<%=(cityErr != null ? cityErr : "Enter City:")%>" id="city" name="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" placeholder="<%=(stateErr != null ? stateErr : "Enter State:")%>" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="postcode">Postcode:</label></td>
                    <td><input type="number" placeholder="<%=(postErr != null ? postErr : "Enter Postcode:")%>" id="postcode" name="postcode" required></td>
                </tr>
                <tr>
                    <td><label for="position">Position:</label></td>
                    <td><input type="text" placeholder="<%=(positionErr != null ? positionErr : "Enter Position:")%>" id="position" name="position" required></td>
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
