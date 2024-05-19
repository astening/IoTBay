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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import uts.isd.model.AccessLog;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

@WebServlet("/searchAccessLogs")
public class SearchAccessLogsServlet extends HttpServlet {
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
            Logger.getLogger(SearchAccessLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(startDateStr);
                Date endDate = sdf.parse(endDateStr);

                ArrayList<AccessLog> searchResults = manager.searchUserAccessLogs(user.getUserID(), startDate, endDate);
                request.setAttribute("searchResults", searchResults);
                request.setAttribute("searched", true); // Indicate that a search was performed

                // Also fetch all access logs for the user
                ArrayList<AccessLog> logs = manager.getUserAccessLogs(user.getUserID());
                request.setAttribute("accessLogs", logs);

                RequestDispatcher dispatcher = request.getRequestDispatcher("userLogs.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException | ParseException e) {
                throw new ServletException("Error processing request", e);
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
            Logger.getLogger(SearchAccessLogsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
