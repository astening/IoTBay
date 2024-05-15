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

public class ValidatorTestCheckEmpty {
    
    private final Validator validator;

    public ValidatorTestCheckEmpty() {
        validator = new Validator();
    }

    // test checkEmpty() functionality
    
    @Test
    public void testCheckEmptyNumberEmpty() { // number is correctly identified as empty
        // this isnt woring
        boolean res = validator.checkEmpty(0, "New");
        assertEquals(true,res) ;
    }
    
    @Test
    public void testCheckEmptyStatusEmpty() { // status is correctly identified as empty
        boolean res = validator.checkEmpty(1, "");
        assertEquals(true,res) ;
    }
    
    @Test
    public void testCheckEmptyStatusBothEmpty() { // both correctly identified as empty
        boolean res = validator.checkEmpty(0, "") ;
        assertEquals(true,res) ;
    }
    
    @Test
    public void testCheckEmptyNoneEmpty() { // both identified as not empty
        boolean res = validator.checkEmpty(1, "Complete") ;
        assertEquals(false,res) ;
    }
    
    @Test
    public void testCheckEmptyNull() { // both identified as empty ie null
        boolean res = validator.checkEmpty(0, null) ;
        assertEquals(true,res) ;
    }

}

