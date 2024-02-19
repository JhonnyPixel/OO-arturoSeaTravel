package impl_dao_postgres;
import dao.BigliettoDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

/**
 * l'implementazione dell interfaccia bigliettoDAO per postgres
 * */
public class implBigliettoDAO implements BigliettoDAO {
    private Connection connection;
    public implBigliettoDAO(){

        connection = ConnessioneDatabase.getInstance().getConnection();

    }

    /**
     * metodo per richiamare la funzione retrieve biglietti interi sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param idPasseggero l'id del passeggero di cui prendere i biglietti interi
     * */


    public void retrieveBigliettiInteri(Integer idPasseggero, ArrayList<Float> importoTotale, ArrayList<Float> sovrapprezzoTot, ArrayList<Integer> nBagagli, ArrayList<String> veicolo, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa){
        try{
            connection = ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_biglietti_interi(?) }";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setInt(2,idPasseggero);


            preparedCall.registerOutParameter(1, Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);


            while(rs.next()) {
                importoTotale.add(rs.getFloat("importo_totale"));
                sovrapprezzoTot.add(rs.getFloat("sovrapprezzo_tot"));
                nBagagli.add(rs.getInt("n_bagagli"));
                veicolo.add(rs.getString("veicolo"));
                prenotazione.add(rs.getTimestamp("prenotazione"));
                corsa.add(rs.getInt("corsa"));

            }
            rs.close();
            preparedCall.close();


        }
        catch (SQLException e) {
            System.out.println("error in implBigliettoDAO retrieveBigliettiInteri: "+e.getMessage());

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
     * metodo per richiamare la funzione retrieve biglietti ridotti sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param idPasseggero l'id del passeggero di cui prendere i biglietti ridotti
     * */
    public void retrieveBigliettiRidotti(Integer idPasseggero, ArrayList<Float> importoTotale, ArrayList<Float> sovrapprezzoTot, ArrayList<Integer> nBagagli, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa, ArrayList<Integer> accompagnatore){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_biglietti_ridotti(?) }";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setInt(2,idPasseggero);


            preparedCall.registerOutParameter(1,Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);





            while(rs.next()) {
                importoTotale.add(rs.getFloat("importo_totale"));
                sovrapprezzoTot.add(rs.getFloat("sovrapprezzo_tot"));
                nBagagli.add(rs.getInt("n_bagagli"));
                prenotazione.add(rs.getTimestamp("prenotazione"));
                corsa.add(rs.getInt("corsa"));
                accompagnatore.add(rs.getInt("accompagnatore"));

            }

            rs.close();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_biglietti_ridotti");
            throw new RuntimeException(e);
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
     * metodo per richiamare la funzione add biglietto intero sul database postgres
     *
     *
     * @param importoTotale importoTotale del biglietto
     * @param sovrapprezzoTot sovrapprezzoTot del biglietto
     * @param nBagagli numero di bagagli del passeggero
     * @param veicolo veicolo portato dal passeggero
     * @param prenotazione prenotazione del biglietto
     * @param corsa corsa del biglietto
     * @param passeggero passeggero del biglietto
     * */
    public void addBigliettoIntero(float importoTotale, float sovrapprezzoTot, int nBagagli, String veicolo, Timestamp prenotazione, int corsa, int passeggero){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query=" CALL add_biglietto_intero(?,?,?,?,?,?,?) ";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setDouble(1,importoTotale);
            preparedCall.setDouble(2,sovrapprezzoTot);
            preparedCall.setInt(3,nBagagli);
            preparedCall.setObject(4,veicolo, Types.OTHER);
            preparedCall.setTimestamp(5,prenotazione);
            preparedCall.setInt(6,corsa);
            preparedCall.setInt(7,passeggero);

            preparedCall.execute();

            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao add_biglietto_intero: "+e.getMessage());

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
     * metodo per richiamare la funzione add biglietto ridotto sul database postgres
     *
     * @param importoTotale importoTotale del biglietto
     * @param sovrapprezzoTot sovrapprezzoTot del biglietto
     * @param nBagagli numero di bagagli del passeggero
     * @param prenotazione prenotazione del biglietto
     * @param corsa corsa del biglietto
     * @param passeggero passeggero del biglietto
     * @param accompagnatore accompagnatore del passeggero
     * */
    public void addBigliettoRidotto(float importoTotale, float sovrapprezzoTot, int nBagagli, Timestamp prenotazione, int corsa, int passeggero, int accompagnatore){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query=" call add_biglietto_ridotto(?,?,?,?,?,?,?) ";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setFloat(1,importoTotale);
            preparedCall.setFloat(2,sovrapprezzoTot);
            preparedCall.setInt(3,nBagagli);
            preparedCall.setTimestamp(4,prenotazione);
            preparedCall.setInt(5,corsa);
            preparedCall.setInt(6,passeggero);
            preparedCall.setInt(7,accompagnatore);

            preparedCall.execute();

            preparedCall.close();
        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao add_biglietto_ridotto: "+e.getMessage());
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
