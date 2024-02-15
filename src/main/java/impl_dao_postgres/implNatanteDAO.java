package impl_dao_postgres;

import dao.NatanteDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

public class implNatanteDAO implements NatanteDAO {
    private Connection connection;
    public implNatanteDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }
    public void retrieve_natanti(ArrayList<Integer> id_natante, ArrayList<String> nome, ArrayList<String> trasporta, ArrayList<String> tipo){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_natanti() }";
            CallableStatement p_s=connection.prepareCall(query);


            p_s.registerOutParameter(1, Types.OTHER);
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

}
