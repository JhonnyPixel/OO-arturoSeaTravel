package dao;

import java.util.ArrayList;

public interface SocialDAO {
    void retrieve_social(int id_compagnia, ArrayList<String> nome_social, ArrayList<String> indirizzo_social);

}
