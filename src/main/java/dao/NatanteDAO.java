package dao;

import java.util.ArrayList;

/**
 * l'interfaccia natanteDAO
 * */
public interface NatanteDAO {
    /**
     * metodo per richiamare la funzione retrieve natanti sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * */
    void retrieveNatanti(ArrayList<Integer> idNatante, ArrayList<String> nome, ArrayList<String> trasporta, ArrayList<String> tipo);
}
