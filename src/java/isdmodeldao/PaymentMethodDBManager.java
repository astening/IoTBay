/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodeldao;

import isdmodel.PaymentMethod;
import isdmodel.Payment;
import java.sql.*;
import java.util.ArrayList;
import isdmodel.User;
/**
 *
 * @author Ella
 */
public class PaymentMethodDBManager {
    
    private Statement st;
    
    public PaymentMethodDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
    //find paymentMethod by userid
    public PaymentMethod findPaymentmethod (int userID) throws SQLException{
        String fetch = "select * from ISDUSER.PaymentMethod where USERID = '" + userID + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()){
            int uID = rs.getInt(2);
            if(userID == uID){
                int paymentMethodID = rs.getInt(1);
                String cardName  = rs.getString(3);
                int cardNo = rs.getInt(4);
                Date expiryDate = rs.getDate(5);
                int cvv = rs.getInt(6);
                return new PaymentMethod(paymentMethodID, userID, cardName, cardNo, cvv, expiryDate);
            }
        }
        return null;
    }
    //add new paymentMethod and attatch it to userID
    public void addPaymentMethod (int userID, String cardName, int cardNo, int cvv, Date expiryDate) throws SQLException{
        st.executeUpdate("INSERT INTO ISDUSER.PaymentMethod " + "VALUES ('"
                + userID + "', '" + cardName + "', '" + cardNo + "', '" + expiryDate + "','" + cvv + "')");
    }
    //update paymentMethod
    public void updatePaymentMethod (int userID, String cardName, int cardNo, int cvv, Date expiryDate) throws SQLException{
        st.executeUpdate("UPDATE ISDUSER.PaymentMethod SET CARDNAME='" 
                + cardName + "',CARDNO'" + cardNo + "',EXPIRYDATE'" + expiryDate + "',CVV'" + cvv
                + "'WHERE USERID='" + userID + "'");
    }
    
    public void deletePaymenetMethod (int userID, String cardName, int cardNo, int cvv, Date expiryDate) throws SQLException{
        st.executeUpdate("DELETE FROM ISDUSER.PaymentMethod WHERE USERID='" + userID + "'");
    }
    
    public boolean checkPaymentMethod (int userID) throws SQLException {
        String fetch = "select * from ISDUSER.PAYMENTMETHOD where USERID = '" + userID + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()){
            int uid = rs.getInt(2);
            if(uid == userID){
                return true;
            }
        }
        return false;
    }
    
}
