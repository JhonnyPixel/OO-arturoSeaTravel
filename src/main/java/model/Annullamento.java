package model;

/**
 * il tipo annullamento
 * */
public class Annullamento{
    private String motivazione;
    private float rimborso;
    private Integer prossimo;
    public Annullamento(String motivazione,float rimborso,Integer prossimo){
        this.motivazione =motivazione;
        this.rimborso =rimborso;
        this.prossimo =prossimo;
    }

    /**
     * getter della motivazione dell annullamento
     * @return la motivazione dell annullamento
     * */

    public String getMotivazione() {
        return motivazione;
    }

    /**
     * getter del rimborso dell annullamento
     * @return il rimborso dell annullamento
     * */

    public Float getRimborso() {
        return rimborso;
    }

    /**
     * getter del prossimo dell annullamento
     * @return l id della prossima corsa sostitutiva dell annullamento
     * */

    public Integer getProssimo() {
        return prossimo;
    }
}
