package MODEL;

public class Annullamento{
    private String Motivazione;
    private float Rimborso;
    private int Prossimo; //id della prossima corsa disponibile sostituiva alla tratta annullata
    public Annullamento(String mot,float r,int p){
        this.Motivazione=mot;
        this.Rimborso=r;
        this.Prossimo=p;
    }
}
