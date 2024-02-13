package gui;

import MODEL.Corsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TopBar extends JPanel implements MouseListener {

    JLabel button_tabellone;
    JLabel button_search;
    JLabel button_profile;
    JFrame panelTabellone;
    JFrame panelProfilo;

    public TopBar(){


        //this.setBackground(Color.red);
        this.setPreferredSize(new Dimension(0,90));
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));

        JPanel logoPanel =new JPanel();
        logoPanel.setPreferredSize(new Dimension(400,80));
        logoPanel.setLayout(new BorderLayout(0,0));

        JPanel iconPanel=new JPanel();
        iconPanel.setPreferredSize(new Dimension(950,80));
        iconPanel.setLayout(new FlowLayout(FlowLayout.LEFT,100,20));

        ImageIcon tb_i=new ImageIcon("src/table-solid.png");
        button_tabellone= new JLabel();
        button_tabellone.setIcon(tb_i);
        button_tabellone.setOpaque(false);
        button_tabellone.addMouseListener(this);

        ImageIcon s_i=new ImageIcon("src/magnifying-glass-solid.png");
        button_search= new JLabel();
        button_search.setIcon(s_i);
        button_search.setOpaque(false);

        ImageIcon p_i=new ImageIcon("src/user-solid.png");
        button_profile= new JLabel();
        button_profile.setIcon(p_i);
        button_profile.setOpaque(false);
        button_profile.addMouseListener(this);


        iconPanel.add(button_tabellone);
        iconPanel.add(button_search);
        iconPanel.add(button_profile);


        ImageIcon logo=new ImageIcon("src/arturos-xs.png");
        JLabel logo_label=new JLabel();
        logo_label.setPreferredSize(new Dimension(600,90));
        logo_label.setIcon(logo);
        logo_label.setHorizontalAlignment(JLabel.CENTER);
        logoPanel.add(logo_label,BorderLayout.WEST);

        this.add(iconPanel,BorderLayout.EAST);
        this.add(logoPanel,BorderLayout.WEST);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==button_tabellone){
            if(panelTabellone==null){
                panelTabellone=new FrameTabellone();
            }
            else{
                panelTabellone.setVisible(true);
            }
        }
        else if(e.getSource()==button_profile){
            if(panelProfilo==null){
                panelProfilo=new FrameProfilo();
            }
            else{
                panelProfilo.setVisible(true);
            }

        }else if(e.getSource()==button_search){

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
