 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel.dao;
import java.sql.*;
import isdmodel.*;
import java.util.ArrayList;

public class DBManager {
    private Statement st;
    private Connection conn;
   
    public DBManager(Connection conn) throws SQLException { 
        this.conn = conn;
        st = conn.createStatement();   
    }

    public ArrayList<Product> getProductList() throws SQLException {       
        String query = "select * from PRODUCTS"; 
        ResultSet rs = st.executeQuery(query);
        ArrayList<Product> productList = new ArrayList();
        while (rs.next()){
            productList.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"),  rs.getFloat("UnitPrice"), rs.getString("ProductType"), rs.getInt("StockLvl")));
        }
        return productList;
    }
    
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

    //delete a user from the database   
    public void deleteProduct(int productID) throws SQLException{       
        String query = "DElETE FROM PRODUCTS WHERE PRODUCTID = " + productID;
        st.executeUpdate(query);
    }
 
}
