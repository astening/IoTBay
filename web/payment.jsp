<%-- 
    Document   : payment
    Created on : May 8, 2024, 1:51:49 AM
    Author     : Ella
--%>
<%@page import="isdmodel.PaymentMethod"%>
<%@page import="isdmodel.User"%>
<%@page import="isdmodel.Payment"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
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
            
            String statusMsg = (String) session.getAttribute("statusMsg");
            
            String cardName="";
            String cardNo="";
            String cvv = "";
            String expiryDate = "";
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
          
            User user = (User) session.getAttribute("user");
            PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("paymentMethod");
//            PaymentMethod paymentMethod = null;

            if(paymentMethod!=null){
                cardName = paymentMethod.getCardName();
                cardNo = paymentMethod.getCardNo();
                LocalDate expiry = paymentMethod.getExpiryDate();
                expiryDate = expiry.format(formatter);
                cvv = paymentMethod.getCvv()+"";
            }
            
            ArrayList<Payment> payments = (ArrayList<Payment>) session.getAttribute("payments");
            ArrayList<Payment> searchResults = (ArrayList<Payment>) session.getAttribute("searchResults");
        %>
        <h1>Payment Details</h1>
        <h2>Edit Card Details <span> <%=(statusMsg!=null? "- "+statusMsg : "")%></span></h2>
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
            </table><br>
            <button>Submit</button>
            <a class ="button" href="DeletePaymentMethodServlet" class="<%=(paymentMethod!=null? "" : "disabled")%>">Delete</a>
        </form>
                <br>

        <h2>Payment History</h2>
        <form method ="POST" action="PaymentsServlet">
            <h3>Payment History Lookup</h3>
            <div>
                Enter PaymentID
                <input type="number" name="paymentID" placeholder="321" min="1">
                Enter Date
                <input type="date" name="paymentDate" placeholder="Enter date" max="2024-05-17">
                <button>Search</button>
            </div><br><br>
            
            <table id="table">
                <tr>
                    <th>PaymentID</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            <%
                    if(searchResults!=null){
                        for(Payment p : searchResults){
                %>
                        <tr>
                            <td><%=p.getPaymentID()%></td>
                            <td><%=p.getPaymentAmount()%></td>
                            <td><%=p.getPaymentDate()%></td>
                        </tr>
                <%
                        }
                    }
                %>
                
            </table>
            
            <h3>Full Payment History</h3>
            <table id="table">
                <tr>
                    <th>PaymentID</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
                <%
                    if(payments!=null){
                        for(Payment p : payments){
                %>
                        <tr>
                            <td><%=p.getPaymentID()%></td>
                            <td><%=p.getPaymentAmount()%></td>
                            <td><%=p.getPaymentDate()%></td>
                        </tr>
                <%
                        }
                    }
                %>
            </table>
        </form>
        
        
    </body>
</html>
