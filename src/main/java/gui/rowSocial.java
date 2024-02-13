package gui;

import javax.swing.*;
import java.awt.*;

public class rowSocial extends JPanel {
    public rowSocial(String nome,String indirizzo){
        this.setPreferredSize(new Dimension(250,50));
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));


        JLabel nome_social=new JLabel("<html>social: "+nome+"<br/> <a href='"+indirizzo+"'> indirizzo: "+indirizzo+"</a><br/></html>");


        this.add(nome_social);
    }
}
