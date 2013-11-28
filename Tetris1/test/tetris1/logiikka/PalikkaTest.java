/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1.logiikka;

import tetris1.logiikka.Palikka;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris1.logiikka.Palikka.Tetrominot;


public class PalikkaTest {
    Palikka palikka;
    private int koordinaatit[][];
    
    public PalikkaTest() {
        palikka = new Palikka();
        koordinaatit = new int[4][2];
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
        koordinaatit = new int[4][2];
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
public void minYTest() {
    Palikka pala = new Palikka();
   
 
    assertEquals(0, pala.minY()  );
}  
    @Test
public void minXTest() {
    Palikka pala = new Palikka();
   
 
    assertEquals(0, pala.minX()  );
}
    @Test
public void koordinaattiTest() {
    Palikka pala = new Palikka();
   
 
    assertEquals(koordinaatit, pala.getKoordinaatit()  );
}
     @Test
public void toinenKoordinaattiTest() {
    Palikka pala = new Palikka();
   pala.setX(2, 1);
koordinaatit = new int [4][2];
   koordinaatit[2][0]=1;

    assertEquals(koordinaatit, pala.getKoordinaatit()  );
} 
     @Test
public void kolmasKoordinaattiTest() {
    Palikka pala = new Palikka();
   pala.setY(2, 1);
koordinaatit = new int [4][2];
   koordinaatit[2][1]=1;

    assertEquals(koordinaatit, pala.getKoordinaatit()  );
} 
     
    
    
}
