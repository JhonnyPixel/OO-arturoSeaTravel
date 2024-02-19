package dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * l'interfaccia utenteDAO
 * */
public interface UtenteDAO{

    /**
     * metodo per richiamare la funzione loginPasseggero sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param loginIn la login inserita
     * @param passwordIn la password inserita
     * */
    public void loginPasseggero(ArrayList<Integer> idPasseggero, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<Integer> eta, String loginIn, String passwordIn);
    /**
     * metodo per richiamare la funzione loginCompagnia sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * @param loginIn la login inserita
     * @param passwordIn la password inserita
     * */
    public void loginCompagnia(ArrayList<Integer> idCompagnia, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<String> telefono, ArrayList<String> mail, ArrayList<String> sitoWeb, String loginIn, String passwordIn);
    /**
     * metodo per richiamare la funzione registerPasseggero sul database
     * @param nome l' username inserita
     * @param login la login inserita
     * @param password la password inserita
     * @param eta l'eta inserita
     * */
    public void registerPasseggero(String nome, String login, String password, Integer eta) throws SQLException;
    /**
     * metodo per richiamare la funzione retrieve accompagnatori sul database
     * e per ritornare al controller i dati serializzati all interno degli ArrayList
     * */

    public void retrieveAccompagnatori(ArrayList<Integer> idPasseggero, ArrayList<String> nome, ArrayList<String> login, ArrayList<String> password, ArrayList<Integer> eta);
    /**
     * metodo per richiamare la funzione change password sul database
     * @param login la login inserita
     * @param newPassword la nuova password inserita
     * @param oldPassword la vecchia password inserita
     * */

    void changePassword(String login, String newPassword, String oldPassword)throws SQLException;

    /**
     * metodo per richiamare la funzione change login sul database
     * @param oldLogin la login inserita
     * @param newLogin la nuova login inserita
     * @param password la password inserita
     * */

    void changeLogin(String oldLogin, String newLogin, String password)throws SQLException;
}
