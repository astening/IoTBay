/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.PaymentMethod;
import isdmodel.User;
import java.sql.SQLException;
import isdmodeldao.DBManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ella
 */
public class DeletePaymentMethodServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        PaymentMethod paymentMethod = (PaymentMethod) session.getAttribute("paymentMethod");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        try {
            manager.deletePaymenetMethod(paymentMethod.getPaymentMethodID());
            paymentMethod = null;
            session.setAttribute("paymentMethod", null);
            
        } catch (SQLException ex){
            Logger.getLogger(DeletePaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
        request.getRequestDispatcher("payment.jsp").include(request, response);
    }
}
