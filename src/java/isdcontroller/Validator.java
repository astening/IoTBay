/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcontroller;

   import jakarta.servlet.http.HttpSession;
   import java.io.Serializable;
   import java.util.Date;
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;


   public class Validator implements Serializable{ 

 /*Creates the validation regexes */
   private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
   private String namePattern = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$";       
   private String passwordPattern = "[a-z0-9]{4,}";
   private String addressPattern = "^\\d+\\s+[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*$";
   private String postcodePattern = "^\\d{4}$";
   private String phonePattern = "^\\d{10}$";
   private String statePattern = "^[a-zA-Z]{3}$";
   private String customerPositionPattern = "^(Individual|Company)$";
              
   public Validator(){    }       

/*check input patterns against regexes*/
   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       
      return match.matches(); 
   }       

   
   public boolean validateEmail(String email){                       
      return validate(emailPattern,email);   
   }

       
   public boolean validateFName(String fName){
      return validate(namePattern,fName); 
   }
   
   public boolean validateLName(String lName){
      return validate(namePattern,lName); 
   }     
   
   public boolean validatePassword(String password){
      return validate(passwordPattern,password); 
   }          
   
   public boolean validatePhone(String phoneNo){
      return validate(phonePattern, phoneNo); 
   }   
   
    public boolean validatePostcode(String postcode){
      return validate(postcodePattern, postcode); 
   }   
   
    public boolean validateCity(String city){
      return validate(namePattern, city); 
    }
    
    public boolean validateState(String state){
      return validate(statePattern, state); 
   }   
    
   public boolean validateAddress(String address){
      return validate(addressPattern, address); 
   }   
   
   public boolean validatePosition(String position){
      return validate(customerPositionPattern, position); 
   }   

   /*reset validation for reuse*/
    void clear(HttpSession session) {
        session.setAttribute("emailError", "Email:");
        session.setAttribute("passwordError", "Password:");
        session.setAttribute("existError", "");
        session.setAttribute("fnameError", "First Name:");
        session.setAttribute("lnameError", "Last Name:");
        session.setAttribute("postError", "Postcode:");
        session.setAttribute("cityError", "City:");
        session.setAttribute("stateError", "State:");
        session.setAttribute("positionError", "Position:");
        session.setAttribute("addressError", "Address:");
        session.setAttribute("phoneError", "Phone Number:");
        session.setAttribute("positionError", "Type:");
    }
}
