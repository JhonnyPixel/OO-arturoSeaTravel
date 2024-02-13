package MODEL;

public class Natante {
    private String Nome;
    private String Trasporta;
    private String Tipo;

    private Integer id_natante;
    public Natante(Integer id_natante,String nome,String trasp,String tipo){
        this.id_natante=id_natante;
        this.Nome=nome;
        this.Trasporta=trasp;
        this.Tipo=tipo;
    }

    public Integer getId() {
        return id_natante;
    }

    public String getNome() {
        return  Nome;
    }

    public String getTrasporta() {
        return Trasporta;
    }

    public String getTipo() {
        return Tipo;
    }
}
