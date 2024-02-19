package model;
/**
 * il tipo porto
 * */
public class Porto {
    private String indirizzo;
    private String comune;
    private String telInfo;
    private Integer idPorto;

    public Porto(Integer idPorto, String ind, String com, String tel){
        this.idPorto = idPorto;
        this.indirizzo =ind;
        this.comune =com;
        this.telInfo =tel;
    }

    /**
     * getter del comune del porto
     * @return il comune del porto
     * */
    public String getComune(){
        return comune;
    }

    /**
     * getter dell' id del porto
     * @return l' id del porto
     * */
    public Integer getId() {
        return idPorto;
    }

    /**
     * getter dell' indirizzo del porto
     * @return l' indirizzo del porto
     * */

    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * getter del telefono del porto
     * @return il telefono del porto
     * */

    public String getTelefono() {
        return telInfo;
    }
}
