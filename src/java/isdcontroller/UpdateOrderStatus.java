/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

/**
 *
 * @author chari
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import isdmodeldao.*;

   public class UpdateOrderStatus extends HttpServlet {

       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response)

               throws ServletException, IOException {

           response.setContentType("text/html;charset=UTF-8");                

       }
       
       
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1- retrieve the current session
            HttpSession session = request.getSession() ;
            
            //2- create a validator object and clear the session variables
            Validator validator = new Validator() ;
            validator.clear(session) ;

            //3- capture the posted orderID and attempt to parse as an Integer    
            String stringOrderID = (String) request.getParameter("orderID") ;
            int orderID =  0 ;
            try {
                orderID = Integer.parseInt(stringOrderID) ;
            }
            catch (NumberFormatException e) {
                session.setAttribute("IDValidated", "Please enter an ID below");
            }
            
            
            //4- capture the posted status 
            String status = (String) request.getParameter("status") ;

            // retrieve the manager instance from session
            DBManager manager = (DBManager) session.getAttribute("manager") ;            

            // check that the values are filled in and correct
            // before updating the db
            if(validator.checkEmpty(orderID, status)) {
                session.setAttribute("statusValidated", "Provide a status") ;
                session.setAttribute("IDvalidated", "Provide a valid order ID") ;
            }
            else if (!validator.validateNumber(stringOrderID)) {
                session.setAttribute("IDvalidated", "Fill in a valid ID") ;
            }
            else if (!validator.validateStatus(status)) {
                session.setAttribute("statusValidated", "Fill in a valid status") ;
            }
            else {
                // use delete method to update status
                if (status.equals("Delete") || status.equals("Cancel") || status.equals("Cancelled") || status.equals("Deleted")) {
                    try {
                        manager.deleteOrder(orderID) ;
                        session.setAttribute("updated", "Cancellation successful");
                    } catch (SQLException ex) {
                        Logger.getLogger(UpdateOrderStatus.class.getName()).log(Level.SEVERE, null, ex);
                        session.setAttribute("updated", "Cancellation unsuccessful") ;
                    }
                }
                else {
                    try {
                        manager.updateOrderStatus(orderID, status) ;
                        session.setAttribute("updated", "Update successful");
                    } catch (SQLException ex) {
                        Logger.getLogger(UpdateStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
                        session.setAttribute("updated", "Update not successful") ;
                    }                    
                }

            }
                
                
            request.getRequestDispatcher("orders.jsp").include(request, response) ; // where do i put this?
                
       }

   }
