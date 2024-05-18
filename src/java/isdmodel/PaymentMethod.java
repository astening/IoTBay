/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel;

import java.time.LocalDate;

/**
 *
 * @author Ella
 */
public class PaymentMethod {
    private int paymentMethodID;
    private String cardNo;
    private int cvv;
    private LocalDate expiryDate;
    private String cardName;
    private int userID;

    public PaymentMethod(int paymentMethodID, int userID, String cardName, String cardNo, int cvv, LocalDate expiryDate) {
        this.paymentMethodID = paymentMethodID;
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardName = cardName;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getCardName() {
        return cardName;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    
}
