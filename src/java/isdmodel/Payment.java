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
public class Payment {
    private int paymentID;
    private Date paymentDate;
    private double paymentAmount;
    private String paymentMethodID;
    private int cardNo;
    private int cvv;
    private Date expiryDate;
    private String cardName;

    public Payment(int paymentID, Date paymentDate, double paymentAmount, String paymentMwthod, int cardNo, int cvv, Date expiryDate, String cardName) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethodID = paymentMethodID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMwthod() {
        return paymentMethodID;
    }

    public void setPaymentMwthod(String paymentMwthod) {
        this.paymentMethodID = paymentMwthod;
    }
}
