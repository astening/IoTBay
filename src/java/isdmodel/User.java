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
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername(){
            return this.username;
        }
        
        public String getPassword(){
            return this.password;
        }

}
