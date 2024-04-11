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
public class Shipment {
    private int shipmentID;
    private Date shipmentDate;
    private String carrier;
    private int trackingNo;

    public Shipment(int shipmentID, Date shipmentDate, String carrier, int trackingNo) {
        this.shipmentID = shipmentID;
        this.shipmentDate = shipmentDate;
        this.carrier = carrier;
        this.trackingNo = trackingNo;
    }

    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(int trackingNo) {
        this.trackingNo = trackingNo;
    }

    
}
