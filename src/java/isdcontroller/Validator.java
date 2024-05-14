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
import java.util.Date;
        

public class Validator implements Serializable {
    
    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
    private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
    private String passwordPattern = "[a-z0-9]{4,}";
    private String cardNoPattern = "([0-9]{4}+[\\s]+[0-9]{4}+[\\s]+[0-9]{4}+[\\s]+[0-9]{4})";
    private String expiryDatePattern = "([0-9])";
    private String cvvPattern = "([0-9])";
    
    public Validator() {
    }
    
    public boolean validate(String pattern, String input){
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);
        return match.matches();
    }
    
    public boolean validateCardNo(String cardNo){
        return validate(cardNoPattern,cardNo);
    }
    
    public boolean validateExpiryDate(String expiryDate){
        return validate(expiryDatePattern,expiryDate);
    }
    
    public boolean validateCVV(String cvv){
        return validate(cvvPattern, cvv);
    }
    
   public boolean checkEmpty(String email, String password){       
      return  email.isEmpty() || password.isEmpty();   
   }

   
   public boolean validateEmail(String email){                       
      return validate(emailPattern,email);   
   }

       
   public boolean validateName(String name){
      return validate(namePattern,name); 
   }       
   

   public boolean validatePassword(String password){
      return validate(passwordPattern,password); 
   }          
    
   public void clear(HttpSession session){
       session.setAttribute("emailErr", "Enter email");
       session.setAttribute("passErr", "Enter password");
       session.setAttribute("existErr", "");
       session.setAttribute("nameErr", null);
       session.setAttribute("cardNoErr", null);
       session.setAttribute("expiryDateErr", null);
       session.setAttribute("cvvErr", null);
       
       //note: dont put gaps between lines^^ will break
   }
}
