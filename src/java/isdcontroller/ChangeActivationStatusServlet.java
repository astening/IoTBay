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
public class ChangeActivationStatusServlet extends HttpServlet {
    //doPost method allows the servlet to process data sent from Customer Information Management page and send back a response to the user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //create new HTTP session
        HttpSession session = request.getSession();
        //create connection to database to access customer records
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        //get input from change account status form
        boolean activation = Boolean.parseBoolean(request.getParameter("activation"));
        int userID = Integer.parseInt(request.getParameter("userID"));
            
        try {
            //change customer status in database
            manager.changeCustomerStatus(userID, activation);
            //direct user to Customer Information Management Page
            response.sendRedirect("customerInformationManagement.jsp");
            System.out.println("Customer updated");
        } catch (SQLException ex) {
            //get information about error encountered in database
            ex.printStackTrace();
            System.out.println("SQLException error - Failed to update Customer in database");
            //redirect user to Customer Information Management Page
            request.getRequestDispatcher("customerInformationManagement.jsp").forward(request, response);
        }
    }
}