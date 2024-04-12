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
                    <td><label for="Email">Email: </label></td>
                    <td><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="password">Password: </label></td>
                    <td><input type="password" id="password" name="password"></td>
                </tr>
            </table>
           <br>
            <button>Login</button>
        </form>
    </body>
</html>
