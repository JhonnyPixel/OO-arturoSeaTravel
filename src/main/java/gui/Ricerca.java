package gui;

import controller.Controller;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Calendar;

public class Ricerca extends JPanel implements ItemListener,ActionListener {


    JRadioButton ARBtn = new JRadioButton("Andata e Ritorno");
    JRadioButton SABtn = new JRadioButton("Solo andata");
    JComboBox Ritornobox = new JComboBox();
    JComboBox Andatabox= new JComboBox();
    JButton btnCerca;
    String[] veicolo = {"nessuno","moto","automobile"};

    String[] bagagli = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9"};

    JDatePickerImpl datePicker;
    JDatePickerImpl datePicker2;

    JComboBox boxbagagli;

    static JCheckBox ResBox;

    static Integer n_bagagli;
    JComboBox boxveicolo;
    static String veicolo_selezionato;
    FramePasseggero frameChiamante;


    public Ricerca(FramePasseggero frameChiamante,ArrayList<String> partenze,ArrayList<String> arrivi) {

        this.frameChiamante=frameChiamante;

        Andatabox.setPreferredSize(new Dimension(500, 30));
        Ritornobox.setEditable(true);
        Andatabox.setEditable(true);

        ArrayList<String> tratte=new ArrayList<>();
        for(int i=0;i<partenze.size();i++){
            if(!tratte.contains(partenze.get(i)+"->"+arrivi.get(i))){
                tratte.add(partenze.get(i)+"->"+arrivi.get(i));
                Ritornobox.addItem(partenze.get(i)+"->"+arrivi.get(i));
                Andatabox.addItem(partenze.get(i)+"->"+arrivi.get(i));
            }

        }
        Controller.getController().setTratte(tratte);



        this.setPreferredSize(new Dimension(750, 810));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));


        JPanel SelectCorsa = new JPanel();
        SelectCorsa.setPreferredSize(new Dimension(750, 200));

        JLabel TratteLabel = new JLabel("Seleziona le tratte");
        TratteLabel.setIcon(new ImageIcon("src/trag.png"));
        TratteLabel.setPreferredSize(new Dimension(700, 30));
        TratteLabel.setFont(new Font("Serif", Font.PLAIN, 18));


        ARBtn.setPreferredSize(new Dimension(500, 30));
        ARBtn.setFont(new Font("Serif", Font.PLAIN, 18));
        ARBtn.setHorizontalAlignment(JRadioButton.RIGHT);
        ARBtn.addItemListener(this);



        SABtn.setFont(new Font("Serif", Font.PLAIN, 18));
        SABtn.setHorizontalAlignment(JRadioButton.RIGHT);
        SABtn.addItemListener(this);



        Ritornobox.setPreferredSize(new Dimension(500, 30));
        Ritornobox.addItemListener(this);


        JPanel SelectDate = new JPanel();
        SelectDate.setPreferredSize(new Dimension(750, 100));
        JLabel DateLabel = new JLabel("Seleziona le date");
        DateLabel.setIcon(new ImageIcon("src/calendario.png"));
        DateLabel.setPreferredSize(new Dimension(700, 30));
        DateLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        UtilDateModel model2 = new UtilDateModel();
        Properties p2 = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());


        ARBtn.setSelected(true);


        JPanel SelectPV = new JPanel();
        SelectPV.setPreferredSize(new Dimension(750, 100));
        JLabel BagagliLabel = new JLabel("Seleziona i bagagli");
        BagagliLabel.setIcon(new ImageIcon("src/icons8-persona-24 (1).png"));
        BagagliLabel.setPreferredSize(new Dimension(350, 30));
        BagagliLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        JLabel VeicoloLabel = new JLabel("Seleziona il veicolo ");
        VeicoloLabel.setIcon(new ImageIcon("src/auto.png"));
        VeicoloLabel.setPreferredSize(new Dimension(350, 30));
        VeicoloLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        boxbagagli = new JComboBox(bagagli);
        boxbagagli.setSelectedItem(bagagli[0]);
        n_bagagli=Integer.parseInt(bagagli[0]);
        boxbagagli.setPreferredSize(new Dimension(100, 30));
        boxbagagli.addActionListener(this);


        JLabel space = new JLabel("");
        space.setPreferredSize(new Dimension(250, 30));

        boxveicolo = new JComboBox(veicolo);
        boxveicolo.setSelectedItem(veicolo[0]);
        veicolo_selezionato=veicolo[0];
        boxveicolo.setPreferredSize(new Dimension(100, 30));
        boxveicolo.addActionListener(this);

        JLabel space2 = new JLabel("");
        space2.setPreferredSize(new Dimension(200, 30));

        JPanel SelectRes = new JPanel();
        SelectRes.setPreferredSize(new Dimension(750, 50));
        ResBox = new JCheckBox("Tariffa residente");
        ResBox.setPreferredSize(new Dimension(700, 30));


        JPanel RicercaPanel = new JPanel();
        RicercaPanel.setPreferredSize(new Dimension(750, 100));
        btnCerca = new JButton("CERCA");
        btnCerca.setBackground(Color.ORANGE);
        btnCerca.setPreferredSize(new Dimension(200, 50));
        btnCerca.setBorder(BorderFactory.createEmptyBorder());
        btnCerca.setFocusable(false);


        btnCerca.addActionListener(this);


        SelectCorsa.add(TratteLabel);
        SelectCorsa.add(ARBtn);
        SelectCorsa.add(SABtn);
        SelectCorsa.add(Andatabox);
        SelectCorsa.add(Ritornobox);

        SelectDate.add(DateLabel);
        SelectDate.add(datePicker);
        SelectDate.add(datePicker2);

        SelectPV.add(BagagliLabel);
        SelectPV.add(VeicoloLabel);
        SelectPV.add(boxbagagli);
        SelectPV.add(space);
        SelectPV.add(boxveicolo);
        SelectPV.add(space2);


        SelectRes.add(ResBox);

        RicercaPanel.add(btnCerca);

        this.add(SelectCorsa);
        this.add(SelectDate);
        this.add(SelectPV);
        this.add(SelectRes);
        this.add(RicercaPanel);
        this.setVisible(true);

    }

    public static int getNBagagli(){
        return n_bagagli;
    }

    public static String getVeicolo(){
        return veicolo_selezionato;
    }

    public static boolean useScontoResidente(){
        return ResBox.isSelected();
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {

            if (e.getItem() == ARBtn) {
                System.out.println("--->");
                SABtn.setSelected(false);
                datePicker2.setVisible(true);
                Ritornobox.setSelectedItem(Andatabox.getSelectedItem());

            }if (e.getItem() == SABtn) {
                System.out.println("<---");
                ARBtn.setSelected(false);
                Ritornobox.setEnabled(false);
                Ritornobox.setSelectedItem(null);
                datePicker2.setVisible(false);
            }
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {

            if (e.getItem() == ARBtn) {
                SABtn.setSelected(true);
                datePicker2.setVisible(false);
            } else if (e.getItem() == SABtn) {
                ARBtn.setSelected(true);
                Ritornobox.setEnabled(true);
                datePicker2.setVisible(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==boxbagagli){
            System.out.println(Integer.parseInt((String)boxbagagli.getSelectedItem()));
            this.n_bagagli=Integer.parseInt((String)boxbagagli.getSelectedItem());
        }
        if(e.getSource()==boxveicolo){
            this.veicolo_selezionato=boxveicolo.getSelectedItem().toString();
        }
        if(e.getSource()==btnCerca){
            String[] andata=new String[2];
            String[] ritorno=new String[2];
            if(Andatabox.getSelectedIndex() != -1){
                andata=Andatabox.getSelectedItem().toString().split("-");
                andata[1]=andata[1].replace(">","");
            }else{
                andata[0]=null;
                andata[1]=null;
            }

            if(Ritornobox.getSelectedIndex() != -1){
                ritorno=Ritornobox.getSelectedItem().toString().split("-");
                ritorno[1]=ritorno[1].replace(">","");
            }else{
                ritorno[0]=null;
                ritorno[1]=null;
            }


            System.out.println(andata[0]+" "+andata[1]);
            System.out.println(ritorno[0]+" "+ritorno[1]);

            Calendar cal = Calendar.getInstance();

            Integer GiornoAndata=null;
            Integer GiornoRitorno=null;

            java.util.Date selectedDateAndata = (java.util.Date) datePicker.getModel().getValue();

            java.sql.Date selectedDateAndataSQL=(selectedDateAndata==null)? null :new java.sql.Date(selectedDateAndata.getTime());


            java.util.Date selectedDateRitorno = (java.util.Date) datePicker2.getModel().getValue();
            java.sql.Date selectedDateRitornoSQL=(selectedDateRitorno==null)? null : new java.sql.Date(selectedDateRitorno.getTime());




            System.out.println(GiornoAndata + " "+ GiornoRitorno);
            frameChiamante.UpdateResultsCorse(andata[0],andata[1],ritorno[0],ritorno[1],  selectedDateAndataSQL, selectedDateRitornoSQL,Ritornobox.isEnabled());

        }
    }


}