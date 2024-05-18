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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author chari
 */
public class DBManager {

    private static Statement st;
   
    public DBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }
        
    // add the order in the database - could be updated for a null user ID
    public int addOrder(int noItems, int userID, int productID) throws SQLException { 

        int productStock=0 ;
        
        // Check product quantity
        String checkStock = "SELECT STOCKLVL FROM ISDUSER.PRODUCTS WHERE PRODUCTID =" + productID ;
        ResultSet rs = st.executeQuery(checkStock) ;
        while(rs.next()) {
            productStock = rs.getInt("STOCKLVL") ;
        }
        rs.close() ;
        
        if ((productStock-noItems)>=0 ) {
            
            // use default status
            String status = "New" ;
            
            // use today's date and convert to string
            LocalDate date = LocalDate.now() ;
            String orderDate = date.toString() ;
            
            // calculate total price of order
            int totalPrice = 0 ;
            
            String priceQuery = "SELECT UNITPRICE FROM ISDUSER.PRODUCTS WHERE PRODUCTID =" + productID ;
            ResultSet priceResults = st.executeQuery(priceQuery) ;
            while(priceResults.next()) {
                totalPrice = noItems*priceResults.getInt("UNITPRICE") ;
            }
            rs.close() ;

            // add the order details
            String addQuery = "INSERT INTO ISDUSER.ORDERS (orderDate, status, totalNoItems, totalPrice, userID) VALUES( '" + orderDate + "', '" + status + "',"  + noItems + "," + totalPrice + "," + userID + ")" ;
            st.executeUpdate(addQuery) ;
            
            // retrieve most recent order and add into orderLine table
            // return order ID to user for reference
            String IDQuery = "SELECT MAX(orderID) FROM ISDUSER.ORDERS" ;
            ResultSet IDResults = st.executeQuery(IDQuery) ;
            int orderID = 0;
            while(IDResults.next()) {
                orderID = IDResults.getInt(1) ;
            }
            rs.close() ;            
            String addOrderLine = "INSERT INTO ISDUSER.ORDERLINEITEM(itemQuantity, orderID, productID) VALUES(" + noItems + ", " + orderID + ", " + productID + ")" ;
            st.executeUpdate(addOrderLine) ;
            
            // deduct product from the product table
            String deductProduct = "UPDATE ISDUSER.PRODUCTS SET STOCKLVL=STOCKLVL-" + noItems  + "WHERE PRODUCTID =" + productID ;
            st.executeUpdate(deductProduct) ;
            
            return orderID ;

        }
        
        return 0 ;
  
    }
    
    // find an order using orderID and orderDate - works
    public ArrayList<Order> findOrder(int orderID, String orderDate) throws SQLException {
        String findQuery = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=" + orderID + "AND orderDate='" + orderDate + "'" ;
        ResultSet rs = st.executeQuery(findQuery) ;
        ArrayList<Order> list = new ArrayList() ;
        
        // adds the order to a list
        while (rs.next()) {
            int rsOrderID = (int) rs.getInt("orderID") ;
            String rsOrderDate = (String) rs.getString("orderDate") ;
            if (rsOrderID==orderID && rsOrderDate.equals(orderDate)) {
                String status = rs.getString(3) ;
                int totalNoItems = rs.getInt(4) ;
                double totalPrice = rs.getDouble(5) ;
                list.add(new Order(orderID, orderDate, status, totalNoItems, totalPrice)) ;
                return list ;
            }

        }
        return null;
    }
    
    // fetch all orders as a list - works
    public ArrayList<Order> fetchAllOrders() throws SQLException {
        String fetch = "SELECT * FROM ORDERS" ;
        ArrayList<Order> list = new ArrayList() ;
        ResultSet rs = st.executeQuery(fetch) ;
        
        // each order is added to a list
        while (rs.next()) {
            int orderID = rs.getInt(1) ;
            String orderDate = rs.getString(2) ;
            String status = rs.getString(3) ;
            int totalNoItems = rs.getInt(4) ;
            double totalPrice = rs.getDouble(5) ;
            list.add(new Order(orderID, orderDate, status, totalNoItems, totalPrice)) ;
        }
        
        return list ;
    }
    
    // fetch all user orders as a list - works
    public ArrayList<Order> fetchUserOrders(int userID) throws SQLException {
        String fetch = "SELECT * FROM ORDERS WHERE USERID=" + userID ;
        ArrayList<Order> list = new ArrayList() ;
        ResultSet rs = st.executeQuery(fetch) ;
        
        // adds orders to the list
        while (rs.next()) {
            int orderID = rs.getInt(1) ;
            String orderDate = rs.getString(2) ;
            String status = rs.getString(3) ;
            int totalNoItems = rs.getInt(4) ;
            double totalPrice = rs.getDouble(5) ;
            list.add(new Order(orderID, orderDate, status, totalNoItems, totalPrice)) ;
        }
        
        return list ;
    }   
    
    // update the order details - works
    public void updateOrder(int orderID, int productID, int quantity) throws SQLException {
        int productStock=0 ;
        
        String checkStock = "SELECT STOCKLVL FROM ISDUSER.PRODUCTS WHERE PRODUCTID =" + productID ;
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
    public void deleteOrder(int orderID) throws SQLException {
        // set status to cancelled
        String changeStatus = "UPDATE ISDUSER.ORDERS SET STATUS='Cancelled' WHERE ORDERID=" + orderID ;
        st.executeUpdate(changeStatus) ;
        
        // add ordered quantity back to the product table
        // is this truly working?
        String restoreProduct = "UPDATE ISDUSER.PRODUCTS SET STOCKLVL=STOCKLVL+" + "(SELECT ITEMQUANTITY FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  + "WHERE PRODUCTID = (SELECT productID FROM ISDUSER.ORDERLINEITEM WHERE ORDERID=" + orderID + ")"  ;
        st.executeUpdate(restoreProduct) ;
        
    }
    
    //update order status in the database - works  
    public void updateOrderStatus( int orderID, String status) throws SQLException {       
    String updateQuery = "UPDATE ISDUSER.ORDERS SET STATUS='" + status +  "' WHERE ORDERID=" + orderID ;
    st.executeUpdate(updateQuery) ;
    
    }
    
    
    
}
