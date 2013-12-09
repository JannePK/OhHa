package tetris1.logiikka;

import tetris1.kayttoliittyma.Kayttis;
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
    public final int RuudunLeveys = 12;
    /**
     * Luku, joka kertoo peliruudun korkeuden.
     */
    public final int RuudunKorkeus = 24;
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
     * Poistettujen rivien määrä.
     */
    public int rivejaPoistettu = 0;
    /**
     * Kayttis-luokan metodeja käytetään tässäkin luokassa.
     */
    Kayttis kayttis;
    /**
     * Kertoo, onko peli pausella.
     */
    public boolean onkoPaussilla = false;
    /**
     * Pelaajan pisteet.
     */
    public int pisteet = 0;

    /**
     * Logiikka-luokan konstruktori.
     *
     */
    public Logiikka(Kayttis k) {
        this.kayttis = k;
        pala = new Palikka();

        muodot = new Tetrominot[RuudunLeveys * RuudunKorkeus];

    }

    public void nollaaRivitJaPisteet() {
        this.rivejaPoistettu = 0;
        this.pisteet = 0;
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

    public boolean getOnkoPaussilla() {
        return onkoPaussilla;
    }

    public void setOnkoPudonnut(boolean onko) {
        boolean onkoPudonnut = onko;
    }

    public void setOnkoAlkanut(boolean onko) {
        boolean onkoAlkanut = onko;
    }

    /**
     * Metodi katsoo, voidaanko palasta liikuttaa. Jos voidaan, kutsutaan
     * pudonnutPala() -metodia.
     *
     */
    public void pykalaAlas() {
        if (!voikoLiikuttaa(getPala(), getNykyinenX(), getNykyinenY() - 1)) {
            pudonnutPala();
        }
    }

    public void paussaa() {

        onkoPaussilla = !onkoPaussilla;
        if (onkoPaussilla) {
            kayttis.getAjastin().stop();
            kayttis.getStatusBar().setText("PAUSED");
        } else {
            kayttis.getAjastin().start();
            kayttis.getStatusBar().setText("Rivejä poistettu: " + String.valueOf(rivejaPoistettu) + "    Pisteet: " + String.valueOf(pisteet));
        }
        kayttis.repaint();
    }

    /**
     * Metodi luo uuden palikan ja antaa sille uuden satunnaisen muodon. Jos
     * uutta palaa ei voi liikuttaa, lopetetaan peli ja näytetään asiaankuuluva
     * viesti.
     *
     */
    public void uusiPala() {

        getPala().asetaSatunnaismuoto();
        setNykyinenX(getRuudunLeveys() / 2 + 1);
        setNykyinenY(getRuudunKorkeus() - 1 + getPala().minY());

        if (!voikoLiikuttaa(getPala(), getNykyinenX(), getNykyinenY())) {

            getPala().asetaMuoto(Tetrominot.EiMuotoa);

            kayttis.getAjastin().stop();
            setOnkoAlkanut(false);

            if (rivejaPoistettu == 0) {
                kayttis.getStatusBar().setText("Peli päättyi. Et saanut poistettua yhtään riviä. Heikko tulos.");
            } else if (rivejaPoistettu >= 1 && rivejaPoistettu < 4) {
                kayttis.getStatusBar().setText("Peli päättyi. Pisteesi: "
                        + String.valueOf(pisteet));
            } else if (rivejaPoistettu >= 4 && rivejaPoistettu < 10) {
                kayttis.getStatusBar().setText("Peli päättyi. Pisteesi: "
                        + String.valueOf(pisteet) + ". Pystyt parempaankin...");
            } else if (rivejaPoistettu >= 10 && rivejaPoistettu < 20) {
                kayttis.getStatusBar().setText("Peli päättyi. Pisteesi: "
                        + String.valueOf(pisteet) + ". Ei hassumpaa!");
            } else if (rivejaPoistettu >= 20 && rivejaPoistettu < 30) {
                kayttis.getStatusBar().setText("Peli päättyi. Pisteesi: "
                        + String.valueOf(pisteet) + ". Loistava tulos!");
            } else if (rivejaPoistettu >= 30) {
                kayttis.getStatusBar().setText("Peli päättyi. Pisteesi: "
                        + String.valueOf(pisteet) + ". All heil the Tetris God!");
            }
        }
    }

    /**
     * Metodi tutkii, löytyykö peliruudulta täysiä rivejä. Jos löytyy, rivit
     * poistetaan ja rivejaPoistettu- ja pisteet -muuttujien arvoa kasvatetaan.
     *
     */
    public void poistaTaydetRivit() {
        int taysiaRiveja = 0;

        for (int i = RuudunKorkeus - 1; i >= 0; --i) {
            boolean RiviTaysi = true;

            for (int j = 0; j < RuudunLeveys; ++j) {
                if (tetrominonMuoto(j, i) == Tetrominot.EiMuotoa) {
                    RiviTaysi = false;
                    break;
                }
            }

            if (RiviTaysi) {
                ++taysiaRiveja;
                for (int k = i; k < RuudunKorkeus - 1; ++k) {
                    for (int j = 0; j < RuudunLeveys; ++j) {
                        muodot[(k * RuudunLeveys) + j] = tetrominonMuoto(j, k + 1);
                    }
                }
            }
        }

        if (taysiaRiveja == 1) {
            rivejaPoistettu += taysiaRiveja;
            this.pisteet = this.pisteet + 10;
            kayttis.getStatusBar().setText("Rivejä poistettu: " + String.valueOf(rivejaPoistettu) + "    Pisteet: " + String.valueOf(pisteet));

            pala.asetaMuoto(Tetrominot.EiMuotoa);
            kayttis.repaint();
        } else if (taysiaRiveja == 2) {
            rivejaPoistettu += taysiaRiveja;
            this.pisteet = this.pisteet + taysiaRiveja * 15;
            kayttis.getStatusBar().setText("Rivejä poistettu: " + String.valueOf(rivejaPoistettu) + "    Pisteet: " + String.valueOf(pisteet));

            pala.asetaMuoto(Tetrominot.EiMuotoa);
            kayttis.repaint();
        } else if (taysiaRiveja > 2) {
            rivejaPoistettu += taysiaRiveja;
            this.pisteet = this.pisteet + taysiaRiveja * 20;
            kayttis.getStatusBar().setText("Rivejä poistettu: " + String.valueOf(rivejaPoistettu) + "    Pisteet: " + String.valueOf(pisteet));

            pala.asetaMuoto(Tetrominot.EiMuotoa);
            kayttis.repaint();
        }
    }

    /**
     * Metodi asettaa putoavat palaset muodot-taulukkoon, jotteivat ne katoaisi
     * ruudulta. Lisäksi se kutsuu poistaTaydetRivit-metodia.
     *
     */
    public void pudonnutPala() {
        for (int i = 0; i < 4; ++i) {
            int x = getNykyinenX() + getPala().x(i);
            int y = getNykyinenY() - getPala().y(i);
            muodot[(y * RuudunLeveys) + x] = getPala().getMuoto();
        }
        poistaTaydetRivit();

        if (!onkoPudonnut) {
            uusiPala();
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
        kayttis.repaint();
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
        pudonnutPala();
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
