package gui;

import MODEL.Compagnia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.Time;

public class CompagniaRow extends JPanel implements MouseListener {

    Integer id_corsa;
    Time orario_partenza;
    Time orario_arrivo;

    Time orario_partenza_scalo;
    Time orario_arrivo_scalo;
    String porto_partenza;
    String porto_arrivo;
    String porto_scalo;
    Date data_inizio_servizio;
    Date data_fine_servizio;
    String giorni_servizio_attivo;
    Float sconto_residente;
    Float prezzo_intero;
    Float prezzo_ridotto;
    Float sovr_veicoli;
    Float sovr_bagagli;
    Float sovr_prenotazioni;

    FrameCompagnia frameCompagnia;

    public CompagniaRow(FrameCompagnia frameCompagnia,Integer id_corsa, Time orario_partenza, Time orario_arrivo,Time orario_partenza_scalo, Time orario_arrivo_scalo, String porto_partenza,String porto_arrivo,String porto_scalo,Date data_inizio_servizio, Date data_fine_servizio, String giorni_servizio_attivo, Float sconto_residente,
                        Float prezzo_intero, Float prezzo_ridotto, Float sovr_veicoli, Float sovr_bagagli, Float sovr_prenotazioni){
        this.id_corsa=id_corsa;
        this.orario_partenza=orario_partenza;
        this.orario_arrivo=orario_arrivo;
        this.orario_partenza_scalo=orario_partenza_scalo;
        this.orario_arrivo_scalo=orario_arrivo_scalo;
        this.porto_partenza=porto_partenza;
        this.porto_arrivo=porto_arrivo;
        this.porto_scalo=porto_scalo;
        this.data_inizio_servizio=data_inizio_servizio;
        this.data_fine_servizio=data_fine_servizio;
        this.sconto_residente=sconto_residente;
        this.prezzo_intero=prezzo_intero;
        this.prezzo_ridotto=prezzo_ridotto;
        this.sovr_veicoli=sovr_veicoli;
        this.sovr_bagagli=sovr_bagagli;
        this.sovr_prenotazioni=sovr_prenotazioni;
        this.giorni_servizio_attivo=giorni_servizio_attivo;
        this.frameCompagnia=frameCompagnia;
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
        new ModParCorsa(frameCompagnia,id_corsa, orario_partenza,  orario_arrivo,orario_partenza_scalo,orario_arrivo_scalo, porto_partenza, porto_arrivo,porto_scalo, data_inizio_servizio,  data_fine_servizio,  giorni_servizio_attivo, sconto_residente,
                 prezzo_intero,  prezzo_ridotto,  sovr_veicoli,  sovr_bagagli,  sovr_prenotazioni);
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
