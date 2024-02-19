package impl_dao_postgres;

import dao.UtenteDAO;
import database.ConnessioneDatabase;

import java.sql.*;
import java.util.ArrayList;

/**
 * l'implementazione dell interfaccia utenteDAO per postgres
 * */
public class implUtenteDAO implements UtenteDAO {
    private Connection connection;
    public implUtenteDAO() {
        connection=ConnessioneDatabase.getInstance().getConnection();

    }

    /**
     * metodo per richiamare la funzione loginPasseggero sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param loginIn la login inserita
     * @param passwordIn la password inserita
     * */
    public void loginPasseggero(ArrayList<Integer> idPasseggero, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<Integer> eta, String loginIn, String passwordIn){
        try {
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query = "{ ? = call loginPasseggero(?,?) }";
            CallableStatement preparedCall = connection.prepareCall(query);
            preparedCall.setString(2, loginIn);
            preparedCall.setString(3, passwordIn);


            preparedCall.registerOutParameter(1, Types.OTHER);
            preparedCall.execute();
            ResultSet rs = (ResultSet) preparedCall.getObject(1);

            while(rs.next()){
                idPasseggero.add(rs.getInt("id_passeggero"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));
            }
            rs.close();
            preparedCall.close();
        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao login passeggero: "+e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("impossibile chiudere la connessione: "+e.getMessage());
            }
        }
    }

    /**
     * metodo per richiamare la funzione loginCompagnia sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param loginIn la login inserita
     * @param passwordIn la password inserita
     * */
    public void loginCompagnia(ArrayList<Integer> idCompagnia, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<String> telefono, ArrayList<String> mail, ArrayList<String> sitoWeb, String loginIn, String passwordIn){
        try {
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query = "{ ? = call logincompagnia(?,?) }";
            CallableStatement preparedCall = connection.prepareCall(query);
            preparedCall.setString(2, loginIn);
            preparedCall.setString(3, passwordIn);


            preparedCall.registerOutParameter(1, Types.OTHER);
            preparedCall.execute();
            ResultSet rs = (ResultSet) preparedCall.getObject(1);

            while(rs.next()){
                idCompagnia.add(rs.getInt("id_compagnia"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                telefono.add(rs.getString("telefono"));
                mail.add(rs.getString("mail"));
                sitoWeb.add(rs.getString("sito_web"));
            }
            rs.close();
            preparedCall.close();
        }
        catch (Exception e){
            System.out.println("error in impl_corsa_dao login passeggero");
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

    /**
     * metodo per richiamare la funzione registerPasseggero sul database postgres
     * @param nome l' username inserita
     * @param login la login inserita
     * @param password la password inserita
     * @param eta l'eta inserita
     * */
    public void registerPasseggero(String nome, String login, String password, Integer eta) throws SQLException{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(true);
            String query = " call register_passeggero(?,?,?,?) ";
            CallableStatement preparedCall = connection.prepareCall(query);
            preparedCall.setString(1, nome);
            preparedCall.setString(2, login);
            preparedCall.setString(3, password);
            preparedCall.setInt(4,eta);

            preparedCall.execute();
            preparedCall.close();



    }

    /**
     * metodo per richiamare la funzione retrieve accompagnatori sul database postgres
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * */

    public void retrieveAccompagnatori(ArrayList<Integer> idPasseggero, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<Integer> eta){
        try{
            connection=ConnessioneDatabase.getInstance().getConnection();
            connection.setAutoCommit(false);
            String query="{ ? = call retrieve_accompagnatori() }";
            CallableStatement preparedCall=connection.prepareCall(query);


            preparedCall.registerOutParameter(1,Types.OTHER);
            preparedCall.execute();
            ResultSet rs=(ResultSet) preparedCall.getObject(1);





            while(rs.next()) {
                idPasseggero.add(rs.getInt("id_passeggero"));
                nome.add(rs.getString("nome"));
                login.add(rs.getString("login"));
                password.add(rs.getString("password"));
                eta.add(rs.getInt("eta"));

            }

            rs.close();
            preparedCall.close();

        }
        catch (SQLException e) {
            System.out.println("error in impl_corsa_dao retrieve_accompagnatori");
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

    /**
     * metodo per richiamare la funzione change password sul database postgres
     * @param login la login inserita
     * @param newPassword la nuova password inserita
     * @param oldPassword la vecchia password inserita
     * */

    public void changePassword(String login, String newPassword, String oldPassword)throws SQLException{
        connection=ConnessioneDatabase.getInstance().getConnection();
        connection.setAutoCommit(true);
        String query=" call change_password(?,?,?) ";
        CallableStatement preparedCall=connection.prepareCall(query);
        preparedCall.setString(1,login);
        preparedCall.setString(2,newPassword);
        preparedCall.setString(3,oldPassword);

        preparedCall.execute();
        preparedCall.close();


    }

    /**
     * metodo per richiamare la funzione change login sul database postgres
     * @param oldLogin la login inserita
     * @param newLogin la nuova login inserita
     * @param password la password inserita
     * */

    public void changeLogin(String oldLogin, String newLogin, String password)throws SQLException{
        connection=ConnessioneDatabase.getInstance().getConnection();
        connection.setAutoCommit(true);
        String query=" call change_login(?,?,?) ";
        System.out.println("old_login: "+oldLogin+" new_login: "+newLogin+" password:"+password);
        CallableStatement preparedCall=connection.prepareCall(query);
        preparedCall.setString(1,oldLogin);
        preparedCall.setString(2,newLogin);
        preparedCall.setString(3,password);

        preparedCall.execute();
        preparedCall.close();

    }
}
