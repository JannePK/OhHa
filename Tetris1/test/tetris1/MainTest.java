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
public void testaaminY() {
    Palikka pala = new Palikka();
 
    double vastaus = pala.minY();
 
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
public void PalanLMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.LMuoto);
 
    assertEquals(Tetrominot.LMuoto, pala.getMuoto() );
}

@Test
public void PalanNelioMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.NelioMuoto);
 
    assertEquals(Tetrominot.NelioMuoto, pala.getMuoto() );
}

@Test
public void PalanSMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.SMuoto);
 
    assertEquals(Tetrominot.SMuoto, pala.getMuoto() );
}

@Test
public void PalanSuoraMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.SuoraMuoto);
 
    assertEquals(Tetrominot.SuoraMuoto, pala.getMuoto() );
}

@Test
public void PalanTMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.TMuoto);
 
    assertEquals(Tetrominot.TMuoto, pala.getMuoto() );
}
    
@Test
public void PalanToinenLMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.ToinenLMuoto);
 
    assertEquals(Tetrominot.ToinenLMuoto, pala.getMuoto() );
}

@Test
public void PalanZMuotoOikea() {
    Palikka pala = new Palikka();
    pala.asetaMuoto(Tetrominot.ZMuoto);
 
    assertEquals(Tetrominot.ZMuoto, pala.getMuoto() );
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
