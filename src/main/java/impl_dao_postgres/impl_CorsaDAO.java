package impl_dao_postgres;

import MODEL.*;
import dao.CorsaDAO;
import javax.swing.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dao.DaoManager;
import database.ConnessioneDatabase;

import java.sql.*;
import java.util.List;
import java.sql.Time;

public class impl_CorsaDAO implements CorsaDAO {
    private Connection connection;
    private Statement p_s,ps_2;
    public impl_CorsaDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void filtra_corse(Integer id_corsa,ArrayList<Integer> id_corse,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza ,Float prezzo ,String  tipo_natante,ArrayList<Time> Orari_Partenza,ArrayList<Time> Orari_Arrivo,ArrayList<Date> Date_Inizio_Servizio,
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


    public void login_passeggero(ArrayList<Integer> id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta,String login_in,String password_in){
        try {
            connection.setAutoCommit(false);
            String query = "{ ? = call loginPasseggero(?,?) }";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setString(2, login_in);
            p_s.setString(3, password_in);


            p_s.registerOutParameter(1, Types.OTHER);
            p_s.execute();
            ResultSet rs = (ResultSet) p_s.getObject(1);

            while(rs.next()){
                id_passeggero.add(rs.getInt("id_passeggero"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));
            }
        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao login passeggero");
            throw new RuntimeException(e);
        }
    }

    public void login_compagnia(ArrayList<Integer> id_compagnia,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<String> telefono,ArrayList<String> mail,ArrayList<String> sito_web,String login_in,String password_in){
        try {
            connection.setAutoCommit(false);
            String query = "{ ? = call logincompagnia(?,?) }";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setString(2, login_in);
            p_s.setString(3, password_in);


            p_s.registerOutParameter(1, Types.OTHER);
            p_s.execute();
            ResultSet rs = (ResultSet) p_s.getObject(1);

            while(rs.next()){
                id_compagnia.add(rs.getInt("id_compagnia"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                telefono.add(rs.getString("telefono"));
                mail.add(rs.getString("mail"));
                sito_web.add(rs.getString("sito_web"));
            }
        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao login passeggero");
            throw new RuntimeException(e);
        }
    }

    public void register_passeggero(String nome,String login,String password){
        try {
            connection.setAutoCommit(false);
            String query = "{ call register_passeggero(?,?,?) }";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setString(1, nome);
            p_s.setString(2, login);
            p_s.setString(3, password);

            p_s.execute();

        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao register passeggero");
            throw new RuntimeException(e);

        }

    }
    public void create_update_corsa(Integer Id_corsa ,Time Orario_partenza , Time Orario_arrivo ,
                             Date Data_inizio_servizio , Date Data_fine_servizio ,
                             byte[] Giorni_Servizio_Attivo , Float Sovr_prenotazione ,
                             Float Sovr_bagaglio , Float Sovr_veicolo , Float Prezzo_intero ,
                             Float Prezzo_ridotto , Float Sconto_residente , Integer Porto_partenza ,
                             Integer Porto_arrivo , Integer Compagnia , Integer Natante ){
        try {
            connection.setAutoCommit(false);
            String query = "{ call create_update_corsa(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setInt(1, Id_corsa);
            p_s.setTime(2, Orario_partenza);
            p_s.setTime(3, Orario_arrivo);
            p_s.setDate(4,Data_inizio_servizio);
            p_s.setDate(5,Data_fine_servizio);
            p_s.setBytes(6,Giorni_Servizio_Attivo);
            p_s.setFloat(7,Sovr_prenotazione);
            p_s.setFloat(8,Sovr_bagaglio);
            p_s.setFloat(9,Sovr_veicolo);
            p_s.setFloat(10,Prezzo_intero);
            p_s.setFloat(11,Prezzo_ridotto);
            p_s.setFloat(12,Sconto_residente);
            p_s.setInt(13,Porto_partenza);
            p_s.setInt(14,Porto_arrivo);
            p_s.setInt(15,Compagnia);
            p_s.setInt(16,Natante);

            p_s.execute();

        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao create update corsa");
            throw new RuntimeException(e);

        }
    }
    public void retrieve_biglietti_interi(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,ArrayList<String> veicolo,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa){
        try{
        connection.setAutoCommit(false);
        String query="{ ? = call retrieve_biglietti_interi(?) }";
        CallableStatement p_s=connection.prepareCall(query);
        p_s.setInt(2,id_passeggero);


        p_s.registerOutParameter(1,Types.OTHER);
        p_s.execute();
        ResultSet rs=(ResultSet) p_s.getObject(1);





        while(rs.next()) {
            importo_totale.add(rs.getFloat("importo_totale"));
            Sovrapprezzo_tot.add(rs.getFloat("sovrapprezzo_tot"));
            n_bagagli.add(rs.getInt("n_bagagli"));
            veicolo.add(rs.getString("veicolo"));
            prenotazione.add(rs.getTimestamp("prenotazione"));
            corsa.add(rs.getInt("corsa"));

        }

    }
        catch (SQLException e) {
        System.out.println("error in impl_corsa_dao retrieve_biglietti_interi");
        throw new RuntimeException(e);
    }
    }


    public void retrieve_biglietti_ridotti(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_biglietti_ridotti(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(2,id_passeggero);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                importo_totale.add(rs.getFloat("importo_totale"));
                Sovrapprezzo_tot.add(rs.getFloat("sovrapprezzo_tot"));
                n_bagagli.add(rs.getInt("n_bagagli"));
                prenotazione.add(rs.getTimestamp("prenotazione"));
                corsa.add(rs.getInt("corsa"));
                accompagnatore.add(rs.getInt("accompagnatore"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_biglietti_ridotti");
            throw new RuntimeException(e);
        }
    }

    public void retrieve_passeggero(Integer id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_passeggero(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(2,id_passeggero);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
               nome.add(rs.getString("nome"));
               login.add(rs.getString("login"));
               password.add(rs.getString("password"));
               eta.add(rs.getInt("eta"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero");
            throw new RuntimeException(e);
        }
    }

    public void add_ritardo(String motivazione,Time ritardo,Integer id_corsa){
        try{
            connection.setAutoCommit(false);
            String query="{ call add_ritardo(?,?,?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setString(1,motivazione);
            p_s.setTime(2,ritardo);
            p_s.setInt(3,id_corsa);

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero");
            throw new RuntimeException(e);
        }
    }

    public void add_annullamento(String motivazione,Float rimborso,Integer id_corsa,Integer prossimo){
        try{
            connection.setAutoCommit(false);
            String query="{ call add_annullamento(?,?,?,?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setString(1,motivazione);
            p_s.setFloat(2,rimborso);
            p_s.setInt(3,id_corsa);
            p_s.setInt(4,prossimo);


        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero");
            throw new RuntimeException(e);
        }
    }

    public void retrieve_corse_compagnia(Integer id_compagnia,ArrayList<Integer> id_corsa,
                                         ArrayList<Time> orario_partenza,ArrayList<Time> orario_arrivo,
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

    public void retrieve_accompagnatori(ArrayList<Integer> id_passeggero,ArrayList<String> nome,
                                        ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_accompagnatori() }";
            CallableStatement p_s=connection.prepareCall(query);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                id_passeggero.add(rs.getInt("id_passeggero"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_accompagnatori");
            throw new RuntimeException(e);
        }
    }

    public void retrieve_porti(ArrayList<Integer> id_porto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> tel_info){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_porti() }";
            CallableStatement p_s=connection.prepareCall(query);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                id_porto.add(rs.getInt("id_porto"));
                indirizzo.add(rs.getString("indirizzo"));
                comune.add(rs.getString("comune"));
                tel_info.add(rs.getString("tel_info"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_porti");
            throw new RuntimeException(e);
        }
    }

    public void retrieve_natanti(ArrayList<Integer> id_natante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_natanti() }";
            CallableStatement p_s=connection.prepareCall(query);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                id_natante.add(rs.getInt("id_natante"));
                nome.add(rs.getString("nome"));
                trasporta.add(rs.getString("trasporta"));
                tipo.add(rs.getString("tipo"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_natanti");
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


    public void add_biglietto_intero(float importo_totale,float sovrapprezzo_tot,int n_bagagli,String veicolo,Timestamp prenotazione,int corsa,int passeggero){
        try{
            connection.setAutoCommit(true);
            String query=" CALL add_biglietto_intero(?,?,?,?,?,?,?) ";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setDouble(1,importo_totale);
            p_s.setDouble(2,sovrapprezzo_tot);
            p_s.setInt(3,n_bagagli);
            p_s.setObject(4,veicolo, Types.OTHER);
            p_s.setTimestamp(5,prenotazione);
            p_s.setInt(6,corsa);
            p_s.setInt(7,passeggero);

            p_s.execute();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao add_biglietto_intero");
            throw new RuntimeException(e);
        }
    }

    public void add_biglietto_ridotto(float importo_totale,float sovrapprezzo_tot,int n_bagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore){
        try{
            connection.setAutoCommit(true);
            String query=" call add_biglietto_ridotto(?,?,?,?,?,?,?) ";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setFloat(1,importo_totale);
            p_s.setFloat(2,sovrapprezzo_tot);
            p_s.setInt(3,n_bagagli);
            p_s.setTimestamp(4,prenotazione);
            p_s.setInt(5,corsa);
            p_s.setInt(6,passeggero);
            p_s.setInt(7,accompagnatore);

            p_s.execute();
        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao add_biglietto_ridotto");
            throw new RuntimeException(e);
        }
    }

    public void change_password(String login,String new_password,String old_password)throws SQLException{
            connection.setAutoCommit(true);
            String query=" call change_password(?,?,?) ";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setString(1,login);
            p_s.setString(2,new_password);
            p_s.setString(3,old_password);

            p_s.execute();


    }

    public void change_login(String old_login,String new_login,String password)throws SQLException{

            connection.setAutoCommit(true);
            String query=" call change_login(?,?,?) ";
            System.out.println("old_login: "+old_login+" new_login: "+new_login+" password:"+password);
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setString(1,old_login);
            p_s.setString(2,new_login);
            p_s.setString(3,password);

            p_s.execute();

    }

    public void retrieve_social(int id_compagnia,ArrayList<String> nome_social,ArrayList<String> indirizzo_social){

        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_social(?) }";
            CallableStatement p_s=connection.prepareCall(query);

            p_s.setInt(2,id_compagnia);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);



            while(rs.next()) {
                nome_social.add(rs.getString("nome_social"));
                indirizzo_social.add(rs.getString("indirizzo_social"));
            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_social");
            throw new RuntimeException(e);
        }

    }





}
