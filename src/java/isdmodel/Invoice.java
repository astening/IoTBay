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
public class Invoice {
    private int invoiceID;
    private Date invoiceSentDate;
    private Date invoiceDueDate;

    public Invoice(int invoiceID, Date invoiceSentDate, Date invoiceDueDate) {
        this.invoiceID = invoiceID;
        this.invoiceSentDate = invoiceSentDate;
        this.invoiceDueDate = invoiceDueDate;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceSentDate() {
        return invoiceSentDate;
    }

    public void setInvoiceSentDate(Date invoiceSentDate) {
        this.invoiceSentDate = invoiceSentDate;
    }

    public Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(Date invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }
    
}
