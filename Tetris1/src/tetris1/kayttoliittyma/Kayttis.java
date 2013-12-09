package tetris1.kayttoliittyma;

import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

import tetris1.logiikka.Logiikka;
import tetris1.logiikka.Palikka.Tetrominot;

/**
 * Kauttis-luokka toimii tetriksen kauttoliittymänä PelinAlustus-luokan kanssa.
 * <p> Ohjelmoinnin harjoitustyö, periodi II, syksy 2013. <p>
 *
 * @author JK.
 */
public class Kayttis extends JPanel implements ActionListener {

    /**
     * statusbaria käytetään, jotta peliruutuun voitaisiin lisätä tekstiä.
     */
    JLabel statusbar;
    /**
     * Olio, joka mahdollistaa pelitapahtumien tapahtumisen oikeaan aikaan.
     */
    Timer ajastin;
    /**
     * Logiikka-olio, jota käytetään tämän luokan metodien apuna.
     */
    Logiikka log;

    /**
     * Kauttis-luokan konstruktori.
     *
     */
    public Kayttis(PelinAlustus a) {

        this.log = new Logiikka(this);

        setFocusable(true);
        ajastin = new Timer(200, this);

        ajastin.start();
        statusbar = a.getStatusBar();

        addKeyListener(new TAdapter());
        log.tyhjennaRuutu();

    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public Timer getAjastin() {
        return ajastin;
    }

    public Logiikka getLogiikka(){
        return this.log;
    }


    /**
     * Metodi aloittaa pelin tyhjentämällä ruudun, luomalla uuden palan ja
     * starttaamalla ajastimen.
     *
     */
    public void starttaa() {

        if (log.getOnkoPaussilla()) {
            return;
        }

        log.setOnkoAlkanut(true);
        log.setOnkoPudonnut(false);

        log.tyhjennaRuutu();

        log.uusiPala();

        ajastin.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (log.getOnkoPudonnut()) {
            log.setOnkoPudonnut(false);
            log.uusiPala();

        } else {
            log.pykalaAlas();
        }
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
        int huippu = (int) koko.getHeight() - log.getRuudunKorkeus() * nelionKorkeus();

        for (int i = 0; i < log.getRuudunKorkeus(); ++i) {
            for (int j = 0; j < log.getRuudunLeveys(); ++j) {
                Tetrominot palanmuoto = log.tetrominonMuoto(j, log.getRuudunKorkeus() - i - 1);
                if (palanmuoto != Tetrominot.EiMuotoa) {
                    piirraNelio(g, 0 + j * nelionLeveys(),
                            huippu + i * nelionKorkeus(), palanmuoto);
                }
            }
        }

        if (log.getPala().getMuoto() != Tetrominot.EiMuotoa) {
            for (int i = 0; i < 4; ++i) {
                int x = log.getNykyinenX() + log.getPala().x(i);
                int y = log.getNykyinenY() - log.getPala().y(i);
                piirraNelio(g, 0 + x * nelionLeveys(),
                        huippu + (log.getRuudunKorkeus() - y - 1) * nelionKorkeus(), log.getPala().getMuoto());
            }
        }
    }

    /**
     * Metodi piirtää neliön.
     *
     * @param g Graphics-olio, jota käytetään piirtämiseen.
     * @param x kokonaisluku, joka vastaa piirrettävän neliön leveyttä.
     * @param y kertoo piirrettävän neliön korkeuden.
     * @param muoto Tetrominon muoto; tarvitaan, jotta kaikista samanlaisista
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

    public int nelionLeveys() {
        return (int) getSize().getWidth() / log.getRuudunLeveys();
    }

    public int nelionKorkeus() {
        return (int) getSize().getHeight() / log.getRuudunKorkeus();
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int koodi = e.getKeyCode();

            if (koodi == 'p' || koodi == 'P') {
                log.paussaa();
                return;
            }

            if (log.getOnkoPaussilla()) {
                return;
            }
            switch (koodi) {
                case KeyEvent.VK_LEFT:
                    log.voikoLiikuttaa(log.getPala(), log.getNykyinenX() - 1, log.getNykyinenY());
                    break;
                case KeyEvent.VK_RIGHT:
                    log.voikoLiikuttaa(log.getPala(), log.getNykyinenX() + 1, log.getNykyinenY());
                    break;
                case KeyEvent.VK_DOWN:
                    log.akkiPudotus();
                    break;
                case KeyEvent.VK_UP:
                    log.voikoLiikuttaa(log.getPala().kaannaVasemmalle(), log.getNykyinenX(), log.getNykyinenY());
                    break;
                case KeyEvent.VK_SPACE:

                    break;

                case 'a':
                    log.pykalaAlas();
                    break;
                case 'A':
                    log.pykalaAlas();
                    break;
                    case 's':
                    starttaa();
                    log.nollaaRivitJaPisteet();
                    statusbar.setText("Rivejä poistettu: " + String.valueOf(log.rivejaPoistettu)+ "    Pisteet: " + String.valueOf(log.pisteet) );
                    break;
                case 'S':
                    starttaa();
                     log.nollaaRivitJaPisteet();
                     statusbar.setText("Rivejä poistettu: " + String.valueOf(log.rivejaPoistettu)+ "    Pisteet: " + String.valueOf(log.pisteet));
                    break;
                    
            }
        }
    }
}
