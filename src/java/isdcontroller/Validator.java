/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

/**
 *
 * @author chari
 */



import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


   public class Validator implements Serializable{ 

 
//   private final String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
   private final String statusPattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
//   private final String passwordPattern = "[a-z0-9]{4,}";
   private final String orderIDPattern = "[0-9]" ;
              
   public Validator(){    }       


   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       

      return match.matches(); 

   }       

 
   // notifies user if values aren't filled
   public boolean checkEmpty(int orderID, String status){  

      return  status.isEmpty() || orderID==0  ; // instead of isEmpty, ==0

   }

   // check status format
   public boolean validateStatus(String status){                       

      return validate(statusPattern,status);   

   }
   
   // check orderID format tbc
       
   public boolean validateName(String name){

      return validate(orderIDPattern,name); 

   } 
   
   // need to update the error names here and add to respective views
   public void clear(HttpSession session) {
       session.setAttribute("emailErr", "Enter email") ;
       session.setAttribute("passErr", "Enter password") ;
       session.setAttribute("existErr", "") ;
       session.setAttribute("nameErr", "Enter name") ;
   }
        
}

