package model;

/**
 * il tipo natante
 * */
public class Natante {
    private String nome;
    private String trasporta;
    private String tipo;

    private Integer idNatante;
    public Natante(Integer idNatante,String nome,String trasp,String tipo){
        this.idNatante=idNatante;
        this.nome =nome;
        this.trasporta =trasp;
        this.tipo =tipo;
    }

    /**
     * getter dell id del natante
     * @return l'id del natante
     * */

    public Integer getId() {
        return idNatante;
    }

    /**
     * getter del nome del natante
     * @return il nome del natante
     * */

    public String getNome() {
        return nome;
    }

    /**
     * getter della stringa contenente cosa trasporta il natante
     * @return cosa traposrta il natante
     * */

    public String getTrasporta() {
        return trasporta;
    }

    /**
     * getter del tipo del natante
     * @return il tipo del natante
     * */

    public String getTipo() {
        return tipo;
    }
}
