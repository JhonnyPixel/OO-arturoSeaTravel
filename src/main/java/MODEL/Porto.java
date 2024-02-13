package MODEL;

public class Porto {
    private String Indirizzo;
    private String Comune;
    private String Tel_Info;
    private Integer id_porto;

    public Porto(Integer id_porto,String ind,String com,String tel){
        this.id_porto=id_porto;
        this.Indirizzo=ind;
        this.Comune=com;
        this.Tel_Info=tel;
    }

    public String getComune(){
        return Comune;
    }

    public Integer getId() {
        return id_porto;
    }

    public String getIndirizzo() {
        return Indirizzo;
    }


    public String getTelefono() {
        return Tel_Info;
    }
}
