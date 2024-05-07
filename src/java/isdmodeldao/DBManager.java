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
       String staffEmail = rs.getString(2);
       String staffPassword = rs.getString(3);
       if (staffEmail.equals(email) && staffPassword.equals(password)) {
           String staffName = rs.getString(1);
           return new User(staffName, staffEmail, staffPassword);
       }
   }
   return null;   
}

//Add a user-data into the database   
public void addStaff(String email, String fname, String lname, String password, Integer phoneNum, String state, Boolean activation, Integer roleId) throws SQLException {                   //code for add-operation       
  st.executeUpdate("INSERT INTO IOTDB.User " + "VALUES ('" + fname + "', '" + lname + "', '" + phoneNum + "', '" + email + "', '" + password + "', '" + state + "', '" + activation + "', '" + roleId);   

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


 

}
