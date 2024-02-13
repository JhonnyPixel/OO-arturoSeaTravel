package MODEL;

import java.sql.Timestamp;

public class Biglietto_Ridotto extends Biglietto{
    private Integer Accompagnatore;

    public Biglietto_Ridotto(Float importo_totale, Float sovrapprezzo_totale, Integer n_bagagli, Timestamp prenotazione, Integer corsa,Integer Accompagnatore){
        super(importo_totale,sovrapprezzo_totale,n_bagagli,prenotazione,corsa);
        this.Accompagnatore=Accompagnatore;
    }


    public Integer getAccompagnatore() {
        return Accompagnatore;
    }
}
