package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Properties;

import MODEL.Compagnia;
import MODEL.Corsa;
import com.github.lgooddatepicker.components.TimePicker;
import controller.Controller;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class FrameCompagnia extends JFrame{



    JPanel leftPanel;

    Controller controller=Controller.getController();





    public FrameCompagnia(){
        this.setSize(1400,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setResizable(false);

        controller.updatePorti();
        controller.updateNatanti();

        leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        //leftPanel.setPreferredSize(new Dimension(400,700));
        JLabel labelCorse = new JLabel("Le tue Corse");
        labelCorse.setFont(new Font("sans serif", Font.BOLD, 45));
        //leftPanel.add(labelCorse);

        leftPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,2,Color.lightGray));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        //leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));


        Controller controller=Controller.getController();

        UpdateResultsCorse();


        JScrollPane scroll=new JScrollPane(leftPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(400,700));
        //scroll.setBounds(0,0,400,700);




        centerPanel.setPreferredSize(new Dimension(700,810));
        JLabel labelAddCorse = new JLabel("Aggiungi Corse");
        labelAddCorse.setFont(new Font("sans serif", Font.BOLD, 45));
        labelAddCorse.setPreferredSize(new Dimension(700,100));
        labelAddCorse.setHorizontalAlignment(JLabel.CENTER);

        JButton aggiungiCorsaBtn=new JButton("+");
        aggiungiCorsaBtn.setFont(new Font("sans serif",Font.BOLD,30));
        aggiungiCorsaBtn.setForeground(Color.white);
        aggiungiCorsaBtn.setPreferredSize(new Dimension(60,60));
        aggiungiCorsaBtn.setBackground(Color.green);
        aggiungiCorsaBtn.setFocusable(false);
        aggiungiCorsaBtn.addActionListener(e->{
            new ModParCorsa(this,null,new Time(8,0,0),new Time(9,0,0),null,null,"bar","genova",
                    null,Date.valueOf(java.time.LocalDate.now()),Date.valueOf(java.time.LocalDate.now().plusMonths(1)),"1111111",
                    0.0f,50.0f,40.0f,0.0f,0.0f,0.0f);
        });











        centerPanel.add(labelAddCorse);
        centerPanel.add(aggiungiCorsaBtn);



        this.add(scroll);
        this.add(centerPanel);
        this.add(rightPanel);
        this.pack();
        this.setVisible(true);
    }


    public void UpdateResultsCorse(){
        leftPanel.removeAll();

        ArrayList<Integer> id_corsa=new ArrayList<>();
        ArrayList<Time> orario_partenza=new ArrayList<>();
        ArrayList<Time> orario_arrivo=new ArrayList<>();
        ArrayList<Time> orario_partenza_scalo=new ArrayList<>();
        ArrayList<Time> orario_arrivo_scalo=new ArrayList<>();
        ArrayList<Date> data_inizio_servizio=new ArrayList<>();
        ArrayList<Date> data_fine_servizio=new ArrayList<>();
        ArrayList<String> giorni_servizio_attivo=new ArrayList<>();
        ArrayList<Float> sovr_prenotazione=new ArrayList<>();
        ArrayList<Float> sovr_bagaglio=new ArrayList<>();
        ArrayList<Float> sovr_veicolo=new ArrayList<>();
        ArrayList<Float> prezzo_intero=new ArrayList<>();
        ArrayList<Float> prezzo_ridotto=new ArrayList<>();
        ArrayList<Float> sconto_residente=new ArrayList<>();
        ArrayList<Integer> porto_partenza=new ArrayList<>();
        ArrayList<Integer> porto_arrivo=new ArrayList<>();
        ArrayList<Integer> porto_scalo=new ArrayList<>();
        ArrayList<Integer> natante=new ArrayList<>();
        ArrayList<String> p1_indirizzo=new ArrayList<>();
        ArrayList<String> p1_comune=new ArrayList<>();
        ArrayList<String> p1_tel_info=new ArrayList<>();
        ArrayList<String> p2_indirizzo=new ArrayList<>();
        ArrayList<String> p2_comune=new ArrayList<>();
        ArrayList<String> p2_tel_info=new ArrayList<>();
        ArrayList<String> ps_indirizzo=new ArrayList<>();
        ArrayList<String> ps_comune=new ArrayList<>();
        ArrayList<String> ps_tel_info=new ArrayList<>();
        ArrayList<String> nome_natante=new ArrayList<>();
        ArrayList<String> trasporta=new ArrayList<>();
        ArrayList<String> tipo_natante=new ArrayList<>();

        controller.retrieve_corse_compagnia((Compagnia) controller.getUtente(),true,id_corsa,orario_partenza,orario_arrivo,
                orario_partenza_scalo,orario_arrivo_scalo,data_inizio_servizio,
                data_fine_servizio,giorni_servizio_attivo,sovr_prenotazione,sovr_bagaglio,sovr_veicolo,
                prezzo_intero,prezzo_ridotto,sconto_residente,porto_partenza,porto_arrivo,porto_scalo,natante,
                p1_indirizzo,p1_comune,p1_tel_info,p2_indirizzo,p2_comune,p2_tel_info,ps_indirizzo,
                ps_comune,ps_tel_info,nome_natante,trasporta,tipo_natante);

        for(int i=0;i<id_corsa.size();i++){
            CompagniaRow j=new CompagniaRow(this,id_corsa.get(i),orario_partenza.get(i),orario_arrivo.get(i),orario_partenza_scalo.get(i),orario_arrivo_scalo.get(i),p1_comune.get(i),p2_comune.get(i),ps_comune.get(i),data_inizio_servizio.get(i),data_fine_servizio.get(i),
                    giorni_servizio_attivo.get(i),sconto_residente.get(i),prezzo_intero.get(i),prezzo_ridotto.get(i),
                    sovr_veicolo.get(i),sovr_bagaglio.get(i),sovr_prenotazione.get(i));
            j.setPreferredSize(new Dimension(400,130));
            j.setMinimumSize(new Dimension(400,130));
            j.setMaximumSize(new Dimension(400,130));
            leftPanel.add(j);
        }





        leftPanel.revalidate();
        leftPanel.repaint();
        this.revalidate();
        this.repaint();

    }


}
