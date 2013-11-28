package tetris1.logiikka;





import tetris1.logiikka.Palikka.Tetrominot;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class Logiikka {

    public final int RuudunLeveys = 25;
    public final int RuudunKorkeus = 25;
    public Palikka pala;
    int nykyinenX = 0;
    int nykyinenY = 0;
    public boolean onkoPudonnut = false;
    public boolean onkoAlkanut = false;
    public Tetrominot[] muodot;

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

    public void pudonnutPala() {
        for (int i = 0; i < 4; ++i) {
            int x = getNykyinenX() + getPala().x(i);
            int y = getNykyinenY() - getPala().y(i);
            muodot[(y * RuudunLeveys) + x] = getPala().getMuoto();
        }

        if (!getOnkoPudonnut()) {
            
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

        
        return true;
    }

    public void akkiPudotus() {
        int uusiY = nykyinenY;
        while (uusiY > 0) {
            if (!voikoLiikuttaa(pala, nykyinenX, uusiY - 1)) {
                break;
            }
            --uusiY;
        }
        
    }

    public void tyhjennaRuutu() {
        for (int i = 0; i < RuudunKorkeus * RuudunLeveys; ++i) {
            muodot[i] = Tetrominot.EiMuotoa;
        }
    }

    public Tetrominot tetrominonMuoto(int x, int y) {
        return muodot[(y * RuudunLeveys) + x];
    }
}
