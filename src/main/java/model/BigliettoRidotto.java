package model;

import java.sql.Timestamp;
/**
 * il tipo biglietto ridotto
 * */
public class BigliettoRidotto extends Biglietto{
    private Passeggero accompagnatore;

    public BigliettoRidotto(Float importoTotale, Float sovrapprezzoTotale, Integer nBagagli, Timestamp prenotazione, Corsa corsa, Passeggero accompagnatore){
        super(importoTotale,sovrapprezzoTotale,nBagagli,prenotazione,corsa);
        this.accompagnatore =accompagnatore;
    }

    /**
     * getter del passeggero accompagnatore del biglietto ridotto
     * @return il passeggero accompagnatore del biglietto ridotto
     * */
    public Passeggero getAccompagnatore() {
        return accompagnatore;
    }
}
