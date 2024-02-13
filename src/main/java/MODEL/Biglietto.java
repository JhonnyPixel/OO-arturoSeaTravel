package MODEL;

import java.sql.Timestamp;

public class Biglietto {
    protected float Importo_Totale;
    protected float Sovrapprezzo_Totale;
    protected int N_bagagli;
    protected String Veicolo;
    protected Timestamp Prenotazione;

    protected Corsa corsa;

    public Biglietto(float Importo_Totale,float Sovrapprezzo_Totale,int N_bagagli,String Veicolo,Timestamp Prenotazione,Corsa corsa){
        this.Importo_Totale=Importo_Totale;
        this.Sovrapprezzo_Totale=Sovrapprezzo_Totale;
        this.N_bagagli=N_bagagli;
        this.Veicolo=Veicolo;
        this.Prenotazione=Prenotazione;
        this.corsa=corsa;
    }


}
