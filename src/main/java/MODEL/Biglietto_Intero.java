package MODEL;

import java.sql.Timestamp;

public class Biglietto_Intero extends Biglietto{
    public Biglietto_Intero(Float importo_totale, Float sovrapprezzo_totale, Integer n_bagagli, String veicolo, Timestamp prenotazione,Corsa corsa){
        super(importo_totale,sovrapprezzo_totale,n_bagagli,veicolo,prenotazione,corsa);
    }
}
