package dao;

import java.sql.Time;

public interface RitardoDAO {
    void add_ritardo(String motivazione, Time ritardo, Integer id_corsa);

}
