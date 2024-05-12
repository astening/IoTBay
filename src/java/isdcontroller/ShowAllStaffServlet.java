/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException; // Import SQLException
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import isdmodel.User; // Adjust import for the User class
import isdmodeldao.DBManager; // Adjust import for the DBManager class

/**
 *
 * @author William Sinclair
 */
@WebServlet("/ShowAllStaffServlet")
public class ShowAllStaffServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBManager db = (DBManager) getServletContext().getAttribute("db");
            // Fetch all staff data from the database
            ArrayList<User> users = db.fetchStaff();
            
            // Prepare HTML response
            StringBuilder html = new StringBuilder();
            html.append("<h2>All Staff Members</h2>");
            html.append("<table>");
            html.append("<tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Phone Number</th><th>Address</th><th>City</th><th>State</th><th>Postcode</th><th>Registration Date</th><th>Position</th></tr>");
            for (User user : users) {
                html.append("<tr>");
                html.append("<td>").append(user.getFname()).append("</td>");
                html.append("<td>").append(user.getLname()).append("</td>");
                html.append("<td>").append(user.getEmail()).append("</td>");
                html.append("<td>").append(user.getPhoneNo()).append("</td>");
                html.append("<td>").append(user.getAddress()).append("</td>");
                html.append("<td>").append(user.getCity()).append("</td>");
                html.append("<td>").append(user.getState()).append("</td>");
                html.append("<td>").append(user.getPostcode()).append("</td>");
                html.append("<td>").append(user.getRegistrationDate()).append("</td>");
                html.append("<td>").append(user.getPosition()).append("</td>");
                html.append("</tr>");
            }
            html.append("</table>");

            // Write HTML response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(html.toString());
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace(); // For debugging
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
