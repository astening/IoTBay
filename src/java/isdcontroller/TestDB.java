/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

/**
 *
 * @author William Sinclair
 */
import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;
import java.sql.*;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import java.util.Date;

 

public class TestDB {

private static Scanner in = new Scanner(System.in);

 

public static void main(String[] args) {

try {

DBConnector connector = new DBConnector();

Connection conn = connector.openConnection();

DBManager db = new DBManager(conn);

System.out.print("User ID: "); 
int userID = Integer.parseInt(in.nextLine());

System.out.print("User email: ");
String email = in.nextLine();

System.out.print("User First name: ");
String fname = in.nextLine();

System.out.print("User Last name: ");
String lname = in.nextLine();

System.out.print("User Phone Number: ");
int phoneNo = Integer.parseInt(in.nextLine());

System.out.print("User password: ");
String password = in.nextLine();

System.out.print("User address: ");
String address = in.nextLine();

System.out.print("User city: ");
String city = in.nextLine();

System.out.print("User state: ");
String state = in.nextLine();

System.out.print("User postcode: ");
int postcode = Integer.parseInt(in.nextLine());

System.out.print("Staff activation status (true/false): ");
boolean activation = Boolean.parseBoolean(in.nextLine());

System.out.print("User Registration Date: ");
String dateString = in.nextLine();

System.out.print("Staff Role Number: ");
int roleID = Integer.parseInt(in.nextLine());

//Convert String to Date
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
try {
    Date registrationDate = formatter.parse(dateString);
    // Now you can use the date variable in your method
    System.out.println("Date object: " + registrationDate);
    
    db.addStaff(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, roleID);
    
} catch (ParseException e) {
    System.out.println("Incorrect format. Please enter date in YYYY-MM-DD format.");
}


System.out.println("User is added to the database.");

connector.closeConnection();

 

} catch (ClassNotFoundException | SQLException ex) {

Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

}

}

}
