package impl_dao_postgres;

import dao.CorsaDAO;
import java.util.ArrayList;
import database.ConnessioneDatabase;
import java.sql.*;
import java.sql.Time;

/**
 * l'implementazione dell interfaccia corsaDAO per postgres
 * */

public class implCorsaDAO implements CorsaDAO {
    private Connection connection;
    public implCorsaDAO() {
       connection=ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * metodo per richimare la funzione filtra corse sul database postgres
     *
     * @param idCorsa l'id della corsa per cui filtrare
     * @param portoPartenza il porto partenza per cui filtrare
     * @param portoArrivo il porto arrivo per cui filtrare
     * @param dataPartenza la data per cui per cui filtrare
     * @param orarioPartenza l orario per cui filtrare
     * @param prezzo il prezzo per cui filtrare
     * @param tipoNatante il tipo natante per cui filtrare
     * */

    public void filtraCorse(Integer idCorsa, ArrayList<Integer> idCorse, String portoPartenza, String portoArrivo , Date dataPartenza, Time orarioPartenza , Float prezzo , String  tipoNatante, ArrayList<Time> orariPartenza, ArrayList<Time> orariArrivo,
                            ArrayList<Time> orariPartenzaScalo, ArrayList<Time> orariArrivoScalo, ArrayList<Date> dateInizioServizio,
                            ArrayList<Date> dateFineServizio, ArrayList<String> giorniServizioAttivo, ArrayList<Float> scontiResidente,
                            ArrayList<Float> prezziInteri, ArrayList<Float> prezziRidotti, ArrayList<Float> sovrVeicoli, ArrayList<Float> sovrBagagli,
                            ArrayList<Float> sovrPrenotazioni, ArrayList<Integer> idNatanti, ArrayList<String> nomiNatanti, ArrayList<String> trasporti, ArrayList<String> tipiNatanti,
                            ArrayList<Integer> idPortiPartenza, ArrayList<String> indirizziPortiPartenza, ArrayList<String> comuniPortiPartenza, ArrayList<String> telefoniPortiPartenza,
                            ArrayList<Integer>idPortiScalo, ArrayList<String> indirizziPortiScalo, ArrayList<String> comuniPortiScalo, ArrayList<String> telefoniPortiScalo,
                            ArrayList<Integer> idPortiArrivo, ArrayList<String> indirizziPortiArrivo, ArrayList<String> comuniPortiArrivo, ArrayList<String> telefoniPortiArrivo,
                            ArrayList<Integer> idCompagnie, ArrayList<String> telefoniCompagnie, ArrayList<String> mailCompagnie, ArrayList<String> sitiWebCompagnie, ArrayList<String> loginCompagnie,
                            ArrayList<String> passwordCompagnie, ArrayList<String> nomiCompagnie, ArrayList<String> motivazioniRitardi, ArrayList<Time> tempiRitardi, ArrayList<String> motivazioniAnnullamenti,
                            ArrayList<Float> rimborsi, ArrayList<Integer> prossimi)
    {
        try {
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call filtra_corse(?,?,?,?,?,?,?) }";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setObject(2,idCorsa);
            preparedCall.setString(3,portoPartenza);
            preparedCall.setString(4,portoArrivo);
            preparedCall.setDate(5,dataPartenza);
            preparedCall.setTime(6,orarioPartenza);
            preparedCall.setObject(7,prezzo);
            preparedCall.setString(8,tipoNatante);

            preparedCall.registerOutParameter(1,Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);





            while(rs.next()) {


                idCorse.add(rs.getInt("id_corsa"));
                orariPartenza.add(rs.getTime("Orario_Partenza"));
                orariArrivo.add(rs.getTime("Orario_Arrivo"));
                orariPartenzaScalo.add(rs.getTime("orario_partenza_scalo"));
                orariArrivoScalo.add(rs.getTime("orario_arrivo_scalo"));
                dateInizioServizio.add(rs.getDate("Data_Inizio_Servizio"));
                dateFineServizio.add(rs.getDate("Data_Fine_Servizio"));
                giorniServizioAttivo.add(rs.getString("Giorni_Servizio_Attivo"));

                scontiResidente.add(rs.getFloat("Sconto_residente"));
                prezziInteri.add(rs.getFloat("Prezzo_intero"));
                prezziRidotti.add(rs.getFloat("Prezzo_ridotto"));
                sovrVeicoli.add(rs.getFloat("Sovr_Veicolo"));
                sovrBagagli.add(rs.getFloat("Sovr_Bagaglio"));
                sovrPrenotazioni.add(rs.getFloat("Sovr_Prenotazione"));

                idNatanti.add(rs.getInt("natante"));
                nomiNatanti.add(rs.getString("Nome"));
                trasporti.add(rs.getString("Trasporta"));
                tipiNatanti.add(rs.getString("Tipo"));

                idPortiPartenza.add(rs.getInt("p1_id_porto"));
                indirizziPortiPartenza.add(rs.getString("P1_Indirizzo"));
                comuniPortiPartenza.add(rs.getString("P1_Comune"));
                telefoniPortiPartenza.add(rs.getString("P1_Tel_Info"));

                idPortiScalo.add(rs.getInt("ps_id_porto"));
                indirizziPortiScalo.add(rs.getString("Ps_Indirizzo"));
                comuniPortiScalo.add(rs.getString("Ps_Comune"));
                telefoniPortiScalo.add(rs.getString("Ps_Tel_Info"));

                idPortiArrivo.add(rs.getInt("p2_id_porto"));
                indirizziPortiArrivo.add(rs.getString("P2_Indirizzo"));
                comuniPortiArrivo.add(rs.getString("P2_Comune"));
                telefoniPortiArrivo.add(rs.getString("P2_Tel_Info"));

                idCompagnie.add(rs.getInt("C_compagnia"));
                telefoniCompagnie.add(rs.getString("C_Telefono"));
                mailCompagnie.add(rs.getString("C_Mail"));
                sitiWebCompagnie.add(rs.getString("C_Sito_Web"));
                loginCompagnie.add(rs.getString("C_Login"));
                passwordCompagnie.add(rs.getString("C_Password")); //da dare in pasto a compagnia che chiam sup del padre
                nomiCompagnie.add(rs.getString("C_Nome"));

                motivazioniRitardi.add(rs.getString("R_Motivazione"));
                tempiRitardi.add(rs.getTime("R_Ritardo"));

                motivazioniAnnullamenti.add(rs.getString("A_Descrizione"));
                rimborsi.add(rs.getFloat("A_Rimborso"));
                prossimi.add(rs.getInt("A_Prossimo"));
            }

            rs.close();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao leggi corse: "+e.getMessage());

        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("impossibile chiudere la connessione: "+e.getMessage());
            }
        }
    }


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

    public void createUpdateCorsa(Integer idCorsa , Time orarioPartenza , Time orarioArrivo ,
                                  Time orarioPartenzaScalo , Time orarioArrivoScalo,
                                  Date dataInizioServizio , Date dataFineServizio ,
                                  String giorniServizioAttivo , Float sovrPrenotazione ,
                                  Float sovrBagaglio , Float sovrVeicolo , Float prezzoIntero ,
                                  Float prezzoRidotto , Float scontoResidente , Integer portoPartenza ,
                                  Integer portoArrivo , Integer portoScalo, Integer compagnia , Integer natante )throws SQLException{

            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query = " call create_update_corsa(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            CallableStatement preparedCall = connection.prepareCall(query);
            preparedCall.setObject(1, idCorsa,Types.INTEGER);
            preparedCall.setTime(2, orarioPartenza);
            preparedCall.setTime(3, orarioArrivo);
            preparedCall.setTime(4, orarioPartenzaScalo);
            preparedCall.setTime(5, orarioArrivoScalo);
            preparedCall.setDate(6,dataInizioServizio);
            preparedCall.setDate(7,dataFineServizio);
            preparedCall.setString(8,giorniServizioAttivo);
            preparedCall.setObject(9,sovrPrenotazione,Types.FLOAT);
            preparedCall.setObject(10,sovrBagaglio,Types.FLOAT);
            preparedCall.setObject(11,sovrVeicolo,Types.FLOAT);
            preparedCall.setFloat(12,prezzoIntero);
            preparedCall.setFloat(13,prezzoRidotto);
            preparedCall.setObject(14,scontoResidente,Types.FLOAT);
            preparedCall.setInt(15,portoPartenza);
            preparedCall.setInt(16,portoArrivo);
            preparedCall.setObject(17,portoScalo,Types.INTEGER);
            preparedCall.setInt(18,compagnia);
            preparedCall.setInt(19,natante);

            preparedCall.execute();

            preparedCall.close();


    }

    /**
     * metodo che richiama la funzione retrieve corse sul database postgres
     *
     * @param idCompagnia l'id della compagnia di cui recuperare le corse
     * */

    public void retrieveCorseCompagnia(Integer idCompagnia, ArrayList<Integer> idCorsa,
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
                                       ArrayList<String> tipoNatante){

        try {
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_corse(?) }";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setInt(2,idCompagnia);


            preparedCall.registerOutParameter(1,Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);





            while(rs.next()) {
                idCorsa.add(rs.getInt("id_corsa"));
                orarioPartenza.add(rs.getTime("Orario_Partenza"));
                orarioArrivo.add(rs.getTime("Orario_Arrivo"));
                orarioPartenzaScalo.add(rs.getTime("orario_partenza_scalo"));
                orarioArrivoScalo.add(rs.getTime("orario_arrivo_scalo"));
                dataInizioServizio.add(rs.getDate("Data_Inizio_Servizio"));
                dataFineServizio.add(rs.getDate("Data_Fine_Servizio"));
                giorniServizioAttivo.add(rs.getString("Giorni_Servizio_Attivo"));

                scontoResidente.add(rs.getFloat("Sconto_residente"));
                prezzoIntero.add(rs.getFloat("Prezzo_intero"));
                prezzoRidotto.add(rs.getFloat("Prezzo_ridotto"));
                sovrVeicolo.add(rs.getFloat("Sovr_Veicolo"));
                sovrBagaglio.add(rs.getFloat("Sovr_Bagaglio"));
                sovrPrenotazione.add(rs.getFloat("Sovr_Prenotazione"));

                natante.add(rs.getInt("natante"));
                nomeNatante.add(rs.getString("Nome_natante"));
                trasporta.add(rs.getString("Trasporta"));
                tipoNatante.add(rs.getString("Tipo_natante"));

                portoPartenza.add(rs.getInt("porto_partenza"));
                portoArrivo.add(rs.getInt("porto_arrivo"));
                p1Indirizzo.add(rs.getString("p1_indirizzo"));
                p1Comune.add(rs.getString("p1_comune"));
                p1TelInfo.add(rs.getString("p1_tel_info"));

                p2Indirizzo.add(rs.getString("p2_indirizzo"));
                p2Comune.add(rs.getString("p2_comune"));
                p2TelInfo.add(rs.getString("p2_tel_info"));

                portoScalo.add(rs.getInt("porto_scalo"));
                psIndirizzo.add(rs.getString("ps_indirizzo"));
                psComune.add(rs.getString("ps_comune"));
                psTelInfo.add(rs.getString("ps_tel_info"));


            }

            rs.close();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao leggi corse: "+e.getMessage());

        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("impossibile chiudere la connessione: "+e.getMessage());
            }
        }

    }

    /**
     * metodo che richiama la funzione delete corsa sul database postgres
     *
     * @param idCorsa l'id della corsa da cancellare
     * */
    public void deleteCorsa(Integer idCorsa){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query=" call delete_corsa(?) ";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setInt(1,idCorsa);

            preparedCall.execute();

            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao delete_corsa: "+e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("impossibile chiudere la connessione: "+e.getMessage());
            }
        }

    }



}
