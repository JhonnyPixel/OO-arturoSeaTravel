package MODEL;

import java.sql.Timestamp;

public class Biglietto_Ridotto extends Biglietto{
    private Passeggero Accompagnatore;

    public Biglietto_Ridotto(Float importo_totale, Float sovrapprezzo_totale, Integer n_bagagli, String veicolo, Timestamp prenotazione, Corsa corsa,Passeggero Accompagnatore){
        super(importo_totale,sovrapprezzo_totale,n_bagagli,veicolo,prenotazione,corsa);
        this.Accompagnatore=Accompagnatore;
    }
}
