/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.User;
import isdmodeldao.DBManager;
import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FindStaffServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            DBManager manager = (DBManager) session.getAttribute("manager");

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String position = request.getParameter("position");
            User foundstaff = null;
       try {
            foundstaff = manager.findStaff(fname, lname, position);
            request.setAttribute("foundStaff", foundstaff);
            request.getRequestDispatcher("StaffInformationManagement.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle your exception here
            System.out.println("Staff Member does not exist in the database.");
        }
    }
}
