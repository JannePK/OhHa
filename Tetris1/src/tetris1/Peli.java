package tetris1;

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

import tetris1.Palikka.Tetrominot;

public class Peli extends JPanel implements ActionListener {

    final int RuudunLeveys = 25;
    final int RuudunKorkeus = 25;
    Timer ajastin;
    boolean onkoPudonnut = false;
    boolean onkoAlkanut = false;
    int nykyinenX = 0;
    int nykyinenY = 0;
    JLabel statusbar;
    Palikka pala;
    Tetrominot[] muodot;

    public Peli(PelinAlustus pa) {

        setFocusable(true);
        pala = new Palikka();
        ajastin = new Timer(200, this);
        ajastin.start();

        statusbar = pa.getStatusBar();
        muodot = new Tetrominot[RuudunLeveys * RuudunKorkeus];
        addKeyListener(new TAdapter());
        tyhjennaRuutu();
    }

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension koko = getSize();
        int huippu = (int) koko.getHeight() - RuudunKorkeus * nelionKorkeus();

        if (pala.getMuoto() != Tetrominot.EiMuotoa) {
            for (int i = 0; i < 4; ++i) {
                int x = nykyinenX + pala.x(i);
                int y = nykyinenY - pala.y(i);
                piirraNelio(g, 0 + x * nelionLeveys(),
                        huippu + (RuudunKorkeus - y - 1) * nelionKorkeus());
            }
        }
    }

    private void piirraNelio(Graphics g, int x, int y) {

        Color vari = new Color(0, 0, 0);

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
