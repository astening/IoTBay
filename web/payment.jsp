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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Payment Details</h1>
        <h2>Update Card Details</h2>
<!--        - name on card
        - card number
        - expiry date
        - CVV
        - save button -->
        <form>
            <table>
                <tr>
                    <td><input type="text" id ="cardName" placeholder="Name on card"</td>
                </tr>
                <tr>
                    <td><input id="cardno" type="tel" pattern="[0-9\s]{13,19}"
                        maxlength="19" placeholder="xxxx xxxx xxxx xxxx" required></td>
                </tr>
                <tr>
                    <td><input id="expiry" type="text" inputmode="numeric" placeholder="Expiry (MM/YY)" maxlength="5" required></td>
                </tr>
            </table>
            <button>Submit</button>
        </form>

        <h2>Payment History</h2>
    </body>
</html>
