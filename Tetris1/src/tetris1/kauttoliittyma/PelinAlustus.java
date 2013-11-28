/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1.kauttoliittyma;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tetris1.Peli;

 /**
     * PelinAlustus-luokka alustaa pelin ja graafisen käyttöliittymän.
     * <p>
     * Ohjelmoinnin harjoitustyö, periodi II, syksy 2013.
     * <p>
     * @author Janne Knuutinen,
     * Helsingin yliopisto.
     */

public class PelinAlustus extends JFrame {

/**
 * PelinAlustus-luokan konstruktori, joka luo uuden Peli-olion, käyttää sen startaa-metodia aloittaakseen pelin. 
 *
 */
    public PelinAlustus() {

        Peli peli = new Peli();
        add(peli);
        peli.starttaa();
        setSize(400, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}