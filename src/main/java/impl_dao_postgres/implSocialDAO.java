package impl_dao_postgres;

import dao.SocialDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

public class implSocialDAO implements SocialDAO {

    private Connection connection;
    public implSocialDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }
    public void retrieve_social(int id_compagnia, ArrayList<String> nome_social, ArrayList<String> indirizzo_social){

        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_social(?) }";
            CallableStatement p_s=connection.prepareCall(query);

            p_s.setInt(2,id_compagnia);


            p_s.registerOutParameter(1, Types.OTHER);
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
