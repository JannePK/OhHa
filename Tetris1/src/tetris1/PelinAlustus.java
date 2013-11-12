/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PelinAlustus extends JFrame {

  

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