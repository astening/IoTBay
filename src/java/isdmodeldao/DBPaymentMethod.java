/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

import isdmodel.Payment;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Ella
 */
public class DBPaymentMethod {
    
    private Statement st;
    
    public DBPaymentMethod(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
    
}
