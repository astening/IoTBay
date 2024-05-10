/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

/**
 *
 * @author William Sinclair
 */
import isdmodel.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

private Statement st;
private Connection conn;
   
public DBManager(Connection conn) throws SQLException {  
   this.conn = conn;
   st = conn.createStatement();   
}

//Find user by email and password in the database   
public User findStaff(String fname, String lname, String position) throws SQLException {       
   //setup the select sql query string       
   //execute this query using the statement field       
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters  
   String fetch = "select * from Users where FNAME = '" + fname + "' and LNAME = '" + lname +"' and POSITION = '" + position +"'";
   ResultSet rs = st.executeQuery(fetch);
   
   while (rs.next()) {
       String staffFname = rs.getString(2);
       String staffLname = rs.getString(3);
       String staffPosition = rs.getString(13);
       if (staffFname.equals(fname) && staffLname.equals(lname)) {
           int userID = rs.getInt(1);
           int phoneNo = rs.getInt(4);
           String email = rs.getString(5);
           String password = rs.getString(6);
           String address = rs.getString(7);
           String city = rs.getString(8);
           String state = rs.getString(9);
           int postcode = rs.getInt(10);
           boolean activation = rs.getBoolean(11);
           Date registrationDate = rs.getDate(12);
           return new User(userID, staffFname, staffLname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, staffPosition);
       }
   }
   return null;   
}

//Add a user-data into the database   
public void addStaff(String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, String position) throws SQLException {
    String query = "INSERT INTO Users (FName, LName, PhoneNo, Email, Password, Address, City, State, Postcode, Activation, RegistrationDate, Position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, fname);
        stmt.setString(2, lname);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, activation);
        stmt.setDate(11, new java.sql.Date(registrationDate.getTime())); // Convert java.util.Date to java.sql.Date
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}

//update a user details in the database   
public void updateStaff(int userID, String fName, String lName, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, String position) throws SQLException {       
   //code for update-operation   
   st.executeUpdate("UPDATE Users SET Fname='" + fName + "', Lname='" + lName + "' ,PhoneNo='" + phoneNo + "' ,Password='" + password + "' ,Address='" + address + "' ,City='" + city + "' ,State='" + state + "' ,Postcode='" + postcode + "' ,Activation='" + activation + "' ,Position='" + position + "' WHERE UserID='" + userID + "'");

}       

//delete a user from the database   
public void deleteStaff(int userID) throws SQLException{       
   //code for delete-operation   
   st.executeUpdate("DELETE FROM Users WHERE USERID='" + userID + "'");
}

public ArrayList<User> fetchStaff() throws SQLException{
    String fetch = "select * from USERS";
    ResultSet rs = st.executeQuery(fetch);
    ArrayList<User> temp = new ArrayList();
    while (rs.next()) {
        int userID = rs.getInt(1);
        String email = rs.getString(5);
        String fname = rs.getString(2);
        String lname = rs.getString(3);
        String password = rs.getString(6);
        int phoneNum = rs.getInt(4);
        String address = rs.getString(7);
        String city = rs.getString(8);
        String state = rs.getString(9);
        int postcode = rs.getInt(10);
        boolean activation = rs.getBoolean(11);
        Date registrationDate = rs.getDate(12);
        String position = rs.getString(13);
        temp.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, position));
    } 
    return temp;
}
 
public boolean checkStaff(int userID) throws SQLException {
    String fetch = "select * from Users where UserID = '" + userID + "'";
    ResultSet rs = st.executeQuery(fetch);
    
    while (rs.next()) {
        int staffID = rs.getInt(1);
     if (staffID == userID){
         return true;
     }
    }
    return false;
}

}
