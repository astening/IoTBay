package isdmodeldao;

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
public User findUser(String email, String password) throws SQLException {       
   //setup the select sql query string
   String fetch = "select * from ISDUSER.USERS where EMAIL='"+email+"' and PASSWORD='"+password+"'";
   //execute this query using the statement field
   ResultSet rs = st.executeQuery(fetch);
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters   
   while(rs.next()){
       String userEmail=rs.getString(1);
       String userPass=rs.getString(3);
       if(userEmail.equals(email) && userPass.equals(password)){
           String userName=rs.getString(2);
           return new User(userName,userEmail, userPass);
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


 

}