/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1;

import tetris1.logiikka.Palikka;
import tetris1.logiikka.Logiikka;
import tetris1.kayttoliittyma.PelinAlustus;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {


        Palikka palikka = new Palikka();
        System.out.println(palikka.getMuoto());
        palikka.asetaSatunnaismuoto();
        System.out.println(palikka.getMuoto());
        palikka.kaannaOikealle();
        System.out.println(palikka.getMuoto());

        
        PelinAlustus peli = new PelinAlustus();




        // TODO code application logic here
    }
}
