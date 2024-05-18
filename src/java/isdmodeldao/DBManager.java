/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

/**
 *
 * @author William Sinclair
 */
import isdmodel.Payment;
import isdmodel.PaymentMethod
import isdmodel.User;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
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

//Find staff based on name and position
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

//Add a new staff-data into the database   
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
        stmt.setBoolean(10, true);
        stmt.setDate(11, new java.sql.Date(new Date().getTime())); // Using current date
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}

//updating existing staff details in the database   
public void updateStaff(int userID, String fName, String lName, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, String position) throws SQLException {
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

//delete a user from the database   
public void deleteStaff(int userID) throws SQLException {
    String query = "DELETE FROM Users WHERE userID=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userID);
        stmt.executeUpdate();
    }
}

//fetch all staff members from the db
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
        Integer phoneNum = rs.getInt(4);
        String address = rs.getString(7);
        String city = rs.getString(8);
        String state = rs.getString(9);
        Integer postcode = rs.getInt(10);
        Boolean activation = rs.getBoolean(11);
        Date registrationDate = rs.getDate(12);
        String position = rs.getString(13);
        temp.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, position));
    } 
    return temp;
}

//check that staff member exists in the DB
public boolean checkStaff(int userID) throws SQLException {
   String fetch = "SELECT * FROM Users WHERE UserID = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(fetch)) {
        stmt.setInt(1, userID);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int staffID = rs.getInt(1);
            if (staffID == userID) {
                return true;
            }
        }
    }
    return false;
}
  
    //find paymentMethod by userid
    public PaymentMethod findPaymentMethod (int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PaymentMethod where USERID = " + userID + " ";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()){
            int uID = rs.getInt(2);
            if(userID == uID){
                int paymentMethodID = rs.getInt(1);
                String cardName  = rs.getString(3);
                String cardNo = rs.getString(4);
                Date expiry = rs.getDate(5);
                LocalDate expiryDate = expiry.toLocalDate();
                int cvv = rs.getInt(6);
                return new PaymentMethod(paymentMethodID, userID, cardName, cardNo, cvv, expiryDate);
            }
        }
        return null;
    }
    //add new paymentMethod and attatch it to userID
    public void addPaymentMethod (int userID, String cardName, String cardNo, int cvv, LocalDate expiryDate) throws SQLException{
        st.executeUpdate("INSERT INTO ISDUSER.PaymentMethod " + "(USERID, CARDNAME, CARDNO, EXPIRYDATE, CVV)" +
                "VALUES (" + userID + ", '" + cardName + "', '" + cardNo + "', '" + expiryDate + "'," + cvv + ")");
    }
    //update paymentMethod
    public void updatePaymentMethod (int userID, String cardName, String cardNo, int cvv, LocalDate expiry) throws SQLException{
        Date expiryDate = Date.valueOf(expiry);
        st.executeUpdate("UPDATE ISDUSER.PaymentMethod SET CARDNAME='" + cardName + "',CARDNO='" + cardNo + "',EXPIRYDATE='" + expiryDate + "',CVV=" + cvv + "WHERE USERID=" + userID + "");
    }
    
    public void deletePaymentMethod (int paymentMethodID) throws SQLException{
        st.executeUpdate("DELETE FROM ISDUSER.PaymentMethod WHERE PAYMENTMETHODID=" + paymentMethodID + "");
    }
    
    public boolean checkPaymentMethod (int userID) throws SQLException {
        String fetch = "select * from ISDUSER.PAYMENTMETHOD where USERID = " + userID + "";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()){
            int uid = rs.getInt(2);
            if(uid == userID){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Payment> getPayments(int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PAYMENT where USERID = " + userID + "";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Payment> payments = new ArrayList<Payment>();
        
        while(rs.next()){
            int uID = rs.getInt(2);
            if(uID==userID){
                int paymentID = rs.getInt(1);
                int orderID = rs.getInt(3);
                Date paymentD = rs.getDate(4);
                LocalDate paymentDate = paymentD.toLocalDate();
                double paymentAmount = rs.getDouble(5);
                payments.add(new Payment(paymentID, orderID, paymentDate, paymentAmount));
            }
        }
        if(payments.isEmpty()){
            return null;
        }
        
        return payments;
    }
    
    public boolean checkPayments(int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PAYMENT where USERID = " + userID + "";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()){
            int uid = rs.getInt(2);
            if((uid == userID)){
                return true;
            }
        }
        return false;
    }

}
