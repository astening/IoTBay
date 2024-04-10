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
                <label for="Email">Email: </label>
                <input type="text" id="email" name="email">
                </tr>
                <tr>
                   <label for="password">Password: </label><br>
                   <input type="password" id="password" name="password">
                </tr>
                <tr>
                <input type="submit" value="Register">
                </tr>
                
            </table>
          

                                    </body>
                                    </html>
