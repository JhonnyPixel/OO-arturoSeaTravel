package model;

import java.sql.Date;
import java.sql.Time;

/**
 * il tipo corsa
 * */
public class Corsa {

    private Integer idCorsa;
    private Time orarioPartenza;
    private Time orarioArrivo;

    private Time orarioPartenzaScalo;
    private Time orarioArrivoScalo;
    private Date dataInizioServizio;
    private Date dataFineServizio;
    private boolean[] giorniServizioAttivo;
    private float scontoResidente;
    private float prezzoIntero;
    private float prezzoRidotto;
    private float sovrapprezzoVeicolo;
    private float sovrapprezzoBagaglio;
    private float sovrapprezzoPrenotazione;
    private Natante natante;
    private Ritardo ritardoCorsa;
    private Annullamento annullamentoCorsa;
    private Compagnia compagnia;
    private Porto portoArrivo;
    private Porto portoPartenza;

    private Porto portoScalo;

    public Corsa(Integer idCorsa, Time orario_partenza, Time orario_arrivo, Time orario_partenza_scalo, Time orario_arrivo_scalo, Date d_i_s, Date d_f_s, boolean[] b, float s_r, float p_i, float p_r, float s_v, float s_b, float s_p, Natante n, Porto p_p, Porto p_a, Porto p_s, Compagnia c, Ritardo r, Annullamento ann){
        this.idCorsa = idCorsa;
        this.orarioPartenza =orario_partenza;
        this.orarioArrivo =orario_arrivo;
        this.orarioPartenzaScalo =orario_partenza_scalo;
        this.orarioArrivoScalo =orario_arrivo_scalo;
        this.dataInizioServizio =d_i_s;
        this.dataFineServizio =d_f_s;
        this.giorniServizioAttivo =b;
        this.scontoResidente =s_r;
        this.prezzoIntero =p_i;
        this.prezzoRidotto =p_r;
        this.sovrapprezzoVeicolo =s_v;
        this.sovrapprezzoBagaglio =s_b;
        this.sovrapprezzoPrenotazione =s_p;
        this.natante =n;
        this.portoPartenza =p_p;
        this.portoArrivo =p_a;
        this.portoScalo =p_s;
        this.compagnia =c;
        this.ritardoCorsa =r;
        this.annullamentoCorsa =ann;
    }

    /**
     * getter del porto partenza della corsa
     * @return porto di partenza della corsa
     * */

    public Porto getPortoPartenza(){
        return portoPartenza;
    }

    /**
     * getter del porto arrivo della corsa
     * @return porto di arrivo della corsa
     * */
    public Porto getPortoArrivo(){
        return portoArrivo;
    }

    /**
     * getter della compagnia della corsa
     * @return compagnia che offre la corsa
     * */

    public Compagnia getCompagnia(){
        return compagnia;
    }

    /**
     * getter dell orario di partenza della corsa
     * @return orario di partenza della corsa
     * */

    public Time getOrarioPartenza() {
        return orarioPartenza;
    }

    /**
     * getter dell orario di arrivo della corsa
     * @return orario di arrivo della corsa
     * */

    public Time getOrarioArrivo() { return orarioArrivo;
    }

    /**
     * getter dei giorni di servizio attivo della corsa
     * @return array di 7 boolean rappresentante i giorni della settimana in cui la corsa è attiva
     * */
    public boolean[] getGiorniServizioAttivo(){
        return giorniServizioAttivo;
    }

    /**
     * getter del prezzo intero della corsa
     * @return prezzo intero della corsa
     * */

    public float getPrezzoIntero() {
        return prezzoIntero;
    }

    /**
     * getter del prezzo ridotto della corsa
     * @return prezzo ridotto della corsa
     * */

    public float getPrezzoRidotto(){
        return prezzoRidotto;
    }

    /**
     * getter dello sconto residente della corsa
     * @return sconto residente della corsa
     * */

    public float getScontoResidente() {
        return scontoResidente;
    }


    /**
     * getter del sovrapprezzo veicolo della corsa
     * @return sovrapprezzo veicolo della corsa
     * */

    public float getSovrVeicolo() {
        return sovrapprezzoVeicolo;
    }

    /**
     * getter del sovrapprezzo bagaglio della corsa
     * @return sovrapprezzo bagaglio della corsa
     * */

    public float getSovrBagagli(){
        return sovrapprezzoBagaglio;
    }

    /**
     * getter del sovrapprezzo prenotazione della corsa
     * @return sovrapprezzo prenotazione della corsa
     * */

    public float getSovrPrenotazione() {
        return sovrapprezzoPrenotazione;
    }

    /**
     * getter dell id della corsa
     * @return l'id della corsa
     * */

    public int getIdCorsa() {
        return idCorsa;
    }

    /**
     * getter della data di inizio servizio della corsa
     * @return data inizio servizio della corsa
     * */

    public Date getDataInizioServizio() {
        return dataInizioServizio;
    }

    /**
     * getter della data di fine servizio della corsa
     * @return data fine servizio della corsa
     * */

    public Date getDataFineServizio() {
        return dataFineServizio;
    }

    /**
     * getter del natante della corsa
     * @return il natante che esegue la corsa
     * */

    public Natante getNatante() {
        return natante;
    }

    /**
     * getter del porto di scalo della corsa
     * @return porto di scalo della corsa
     * */

    public Porto getPortoScalo() {
        return portoScalo;
    }

    /**
     * getter del ritardo della corsa
     * @return ritardo della corsa
     * */

    public Ritardo getRitardo() {
        return ritardoCorsa;
    }

    /**
     * getter dell annullamento della corsa
     * @return l annullamento della corsa
     * */

    public Annullamento getAnnullamento() {
        return annullamentoCorsa;
    }

    /**
     * getter dell orario di partenza dallo scalo della corsa
     * @return l orario di partenza dallo scalo della corsa
     * */

    public Time getOrarioPartenzaScalo() {
        return orarioPartenzaScalo;
    }

    /**
     * getter dell orario di arrivo allo scalo della corsa
     * @return l orario di arrivo allo scalo della corsa
     * */
    public Time getOrarioArrivoScalo() {
        return orarioArrivoScalo;
    }
}
