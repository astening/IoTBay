/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;

/**
 *
 * @author chari
 */

import junit.framework.* ;
import org.junit.Test ;
import static org.junit.Assert.* ;
import isdmodeldao.DBManager ;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateOrderStatusTest {
    
    private Connection conn ; // what the?
    private static Statement st ;
    
    public UpdateOrderStatusTest() {    }
    
    // test the functionality of updateOrder
    
    @Test
    public void testCapitalisedOrder() throws SQLException { // test status with capital letters
        // the test is broken
        
        // set up objects and variables
        DBManager manager = new DBManager(conn) ;
        String status = null;
        
        // run status method
        manager.updateOrderStatus(2, "NEW") ;
        
        // check that the results are true?
        String updateStatus = "SELECT * FROM ISDUSER.ORDERS WHERE ORDERID=2" ;
        ResultSet rs = st.executeQuery(updateStatus) ;
        while (rs.next()) {
            status = rs.getString("status") ;
        }
        
        // check if values are changed
        assertEquals("NEW", status) ;

    }
    
    // test with all lowercase, invalid and status with a letter --> surely this should be covered in the check status anyway? - tbc
    
}
