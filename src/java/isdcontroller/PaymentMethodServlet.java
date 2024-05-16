/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package isdcontroller;

/**
 *
 * @author gubbs
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import isdmodel.*;
import isdmodeldao.DBManager;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PaymentMethodServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        int userID = Integer.parseInt(request.getParameter("userID"));
        //reset all variables used
        PaymentMethod paymentMethod = null;
        session.setAttribute("statusMsg", null);
        ArrayList<Payment> payments = null;
        
        Validator validator = new Validator();
        validator.clear(session);
        
        try{
            //gets the users saved card if they have one
            if(manager.checkPaymentMethod(userID)){
                paymentMethod = manager.findPaymentMethod(userID);
                session.setAttribute("paymentMethod", paymentMethod);
            }
            //gets the users payment history if they have any payments
            if(manager.checkPayments(userID)){
                payments = manager.getPayments(userID);
                session.setAttribute("payments", payments);
            }
            
        } catch (SQLException ex){
            Logger.getLogger(PaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
            response.sendRedirect("payment.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("paymentMethod");
        
        //get the inputted card details from the form inputs
        String cardName = request.getParameter("cardName");
        String cardNo = request.getParameter("cardNo");
        String expiryString = request.getParameter("expiryDate");
        String cvvString = request.getParameter("cvv");
        int cvv = Integer.valueOf(cvvString);
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();
        validator.clear(session);
        session.setAttribute("statusMsg", null);
        
        //validate all inputted data
        if(!validator.validateName(cardName)){
            session.setAttribute("nameErr", "Error: Card Name format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        }
        else if (!validator.validateCardNo(cardNo)){
            session.setAttribute("cardNoErr", "Error: Card Number format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        }
        else if (!validator.validateExpiryDate(expiryString)){
            session.setAttribute("expiryDateErr", "Error: Card Expiry Date format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        }
        else if (!validator.validateCVV(cvvString)){
            session.setAttribute("cvvErr", "Error: Card CVV/CVC format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        } else{
            try{
                //format date correctly from expiry date formatting to LocalDate formatting
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                YearMonth yearMonth = YearMonth.parse(expiryString, formatter);
                LocalDate expiryDate = yearMonth.atEndOfMonth();
                
                //updates card details if user has a saved card
                //status message updated
                if(paymentMethod!=null){
                    manager.updatePaymentMethod(userID, cardName, cardNo, cvv, expiryDate);
                    paymentMethod.setCardName(cardName);
                    paymentMethod.setCardNo(cardNo);
                    paymentMethod.setCvv(cvv);
                    paymentMethod.setExpiryDate(expiryDate);
                    session.setAttribute("statusMsg", "Card details updated successfully");
                } else{
                    //creates a new card attached to user if there isnt one already
                    //status message updated
                    manager.addPaymentMethod(userID, cardName, cardNo, cvv, expiryDate);
                    paymentMethod = manager.findPaymentMethod(userID);
                    session.setAttribute("paymentMethod", paymentMethod);
                    session.setAttribute("statusMsg", "Card details created successfully");
                }
            } catch (SQLException ex){
                Logger.getLogger(PaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
            }
              response.sendRedirect("payment.jsp");
        }
    }
}
