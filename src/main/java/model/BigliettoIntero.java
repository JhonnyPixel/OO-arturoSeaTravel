package model;

import java.sql.Timestamp;
/**
 * il tipo biglietto intero
 * */
public class BigliettoIntero extends Biglietto{

    private String veicolo;
    public BigliettoIntero(Float importoTotale, Float sovrapprezzoTotale, Integer nBagagli, String veicolo, Timestamp prenotazione, Corsa corsa){
        super(importoTotale,sovrapprezzoTotale,nBagagli,prenotazione,corsa);
        this.veicolo=veicolo;
    }

    /**
     * getter del veicolo del biglietto intero
     * @return il veicolo imbarcato specificato dal biglietto intero
     * */

    public String getVeicolo() {
        return veicolo;
    }
}
