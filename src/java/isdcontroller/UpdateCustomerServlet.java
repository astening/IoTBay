/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

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
public class UpdateCustomerServlet extends HttpServlet {
    //doPost method allows the servlet to process data sent from Customer Information Management page and send back a response to the user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //create new HTTP session
        HttpSession session = request.getSession();
        //create new instance of validator to validate user input
        Validator validator = new Validator();
        //create connection to database to access customer records
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        //get input from update customer form
        int userID = Integer.parseInt(request.getParameter("userID"));
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        Integer phoneNo = Integer.valueOf(request.getParameter("phoneNo"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        Integer postcode = Integer.valueOf(request.getParameter("postcode"));
        boolean activation = Boolean.parseBoolean(request.getParameter("activation"));
        String position = request.getParameter("position");
        
        //clear validator for session
        validator.clear(session);
        //if input isn't valid, show error message in textfield
        if (!validator.validateFName(fName)) {
            session.setAttribute("fnameError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validateLName(lName)) {
            session.setAttribute("lnameError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validateAddress(request.getParameter("address"))) {
            session.setAttribute("addressError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validateCity(request.getParameter("city"))) {
            session.setAttribute("cityError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validateState(request.getParameter("state"))) {
            session.setAttribute("stateError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else if (!validator.validatePostcode(request.getParameter("postcode"))) {
            session.setAttribute("postError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        }  else if (!validator.validatePosition(position)) {
            session.setAttribute("positionError", "Error: Incorrect format");
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        } else {
            try {
                //update customer in database
                manager.updateCustomer(userID, fName, lName, phoneNo, email, password, address, city, state, postcode, activation, position);
                response.sendRedirect("customerInformationManagement.jsp");
            } catch (SQLException ex) {
                //get information about error encountered in database
                ex.printStackTrace();
                System.out.println("SQLException error - Failed to update Customer in database");
                //redirect user to Customer Information Management Page
                request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
            }
        }
    }
}
