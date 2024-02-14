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
    void login_passeggero(ArrayList<Integer> id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta,String login_in,String password_in);
    void login_compagnia(ArrayList<Integer> id_compagnia,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<String> telefono,ArrayList<String> mail,ArrayList<String> sito_web,String login_in,String password_in);
    void register_passeggero(String nome,String login,String password);
    void create_update_corsa(Integer Id_corsa ,Time Orario_partenza , Time Orario_arrivo ,
                                    Date Data_inizio_servizio , Date Data_fine_servizio ,
                                    String Giorni_Servizio_Attivo , Float Sovr_prenotazione ,
                                    Float Sovr_bagaglio , Float Sovr_veicolo , Float Prezzo_intero ,
                                    Float Prezzo_ridotto , Float Sconto_residente , Integer Porto_partenza ,
                                    Integer Porto_arrivo , Integer Compagnia , Integer Natante )throws SQLException;
    void retrieve_biglietti_interi(Integer id_passeggero, ArrayList<Float> importo_totale, ArrayList<Float> Sovrapprezzo_tot, ArrayList<Integer> n_bagagli, ArrayList<String> veicolo, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa);
    void retrieve_biglietti_ridotti(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore);
    void retrieve_passeggero(Integer id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta);
    void add_ritardo(String motivazione,Time ritardo,Integer id_corsa);
    void add_annullamento(String motivazione,Float rimborso,Integer id_corsa,Integer prossimo);

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
    void retrieve_accompagnatori(ArrayList<Integer> id_passeggero,ArrayList<String> nome,
                                 ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta);
    void retrieve_porti(ArrayList<Integer> id_porto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> tel_info);
    void retrieve_natanti(ArrayList<Integer> id_natante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo);
    void delete_corsa(Integer id_corsa);
    void add_biglietto_intero(float importo_totale,float sovrapprezzo_tot,int n_bagagli,String veicolo,Timestamp prenotazione,int corsa,int passeggero);

    void add_biglietto_ridotto(float importo_totale,float sovrapprezzo_tot,int n_bagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore);
    void change_password(String login,String new_password,String old_password)throws SQLException;
    void change_login(String old_login,String new_login,String password)throws SQLException;

    void retrieve_social(int id_compagnia,ArrayList<String> nome_social,ArrayList<String> indirizzo_social);
}
