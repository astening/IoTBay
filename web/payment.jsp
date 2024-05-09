<%-- 
    Document   : payment
    Created on : May 8, 2024, 1:51:49 AM
    Author     : Ella
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Payment Details</h1>
        <h2>Update Card Details</h2>
        <form method="POST">
            <table>
                <tr>
                    <td>Name on card</td>
                </tr>
                <tr>
                    <td><input type="text" id ="John Smith" placeholder="Name on card"</td>
                </tr>
                <tr>
                    <td>Card number</td>
                </tr>
                <tr>
                    <td><input id="cardno" type="text" pattern="[0-9\s]{16,19}"
                        maxlength="19" placeholder="xxxx xxxx xxxx xxxx" required></td>
                </tr>
                <tr>
                    <td>Expiry date</td>
                </tr>
                <tr>
                    <td><input id="expiry" type="text" placeholder="MM/YY" maxlength="5" required></td>
                </tr>
                <tr>
                <tr>
                    <td>CVC</td>
                </tr>
                    <td><input id="CVC" type="text" placeholder="123" pattern="[0-9]{3}" maxlength="3" required></td>
                </tr>
            </table>
            <button>Submit</button>
        </form>

        <h2>Payment History</h2>
        
    </body>
</html>
