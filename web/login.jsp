<%-- 
    Document   : login
    Created on : 16 May 2024, 11:31:32 pm
    Author     : phoen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript" src="js/script.js"></script>
        <title>Login</title>
    </head>

    <body>
        <div> <span class="time" id="time"></span></div>
                <%
                   String existError = (String) session.getAttribute("existError");
                   String emailError = (String) session.getAttribute("emailError");
                %>
        <h1>Login <span class="message"> <%=(existError != null ? existError : "")%> </span></h1>
        <form action="LoginServlet" method="post">
            <table id="form_table">
                <tr>
                    <td>Email:</td>
                    <td><input type="text" placeholder="<%=(emailError != null ? emailError : "Email:")%>" name="email" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" placeholder="Password:" name="password" required></td>
                </tr>
                <tr><td><td>
                    <td><a href="CancelServlet" class="button">Cancel</a>
                        <input class="button" type="submit" value="Log in">
                    </td>
                </tr>
            </table>
           <br>
        </form>
    </body>
</html>