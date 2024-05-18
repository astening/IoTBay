/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package uts.isd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uts.isd.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException; // Added the missing import
import java.util.Date;

import uts.isd.model.dao.DBManager;
import java.util.logging.Level; // Fixed import for Logger.Level
import java.util.logging.Logger; // Fixed import for Logger
import uts.isd.model.dao.DBConnector;

public class RegistrationServlet extends HttpServlet {
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/iotdb";
    private static final String JDBC_USER = "isduser";
    private static final String JDBC_PASSWORD = "admin";
   

private DBConnector db;

       private DBManager manager;

       private Connection conn;

    @Override
       public void init() {

           try {

               DBConnector db = new DBConnector();

           } catch (ClassNotFoundException | SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }      

       }

    /*Post method creates new Http Session and Validator. 
    Following Validation Checks, it creates the user in the DB and saves them to the session */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       Validator validator = new Validator();
        // Capture the registration data from the form
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        Integer phoneNo = Integer.valueOf(request.getParameter("phoneNo"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        Integer postcode = Integer.valueOf(request.getParameter("postcode"));
        boolean activation = true;
        Date registrationDate = new java.sql.Date(new Date().getTime());
        String position = request.getParameter("position");
        
        //Create a DB Manager instance and an empty user
       DBManager manager = (DBManager) session.getAttribute("manager");
        //DBManager manager = (conn) ;
       User user = null;
       validator.clear(session);
       if(!validator.validateFName(fname)) {
            session.setAttribute("fnameErr", "Error: Fname format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validateLName(lname)) {
            session.setAttribute("lnameErr", "Error: Lname format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validatePhone(request.getParameter("phoneNo"))) {
            session.setAttribute("phoneErr", "Error: Phone Number format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validateAddress(request.getParameter("address"))) {
            session.setAttribute("addressErr", "Error: Address format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validateCity(request.getParameter("city"))) {
            session.setAttribute("cityErr", "Error: City format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validateState(request.getParameter("state"))) {
            session.setAttribute("stateErr", "Error: State format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validatePostcode(request.getParameter("postcode"))) {
            session.setAttribute("postErr", "Error: Postcode format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!validator.validatePosition(position)) {
            session.setAttribute("positionErr", "Error: Position format incorrect");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            try {
                // Store registration data in the database
                manager.addUser(fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
                user = manager.findUser(email, password); // Adjusted to match the method signature in DBManager
                // Forward the request to a confirmation page or display a success message
                session.setAttribute("user", user);
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
