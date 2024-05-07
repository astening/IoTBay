/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

import isdmodel.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author chari
 */
public class DBManager {

    private static Statement st;
   
    public DBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }
    
    // add the order in the database - tbc
    //  also need to check if quanity > 0 before ordering
    public void addOrder(int orderID, String orderDate, String status, int noItems, double totalPrice, int userID) throws SQLException { //change from string to date
        String addQuery = "INSERT INTO ISDUSER.ORDERS(orderID, orderDate, status, totalNoItems, totalPrice, userID) VALUES('" + orderID + "'," + "'" + orderDate + "'," + "'" + status + "',"  + "'" + noItems + "'," + "'" + totalPrice + "'," + "'" + userID + "')" ;
        st.executeUpdate(addQuery) ;
    }
    
    // find an order using orderID and orderDate - tbc
    public void findOrder(int orderID, String orderDate) throws SQLException { // string or date
        String findQuery = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=" + orderID + "AND orderDate = " + orderDate ;
        ResultSet rs = st.executeQuery(findQuery) ;
        while (rs.next()) {
            String rsOrderID = rs.getString(1) ;
            String rsOrderDate = rs.getString(2) ;
            if (rsOrderID.equals(orderID) && rsOrderDate.equals(orderDate)) {
//                return new Order(orderID, null, null, null, null) ; //WIP
            }
        }
        
//        return null; //  apparently unnecessary
    }
    
    // update the stored order tbc
    //  also need to check if quantity > 0 before ordering
    // need to change the orderlineitem quantity and total quantity
    public void updateOrder(int orderID, int productID, int quantity) throws SQLException {
        String updateQuery = "UPDATE ISDUSER.ORDERLINEITEM SET PRODUCTID=" + productID + ", ITEMQUANTITY=" + quantity + "WHERE ORDERID=" + orderID ; // orders or orderlineitem
        st.executeUpdate(updateQuery) ;
    }
    
    // delete the order from the database and restore product quantity - works
    public void deleteOrder(int orderID) throws SQLException {
        // set status to cancelled
        String changeStatus = "UPDATE ISDUSER.ORDERS SET STATUS='Cancelled' WHERE ORDERID=" + orderID ;
        st.executeUpdate(changeStatus) ;
        
        // add ordered quantity back to the product table
        String restoreProduct = "UPDATE ISDUSER.PRODUCTS SET STOCKLVL=STOCKLVL+" + "(SELECT ITEMQUANTITY FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  + "WHERE PRODUCTID = (SELECT productID FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  ;
        st.executeUpdate(restoreProduct) ;
        
    }
    
    //update order status in the database - works  
    public void updateOrderStatus( int orderID, String status) throws SQLException {       
    //code for update-operation
    String updateQuery = "UPDATE ISDUSER.ORDERS SET STATUS='" + status +  "' WHERE ORDERID=" + orderID ;
    st.executeUpdate(updateQuery) ;
    
    }
    
    
    
}

// for reference, these are the inputs for the table
//    orderID INT NOT NULL,
//    orderDate DATE,
//    status VARCHAR(10),
//    totalNoItems INT,
//    totalPrice DOUBLE,
//    userID INT,

// and this is from the model
//    private int orderID;
//    private Date orderDate;
//    private String status;
//    private int noItems;
//    private double totalPrice;
