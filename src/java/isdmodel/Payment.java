/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ella
 */
public class Payment {
    private int paymentID;
    private LocalDate paymentDate;
    private double paymentAmount;
    private int orderID;

    public Payment(int paymentID, int orderID, LocalDate paymentDate, double paymentAmount) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

}
    
    