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
        ArrayList<Payment> searchResults = new ArrayList<Payment>();
        ArrayList<Payment> payments = (ArrayList<Payment>) session.getAttribute("payments");
        
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();
        
        //gets the inputted data from the enter paymentID and date boxes
        String paymentIDString = request.getParameter("paymentID");
        String pdate = request.getParameter("paymentDate");
        
        //date formatter for turning input data into correct LocalDate format
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate paymentDate =null;
        int paymentID = 0;
        
        //checks if no date has been inputted before trying to parse
        if(!pdate.isEmpty()){
            paymentDate = LocalDate.parse(pdate, df);
        }
        
        //checks if no paymentID has been inputted before trying to get integer value
        if(!paymentIDString.isEmpty()){
             paymentID = Integer.valueOf(paymentIDString);
        }
        
        //loops over array of users payments and checks if the inputted data matches any
        for(Payment payment : payments){
            if(payment.getPaymentID()==paymentID || payment.getPaymentDate().equals(paymentDate)){
                searchResults.add(payment);
            }
        }
        
        session.setAttribute("searchResults", searchResults);
        response.sendRedirect("payment.jsp");
        
    }
}
