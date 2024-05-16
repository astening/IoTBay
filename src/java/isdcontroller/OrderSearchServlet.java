/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chari
 */

//tbc obviously
public class OrderSearchServlet extends HttpServlet {
    
    private DBConnector db;

    private DBManager manager;

    private Connection conn;
    
    @Override //Create an instance of DBConnector for the deployment session

    public void init() {

        try {

            db = new DBConnector();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }      

    }

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
            out.println("<title>Servlet OrderSearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderSearchServlet at " + request.getContextPath() + "</h1>");
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
        
        // Capture and store input from the session request
        String orderDate = (String) request.getParameter("orderDate") ;
        
        String orderID = (String) request.getParameter("orderID") ;
        int intOrderID = 0;
        try {
            intOrderID = Integer.parseInt(orderID) ;
        }
        catch (NumberFormatException e) {
            session.setAttribute("IDvalidated", "Provide a valid order ID") ;
        }
        
        // set up db manager
        conn = db.openConnection();       
        try {
            manager = new DBManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

            // check that the values are filled in and correct before sending to db
            if (validator.checkEmpty(2, orderID)) {
                session.setAttribute("IDValidated", "ID is empty") ;
            }
            else if (validator.checkEmpty(2, orderDate)) {
                session.setAttribute("dateValidated", "Date is empty") ;
            }
            else if (!validator.validateNumber(orderID)) {
                session.setAttribute("IDvalidated", "Fill in a valid ID") ;
            }
            else {

                try {
                    intOrderID = Integer.parseInt(orderID) ;
                }
                catch (NumberFormatException e) {
                    session.setAttribute("updated", "Search not successful") ;
                }
                        
                try {
//                    
                    manager.findOrder(intOrderID, orderDate) ;
                    session.setAttribute("searched", "Search request sent"); // could change to search
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
                    session.setAttribute("searched", "Search not successful") ;
                }
            }
        
        // pretty sure this could be better
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
    }// </editor-fold>

}
