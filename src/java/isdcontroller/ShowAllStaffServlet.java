/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;
import isdmodel.User; // Adjust import for the User class
import isdmodeldao.DBManager; // Adjust import for the DBManager class

/**
 *
 * @author William Sinclair
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShowAllStaffServlet")
public class ShowAllStaffServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            DBManager manager = (DBManager) session.getAttribute("manager");
            
            request.setAttribute("staffList", manager.fetchStaff());
            request.getRequestDispatcher("StaffInformationManagement.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle your exception here
            System.out.println("Failed to fetch StaffList");
        }
    }
}
