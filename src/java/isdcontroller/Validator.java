/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

/**
 *
 * @author gubbs
 */

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.servlet.http.HttpSession;
        

public class Validator implements Serializable {
    
    private String cardNoPattern = "([0-9]+[\\s]+[0-9]+[\\s]+[0-9]+[\\s]+[0-9])";
    private String expiryDatePattern = "([0-9)";
    private String cvvPattern = "([0-9])";
    
    public Validator() {
    }
    
    public boolean validate(String pattern, String input){
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);
        return match.matches();
    }
    
    public boolean validateCardNo(){
        
    }
}
