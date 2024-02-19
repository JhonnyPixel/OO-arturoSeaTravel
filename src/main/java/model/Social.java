package model;
/**
 * il tipo social
 * */
public class Social {
    private String nomeSocial;
    private String indirizzoSocial;
    public Social(String nome,String ind){
        this.nomeSocial=nome;
        this.indirizzoSocial=ind;
    }

    /**
     * getter del nome del social
     * @return stringa con il nome del social
     * */

    public String getNome() {
        return nomeSocial;
    }

    /**
     * getter dell indirizzo del social
     * @return stringa con l indirizzo del social
     * */

    public String getIndirizzo() {
        return indirizzoSocial;
    }
}
