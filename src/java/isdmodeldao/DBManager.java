/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uts.isd.model.dao;

/**
 *
 * @author aneir
 */
import uts.isd.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import uts.isd.model.AccessLog;

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
public User findUser(String email, String password) throws SQLException {       
   //setup the select sql query string       
   //execute this query using the statement field       
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters  
   String fetch = "select * from Users where EMAIL = '" + email + "' and PASSWORD = '" + password + "'";
   ResultSet rs = st.executeQuery(fetch);
   
   while (rs.next()) {
       String userEmail = rs.getString(5);
       String userPass = rs.getString(6);
       if (userEmail.equals(email) && userPass.equals(password)) {
           int userID = rs.getInt(1);
           int phoneNo = rs.getInt(4);
           String fname = rs.getString(2);
           String lname = rs.getString(3);
           String address = rs.getString(7);
           String city = rs.getString(8);
           String state = rs.getString(9);
           int postcode = rs.getInt(10);
           boolean activation = rs.getBoolean(11);
           Date registrationDate = rs.getDate(12);
           String position = rs.getString(13);
           return new User(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
       }
   }
   return null;   
}


//Add a user-data into the database   
public void addUser(String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, String position) throws SQLException {
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
        stmt.setBoolean(10, true);
        stmt.setDate(11, new java.sql.Date(new Date().getTime())); // Using current date
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}


public void updateUser(int userID, String fName, String lName, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, String position) throws SQLException {
    String query = "UPDATE Users SET fName=?, lName=?, phoneNo=?, email=?, password=?, address=?, city=?, state=?, postcode=?, activation=?, position=? WHERE userID=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
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

public void deleteUser(String email) throws SQLException {
    String query = "DELETE * FROM Users WHERE email=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email);
        stmt.executeUpdate();
    }
}

// Method to fetch access logs for a specific user
public ArrayList<AccessLog> getUserAccessLogs(int userID) throws SQLException {
        String query = "SELECT * FROM AccessLog WHERE userID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userID);
        ResultSet rs = stmt.executeQuery();

        ArrayList<AccessLog> logs = new ArrayList<>();
        while (rs.next()) {
            AccessLog log = new AccessLog(
                rs.getInt("logID"),
                rs.getInt("userID"),
                rs.getTimestamp("loginTimestamp"),
                rs.getTimestamp("logoutTimestamp"),
                rs.getString("logDetails")
            );
            logs.add(log);
        }
        return logs;
    }

public ArrayList<AccessLog> getUserAccessLogsByDate(int userID, Date date) throws SQLException {
    String query = "SELECT * FROM AccessLog WHERE userID = ? AND CAST(loginDateTime AS DATE) = ?";
    ArrayList<AccessLog> logs = new ArrayList<>();

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userID);
        stmt.setDate(2, new java.sql.Date(date.getTime()));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int logID = rs.getInt("logID");
            Date loginDateTime = rs.getTimestamp("loginDateTime");
            Date logoutDateTime = rs.getTimestamp("logoutDateTime");
            String logDetails = rs.getString("logDetails");
            logs.add(new AccessLog(logID, userID, loginDateTime, logoutDateTime, logDetails));
        }
    }

    return logs;
}

public void addAccessLog(int userID, String logDetails, Timestamp logoutTimestamp) throws SQLException {
    String query = "INSERT INTO AccessLog (userID, loginTimestamp, logoutTimestamp, logDetails) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userID);
        stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
        stmt.setTimestamp(3, logoutTimestamp);
        stmt.setString(4, logDetails);
        stmt.executeUpdate();
    }
}

public void updateLogoutTime(int userID) throws SQLException {
    String query = "UPDATE AccessLog SET logoutTimestamp = ? WHERE userID = ? AND logoutTimestamp IS NULL";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
        stmt.setInt(2, userID);
        stmt.executeUpdate();
    }
}

public void addUserLogout(int userID) throws SQLException {
    ResultSet result = st.executeQuery("SELECT max(login) from USERS WHERE userID="+userID); 
    
    if(result.next()) {
        Timestamp max = result.getTimestamp(1);
    
        String query = "UPDATE AcessLogs SET logoutTimestamp= CURRENT_TIMESTAMP WHERE userID="+userID+ " AND login='" +max+"'"; 
        st.executeUpdate(query);
    }
}

public ArrayList<AccessLog> searchUserAccessLogs(int userID, Date startDate, Date endDate) throws SQLException {
        String query = "SELECT * FROM AccessLog WHERE userID = ? AND loginTimestamp BETWEEN ? AND ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userID);
        stmt.setTimestamp(2, new Timestamp(startDate.getTime()));
        stmt.setTimestamp(3, new Timestamp(endDate.getTime()));
        ResultSet rs = stmt.executeQuery();

        ArrayList<AccessLog> logs = new ArrayList<>();
        while (rs.next()) {
            AccessLog log = new AccessLog(
                rs.getInt("logID"),
                rs.getInt("userID"),
                rs.getTimestamp("loginTimestamp"),
                rs.getTimestamp("logoutTimestamp"),
                rs.getString("logDetails")
            );
            logs.add(log);
        }
        return logs;
    }
}
