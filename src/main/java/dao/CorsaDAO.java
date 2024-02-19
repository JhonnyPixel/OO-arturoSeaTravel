package dao;

import java.sql.*;
import java.util.ArrayList;

/**
 * l'interfaccia corsaDAO
 * */
public interface CorsaDAO {
    /**
     * metodo per richimare la funzione filtra corse sul database
     *
     * @param idCorsa l'id della corsa per cui filtrare
     * @param portoPartenza il porto partenza per cui filtrare
     * @param portoArrivo il porto arrivo per cui filtrare
     * @param dataPartenza la data per cui per cui filtrare
     * @param orarioPartenza l orario per cui filtrare
     * @param prezzo il prezzo per cui filtrare
     * @param tipoNatante il tipo natante per cui filtrare
     * */
     void filtraCorse(Integer idCorsa, ArrayList<Integer> idCorse, String portoPartenza, String portoArrivo , Date dataPartenza, Time orarioPartenza , Float prezzo , String  tipoNatante, ArrayList<Time> orariPartenza, ArrayList<Time> orariArrivo,
                      ArrayList<Time> orariPartenzaScalo, ArrayList<Time> orariArrivoScalo, ArrayList<Date> dateInizioServizio,
                      ArrayList<Date> dateFineServizio, ArrayList<String> giorniServizioAttivo, ArrayList<Float> scontiResidente,
                      ArrayList<Float> prezziInteri, ArrayList<Float> prezziRidotti, ArrayList<Float> sovrVeicoli, ArrayList<Float> sovrBagagli,
                      ArrayList<Float> sovrPrenotazioni, ArrayList<Integer> idNatanti, ArrayList<String> nomiNatanti, ArrayList<String> trasporti, ArrayList<String> tipiNatanti,
                      ArrayList<Integer> idPortiPartenza, ArrayList<String> indirizziPortiPartenza, ArrayList<String> comuniPortiPartenza, ArrayList<String> telefoniPortiPartenza,
                      ArrayList<Integer>idPortiScalo, ArrayList<String> indirizziPortiScalo, ArrayList<String> comuniPortiScalo, ArrayList<String> telefoniPortiScalo,
                      ArrayList<Integer> idPortiArrivo, ArrayList<String> indirizziPortiArrivo, ArrayList<String> comuniPortiArrivo, ArrayList<String> telefoniPortiArrivo,
                      ArrayList<Integer> idCompagnie, ArrayList<String> telefoniCompagnie, ArrayList<String> mailCompagnie, ArrayList<String> sitiWebCompagnie, ArrayList<String> loginCompagnie,
                      ArrayList<String> passwordCompagnie, ArrayList<String> nomiCompagnie, ArrayList<String> motivazioniRitardi, ArrayList<Time> tempiRitardi, ArrayList<String> motivazioniAnnullamenti,
                      ArrayList<Float> rimborsi, ArrayList<Integer> prossimi);
    /**
     * metodo che permette alla compagnia di modificare o creare una nuova corsa nel database.
     *
     * @param idCorsa id della corsa
     * @param orarioPartenza orarioPartenza della corsa
     * @param orarioArrivo orarioArrivo della corsa
     * @param orarioPartenzaScalo orarioPartenzaScalo della corsa
     * @param orarioArrivoScalo orarioArrivoScalo della corsa
     * @param dataInizioServizio dataInizioServizio della corsa
     * @param dataFineServizio dataFineServizio della corsa
     * @param giorniServizioAttivo giorniServizioAttivo della corsa
     * @param sovrPrenotazione sovrPrenotazione della corsa
     * @param sovrBagaglio sovrBagaglio della corsa
     * @param sovrVeicolo sovrVeicolo della corsa
     * @param prezzoIntero prezzoIntero della corsa
     * @param prezzoRidotto prezzoRidotto della corsa
     * @param scontoResidente scontoResidente della corsa
     * @param portoPartenza portoPartenza della corsa
     * @param portoArrivo portoArrivo della corsa
     * @param portoScalo portoScalo della corsa
     * @param compagnia compagnia della corsa
     * @param natante natante della corsa
     * */

    void createUpdateCorsa(Integer idCorsa , Time orarioPartenza , Time orarioArrivo ,
                           Time orarioPartenzaScalo , Time orarioArrivoScalo,
                           Date dataInizioServizio , Date dataFineServizio ,
                           String giorniServizioAttivo , Float sovrPrenotazione ,
                           Float sovrBagaglio , Float sovrVeicolo , Float prezzoIntero ,
                           Float prezzoRidotto , Float scontoResidente , Integer portoPartenza ,
                           Integer portoArrivo , Integer portoScalo, Integer compagnia , Integer natante )throws SQLException;

    /**
     * metodo che richiama la funzione retrieve corse sul database
     *
     * @param idCompagnia l'id della compagnia di cui recuperare le corse
     * */

    void retrieveCorseCompagnia(Integer idCompagnia, ArrayList<Integer> idCorsa,
                                ArrayList<Time> orarioPartenza, ArrayList<Time> orarioArrivo,
                                ArrayList<Time> orarioPartenzaScalo, ArrayList<Time> orarioArrivoScalo,
                                ArrayList<Date> dataInizioServizio, ArrayList<Date> dataFineServizio,
                                ArrayList<String> giorniServizioAttivo, ArrayList<Float> sovrPrenotazione,
                                ArrayList<Float> sovrBagaglio, ArrayList<Float> sovrVeicolo,
                                ArrayList<Float> prezzoIntero, ArrayList<Float> prezzoRidotto,
                                ArrayList<Float> scontoResidente, ArrayList<Integer> portoPartenza,
                                ArrayList<Integer> portoArrivo, ArrayList<Integer> portoScalo,
                                ArrayList<Integer> natante,
                                ArrayList<String> p1Indirizzo, ArrayList<String> p1Comune,
                                ArrayList<String> p1TelInfo, ArrayList<String> p2Indirizzo,
                                ArrayList<String> p2Comune, ArrayList<String> p2TelInfo,
                                ArrayList<String> psIndirizzo,
                                ArrayList<String> psComune, ArrayList<String> psTelInfo,
                                ArrayList<String> nomeNatante, ArrayList<String> trasporta,
                                ArrayList<String> tipoNatante);
    /**
     * metodo che richiama la funzione delete corsa sul database
     *
     * @param idCorsa l'id della corsa da cancellare
     * */
     void deleteCorsa(Integer idCorsa);




}
