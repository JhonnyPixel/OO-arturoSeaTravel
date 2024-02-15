package impl_dao_postgres;
import dao.BigliettoDAO;
import database.ConnessioneDatabase;
import java.sql.*;
import java.util.ArrayList;

public class implBigliettoDAO implements BigliettoDAO {
    private Connection connection;
    public implBigliettoDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }


    public void retrieve_biglietti_interi(Integer id_passeggero, ArrayList<Float> importo_totale, ArrayList<Float> Sovrapprezzo_tot, ArrayList<Integer> n_bagagli, ArrayList<String> veicolo, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_biglietti_interi(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(2,id_passeggero);


            p_s.registerOutParameter(1, Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                importo_totale.add(rs.getFloat("importo_totale"));
                Sovrapprezzo_tot.add(rs.getFloat("sovrapprezzo_tot"));
                n_bagagli.add(rs.getInt("n_bagagli"));
                veicolo.add(rs.getString("veicolo"));
                prenotazione.add(rs.getTimestamp("prenotazione"));
                corsa.add(rs.getInt("corsa"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_biglietti_interi");
            throw new RuntimeException(e);
        }
    }
    public void retrieve_biglietti_ridotti(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_biglietti_ridotti(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(2,id_passeggero);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                importo_totale.add(rs.getFloat("importo_totale"));
                Sovrapprezzo_tot.add(rs.getFloat("sovrapprezzo_tot"));
                n_bagagli.add(rs.getInt("n_bagagli"));
                prenotazione.add(rs.getTimestamp("prenotazione"));
                corsa.add(rs.getInt("corsa"));
                accompagnatore.add(rs.getInt("accompagnatore"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_biglietti_ridotti");
            throw new RuntimeException(e);
        }
    }
    public void add_biglietto_intero(float importo_totale,float sovrapprezzo_tot,int n_bagagli,String veicolo,Timestamp prenotazione,int corsa,int passeggero){
        try{
            connection.setAutoCommit(true);
            String query=" CALL add_biglietto_intero(?,?,?,?,?,?,?) ";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setDouble(1,importo_totale);
            p_s.setDouble(2,sovrapprezzo_tot);
            p_s.setInt(3,n_bagagli);
            p_s.setObject(4,veicolo, Types.OTHER);
            p_s.setTimestamp(5,prenotazione);
            p_s.setInt(6,corsa);
            p_s.setInt(7,passeggero);

            p_s.execute();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao add_biglietto_intero");
            throw new RuntimeException(e);
        }
    }
    public void add_biglietto_ridotto(float importo_totale,float sovrapprezzo_tot,int n_bagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore){
        try{
            connection.setAutoCommit(true);
            String query=" call add_biglietto_ridotto(?,?,?,?,?,?,?) ";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setFloat(1,importo_totale);
            p_s.setFloat(2,sovrapprezzo_tot);
            p_s.setInt(3,n_bagagli);
            p_s.setTimestamp(4,prenotazione);
            p_s.setInt(5,corsa);
            p_s.setInt(6,passeggero);
            p_s.setInt(7,accompagnatore);

            p_s.execute();
        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao add_biglietto_ridotto");
            throw new RuntimeException(e);
        }
    }
}
