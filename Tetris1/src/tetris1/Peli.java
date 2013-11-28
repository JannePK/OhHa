package tetris1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


import tetris1.Palikka.Tetrominot;

/**
 * Peli-luokka sisältää tetriksen pelilogiikan. <p> Ohjelmoinnin harjoitustyö,
 * periodi II, syksy 2013. <p>
 *
 * @author Janne Knuutinen, Helsingin yliopisto.
 */
public class Peli extends JPanel implements ActionListener {
    
    /**
     * Luku, joka kertoo peliruudun leveyden.
     */
    final int RuudunLeveys = 25;
    /**
     * Luku, joka kertoo peliruudun korkeuden.
     */
    final int RuudunKorkeus = 25;
     /**
     * Olio, joka mahdollistaa pelitapahtumien tapahtumisen oikeaan aikaan.
     */
    Timer ajastin;
     /**
     * Kertoo, onko palikka pudonnut vai ei.
     */
    boolean onkoPudonnut = false;
     /**
     * Kertoo, onko peli alkanut.
     */
    boolean onkoAlkanut = false;
     /**
     * Palikan tämänhetkinen x-koordinaatti.
     */
    int nykyinenX = 0;
    /**
     * Palikan tämänhetkinen x-koordinaatti.
     */
    int nykyinenY = 0;
    /**
     * Palikkaolio jota käytetään pelissä.
     */
    Palikka pala;
    /**
     * Tetrominot joita käytetään pelissä.
     */
    Tetrominot[] muodot;

    /**
     * Peli-luokan konstruktori.
     *
     */
    public Peli() {

        setFocusable(true);
        pala = new Palikka();
        ajastin = new Timer(200, this);
        ajastin.start();
        muodot = new Tetrominot[RuudunLeveys * RuudunKorkeus];
        addKeyListener(new TAdapter());
        tyhjennaRuutu();
    }

    /**
     * Metodi aloittaa pelin tyhjentämällä ruudun, luomalla uuden palan ja
     * starttaamalla ajastimen.
     *
     */
    public void starttaa() {

        onkoAlkanut = true;
        onkoPudonnut = false;

        tyhjennaRuutu();

        uusiPala();
        ajastin.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (onkoPudonnut) {
            onkoPudonnut = false;
            uusiPala();
        } else {
            pykalaAlas();
        }
    }

    public int nelionLeveys() {
        return (int) getSize().getWidth() / RuudunLeveys;
    }

    public int nelionKorkeus() {
        return (int) getSize().getHeight() / RuudunKorkeus;
    }

    public Tetrominot tetrominonMuoto(int x, int y) {
        return muodot[(y * RuudunLeveys) + x];
    }

    /**
     * Metodi, joka vastaa palikoiden värittämisestä.
     *
     * @param g importattu grafiikka.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension koko = getSize();
        int huippu = (int) koko.getHeight() - RuudunKorkeus * nelionKorkeus();

        for (int i = 0; i < RuudunKorkeus; ++i) {
            for (int j = 0; j < RuudunLeveys; ++j) {
                Tetrominot shape = tetrominonMuoto(j, RuudunKorkeus - i - 1);
                if (shape != Tetrominot.EiMuotoa) {
                    piirraNelio(g, 0 + j * nelionLeveys(),
                            huippu + i * nelionKorkeus(), shape);
                }
            }
        }

        if (pala.getMuoto() != Tetrominot.EiMuotoa) {
            for (int i = 0; i < 4; ++i) {
                int x = nykyinenX + pala.x(i);
                int y = nykyinenY - pala.y(i);
                piirraNelio(g, 0 + x * nelionLeveys(),
                        huippu + (RuudunKorkeus - y - 1) * nelionKorkeus(), pala.getMuoto());
            }
        }
    }

    
     /**
     * Metodi piirtää neliön.
     *
     * @param g Graphics-olio, jota käytetään piirtämiseen.
     * @param x kokonaisluku, joka vastaa piirrettävän neliön leveyttä.
     * @param y kertoo piirrettävän neliön korkeuden.
     * @param  muoto Tetrominon muoto; tarvitaan, jotta kaikista samanlaisista
     * Tetrominoista tulisi saman värisiä.
     */
    private void piirraNelio(Graphics g, int x, int y, Tetrominot muoto) {



        Color varit[] = {new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204),
            new Color(204, 204, 102), new Color(204, 102, 204),
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color vari = varit[muoto.ordinal()];

        g.setColor(vari);
        g.fillRect(x + 1, y + 1, nelionLeveys() - 2, nelionKorkeus() - 2);

        g.drawLine(x + 1, y + nelionKorkeus() - 1,
                x + nelionLeveys() - 1, y + nelionKorkeus() - 1);
        g.drawLine(x + nelionLeveys() - 1, y + nelionKorkeus() - 1,
                x + nelionLeveys() - 1, y + 1);
    }

      /**
     * Metodi laskee palikan niin alas peliruudulla kuin mahdollista,
     * ilman että se menee toisen palikan sisälle.
     *
     */
     public void akkiPudotus()
    {
        int uusiY = nykyinenY;
        while (uusiY > 0) {
            if (!voikoLiikuttaa(pala, nykyinenX, uusiY - 1))
                break;
            --uusiY;
        }
        pudonnutPala();
    }
    
    /**
     * Metodi katsoo, voidaanko palasta liikuttaa. Jos voidaan, kutsutaan 
     * pudonnutPala() -metodia.
     *
     */
    private void pykalaAlas() {
        if (!voikoLiikuttaa(pala, nykyinenX, nykyinenY - 1)) {
            pudonnutPala();
        }
    }
   /**
     * Metodi tyhjentää peliruudun, eli asettaa kaikki muodot EiMuotoa-muodoiksi. 
     *
     */
    private void tyhjennaRuutu() {
        for (int i = 0; i < RuudunKorkeus * RuudunLeveys; ++i) {
            muodot[i] = Tetrominot.EiMuotoa;
        }
    }
 /**
     * Metodi asettaa putoavat palaset muodot-taulukkoon, jotteivat ne katoaisi 
     * ruudulta. Lisäksi luodaan uusi pala jos palanen on jo ruudun alareunassa.
     *
     */
    private void pudonnutPala() {
        for (int i = 0; i < 4; ++i) {
            int x = nykyinenX + pala.x(i);
            int y = nykyinenY - pala.y(i);
            muodot[(y * RuudunLeveys) + x] = pala.getMuoto();
        }

        if (!onkoPudonnut) {
            uusiPala();
        }
    }

      /**
     * Metodi luo uuden palikan ja antaa sille uuden satunnaisen muodon. Jos 
     * uutta palaa ei voi liikuttaa, lopetetaan peli.
     *
     */
    public void uusiPala() {
        pala.asetaSatunnaismuoto();
        nykyinenX = RuudunLeveys / 2 + 1;
        nykyinenY = RuudunKorkeus - 1 + pala.minY();

        if (!voikoLiikuttaa(pala, nykyinenX, nykyinenY)) {
            pala.asetaMuoto(Tetrominot.EiMuotoa);
            ajastin.stop();
            onkoAlkanut = false;

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
        repaint();
        return true;
    }
    
     
    

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int koodi = e.getKeyCode();

            switch (koodi) {
                case KeyEvent.VK_LEFT:
                    voikoLiikuttaa(pala, nykyinenX - 1, nykyinenY);
                    break;
                case KeyEvent.VK_RIGHT:
                    voikoLiikuttaa(pala, nykyinenX + 1, nykyinenY);
                    break;
                case KeyEvent.VK_DOWN:
                    akkiPudotus();
                    break;
                case KeyEvent.VK_UP:
                    voikoLiikuttaa(pala.kaannaVasemmalle(), nykyinenX, nykyinenY);
                    break;

            }

        }
    }
}
