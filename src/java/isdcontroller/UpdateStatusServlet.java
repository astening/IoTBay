/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodeldao.DBManager;
import isdmodel.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author chari
 */

public class UpdateStatusServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateStatusServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStatusServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       

            //1- retrieve the current session
            HttpSession session = request.getSession() ;

            //2- create an instance of the Validator class HGEFHK i need to do this   
            Validator validator = new Validator() ;
            validator.clear(session) ;
            
            //3- capture the posted orderID      
//            int orderID = (int) request.getAttribute("orderID") ;
            
            //4- capture the posted status 
            String status = (String) request.getParameter("status") ;

            //5- retrieve the manager instance from session
            DBManager manager = (DBManager)session.getAttribute("manager") ;
                 
            // create order instance
            Order order = null ;       

//            try {       
//
//                //6- find user by email and password
//                User = manager.findUser(email, password) ;
//
//            } catch (SQLException ex) {           
//
//                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
//
//            }

// UP TO HERE
            if (validator.validateStatus(status)     /*7-   validate status  */   ) {           

                    //8-set incorrect status error to the session 
                    session.setAttribute("statusValidated", "Status incorrect format") ;

                    //9- redirect user back to orders.jsp
                    request.getRequestDispatcher("orders.jsp").include(request, response) ;

//            } else if (validator.validatePassword(password) /*10-   validate orderID  */ ) {                  
//
//                    //11-set incorrect password error to the session
//                    session.setAttribute("passwordValidated", "Password incorrect format") ;
//                    
//
//                    //12- redirect user back to the login.jsp 
//                    request.getRequestDispatcher("login.jsp").include(request, response) ;

//            } else if (user!=null) {                     
//
//                    //13-save the logged in user object to the session
//                    session.setAttribute("user", user) ;
//                    manager.updateUser(email, password, name, dob) ;
//               
//
//                    //14- redirect user to the main.jsp
//                    session.setAttribute("updated", "Update was successful") ;
//                    request.getRequestDispatcher("main.jsp").include(request, response) ;

            } else {                       

                    //15-set user does not exist error to the session
                    session.setAttribute("updated", "Update was not successful!") ;
                    

                    //16- redirect user back to the orders.jsp
                    request.getRequestDispatcher("orders.jsp").include(request, response) ;

            }   

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
