/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.User;
import isdmodeldao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 *
 * @author taylah
 */
public class SearchForSpecificCustomerServlet extends HttpServlet {
    //doPost method allows the servlet to process data sent from Customer Information Management page and send back a response to the user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //create new HTTP session    
        HttpSession session = request.getSession();
        //create new instance of validator to validate user input
        Validator validator = new Validator();
        //create connection to database to access customer records
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        //inititally set foundCustomer as null so that customer record can be assigned later
        User foundCustomer = null;
        //get input from find customer form
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String position = request.getParameter("position");
        
        validator.clear(session);  
        if (!validator.validateFName(fname)) {
            session.setAttribute("fnameError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validateLName(lname)) {
            session.setAttribute("lnameError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response); 
        } else if (!validator.validatePosition(position)) {
            session.setAttribute("positionError", "Error: Invalid Type");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);     
        } else { 
            try {
            //find customer using findCustomer method and assign to foundCustomer
                foundCustomer = manager.findCustomer(fname, lname, position);
                request.setAttribute("foundCustomer", foundCustomer);
                request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);                
            } catch (SQLException ex) {
                //get information about error encountered in database
                ex.printStackTrace();
                System.out.println("SQLException error - Failed to retrieve Customers");
                //redirect user to Customer Information Management Page
                request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
            }
        }
    }
}
