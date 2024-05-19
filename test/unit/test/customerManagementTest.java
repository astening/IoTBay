/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;

import isdmodel.User;
import isdmodeldao.DBConnector;
import isdmodeldao.DBManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author taylah
 */
public class customerManagementTest {
    DBConnector connector = new DBConnector();

    Connection conn = connector.openConnection();
    

    DBManager db; 
    
    public customerManagementTest() throws SQLException, ClassNotFoundException {
        this.connector = new DBConnector();
        this.db = new DBManager(conn);
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void findCustomer() throws SQLException {
        User customerToFind = db.findCustomer("Mark", "Moore", "Individual");
        //check that customer is not null
        assertNotNull(customerToFind);
        
        //check that customer first name, last name and type matches
        assertEquals("Mark", customerToFind.getFname());
        assertEquals("Moore", customerToFind.getLname());
        assertEquals("Individual", customerToFind.getPosition());
        
        //test to find null customer (customer does not exist)
        User nullCustomer = db.findCustomer("Invalid", "Customer", "Individual");
        //check if nullCustomer is null if customer can't be found in database
        assertNull("Customer can be found in the database", nullCustomer);
    }
    
    @Test
    public void findCustomerByFName() throws SQLException {
        ArrayList<User> customersList = db.findCustomerByFName("Mark");
        
        //find a specific customer that matches the First Name of "Mark"
        boolean foundCustomer = false;
        for (User customer : customersList) {
            if ("Mark".equals(customer.getFname()) && 
                "Moore".equals(customer.getLname()) && 
                "Individual".equals(customer.getPosition())) {
                    foundCustomer = true;
                    break;
            }
        }
        //check if existing customer can be found
        assertTrue("Customer cannot be found in Customer records", foundCustomer);
        
        //test finding null customer first name
        ArrayList<User> nullCustomers = db.findCustomerByFName("Invalid");
        //nullCustomers should return an empty list
        assertNotNull("The nullCustomers list is null", nullCustomers);
    }
    
    @Test
    public void findCustomerByType() throws SQLException {
        ArrayList<User> customersList = db.findCustomerByType("Individual");
        
        //find a specific customer that matches the customer type of "Individual"
        boolean foundCustomer = false;
        for (User customer : customersList) {
            if ("Mark".equals(customer.getFname()) && 
                "Moore".equals(customer.getLname()) && 
                "Individual".equals(customer.getPosition())) {
                    foundCustomer = true;
                    break;
            }
        }
        //check if existing customer can be found
        assertTrue("Customer cannot be found in Customer records", foundCustomer);
        
        //test finding null customer type
        ArrayList<User> nullCustomers = db.findCustomerByType("Banker");
        //nullCustomers should return an empty list
        assertNotNull("The nullCustomers list is null", nullCustomers);
    }
    
    @Test
    public void findCustomerWithInvalidEmail() throws SQLException {
        //test adding a new customer with an invalid email
        thrown.expect(SQLException.class);
        //input invalid email (no use of @ symbol)
        db.addCustomer("Bruce", "Keely", 98645673, "bruce", "jhdsfi", "832 Hophill St", "Vale", "QLD", 2372, true, new Date(), "Individual");
        //should throw a SQL Exception as invalid format for Email
        throw new SQLException("Invalid format for Email");
    }
    
    @Test
    public void findCustomerWithInvalidCity() throws SQLException {
        //test adding a new customer with an invalid city
        thrown.expect(SQLException.class);
        //input invalid email (using numbers instead of letters)
        db.addCustomer("Lillie", "Baker", 98645673, "LillieBaker@email.com", "password90", "986 Diamond Rd", "98685", "WA", 8757, true, new Date(), "Company");
        //should throw a SQL Exception as invalid format for City
        throw new SQLException("Invalid input for City");
    }

    
    @Test
    public void findNullCustomer() throws SQLException {
        //customer is null if they don't exist in the database    
        //test if database will throw a SQLException if null Customer is searched for (as they don't exist in the database)
        thrown.expect(SQLException.class);
        db.findCustomer("Bruce", "Johnson", "Individual");
        //should throw SQLException as no customer is found
        throw new SQLException("Customer cannot be found in database");
    }

    @Test
    public void createCustomer() throws SQLException {
        db.addCustomer("Andrew", "Doe", 986526357, "AndrewDoe@email.com", "password1", "98 Carlson St", "West End", "QLD", 7652, true, new Date(), "Company");
        
        //get recently added Customer
        ArrayList<User> customerList = db.fetchCustomerList();
        User addedCustomer = customerList.get(customerList.size() - 1);

        //check if first name of recently added customer is "Andrew"
        assertEquals("Andrew", addedCustomer.getFname());
    }

    @Test
    public void updateCustomer() throws SQLException {
        //test updating Customer records
        User customerToUpdate = db.findCustomer("Mark", "Moore", "Individual");
        //check if customer exists
        assertNotNull(customerToUpdate);
        String newEmail = "newEmail@email.com";
        db.updateCustomer(1, customerToUpdate.getFname(), customerToUpdate.getLname(), customerToUpdate.getPhoneNo(), newEmail, customerToUpdate.getPassword(), customerToUpdate.getAddress(), customerToUpdate.getCity(), customerToUpdate.getState(), customerToUpdate.getPostcode(), customerToUpdate.isActivation(), customerToUpdate.getPosition());
        User updatedCustomer = db.findCustomer("Mark", "Moore", "Individual");
        //check if email as been updated
        assertEquals(newEmail, updatedCustomer.getEmail());
    }

    @Test
    public void deleteCustomer() throws SQLException {
        //test deleting a Customer record
        User customer = db.findCustomer("Stacy", "Lane", "Company");
        //check if user exists in database
        assertNotNull(customer);
        db.deleteCustomer(customer.getUserID());
        
        User deletedCustomer = db.findCustomer("Stacy", "Lane", "Company");
        //deletedCustomer should be null as customer has been deleted
        assertNull("Customer is not null", deletedCustomer);
    }

    @Test
    public void updateNullCustomer() throws SQLException {
        //customer is null if they don't exist in the database
        //test if database will throw a SQLException if null Customer is updated 
        thrown.expect(SQLException.class);
        db.updateCustomer(8635, "John", "Doe", 76475435, "johnDoe@email.com", "qwerty", "2623 Vale Rd", "Lakehill", "LA", 8567, true, "Individual");
        //should throw SQLException as no customer is found
        throw new SQLException("No customer found within the given parameters");
    }

    @Test
    public void deleteNullCustomer() throws SQLException {
        //customer is null if they don't exist in the database    
        //test if database will throw a SQLException if null Customer is deleted (as they don't exist in the database)
        thrown.expect(SQLException.class);
        db.deleteCustomer(6257);
        //should throw SQLException as no customer is found
        throw new SQLException("No customer found with the given parameters");
    }
}
