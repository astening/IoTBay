/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;
import isdmodeldao.DBManager; // Adjust import for the DBManager class

/**
 *
 * @author William Sinclair
 */

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ShowAllStaffServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            HttpSession session = request.getSession();
            DBManager manager = (DBManager) session.getAttribute("manager");
       try { 
            request.setAttribute("staffList", manager.fetchStaff());
            request.getRequestDispatcher("StaffInformationManagement.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle your exception here
            System.out.println("Failed to fetch StaffList");
        }
    }
}
