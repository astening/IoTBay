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
        <title>Login</title>
    </head>
    
    <body>
        
        <form action="LoginServlet">
            Please enter your email 		
            <input type="text" name="un"/><br>		
            
            Please enter your password
            <input type="text" name="pw"/>
            <input type="submit" value="submit">			
        
        </form>
    </body>
</html>
