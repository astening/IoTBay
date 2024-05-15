<%-- 
    Document   : payment
    Created on : May 8, 2024, 1:51:49 AM
    Author     : Ella
--%>
<%@page import="isdmodel.PaymentMethod"%>
<%@page import="isdmodel.User"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String nameErr = (String) session.getAttribute("nameErr");
            String cardNoErr = (String) session.getAttribute("cardNoErr");
            String expiryDateErr = (String) session.getAttribute("expiryDateErr");
            String cvvErr = (String) session.getAttribute("cvvErr");
            
            String cardName="";
            String cardNo="";
            String cvv = "";
            String expiryDate = "";
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
          
//            PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("paymentMethod");
            PaymentMethod paymentMethod = null;
            if(paymentMethod!=null){
                cardName = paymentMethod.getCardName();
                cardNo = paymentMethod.getCardNo();
                LocalDate expiry = paymentMethod.getExpiryDate();
                expiryDate = expiry.format(formatter);
                cvv = paymentMethod.getCvv()+"";
            }
        %>
        <h1>Payment Details</h1>
        <h2>Update Card Details</h2>
        <form method="POST" action="PaymentMethodServlet">
            <table>
                <tr>
                    <td>Name on card</td>
                </tr>
                <tr>
                    <td><input type="text" name="cardName" value = "<%=(nameErr!=null? nameErr : cardName)%>" placeholder="John Smith" required></td>
                </tr>
                <tr>
                    <td>Card number</td>
                </tr>
                <tr>
                    <td><input type="text" name="cardNo" value = "<%=(cardNoErr!=null? cardNoErr : cardNo)%>" placeholder="xxxx xxxx xxxx xxxx" maxlength="19" required></td>
                </tr>
                <tr>
                    <td>Expiry date</td>
                </tr>
                <tr>
                    <td><input name="expiryDate" type="text" value="<%=(expiryDateErr!=null? expiryDateErr : expiryDate)%>" placeholder="MM/YY" maxlength="5" required></td>
                </tr>
                <tr>
                <tr>
                    <td>CVC/CVV</td>
                </tr>
                <td><input name="cvv" type="text" value="<%=(cvvErr!=null? cvvErr : cvv)%>" placeholder="123" maxlength="3" required></td>
                </tr>
            </table>
            <button>Submit</button>
        </form>
                <button <%=(paymentMethod!=null? "enabled" : "disabled")%>>Delete</button>

        <h2>Payment History</h2>
        
        
    </body>
</html>
