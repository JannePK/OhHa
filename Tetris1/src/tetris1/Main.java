/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris1;

import tetris1.kauttoliittyma.PelinAlustus;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {

Peli peli1 = new Peli();
        Palikka palikka = new Palikka();
        System.out.println(palikka.getMuoto());
        palikka.asetaSatunnaismuoto();
        System.out.println(palikka.getMuoto());
        palikka.kaannaOikealle();
        System.out.println(palikka.getMuoto());
System.out.println(peli1.nelionLeveys());
        
        PelinAlustus peli = new PelinAlustus();




        // TODO code application logic here
    }
}
