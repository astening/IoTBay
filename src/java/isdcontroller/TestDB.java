/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

/**
 *
 * @author William Sinclair
 */
import isdmodel.User;
import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class TestDB {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;

    public static void main(String[] args) throws SQLException{
            (new TestDB()).runQueries();
    }
    
    public TestDB(){
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
            } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private char readChoice() {
        System.out.print("Operation CRUDS or * to exit:");
        return in.nextLine().charAt(0);
    }
    
    private void runQueries() throws SQLException{
        char c; 
        
        while ((c = readChoice()) != '*'){
            switch (c) {
                case 'C':
                testAddStaff();
                break;
                case 'R':
                testFindStaff();
                break; 
                case 'U': 
                testUpdateStaff();
                break; 
                case 'D':
                testDeleteStaff();
                break;
                case 'S':
                testShowAllStaff();
                break;
                default: 
                System.out.println("Unknown Command");
                break;
        }
      }
    }
    
    private void testAddStaff(){
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

            System.out.print("Staff Position: ");
            String position = in.nextLine();

            // Generate the current registration date
            Date registrationDate = new Date();
            
            try{
            db.addStaff(fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
            System.out.println("User is added to the database.");
            } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    private void testFindStaff() throws SQLException{
        
        System.out.print("Staff First Name: ");
        String fname = in.nextLine();
        System.out.print("Student Last Name: ");
        String lname = in.nextLine();
        System.out.print("Staff Position: ");
        String position = in.nextLine();
        User user = db.findStaff(fname, lname, position);
        if (user != null) {
            System.out.print("Staff member " + user.getFname() + " " +  user.getLname() + " exists in the database.");
        } else {
            System.out.print("Staff member does not exist");
        }
        
    }
    
    private void testUpdateStaff() {
            System.out.print("Staff ID: ");
            int userID = Integer.parseInt(in.nextLine());
            try{ 
                if(db.checkStaff(userID)){
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
                    System.out.print("Staff Position: ");
                    String position = in.nextLine();
                    db.updateStaff(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, position);
                } else {
                    System.out.println("Staff Member does not exist.");
                }
           } catch (SQLException ex){
               Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    private void testDeleteStaff() {
        System.out.print("Staff ID: ");
        int userID = Integer.parseInt(in.nextLine());
        
        try {
            if (db.checkStaff(userID)){
                db.deleteStaff(userID);
            } else {
                System.out.println("Staff Member does not exist.");
            }
        } catch (SQLException ex){
               Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void testShowAllStaff() {
        try {
            ArrayList<User> users = db.fetchStaff();
            System.out.println("STAFF TABLE: ");
            users.stream().forEach((user) -> {
                System.out.printf("%-20s %-30s %-20s %-10s \n", user.getFname(), user.getLname(), user.getEmail(), user.getPassword(), user.getPhoneNo(), user.getAddress(), user.getCity(), user.getState(), user.getPostcode(), user.getRegistrationDate(), user.getPosition());
            });
            System.out.println();
        } catch (SQLException ex){
               Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    }

