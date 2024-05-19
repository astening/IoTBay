/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import isdmodel.AccessLog;
import isdmodel.User;
import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;

@WebServlet("/viewAccessLogs")
public class ViewAccessLogsServlet extends HttpServlet {

    private DBConnector db;
    private DBManager manager;
    private Connection conn;

    @Override
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            manager = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user != null) {
        try {
            ArrayList<AccessLog> logs = manager.getUserAccessLogs(user.getUserID());
            request.setAttribute("accessLogs", logs); // Set all access logs
            RequestDispatcher dispatcher = request.getRequestDispatcher("userLogs.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    } else {
        response.sendRedirect("login.jsp");
    }
}

    @Override
    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewAccessLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
