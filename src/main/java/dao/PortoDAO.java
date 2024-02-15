package dao;

import java.util.ArrayList;

public interface PortoDAO {
    void retrieve_porti(ArrayList<Integer> id_porto, ArrayList<String> indirizzo, ArrayList<String> comune, ArrayList<String> tel_info);
}
