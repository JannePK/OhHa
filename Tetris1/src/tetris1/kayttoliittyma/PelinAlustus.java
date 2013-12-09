
package tetris1.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import tetris1.logiikka.Logiikka;

/**
 * PelinAlustus-luokka alustaa pelin ja graafisen käyttöliittymän. <p>
 * Ohjelmoinnin harjoitustyö, periodi II, syksy 2013. <p>
 *
 * @author JK.
 */
public class PelinAlustus extends JFrame {

    /**
     * statusbaria käytetään, jotta peliruutuun voitaisiin lisätä tekstiä.
     */
    JLabel statusbar;

    /**
     * PelinAlustus-luokan konstruktori, joka luo uuden Peli-olion, käyttää sen
     * startaa-metodia aloittaakseen pelin.
     *
     */
    public PelinAlustus() {
Component komponentti = null;
        JOptionPane.showMessageDialog(komponentti, "Peli alkaa! \n \nKontrollit:\nVasen ja oikea nuolinäppäin: Liikuta palikkaa vasemmalle"
                + " tai oikealle\nYlempi nuolinäppäin: Käännä palikkaa\nAlempi nuolinäppäin: Pudota palikka alas\n"
                + "P: Pause\nS: Uusi peli\nA: Laske palikkaa nopeammin.");
        statusbar = new JLabel("Rivejä poistettu: 0    Pisteet: 0");
        add(statusbar, BorderLayout.SOUTH);
        
        Kayttis peli = new Kayttis(this);
        add(peli);
        peli.starttaa();
        setSize(400, 600);
        setTitle("TETRIS: Liikuta palikoita nuolinäppäimillä! ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }
}
