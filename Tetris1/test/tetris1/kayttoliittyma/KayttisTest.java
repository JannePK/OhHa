/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1.kayttoliittyma;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris1.logiikka.Logiikka;
import tetris1.logiikka.Palikka;

/**
 *
 * @author Janne
 */
public class KayttisTest {
    Kayttis kayttis;
    Logiikka log;
    PelinAlustus alustus;
    public KayttisTest() {
        
        alustus = new PelinAlustus();
  kayttis = new Kayttis(alustus);
  log = new Logiikka(kayttis);
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

int leveys = kayttis.nelionLeveys();
    assertEquals(0, leveys );
}
      @Test
public void nelionKorkeusTest() {

int leveys = kayttis.nelionKorkeus();
    assertEquals(0, leveys );
}
      
         @Test
public void starttiTest() {

kayttis.starttaa();
boolean alkanut = log.onkoAlkanut;
    assertEquals(false, alkanut );
}     
    @Test
public void starttiTest2() {

kayttis.starttaa();
boolean alkanut = log.onkoPudonnut;
    assertEquals(false, alkanut );
}     
     @Test
public void starttiTest3() {

kayttis.starttaa();
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

log.uusiPala();
    assertEquals(log.voikoLiikuttaa(log.pala, 1, 1), log.onkoAlkanut );
   
}
}
