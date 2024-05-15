/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package uts.isd.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uts.isd.model.Users;
import java.sql.*;
import java.util.logging.*;
import java.io.IOException;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;

public class LoginController extends HttpServlet{
    
    
    public LoginController(){}
    public static final Users user = new Users();
    
    public static Users getUser() {
        return LoginController.user;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        try {
                    
            DBConnector DBC = new DBConnector();
            Connection Connection = DBC.openConnection();
            DBManager DB = new DBManager(Connection); 
            //these steps allow the controller to be able to access the database manager
            
            HttpSession session = request.getSession();
            String email = request.getParameter("username"); //gets inputted email from form
            String pass = request.getParameter("password"); //gets inputted password from form
            
            if (!(DB.hasUser(email, pass))) { //checks if a customer with given email and login exists
                //customer does not exist
                response.sendRedirect("ErrorLogin.jsp"); //loads error version of login.jsp
 
            } else { //customer with data exists
                Users nuser = DB.getUser(email, pass);
                user.setID(nuser.getID());
                user.setEmail(nuser.getEmail());
                user.setPass(nuser.getPass());
                user.setFName(nuser.getFName());
                user.setLName(nuser.getLName());
                user.setPhone(nuser.getPhone());
                user.toggleActive(true);
                
                session.setAttribute("user", user);
                
                DB.addUserLog(nuser.getID());
                
                response.sendRedirect("welcomeLogin.jsp");
            }
        
        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}