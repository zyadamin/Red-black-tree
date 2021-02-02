/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first_gui;

import java.awt.Color;
import javax.swing.JFrame;


/**
 *
 * @author zezo
 */

public class treeGUI extends JFrame {

        gPanal panal = new gPanal();
        
   

    public treeGUI(){
    
    
   
        this.setTitle("red black tree");
        this.setSize(1300, 750);
        this.setVisible(true);
        this.setLocation(50, 0);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panal.setBackground(Color.lightGray);
        this.add(panal);

        

}

    
}