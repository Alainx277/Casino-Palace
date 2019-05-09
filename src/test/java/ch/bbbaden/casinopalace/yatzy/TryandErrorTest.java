/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.casinopalace.yatzy;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author denni
 */
public class TryandErrorTest {
    
    public TryandErrorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class TryandError.
     */
    @Test
    public void testOrganizeDices() {
      //Arrange
      TryandError te = TryandError.getInstance();
      ArrayList<Dice> testAL = new ArrayList<>();
      testAL.add(new Dice());
      testAL.add(new Dice());
      int dice1;
      int dice2;
      //Act
      testAL = te.organizeDices(testAL);
      dice1 = testAL.get(0).getWert();
      dice2 = testAL.get(1).getWert();
      //Assert
      assert(dice1 > dice2);
      
    }
    
}
