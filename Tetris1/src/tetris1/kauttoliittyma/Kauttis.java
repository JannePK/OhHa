/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1.kauttoliittyma;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import tetris1.logiikka.Logiikka;
import tetris1.logiikka.Palikka.Tetrominot;



public class Kauttis extends JPanel implements ActionListener {

    JLabel statusbar;
    Timer ajastin;
    Logiikka log;

    /**
     * Peli-luokan konstruktori.
     *
     */
    public Kauttis(Logiikka logiikka, PelinAlustus a) {

        this.log = logiikka;

        setFocusable(true);
        ajastin = new Timer(200, this);

        ajastin.start();
        statusbar = a.getStatusBar();

        addKeyListener(new TAdapter());
        log.tyhjennaRuutu();

    }

    public void uusiPala() {
        log.getPala().asetaSatunnaismuoto();
        log.setNykyinenX(log.getRuudunLeveys() / 2 + 1);
        log.setNykyinenY(log.getRuudunKorkeus() - 1 + log.getPala().minY());

        if (!log.voikoLiikuttaa(log.getPala(), log.getNykyinenX(), log.getNykyinenY())) {
            repaint();
            log.getPala().asetaMuoto(Tetrominot.EiMuotoa);

            ajastin.stop();
            log.setOnkoAlkanut(false);

        }
    }

    public void pykalaAlas() {
        if (!log.voikoLiikuttaa(log.getPala(), log.getNykyinenX(), log.getNykyinenY() - 1)) {

            log.pudonnutPala();
            if (!log.getOnkoPudonnut()) {
                uusiPala();
            }
        }
        repaint();
    }

    /**
     * Metodi aloittaa pelin tyhjentämällä ruudun, luomalla uuden palan ja
     * starttaamalla ajastimen.
     *
     */
    public void starttaa() {

        log.setOnkoAlkanut(true);
        log.setOnkoPudonnut(false);

        log.tyhjennaRuutu();

        uusiPala();

        ajastin.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (log.getOnkoPudonnut()) {
            log.setOnkoPudonnut(false);
            uusiPala();
            if (!log.voikoLiikuttaa(log.getPala(), log.getNykyinenX(), log.getNykyinenX())) {
                repaint();
                ajastin.stop();

            }

        } else {
            pykalaAlas();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension koko = getSize();
        int huippu = (int) koko.getHeight() - log.getRuudunKorkeus() * nelionKorkeus();

        for (int i = 0; i < log.getRuudunKorkeus(); ++i) {
            for (int j = 0; j < log.getRuudunLeveys(); ++j) {
                Tetrominot shape = log.tetrominonMuoto(j, log.getRuudunKorkeus() - i - 1);
                if (shape != Tetrominot.EiMuotoa) {
                    piirraNelio(g, 0 + j * nelionLeveys(),
                            huippu + i * nelionKorkeus(), shape);
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

            }

        }
    }
}