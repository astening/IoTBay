/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodeldao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chari
 */
public class UpdateOrderForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
          
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderFormServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");       

           HttpSession session = request.getSession();

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("updated", "Order was not submitted") ;
        
        // Retrieve session from request
        HttpSession session = request.getSession() ;
        
        // Set up a validator object for the session and clear variables
        Validator validator = new Validator() ;
        validator.clear(session) ;
        
        // Retrieve posted data - not working
        String productID = (String) request.getParameter("productID") ; // this is null
        int intProductID = 0 ; // dont think is changed
        int intItemQuantity = 0 ;
        String itemQuantity = (String) request.getParameter("noItems") ; // and this is null
        String orderID = (String) request.getParameter("orderID") ;
        int intOrderID = 0;

        // retrieve the manager instance from session
        DBManager manager = (DBManager) session.getAttribute("manager") ; 
        session.setAttribute("updated", "Order was not submitted") ;
        
        // Check variables are valid before using manager to perform update
        if(validator.checkEmpty(2, productID)) {
            session.setAttribute("IDValidated", "Please provide a product ID") ;
            session.setAttribute("updated", "Order was not submitted") ;
        }
        else if (validator.checkEmpty(2, itemQuantity)) {
            session.setAttribute("quantityValidated", "Please provide a quantity") ;
            session.setAttribute("updated", "Order was not submitted") ;
        }
        else if (validator.checkEmpty(2, orderID)) {
            session.setAttribute("IDValidated", "Please provide an order ID") ;
            session.setAttribute("updated", "Order was not submitted") ;
        }        
//        else if (!validator.validateNumber(itemQuantity)) { // this isnt working properly
//            session.setAttribute("IDValidated", "Please enter a valid quantity number above 0") ;
//        }
        else if (!validator.validateNumber(productID)) {
            session.setAttribute("IDValidated", "Please provide a valid product ID") ;
            session.setAttribute("updated", "Order was not submitted") ;
        }
        else {
            // convert int
            try {
                intItemQuantity = Integer.parseInt(itemQuantity) ;
                intProductID = Integer.parseInt(productID) ;
                intOrderID = Integer.parseInt(orderID) ;
            }
            catch(NumberFormatException e) {
                session.setAttribute("quantityValidated", "Please provide a quantity") ;
                session.setAttribute("updated", "Order was not submitted") ;
            }            
            try {
            int userID = 1 ; // this needs to be worked on
            // Use manager to add the order into the database
            manager.updateOrder(intOrderID, intProductID, intItemQuantity);
               // set confirmation message for product id, quantity
               session.setAttribute("updated", "Order was submitted") ;
               
               // provide user with the order number and date
           } catch (SQLException ex) {
               Logger.getLogger(OrderFormServlet.class.getName()).log(Level.SEVERE, null, ex) ;
               session.setAttribute("updated", "Order was not submitted") ;
           }
        }
        
        request.getRequestDispatcher("orderForm.jsp").include(request, response) ; 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description" ;
    }// </editor-fold>

}
