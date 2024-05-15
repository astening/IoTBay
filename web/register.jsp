<%-- 
    Document   : register.jsp
    Created on : 22 Mar 2024, 1:30:05 pm
    Author     : anna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./main.css">
    <title>Registration Form</title>
</head>
<body>
    <h1>Registration Form</h1>
    
    <form class="form-register" method="POST" action="RegisterServlet">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="fName" required></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lName" required></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><input type="text" name="phoneNo" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Register"></td>
            </tr>
        </table>
    </form>

    <%-- Check if error parameter exists --%>
    <%
        String error = request.getParameter("error");
        if (error != null && error.equals("true")) {
    %>
        <p>Error occurred during registration. Please try again.</p>
    <% } %>
</body>
