package MODEL;

import java.sql.Timestamp;

public class Biglietto_Intero extends Biglietto{

    String veicolo;
    public Biglietto_Intero(Float importo_totale, Float sovrapprezzo_totale, Integer n_bagagli, String veicolo, Timestamp prenotazione,Integer corsa){
        super(importo_totale,sovrapprezzo_totale,n_bagagli,prenotazione,corsa);
        this.veicolo=veicolo;
    }

    public String getVeicolo() {
        return veicolo;
    }
}
