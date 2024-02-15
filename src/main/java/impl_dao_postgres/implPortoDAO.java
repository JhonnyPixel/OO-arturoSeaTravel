package impl_dao_postgres;
import dao.PortoDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

public class implPortoDAO implements PortoDAO {
    private Connection connection;
    public implPortoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }
    public void retrieve_porti(ArrayList<Integer> id_porto, ArrayList<String> indirizzo, ArrayList<String> comune, ArrayList<String> tel_info){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_porti() }";
            CallableStatement p_s=connection.prepareCall(query);


            p_s.registerOutParameter(1, Types.OTHER);
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

}
