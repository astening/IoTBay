/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunnerValidatorStatusNumber {

    public static void main(String[] args) {
        
    System.out.println("Testing Status and Number Checker: ");
    Result result = JUnitCore.runClasses(ValidatorTestStatusNumber.class);

    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }

    String status = result.wasSuccessful() ? "Passed" : "Failed";
    
    System.out.println(" Test status = " + status);
    System.out.println(" Number of Tests Passed = "+ result.getRunCount());
    System.out.println(" Number of Tests Ignored = "+ result.getIgnoreCount());
    System.out.println(" Number of Tests Failed = "+ result.getFailureCount());
    
  }
    
}
