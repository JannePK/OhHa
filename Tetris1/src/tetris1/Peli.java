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

    final int RuudunLeveys = 25;
    final int RuudunKorkeus = 25;
    Timer ajastin;
    boolean onkoPudonnut = false;
    boolean onkoAlkanut = false;
    int nykyinenX = 0;
    int nykyinenY = 0;
    Palikka pala;
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
     * Use {@link #doMove(int, int, int, int)} to move a piece.
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

    private void pykalaAlas() {
        if (!voikoLiikuttaa(pala, nykyinenX, nykyinenY - 1)) {
            pudonnutPala();
        }
    }

    private void tyhjennaRuutu() {
        for (int i = 0; i < RuudunKorkeus * RuudunLeveys; ++i) {
            muodot[i] = Tetrominot.EiMuotoa;
        }
    }

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

    private void uusiPala() {
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
                    voikoLiikuttaa(pala.kaannaOikealle(), nykyinenX, nykyinenY);
                    break;
                case KeyEvent.VK_UP:
                    voikoLiikuttaa(pala.kaannaVasemmalle(), nykyinenX, nykyinenY);
                    break;

            }

        }
    }
}
