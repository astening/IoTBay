/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/iotdb";
    private static final String JDBC_USER = "isduser";
    private static final String JDBC_PASSWORD = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capture the registration data from the form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        // Capture the current date
        LocalDate currentDate = LocalDate.now();
        
        // Generate a unique user ID
        String userID = generateUserID();

        // Store registration data in the database
        storeRegistrationData(username, email, userID, currentDate);

        // Forward the request to a confirmation page or display a success message
        response.sendRedirect("registration_confirmation.jsp");
    }

    private String generateUserID() {
        // Generate a unique user ID using UUID
        return UUID.randomUUID().toString();
    }

    private void storeRegistrationData(String username, String email, String userID, LocalDate currentDate) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Prepare the INSERT statement
            String sql = "INSERT INTO users (user_id, username, email, registration_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set the parameters
                statement.setString(1, userID);
                statement.setString(2, username);
                statement.setString(3, email);
                statement.setDate(4, java.sql.Date.valueOf(currentDate));

                // Execute the query
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
