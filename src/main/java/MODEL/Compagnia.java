package MODEL;

import javax.swing.*;
import java.util.ArrayList;

public class Compagnia extends Utente{
    private String Telefono;
    private String Mail;
    private String Sito_Web;

    public Compagnia(Integer id_compagnia,String tel,String mail,String sito_Web,String login,String passw,String nome){
        super(id_compagnia,login,passw,nome);
        this.Telefono=tel;
        this.Mail=mail;
        this.Sito_Web=sito_Web;
    }


    public String getTelefono() {
        return Telefono;
    }

    public String getMail() {
        return Mail;
    }

    public String getSitoWeb() {
        return Sito_Web;
    }
}
