/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;
import isdcontroller.Validator;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import isdmodel.*;
import isdmodeldao.DBManager;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DeviceCatalogueTest {
    private DBManager manager;
    private Validator validator = new Validator();
    protected String URL = "jdbc:derby://localhost:1527/";  
    protected String db = "iotdb";//name of the database   
    protected String dbuser = "app";//db root user   
    protected String dbpass = "app"; //db root password   
    protected String driver = "org.apache.derby.jdbc.ClientDriver"; //jdbc client driver - built in with NetBeans   
    protected Connection conn; //connection null-instance to be initialized in sub-classes

    public DeviceCatalogueTest() throws SQLException {
        conn = DriverManager.getConnection(URL+db, dbuser, dbpass);
        manager = new DBManager(conn);
    }
    
    //for testing, select a product from the database and update the details accordingly
    
    /*
    @Test
    public void testFindProduct() throws SQLException {
        // Testing against existing product with:
        // ID: 5
        // Name: "Product5"
        // Price: 30
        // Type: "Type2"
        // StockLvl: 300
        
        Product product = manager.findProduct(5); //looks for product with productID of 5
        assertNotNull(product); //tests if the product is null
        assertEquals("Product5", product.getName());
        assertEquals(30, product.getPrice(), 0);
        assertEquals("Type2", product.getType());
        assertEquals(300, product.getStockLvl());
    }
    
    @Test
    public void testAddProduct() throws SQLException {
        ArrayList<Product> originalProducts = manager.getProductList(); //gets array before product is added
        
        manager.addProduct("newProduct", 30, "newType", 10); //adds a new product
        ArrayList<Product> newProducts = manager.getProductList(); //gets array with the new product included
        
        assertEquals(originalProducts.size()+1, newProducts.size()); //checks the size went up by 1
    }
    
    /*
    @Test
    public void testUpdateProduct() throws SQLException {
        Product product = manager.findProduct(38); //looks for product with productID of 38
        String ogName = product.getName();
        float ogPrice = product.getPrice();
        String ogType = product.getType();
        int ogStock = product.getStockLvl();
        
        manager.updateProduct(38, "upName", 928, "upType", 1392);
        
        assertNotEquals(ogName, "upName"); //tests if the name was updated
        assertNotEquals(ogPrice, 928); //tests if the price was updated
        assertNotEquals(ogType, "upType"); //tests if the type was updated
        assertNotEquals(ogStock, 1392); //tests if the stock was updated
        
    }
    
    @Test
    public void testDeleteProduct() throws SQLException {
        assertNotNull(manager.findProduct(24)); //checks the product exists in the first place
        manager.deleteProduct(24);
        assertNull(manager.findProduct(24)); //checks the product was deleted
    } */
    
    @Test
    public void testFindNullProduct() throws SQLException {
        // Testing finding product that does not exist:
        Product notAProduct = manager.findProduct(7284);
        assertNull(notAProduct); //tests that the product is null
    }
    
    @Test
    public void testUpdateNullProduct() throws SQLException {
        // Testing updating product that does not exist:
        Product notAProduct = manager.findProduct(7284);
        manager.updateProduct(7284, "upName", 928, "upType", 1392);
        assertNull(notAProduct); //tests that the product is null
    }
    
    @Test
    public void testDeleteNullProduct() throws SQLException {
        // Testing deleting product that does not exist:
        Product notAProduct = manager.findProduct(7284);
        manager.deleteProduct(7284);
        assertNull(notAProduct); //tests that the product is null
    }
    
    @Test 
    public void testValidateStock() throws SQLException {
        assertEquals(validator.validateProductStock("123"), true);
        assertEquals(validator.validateProductStock("*s2"), false);
    }
    
    @Test 
    public void testValidatePrice() throws SQLException {
        assertEquals(validator.validateProductStock("123"), true);
        assertEquals(validator.validateProductStock("sj*h3#$h"), false);
    }
    
    @Test 
    public void testValidateType() throws SQLException {
        assertEquals(validator.validateProductStock("Type"), true);
        assertEquals(validator.validateProductStock("sj*h3#$h"), false);
    }
    
    @Test 
    public void testValidateName() throws SQLException {
        assertEquals(validator.validateProductStock("Smart Thermostat"), true);
        assertEquals(validator.validateProductStock("sj*h3#$h"), false);
    }
    
}
