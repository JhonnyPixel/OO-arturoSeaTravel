package MODEL;

import java.sql.Timestamp;

public class Biglietto {
    protected float Importo_Totale;
    protected float Sovrapprezzo_Totale;
    protected int N_bagagli;
    protected Timestamp Prenotazione;

    protected Integer id_corsa;

    public Biglietto(float Importo_Totale,float Sovrapprezzo_Totale,int N_bagagli,Timestamp Prenotazione,Integer corsa){
        this.Importo_Totale=Importo_Totale;
        this.Sovrapprezzo_Totale=Sovrapprezzo_Totale;
        this.N_bagagli=N_bagagli;
        this.Prenotazione=Prenotazione;
        this.id_corsa=corsa;
    }


    public Float getImporto() {
        return Importo_Totale;
    }

    public Float getSovr() {
        return  Sovrapprezzo_Totale;
    }

    public Integer getNBagagli() {
        return N_bagagli;
    }

    public Timestamp getPrenotazione() {
        return Prenotazione;
    }

    public Integer getIdCorsa() {
        return id_corsa;
    }
}
