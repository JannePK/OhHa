package tetris1.logiikka;

import tetris1.logiikka.Palikka.Tetrominot;

/**
 * Logiikka-luokka sisältää tetriksen pelilogiikan. <p> Ohjelmoinnin
 * harjoitustyö, periodi II, syksy 2013. <p>
 *
 * @author JK.
 */
public class Logiikka {

    /**
     * Luku, joka kertoo peliruudun leveyden.
     */
    public final int RuudunLeveys = 25;
    /**
     * Luku, joka kertoo peliruudun korkeuden.
     */
    public final int RuudunKorkeus = 25;
    /**
     * Palikkaolio jota käytetään pelissä.
     */
    public Palikka pala;
    /**
     * Palikan tämänhetkinen x-koordinaatti.
     */
    int nykyinenX = 0;
    /**
     * Palikan tämänhetkinen x-koordinaatti.
     */
    int nykyinenY = 0;
    /**
     * Kertoo, onko palikka pudonnut vai ei.
     */
    public boolean onkoPudonnut = false;
    /**
     * Kertoo, onko peli alkanut.
     */
    public boolean onkoAlkanut = false;
    /**
     * Tetrominot joita käytetään pelissä.
     */
    public Tetrominot[] muodot;

    /**
     * Logiikka-luokan konstruktori.
     *
     */
    public Logiikka() {

        pala = new Palikka();

        muodot = new Tetrominot[RuudunLeveys * RuudunKorkeus];

    }

    public Tetrominot[] getTetrominot() {
        return muodot;
    }

    public Palikka getPala() {
        return pala;
    }

    public int getNykyinenX() {
        return nykyinenX;
    }

    public int getNykyinenY() {
        return nykyinenY;
    }

    public void setNykyinenX(int x) {
        nykyinenX = x;
    }

    public void setNykyinenY(int y) {
        nykyinenY = y;
    }

    public int getRuudunLeveys() {
        return RuudunLeveys;
    }

    public int getRuudunKorkeus() {
        return RuudunKorkeus;
    }

    public boolean getOnkoPudonnut() {
        return onkoPudonnut;
    }

    public boolean getOnkoAlkanut() {
        return onkoAlkanut;
    }

    public void setOnkoPudonnut(boolean onko) {
        boolean onkoPudonnut = onko;
    }

    public void setOnkoAlkanut(boolean onko) {
        boolean onkoAlkanut = onko;
    }

    /**
     * Metodi asettaa putoavat palaset muodot-taulukkoon, jotteivat ne katoaisi
     * ruudulta.
     *
     */
    public void pudonnutPala() {
        for (int i = 0; i < 4; ++i) {
            int x = getNykyinenX() + getPala().x(i);
            int y = getNykyinenY() - getPala().y(i);
            muodot[(y * RuudunLeveys) + x] = getPala().getMuoto();
        }

    }

    /**
     * Kertoo voiko palikkaa liikuttaa.
     *
     * @param uusiPala palikka-olio, jonka tilaa metodin halutaan tutkivan.
     * @param uusiX kuinka paljon palikkaa halutaan liikuttaa leveyssuunnassa.
     * @param uusiY kuinka paljon palikkaa halutaan liikuttaa pituussuunnassa.
     * @return false, jos palan liikuttaminen johtaisi ruudun reunoihin tai
     * toiseen palikkaan osumiseen, muuten true.
     */
    public boolean voikoLiikuttaa(Palikka uusiPala, int uusiX, int uusiY) {
        for (int i = 0; i < 4; ++i) {
            int x = uusiX + uusiPala.x(i);
            int y = uusiY - uusiPala.y(i);
            if (x < 0 || x >= RuudunLeveys || y < 0 || y >= RuudunKorkeus) {
                return false;
            }
            if (tetrominonMuoto(x, y) != Tetrominot.EiMuotoa) {
                return false;
            }
        }

        pala = uusiPala;
        nykyinenX = uusiX;
        nykyinenY = uusiY;

        return true;
    }

    /**
     * Metodi laskee palikan niin alas peliruudulla kuin mahdollista, ilman että
     * se menee toisen palikan sisälle.
     *
     */
    public void akkiPudotus() {
        int uusiY = nykyinenY;
        while (uusiY > 0) {
            if (!voikoLiikuttaa(pala, nykyinenX, uusiY - 1)) {
                break;
            }
            --uusiY;
        }
    }
    /**
     * Metodi tyhjentää peliruudun, eli asettaa kaikki muodot
     * EiMuotoa-muodoiksi.
     *
     */
    public void tyhjennaRuutu() {
        for (int i = 0; i < RuudunKorkeus * RuudunLeveys; ++i) {
            muodot[i] = Tetrominot.EiMuotoa;
        }
    }

    public Tetrominot tetrominonMuoto(int x, int y) {
        return muodot[(y * RuudunLeveys) + x];
    }
}
