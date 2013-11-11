package tetris1;

import java.util.Random;
import java.lang.Math;

public class Palikka {

    enum Tetrominot {

        EiMuotoa, ZMuoto, SMuoto, SuoraMuoto,
        TMuoto, NelioMuoto, LMuoto, ToinenLMuoto
    };
    private Tetrominot palanMuoto;
    private int koordinaatit[][];
    private int[][][] koordinaattiTaulu;

    public Palikka() {

        koordinaatit = new int[4][2];
        asetaMuoto(Tetrominot.EiMuotoa);

    }

    public void asetaMuoto(Tetrominot muoto) {

        koordinaattiTaulu = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; ++j) {
                koordinaatit[i][j] = koordinaattiTaulu[muoto.ordinal()][i][j];
            }
        }
        palanMuoto = muoto;

    }

    public void setX(int index, int x) {
        koordinaatit[index][0] = x;
    }

    public void setY(int index, int y) {
        koordinaatit[index][1] = y;
    }

    public int x(int index) {
        return koordinaatit[index][0];
    }

    public int y(int index) {
        return koordinaatit[index][1];
    }

    public Tetrominot getMuoto() {
        return palanMuoto;
    }

    public Tetrominot getEiMuotoa() {
        return Tetrominot.EiMuotoa;
    }

    public void asetaSatunnaismuoto() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominot[] arvot = Tetrominot.values();
        asetaMuoto(arvot[x]);
    }

    public int minX() {
        int m = koordinaatit[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, koordinaatit[i][0]);
        }
        return m;
    }

    public int minY() {
        int m = koordinaatit[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, koordinaatit[i][1]);
        }
        return m;
    }

    public Palikka kaannaVasemmalle() {
        if (palanMuoto == Tetrominot.NelioMuoto) {
            return this;
        }
        Palikka tulos = new Palikka();
        tulos.palanMuoto = palanMuoto;

        for (int i = 0; i < 4; ++i) {
            tulos.setX(i, y(i));
            tulos.setY(i, -x(i));
        }
        return tulos;
    }

    public Palikka kaannaOikealle() {
        if (palanMuoto == Tetrominot.NelioMuoto) {
            return this;
        }
        Palikka pala = new Palikka();
        pala.palanMuoto = palanMuoto;

        for (int i = 0; i < 4; ++i) {
            pala.setX(i, -y(i));
            pala.setY(i, x(i));
        }
        return pala;
    }
    
    public int[][] getKoordinaatit(){
   
        return koordinaatit;
    }
}
