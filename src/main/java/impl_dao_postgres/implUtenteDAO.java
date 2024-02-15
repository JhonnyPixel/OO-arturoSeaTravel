package impl_dao_postgres;

import dao.UtenteDAO;
import database.ConnessioneDatabase;

import java.sql.*;
import java.util.ArrayList;

public class implUtenteDAO implements UtenteDAO {
    private Connection connection;
    public implUtenteDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            System.out.println("impossibile connetersi al database:"+e.getMessage());
        }
    }

    public void login_passeggero(ArrayList<Integer> id_passeggero, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<Integer> eta, String login_in, String password_in){
        try {
            connection.setAutoCommit(false);
            String query = "{ ? = call loginPasseggero(?,?) }";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setString(2, login_in);
            p_s.setString(3, password_in);


            p_s.registerOutParameter(1, Types.OTHER);
            p_s.execute();
            ResultSet rs = (ResultSet) p_s.getObject(1);

            while(rs.next()){
                id_passeggero.add(rs.getInt("id_passeggero"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));
            }
        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao login passeggero");
            throw new RuntimeException(e);
        }
    }

    public void login_compagnia(ArrayList<Integer> id_compagnia,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<String> telefono,ArrayList<String> mail,ArrayList<String> sito_web,String login_in,String password_in){
        try {
            connection.setAutoCommit(false);
            String query = "{ ? = call logincompagnia(?,?) }";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setString(2, login_in);
            p_s.setString(3, password_in);


            p_s.registerOutParameter(1, Types.OTHER);
            p_s.execute();
            ResultSet rs = (ResultSet) p_s.getObject(1);

            while(rs.next()){
                id_compagnia.add(rs.getInt("id_compagnia"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                telefono.add(rs.getString("telefono"));
                mail.add(rs.getString("mail"));
                sito_web.add(rs.getString("sito_web"));
            }
        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao login passeggero");
            throw new RuntimeException(e);
        }
    }

    public void register_passeggero(String nome,String login,String password,Integer eta) throws SQLException{

            connection.setAutoCommit(true);
            String query = " call register_passeggero(?,?,?,?) ";
            CallableStatement p_s = connection.prepareCall(query);
            p_s.setString(1, nome);
            p_s.setString(2, login);
            p_s.setString(3, password);
            p_s.setInt(4,eta);

            p_s.execute();



    }

    public void retrieve_passeggero(Integer id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_passeggero(?) }";
            CallableStatement p_s=connection.prepareCall(query);
            p_s.setInt(2,id_passeggero);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_passeggero");
            throw new RuntimeException(e);
        }
    }

    public void retrieve_accompagnatori(ArrayList<Integer> id_passeggero,ArrayList<String> nome, ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        try{
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_accompagnatori() }";
            CallableStatement p_s=connection.prepareCall(query);


            p_s.registerOutParameter(1,Types.OTHER);
            p_s.execute();
            ResultSet rs=(ResultSet) p_s.getObject(1);





            while(rs.next()) {
                id_passeggero.add(rs.getInt("id_passeggero"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));

            }

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_accompagnatori");
            throw new RuntimeException(e);
        }
    }

    public void change_password(String login,String new_password,String old_password)throws SQLException{
        connection.setAutoCommit(true);
        String query=" call change_password(?,?,?) ";
        CallableStatement p_s=connection.prepareCall(query);
        p_s.setString(1,login);
        p_s.setString(2,new_password);
        p_s.setString(3,old_password);

        p_s.execute();


    }

    public void change_login(String old_login,String new_login,String password)throws SQLException{

        connection.setAutoCommit(true);
        String query=" call change_login(?,?,?) ";
        System.out.println("old_login: "+old_login+" new_login: "+new_login+" password:"+password);
        CallableStatement p_s=connection.prepareCall(query);
        p_s.setString(1,old_login);
        p_s.setString(2,new_login);
        p_s.setString(3,password);

        p_s.execute();

    }
}
