package model;
/**
 * il tipo utente
 * */
public class Utente {
    protected String nome;
    protected String login;
    protected String password;
    protected Integer idUtente;

    public Utente(Integer idUtente, String login, String password, String nome){
        this.idUtente = idUtente;
        this.nome =nome;
        this.login =login;
        this.password =password;
    }

    /**
     * getter del nome dell utente
     * @return stringa con il nome dell utente
     * */

    public String getNome(){
        return nome;
    }

    /**
     * getter della login dell utente
     * @return stringa con la login dell utente
     * */

    public String getLogin() {
        return login;
    }

    /**
     * getter dell id dell utente
     * @return l'id dell utente
     * */

    public Integer getId() {
        return idUtente;
    }

    /**
     * getter della password dell utente
     * @return stringa con la password dell utente
     * */

    public String getPassword() {
        return password;
    }
}
