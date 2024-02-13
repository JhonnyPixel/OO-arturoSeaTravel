package MODEL;

import controller.Controller;

import java.util.ArrayList;

public class Passeggero extends Utente{
    private ArrayList<Biglietto> Biglietti;

    private Integer eta;

    public Passeggero(Integer id_passeggero,String nome,String login,String password,Integer eta){
        super(id_passeggero,login,password,nome);
        this.eta=eta;
    }


    public int getEta() {
        return this.eta;
    }
}
