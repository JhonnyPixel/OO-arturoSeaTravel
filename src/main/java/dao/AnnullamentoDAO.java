package dao;

/**
 * l'interfaccia annullamentoDAO
 * */
public interface AnnullamentoDAO {
    /**
     * metodo per richiamare la funzione add annullamento sul database
     *
     * @param motivazione motivazione dell annullamento
     * @param rimborso eventuale ammontare del rimborso
     * @param idCorsa l' id della corsa a cui aggiungere l'annullamento
     * @param prossimo l'id di eventuale corsa sostitutiva
     * */
    void addAnnullamento(String motivazione, Float rimborso, Integer idCorsa, Integer prossimo);
}
