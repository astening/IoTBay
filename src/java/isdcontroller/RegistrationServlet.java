/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/iotdb";
    private static final String JDBC_USER = "isduser";
    private static final String JDBC_PASSWORD = "admin";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capture the registration data from the form
        int userID = Integer.parseInt(request.getParameter("userID"));
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        int phoneNo = Integer.parseInt(request.getParameter("phoneNo"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int postcode = Integer.parseInt(request.getParameter("postcode"));
        boolean activation = Boolean.parseBoolean(request.getParameter("activation"));
        String position = request.getParameter("position");
        // Capture the current date
        LocalDate currentDate = LocalDate.now();
        
        
        // Store registration data in the database
        storeRegistrationData(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, currentDate, position);

        // Forward the request to a confirmation page or display a success message
        response.sendRedirect("registration_confirmation.jsp");
    }


    private void storeRegistrationData(int userID, String fname, String lname, Integer phoneNo, String email, String password, String address, String city, String state, Integer postcode, Boolean activation, LocalDate currentDate, String position) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Prepare the INSERT statement
            String sql = "INSERT INTO users (userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set the parameters
                statement.setInt(1, userID);
                statement.setString(2, fname);
                statement.setString(3, lname);
                statement.setInt(4, phoneNo);
                statement.setString(5, email);
                statement.setString(6, password);
                statement.setString(7, address);
                statement.setString(8, city);
                statement.setString(9, state);
                statement.setInt(10, postcode);
                statement.setBoolean(11, true);
                statement.setDate(12, java.sql.Date.valueOf(currentDate));
                statement.setString(13, position);
                // Execute the query
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
