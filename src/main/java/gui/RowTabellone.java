package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class RowTabellone extends JPanel{
    public RowTabellone(String partenza, String arrivo,String scalo, Time orarioPartenza, Time orarioArrivo,Time orarioPartenzaScalo,Time orarioArrivoScalo, String nomeCompagnia,String motivazioneRitardo,Time ritardo,String motivazioneAnullamento){
        this.setPreferredSize(new Dimension(1000,100));
        this.setBackground(Color.pink);
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

        JPanel panel1=new JPanel();
        //panel1.setBackground(Color.green);
        panel1.setPreferredSize(new Dimension(250,100));
        panel1.setLayout(new BorderLayout(0,0));
        panel1.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.lightGray));


        ImageIcon compagniaIcon= new ImageIcon("src/calendario.png");
        JLabel nomeCompagniaLabel=new JLabel(nomeCompagnia);
        nomeCompagniaLabel.setIcon(compagniaIcon);
        nomeCompagniaLabel.setHorizontalAlignment(JLabel.CENTER);
        nomeCompagniaLabel.setHorizontalTextPosition(JLabel.RIGHT);

        panel1.add(nomeCompagniaLabel,BorderLayout.CENTER);

        JPanel panel2=new JPanel();
        //panel2.setBackground(Color.gray);
        panel2.setPreferredSize(new Dimension(250,100));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        panel2.setBorder(BorderFactory.createMatteBorder(0,2,2,0,Color.lightGray));


        JLabel portoPartenza=new JLabel(partenza);
        JLabel portoArrivo=new JLabel(scalo!=null?scalo + " "+ arrivo:arrivo);
        portoPartenza.setHorizontalAlignment(JLabel.CENTER);
        portoArrivo.setHorizontalAlignment(JLabel.CENTER);


        JPanel panel2Top = new JPanel();
        panel2Top.setPreferredSize(new Dimension(248,50));
        panel2Top.setLayout(new BorderLayout(0,0));
        panel2Top.add(portoPartenza);

        JPanel panel2Bottom=new JPanel();
        panel2Bottom.setPreferredSize(new Dimension(248,48));
        panel2Bottom.setLayout(new BorderLayout(0,0));
        panel2Bottom.add(portoArrivo);

        panel2.add(panel2Top);
        panel2.add(panel2Bottom);

        JPanel panel3=new JPanel();
        //panel3.setBackground(Color.yellow);
        panel3.setPreferredSize(new Dimension(250,100));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        panel3.setBorder(BorderFactory.createMatteBorder(0,2,2,0,Color.lightGray));


        JLabel datetimePartenza=new JLabel(scalo!=null?"partenza: "+orarioPartenza+" - "+orarioArrivoScalo:"partenza: "+orarioPartenza);
        JLabel datetimeArrivo=new JLabel(scalo!=null?"arrivo: "+orarioPartenzaScalo+" - "+orarioArrivo:"arrivo: "+orarioArrivo);
        datetimePartenza.setHorizontalAlignment(JLabel.CENTER);
        datetimeArrivo.setHorizontalAlignment(JLabel.CENTER);


        JPanel panel3Top = new JPanel();
        panel3Top.setPreferredSize(new Dimension(248,50));
        panel3Top.setLayout(new BorderLayout(0,0));
        panel3Top.add(datetimePartenza);

        JPanel panel3Bottom=new JPanel();
        panel3Bottom.setPreferredSize(new Dimension(248,48));
        panel3Bottom.setLayout(new BorderLayout(0,0));
        panel3Bottom.add(datetimeArrivo);

        panel3.add(panel3Top);
        panel3.add(panel3Bottom);

        JPanel panel4=new JPanel();
        //panel4.setBackground(Color.white);
        panel4.setPreferredSize(new Dimension(250,100));
        panel4.setBorder(BorderFactory.createMatteBorder(0,2,2,0,Color.lightGray));
        panel4.setLayout(new BorderLayout(0,0));

        JLabel info;
        if(motivazioneRitardo!=null){
            if(motivazioneAnullamento!=null){
                info=new JLabel(motivazioneAnullamento);
            }else{
                info=new JLabel(motivazioneRitardo + "---Ritardo accumulato: " + ritardo);
            }
            info.setFont(new Font("sans serif",Font.BOLD,40));
            TextAnimation txtA=new TextAnimation(info);
            panel4.add(txtA);
        }




        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);

    }
}
