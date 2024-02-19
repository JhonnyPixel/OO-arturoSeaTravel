package dao;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * l'interfaccia bigliettoDAO
 * */
public interface BigliettoDAO {

    /**
     * metodo per richiamare la funzione add biglietto intero sul database
     *
     *
     * @param importoTotale importoTotale del biglietto
     * @param sovrapprezzoTot sovrapprezzoTot del biglietto
     * @param nBagagli numero di bagagli del passeggero
     * @param veicolo veicolo portato dal passeggero
     * @param prenotazione prenotazione del biglietto
     * @param corsa corsa del biglietto
     * @param passeggero passeggero del biglietto
     * */
    void addBigliettoIntero(float importoTotale, float sovrapprezzoTot, int nBagagli, String veicolo, Timestamp prenotazione, int corsa, int passeggero);

    /**
     * metodo per richiamare la funzione add biglietto ridotto sul database
     *
     * @param importoTotale importoTotale del biglietto
     * @param sovrapprezzoTot sovrapprezzoTot del biglietto
     * @param nBagagli numero di bagagli del passeggero
     * @param prenotazione prenotazione del biglietto
     * @param corsa corsa del biglietto
     * @param passeggero passeggero del biglietto
     * @param accompagnatore accompagnatore del passeggero
     * */
    void addBigliettoRidotto(float importoTotale, float sovrapprezzoTot, int nBagagli, Timestamp prenotazione, int corsa, int passeggero, int accompagnatore);
    /**
     * metodo per richiamare la funzione retrieve biglietti interi sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param idPasseggero l'id del passeggero di cui prendere i biglietti interi
     * */
    void retrieveBigliettiInteri(Integer idPasseggero, ArrayList<Float> importoTotale, ArrayList<Float> sovrapprezzoTot, ArrayList<Integer> nBagagli, ArrayList<String> veicolo, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa);
    /**
     * metodo per richiamare la funzione retrieve biglietti ridotti sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param idPasseggero l'id del passeggero di cui prendere i biglietti ridotti
     * */
    void retrieveBigliettiRidotti(Integer idPasseggero, ArrayList<Float> importoTotale, ArrayList<Float> sovrapprezzoTot, ArrayList<Integer> nBagagli, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa, ArrayList<Integer> accompagnatore);
}
