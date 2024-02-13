package gui;

import MODEL.Corsa;
import com.github.lgooddatepicker.components.TimePicker;
import controller.Controller;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.*;
import java.util.Timer;


public class FrameTabellone extends JFrame implements ActionListener {

    String[] veicolo={"qualsiasi","traghetto","aliscafo","motonave"};
    ArrayList<Corsa> corse;

    JPanel panelResults;

    Controller controller;

    JButton filtraButton;

    JComboBox Andatabox;
    JComboBox boxveicolo;

    TimePicker timePicker;
    JSpinner spinner;

    JDatePickerImpl datePicker;

    public FrameTabellone(){
        controller=Controller.getController();
        this.setSize(new Dimension(1000,610));
        //this.setBackground(Color.red);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.corse=new ArrayList<>(); //lo metto solo per provare poi elimina sta schifezza dalla gui (no model)

        JComboBox Ritornobox=new JComboBox();
        Andatabox=new JComboBox();
        timePicker=new TimePicker();

        Andatabox.setPreferredSize(new Dimension(130,30));
        Ritornobox.setPreferredSize(new Dimension(130,30));

        Andatabox.addItem("tutte");

        for(String s:controller.getTratte()){
            Ritornobox.addItem(s);
            Andatabox.addItem(s);
        }

        JPanel panelFiltro = new JPanel();
        panelFiltro.setPreferredSize(new Dimension(1000,130));
        //panelFiltro.setBackground(Color.magenta);
        panelFiltro.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        datePicker.setPreferredSize(new Dimension(130,30));


        JPanel panelDate =new JPanel();
        //panelDate.setBackground(Color.blue);
        panelDate.setPreferredSize(new Dimension(200,110));

        JPanel panelChoose=new JPanel();
        //panelChoose.setBackground(Color.red);
        panelChoose.setPreferredSize(new Dimension(200,110));
        panelChoose.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));

        JPanel panelFiltra=new JPanel();
        //panelFiltra.setBackground(Color.yellow);
        panelFiltra.setPreferredSize(new Dimension(200,110));

        filtraButton=new JButton("FILTRA");
        filtraButton.setFocusable(false);
        filtraButton.addActionListener(this);

        panelChoose.add(filtraButton);
        panelDate.add(new JLabel("Data partenza"));
        panelDate.add(datePicker);


        boxveicolo = new JComboBox(veicolo);
        boxveicolo.setPreferredSize(new Dimension(100, 30));


        JLabel veicololabel=new JLabel("veicolo");
        veicololabel.setPreferredSize(new Dimension(200,15));
        veicololabel.setHorizontalTextPosition(JLabel.CENTER);
        veicololabel.setHorizontalAlignment(JLabel.CENTER);


        SpinnerModel modelspinner = new SpinnerNumberModel(80.0, 1.0, 100.0, 1.0);
        spinner = new JSpinner(modelspinner);
        spinner.setPreferredSize(new Dimension(80, 22));

        JLabel prezzo=new JLabel("prezzo");
        prezzo.setPreferredSize(new Dimension(200,15));
        prezzo.setHorizontalTextPosition(JLabel.CENTER);
        prezzo.setHorizontalAlignment(JLabel.CENTER);

        panelFiltra.add(veicololabel);
        panelFiltra.add(boxveicolo);
        panelFiltra.add(prezzo);
        panelFiltra.add(spinner);

        //codice per aggiungere animazione dell orologio

        Clock clock = new Clock();
        ClockPanel clockPanel = new ClockPanel(clock);

        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                clock.update();
                clockPanel.repaint();
            }

        }, 0, 1000);

        //fine codice del clock

        JPanel panelSelezionaTratte =new JPanel();
        panelSelezionaTratte.setPreferredSize(new Dimension(200,110));
        panelSelezionaTratte.add(Andatabox);
        panelSelezionaTratte.add(timePicker);



        panelFiltro.add(clockPanel);
        panelFiltro.add(panelSelezionaTratte);
        panelFiltro.add(panelChoose);
        panelFiltro.add(panelFiltra);
        panelFiltro.add(panelDate);




        JPanel nomeColonne=new JPanel();
        nomeColonne.setPreferredSize(new Dimension(1000,20));
        nomeColonne.setBackground(Color.lightGray);
        nomeColonne.setLayout(new FlowLayout(FlowLayout.CENTER,150,0));
        nomeColonne.add(new JLabel("COMPAGNIA"));
        nomeColonne.add(new JLabel("PARTENZA-ARRIVO"));
        nomeColonne.add(new JLabel("ORARIO E DATA"));
        nomeColonne.add(new JLabel("INFORMAZIONI"));

        panelFiltro.add(nomeColonne);





        panelResults=new JPanel();
        panelResults.setLayout(new BoxLayout(panelResults,BoxLayout.PAGE_AXIS));




       // panelResults.add(nomeColonne);

        updateResultsCorse(null,null,null,null,null,null);



        JScrollPane scroll=new JScrollPane(panelResults,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(panelFiltro,BorderLayout.NORTH);
        this.add(scroll,BorderLayout.CENTER);
        this.setVisible(true);

    }

    /*

    public void updateResultsCorse(String portoPartenzaAndata, String portoArrivoAndata, String portoPartenzaRitorno, String portoArrivoRitorno,Integer giornoAndata,Integer giornoRitorno){
        panelResults.removeAll();

        Boolean conditionGiornoAndata=true;
        Boolean conditionGiornoRitorno=true;

        for (Corsa c:corse) {
            for(int i=0;i<7;i++){
                System.out.println(c.getGiorniServizioAttivo()[i]);
            }
            if(giornoAndata!=null){
                conditionGiornoAndata=controller.getGiorniServizioAttivo(c)[giornoAndata];
                if(giornoRitorno!=null){
                    conditionGiornoRitorno=controller.getGiorniServizioAttivo(c)[giornoRitorno];
                }
            }
            if(controller.getComune(controller.getPorto_Partenza(c)).equals(portoPartenzaAndata) && controller.getComune(controller.getPorto_Arrivo(c)).equals(portoArrivoAndata) && conditionGiornoAndata||
                    controller.getComune(controller.getPorto_Partenza(c)).equals(portoPartenzaRitorno) && controller.getComune(controller.getPorto_Arrivo(c)).equals(portoArrivoRitorno) && conditionGiornoRitorno) {
                panelResults.add(new RowTabellone());
            }
        }
        panelResults.revalidate();
        panelResults.repaint();
        this.revalidate();
        this.repaint();
    }

     */

    public void updateResultsCorse(String portoPartenzaAndata, String portoArrivoAndata, java.sql.Date dataAndata,Time orario_partenza,Float prezzo,String tipo_natante){
        panelResults.removeAll();

        ArrayList<Integer> id_corse=new ArrayList<>();
        ArrayList<Time> Orari_Partenza=new ArrayList<>();
        ArrayList<Time> Orari_Arrivo=new ArrayList<>();
        ArrayList<java.sql.Date> Date_Inizio_Servizio=new ArrayList<>();
        ArrayList<java.sql.Date> Date_Fine_Servizio=new ArrayList<>();
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



        controller.filtra_corse(null,portoPartenzaAndata,portoArrivoAndata,dataAndata,orario_partenza,prezzo,tipo_natante,id_corse,Orari_Partenza,Orari_Arrivo,
                Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,Sconti_residente,Prezzi_interi,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,id_natanti,
                Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,id_porti_arrivo,Indirizzi_porti_arrivo,
                Comuni_porti_arrivo,Telefoni_porti_arrivo,id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,id_compagnie,Telefoni_compagnie,Mail_compagnie,Siti_web_compagnie,
                login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);

        int i;

        for(i=0;i<id_corse.size();i++){
            System.out.println("p"+i+": "+Comuni_porti_partenza.get(i));
            System.out.println("r"+i+": "+Comuni_porti_arrivo.get(i)); //problema qui
            panelResults.add(new RowTabellone(Comuni_porti_partenza.get(i),Comuni_porti_arrivo.get(i),Orari_Partenza.get(i),Orari_Arrivo.get(i),nomi_compagnie.get(i),
                    Motivazioni_ritardi.get(i),Tempi_ritardi.get(i),Motivazioni_annullamenti.get(i)));
        }




        panelResults.revalidate();
        panelResults.repaint();
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==filtraButton){

            String[] andata=new String[2];
            andata[0]=null;
            andata[1]=null;
            if(!Andatabox.getSelectedItem().equals("tutte")){
                andata=Andatabox.getSelectedItem().toString().split("-");
                andata[1]=andata[1].replace(">","");
            }

            System.out.println(andata[0]+" "+andata[1]);



            Date selectedDateAndata = (Date) datePicker.getModel().getValue();

            java.sql.Date selectedDateAndataSQL=(selectedDateAndata==null)? null :new java.sql.Date(selectedDateAndata.getTime());

            Float prezzo= (float) ((Double) spinner.getValue()).doubleValue();

            System.out.println("prezzo--->"+prezzo);
            System.out.println("veicolo--->"+boxveicolo.getSelectedItem().toString());

            String veicolo=null;

            if(!boxveicolo.getSelectedItem().toString().equals("qualsiasi")){
                veicolo=boxveicolo.getSelectedItem().toString();
            }

            Time time=null;
            if(timePicker.getTime()!=null){
                time=Time.valueOf(timePicker.getTime());
            }

            updateResultsCorse(andata[0],andata[1],selectedDateAndataSQL, time, prezzo, veicolo);

        }
    }
}
