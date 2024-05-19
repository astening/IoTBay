/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package isdmodeldao;

/**
 *
 * @author William Sinclair
 */
import isdmodel.Order;
import isdmodel.Payment;
import isdmodel.PaymentMethod;
import isdmodel.User;
import isdmodel.AccessLog;
import isdmodel.Product;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

private Statement st;
private Connection conn;
   
public DBManager(Connection conn) throws SQLException {  
   this.conn = conn;
   st = conn.createStatement();   
}

//Find user by email and password in the database 
public User findUser(String email, String password) throws SQLException {       
   //setup the select sql query string       
   //execute this query using the statement field       
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters  
   String fetch = "select * from Users where EMAIL = '" + email + "' and PASSWORD = '" + password + "'";
   ResultSet rs = st.executeQuery(fetch);
   
   while (rs.next()) {
       String userEmail = rs.getString(5);
       String userPass = rs.getString(6);
       if (userEmail.equals(email) && userPass.equals(password)) {
           int userID = rs.getInt(1);
           int phoneNo = rs.getInt(4);
           String fname = rs.getString(2);
           String lname = rs.getString(3);
           String address = rs.getString(7);
           String city = rs.getString(8);
           String state = rs.getString(9);
           int postcode = rs.getInt(10);
           boolean activation = rs.getBoolean(11);
           Date registrationDate = rs.getDate(12);
           String position = rs.getString(13);
           return new User(userID, fname, lname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, position);
       }
   }
   return null;   
}

//Find staff based on name and position
public User findStaff(String fname, String lname, String position) throws SQLException {       
   //setup the select sql query string       
   //execute this query using the statement field       
   //add the results to a ResultSet       
   //search the ResultSet for a user using the parameters  
   String fetch = "select * from Users where FNAME = '" + fname + "' and LNAME = '" + lname +"' and POSITION = '" + position +"'";
   ResultSet rs = st.executeQuery(fetch);
   
   while (rs.next()) {
       String staffFname = rs.getString(2);
       String staffLname = rs.getString(3);
       String staffPosition = rs.getString(13);
       if (staffFname.equals(fname) && staffLname.equals(lname)) {
           int userID = rs.getInt(1);
           int phoneNo = rs.getInt(4);
           String email = rs.getString(5);
           String password = rs.getString(6);
           String address = rs.getString(7);
           String city = rs.getString(8);
           String state = rs.getString(9);
           int postcode = rs.getInt(10);
           boolean activation = rs.getBoolean(11);
           Date registrationDate = rs.getDate(12);
           return new User(userID, staffFname, staffLname, phoneNo, email, password, address, city, state, postcode, activation, registrationDate, staffPosition);
       }
   }
   return null;   
}

//Add a user-data into the database   
public void addUser(String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, String position) throws SQLException {
    String query = "INSERT INTO Users (FName, LName, PhoneNo, Email, Password, Address, City, State, Postcode, Activation, RegistrationDate, Position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, fname);
        stmt.setString(2, lname);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, true);
        stmt.setDate(11, new java.sql.Date(new Date().getTime())); // Using current date
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}

//Add a new staff-data into the database   
public void addStaff(String fname, String lname, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, Date registrationDate, String position) throws SQLException {
    String query = "INSERT INTO Users (FName, LName, PhoneNo, Email, Password, Address, City, State, Postcode, Activation, RegistrationDate, Position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, fname);
        stmt.setString(2, lname);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, true);
        stmt.setDate(11, new java.sql.Date(new Date().getTime())); // Using current date
        stmt.setString(12, position);
        
        stmt.executeUpdate();
    }
}

//updating existing staff details in the database   
public void updateStaff(int userID, String fName, String lName, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, String position) throws SQLException {
String query = "UPDATE Users SET fName=?, lName=?, phoneNo=?, email=?, password=?, address=?, city=?, state=?, postcode=?, activation=?, position=? WHERE userID=?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, fName);
        stmt.setString(2, lName);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, activation);
        stmt.setString(11, position);
        stmt.setInt(12, userID);

        stmt.executeUpdate();
    }
}

public void updateUser(int userID, String fName, String lName, int phoneNo, String email, String password, String address, String city, String state, int postcode, boolean activation, String position) throws SQLException {
    String query = "UPDATE Users SET fName=?, lName=?, phoneNo=?, email=?, password=?, address=?, city=?, state=?, postcode=?, activation=?, position=? WHERE userID=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, fName);
        stmt.setString(2, lName);
        stmt.setInt(3, phoneNo);
        stmt.setString(4, email);
        stmt.setString(5, password);
        stmt.setString(6, address);
        stmt.setString(7, city);
        stmt.setString(8, state);
        stmt.setInt(9, postcode);
        stmt.setBoolean(10, activation);
        stmt.setString(11, position);
        stmt.setInt(12, userID);
        
        stmt.executeUpdate();
    }
}   


//delete a user from the database   
public void deleteStaff(int userID) throws SQLException {
    String query = "DELETE FROM Users WHERE userID=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userID);
        stmt.executeUpdate();
    }
}

public void deleteUser(String email) throws SQLException {
    String query = "DELETE * FROM Users WHERE email=?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email);
        stmt.executeUpdate();
    }
}

//fetch all staff members from the db
public ArrayList<User> fetchStaff() throws SQLException{
    String fetch = "SELECT * FROM USERS WHERE position IN ('Systems Admin', 'Salesperson', 'Clerk', 'Manager')";
    ResultSet rs = st.executeQuery(fetch);
    ArrayList<User> temp = new ArrayList();
    while (rs.next()) {
        int userID = rs.getInt(1);
        String email = rs.getString(5);
        String fname = rs.getString(2);
        String lname = rs.getString(3);
        String password = rs.getString(6);
        Integer phoneNum = rs.getInt(4);
        String address = rs.getString(7);
        String city = rs.getString(8);
        String state = rs.getString(9);
        Integer postcode = rs.getInt(10);
        Boolean activation = rs.getBoolean(11);
        Date registrationDate = rs.getDate(12);
        String position = rs.getString(13);
        temp.add(new User(userID, fname, lname, phoneNum, email, password, address, city, state, postcode, activation, registrationDate, position));
    } 
    return temp;
}

//check that staff member exists in the DB
public boolean checkStaff(int userID) throws SQLException {
   String fetch = "SELECT * FROM Users WHERE UserID = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(fetch)) {
        stmt.setInt(1, userID);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int staffID = rs.getInt(1);
            if (staffID == userID) {
                return true;
            }
        }
    }
    return false;
}
  
    //find paymentMethod by userid
    public PaymentMethod findPaymentMethod (int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PaymentMethod where USERID = " + userID + " ";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()){
            int uID = rs.getInt(2);
            if(userID == uID){
                int paymentMethodID = rs.getInt(1);
                String cardName  = rs.getString(3);
                String cardNo = rs.getString(4);
                java.sql.Date expiry = rs.getDate(5);
                LocalDate expiryDate = expiry.toLocalDate();
                int cvv = rs.getInt(6);
                return new PaymentMethod(paymentMethodID, userID, cardName, cardNo, cvv, expiryDate);
            }
        }
        return null;
    }
    //add new paymentMethod and attatch it to userID
    public void addPaymentMethod (int userID, String cardName, String cardNo, int cvv, LocalDate expiryDate) throws SQLException{
        st.executeUpdate("INSERT INTO ISDUSER.PaymentMethod " + "(USERID, CARDNAME, CARDNO, EXPIRYDATE, CVV)" +
                "VALUES (" + userID + ", '" + cardName + "', '" + cardNo + "', '" + expiryDate + "'," + cvv + ")");
    }
    //update paymentMethod
    public void updatePaymentMethod (int userID, String cardName, String cardNo, int cvv, LocalDate expiry) throws SQLException{
        java.sql.Date expiryDate = java.sql.Date.valueOf(expiry);
        st.executeUpdate("UPDATE ISDUSER.PaymentMethod SET CARDNAME='" + cardName + "',CARDNO='" + cardNo + "',EXPIRYDATE='" + expiryDate + "',CVV=" + cvv + "WHERE USERID=" + userID + "");
    }
    
    public void deletePaymentMethod (int paymentMethodID) throws SQLException{
        st.executeUpdate("DELETE FROM ISDUSER.PaymentMethod WHERE PAYMENTMETHODID=" + paymentMethodID + "");
    }
    
    public boolean checkPaymentMethod (int userID) throws SQLException {
        String fetch = "select * from ISDUSER.PAYMENTMETHOD where USERID = " + userID + "";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()){
            int uid = rs.getInt(2);
            if(uid == userID){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Payment> getPayments(int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PAYMENT where USERID = " + userID + "";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Payment> payments = new ArrayList<Payment>();
        
        while(rs.next()){
            int uID = rs.getInt(2);
            if(uID==userID){
                int paymentID = rs.getInt(1);
                int orderID = rs.getInt(3);
                java.sql.Date paymentD = rs.getDate(4);
                LocalDate paymentDate = paymentD.toLocalDate();
                double paymentAmount = rs.getDouble(5);
                payments.add(new Payment(paymentID, orderID, paymentDate, paymentAmount));
            }
        }
        if(payments.isEmpty()){
            return null;
        }
        
        return payments;
    }
    
    public boolean checkPayments(int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PAYMENT where USERID = " + userID + "";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()){
            int uid = rs.getInt(2);
            if((uid == userID)){
                return true;
            }
        }
        return false;
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

        // Method to fetch access logs for a specific user
        public ArrayList<AccessLog> getUserAccessLogs(int userID) throws SQLException {
            String query = "SELECT * FROM AccessLog WHERE userID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            ArrayList<AccessLog> logs = new ArrayList<>();
            while (rs.next()) {
                AccessLog log = new AccessLog(
                rs.getInt("logID"),
                rs.getInt("userID"),
                rs.getTimestamp("loginTimestamp"),
                rs.getTimestamp("logoutTimestamp"),
                rs.getString("logDetails")
                );
                logs.add(log);
            }
        return logs;
    }

    public ArrayList<AccessLog> getUserAccessLogsByDate(int userID, Date date) throws SQLException {
        String query = "SELECT * FROM AccessLog WHERE userID = ? AND CAST(loginDateTime AS DATE) = ?";
        ArrayList<AccessLog> logs = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int logID = rs.getInt("logID");
                Date loginDateTime = rs.getTimestamp("loginDateTime");
                Date logoutDateTime = rs.getTimestamp("logoutDateTime");
                String logDetails = rs.getString("logDetails");
                logs.add(new AccessLog(logID, userID, loginDateTime, logoutDateTime, logDetails));
            }
        }

        return logs;
    }   

    public void addAccessLog(int userID, String logDetails, Timestamp logoutTimestamp) throws SQLException {
        String query = "INSERT INTO AccessLog (userID, loginTimestamp, logoutTimestamp, logDetails) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.setTimestamp(3, logoutTimestamp);
            stmt.setString(4, logDetails);
            stmt.executeUpdate();
        }
    }

    public void updateLogoutTime(int userID) throws SQLException {
        String query = "UPDATE AccessLog SET logoutTimestamp = ? WHERE userID = ? AND logoutTimestamp IS NULL";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        }
    }

    public void addUserLogout(int userID) throws SQLException {
        ResultSet result = st.executeQuery("SELECT max(login) from USERS WHERE userID="+userID); 
    
        if(result.next()) {
            Timestamp max = result.getTimestamp(1);
    
            String query = "UPDATE AcessLogs SET logoutTimestamp= CURRENT_TIMESTAMP WHERE userID="+userID+ " AND login='" +max+"'"; 
            st.executeUpdate(query);
        }
    }

    public ArrayList<AccessLog> searchUserAccessLogs(int userID, Date startDate, Date endDate) throws SQLException {
        String query = "SELECT * FROM AccessLog WHERE userID = ? AND loginTimestamp BETWEEN ? AND ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userID);
        stmt.setTimestamp(2, new Timestamp(startDate.getTime()));
        stmt.setTimestamp(3, new Timestamp(endDate.getTime()));
        ResultSet rs = stmt.executeQuery();

        ArrayList<AccessLog> logs = new ArrayList<>();
        while (rs.next()) {
            AccessLog log = new AccessLog(
                rs.getInt("logID"),
                rs.getInt("userID"),
                rs.getTimestamp("loginTimestamp"),
                rs.getTimestamp("logoutTimestamp"),
                rs.getString("logDetails")
            );
            logs.add(log);
        }
        return logs;
    }
    
    //get all the products in the database
    public ArrayList<Product> getProductList() throws SQLException {       
        String query = "select * from PRODUCTS"; 
        ResultSet rs = st.executeQuery(query);
        ArrayList<Product> productList = new ArrayList();
        while (rs.next()){
            productList.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"),  rs.getFloat("UnitPrice"), rs.getString("ProductType"), rs.getInt("StockLvl")));
        }
        return productList;
    }
    
    //find a product in the database by it's ID
    public Product findProduct(int productID) throws SQLException {
        String query = "select * from PRODUCTS where PRODUCTID = " + productID + " "; 
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            int id = rs.getInt(1);
            if (productID == id){
                return new Product(rs.getInt(1), rs.getString(2), rs.getFloat(4), rs.getString(3), rs.getInt(5));
            }
        }
        return null;
    }
    
    //add a product to the database
    public void addProduct(String name, float price, String type, int stockLvl) throws SQLException {                   //code for add-operation       
        String query = "INSERT INTO Products (PRODUCTNAME, PRODUCTTYPE, UNITPRICE, STOCKLVL) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, name); 
            stmt.setString(2, type);
            stmt.setFloat(3, price); 
            stmt.setInt(4, stockLvl);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
    }

    //update a product in the database
    public void updateProduct(int productID, String name, float price, String type, int stockLvl) throws SQLException {       
        String query = "UPDATE Products SET PRODUCTNAME = ?, PRODUCTTYPE = ?, UNITPRICE = ?, STOCKLVL = ? WHERE PRODUCTID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1, name); 
            stmt.setString(2, type);
            stmt.setFloat(3, price); 
            stmt.setInt(4, stockLvl);
            stmt.setInt(5, productID);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
    }       

    //delete a product from the database   
    public void deleteProduct(int productID) throws SQLException{       
        String query = "DElETE FROM PRODUCTS WHERE PRODUCTID = " + productID;
        st.executeUpdate(query);
    }
}