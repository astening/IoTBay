/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;


import isdmodel.User;
import isdmodeldao.DBManager;
import java.io.IOException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet {
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/iotdb";
    private static final String JDBC_USER = "isduser";
    private static final String JDBC_PASSWORD = "admin";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
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
       User user = null;
        try {
            // Store registration data in the database
            manager.addUser(fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
            user = manager.findUser(email, password);
            // Forward the request to a confirmation page or display a success message
            session.setAttribute("user", user);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
