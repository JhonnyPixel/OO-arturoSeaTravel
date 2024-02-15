package impl_dao_postgres;

import dao.CorsaDAO;
import java.util.ArrayList;
import database.ConnessioneDatabase;
import java.sql.*;
import java.sql.Time;

public class implCorsaDAO implements CorsaDAO {
    private Connection connection;
    public implCorsaDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }


    public void filtra_corse(Integer id_corsa,ArrayList<Integer> id_corse,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza ,Float prezzo ,String  tipo_natante,ArrayList<Time> Orari_Partenza,ArrayList<Time> Orari_Arrivo,
                             ArrayList<Time> Orari_Partenza_Scalo,ArrayList<Time> Orari_Arrivo_Scalo, ArrayList<Date> Date_Inizio_Servizio,
                            ArrayList<Date> Date_Fine_Servizio,ArrayList<String> Giorni_Servizio_Attivo,ArrayList<Float> Sconti_residente,
                            ArrayList<Float> Prezzi_interi,ArrayList<Float> Prezzi_ridotti,ArrayList<Float> Sovr_Veicoli,ArrayList<Float> Sovr_Bagagli,
                            ArrayList<Float> Sovr_Prenotazioni,ArrayList<Integer> id_natanti,ArrayList<String> Nomi_natanti,ArrayList<String> Trasporti,ArrayList<String> Tipi_natanti,
                            ArrayList<Integer> id_porti_partenza,ArrayList<String> Indirizzi_porti_partenza,ArrayList<String> Comuni_porti_partenza,ArrayList<String> Telefoni_porti_partenza,
                             ArrayList<Integer>id_porti_scalo,ArrayList<String> Indirizzi_porti_scalo,ArrayList<String> Comuni_porti_scalo,ArrayList<String> Telefoni_porti_scalo,
                             ArrayList<Integer> id_porti_arrivo,ArrayList<String> Indirizzi_porti_arrivo,ArrayList<String> Comuni_porti_arrivo,ArrayList<String> Telefoni_porti_arrivo,
                            ArrayList<Integer> id_compagnie,ArrayList<String> Telefoni_compagnie,ArrayList<String> Mail_compagnie,ArrayList<String> Siti_web_compagnie,ArrayList<String> login_compagnie,
                            ArrayList<String> password_compagnie,ArrayList<String> nomi_compagnie,ArrayList<String> Motivazioni_ritardi,ArrayList<Time> Tempi_ritardi,ArrayList<String> Motivazioni_annullamenti,
                            ArrayList<Float> Rimborsi,ArrayList<Integer> Prossimi)
    {
        try {
            connection.setAutoCommit(false);
            String query="{ ? = call filtra_corse(?,?,?,?,?,?,?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setObject(2,id_corsa);
            p_s.setString(3,portoPartenza);
            p_s.setString(4,portoArrivo);
            p_s.setDate(5,dataPartenza);
            p_s.setTime(6,orarioPartenza);
            p_s.setObject(7,prezzo);
            p_s.setString(8,tipo_natante);

            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {


                id_corse.add(rs.getInt("id_corsa"));
                Orari_Partenza.add(rs.getTime("Orario_Partenza"));
                Orari_Arrivo.add(rs.getTime("Orario_Arrivo"));
                Orari_Partenza_Scalo.add(rs.getTime("orario_partenza_scalo"));
                Orari_Arrivo_Scalo.add(rs.getTime("orario_arrivo_scalo"));
                Date_Inizio_Servizio.add(rs.getDate("Data_Inizio_Servizio"));
                Date_Fine_Servizio.add(rs.getDate("Data_Fine_Servizio"));
                Giorni_Servizio_Attivo.add(rs.getString("Giorni_Servizio_Attivo"));

                Sconti_residente.add(rs.getFloat("Sconto_residente"));
                Prezzi_interi.add(rs.getFloat("Prezzo_intero"));
                Prezzi_ridotti.add(rs.getFloat("Prezzo_ridotto"));
                Sovr_Veicoli.add(rs.getFloat("Sovr_Veicolo"));
                Sovr_Bagagli.add(rs.getFloat("Sovr_Bagaglio"));
                Sovr_Prenotazioni.add(rs.getFloat("Sovr_Prenotazione"));

                //prendo i dati del natante e lo creo
                id_natanti.add(rs.getInt("natante"));
                Nomi_natanti.add(rs.getString("Nome"));
                Trasporti.add(rs.getString("Trasporta"));
                Tipi_natanti.add(rs.getString("Tipo"));

                //Natante nat = new Natante(Nome_natante, Trasporta, Tipo_natante);
                id_porti_partenza.add(rs.getInt("p1_id_porto"));
                Indirizzi_porti_partenza.add(rs.getString("P1_Indirizzo"));
                Comuni_porti_partenza.add(rs.getString("P1_Comune"));
                Telefoni_porti_partenza.add(rs.getString("P1_Tel_Info"));

                id_porti_scalo.add(rs.getInt("ps_id_porto"));
                Indirizzi_porti_scalo.add(rs.getString("Ps_Indirizzo"));
                Comuni_porti_scalo.add(rs.getString("Ps_Comune"));
                Telefoni_porti_scalo.add(rs.getString("Ps_Tel_Info"));

                id_porti_arrivo.add(rs.getInt("p2_id_porto"));
                Indirizzi_porti_arrivo.add(rs.getString("P2_Indirizzo"));
                Comuni_porti_arrivo.add(rs.getString("P2_Comune"));
                Telefoni_porti_arrivo.add(rs.getString("P2_Tel_Info"));

                //Porto porto_arrivo = new Porto(Indirizzo, Comune, Tel);
                id_compagnie.add(rs.getInt("C_compagnia"));
                Telefoni_compagnie.add(rs.getString("C_Telefono"));
                Mail_compagnie.add(rs.getString("C_Mail"));
                Siti_web_compagnie.add(rs.getString("C_Sito_Web"));
                login_compagnie.add(rs.getString("C_Login"));
                password_compagnie.add(rs.getString("C_Password")); //da dare in pasto a compagnia che chiam sup del padre
                nomi_compagnie.add(rs.getString("C_Nome"));

                Motivazioni_ritardi.add(rs.getString("R_Motivazione"));
                Tempi_ritardi.add(rs.getTime("R_Ritardo"));

                Motivazioni_annullamenti.add(rs.getString("A_Descrizione"));
                Rimborsi.add(rs.getFloat("A_Rimborso"));
                Prossimi.add(rs.getInt("A_Prossimo"));
            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao leggi corse");
            throw new RuntimeException(e);
        }
    }


    public void create_update_corsa(Integer Id_corsa ,Time Orario_partenza , Time Orario_arrivo ,
                                    Time Orario_partenza_scalo , Time Orario_arrivo_scalo,
                                    Date Data_inizio_servizio , Date Data_fine_servizio ,
                                    String Giorni_Servizio_Attivo , Float Sovr_prenotazione ,
                                    Float Sovr_bagaglio , Float Sovr_veicolo , Float Prezzo_intero ,
                                    Float Prezzo_ridotto , Float Sconto_residente , Integer Porto_partenza ,
                                    Integer Porto_arrivo ,Integer Porto_scalo, Integer Compagnia , Integer Natante )throws SQLException{

            connection.setAutoCommit(true);
            String query = " call create_update_corsa(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setObject(1, Id_corsa,Types.INTEGER);
            p_s.setTime(2, Orario_partenza);
            p_s.setTime(3, Orario_arrivo);
            p_s.setTime(4, Orario_partenza_scalo);
            p_s.setTime(5, Orario_arrivo_scalo);
            p_s.setDate(6,Data_inizio_servizio);
            p_s.setDate(7,Data_fine_servizio);
            p_s.setString(8,Giorni_Servizio_Attivo);
            p_s.setObject(9,Sovr_prenotazione,Types.FLOAT);
            p_s.setObject(10,Sovr_bagaglio,Types.FLOAT);
            p_s.setObject(11,Sovr_veicolo,Types.FLOAT);
            p_s.setFloat(12,Prezzo_intero);
            p_s.setFloat(13,Prezzo_ridotto);
            p_s.setObject(14,Sconto_residente,Types.FLOAT);
            p_s.setInt(15,Porto_partenza);
            p_s.setInt(16,Porto_arrivo);
            p_s.setObject(17,Porto_scalo,Types.INTEGER);
            p_s.setInt(18,Compagnia);
            p_s.setInt(19,Natante);

            p_s.execute();


    }

    public void retrieve_corse_compagnia(Integer id_compagnia,ArrayList<Integer> id_corsa,
                                         ArrayList<Time> orario_partenza,ArrayList<Time> orario_arrivo,
                                         ArrayList<Time> orario_partenza_scalo,ArrayList<Time> orario_arrivo_scalo,
                                         ArrayList<Date> data_inizio_servizio,ArrayList<Date> data_fine_servizio,
                                         ArrayList<String> giorni_servizio_attivo,ArrayList<Float> sovr_prenotazione,
                                         ArrayList<Float> sovr_bagaglio,ArrayList<Float> sovr_veicolo,
                                         ArrayList<Float> prezzo_intero,ArrayList<Float> prezzo_ridotto,
                                         ArrayList<Float> sconto_residente,ArrayList<Integer> porto_partenza,
                                         ArrayList<Integer> porto_arrivo,ArrayList<Integer> porto_scalo,
                                         ArrayList<Integer> natante,
                                         ArrayList<String> p1_indirizzo, ArrayList<String> p1_comune,
                                         ArrayList<String> p1_tel_info, ArrayList<String> p2_indirizzo,
                                         ArrayList<String> p2_comune, ArrayList<String> p2_tel_info,
                                         ArrayList<String> ps_indirizzo,
                                         ArrayList<String> ps_comune,ArrayList<String> ps_tel_info,
                                         ArrayList<String> nome_natante,ArrayList<String> trasporta,
                                         ArrayList<String> tipo_natante){

        try {
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_corse(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(2,id_compagnia);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                id_corsa.add(rs.getInt("id_corsa"));
                orario_partenza.add(rs.getTime("Orario_Partenza"));
                orario_arrivo.add(rs.getTime("Orario_Arrivo"));
                orario_partenza_scalo.add(rs.getTime("orario_partenza_scalo"));
                orario_arrivo_scalo.add(rs.getTime("orario_arrivo_scalo"));
                data_inizio_servizio.add(rs.getDate("Data_Inizio_Servizio"));
                data_fine_servizio.add(rs.getDate("Data_Fine_Servizio"));
                giorni_servizio_attivo.add(rs.getString("Giorni_Servizio_Attivo"));

                sconto_residente.add(rs.getFloat("Sconto_residente"));
                prezzo_intero.add(rs.getFloat("Prezzo_intero"));
                prezzo_ridotto.add(rs.getFloat("Prezzo_ridotto"));
                sovr_veicolo.add(rs.getFloat("Sovr_Veicolo"));
                sovr_bagaglio.add(rs.getFloat("Sovr_Bagaglio"));
                sovr_prenotazione.add(rs.getFloat("Sovr_Prenotazione"));

                //prendo i dati del natante e lo creo
                natante.add(rs.getInt("natante"));
                nome_natante.add(rs.getString("Nome_natante"));
                trasporta.add(rs.getString("Trasporta"));
                tipo_natante.add(rs.getString("Tipo_natante"));

                //Natante nat = new Natante(Nome_natante, Trasporta, Tipo_natante);
                porto_partenza.add(rs.getInt("porto_partenza"));
                porto_arrivo.add(rs.getInt("porto_arrivo"));
                p1_indirizzo.add(rs.getString("p1_indirizzo"));
                p1_comune.add(rs.getString("p1_comune"));
                p1_tel_info.add(rs.getString("p1_tel_info"));

                p2_indirizzo.add(rs.getString("p2_indirizzo"));
                p2_comune.add(rs.getString("p2_comune"));
                p2_tel_info.add(rs.getString("p2_tel_info"));

                porto_scalo.add(rs.getInt("porto_scalo"));
                ps_indirizzo.add(rs.getString("ps_indirizzo"));
                ps_comune.add(rs.getString("ps_comune"));
                ps_tel_info.add(rs.getString("ps_tel_info"));


            }

            p_s.close();
            rs.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao leggi corse");
            throw new RuntimeException(e);
        }
    }

    public void delete_corsa(Integer id_corsa){
        try{
            connection.setAutoCommit(false);
            String query="{ call delete_corsa(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(1,id_corsa);

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao delete_corsa");
            throw new RuntimeException(e);
        }
    }



}
