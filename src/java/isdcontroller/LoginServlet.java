/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.User;
import isdmodeldao.DBManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;


/**
 *
 * @author phoen
 */
public class LoginServlet extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Validator validator = new Validator();
        User user = null;
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        validator.clear(session);
        if (!validator.validateEmail(email)) {
            session.setAttribute("emailError", "Error: Incorrect format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            try {
                user = manager.findUser(email, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                } else {
                    session.setAttribute("existError", "- User does not exist");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? "User does not exist" : "welcome");
            }
        }
    }
}
