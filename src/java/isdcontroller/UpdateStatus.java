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

   import java.sql.Connection;

   import java.sql.SQLException;

   import java.util.logging.Level;

   import java.util.logging.Logger;

   import jakarta.servlet.ServletException;

   import jakarta.servlet.http.HttpServlet;

   import jakarta.servlet.http.HttpServletRequest;

   import jakarta.servlet.http.HttpServletResponse;

   import jakarta.servlet.http.HttpSession;

   import isdmodeldao.*;

 

   public class UpdateStatus extends HttpServlet {

 

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

      

       @Override //Add the DBConnector, DBManager, Connection instances to the session

       protected void doGet(HttpServletRequest request, HttpServletResponse response)

               throws ServletException, IOException {

           response.setContentType("text/html;charset=UTF-8");       

//           HttpSession session = request.getSession();
//
//           conn = db.openConnection();       
//
//           try {
//
//               manager = new DBManager(conn);
//
//           } catch (SQLException ex) {
//
//               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
//
//           }

           //export the DB manager to the view-session (JSPs)

//           session.setAttribute("manager", manager);           

       }
       
       
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //1- retrieve the current session
            HttpSession session = request.getSession() ;

            //3- capture the posted orderID      
            String stringOrderID = (String) request.getParameter("orderID") ;
            int orderID = Integer.parseInt(stringOrderID) ;
            
            //4- capture the posted status 
            String status = (String) request.getParameter("status") ;

            //4.1 - set the manager attribute
//            session.setAttribute("manager", manager);
            
            //5- retrieve the manager instance from session
//            manager = (DBManager) session.getAttribute("manager") ;


           conn = db.openConnection();       

           try {

               manager = new DBManager(conn);

           } catch (SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }
            
                try {
                    
                    manager.updateOrderStatus(orderID, status) ;
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            request.getRequestDispatcher("orders.jsp").include(request, response) ;
                
       }

        

//       @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
//
//        public void destroy() {
//
//           try {
//
//               db.closeConnection();
//
//           } catch (SQLException ex) {
//
//               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
//
//           }
//
//       }

   }
