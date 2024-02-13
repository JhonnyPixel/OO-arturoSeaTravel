package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.sql.Timestamp;

public class FrameProfilo extends JFrame {

    Controller controller;

    JPanel panelBiglietti;
    public FrameProfilo(){

        this.setSize(1200,600);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);

        controller=Controller.getController();

        JPanel panelLeft=new JPanel();
        JPanel panelRight=new JPanel();

        panelLeft.setPreferredSize(new Dimension(600,600));
        panelRight.setPreferredSize(new Dimension(600,600));
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));


        panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        JLabel label=new JLabel("I tuoi biglietti");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("sans serif",Font.BOLD,35));

        panelBiglietti=new JPanel();
        panelBiglietti.setLayout(new BoxLayout(panelBiglietti, BoxLayout.Y_AXIS));




        updateBiglietti();





        JScrollPane scroll=new JScrollPane(panelBiglietti,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(600,520));

        SettingPanel setting = new SettingPanel();



        panelLeft.add(label);
        panelLeft.add(scroll);

        panelRight.add(setting);



        this.add(panelLeft,BorderLayout.EAST);
        this.add(panelRight,BorderLayout.WEST);



        this.setVisible(true);


    }

    private void updateBiglietti() {
        panelBiglietti.removeAll();

        ArrayList<Float> importo_totale=new ArrayList<>();
        ArrayList<Float> Sovrapprezzo_tot=new ArrayList<>();
        ArrayList<Integer> n_bagagli=new ArrayList<>();
        ArrayList<Timestamp> prenotazione=new ArrayList<>();
        ArrayList<Integer> corsa=new ArrayList<>();

        if(controller.getEtaPasseggero()>=16){

            ArrayList<String> veicolo=new ArrayList<>();

            controller.retrieve_biglietti_interi(controller.getIdUtente(),true,importo_totale,Sovrapprezzo_tot,n_bagagli,veicolo,prenotazione,corsa);

            for(int i=0;i<importo_totale.size();i++){
                panelBiglietti.add(new bigliettoRow(corsa.get(i),importo_totale.get(i),Sovrapprezzo_tot.get(i),n_bagagli.get(i),prenotazione.get(i),veicolo.get(i),null));
            }

        }else{

            ArrayList<Integer> accompagnatore=new ArrayList<>();

            controller.retrieve_biglietti_ridotti(controller.getIdUtente(),true,importo_totale,Sovrapprezzo_tot,n_bagagli,prenotazione,corsa,accompagnatore);

            for(int i=0;i<importo_totale.size();i++){
                panelBiglietti.add(new bigliettoRow(corsa.get(i),importo_totale.get(i),Sovrapprezzo_tot.get(i),n_bagagli.get(i),prenotazione.get(i),null,accompagnatore.get(i)));
            }
        }



        panelBiglietti.revalidate();
        panelBiglietti.repaint();
        this.revalidate();
        this.repaint();
    }
}
