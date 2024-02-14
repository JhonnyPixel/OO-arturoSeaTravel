package gui;

import com.github.lgooddatepicker.components.TimePicker;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;

public class ModCorsa extends JFrame {

    String[] motivo = {"maltempo","esplosione","mi scocciavo di guidare","guasto","dovevo cagare"};
    public ModCorsa(Integer id_corsa){
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setResizable(false);

        JPanel TopPanel = new JPanel();
        TopPanel.setPreferredSize(new Dimension(1000,100));

        TopPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));


        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 200));
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS));

        JLabel labelCanc = new JLabel("Cancella corse");
        labelCanc.setFont(new Font("serif", Font.BOLD, 40));
        JLabel MotivoCanc = new JLabel("Inserisci il motivo della cancellazione: ");
        MotivoCanc.setFont(new Font("serif",Font.PLAIN, 20 ));
        JTextArea Boxmotivo = new JTextArea();
        Boxmotivo.setPreferredSize(new Dimension(150, 150));

        SpinnerModel rimborsoModel = new SpinnerNumberModel(0.0f, 0.0f, 1000.0f, 0.1f);
        JSpinner spinnerRimborso=new JSpinner(rimborsoModel);


        JButton CancelBtn = new JButton("Cancella corsa");
        CancelBtn.setPreferredSize(new Dimension(200,50));
        CancelBtn.setBackground(Color.RED);
        CancelBtn.addActionListener(e -> {
            Controller.getController().add_annullamento(Boxmotivo.getText(),Float.valueOf(spinnerRimborso.getValue().toString())<=0.05f?null:Float.valueOf(spinnerRimborso.getValue().toString()),id_corsa,null);
            this.setVisible(false);

        });


        leftPanel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.lightGray));


        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(480, 200));
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS));

        JLabel labelRitardo = new JLabel("Inserisci Ritardo:");
        labelRitardo.setFont(new Font("serif", Font.BOLD, 40));

        JLabel MotivoRitardo = new JLabel("Inserisci il motivo del ritardo: ");
        MotivoRitardo.setFont(new Font("serif",Font.PLAIN, 20 ));
        JTextArea BoxRitardo = new JTextArea();
        BoxRitardo.setPreferredSize(new Dimension(150, 150));


        JLabel OreRitardo = new JLabel ("Inserisci le ore di ritardo");
        TimePicker tempoRitardo=new TimePicker();

        JLabel space2 = new JLabel("");
        space2.setPreferredSize(new Dimension(480, 15) );


        JButton RitBtn = new JButton("Aggiorna Ritardo");
        RitBtn.setBackground(Color.RED);
        RitBtn.setPreferredSize(new Dimension(200, 50));

        RitBtn.addActionListener(e->{
            Controller.getController().add_ritardo(BoxRitardo.getText(), Time.valueOf(tempoRitardo.getTime()),id_corsa);
            this.setVisible(false);
        });

        rightPanel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.lightGray));

        leftPanel.add(labelCanc);
        leftPanel.add(MotivoCanc);
        leftPanel.add(Boxmotivo);
        leftPanel.add(new JLabel("Scegli il rimborso:"));
        leftPanel.add(spinnerRimborso);
        leftPanel.add(CancelBtn);


        rightPanel.add(labelRitardo);
        rightPanel.add(OreRitardo);
        rightPanel.add(tempoRitardo);
        rightPanel.add(MotivoRitardo);
        rightPanel.add(BoxRitardo);
        rightPanel.add(space2);
        rightPanel.add(RitBtn);

        this.add(TopPanel);
        this.add(leftPanel);
        this.add(rightPanel);

        this.setVisible(true);
    }

}