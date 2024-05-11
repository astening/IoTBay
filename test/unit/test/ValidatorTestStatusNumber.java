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
    
    // test validateStatus() functionality
    
}
