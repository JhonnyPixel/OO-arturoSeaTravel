package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class frameInfoCompagnia extends JFrame {

    JPanel panelSocial;

    Controller controller;

    Integer id_compagnia;
    public frameInfoCompagnia(JLabel logoCompagnia,Integer id_compagnia,String telefono,String mail_compagnia,String sito_web_compagnia){
        this.setSize(500,350);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.id_compagnia=id_compagnia;


        controller= Controller.getController();

        JPanel panelLeft=new JPanel();
        JPanel panelRight=new JPanel();

        panelLeft.setPreferredSize(new Dimension(250,350));
        panelRight.setPreferredSize(new Dimension(250,350));
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));


        panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        JLabel label=new JLabel("Social");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("sans serif",Font.BOLD,25));



        panelSocial=new JPanel();
        panelSocial.setLayout(new BoxLayout(panelSocial, BoxLayout.PAGE_AXIS));




        updateSocial();





        JScrollPane scroll=new JScrollPane(panelSocial,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(250,300));




        panelLeft.add(label);
        panelLeft.add(scroll);

        logoCompagnia.setPreferredSize(new Dimension(250,70));
        panelRight.add(logoCompagnia);
        JLabel tel=new JLabel(" telefono: "+telefono);
        tel.setPreferredSize(new Dimension(250,20));
        panelRight.add(tel);
        JLabel mail=new JLabel(" mail: "+mail_compagnia);
        mail.setPreferredSize(new Dimension(250,20));
        panelRight.add(mail);
        JLabel sito_web=new JLabel(" sito web: "+sito_web_compagnia);
        sito_web.setPreferredSize(new Dimension(250,20));
        panelRight.add(sito_web);




        this.add(panelLeft,BorderLayout.EAST);
        this.add(panelRight,BorderLayout.WEST);


        this.setVisible(true);
    }

    public void updateSocial(){
        panelSocial.removeAll();

        ArrayList<String> nome_social=new ArrayList<>();
        ArrayList<String> indirizzo_social=new ArrayList<>();

        controller.retrieve_social(true,id_compagnia,nome_social,indirizzo_social);

        for (int i=0;i<nome_social.size();i++){
            panelSocial.add(new rowSocial(nome_social.get(i),indirizzo_social.get(i)));
        }

        panelSocial.revalidate();
        panelSocial.repaint();
        this.revalidate();
        this.repaint();
    }
}
