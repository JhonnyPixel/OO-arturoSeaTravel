package impl_dao_postgres;

import dao.NatanteDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

/**
 * l'implementazione dell interfaccia natanteDAO per postgres
 * */
public class implNatanteDAO implements NatanteDAO {
    private Connection connection;
    public implNatanteDAO() {
        connection=ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * metodo per richiamare la funzione retrieve natanti sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * */
    public void retrieveNatanti(ArrayList<Integer> idNatante, ArrayList<String> nome, ArrayList<String> trasporta, ArrayList<String> tipo){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_natanti() }";
            CallableStatement preparedCall=connection.prepareCall(query);


            preparedCall.registerOutParameter(1, Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);





            while(rs.next()) {
                idNatante.add(rs.getInt("id_natante"));
                nome.add(rs.getString("nome"));
                trasporta.add(rs.getString("trasporta"));
                tipo.add(rs.getString("tipo"));

            }
            rs.close();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_natanti");
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
