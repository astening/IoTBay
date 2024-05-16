/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.Payment;
import isdmodel.User;
import isdmodeldao.DBManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Ella
 */
public class PaymentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validate = new Validator();
        DBManager manager = (DBManager) session.getAttribute("manager");
//        Payment payment = null;
        ArrayList<Payment> searchResults = new ArrayList<Payment>();
        ArrayList<Payment> payments = (ArrayList<Payment>) session.getAttribute("payments");
        
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();
        String paymentIDString = request.getParameter("paymentID");
       
        String pdate = request.getParameter("paymentDate");
        
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate paymentDate =null;
        int paymentID = 0;
        
        if(!pdate.isEmpty()){
            paymentDate = LocalDate.parse(pdate, df);
        }
        
        if(!paymentIDString.isEmpty()){
             paymentID = Integer.valueOf(paymentIDString);
        }
        
        for(Payment payment : payments){
            if(payment.getPaymentID()==paymentID || payment.getPaymentDate().equals(paymentDate)){
                searchResults.add(payment);
            }
        }
        
        session.setAttribute("searchResults", searchResults);
        response.sendRedirect("payment.jsp");
        
    }
}
