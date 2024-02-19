package model;

import java.sql.Time;
/**
 * il tipo ritardo
 * */
public class Ritardo{
    private String motivazione;
    private Time ritardo;

    public Ritardo(String motivazioneRitardo, Time tempoRitardo) {
        this.motivazione =motivazioneRitardo;
        this.ritardo =tempoRitardo;
    }

    /**
     * getter della motivazione del ritardo
     * @return la motivazione del ritardo
     * */

    public String getMotivazione() {
        return motivazione;
    }

    /**
     * getter del tempo del ritardo
     * @return il tempo del ritardo
     * */

    public Time getTempo() {
        return ritardo;
    }
}
