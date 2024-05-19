package isdcontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;

@WebServlet("/deleteAccount")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DBConnector db;
    private DBManager manager;
    private Connection conn;

    @Override
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection(); // Open the connection
            manager = new DBManager(conn); // Initialise the DBManager with the connection
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            manager.deleteUser(email); // Use the initialised manager to delete the user
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: Failed to delete user");
            request.getRequestDispatcher("deleteAccount.jsp").forward(request, response); // Forward to the JSP page with error
        }
    }

    @Override
    public void destroy() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close(); // Ensure the connection is closed when the servlet is destroyed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
