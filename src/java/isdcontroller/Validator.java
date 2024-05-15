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

 
   private final String statusPattern = "([A-Z][a-z]+)";       
   private final String numberPattern = "([0-9])+" ;
              
   public Validator(){    }       

   // set up validator patterns
   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       

      return match.matches(); 

   }       


   // notifies user if values aren't filled
   public boolean checkEmpty(int orderID, String status){  
      if (status!=null ) {
          if (orderID==0) {
              return true ;
          } else {
              return  status.isEmpty() ;
          }
      }
      else if (orderID==0) { // order is the default
          return true ;
      }
      else {
          return true ;
      }
   }

   // check status format
   public boolean validateStatus(String status){                       
      return validate(statusPattern,status);   

   }

   // checks that the number is a digit 0-9    
   public boolean validateNumber(String number){
       
       if (number==null) {
           return false ;
       }
       else if (number.length()==0) {
           return false ;
       }
       else if (number.equals("0")) {
           return false ;
       }
       else {
           
           return validate(numberPattern,number); 
       }

   } 
   
   // set validation messages to default
   public void clear(HttpSession session) {
       session.setAttribute("statusValidated", "Enter status") ;
       session.setAttribute("updated", "No change made yet") ;
       session.setAttribute("IDValidated", "Enter ID") ;
       session.setAttribute("quantityValidated", "Enter a number above 0") ;
   }
        
}

