package impl_dao_postgres;

import dao.RitardoDAO;
import database.ConnessioneDatabase;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

/**
 * l'implementazione dell interfaccia ritardoDAO per postgres
 * */
public class implRitardoDAO implements RitardoDAO {
    private Connection connection;
    public implRitardoDAO() {
        connection=ConnessioneDatabase.getInstance().getConnection();

    }

    /**
     * metodo per richiamare la funzione add ritardo sul database postgres
     * @param motivazione motivazione del ritardo da inserire
     * @param ritardo il ritardo da inserire
     * @param idCorsa l'id della corsa a cui aggiungere il ritardo
     * */
    public void addRitardo(String motivazione, Time ritardo, Integer idCorsa){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query="call add_ritardo(?,?,?) ";
            CallableStatement preparedCall=connection.prepareCall(query);
            preparedCall.setString(1,motivazione);
            preparedCall.setTime(2,ritardo);
            preparedCall.setInt(3,idCorsa);

            preparedCall.execute();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero: "+e.getMessage());
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
