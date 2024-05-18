/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdmodel.dao;
import java.sql.Connection;

//Super class of DAO classes that stores the database information 

public abstract class DB {   
    protected String URL = "jdbc:derby://localhost:1527/";  
    protected String db = "IoTBayDB";//name of the database   
    protected String dbuser = "app";//db root user   
    protected String dbpass = "app"; //db root password   
    protected String driver = "org.apache.derby.jdbc.ClientDriver"; //jdbc client driver - built in with NetBeans   
    protected Connection conn; //connection null-instance to be initialized in sub-classes

}