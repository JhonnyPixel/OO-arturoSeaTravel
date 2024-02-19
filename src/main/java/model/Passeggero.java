package model;
/**
 * il tipo passeggero
 * */
public class Passeggero extends Utente{
    private Integer eta;

    public Passeggero(Integer idPasseggero,String nome,String login,String password,Integer eta){
        super(idPasseggero,login,password,nome);
        this.eta=eta;
    }


    /**
     * getter dell eta dell passeggero
     * @return l'eta dell passeggero
     * */

    public int getEta() {
        return this.eta;
    }
}
