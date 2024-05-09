/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel;

import java.util.Date;

/**
 *
 * @author Ella
 */
public class PaymentMethod {
    private int paymentMethodID;
    private int cardNo;
    private int cvv;
    private Date expiryDate;
    private String cardName;

    public PaymentMethod(int paymentMethodID, int cardNo, int cvv, Date expiryDate, String cardName) {
        this.paymentMethodID = paymentMethodID;
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardName = cardName;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public int getCardNo() {
        return cardNo;
    }

    public int getCvv() {
        return cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getCardName() {
        return cardName;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    
    
}
