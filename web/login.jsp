<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript" src="js/script.js"></script>
        <title>Login</title>
    </head>

    <body onload="startTime();">
        <div> <span class="time" id="time"></span></div>
                <%
                   String existErr = (String) session.getAttribute("existErr");
                   String emailErr = (String) session.getAttribute("emailErr");
                   String passErr = (String) session.getAttribute("passErr");
                %>

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <a class="node" href="account.jsp">Account</a>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <a class="node" href="payment.jsp">Payments</a>
        <div class="navBar-right">
            <a class="node" href="register.jsp">Register</a>
            <a class="active" href="login.jsp">Login</a>
        </div>
    </div>
        
        <h1>Login <span class="message"> <%=(existErr != null ? existErr : "")%> </span></h1>
        <form action="LoginServlet" method="post">
            <table id="form_table">
                <tr>
                    <td>Email:</td>
                    <td><input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email:")%>" name="email" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password:")%>" name="password" required></td>
                </tr>
                <tr><td><td>
                    <td><a href="index.jsp" class="button">Cancel</a>
                        <input class="button" type="submit" value="Log in">
                    </td>
                </tr>
            </table>
           <br>
        </form>
    </body>
</html>
