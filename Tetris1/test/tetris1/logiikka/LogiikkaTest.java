/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1.logiikka;

import tetris1.logiikka.Palikka;
import tetris1.logiikka.Logiikka;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris1.kayttoliittyma.Kayttis;
import tetris1.kayttoliittyma.PelinAlustus;
import tetris1.logiikka.Palikka.Tetrominot;

/**
 *
 * @author Janne
 */
public class LogiikkaTest {
   PelinAlustus a;
    Kayttis k;
    Logiikka log;
    
    public LogiikkaTest() {
        a = new PelinAlustus();
        k = new Kayttis(a);
        log = new Logiikka(k);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
         a = new PelinAlustus();
        k = new Kayttis(a);
        log = new Logiikka(k);
        
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
public void voikohanLiikuttaa() {
    Palikka pala = new Palikka();

    assertEquals(false, log.voikoLiikuttaa(pala, 1,2) );
}


 
       @Test
public void voikoLiikuttaaTest() {
Palikka p = new Palikka();
p.asetaMuoto(Tetrominot.ZMuoto);

boolean b = log.voikoLiikuttaa(p, 4, 4);
    assertEquals(false, b );
   
} 
     @Test
public void voikoLiikuttaaTest2() {
Palikka p = new Palikka();


boolean b = log.voikoLiikuttaa(p, 100, 100);
    assertEquals(false, b );
 boolean b2 = log.voikoLiikuttaa(p, 1, 1);
 assertEquals(false, b2 );
}
     
         @Test
public void voikoLiikuttaaTest3() {
Palikka p = new Palikka();
  log.voikoLiikuttaa(p, 2, 3);

    assertEquals(0, log.getNykyinenX() );
   
}   
    
             @Test
public void uusiPalaTest() {

log.onkoAlkanut = true;

log.uusiPala();
    assertEquals(true , log.onkoAlkanut );
   
}  
           @Test
public void poistaTaydetRivitTest() {
log.rivejaPoistettu = 0;
log.poistaTaydetRivit();


    assertEquals(log.RuudunKorkeus , log.rivejaPoistettu );
   
}         
             
      
    
}
