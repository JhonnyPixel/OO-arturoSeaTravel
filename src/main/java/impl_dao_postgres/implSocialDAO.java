package impl_dao_postgres;

import dao.SocialDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

/**
 * l'implementazione dell interfaccia socialDAO per postgres
 * */
public class implSocialDAO implements SocialDAO {

    private Connection connection;
    public implSocialDAO() {
        connection=ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * metodo per richiamare la funzione retrieve social sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param idCompagnia l'id della compagnia di cui prendere i social
     * */
    public void retrieveSocial(int idCompagnia, ArrayList<String> nomeSocial, ArrayList<String> indirizzoSocial){

        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_social(?) }";
            CallableStatement preparedCall=connection.prepareCall(query);

            preparedCall.setInt(2,idCompagnia);


            preparedCall.registerOutParameter(1, Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);



            while(rs.next()) {
                nomeSocial.add(rs.getString("nome_social"));
                indirizzoSocial.add(rs.getString("indirizzo_social"));
            }

            rs.close();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_social: "+e.getMessage());
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
