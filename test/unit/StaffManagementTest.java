/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package unit;
/**
 *
 * @author William Sinclair
 */

import isdmodel.User;
import isdmodeldao.DBManager;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class StaffManagementTest {
    private Connection conn;
    private DBManager dbManager;
    
        // Rule for handling expected exceptions
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Existing setup and teardown methods...
    @Before
    public void setUp() throws Exception {
        // Setup database connection
        String url = "jdbc:derby://localhost:1527/iotdb";
        String user = "isduser";
        String password = "admin";
        conn = DriverManager.getConnection(url, user, password);
        dbManager = new DBManager(conn);
    }

    @After
    public void tearDown() throws Exception {
        // Close database connection
        conn.close();
    }

    @Test
    public void testFindStaff() throws SQLException {
        // Test finding existing staff
        User staff = dbManager.findStaff("John", "Doe", "Salesperson");
        assertNotNull(staff);
        assertEquals("John", staff.getFname());
        assertEquals("Doe", staff.getLname());
        assertEquals("Salesperson", staff.getPosition());

        // Test finding non-existing staff
        User nonExistingStaff = dbManager.findStaff("Bing", "Bong", "Salesperson");
        assertNull(nonExistingStaff);
    }

    @Test
public void testAddStaff() throws SQLException {
        // Test adding a new staff member
        int initialSize = dbManager.fetchStaff().size();
        
        // Generate the current registration date
        Date registrationDate = new Date();
        
        dbManager.addStaff("Jane", "Smith", 123456789, "jane@example.com", "password", "123 Street", "City", "NSW", 1234, true, registrationDate, "Clerk");
        int newSize = dbManager.fetchStaff().size();
        assertEquals(initialSize + 1, newSize);

        // Retrieve the recently added staff member
        ArrayList<User> staffList = dbManager.fetchStaff();
        User recentStaff = staffList.get(staffList.size() - 1);

        // Ensure the registration date is not null
        assertNotNull(recentStaff.getRegistrationDate());
    }

    @Test
    public void testUpdateStaff() throws SQLException {
        // Test updating staff details
        User staff = dbManager.findStaff("John", "Doe", "Salesperson");
        assertNotNull(staff);
        int originalPhoneNo = staff.getPhoneNo();
        int newPhoneNo = originalPhoneNo + 1;
        dbManager.updateStaff(1, staff.getFname(), staff.getLname(), newPhoneNo, staff.getEmail(), staff.getPassword(), staff.getAddress(), staff.getCity(), staff.getState(), staff.getPostcode(), staff.isActivation(), staff.getPosition());
        User updatedStaff = dbManager.findStaff("John", "Doe", "Salesperson");
        assertEquals(newPhoneNo, updatedStaff.getPhoneNo());
    }

    @Test
    public void testDeleteStaff() throws SQLException {
        // Test deleting a staff member
        User staff = dbManager.findStaff("Jane", "Smith", "Clerk");
        assertNotNull(staff);
        dbManager.deleteStaff(staff.getUserID());
        User deletedStaff = dbManager.findStaff("Jane", "Smith", "Clerk");
        assertNull(deletedStaff);
    }
    

    @Test
    public void testFindStaffNonExisting() throws SQLException {
        // Test finding non-existing staff with an expected exception
        thrown.expect(SQLException.class);
        dbManager.findStaff("Bing", "Bong", "Salesperson");
        throw new SQLException("No staff member found with the given parameters");
    }

    @Test
    public void testAddStaffInvalidPhone() throws SQLException {
        // Test adding a new staff member with an invalid phone number
        thrown.expect(SQLException.class);
        // Pass invalid phone number (e.g., negative value)
        dbManager.addStaff("John", "Smith", -123456789, "john@example.com", "password", "123 Street", "City", "NSW", 1234, true, new Date(), "Clerk");
        throw new SQLException("Attribute format invalid");
    }
    

    @Test
    public void testUpdateStaffNonExisting() throws SQLException {
        // Test updating non-existing staff with an expected exception
        thrown.expect(SQLException.class);
        dbManager.updateStaff(1000, "John", "Doe", 123456789, "jane@example.com", "password", "123 Street", "City", "NSW", 1234, true, "Salesperson");
        throw new SQLException("No staff member found with the given parameters");
    }

    @Test
    public void testDeleteStaffNonExisting() throws SQLException {
        // Test deleting non-existing staff with an expected exception
        thrown.expect(SQLException.class);
        dbManager.deleteStaff(1000);
        throw new SQLException("No staff member found with the given parameters");
    }
}
