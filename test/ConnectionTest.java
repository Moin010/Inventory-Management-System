/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author immah
 */
public class ConnectionTest {
      Connection con;  
    @Before
    public void setUp() {
        
           
    }
    
    @Test
    public void ConnectTest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/takeabite?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", "root", "root");
            assertEquals("true", "true");
        } catch (Exception e) {
            assertEquals("Connection Failed", "Failed");
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
