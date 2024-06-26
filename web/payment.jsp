<%-- 
    Document   : payment
    Created on : May 8, 2024, 1:51:49 AM
    Author     : Ella
--%>
<%@page import="isdmodel.PaymentMethod"%>
<%@page import="isdmodel.Payment"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="isdmodel.User"%>
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
            //get error messages
            String nameErr = (String) session.getAttribute("nameErr");
            String cardNoErr = (String) session.getAttribute("cardNoErr");
            String expiryDateErr = (String) session.getAttribute("expiryDateErr");
            String cvvErr = (String) session.getAttribute("cvvErr");
            
            //get status message to show user
            String statusMsg = (String) session.getAttribute("statusMsg");
            
            //reset varaibles used in input boxes
            String cardName="";
            String cardNo="";
            String cvv = "";
            String expiryDate = "";
            
            //date formatter for expiry date pattern
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
          
            //get paymentMethod obj from session (linked to user)
            PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("paymentMethod");
            
            //get user's card details if they have one saved
            if(paymentMethod!=null){
                cardName = paymentMethod.getCardName();
                cardNo = paymentMethod.getCardNo();
                LocalDate expiry = paymentMethod.getExpiryDate();
                expiryDate = expiry.format(formatter);
                cvv = paymentMethod.getCvv()+"";
            }
            
            //get users payment history and search result
            ArrayList<Payment> payments = (ArrayList<Payment>) session.getAttribute("payments");
            ArrayList<Payment> searchResults = (ArrayList<Payment>) session.getAttribute("searchResults");
        %>

    <% User user = (User) session.getAttribute("user") ; %>
    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <% if (user != null) { %>
            <a class="node" href="account.jsp">Account</a>
        <% } %>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <a class="active" href="payment.jsp">Payments</a>
        <% if (user != null && user.getPosition().equals("Systems Admin")) { %>
            <a class="node" href="customerInformationManagement.jsp">Manage Customers</a>
            <a class="node" href="StaffInformationManagement.jsp">Manage Staff</a>
        <% } %>
        <div class="navBar-right">
            <% if (user==null){ %>
                <a class="node" href="register.jsp">Register</a>
                <a class="node" href="login.jsp">Login</a>
            <% } else {%>
                <a class="node" href="logout.jsp">Logout</a>
            <% } %>
        </div>
    </div>        
        
        <br>
        <h1>Payment Details</h1>
        <h2>Edit Card Details</h2>
        <h3><span> <%=(statusMsg!=null? statusMsg : "")%></h3>
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
            <a class ="button" href="DeletePaymentMethodServlet">Delete</a>
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
            </div><br>
            
            <table id="table">
                <tr>
                    <th>PaymentID</th>
                    <th>OrderID</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
            <%  //loop to generate table rows + data for each search  result
                    if(searchResults!=null){
                        for(Payment p : searchResults){
                %>
                        <tr>
                            <td><%=p.getPaymentID()%></td>
                            <td><%=p.getOrderID()%></td>
                            <td><%=p.getPaymentAmount()%></td>
                            <td><%=p.getPaymentDate()%></td>
                        </tr>
                <%
                        }
                    }
                %>
                
            </table><br>
            
            <h3>Full Payment History</h3>
            <table id="table">
                <tr>
                    <th>PaymentID</th>
                    <th>OrderID</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
                <% //loop to generate table rows + data for each payment in the user's payment history
                    if(payments!=null){
                        for(Payment p : payments){
                %>
                        <tr>
                            <td><%=p.getPaymentID()%></td>
                            <td><%=p.getOrderID()%></td>
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
