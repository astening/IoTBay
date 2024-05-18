/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel;

import java.util.Date;

/**
 *
 * @author William
 */
public class User {
    private int userID;
    private String fname;
    private String lname;
    private Integer phoneNo; 
    private String email;
    private String password;
    private String address; 
    private String city;
    private String state;
    private Integer postcode; 
    private Boolean activation;
    private Date registrationDate; 
    private String position;

public User(int userID, String fname, String lname, Integer phoneNo, String email, String password, String address, String city, String state, Integer postcode, Boolean activation, Date registrationDate, String position) {
        this.userID = userID;
        this.fname = fname; 
        this.lname = lname;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.activation = activation;
        this.registrationDate = registrationDate;
        this.position = position;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setUserID(int userID) {
        this.userID = userID;
      
    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(Boolean activation) {
        this.activation = activation;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getUserID() {
        return userID;
    }
    

}
