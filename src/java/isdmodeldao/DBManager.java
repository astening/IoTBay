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
   
public DBManager(Connection conn) throws SQLException {       
   st = conn.createStatement();   
}

//Find user by email and password in the database   
public User findStaff(String email, String password) throws SQLException {       
   //setup the select sql query string       
   //execute this query using the statement field       
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters  
   String fetch = "select * from IOTDB.User where EMAIL = '" + email + "' and PASSWORD = '" + password +"'";
   ResultSet rs = st.executeQuery(fetch);
   
   while (rs.next()) {
       String staffEmail = rs.getString(5);
       String staffPassword = rs.getString(6);
       if (staffEmail.equals(email) && staffPassword.equals(password)) {
           int userID = rs.getInt(1);
           String fName = rs.getString(2);
           String lName = rs.getString(3);
           int phoneNo = rs.getInt(4);
           String address = rs.getString(7);
           String city = rs.getString(8);
           String state = rs.getString(9);
           int postcode = rs.getInt(10);
           boolean activation = rs.getBoolean(11);
           Date registrationDate = rs.getDate(12);
           int roleID = rs.getInt(13);
           return new User(userID, fName, lName, phoneNo, staffEmail, staffPassword, address, city, state, postcode, activation, registrationDate, roleID);
       }
   }
   return null;   
}

//Add a user-data into the database   
public void addStaff(int userID, String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, int roleID) throws SQLException {                   //code for add-operation       
  st.executeUpdate("INSERT INTO IOTDB.User " + "VALUES ('" + userID + "','" + fname + "', '" + lname + "', '" + phoneNo + "', '" + email + "', '" + password + "','" + address + "', '" + city + "', '" + state + "', '" + postcode + "', '" + activation + "', '" + registrationDate + "', '" + roleID);   

}

//update a user details in the database   
public void updateStaff( String email, String name, String password) throws SQLException {       
   //code for update-operation   
   st.executeUpdate("UPDATE IOTDB.User SET NAME='" + name + "',PASSWORD='" + password);

}       

//delete a user from the database   
public void deleteStaff(String email) throws SQLException{       
   //code for delete-operation   
   st.executeUpdate("DELETE FROM IOTDB.User WHERE EMAIL='" + email + "'");
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
        int roleId = rs.getInt(13);
        temp.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, roleId));
    } 
    return temp;
}
 

}
