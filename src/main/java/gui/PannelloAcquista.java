package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PannelloAcquista extends JFrame implements ActionListener {

    JButton btn_confirm;

    float tot;
    float sovr_totale;

    int id_corsa;

    int n_bagagli;

    String veicolo;

    JComboBox boxAccompagnatore;
    Controller controller;

    public PannelloAcquista(JButton btnChiamante,float prezzo,Float sconto_residente,Float sovr_veicolo,float sovr_prenotazione,Float sovr_bagagli,int id_corsa){

        this.setSize(450,370);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                    e.getWindow().dispose();
                    btnChiamante.setEnabled(true);
            }
        });
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT,12,12));
        btnChiamante.setEnabled(false);

        controller=Controller.getController();

        this.id_corsa=id_corsa;


        JPanel all=new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));

        JLabel riepilogo=new JLabel("<html>RIEPILOGO<br/><br/></html>");
        riepilogo.setHorizontalTextPosition(JLabel.CENTER);
        riepilogo.setFont(new Font("sans serif",Font.BOLD,30));


        all.add(riepilogo);

        tot=prezzo;
        sovr_totale=sovr_prenotazione;

        all.add(new JLabel("<html>Prezzo biglietto:"+prezzo+"$<br/><br/></html>"));
        if(Ricerca.useScontoResidente()){
            all.add(new JLabel("<html>Sconto residente: "+sconto_residente+"<br/><br/></html>"));
            tot= tot * sconto_residente;
        }
        this.veicolo=Ricerca.getVeicolo();
        if(!this.veicolo.equals("nessuno")){
            all.add(new JLabel("<html>Sovrapprezzo veicolo: "+sovr_veicolo+"$<br/><br/></html>"));
            tot+=sovr_veicolo;
            sovr_totale+=sovr_veicolo;
        }else{
            this.veicolo=null;
        }
        this.n_bagagli=Ricerca.getNBagagli();
        if(sovr_bagagli!=null){
            all.add(new JLabel("<html>Sovrapprezzo bagagli: "+n_bagagli+" bagagli x "+sovr_bagagli+"$ = "+n_bagagli*sovr_bagagli+"$<br/><br/></html>"));
            tot += n_bagagli*sovr_bagagli;
            sovr_totale+= n_bagagli*sovr_bagagli;
        }
        all.add(new JLabel("<html>Sovrapprezzo prenotazione: "+sovr_prenotazione+"$<br/><br/></html>"));

        tot+=sovr_prenotazione;


        all.add(new JLabel("<html>Totale: "+ tot + "<br/><br/><br/></html>"));



        if(controller.getEtaPasseggero()<16){
            JLabel accompLabel=new JLabel("<html>Scegli un accompagnatore<br/><br/></html>");

            ArrayList<Integer> id_passeggero=new ArrayList<>();
            ArrayList<String> nome=new ArrayList<>();
            ArrayList<String> login=new ArrayList<>();
            ArrayList<String> password=new ArrayList<>();
            ArrayList<Integer> eta=new ArrayList<>();

            boxAccompagnatore=new JComboBox();
            boxAccompagnatore.setPreferredSize(new Dimension(50,20));

            controller.retrieve_accompagnatori(true,id_passeggero,nome,login,password,eta);
            for (int i=0;i<id_passeggero.size();i++){
                boxAccompagnatore.addItem(id_passeggero.get(i)+"-"+nome.get(i));
            }

            all.add(accompLabel);
            all.add(boxAccompagnatore);
        }

        btn_confirm=new JButton("Conferma");
        btn_confirm.addActionListener(this);

        all.add(btn_confirm);

        this.add(all);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_confirm) {
            System.out.println("Acquistato");
            if(controller.getEtaPasseggero()<16){
                System.out.println("comprato ridotto");
                Integer accompagnatore=Integer.parseInt(boxAccompagnatore.getSelectedItem().toString().split("-")[0]);
                controller.add_biglietto_ridotto(tot,sovr_totale,n_bagagli,new Timestamp(System.currentTimeMillis()),
                        id_corsa,controller.getIdUtente(),accompagnatore);
            }else{
                System.out.println("comprato intero");

                controller.add_biglietto_intero(tot, sovr_totale, n_bagagli, veicolo, new Timestamp(System.currentTimeMillis()),
                        id_corsa, controller.getIdUtente());
            }

            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
