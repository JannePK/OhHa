/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris1.Palikka.Tetrominot;


public class MainTest {
   
    
    Palikka palikka;
    
    
    public MainTest() {
    palikka = new Palikka();
    
    
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    palikka = new Palikka();
    }
    
    @After
    public void tearDown() {
    }

@Test
public void testaaminX() {
    Palikka pala = new Palikka();
 
    double vastaus = pala.minX();
 
    assertEquals( 0, vastaus, 0.001 );
}
@Test
public void OikeaPalanen() {
    Palikka pala = new Palikka();
    
 
    assertEquals(pala.getEiMuotoa(), pala.getMuoto() );
}
@Test
public void KaantyykoNelioOikealle() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.NelioMuoto);
 pala.kaannaOikealle();
    
    assertEquals(Tetrominot.NelioMuoto, pala.getMuoto() );
}
@Test
public void KaantyykoNelioVasemmalle() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.NelioMuoto);
 pala.kaannaVasemmalle();
    
    assertEquals(Tetrominot.NelioMuoto, pala.getMuoto() );
}

@Test
public void PalanMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.LMuoto);
 
    assertEquals(Tetrominot.LMuoto, pala.getMuoto() );
}



    
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }
}
