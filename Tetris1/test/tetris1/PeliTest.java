/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris1.Palikka.Tetrominot;

/**
 *
 * @author Janne
 */
public class PeliTest {
    Peli peli;
    
    public PeliTest() {
        
        peli = new Peli();
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
public void voikohanLiikuttaa() {
    Palikka pala = new Palikka();

    assertEquals(true, peli.voikoLiikuttaa(pala, 1,2) );
}
    @Test
public void nelionLeveysTest() {

int leveys = peli.nelionLeveys();
    assertEquals(0, leveys );
}
      @Test
public void nelionKorkeusTest() {

int leveys = peli.nelionKorkeus();
    assertEquals(0, leveys );
}

   @Test
public void starttiTest() {

peli.starttaa();
boolean alkanut = peli.onkoAlkanut;
    assertEquals(true, alkanut );
}     
    @Test
public void starttiTest2() {

peli.starttaa();
boolean alkanut = peli.onkoPudonnut;
    assertEquals(false, alkanut );
}     
     @Test
public void starttiTest3() {

peli.starttaa();
Tetrominot[] muodot = peli.muodot;
Tetrominot[] muodot2 = new Tetrominot[peli.RuudunKorkeus*peli.RuudunLeveys];
for (int i = 0; i < peli.RuudunKorkeus * peli.RuudunLeveys; ++i) {
            muodot2[i] = Tetrominot.EiMuotoa;
        }

    assertEquals(muodot2, muodot );
}  
    
   
    
}
