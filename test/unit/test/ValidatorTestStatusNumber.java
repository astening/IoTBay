/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;

/**
 *
 * @author chari
 */

import junit.framework.* ;
import org.junit.Test ;
import static org.junit.Assert.* ;
import isdcontroller.Validator ;

public class ValidatorTestStatusNumber {
    
    private final Validator validator;

    public ValidatorTestStatusNumber() {
        validator = new Validator();
    }

    // test validateStatus() functionality
    
    @Test
    public void testValidateStatusValid() { // status is valid
        boolean res = validator.validateStatus("New");
        assertEquals(true,res) ;
    }
    
    @Test
    public void testValidateStatusInvalid() { // status is invalid
        boolean res = validator.validateStatus("New2");
        assertEquals(false,res) ;
    }
    
    @Test
    public void testValidateStatusInvalidSpace() { // status is invalid
        boolean res = validator.validateStatus("New order");
        assertEquals(false,res) ;
    }
    
    // test validateNumber() functionality
    
    @Test
    public void testValidateNumberValid() {
        boolean res = validator.validateNumber("1") ; // number is valid
        assertEquals(true, res) ;
    }
    
    @Test
    public void testValidateNumberValidMultiple() {
        boolean res = validator.validateNumber("13") ; // double digit number is valid
        assertEquals(true, res) ;
    }
    
    @Test
    public void testValidateNumberInvalidLetter() {
        boolean res = validator.validateNumber("1a") ; // letter is invalid
        assertEquals(false, res) ;
    }
    
    @Test
    public void testValidateNumberInvalidCharacter() {
        boolean res = validator.validateNumber("12!") ; // character is invalid
        assertEquals(false, res) ;
    }
    
    @Test
    public void testValidateNumberInvalidNegative() {
        boolean res = validator.validateNumber("-2") ; // negative number is invalid
        assertEquals(false, res) ;
    }
    
}
