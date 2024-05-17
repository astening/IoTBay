/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel;

/**
 *
 * @author anna
 */
public class User {
    private String name;
    private String email;
    private String password;
    private String position;
    //roles are "Staff", "Customer", and "Unregistered?

    public User(String name, String email, String password, String position) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPosition(){
        return this.position;
    }

    
        

}
