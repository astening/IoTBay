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

/*Post method creates new Http Session. Gets the userID and deletes the matching user */
public class DeleteStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
            DBManager manager = (DBManager) session.getAttribute("manager");

            int userID = Integer.parseInt(request.getParameter("userID"));
   try {
            manager.deleteStaff(userID);
            response.sendRedirect("ShowAllStaffServlet");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception here
            System.out.println("SQL Exception: Failed to find Staff member to delete");
            request.getRequestDispatcher("StaffInformationManagement.jsp").forward(request, response);
        }
    }
}
