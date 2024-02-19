package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class bigliettoRow extends JPanel {
    public bigliettoRow(Integer id_corsa, Float importo_tot,Float sovr_tot, Integer n_bagagli, Timestamp prenotazione, String veicolo, Integer id_accompagnatore){
        this.setPreferredSize(new Dimension(600,100));
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

        JPanel panel1=new JPanel();
        panel1.setPreferredSize(new Dimension(200,100));
        panel1.setLayout(new BorderLayout(0,0));

        ImageIcon logo=new ImageIcon("src/calendario.png");
        JLabel idCorsa=new JLabel("ID_CORSA: "+id_corsa);
        idCorsa.setIcon(logo);
        idCorsa.setHorizontalTextPosition(JLabel.RIGHT);
        idCorsa.setHorizontalAlignment(JLabel.CENTER);
        idCorsa.setVerticalAlignment(JLabel.CENTER);
        panel1.add(idCorsa);


        JPanel panel2=new JPanel();
        panel2.setPreferredSize(new Dimension(200,100));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        JPanel panel2top=new JPanel();
        JPanel panel2bottom=new JPanel();
        panel2top.setPreferredSize(new Dimension(200,50));
        panel2bottom.setPreferredSize(new Dimension(200,50));

        JLabel importoTotale=new JLabel("Importo_totale: "+importo_tot);



        String s1="<html>Sovrapprezzo: "+sovr_tot;
        if(id_accompagnatore!=null){
            s1= s1+"<br/>Id_accompagnatore: "+id_accompagnatore;
        }
        s1=s1 + "</html>";
        JLabel sovrapprezzoTotale=new JLabel(s1);

        importoTotale.setHorizontalAlignment(JLabel.CENTER);
        sovrapprezzoTotale.setHorizontalAlignment(JLabel.CENTER);
        panel2top.add(importoTotale);
        panel2bottom.add(sovrapprezzoTotale);

        panel2.add(panel2top);
        panel2.add(panel2bottom);




        JPanel panel3=new JPanel();
        panel3.setPreferredSize(new Dimension(200,100));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        JPanel panel3top=new JPanel();
        JPanel panel3bottom=new JPanel();
        panel3top.setPreferredSize(new Dimension(200,50));
        panel3bottom.setPreferredSize(new Dimension(200,50));

        JLabel prenotazioneLabel=new JLabel("prenotazione: "+prenotazione);
        JLabel veicoloBagagli=new JLabel("veicolo: "+veicolo +" bagagli("+n_bagagli+")");

        prenotazioneLabel.setHorizontalAlignment(JLabel.CENTER);
        veicoloBagagli.setHorizontalAlignment(JLabel.CENTER);
        panel3top.add(prenotazioneLabel);
        panel3bottom.add(veicoloBagagli);

        panel3.add(panel3top);
        panel3.add(panel3bottom);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
    }


}
