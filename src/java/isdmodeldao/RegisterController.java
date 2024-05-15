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
import java.util.ArrayList;

public class RegisterController extends HttpServlet{
    
    
    public RegisterController(){}
    private static final ArrayList<String> error = new ArrayList<String>();
    public static final Users user = new Users();
    
    public static Users getUser() {
        return RegisterController.user;
    }
    
    public static ArrayList<String> getError() {
        return RegisterController.error;
    }
    
    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( NumberFormatException e ) {
            return false;
        }
    }   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        try {
                    
            DBConnector DBC = new DBConnector();
            Connection Connection = DBC.openConnection();
            DBManager DB = new DBManager(Connection); 
            //these steps allow the controller to be able to access the database manager
            
            HttpSession session = request.getSession();
            Validator val = new Validator();
            String email = request.getParameter("email"); //gets inputted email from form
            String pass = request.getParameter("password"); //gets inputted password from form
            String number = request.getParameter("phone");
            
            if (!(val.validateEmail(email) && val.validatePassword(pass) && isInteger(number))) { //checks if the email and password inputted are valid
                error.clear();
                error.add("Username or Password are invalid please try again"); //sets error message to display
                
                response.sendRedirect("ErrorRegister.jsp"); //loads error version of register.jsp that displays custom error message
                
            } else if (DB.hasUser(email)) { //checks if a customer with the inputted email already exists in the system

                error.clear();
                error.add("Email entered has already been registered"); //sets error message to display
                
                response.sendRedirect("ErrorRegister.jsp"); //loads error version of register.jsp that displays error message
                
            } else {

                int phone = Integer.parseInt(number); //gets inputted phone number from form
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                int id = DB.getNewUserID(); //gets a unique id to assign to a new address
                
                
                DB.addUser(id, email, pass, fName, lName, phone); //adds customer into database
                DB.addUserLog(id);
                
                user.setID(id);
                user.setEmail(email);
                user.setPass(pass);
                user.setFName(fName);
                user.setFName(lName);
                user.setPhone(phone);
                user.toggleActive(true);
                
                session.setAttribute("user", user);
                
                DBC.closeConnection(); //closes the connection to the datbase
                
                response.sendRedirect("welcomeRegister.jsp");
            }
            
            
            
            
        } catch (SQLException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
