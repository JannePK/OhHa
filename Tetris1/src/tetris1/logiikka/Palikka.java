package tetris1.logiikka;

import java.util.Random;
import java.lang.Math;

/**
 * Palikka-luokka mahdollistaa palikka-olioiden luonnin ja sisältää hyödyllisiä
 * metodeja. <p> Ohjelmoinnin harjoitustyö, periodi II, syksy 2013. <p>
 *
 * @author JK.
 */
public class Palikka {

    /**
     * Tetrominojen kaikki mahdolliset muodot.
     */
    public enum Tetrominot {

        EiMuotoa, ZMuoto, SMuoto, SuoraMuoto,
        TMuoto, NelioMuoto, LMuoto, ToinenLMuoto
    };
    /**
     * Tetrominon muoto, esimerkiksi nelioMuoto tai TMuoto.
     */
    private Tetrominot palanMuoto;
    /**
     * Palojen sijainti määritetään näiden koordinaattien avulla.
     */
    private int koordinaatit[][];
    /**
     * Taulukko, jossa on lueteltu eri koordinaatit.
     */
    private int[][][] koordinaattiTaulu;

    /**
     * Palikan konstruktori, asettaa palikka-oliolle koordinaatit ja muodon
     * Tetrominot.EiMuotoa.
     *
     */
    public Palikka() {

        koordinaatit = new int[4][2];
        asetaMuoto(Tetrominot.EiMuotoa);

    }

    /**
     * Asettaa palikkaoliolle halutun muodon.
     *
     * @param muoto se muoto, jonka metodi asettaa palikalle.
     */
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

    /**
     * Asettaa palikka-oliolle satunnaisesti valitun muodon käyttäen
     * asetaMuoto-metodia.
     *
     */
    public void asetaSatunnaismuoto() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominot[] arvot = Tetrominot.values();
        asetaMuoto(arvot[x]);
    }

    /**
     * Metodi palauttaa x-koordinaattien pienimmän arvon.
     *
     * @return pienin x-koordinaattien arvo.
     */
    public int minX() {
        int m = koordinaatit[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, koordinaatit[i][0]);
        }
        return m;
    }

    /**
     * Metodi palauttaa y-koordinaattien pienimmän arvon.
     *
     * @return pienin y-koordinaattien arvo.
     */
    public int minY() {
        int m = koordinaatit[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, koordinaatit[i][1]);
        }
        return m;
    }

    /**
     * Metodi muuttaa palikka-olion koordinaatteja niin, että se kääntyy
     * vasemmalle. Neliön muotoisia palikoita ei tarvitse kääntää.
     *
     * @return uusi käännetty palikka.
     */
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

    /**
     * Metodi muuttaa palikka-olion koordinaatteja niin, että se kääntyy
     * oikealle. Neliön muotoisia palikoita ei tarvitse kääntää.
     *
     * @return uusi käännetty palikka.
     */
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

    public int[][] getKoordinaatit() {

        return koordinaatit;
    }
}
