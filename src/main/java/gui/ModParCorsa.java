package gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import controller.Controller;

public class ModParCorsa extends JFrame {

    JComboBox portoPartenzaBox = new JComboBox();

    JComboBox portoArrivoBox = new JComboBox();

    JComboBox portoScaloBox = new JComboBox();

    Controller controller;

    JFrame modCorsa;

    public ModParCorsa(Object btnChiamante,FrameCompagnia frameCompagnia,Integer id_corsa, Time orario_partenza, Time orario_arrivo,Time orario_partenza_scalo, Time orario_arrivo_scalo, String porto_partenza, String porto_arrivo, String porto_scalo, Date data_inizio_servizio, Date data_fine_servizio, String giorni_servizio_attivo, Float sconto_residente,
                       Float prezzo_intero, Float prezzo_ridotto, Float sovr_veicoli, Float sovr_bagagli, Float sovr_prenotazioni){


        this.setSize(1400,840);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                e.getWindow().dispose();
                if(modCorsa!=null){
                    modCorsa.dispose();
                }
                if(id_corsa==null){
                    ((JButton)btnChiamante).setEnabled(true);
                }else{
                    ((JPanel)btnChiamante).setEnabled(true);
                }

            }
        });
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setResizable(false);

        if(id_corsa==null){
            ((JButton)btnChiamante).setEnabled(false);
        }else{
            ((JPanel)btnChiamante).setEnabled(false);
        }

        controller=Controller.getController();

        JPanel LabelPanel = new JPanel();
        LabelPanel.setPreferredSize(new Dimension(1400,60));
        JLabel Label = new JLabel("Modifica i parametri della corsa");
        Label.setFont(new Font("sans serif", Font.BOLD, 45));

        LabelPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));

        JPanel OrarioPanel = new JPanel();
        OrarioPanel.setPreferredSize(new Dimension(450,300));

        JLabel ModOraLabel = new JLabel("Sezione di modifica degli orari");
        ModOraLabel.setFont(new Font("sans serif", Font.PLAIN, 30));
        ModOraLabel.setIcon(new ImageIcon("src/orologio.png"));

        JLabel PartenzaLabel = new JLabel("Orario Partenza: ");
        PartenzaLabel.setFont(new Font("serif", Font.PLAIN, 20));
        PartenzaLabel.setPreferredSize(new Dimension(350,50));
        TimePicker OraPartenza=new TimePicker();
        OraPartenza.setTime(orario_partenza.toLocalTime());

        JLabel ArrivoLabel = new JLabel("Orario Arrivo: ");
        ArrivoLabel.setFont(new Font("serif", Font.PLAIN, 20));
        ArrivoLabel.setPreferredSize(new Dimension(350,50));
        TimePicker OraArrivo=new TimePicker();
        OraArrivo.setTime(orario_arrivo.toLocalTime());

        JLabel PartenzaScaloLabel = new JLabel("Orario Partenza Scalo: ");
        PartenzaScaloLabel.setFont(new Font("serif", Font.PLAIN, 20));
        PartenzaScaloLabel.setPreferredSize(new Dimension(350,50));
        TimePicker PartenzaScalo=new TimePicker();


        JLabel ArrivoScaloLabel = new JLabel("Orario Arrivo Scalo: ");
        ArrivoScaloLabel.setFont(new Font("serif", Font.PLAIN, 20));
        ArrivoScaloLabel.setPreferredSize(new Dimension(350,50));
        TimePicker ArrivoScalo=new TimePicker();

        if(porto_scalo!=null){
            PartenzaScalo.setTime(orario_partenza_scalo.toLocalTime());
            ArrivoScalo.setTime(orario_arrivo_scalo.toLocalTime());
        }

        OrarioPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));

        JPanel ServizioPanel = new JPanel();
        ServizioPanel.setPreferredSize(new Dimension(480,300));
        JLabel ServizioLabel = new JLabel("Sezione di modifica dei Servizi");
        ServizioLabel.setFont(new Font("sans serif", Font.PLAIN, 30));


        JLabel InizioServizioLabel = new JLabel("Inizio Servizio: ");
        InizioServizioLabel.setFont(new Font ("serif", Font.PLAIN, 20));
        InizioServizioLabel.setPreferredSize(new Dimension (250, 50));
        DatePicker pickerInizioServizio=new DatePicker();
        pickerInizioServizio.setDate(data_inizio_servizio.toLocalDate());

        JLabel FineServizioLabel = new JLabel("Fine Servizio: ");
        FineServizioLabel.setFont(new Font ("serif", Font.PLAIN, 20));
        FineServizioLabel.setPreferredSize(new Dimension (250, 50));

        DatePicker pickerFineServizio=new DatePicker();
        pickerFineServizio.setDate(data_fine_servizio.toLocalDate());

        JLabel ServizioAttivoLabel = new JLabel("Giorni Servizio Attivo:");
        ServizioAttivoLabel.setFont(new Font("sans serif", Font.PLAIN, 30));
        JCheckBox lunedi = new JCheckBox("Lunedì");
        lunedi.setSelected(giorni_servizio_attivo.charAt(0)=='1');
        JCheckBox martedi = new JCheckBox("martedì");
        martedi.setSelected(giorni_servizio_attivo.charAt(1)=='1');
        JCheckBox mercoledi = new JCheckBox("mercoledì");
        mercoledi.setSelected(giorni_servizio_attivo.charAt(2)=='1');
        JCheckBox giovedi = new JCheckBox("Giovedì");
        giovedi.setSelected(giorni_servizio_attivo.charAt(3)=='1');
        JCheckBox venerdi = new JCheckBox("Venerdì");
        venerdi.setSelected(giorni_servizio_attivo.charAt(4)=='1');
        JCheckBox sabato = new JCheckBox("Sabato");
        sabato.setSelected(giorni_servizio_attivo.charAt(5)=='1');
        JCheckBox domenica = new JCheckBox("domenica");
        domenica.setSelected(giorni_servizio_attivo.charAt(6)=='1');


        ServizioPanel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.lightGray));

        JPanel SovrapprezzoPanel = new JPanel();
        SovrapprezzoPanel.setPreferredSize(new Dimension(450,300));
        JLabel SovrapprezzoLabel = new JLabel("Sezione di modifica dei Sovrapprezzi");
        SovrapprezzoLabel.setFont(new Font("sans serif", Font.PLAIN, 27));

        JLabel SovrapprezzoPrenotLabel = new JLabel("Sovrapprezzo prenotazione:");
        SovrapprezzoPrenotLabel.setFont(new Font("serif", Font.PLAIN, 20));
        SovrapprezzoPrenotLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel sovrapprezzoprenotmax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinnerSovrapprezzoPrenot = new JSpinner(sovrapprezzoprenotmax);
        spinnerSovrapprezzoPrenot.setValue(sovr_prenotazioni);


        JLabel SovrapprezzoBagaglioLabel = new JLabel("Sovrapprezzo bagaglio: ");
        SovrapprezzoBagaglioLabel.setFont(new Font("serif", Font.PLAIN, 20));
        SovrapprezzoBagaglioLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel SovrapprezzoBagagmax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinnerSovrapprezzoBag = new JSpinner(SovrapprezzoBagagmax);
        spinnerSovrapprezzoBag.setValue(sovr_bagagli);

        JLabel SovrapprezzoveicoloLabel = new JLabel("Sovrapprezzo veicolo:");
        SovrapprezzoveicoloLabel.setFont(new Font("serif", Font.PLAIN, 20));
        SovrapprezzoveicoloLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel sovrapprezzoveicolomax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinnerSovrapprezzoVeicolo = new JSpinner(sovrapprezzoveicolomax);
        spinnerSovrapprezzoVeicolo.setValue(sovr_veicoli);


        JLabel  residenteLabel = new JLabel("Sconto residente: ");
        residenteLabel.setFont(new Font("serif", Font.PLAIN, 20));
        residenteLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel Scontomax = new SpinnerNumberModel(0.0, 0.0, 0.99, 0.01);
        JSpinner spinnerScontoResidente = new JSpinner(Scontomax);
        spinnerScontoResidente.setPreferredSize(new Dimension(80,20));
        spinnerScontoResidente.setValue(sconto_residente);

        SovrapprezzoPanel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.lightGray));

        JPanel PrezzoPanel = new JPanel();
        PrezzoPanel.setPreferredSize(new Dimension(450,300));
        JLabel PrezzoLabel = new JLabel("Sezione di modifica dei Prezzi");
        PrezzoLabel.setFont(new Font("sans serif", Font.PLAIN, 30));

        JLabel PrezzointLabel = new JLabel("Prezzo intero:");
        PrezzointLabel.setFont(new Font("serif", Font.PLAIN, 30));
        PrezzointLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel prezzointmax = new SpinnerNumberModel(0.0f, 0.0f, 1000.0f, 0.1f);
        JSpinner spinnerPrezzoInt = new JSpinner(prezzointmax);
        spinnerPrezzoInt.setValue(prezzo_intero);


        JLabel PrezzoridLabel = new JLabel("Prezzo ridotto: ");
        PrezzoridLabel.setFont(new Font("serif", Font.PLAIN, 30));
        PrezzoridLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel prezzoridmax = new SpinnerNumberModel(0.0f, 0.0f, 1000.0f, 0.1f);
        JSpinner spinnerPrezzoRid = new JSpinner(prezzoridmax);
        spinnerPrezzoRid.setValue(prezzo_ridotto);

        PrezzoPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.lightGray));


        JPanel PortoPanel = new JPanel();
        PortoPanel.setPreferredSize(new Dimension(480,300));
        JLabel PortoLabel = new JLabel("Sezione di modifica dei Porti");
        PortoLabel.setFont(new Font("sans serif", Font.PLAIN, 30));

        ArrayList<String> comuni_porti=new ArrayList<>();
        ArrayList<Integer> id_porti=new ArrayList<>();
        ArrayList<String> tel_porti=new ArrayList<>();
        ArrayList<String> indirizzo_porti=new ArrayList<>();
        controller.retrieve_porti(false,id_porti,indirizzo_porti,comuni_porti,tel_porti);

        JLabel portoPartenza = new JLabel("Porto partenza:");
        portoPartenza.setFont(new Font("serif", Font.PLAIN, 20));
        portoPartenza.setPreferredSize(new Dimension(300,50));
        portoPartenzaBox = new JComboBox();
        portoPartenzaBox.setPreferredSize(new Dimension(100, 30));

        JLabel portoarrivo = new JLabel("Porto arrivo:");
        portoarrivo.setFont(new Font("serif", Font.PLAIN, 20));
        portoarrivo.setPreferredSize(new Dimension(300,50));
        portoArrivoBox = new JComboBox();
        portoArrivoBox.setPreferredSize(new Dimension(100, 30));

        JLabel portoScalo = new JLabel("Porto partenza scalo:");
        portoScalo.setFont(new Font("serif", Font.PLAIN, 20));
        portoScalo.setPreferredSize(new Dimension(300,50));
        portoScaloBox = new JComboBox();
        portoScaloBox.setPreferredSize(new Dimension(100, 30));

        portoScaloBox.addItem("nessuno");

        for (int i=0;i<id_porti.size();i++){
            portoPartenzaBox.addItem(id_porti.get(i)+"-"+comuni_porti.get(i));
            if(porto_partenza.equals(comuni_porti.get(i))){
                portoPartenzaBox.setSelectedItem(id_porti.get(i)+"-"+comuni_porti.get(i));
            }
            portoArrivoBox.addItem(id_porti.get(i)+"-"+comuni_porti.get(i));
            if(porto_arrivo.equals(comuni_porti.get(i))){
                portoArrivoBox.setSelectedItem(id_porti.get(i)+"-"+comuni_porti.get(i));
            }
            portoScaloBox.addItem(id_porti.get(i)+"-"+comuni_porti.get(i));
            if(porto_scalo!=null && porto_scalo.equals(comuni_porti.get(i))){
                portoScaloBox.setSelectedItem(id_porti.get(i)+"-"+comuni_porti.get(i));
            }

        }


        JLabel labelNatante = new JLabel("Natante:");
        labelNatante.setFont(new Font("serif", Font.PLAIN, 20));
        labelNatante.setPreferredSize(new Dimension(300,50));
        JComboBox natanteBox = new JComboBox();
        natanteBox.setPreferredSize(new Dimension(100, 30));

        ArrayList<Integer> id_natante=new ArrayList<>();
        ArrayList<String> nome_natante=new ArrayList<>();
        ArrayList<String> trasporta=new ArrayList<>();
        ArrayList<String> tipo=new ArrayList<>();

        controller.retrieve_natanti(false,id_natante,nome_natante,trasporta,tipo);

        for (int i=0;i<id_natante.size();i++){
            natanteBox.addItem(id_natante.get(i)+"-"+nome_natante.get(i)+", "+tipo.get(i));
        }





        PortoPanel.setBorder(BorderFactory.createMatteBorder(2,2,0,0,Color.lightGray));



        JPanel ModorAnnCorsPanel = new JPanel();
        ModorAnnCorsPanel.setPreferredSize(new Dimension(450,300));
        JLabel ModorAnnCorsLabel = new JLabel(" Annulla o ritarda una corsa:");
        ModorAnnCorsLabel.setFont(new Font("sans serif", Font.PLAIN, 30));
        ModorAnnCorsLabel.setPreferredSize(new Dimension(450, 50));
        JButton ModorAnnBtn = new JButton("Modifica corsa");
        ModorAnnBtn.setPreferredSize(new Dimension(200,50));
        ModorAnnBtn.setBackground(Color.ORANGE);
        ModorAnnBtn.addActionListener(e -> {
            modCorsa=new ModCorsa(ModorAnnBtn,id_corsa);
        });


        JLabel deleteCorsaLabel = new JLabel(" Cancella la corsa:");
        deleteCorsaLabel.setFont(new Font("sans serif", Font.PLAIN, 30));
        deleteCorsaLabel.setPreferredSize(new Dimension(450, 50));

        JButton deleteBtn = new JButton("Cancella");
        deleteBtn.setPreferredSize(new Dimension(200,50));
        deleteBtn.setBackground(Color.RED);
        deleteBtn.addActionListener(e -> {
            controller.delete_corsa(id_corsa);
            frameCompagnia.UpdateResultsCorse();
            this.dispose();
        });



        ModorAnnCorsPanel.setBorder(BorderFactory.createMatteBorder(2,2,0,0,Color.lightGray));


        JPanel SendModPanel = new JPanel();
        SendModPanel.setPreferredSize(new Dimension(1400, 150));

        JLabel errorLabel=new JLabel("");
        errorLabel.setPreferredSize(new Dimension(1400,70));
        errorLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton ModBtn = new JButton("Invia Modifiche");
        ModBtn.setPreferredSize(new Dimension(200,50));
        ModBtn.setBackground(Color.green);
        ModBtn.addActionListener(e->{
            boolean[] bools=new boolean[7];
            bools[0]=lunedi.isSelected();bools[1]=martedi.isSelected();bools[2]=mercoledi.isSelected();bools[3]=giovedi.isSelected();bools[4]=venerdi.isSelected();bools[5]=sabato.isSelected();bools[6]=domenica.isSelected();
            Integer p_p= Integer.valueOf(portoPartenzaBox.getSelectedItem().toString().split("-")[0]);
            Integer p_a= Integer.valueOf(portoArrivoBox.getSelectedItem().toString().split("-")[0]);
            Integer nat=Integer.valueOf(natanteBox.getSelectedItem().toString().split("-")[0]);
            Integer p_s= portoScaloBox.getSelectedItem().toString().equals("nessuno")? null : Integer.valueOf(portoScaloBox.getSelectedItem().toString().split("-")[0]);
            try{
                controller.createUpdateCorsa(id_corsa,Time.valueOf(OraPartenza.getTime()),Time.valueOf(OraArrivo.getTime()),PartenzaScalo.getTime()!=null?Time.valueOf(PartenzaScalo.getTime()):null,ArrivoScalo.getTime()!=null?Time.valueOf(ArrivoScalo.getTime()):null,Date.valueOf(pickerInizioServizio.getDate()),Date.valueOf(pickerFineServizio.getDate()),
                        bools,Float.valueOf(spinnerSovrapprezzoPrenot.getValue().toString())<=0.05f?null:Float.valueOf(spinnerSovrapprezzoPrenot.getValue().toString()),Float.valueOf(spinnerSovrapprezzoBag.getValue().toString())<=0.05f?null:Float.valueOf(spinnerSovrapprezzoBag.getValue().toString()),Float.valueOf(spinnerSovrapprezzoVeicolo.getValue().toString())<=0.05f?null:Float.valueOf(spinnerSovrapprezzoVeicolo.getValue().toString()),
                        Float.valueOf(spinnerPrezzoInt.getValue().toString()),Float.valueOf(spinnerPrezzoRid.getValue().toString()),Float.valueOf(spinnerScontoResidente.getValue().toString()) <= 0.05f ? null:Float.valueOf(spinnerScontoResidente.getValue().toString()),p_p,p_a,p_s,controller.getIdUtente(),
                        nat);
                frameCompagnia.UpdateResultsCorse();
                this.dispose();

            }catch(SQLException error){
                errorLabel.setText("<html><p style=\"width:1400px;color:red\">"+error.getMessage()+"</p></html>");
                System.out.println(error.getMessage());
            }



        });

        SendModPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.lightGray));


        LabelPanel.add(Label);

        OrarioPanel.add(ModOraLabel);
        OrarioPanel.add(PartenzaLabel);
        OrarioPanel.add(OraPartenza);
        OrarioPanel.add(ArrivoLabel);
        OrarioPanel.add(OraArrivo);
        OrarioPanel.add(PartenzaScaloLabel);
        OrarioPanel.add(PartenzaScalo);
        OrarioPanel.add(ArrivoScaloLabel);
        OrarioPanel.add(ArrivoScalo);

        ServizioPanel.add(ServizioLabel);
        ServizioPanel.add(InizioServizioLabel);
        ServizioPanel.add(pickerInizioServizio);
        ServizioPanel.add(FineServizioLabel);
        ServizioPanel.add(pickerFineServizio);
        ServizioPanel.add(lunedi);
        ServizioPanel.add(martedi);
        ServizioPanel.add(mercoledi);
        ServizioPanel.add(giovedi);
        ServizioPanel.add(venerdi);
        ServizioPanel.add(sabato);
        ServizioPanel.add(domenica);

        SovrapprezzoPanel.add(SovrapprezzoLabel);
        SovrapprezzoPanel.add(SovrapprezzoPrenotLabel);
        SovrapprezzoPanel.add(spinnerSovrapprezzoPrenot);
        SovrapprezzoPanel.add(SovrapprezzoBagaglioLabel);
        SovrapprezzoPanel.add(spinnerSovrapprezzoBag);
        SovrapprezzoPanel.add(SovrapprezzoveicoloLabel);
        SovrapprezzoPanel.add(spinnerSovrapprezzoVeicolo);
        SovrapprezzoPanel.add(residenteLabel);
        SovrapprezzoPanel.add(spinnerScontoResidente);

        PrezzoPanel.add(PrezzoLabel);
        PrezzoPanel.add(PrezzointLabel);
        PrezzoPanel.add(spinnerPrezzoInt);
        PrezzoPanel.add(PrezzoridLabel);
        PrezzoPanel.add(spinnerPrezzoRid);

        PortoPanel.add(PortoLabel);
        PortoPanel.add(portoPartenza);
        PortoPanel.add(portoPartenzaBox);
        PortoPanel.add(portoarrivo);
        PortoPanel.add(portoArrivoBox);
        PortoPanel.add(portoScalo);
        PortoPanel.add(portoScaloBox);

        if(id_corsa!=null){
            ModorAnnCorsPanel.add(ModorAnnCorsLabel);
            ModorAnnCorsPanel.add(ModorAnnBtn);
            ModorAnnCorsPanel.add(deleteCorsaLabel);
            ModorAnnCorsPanel.add(deleteBtn);
        }

        SendModPanel.add(ModBtn);
        SendModPanel.add(errorLabel);



        this.add(LabelPanel);
        this.add(OrarioPanel);
        this.add(ServizioPanel);
        this.add(SovrapprezzoPanel);
        this.add(PrezzoPanel);
        this.add(PortoPanel);
        this.add(ModorAnnCorsPanel);
        this.add(SendModPanel);
        this.setVisible(true);
    }


}
