package MODEL;

import javax.swing.*;
import java.util.ArrayList;

public class Compagnia extends Utente{
    private String Telefono;
    private String Mail;
    private String Sito_Web;
    private ImageIcon Logo;

    private ArrayList<Social> Socials;
    public Compagnia(Integer id_compagnia,String tel,String mail,String sito_Web,ArrayList<Social> socials,String login,String passw,String nome){
        super(id_compagnia,login,passw,nome);
        this.Telefono=tel;
        this.Mail=mail;
        this.Sito_Web=sito_Web;
        this.Socials=socials;
    }


}
