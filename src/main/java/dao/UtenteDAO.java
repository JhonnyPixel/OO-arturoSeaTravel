package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UtenteDAO{

    void login_passeggero(ArrayList<Integer> id_passeggero, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<Integer> eta, String login_in, String password_in);
    void login_compagnia(ArrayList<Integer> id_compagnia,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<String> telefono,ArrayList<String> mail,ArrayList<String> sito_web,String login_in,String password_in);
    void retrieve_passeggero(Integer id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta);
    void register_passeggero(String nome,String login,String password,Integer eta)throws SQLException;
    void retrieve_accompagnatori(ArrayList<Integer> id_passeggero,ArrayList<String> nome, ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta);
    void change_password(String login,String new_password,String old_password)throws SQLException;
    void change_login(String old_login,String new_login,String password)throws SQLException;
}
