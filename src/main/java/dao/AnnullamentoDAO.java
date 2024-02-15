package dao;

public interface AnnullamentoDAO {
    void add_annullamento(String motivazione,Float rimborso,Integer id_corsa,Integer prossimo);
}
