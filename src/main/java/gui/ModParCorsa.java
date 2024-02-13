package gui;

import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Time;
import java.util.Properties;

import com.github.lgooddatepicker.components.TimePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ModParCorsa extends JFrame implements ItemListener {

    JComboBox portopartenzabox = new JComboBox();

    JComboBox portoarrivobox = new JComboBox();

    JComboBox portopartenzascalobox = new JComboBox();

    JComboBox portoarrivoscalobox = new JComboBox();


    String[] porti = {"procida", "Napoli", "trieste", "Genova"};
    public ModParCorsa(Integer id_corsa, Time orario_partenza, Time orario_arrivo, String porto_partenza, String porto_arrivo, String porto_scalo, Date data_inizio_servizio, Date data_fine_servizio, String giorni_servizio_attivo, Float sconto_residente,
                       Float prezzo_intero, Float prezzo_ridotto, Float sovr_veicoli, Float sovr_bagagli, Float sovr_prenotazioni){
        this.setSize(1400,800);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setResizable(false);

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

        OrarioPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));

        JPanel ServizioPanel = new JPanel();
        ServizioPanel.setPreferredSize(new Dimension(480,300));
        JLabel ServizioLabel = new JLabel("Sezione di modifica dei Servizi");
        ServizioLabel.setFont(new Font("sans serif", Font.PLAIN, 30));


        JLabel InizioServizioLabel = new JLabel("Inizio Servizio: ");
        InizioServizioLabel.setFont(new Font ("serif", Font.PLAIN, 20));
        InizioServizioLabel.setPreferredSize(new Dimension (250, 50));
        UtilDateModel model = new UtilDateModel();
        model.setDate(data_inizio_servizio.getYear(),data_inizio_servizio.getMonth(),data_inizio_servizio.getDay());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JLabel FineServizioLabel = new JLabel("Fine Servizio: ");
        FineServizioLabel.setFont(new Font ("serif", Font.PLAIN, 20));
        FineServizioLabel.setPreferredSize(new Dimension (250, 50));
        UtilDateModel model2 = new UtilDateModel();
        model2.setDate(data_fine_servizio.getYear(),data_fine_servizio.getMonth(),data_fine_servizio.getDay());
        Properties p2 = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());

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
        SpinnerModel Scontomax = new SpinnerNumberModel(0.1, 0.1, 0.99, 0.01);
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
        SpinnerModel prezzointmax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinnerPrezzoInt = new JSpinner(prezzointmax);
        spinnerPrezzoInt.setValue(prezzo_intero);


        JLabel PrezzoridLabel = new JLabel("Prezzo ridotto: ");
        PrezzoridLabel.setFont(new Font("serif", Font.PLAIN, 30));
        PrezzoridLabel.setPreferredSize(new Dimension(350, 50));
        SpinnerModel prezzoridmax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner spinnerPrezzoRid = new JSpinner(prezzoridmax);
        spinnerPrezzoRid.setValue(prezzo_ridotto);

        PrezzoPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.lightGray));


        JPanel PortoPanel = new JPanel();
        PortoPanel.setPreferredSize(new Dimension(480,300));
        JLabel PortoLabel = new JLabel("Sezione di modifica dei Porti");
        PortoLabel.setFont(new Font("sans serif", Font.PLAIN, 30));

        JLabel portoparetnza = new JLabel("Porto partenza:");
        portoparetnza.setFont(new Font("serif", Font.PLAIN, 20));
        portoparetnza.setPreferredSize(new Dimension(300,50));
        portopartenzabox = new JComboBox(porti);
        portopartenzabox.setPreferredSize(new Dimension(100, 30));
        portopartenzabox.addItemListener(this);

        JLabel portoarrivo = new JLabel("Porto arrivo:");
        portoarrivo.setFont(new Font("serif", Font.PLAIN, 20));
        portoarrivo.setPreferredSize(new Dimension(300,50));
        portoarrivobox = new JComboBox(porti);
        portoarrivobox.setPreferredSize(new Dimension(100, 30));
        portoarrivobox.addItemListener(this);

        JLabel portoparetnzascalo = new JLabel("Porto partenza scalo:");
        portoparetnzascalo.setFont(new Font("serif", Font.PLAIN, 20));
        portoparetnzascalo.setPreferredSize(new Dimension(300,50));
        portopartenzascalobox = new JComboBox(porti);
        portopartenzascalobox.setPreferredSize(new Dimension(100, 30));
        portopartenzascalobox.addItemListener(this);

        JLabel portoarrivoscalo = new JLabel("Porto arrivo scalo:");
        portoarrivoscalo.setFont(new Font("serif", Font.PLAIN, 20));
        portoarrivoscalo.setPreferredSize(new Dimension(300,50));
        portoarrivoscalobox = new JComboBox(porti);
        portoarrivoscalobox.setPreferredSize(new Dimension(100, 30));
        portoarrivoscalobox.addItemListener(this);



        PortoPanel.setBorder(BorderFactory.createMatteBorder(2,2,0,0,Color.lightGray));



        JPanel ModorAnnCorsPanel = new JPanel();
        ModorAnnCorsPanel.setPreferredSize(new Dimension(450,300));
        JLabel ModorAnnCorsLabel = new JLabel(" Annulla o ritarda una corsa:");
        ModorAnnCorsLabel.setFont(new Font("sans serif", Font.PLAIN, 30));
        ModorAnnCorsLabel.setPreferredSize(new Dimension(450, 50));
        JButton ModorAnnBtn = new JButton("Modifica corsa");
        ModorAnnBtn.setPreferredSize(new Dimension(200,50));
        ModorAnnBtn.setBackground(Color.RED);
        ModorAnnBtn.addActionListener(e -> {
            new ModCorsa();
        });

        ModorAnnCorsPanel.setBorder(BorderFactory.createMatteBorder(2,2,0,0,Color.lightGray));


        JPanel SendModPanel = new JPanel();
        SendModPanel.setPreferredSize(new Dimension(1400, 150));

        JButton ModBtn = new JButton("Invia Modifiche");
        ModBtn.setPreferredSize(new Dimension(200,50));
        ModBtn.setBackground(Color.green);

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
        ServizioPanel.add(datePicker);
        ServizioPanel.add(FineServizioLabel);
        ServizioPanel.add(datePicker2);
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
        PortoPanel.add(portoparetnza);
        PortoPanel.add(portopartenzabox);
        PortoPanel.add(portoarrivo);
        PortoPanel.add(portoarrivobox);
        PortoPanel.add(portoparetnzascalo);
        PortoPanel.add(portopartenzascalobox);
        PortoPanel.add(portoarrivoscalo);
        PortoPanel.add(portoarrivoscalobox);


        ModorAnnCorsPanel.add(ModorAnnCorsLabel);
        ModorAnnCorsPanel.add(ModorAnnBtn);

        SendModPanel.add(ModBtn);


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

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
