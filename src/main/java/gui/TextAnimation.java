package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextAnimation extends JPanel implements ActionListener {

    int x;
    int y;

    String str;
    float wid;
    Timer timer;

    public TextAnimation(JLabel l){
        str=l.getText();
        wid=l.getPreferredSize().width;
        x=this.getWidth();
        y=50;
        timer=new Timer(50,this);
        timer.start();
    }
    public void paint(Graphics gp){
        super.paint(gp);

        Graphics2D gp2d=(Graphics2D) gp;
        gp2d.setColor(Color.BLACK);
        gp2d.setFont(new Font("sans serif",Font.BOLD,40));
        gp2d.drawString(str,x,y);


        x-=2;

        if(x< - wid){
            x=this.getWidth();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            repaint();
        }
    }
}
