package MODEL;

import java.sql.Time;

public class Ritardo{
    private String Motivazione;
    private Time Ritardo;

    public Ritardo(String motivazioneRitardo, Time tempoRitardo) {
        this.Motivazione=motivazioneRitardo;
        this.Ritardo=tempoRitardo;
    }
}
