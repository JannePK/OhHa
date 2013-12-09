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
    PelinAlustus alustus;

    public KayttisTest() {

        alustus = new PelinAlustus();
        kayttis = new Kayttis(alustus);

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
        assertEquals(0, leveys);
    }

    @Test
    public void nelionKorkeusTest() {

        int leveys = kayttis.nelionKorkeus();
        assertEquals(0, leveys);
    }

    @Test
    public void starttiTest() {

        kayttis.starttaa();
        boolean alkanut = kayttis.getLogiikka().onkoAlkanut;
        assertEquals(false, alkanut);
    }

    @Test
    public void starttiTest2() {

        kayttis.starttaa();
        boolean alkanut = kayttis.getLogiikka().onkoPudonnut;
        assertEquals(false, alkanut);
    }

    @Test
    public void starttiTest3() {

        kayttis.starttaa();
        Palikka.Tetrominot[] muodot = kayttis.getLogiikka().muodot;
        Palikka.Tetrominot[] muodot2 = new Palikka.Tetrominot[kayttis.getLogiikka().RuudunKorkeus * kayttis.getLogiikka().RuudunLeveys];
        for (int i = 0; i < kayttis.getLogiikka().RuudunKorkeus * kayttis.getLogiikka().RuudunLeveys; ++i) {
            muodot2[i] = Palikka.Tetrominot.EiMuotoa;
        }

        assertEquals(muodot2, muodot);
    }
}
