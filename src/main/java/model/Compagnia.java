package model;
/**
 * il tipo compagnia
 * */
public class Compagnia extends Utente{
    private String telefono;
    private String mail;
    private String sitoWeb;

    public Compagnia(Integer idCompagnia,String tel,String mail,String sitoWeb,String login,String passw,String nome){
        super(idCompagnia,login,passw,nome);
        this.telefono =tel;
        this.mail =mail;
        this.sitoWeb =sitoWeb;
    }

    /**
     * getter del numero di telefono della compagnia
     * @return numero di telefono della compagnia
     * */


    public String getTelefono() {
        return telefono;
    }

    /**
     * getter della mail della compagnia
     * @return la mail della compagnia
     * */

    public String getMail() {
        return mail;
    }

    /**
     * getter dell indirizzo al sito web della compagnia
     * @return indirizzo al sito web della compagnia
     * */

    public String getSitoWeb() {
        return sitoWeb;
    }
}
