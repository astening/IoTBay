/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

import isdmodel.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author phoen
 */
public class DBManager {

private Statement st;
private Connection conn;
   
public DBManager(Connection conn) throws SQLException {  
   this.conn = conn;
   st = conn.createStatement();   
}


public User findUser(String email, String password) throws SQLException {      
   ResultSet fetch = st.executeQuery("select * from Users2 where EMAIL = '" + email + "' and PASSWORD = '" + password + "'");
   
   while (fetch.next()) {
       String userEmail = fetch.getString(5);
       String userPass = fetch.getString(6);
       if (userEmail.equals(email) && userPass.equals(password)) {
           int userID = fetch.getInt(1);
           int phoneNo = fetch.getInt(4);
           String fname = fetch.getString(2);
           String lname = fetch.getString(3);
           String address = fetch.getString(7);
           String city = fetch.getString(8);
           String state = fetch.getString(9);
           int postcode = fetch.getInt(10);
           boolean activation = fetch.getBoolean(11);
           Date registrationDate = fetch.getDate(12);
           String position = fetch.getString(13);
           return new User(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
       }
   }
   return null;   
}

public User findCustomer(String fname, String lname, String position) throws SQLException {       
   ResultSet fetch = st.executeQuery("select * from Users2 where FNAME = '" + fname + "' and LNAME = '" + lname +"' and POSITION = '" + position +"'");
   while (fetch.next()) {
       String customerFname = fetch.getString(2);
       String customerLname = fetch.getString(3);
       String customerPosition = fetch.getString(13);
       if (customerFname.equals(fname) && customerLname.equals(lname)) {
           int userID = fetch.getInt(1);
           int phoneNo = fetch.getInt(4);
           String email = fetch.getString(5);
           String password = fetch.getString(6);
           String address = fetch.getString(7);
           String city = fetch.getString(8);
           String state = fetch.getString(9);
           int postcode = fetch.getInt(10);
           boolean activation = fetch.getBoolean(11);
           Date registrationDate = fetch.getDate(12);
           return new User(userID, customerFname, customerLname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, customerPosition);
       }
   }
   return null;   
}

  
public void addUser(String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, String position) throws SQLException {
    try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users2 (FName, LName, PhoneNo, Email, Password, Address, City, State, Postcode, Activation, RegistrationDate, Position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
        stmt.setString(1, fname);
        stmt.setString(2, lname);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, true);
        stmt.setDate(11, new java.sql.Date(new Date().getTime())); 
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}

public void addCustomer(String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, String position) throws SQLException {
    try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users2 (fName, lName, PhoneNo, Email, Password, Address, City, State, Postcode, Activation, RegistrationDate, Position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
        stmt.setString(1, fname);
        stmt.setString(2, lname);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, true);
        stmt.setDate(11, new java.sql.Date(new Date().getTime())); 
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}
   
public void updateCustomer(int userID, String fName, String lName, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, String position) throws SQLException {
    try (PreparedStatement stmt = conn.prepareStatement("UPDATE Users2 SET fName=?, lName=?, phoneNo=?, email=?, password=?, address=?, city=?, state=?, postcode=?, activation=?, position=? WHERE userID=?")) {
        stmt.setString(1, fName);
        stmt.setString(2, lName);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, activation);
        stmt.setString(11, position);
        stmt.setInt(12, userID);
        
        stmt.executeUpdate();
    }
}   

   
public void deleteCustomer(int userID) throws SQLException {
    String query = "DELETE FROM Users2 WHERE userID = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userID);
        stmt.executeUpdate();
    }
}

public ArrayList<User> fetchCustomerList() throws SQLException{
    ArrayList<User> customerList = new ArrayList();
    ResultSet fetch = st.executeQuery("SELECT * FROM USERS2 WHERE position IN ('Individual', 'Company')");
    while (fetch.next()) {
        int userID = fetch.getInt(1);
        String fname = fetch.getString(2);
        String lname = fetch.getString(3);
        Integer phoneNum = fetch.getInt(4);
        String email = fetch.getString(5);
        String password = fetch.getString(6);
        String address = fetch.getString(7);
        String city = fetch.getString(8);
        String state = fetch.getString(9);
        Integer postcode = fetch.getInt(10);
        Boolean activation = fetch.getBoolean(11);
        Date registrationDate = fetch.getDate(12);
        String position = fetch.getString(13);
        customerList.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, position));
    } 
    return customerList;
}
 

public void changeCustomerStatus(int userID, boolean activation) throws SQLException {
    String query = "UPDATE Users2 SET activation=? WHERE userID=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setBoolean(1, activation);
        stmt.setInt(2, userID);
        
        stmt.executeUpdate();
    }
} 

public ArrayList<User> findCustomerByType(String position) throws SQLException {       
  ArrayList<User> customerTypeList = new ArrayList();
        ResultSet fetch = st.executeQuery("select * from Users2 where POSITION = '" + position +"'");
        while (fetch.next()) {
            int userID = fetch.getInt(1);
            String fname = fetch.getString(2);
            String lname = fetch.getString(3);
            Integer phoneNum = fetch.getInt(4);
            String email = fetch.getString(5);
            String password = fetch.getString(6);
            String address = fetch.getString(7);
            String city = fetch.getString(8);
            String state = fetch.getString(9);
            Integer postcode = fetch.getInt(10);
            Boolean activation = fetch.getBoolean(11);
            Date registrationDate = fetch.getDate(12);
            customerTypeList.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, position));
        } 
        return customerTypeList;
}

public ArrayList<User> findCustomerByFName(String fname) throws SQLException {       
  ArrayList<User> customerTypeList = new ArrayList();
        ResultSet fetch = st.executeQuery("select * from Users2 where fName = '" + fname +"' AND position IN ('Individual', 'Company')");
        while (fetch.next()) {
            int userID = fetch.getInt(1);
            String lname = fetch.getString(3);
            Integer phoneNum = fetch.getInt(4);
            String email = fetch.getString(5);
            String password = fetch.getString(6);
            String address = fetch.getString(7);
            String city = fetch.getString(8);
            String state = fetch.getString(9);
            Integer postcode = fetch.getInt(10);
            Boolean activation = fetch.getBoolean(11);
            Date registrationDate = fetch.getDate(12);
            String position = fetch.getString(13);
            customerTypeList.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, position));
        } 
        return customerTypeList;
}



}
