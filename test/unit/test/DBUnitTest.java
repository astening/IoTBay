/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;

/**
 *
 * @author chari
 */

import isdmodel.Order;
import org.junit.Test ;
import static org.junit.Assert.* ;
import isdmodeldao.* ;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUnitTest {
    
    DBConnector connector = new DBConnector();

    Connection conn = connector.openConnection();
    
    Statement st = conn.createStatement() ;

    DBManager db;    
    
    public DBUnitTest() throws SQLException, ClassNotFoundException {
        this.connector = new DBConnector();
        this.db = new DBManager(conn);
    }
    
    // test update order status   
    @Test
    public void testUpdateOrderStatus() throws SQLException { // validation occurs with validator
        
        // set up objects and variables
        String status = null;
        
        // run status method
        db.updateOrderStatus(2, "New") ;
        
        // check that the results are equal
        String updateStatus = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=2" ;
        ResultSet rs = st.executeQuery(updateStatus) ;
        while (rs.next()) {
            status = rs.getString("status") ;
        }
        
        // check if values are changed
        assertEquals("New", status) ;

    }   
    
    // test add order
    @Test
    public void testAddOrder() throws SQLException {
//        
//        // set up variables
//        int returnID = 0;
//        String returnStatus = "" ;
//        int returnProductID = 0 ;
//        int returnUserID = 0 ;
//        int returnItems = 0 ;
//        
//        try {
//            
//        db.addOrder(2, 1, 3); // items, user product
//        
//        // retrieve variables
//        String getOrder = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=123" ;
//        ResultSet rs = st.executeQuery(getOrder) ;
//        while (rs.next()) {
//            returnStatus = rs.getString("status") ;
//            returnID = rs.getInt("orderID") ;
//            returnProductID = rs.getInt("productID") ;
//            returnUserID = rs.getInt("userID") ;
//            returnItems = rs.getInt("noItems") ;
//        }   
//        
//        // check the values are as expected
//        assertEquals("New", returnStatus) ;
//        assertEquals(123, returnID) ;
//        assertEquals(3, returnProductID) ;
//        assertEquals(1, returnUserID) ;
//        assertEquals(2, returnItems) ;
//
//        connector.closeConnection();
//
//        } catch (SQLException ex) {
//
//        Logger.getLogger(DBUnitTest.class.getName()).log(Level.SEVERE, null, ex);
//        
//        }  
    
    }
    
    // test find order
    @Test
    public void testFindOrder() throws SQLException {
        
        // set up objects and variables
        
       int testID = 1 ;
       String testDate = "2024-04-01" ;
       String testStatus = "Pending" ;
       double testPrice = 150.0 ;
       int testQuantity = 5 ;
       
       int resultID = 0 ;
       String resultDate = "" ;
       String resultStatus = "" ;
       double resultPrice = 0 ;
       int resultQuantity = 0 ;       
        
        // run find order
        ArrayList<Order> list = db.findOrder(1, "2024-04-01");
        for (Order i: list) {
            resultID = i.getOrderID() ;
            resultDate = i.getOrderDate() ;
            resultStatus = i.getStatus() ;
            resultPrice = i.getTotalPrice() ;
            resultQuantity = i.getNoItems() ;
        }
        
        // check if values are equal
        assertEquals(testID, resultID) ;
        assertEquals(testDate, resultDate) ;
        assertEquals(testStatus, resultStatus) ;
        assertEquals(testPrice, resultPrice, 0.1) ;
        assertEquals(testID, resultID) ;
        assertEquals(testQuantity, resultQuantity) ;

    }      
    
    // test update order
    @Test
    public void testUpdateOrder() {
        
        // set up variables
        int returnItems = 0 ;
        
        try {
            
        db.updateOrder(2, 2, 2);
        
        // retrieve variables
        String getOrder = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=2" ;
        ResultSet rs = st.executeQuery(getOrder) ;
        while (rs.next()) {
            returnItems = rs.getInt("totalNoItems") ;
        }   
        
        // check the values are as expected
        assertEquals(2, returnItems) ;

        connector.closeConnection();

        } catch (SQLException ex) {

        Logger.getLogger(DBUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        
        }         
    }
    
    // test delete order
    @Test
    public void testDeleteOrder() throws SQLException {
        
        // set up objects and variables
        String status = null;
        
        // run status method
        db.deleteOrder(6) ;
        
        // check that the results are true?
        String deleteStatus = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=5" ;
        ResultSet rs = st.executeQuery(deleteStatus) ;
        while (rs.next()) {
            status = rs.getString("status") ;
        }
        
        // check if values are changed
        assertEquals("Cancelled", status) ;

    }      
    
    // test find all orders --> arraylist, for loop to check the list   
//    @Test
//    public void testFetchAllOrders() throws SQLException {
//    }
    
    
    // test find user orders
    @Test
    public void testFetchUserOrders() throws SQLException {
        // set up variables
        ArrayList<Order> resultList = db.fetchUserOrders(8) ;
                
        int resultID = 0 ;
        String resultDate = "" ;
        String resultStatus = "" ;
        int resultQuantity = 0 ;
        double resultPrice = 0.0 ;     
        
        for (Order order: resultList) {
            resultID = order.getOrderID() ;
            resultDate = order.getOrderDate() ;
            resultStatus = order.getStatus() ;
            resultQuantity = order.getNoItems();
            resultPrice = order.getTotalPrice() ;
        }  
        
        // test values
        int testID = 8 ;
        String testDate = "2024-04-08" ;
        String testStatus = "Completed" ;
        int testQuantity = 4 ;
        double testPrice = 120.0 ;
        
        // check if equal
        assertEquals(testID, resultID) ;
        assertEquals(testDate, resultDate) ;
        assertEquals(testStatus, resultStatus) ;
        assertEquals(testQuantity, resultQuantity) ;
        assertEquals(testPrice, resultPrice, 0.1) ;
    }
    
    
}
