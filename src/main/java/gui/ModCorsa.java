package gui;

import javax.swing.*;
import java.awt.*;

public class ModCorsa extends JFrame {

    String[] motivo = {"maltempo","esplosione","mi scocciavo di guidare","guasto","dovevo cagare"};
    public ModCorsa(){
        this.setSize(1000,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setResizable(false);

        JPanel TopPanel = new JPanel();
        TopPanel.setPreferredSize(new Dimension(1000,100));

        TopPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));


        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(500, 200));

        JLabel labelCanc = new JLabel("Cancella corse");
        labelCanc.setFont(new Font("serif", Font.BOLD, 40));
        JLabel MotivoCanc = new JLabel("Inserisci il motivo della cancellazione: ");
        MotivoCanc.setFont(new Font("serif",Font.PLAIN, 20 ));
        JComboBox Boxmotivo = new JComboBox(motivo);
        Boxmotivo.setPreferredSize(new Dimension(150, 30));

        JLabel space1 = new JLabel("");
        space1.setPreferredSize(new Dimension(480, 50) );

        JButton CancelBtn = new JButton("Cancella corsa");
        CancelBtn.setPreferredSize(new Dimension(200,50));
        CancelBtn.setBackground(Color.RED);
        CancelBtn.addActionListener(e -> {
            new RSureMessage();
        });


        leftPanel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.lightGray));


        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(480, 200));

        JLabel labelRitardo = new JLabel("Inserisci Ritardo:");
        labelRitardo.setFont(new Font("serif", Font.BOLD, 40));


        JLabel OreRitardo = new JLabel ("Inserisci le ore di ritardo");
        OreRitardo.setFont(new Font("serif", Font.PLAIN, 20));
        SpinnerModel spinmax1 = new SpinnerNumberModel(1, 1, 23, 1);
        JSpinner RitoreSpinner = new JSpinner(spinmax1);
        RitoreSpinner.setPreferredSize(new Dimension(80,20));

        JLabel minRitardo = new JLabel ("Inserisci i minuti di ritardo");
        minRitardo.setFont(new Font("serif", Font.PLAIN, 20));
        SpinnerModel spinmax2 = new SpinnerNumberModel(1, 1, 59, 1);
        JSpinner RitminSpinner = new JSpinner(spinmax2);
        RitminSpinner.setPreferredSize(new Dimension(80,20));

        JLabel space2 = new JLabel("");
        space2.setPreferredSize(new Dimension(480, 15) );


        JButton RitBtn = new JButton("Aggiorna Ritardo");
        RitBtn.setBackground(Color.RED);
        RitBtn.setPreferredSize(new Dimension(200, 50));

        rightPanel.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.lightGray));

        leftPanel.add(labelCanc);
        leftPanel.add(MotivoCanc);
        leftPanel.add(Boxmotivo);
        leftPanel.add(space1);
        leftPanel.add(CancelBtn);


        rightPanel.add(labelRitardo);
        rightPanel.add(OreRitardo);
        rightPanel.add(RitoreSpinner);
        rightPanel.add(minRitardo);
        rightPanel.add(RitminSpinner);
        rightPanel.add(space2);
        rightPanel.add(RitBtn);

        this.add(TopPanel);
        this.add(leftPanel);
        this.add(rightPanel);

        this.setVisible(true);
    }

}