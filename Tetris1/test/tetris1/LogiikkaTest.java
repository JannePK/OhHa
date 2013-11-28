/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1;

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
import tetris1.logiikka.Palikka.Tetrominot;

/**
 *
 * @author Janne
 */
public class LogiikkaTest {
    Logiikka log;
    
    public LogiikkaTest() {
        
        log = new Logiikka();
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        log = new Logiikka();
        
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

 boolean b2 = log.voikoLiikuttaa(p, 1, 1);
  
 assertEquals(p, log.pala );
}  
   
      
    
}
