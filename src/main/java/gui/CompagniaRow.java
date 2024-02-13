package gui;

import MODEL.Compagnia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;

public class CompagniaRow extends JPanel implements MouseListener {
    public CompagniaRow(Integer id_corsa, Time orario_partenza, Time orario_arrivo, String porto_partenza,String porto_arrivo,String porto_scalo,Date data_inizio_servizio, Date data_fine_servizio, String giorni_servizio_attivo, Float sconto_residente,
                        Float prezzo_intero, Float prezzo_ridotto, Float sovr_veicoli, Float sovr_bagagli, Float sovr_prenotazioni){
        this.addMouseListener(this);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
        JLabel info=new JLabel("<html>IdCorsa: "+id_corsa+"<br/>GiorniServizio-> lunedi:"+giorni_servizio_attivo.charAt(0)+" martedi:"+giorni_servizio_attivo.charAt(1)+" mercoledi:"+giorni_servizio_attivo.charAt(2)+" giovedi:"+giorni_servizio_attivo.charAt(3)+" venerdi:"+giorni_servizio_attivo.charAt(4)+" sabato:"+giorni_servizio_attivo.charAt(5)+" domenica:"+giorni_servizio_attivo.charAt(6)+
                "<br/>Data inizio Servizio:"+data_inizio_servizio+" Data fine servizio:"+data_fine_servizio+
                "<br/>Orario partenza: "+orario_partenza+" Orario arrivo: "+orario_arrivo+
                "<br/>partenza: "+porto_partenza+" arrivo: "+porto_arrivo+" scalo: "+porto_scalo+
                "<br/>"+"</html>");

        info.setPreferredSize(new Dimension(400,100));

        info.setHorizontalAlignment(JLabel.RIGHT);

        this.add(info);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        new ModParCorsa();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
