<%-- 
    Document   : logout
    Created on : 1 Apr 2024, 4:51:23 pm
    Author     : anna
--%>

package uts.isd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uts.isd.model.User;
import java.sql.*;
import java.util.logging.*;
import java.io.IOException;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnector db = new DBConnector();
            Connection conn = db.openConnection();
            DBManager manager = new DBManager(conn);
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                manager.updateLogoutTime(user.getUserID()); // Update logout time
                session.invalidate(); // Invalidate session
            }

            response.sendRedirect("logout.jsp"); // Redirect to logout page
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Database access error", ex);
        }
    }
}
