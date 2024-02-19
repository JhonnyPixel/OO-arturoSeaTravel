package impl_dao_postgres;
import dao.PortoDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

/**
 * l'implementazione dell interfaccia portoDAO per postgres
 * */
public class implPortoDAO implements PortoDAO {
    private Connection connection;
    public implPortoDAO() {
        connection=ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * metodo per richiamare la funzione retrieve porti sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * */
    public void retrievePorti(ArrayList<Integer> idPorto, ArrayList<String> indirizzo, ArrayList<String> comune, ArrayList<String> telInfo){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_porti() }";
            CallableStatement preparedCall=connection.prepareCall(query);


            preparedCall.registerOutParameter(1, Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);





            while(rs.next()) {
                idPorto.add(rs.getInt("id_porto"));
                indirizzo.add(rs.getString("indirizzo"));
                comune.add(rs.getString("comune"));
                telInfo.add(rs.getString("tel_info"));

            }
            rs.close();
            preparedCall.close();


        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_porti");
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

}
