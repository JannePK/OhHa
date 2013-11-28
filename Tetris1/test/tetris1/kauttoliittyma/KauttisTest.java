/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1.kauttoliittyma;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris1.kauttoliittyma.Kauttis;
import tetris1.kauttoliittyma.PelinAlustus;
import tetris1.logiikka.Logiikka;
import tetris1.logiikka.Palikka;

/**
 *
 * @author Janne
 */
public class KauttisTest {
    Kauttis kauttis;
    Logiikka log;
    PelinAlustus alustus;
    public KauttisTest() {
        log = new Logiikka();
        alustus = new PelinAlustus();
  kauttis = new Kauttis(log, alustus);
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
        @Test
public void nelionLeveysTest() {

int leveys = kauttis.nelionLeveys();
    assertEquals(0, leveys );
}
      @Test
public void nelionKorkeusTest() {

int leveys = kauttis.nelionKorkeus();
    assertEquals(0, leveys );
}
      
         @Test
public void starttiTest() {

kauttis.starttaa();
boolean alkanut = log.onkoAlkanut;
    assertEquals(false, alkanut );
}     
    @Test
public void starttiTest2() {

kauttis.starttaa();
boolean alkanut = log.onkoPudonnut;
    assertEquals(false, alkanut );
}     
     @Test
public void starttiTest3() {

kauttis.starttaa();
Palikka.Tetrominot[] muodot = log.muodot;
Palikka.Tetrominot[] muodot2 = new Palikka.Tetrominot[log.RuudunKorkeus*log.RuudunLeveys];
for (int i = 0; i < log.RuudunKorkeus * log.RuudunLeveys; ++i) {
            muodot2[i] = Palikka.Tetrominot.EiMuotoa;
        }

    assertEquals(muodot2, muodot );
}
     
            @Test
public void uusiPalaTest() {

log.onkoAlkanut = true;

kauttis.uusiPala();
    assertEquals(log.voikoLiikuttaa(log.pala, 1, 1), log.onkoAlkanut );
   
}
}
