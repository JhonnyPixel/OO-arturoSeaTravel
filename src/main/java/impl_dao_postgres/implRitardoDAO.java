package impl_dao_postgres;

import dao.RitardoDAO;
import database.ConnessioneDatabase;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

public class implRitardoDAO implements RitardoDAO {
    private Connection connection;
    public implRitardoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }
    public void add_ritardo(String motivazione, Time ritardo, Integer id_corsa){
        try{
            connection.setAutoCommit(true);
            String query="call add_ritardo(?,?,?) ";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setString(1,motivazione);
            p_s.setTime(2,ritardo);
            p_s.setInt(3,id_corsa);

            p_s.execute();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero");
            throw new RuntimeException(e);
        }
    }

}
