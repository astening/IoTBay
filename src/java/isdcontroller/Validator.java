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
   private String cardNoPattern = "([0-9]{4}+[\\s]+[0-9]{4}+[\\s]+[0-9]{4}+[\\s]+[0-9]{4})";
   private String expiryDatePattern = "(0[1-9]|1[0-2])/(2[4-9])";
   private String cvvPattern = "[0-9]{3}";
              
   public Validator(){    }       

/*Validates inputs against regex pattern*/
   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       
      return match.matches(); 
   }       

/*Validation methods to check for empty inputs and check inputs against regexes*/
   public boolean checkEmpty(String email, String password){       

      return  email.isEmpty() || password.isEmpty();   

   }

   
   public boolean validateEmail(String email){                       

      return validate(emailPattern,email);   

   }

      public boolean validateName(String name){
      return validate(namePattern,name); 
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

      return validate(namePattern, position); 

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

   /*Method to reset the validation session to default attributes and values*/
    void clear(HttpSession session) {
        session.setAttribute("emailErr", "Enter Email:");
        session.setAttribute("passErr", "Enter Password:");
        session.setAttribute("existErr", "");
        session.setAttribute("fnameErr", "Enter First Name:");
        session.setAttribute("lnameErr", "Enter Last Name:");
        session.setAttribute("postErr", "Enter Postcode:");
        session.setAttribute("cityErr", "Enter City:");
        session.setAttribute("stateErr", "Enter State:");
        session.setAttribute("positionErr", "Enter Position:");
        session.setAttribute("addressErr", "Enter Address:");
        session.setAttribute("phoneErr", "Enter Phone Number:");
        session.setAttribute("cardNoErr", null);
        session.setAttribute("expiryDateErr", null);
        session.setAttribute("cvvErr", null);
    }
}