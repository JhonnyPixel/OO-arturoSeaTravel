package MODEL;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

public class Corsa {

    private Integer Id_corsa;
    private Time Orario_Partenza;
    private Time Orario_Arrivo;
    private Date Data_Inizio_Servizio;
    private Date Data_Fine_Servizio;
    private boolean[] Giorni_Servizio_Attivo;
    private float Sconto_Residente;
    private float Prezzo_Intero;
    private float Prezzo_Ridotto;
    private float Sovrapprezzo_Veicolo;
    private float Sovrapprezzo_Bagaglio;
    private float Sovrapprezzo_Prenotazione;
    private Natante Natante;
    private Ritardo ritardo_corsa;// ------------------||||
    private Annullamento annullamento_corsa;// navigabilita in questo lato ci servirà
    private Compagnia Compagnia;
    private Porto Porto_Arrivo;
    private Porto Porto_Partenza;

    private Porto Porto_Scalo;

    public Corsa(Integer id_corsa,Time o_p,Time o_a,Date d_i_s,Date d_f_s,boolean[] b,float s_r,float p_i,float p_r,float s_v,float s_b,float s_p,Natante n,Porto p_p,Porto p_a,Porto p_s,Compagnia c,Ritardo r,Annullamento ann){
        this.Id_corsa=id_corsa;
        this.Orario_Partenza=o_p;
        this.Orario_Arrivo=o_a;
        this.Data_Inizio_Servizio=d_i_s;
        this.Data_Fine_Servizio=d_f_s;
        this.Giorni_Servizio_Attivo=b;
        this.Sconto_Residente=s_r;
        this.Prezzo_Intero=p_i;
        this.Prezzo_Ridotto=p_r;
        this.Sovrapprezzo_Veicolo=s_v;
        this.Sovrapprezzo_Bagaglio=s_b;
        this.Sovrapprezzo_Prenotazione=s_p;
        this.Natante=n;
        this.Porto_Partenza=p_p;
        this.Porto_Arrivo=p_a;
        this.Porto_Scalo=p_s;
        this.Compagnia=c;
        this.ritardo_corsa=r;
        this.annullamento_corsa=ann;
    }

    public Porto getPorto_Partenza(){
        return Porto_Partenza;
    }
    public Porto getPorto_Arrivo(){
        return Porto_Arrivo;
    }

    public Compagnia getCompagnia(){
        return Compagnia;
    }

    public Time getOrario_Partenza() {
        return Orario_Partenza;
    }

    public Time getOrario_Arrivo() { return Orario_Arrivo;
    }
    public boolean[] getGiorniServizioAttivo(){
        return Giorni_Servizio_Attivo;
    }


    public float getPrezzoIntero() {
        return Prezzo_Intero;
    }

    public float getPrezzoRidotto(){
        return Prezzo_Ridotto;
    }

    public float getScontoResidente() {
        return Sconto_Residente;
    }

    public float getSovrVeicolo() {
        return Sovrapprezzo_Veicolo;
    }

    public float getSovrBagagli(){
        return Sovrapprezzo_Bagaglio;
    }

    public float getSovrPrenotazione() {
        return Sovrapprezzo_Prenotazione;
    }

    public int getId_corsa() {
        return Id_corsa;
    }
}
