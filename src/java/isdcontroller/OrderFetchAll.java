/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.Order;
import isdmodeldao.* ;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chari
 */

public class OrderFetchAll extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderSearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderSearchServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        // Retrieve session from the request
        HttpSession session = request.getSession() ;
        
        // Create a validator object and clear the session
        Validator validator = new Validator() ;
        validator.clear(session) ;
        
        // retrieve the manager instance from session
        DBManager manager = (DBManager) session.getAttribute("manager") ;
        
        // set up ArrayList and order for search results
        ArrayList<Order> list = new ArrayList() ;
        Order resultOrder ;
        
        // return all orders
        try { // need to add an if else --> if staff, fetch all, if user, fetch user orders, if not logged in, fetch session orders?
//                    
            list = manager.fetchAllOrders() ;
            session.setAttribute("searched", "Search request sent");
            session.setAttribute("resultList", list) ; // add order to session
        } catch (SQLException ex) {
            Logger.getLogger(UpdateOrderStatus.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("searched", "Search not successful") ;
        }

        request.getRequestDispatcher("orders.jsp").include(request, response) ;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
