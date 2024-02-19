package dao;

import java.sql.Time;

/**
 * l'interfaccia ritardoDAO
 * */
public interface RitardoDAO {
    /**
     * metodo per richiamare la funzione add ritardo sul database
     * @param motivazione motivazione del ritardo da inserire
     * @param ritardo il ritardo da inserire
     * @param idCorsa l'id della corsa a cui aggiungere il ritardo
     * */
    void addRitardo(String motivazione, Time ritardo, Integer idCorsa);

}
