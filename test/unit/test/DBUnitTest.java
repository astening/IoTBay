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
    
    // test the functionality of updateOrder   
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
    
    // test add order - TBC - also need to check product table, order line table etc
    @Test
    public void testAddOrder() throws SQLException {
//        try {
//
//        int returnID = db.addOrder(2, 1, 3);
//        
//        // check that the order is assigned an ID, and hence has been added
//        assertEquals(121, returnID) ;
//
//        connector.closeConnection();
//
//        } catch (SQLException ex) {
//
//        Logger.getLogger(DBUnitTest.class.getName()).log(Level.SEVERE, null, ex);
//        
//        }  
//    
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
    
    // test find all orders --> arraylist, for loop to check the list :)
    
    // test update order
    
    // test delete order - need to check that products are restored
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
    
    
    
    
}
