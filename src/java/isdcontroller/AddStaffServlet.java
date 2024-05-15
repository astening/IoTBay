/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodeldao.DBManager;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

public class AddStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            DBManager manager = (DBManager) session.getAttribute("manager");

            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
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
        try {
            manager.addStaff(fName, lName, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
            response.sendRedirect("ShowAllStaffServlet");
            System.out.println("Successfully added staff member to the database!");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle your exception here
            System.out.println("Failed to add staff member to the database.");
        }
    }
}
