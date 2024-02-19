package impl_dao_postgres;

import dao.AnnullamentoDAO;
import database.ConnessioneDatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * l'implementazione dell interfaccia annullamentoDAO per postgres
 * */
public class implAnnullamentoDAO implements AnnullamentoDAO {
    private Connection connection;
    public implAnnullamentoDAO() {
        connection=ConnessioneDatabase.getInstance().getConnection();
    }

    /**
     * metodo per richiamare la funzione add annullamento sul database postgres
     *
     * @param motivazione motivazione dell annullamento
     * @param rimborso eventuale ammontare del rimborso
     * @param idCorsa l' id della corsa a cui aggiungere l'annullamento
     * @param prossimo l'id di eventuale corsa sostitutiva
     * */
    public void addAnnullamento(String motivazione, Float rimborso, Integer idCorsa, Integer prossimo){
        CallableStatement preparedCall;
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query="call add_annullamento(?,?,?,?)";
            preparedCall=connection.prepareCall(query);
            preparedCall.setString(1,motivazione);
            preparedCall.setObject(2,rimborso, Types.FLOAT);
            preparedCall.setInt(3,idCorsa);
            preparedCall.setObject(4,prossimo,Types.INTEGER);

            preparedCall.execute();

            preparedCall.close();


        }
        catch (SQLException e) {
            System.out.println("error in implAnnullamentoDAO addAnnullamento: "+e.getMessage());

        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("impossibile chiudere la connessione: "+e.getMessage());
            }
        }
    }


}
