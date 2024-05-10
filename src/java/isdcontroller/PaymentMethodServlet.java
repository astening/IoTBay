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
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import isdmodeldao.PaymentMethodDBManager;
import isdmodel.*;

public class PaymentMethodServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String cardNo = request.getParameter("cardNo");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        PaymentMethodDBManager manager = (PaymentMethodDBManager) session.getAttribute("manager");
        PaymentMethod paymentMethod = null;
        validator.clear(session);
        
        if(!validator.validateCardNo(cardNo)){
            session.setAttribute("cardNoErr", "Error: Card Number format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        } else if (!validator.validateExpiryDate(expiryDate)){
            session.setAttribute("expiryDateErr", "Error: Card Expiry Date format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        } else if (!validator.validateCVV(cvv)){
            session.setAttribute("cvvErr", "Error: Card CVV format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response);
        }
        
    }
}
