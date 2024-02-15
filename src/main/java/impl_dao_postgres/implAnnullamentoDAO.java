package impl_dao_postgres;

import dao.AnnullamentoDAO;
import database.ConnessioneDatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class implAnnullamentoDAO implements AnnullamentoDAO {
    private Connection connection;
    public implAnnullamentoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }
    public void add_annullamento(String motivazione,Float rimborso,Integer id_corsa,Integer prossimo){
        try{
            connection.setAutoCommit(true);
            String query="call add_annullamento(?,?,?,?)";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setString(1,motivazione);
            p_s.setObject(2,rimborso, Types.FLOAT);
            p_s.setInt(3,id_corsa);
            p_s.setObject(4,prossimo,Types.INTEGER);

            p_s.execute();



        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero");
            throw new RuntimeException(e);
        }
    }


}
