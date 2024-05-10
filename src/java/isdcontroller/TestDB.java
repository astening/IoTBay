/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chari
 */

import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;
//import java.time.LocalDate;
//import java.util.Date;

//import isdmodel.Order ;

public class TestDB {

private static Scanner in = new Scanner(System.in);

 

public static void main(String[] args) {

try {

DBConnector connector = new DBConnector();

Connection conn = connector.openConnection();

DBManager db = new DBManager(conn);

System.out.println("Order changes") ;

System.out.print("Order status: ");

String status = in.nextLine();

//System.out.print("Order date: ");

//String orderDate = in.nextLine(); //convert to date
String stringDate = "2024-05-06" ;

//System.out.print("No of items: ");

//String totalNoItems = in.nextLine(); //convert to int

//System.out.print("Total price: ");
//
//double totalPrice = (double) in.nextInt(); //convert to double

System.out.print("Order ID: ");

int orderID = (int) in.nextInt();

//System.out.print("Product ID: ");
//
//int productID = in.nextInt();
//
//System.out.print("Quantity: ");
//
//int quantity = in.nextInt();

// update status
//db.updateOrderStatus(orderID, status) ;

//System.out.println("Status is updated in the database.") ;

// add order
//db.addOrder(orderID, stringDate , status, quantity, totalPrice, orderID); // change 2 to orderDate, 4 to noItems, 6 to userID
db.addOrder(orderID, "2024-05-09" , status, orderID, orderID, orderID);
//addOrder(int orderID, Date orderDate, String status, int noItems, double totalPrice, int userID)
//System.out.println("Order added to the database") ;

// find order
//db.findOrder(orderID, "2024-04-01") ;

// delete order
//db.deleteOrder(orderID) ;

// update order
//db.updateOrder(orderID, productID, quantity) ;

connector.closeConnection();



} catch (ClassNotFoundException | SQLException ex) {

Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

}

}

}

