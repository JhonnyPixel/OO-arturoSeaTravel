package gui;

import model.Corsa;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;



public class FramePasseggero extends JFrame{

    JPanel rightPanel;
    Controller controller;


    public FramePasseggero() {
        this.setSize(1500,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        controller=Controller.getController();


        JPanel all=new JPanel();
        all.setLayout(new BorderLayout(0,0));

        TopBar topbar=new TopBar();

        rightPanel=new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

        JPanel leftPanel=new JPanel();
        leftPanel.setPreferredSize(new Dimension(750,810));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,2,Color.lightGray));





        UpdateResultsCorse(null, null, null, null,null,null,false);
        controller.setCorse();


        JScrollPane scroll=new JScrollPane(rightPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        Ricerca ricerca = new Ricerca(this,controller.getComuniPortiPartenzaCorseFiltrate(),controller.getComuniPortiArrivoCorseFiltrate());

        leftPanel.add(ricerca);




        all.add(leftPanel,BorderLayout.WEST);
        all.add(scroll,BorderLayout.EAST);
        this.add(topbar,BorderLayout.NORTH);
        this.add(all);
        this.setVisible(true);
    }


    public void UpdateResultsCorse(String portoPartenzaAndata, String portoArrivoAndata, String portoPartenzaRitorno, String portoArrivoRitorno,java.sql.Date dataAndata,java.sql.Date dataRitorno,Boolean Andata_e_Ritorno){
        rightPanel.removeAll();



        ArrayList<Corsa> corse=new ArrayList<>();

        ArrayList<Integer> id_corse=new ArrayList<>();
        ArrayList<Time> Orari_Partenza=new ArrayList<>();
        ArrayList<Time> Orari_Arrivo=new ArrayList<>();
        ArrayList<Time> Orari_Partenza_Scalo=new ArrayList<>();
        ArrayList<Time> Orari_Arrivo_Scalo=new ArrayList<>();
        ArrayList<Date> Date_Inizio_Servizio=new ArrayList<>();
        ArrayList<Date> Date_Fine_Servizio=new ArrayList<>();
        ArrayList<String> Giorni_Servizio_Attivo=new ArrayList<>();
        ArrayList<Float> Sconti_residente = new ArrayList<>();
        ArrayList<Float> Prezzi_interi= new ArrayList<>();
        ArrayList<Float> Prezzi_ridotti = new ArrayList<>();
        ArrayList<Float> Sovr_Veicoli=new ArrayList<>();
        ArrayList<Float> Sovr_Bagagli=new ArrayList<>();
        ArrayList<Float> Sovr_Prenotazioni=new ArrayList<>();

        ArrayList<Integer> id_natanti=new ArrayList<>();
        ArrayList<String> Nomi_natanti = new ArrayList<>();
        ArrayList<String> Trasporti = new ArrayList<>();
        ArrayList<String> Tipi_natanti=new ArrayList<>();

        ArrayList<Integer> id_porti_partenza=new ArrayList<>();
        ArrayList<String> Indirizzi_porti_partenza=new ArrayList<>();
        ArrayList<String> Comuni_porti_partenza=new ArrayList<>();
        ArrayList<String> Telefoni_porti_partenza=new ArrayList<>();

        ArrayList<Integer> id_porti_arrivo=new ArrayList<>();
        ArrayList<String> Indirizzi_porti_arrivo=new ArrayList<>();
        ArrayList<String> Comuni_porti_arrivo=new ArrayList<>();
        ArrayList<String> Telefoni_porti_arrivo=new ArrayList<>();

        ArrayList<Integer> id_porti_scalo=new ArrayList<>();
        ArrayList<String> Indirizzi_porti_scalo=new ArrayList<>();
        ArrayList<String> Comuni_porti_scalo=new ArrayList<>();
        ArrayList<String> Telefoni_porti_scalo=new ArrayList<>();

        ArrayList<Integer> id_compagnie=new ArrayList<>();
        ArrayList<String> Telefoni_compagnie=new ArrayList<>();
        ArrayList<String> Mail_compagnie=new ArrayList<>();
        ArrayList<String> Siti_web_compagnie=new ArrayList<>();
        ArrayList<String> login_compagnie=new ArrayList<>();
        ArrayList<String> password_compagnie=new ArrayList<>();
        ArrayList<String> nomi_compagnie=new ArrayList<>();
        ArrayList<String> Motivazioni_ritardi=new ArrayList<>();
        ArrayList<Time> Tempi_ritardi=new ArrayList<>();
        ArrayList<String> Motivazioni_annullamenti=new ArrayList<>();
        ArrayList<Float> Rimborsi = new ArrayList<>();
        ArrayList<Integer> Prossimi=new ArrayList<>();

        /*
        controller.filtra_corse(null,portoPartenzaAndata,portoArrivoAndata,dataAndata,null,null,null,id_corse,Orari_Partenza,Orari_Arrivo,
                Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,Sconti_residente,Prezzi_interi,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,id_natanti,
                Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,id_porti_arrivo,Indirizzi_porti_arrivo,
                Comuni_porti_arrivo,Telefoni_porti_arrivo,id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,id_compagnie,Telefoni_compagnie,Mail_compagnie,Siti_web_compagnie,
                login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);
*/
        controller.filtraCorse(null,portoPartenzaAndata,portoArrivoAndata,dataAndata,null,null,null,true,id_corse,Orari_Partenza,Orari_Arrivo,
                Orari_Partenza_Scalo,Orari_Arrivo_Scalo,Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,Sconti_residente,Prezzi_interi,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,id_natanti,
                Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,id_porti_arrivo,Indirizzi_porti_arrivo,
                Comuni_porti_arrivo,Telefoni_porti_arrivo,id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,id_compagnie,Telefoni_compagnie,Mail_compagnie,Siti_web_compagnie,
                login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);

        int i;
        for(i=0;i<id_corse.size();i++){
            rightPanel.add(new ResultRow(Comuni_porti_partenza.get(i),Comuni_porti_arrivo.get(i),Comuni_porti_scalo.get(i),nomi_compagnie.get(i),id_compagnie.get(i),
                    Telefoni_compagnie.get(i), Mail_compagnie.get(i), Siti_web_compagnie.get(i),
                    Orari_Partenza.get(i),Orari_Arrivo.get(i),Orari_Partenza_Scalo.get(i),Orari_Arrivo_Scalo.get(i),Prezzi_interi.get(i),Prezzi_ridotti.get(i),Sconti_residente.get(i),
                    Sovr_Veicoli.get(i),Sovr_Prenotazioni.get(i),Sovr_Bagagli.get(i),id_corse.get(i)));
        }


        if(Andata_e_Ritorno && !(portoPartenzaAndata.equals(portoPartenzaRitorno) && portoArrivoAndata.equals(portoArrivoRitorno))) {

            controller.filtraCorse(null,portoPartenzaRitorno,portoArrivoRitorno,dataRitorno,null,null,null,true,id_corse,Orari_Partenza,Orari_Arrivo,
                    Orari_Partenza_Scalo,Orari_Arrivo_Scalo,Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,Sconti_residente,Prezzi_interi,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,id_natanti,
                    Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,id_porti_arrivo,Indirizzi_porti_arrivo,
                    Comuni_porti_arrivo,Telefoni_porti_arrivo,id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,id_compagnie,Telefoni_compagnie,Mail_compagnie,Siti_web_compagnie,
                    login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);
                    for(int j=i;j<id_corse.size();j++){
                            rightPanel.add(new ResultRow(Comuni_porti_partenza.get(j),Comuni_porti_arrivo.get(j),Comuni_porti_scalo.get(i),nomi_compagnie.get(j),id_compagnie.get(i),
                                    Telefoni_compagnie.get(i), Mail_compagnie.get(i), Siti_web_compagnie.get(i),
                            Orari_Partenza.get(j),Orari_Arrivo.get(j),Orari_Partenza_Scalo.get(j),Orari_Arrivo_Scalo.get(j),Prezzi_interi.get(j),Prezzi_ridotti.get(j),Sconti_residente.get(j),
                            Sovr_Veicoli.get(j),Sovr_Prenotazioni.get(j),Sovr_Bagagli.get(j),id_corse.get(j)));

                    }




        }





        rightPanel.revalidate();
        rightPanel.repaint();
        this.revalidate();
        this.repaint();

    }







}
