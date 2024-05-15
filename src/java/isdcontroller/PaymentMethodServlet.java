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

public class PaymentMethodServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        HttpSession session = request.getSession();
        int userID = Integer.parseInt(request.getParameter("userID"));
        DBManager manager = (DBManager) session.getAttribute("manager");
        PaymentMethod paymentMethod = null;
        
        Validator validator = new Validator();
        validator.clear(session);
        
        try{
            if(manager.checkPaymentMethod(userID)){
                paymentMethod = manager.findPaymentMethod(userID);
                session.setAttribute("paymentMethod", paymentMethod);
            }
        } catch (SQLException ex){
            Logger.getLogger(PaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
         request.getRequestDispatcher("payment.jsp").include(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        
        //name = parameter, not id
        String cardName = request.getParameter("cardName");
        String cardNo = request.getParameter("cardNo");
        String expiryString = request.getParameter("expiryDate");
        String cvvString = request.getParameter("cvv");
        int cvv = Integer.valueOf(cvvString);
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();
        validator.clear(session);
        
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                YearMonth yearMonth = YearMonth.parse(expiryString, formatter);
                LocalDate expiryDate = yearMonth.atEndOfMonth();
                manager.updatePaymentMethod(userID, cardName, cardNo, cvv, expiryDate);
            } catch (SQLException ex){
                Logger.getLogger(PaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
            }
            request.getRequestDispatcher("payment.jsp").include(request, response);
        }
    }
}
