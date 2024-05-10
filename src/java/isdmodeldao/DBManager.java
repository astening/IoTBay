/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

import isdmodel.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
//import java.util.Date;

/**
 *
 * @author chari
 */
public class DBManager {

    private static Statement st;
   
    public DBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }
    
    // dates should be input as string - according to video
    
    // add the order in the database - tbc
    //  also need to check if quanity > 0 before ordering
    // would need to deduct quantity from stock - borrow from the updateOrder function :D
    // am i meant to add the userID as well
    public void addOrder(int orderID, String orderDate, String status, int noItems, double totalPrice, int userID) throws SQLException {
        String addQuery = "INSERT INTO ISDUSER.ORDERS(orderID, orderDate, status, totalNoItems, totalPrice, userID) VALUES('" + orderID + "'," + orderDate + "," + "'" + status + "',"  + "'" + noItems + "'," + "'" + totalPrice + "'," + "'" + userID + "')" ;
        st.executeUpdate(addQuery) ;
    }
    
    // find an order using orderID and orderDate - in progress
    public void findOrder(int orderID, String orderDate) throws SQLException {
        String findQuery = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=" + orderID + "AND orderDate=" + orderDate ;
        ResultSet rs = st.executeQuery(findQuery) ;
        while (rs.next()) {
            int rsOrderID = rs.getInt("orderID") ;
            String rsOrderDate = rs.getString("orderDate") ;
            if (rsOrderID==orderID && rsOrderDate.equals(orderDate)) {
                // tbc return a proper order
                String status = rs.getString("status") ;
                java.sql.Date rsOrderDateDate = rs.getDate("orderDate") ;
                double totalPrice = rs.getDouble("totalPrice") ;
                int noItems = rs.getInt("noItems") ;
                System.out.println("ID is: " + rsOrderID + ", and date is: " + rsOrderDate) ;
                // check for order object return
                // ignore for now and come back
//                return new Order(orderID, rsOrderDateDate, status, noItems, totalPrice) ; // java date and string
            }

        }
//        return null; //  apparently unnecessary
    }
    
    // update the order details - works
    public void updateOrder(int orderID, int productID, int quantity) throws SQLException {
        int productStock=0 ;
        
        String checkStock = "SELECT STOCKLVL FROM ISDUSER.PRODUCTS WHERE PRODUCTID = (SELECT productID FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")" ;
        ResultSet rs = st.executeQuery(checkStock) ;
        while(rs.next()) {
            productStock = rs.getInt("STOCKLVL") ;
        }
        rs.close() ;
        
        if ((productStock-quantity)>0 ) {
      
            // add back old value to product stock
            String restoreProduct = "UPDATE ISDUSER.PRODUCTS SET STOCKLVL=STOCKLVL+" + "(SELECT ITEMQUANTITY FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  + "WHERE PRODUCTID = (SELECT productID FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  ;
            st.executeUpdate(restoreProduct) ;
            
            // update quantity ordered in order
            String updateOrderQuantity = "UPDATE ISDUSER.ORDERS SET TOTALNOITEMS=" + quantity + "WHERE ORDERID=" + orderID ;
            st.executeUpdate(updateOrderQuantity) ;
            
            // update total price
            String updatePrice = "UPDATE ISDUSER.ORDERS SET TOTALPRICE=((SELECT UNITPRICE FROM ISDUSER.PRODUCTS WHERE PRODUCTID=" + productID + ")*" + quantity + ")" + "WHERE ORDERID=" + orderID ;
            st.executeUpdate(updatePrice) ;

            // update quantity in orderLineItem
            String updateQuery = "UPDATE ISDUSER.ORDERLINEITEM SET PRODUCTID=" + productID + ", ITEMQUANTITY=" + quantity + "WHERE ORDERID=" + orderID ;
            st.executeUpdate(updateQuery) ;
        }
        
        
    }
    
    // delete the order from the database and restore product quantity - works
    // ideally, put a for statement and loop through each orderline item ie when the customer makes a purchase for multiple products
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
