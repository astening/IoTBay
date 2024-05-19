<%@page contentType="text/html" pageEncoding = "UTF-8"%>
<%@page import="isdmodel.*" %>
<!DOCTYPE html>
<!-- 
    Document   : index
    Created on : 1 Apr 2024, 4:51:23 pm
    Author     : taylah-->

<html>
    <head>
        <title>IoT Bay Application</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script type="text/javascript" src="js.script.js"> </script>
    </head>
     <!-- Establish DB connection through Conn Servlet and display buttons -->
    <body onload="startTime()">
        <h1>Welcome to IoTBay!</h1>
        <div> <span class="time" id="time"> </span> </div>
        <h3>Please choose from the following:</h3>
        <div>
            <br>
                <a class="button" href="register.jsp">Register</a> 
                <a class="button" href="login.jsp">Login</a>
                <a href="main.jsp">Browse The Application</a>
                <a href="DeviceCatalogueServlet">Devices</a>
        </div>
        <%-- include conn servlet at the start of the session --%>
        <jsp:include page="/ConnServlet" flush="true"/>
    </body>
</html>
