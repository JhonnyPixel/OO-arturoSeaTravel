package dao;

import MODEL.Corsa;

import java.sql.*;
import java.util.ArrayList;

public interface CorsaDAO {
    void filtra_corse(Integer id_corsa,ArrayList<Integer> id_corse,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza ,Float prezzo ,String  tipo_natante,ArrayList<Time> Orari_Partenza, ArrayList<Time> Orari_Arrivo,
                                 ArrayList<Time> Orari_Partenza_Scalo,ArrayList<Time> Orari_Arrivo_Scalo,ArrayList<Date> Date_Inizio_Servizio,
                                 ArrayList<Date> Date_Fine_Servizio, ArrayList<String> Giorni_Servizio_Attivo, ArrayList<Float> Sconti_residente,
                                 ArrayList<Float> Prezzi_interi, ArrayList<Float> Prezzi_ridotti, ArrayList<Float> Sovr_Veicoli, ArrayList<Float> Sovr_Bagagli,
                                 ArrayList<Float> Sovr_Prenotazioni,ArrayList<Integer> id_natanti, ArrayList<String> Nomi_natanti, ArrayList<String> Trasporti, ArrayList<String> Tipi_natanti,
                                 ArrayList<Integer> id_porti_partenza,ArrayList<String> Indirizzi_porti_partenza, ArrayList<String> Comuni_porti_partenza, ArrayList<String> Telefoni_porti_partenza,
                                 ArrayList<Integer>id_porti_scalo,ArrayList<String> Indirizzi_porti_scalo,ArrayList<String> Comuni_porti_scalo,ArrayList<String> Telefoni_porti_scalo,
                                 ArrayList<Integer> id_porti_arrivo,ArrayList<String> Indirizzi_porti_arrivo, ArrayList<String> Comuni_porti_arrivo, ArrayList<String> Telefoni_porti_arrivo,
                                 ArrayList<Integer> id_compagnie,ArrayList<String> Telefoni_compagnie, ArrayList<String> Mail_compagnie, ArrayList<String> Siti_web_compagnie, ArrayList<String> login_compagnie,
                                 ArrayList<String> password_compagnie, ArrayList<String> nomi_compagnie, ArrayList<String> Motivazioni_ritardi, ArrayList<Time> Tempi_ritardi, ArrayList<String> Motivazioni_annullamenti,
                                 ArrayList<Float> Rimborsi, ArrayList<Integer> Prossimi);
    void create_update_corsa(Integer Id_corsa ,Time Orario_partenza , Time Orario_arrivo ,
                                    Time Orario_partenza_scalo , Time Orario_arrivo_scalo,
                                    Date Data_inizio_servizio , Date Data_fine_servizio ,
                                    String Giorni_Servizio_Attivo , Float Sovr_prenotazione ,
                                    Float Sovr_bagaglio , Float Sovr_veicolo , Float Prezzo_intero ,
                                    Float Prezzo_ridotto , Float Sconto_residente , Integer Porto_partenza ,
                                    Integer Porto_arrivo ,Integer Porto_scalo,
                                    Integer Compagnia , Integer Natante )throws SQLException;

    void retrieve_corse_compagnia(Integer id_compagnia,ArrayList<Integer> id_corsa,
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
                                  ArrayList<String> tipo_natante);
    void delete_corsa(Integer id_corsa);


}
