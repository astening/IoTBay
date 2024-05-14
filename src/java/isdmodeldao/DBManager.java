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

/**
 *
 * @author chari
 */
public class DBManager {

    private static Statement st;
   
    public DBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }
        
    // add the order in the database - few changes to be made
    // change input so you dont need the total price and status and useriD and product iD --> manually insert these with defaults in statement
    public void addOrder(int orderID, String orderDate, String status, int noItems, int totalPrice, int userID, int productID) throws SQLException { // change this and add product ID

        int productStock=0 ;
        
        // i would need to get the product from the user and then add into orderLineItem
        // and then I need to do the quantity checks etc
        String checkStock = "SELECT STOCKLVL FROM ISDUSER.PRODUCTS WHERE PRODUCTID =" + productID ;
        ResultSet rs = st.executeQuery(checkStock) ;
        while(rs.next()) {
            productStock = rs.getInt("STOCKLVL") ;
        }
        rs.close() ;
        
        if ((productStock-noItems)>=0 ) {

            // add the order details
            String addQuery = "INSERT INTO ISDUSER.ORDERS(orderID, orderDate, status, totalNoItems, totalPrice, userID) VALUES(" + orderID + ", '" + orderDate + "', '" + status + "',"  + noItems + "," + totalPrice + "," + userID + ")" ;
            st.executeUpdate(addQuery) ;
            
            // add into orderLine table
            String addOrderLine = "INSERT INTO ISDUSER.ORDERLINEITEM(itemQuantity, orderID, productID) VALUES(" + noItems + ", " + orderID + ", " + productID + ")" ;
            st.executeUpdate(addOrderLine) ;
            
            // deduct producct from the product table
            String deductProduct = "UPDATE ISDUSER.PRODUCTS SET STOCKLVL=STOCKLVL-" + noItems  + "WHERE PRODUCTID =" + productID ;
            st.executeUpdate(deductProduct) ;

        }
  
    }
    
    // find an order using orderID and orderDate - need to return the order itself
    // use a list here to return the stuff
    public void findOrder(int orderID, String orderDate) throws SQLException {
        String findQuery = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=" + orderID + "AND orderDate='" + orderDate + "'" ;
        ResultSet rs = st.executeQuery(findQuery) ;
        while (rs.next()) {
            int rsOrderID = (int) rs.getInt("orderID") ;
            String rsOrderDate = (String) rs.getString("orderDate") ;
            if (rsOrderID==orderID && rsOrderDate.equals(orderDate)) {
                // tbc return a proper order
                String status = rs.getString("status") ;
                double totalPrice = rs.getDouble("totalPrice") ;
                int totalNoItems = rs.getInt("totalNoItems") ;
                System.out.println("ID is: " + rsOrderID + ", and date is: " + rsOrderDate) ;
                // check for order object return
                // ignore for now and come back
//                return new Order(orderID, orderDate, status, noItems, totalPrice) ; // string not working
            }

        }
//        return null; //  apparently unnecessary
    }
    
    // if time, create a fetch students list ie select * from students
    
    // update the order details - works
    public void updateOrder(int orderID, int productID, int quantity) throws SQLException {
        int productStock=0 ;
        
        String checkStock = "SELECT STOCKLVL FROM ISDUSER.PRODUCTS WHERE PRODUCTID = (SELECT productID FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")" ;
        ResultSet rs = st.executeQuery(checkStock) ;
        while(rs.next()) {
            productStock = rs.getInt("STOCKLVL") ;
        }
        rs.close() ;
        
        if ((productStock-quantity)>=0 ) {
      
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
            String updateOrderLineQuantity = "UPDATE ISDUSER.ORDERLINEITEM SET PRODUCTID=" + productID + ", ITEMQUANTITY=" + quantity + "WHERE ORDERID=" + orderID ;
            st.executeUpdate(updateOrderLineQuantity) ;
            
            // deduct new product from stock
            String deductProduct = "UPDATE ISDUSER.PRODUCTS SET STOCKLVL=STOCKLVL-" + quantity  + "WHERE PRODUCTID = (SELECT productID FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  ;
            st.executeUpdate(deductProduct) ;
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
    // add an if-branch to perform delete functionality if status = cancelled / deleted
    String updateQuery = "UPDATE ISDUSER.ORDERS SET STATUS='" + status +  "' WHERE ORDERID=" + orderID ;
    st.executeUpdate(updateQuery) ;
    
    }
    
    
    
}

// for reference, these are the inputs for the table
//    orderID INT NOT NULL,
//    orderDate STRING,
//    status VARCHAR(10),
//    totalNoItems INT,
//    totalPrice DOUBLE,
//    userID INT,

// and this is from the model
//    private int orderID;
//    private String orderDate;
//    private String status;
//    private int noItems;
//    private double totalPrice;
