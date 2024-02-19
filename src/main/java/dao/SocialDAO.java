package dao;

import java.util.ArrayList;

/**
 * l'interfaccia socialDAO
 * */
public interface SocialDAO {
    /**
     * metodo per richiamare la funzione retrieve social sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param idCompagnia l'id della compagnia di cui prendere i social
     * */
    void retrieveSocial(int idCompagnia, ArrayList<String> nomeSocial, ArrayList<String> indirizzoSocial);

}
