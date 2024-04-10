<!-- 
    Document   : logout
    Created on : 1 Apr 2024, 4:51:23 pm
    Author     : anna

    Logout page – Implement the logout page for users to use from the main page. 
    The logout page must redirect users to the index page and terminate the session.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <h1>Logout Page</h1>
        <%
            // Invalidate the session to logout the user
            session.invalidate();
        %>
        <p>You have been logged out.</p>
        <p><a href="login.jsp">Login Again</a></p>
    </body>
</html>
