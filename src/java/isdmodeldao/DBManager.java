package isdmodeldao;

import isdmodel.PaymentMethod;
import isdmodel.User;
import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;

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
public User findUser(String email, String password) throws SQLException {       
   //setup the select sql query string
   String fetch = "select * from ISDUSER.USERS where EMAIL='"+email+"' and PASSWORD='"+password+"'";
   //execute this query using the statement field
   ResultSet rs = st.executeQuery(fetch);
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters   
   while(rs.next()){
       String userEmail=rs.getString(5);
       String userPass=rs.getString(6);
       if(userEmail.equals(email) && userPass.equals(password)){
           String userName=rs.getString(2);
           int userID = rs.getInt(1);
           return new User(userID, userName,userEmail, userPass);
       }
   }
   return null;   
}

//Add a user-data into the database   
public void addUser(String email, String name, String password, String gender, String favcol) throws SQLException {                   //code for add-operation       
  st.executeUpdate("INSERT INTO ISDUSER.USERS " + "VALUES ('"+email +"', '" + name + "', '" + password + "', '" +gender+ "', '" + favcol + "')" );   

}

//update a user details in the database   
public void updateUser( String email, String name, String password, String gender, String favcol) throws SQLException {       
   //code for update-operation   
   st.executeUpdate("UPDATE ISDUSER.USERS SET EMAIL='" + email + "', NAME='"+ name + "', PASSWORD='"+password+"', GENDER='"+gender+"', FAVCOL='"+favcol+"'" );
}       

//delete a user from the database   
public void deleteUser(String email) throws SQLException{       
   //code for delete-operation   
   st.executeUpdate("DELETE FROM ISDUSER.USERS WHERE EMAIl='"+email+"'");   
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
    
    public void deletePaymenetMethod (int paymentMethodID) throws SQLException{
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


 

}