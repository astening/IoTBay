<%-- 
    Document   : index
    Created on : 17 May 2024, 4:38pm
    Author     : chari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IoT Bay Application</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1>Welcome to IoTBay!</h1>
        <h3>Please choose from the following:</h3>
        <div>
            <br>
                <a class="button" href="register.jsp">Register</a> 
                <a class="button" href="login.jsp">Login</a>
                <a href="main.jsp">Browse The Application</a>
        </div>
        <%-- include conn servlet at the start of the session --%>
        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>
