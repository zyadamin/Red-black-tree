/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first_gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.paint.Paint;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author zezo
 */
public class gPanal  extends JPanel implements ActionListener{

    
        JTextField t1,t2;
        JButton button1,button2,button3;
        tree mytree=new tree();
        
     int b1=400,b2=300,bb1=5,bb2=5;
     Timer tt = new Timer(50, this); 

    public gPanal() {
    
    
        t1 = new JTextField();
        t2 = new JTextField();
       
        button1= new JButton("insert");
        button2 = new JButton("delete");
        button3 = new JButton("clear");

        button1.setBounds(80, 50, 70, 30);
        button1.setLocation(80,5 );
        
        button2.setBounds(200, 444, 70, 30);
        button2.setLocation(240,5 );
        
        button3.setBounds(200, 444, 70, 30);
        button3.setLocation(330,5 );
        
        t1.setSize(70, 30);
        t1.setLocation(5,5);
        

        
        t2.setSize(70, 30);
        t2.setLocation(160, 5);
        this.setLayout(null);

        this.add(button1);
        this.add(button2);
        this.add(t1);
        this.add(t2);
        this.add(button3);


        
button1.addActionListener(this);
button2.addActionListener(this);
button3.addActionListener(this);

    }

    
     
     
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        inorderHelper(mytree.root, g,600,50,300,mytree.length);
       
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    
    if(e.getSource()==button1){
        

         
          int x=Integer.parseInt(t1.getText());
          mytree.insertnode(x);
          
          
         // mytree.inorderHelper(mytree.root);
         // System.out.println("-----------------------------------");
              repaint();

      }
      
         if(e.getSource()==button2){
          int x=Integer.parseInt(t2.getText());

            mytree.Delete(x);
        //  mytree.inorderHelper(mytree.root);
         // System.out.println("-----------------------------------");
              repaint();

            
            }
         
         if(e.getSource()==button3){
         mytree.Clear(mytree.root);
               repaint();

         }

    
            
            
    }

    
    
    
    
public void inorderHelper(tree.node  root, Graphics g,int b1,int b2,int dynamic,int size)
{
    System.out.println(size);
    if (root ==  null)
        return;
    if(root.c==tree.color.Red){
         g.setColor(Color.red);
    
    }
       else{g.setColor(Color.black);}
        g.fillRoundRect(b1,b2, 50, 50, 50, 50);

         g.setColor(Color.white);
         g.drawString(root.value+"", b1+20, b2+27);
         
    inorderHelper(root.left,g,b1-dynamic,b2+90,dynamic/2,size-1);
    if(root.left!=null){
       g.setColor(Color.black);
       g.drawLine(b1, b2+35, b1-dynamic+20, b2+90);

    }

    inorderHelper(root.right,g,b1+dynamic,b2+90,dynamic/2,size-1);
    
    if(root.right!=null){
        g.setColor(Color.black);
      g.drawLine(b1+50, b2+35, b1+20+dynamic, b2+90);

    }
      //  System.out.println(dynamic);

}

    
    
}
