package MODEL;

public class Utente {
    protected String Nome;
    protected String Login;
    protected String Password;

    protected Integer id_utente;
    public Utente(Integer id_utente,String login,String pssw,String nome){
        this.id_utente=id_utente;
        this.Nome=nome;
        this.Login=login;
        this.Password=pssw;
    }

    public String getNome(){
        return Nome;
    }

    public String getLogin() {
        return Login;
    }

    public Integer getId() {
        return id_utente;
    }

}
