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

 
   private final String statusPattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
   private final String numberPattern = "[0-9]+" ;
              
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

   // checks that the number is a digit 0-9    
   public boolean validateNumber(String number){

      return validate(numberPattern,number); 

   } 
   
   // need to update the error names here and add to respective views
   public void clear(HttpSession session) {
       session.setAttribute("statusValidated", "Enter status") ;
       session.setAttribute("updated", "No change made yet") ;
       session.setAttribute("IDValidated", "Enter ID") ;
   }
        
}

