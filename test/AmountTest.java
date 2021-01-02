/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.amount;
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
public class AmountTest {
    amount am;
    @Before
    public void setUp() {
        am = new amount("7000","29/09/2020","10000","book");
    }
    
    @Test
    public void amountTest() {
        assertEquals("7000",am.getAmount());
    }
    @Test
    public void dateTest() {
        assertEquals("29/09/2020",am.getDate());
    }

    @Test
    public void totalTest() {
        assertEquals("10000",am.getTotal());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
