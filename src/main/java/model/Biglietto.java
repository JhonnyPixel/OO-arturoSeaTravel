package model;

import java.sql.Timestamp;

/**
 * il tipo biglietto
 * */
public class Biglietto {
    protected float importoTotale;
    protected float sovrapprezzoTotale;
    protected int nBagagli;
    protected Timestamp prenotazione;

    protected Corsa corsa;

    public Biglietto(float importoTotale,float sovrapprezzoTotale,int nBagagli,Timestamp prenotazione,Corsa corsa){
        this.importoTotale =importoTotale;
        this.sovrapprezzoTotale =sovrapprezzoTotale;
        this.nBagagli =nBagagli;
        this.prenotazione =prenotazione;
        this.corsa =corsa;
    }

    /**
     * getter dell importo del biglietto
     * @return l'importo del biglietto
     * */


    public Float getImporto() {
        return importoTotale;
    }

    /**
     * getter del sovrapprezzo del biglietto
     * @return il sovrapprezzo totale del biglietto
     * */

    public Float getSovr() {
        return sovrapprezzoTotale;
    }

    /**
     * getter del numero di bagagli del biglietto
     * @return il numero di bagagli imbarcati specificati dal biglietto
     * */

    public Integer getNBagagli() {
        return nBagagli;
    }

    /**
     * getter della data di prenotazione del biglietto
     * @return la data di prenotazione del biglietto
     * */

    public Timestamp getPrenotazione() {
        return prenotazione;
    }

    /**
     * getter della corsa a cui fa riferimento il biglietto
     * @return la corsa a cui fa riferimento il biglietto
     * */

    public Corsa getCorsa() {
        return corsa;
    }
}
