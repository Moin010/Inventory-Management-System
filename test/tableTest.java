/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.table;
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
public class tableTest {
    
    table t;
    @Before
    public void setUp() {
        t = new table("12","Moin","Rampura","01521436216");
    }
    
    @Test
    public void idTest() {
        assertEquals("12",t.getID());
    }
    @Test
    public void nameTest() {
        assertEquals("Moin",t.getName());
    }
    @Test
    public void addressTest() {
        assertEquals("Rampura",t.getAddress());
    }
    @Test
    public void phoneTest() {
        assertEquals("01521436216",t.getPhone());
    }
    
    @Test
    public void setphoneTest() {
        t.setPhone("015214362167");
        assertEquals("015214362167",t.getPhone());
    }
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
