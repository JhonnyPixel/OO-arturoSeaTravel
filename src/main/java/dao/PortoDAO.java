package dao;

import java.util.ArrayList;

/**
 * l'interfaccia portoDAO
 * */
public interface PortoDAO {
    /**
     * metodo per richiamare la funzione retrieve porti sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * */
    void retrievePorti(ArrayList<Integer> idPorto, ArrayList<String> indirizzo, ArrayList<String> comune, ArrayList<String> telInfo);
}
