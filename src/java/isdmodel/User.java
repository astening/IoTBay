/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author aneir
 */
public class Users implements Serializable{
    
    private int userID; //stores ID
    private String fName; //stores first name
    private String lName; //stores last name
    private String email; //stores email
    private String password; //stores password
    private int phoneNo; //stores phone number
    private boolean activation;
    
    public Users() { //Constructs a new customer class
    }
    
    public int getID() { //fetches cutomer ID
        return this.userID;
    }
    
    public void setID(int id) { //changes cutomer ID to a new value
        this.userID = id;
    }
    
    public String getFName() { //fetches cutomer name
        return this.fName;
    }
    
    public void setFName(String n) { //changes cutomer name to a new value
        this.fName = n;
    }
    
    public String getLName() { //fetches cutomer name
        return this.lName;
    }
    
    public void setLName(String n) { //changes cutomer name to a new value
        this.lName = n;
    }
    
    public String getEmail() { //fetches cutomer email
        return this.email;
    }
    
    public void setEmail(String n) { //changes cutomer email to a new value
        this.email = n;
    }
    
    public String getPass() { //fetches cutomer password
        return this.password;
    }
    
    public void setPass(String n) { //changes cutomer password to a new value
        this.password = n;
    }
    
    public int getPhone() { //fetches cutomer phone number
        return this.phoneNo;
    }
    
    public void setPhone(int n) { //changes cutomer number to a new value
        this.phoneNo = n;
    }
    
    public boolean isActive() {
        return this.activation;
    }
    
    public void toggleActive(boolean b) {
        this.activation = b;
    }
}
