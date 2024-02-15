package dao;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface BigliettoDAO {
    void add_biglietto_intero(float importo_totale, float sovrapprezzo_tot, int n_bagagli, String veicolo, Timestamp prenotazione, int corsa, int passeggero);
    void add_biglietto_ridotto(float importo_totale,float sovrapprezzo_tot,int n_bagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore);
    void retrieve_biglietti_interi(Integer id_passeggero, ArrayList<Float> importo_totale, ArrayList<Float> Sovrapprezzo_tot, ArrayList<Integer> n_bagagli, ArrayList<String> veicolo, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa);
    void retrieve_biglietti_ridotti(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore);
}
