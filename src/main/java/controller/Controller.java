package controller;

import MODEL.*;
import dao.*;
import impl_dao_postgres.*;
import java.sql.*;
import java.util.ArrayList;

public class Controller {
    private CorsaDAO corsaDao;
    private UtenteDAO utenteDao;
    private BigliettoDAO bigliettoDao;
    private NatanteDAO natanteDao;
    private PortoDAO portoDao;
    private RitardoDAO ritardoDao;
    private AnnullamentoDAO annullamentoDao;
    private SocialDAO socialDao;
    private static Controller controller;
    private ArrayList<Corsa> corseFiltrate=new ArrayList<>();
    private ArrayList<Biglietto> bigliettiUtente=new ArrayList<>();
    private ArrayList<Passeggero> accompagnatori=new ArrayList<>();
    private ArrayList<Corsa> corseCompagnia=new ArrayList<>();
    private ArrayList<Porto> porti=new ArrayList<>();
    private ArrayList<Natante> natanti=new ArrayList<>();
    private ArrayList<Social> socialsCompagnia=new ArrayList<>();
    private ArrayList<String> tratte=new ArrayList<>();
    private Utente utente;

    /**
    * Costruttore del controller che crea le implementazioniDao
    * da utilizzare per i vari metodi
    */
    private Controller(){
        corsaDao=new implCorsaDAO();
        bigliettoDao=new implBigliettoDAO();
        utenteDao=new implUtenteDAO();
        natanteDao=new implNatanteDAO();
        portoDao=new implPortoDAO();
        ritardoDao=new implRitardoDAO();
        annullamentoDao=new implAnnullamentoDAO();
        socialDao=new implSocialDAO();

    }

    /**
    * Metodo che garantisce un unico punto di accesso al controller,
    * crea un unica istanza e si assicura di restituire sempre quell istanza
    * @return controller instance
    */

    public static Controller getController(){
        if(controller==null){
            controller=new Controller();
        }
            return controller;
    }

    /**
     * metodo che richiama l implementazioneDao e costruisce
     * oggetti del model riempendo gli arraylist e
     * deserializzandoli
     *
     * @param id_corsa l'id della corsa per cui filtrare
     * @param portoPartenza il porto partenza per cui filtrare
     * @param portoArrivo il porto arrivo per cui filtrare
     * @param dataPartenza la data per cui per cui filtrare
     * @param orarioPartenza l orario per cui filtrare
     * @param prezzo il prezzo per cui filtrare
     * @param tipoNatante il tipo natante per cui filtrare
     * */

    public void updateDataCorse(Integer id_corsa,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza,Float prezzo ,String tipoNatante,ArrayList<Integer> id_corse,
                             ArrayList<Time> Orari_Partenza,ArrayList<Time> Orari_Arrivo,ArrayList<Time> Orari_Partenza_Scalo,ArrayList<Time> Orari_Arrivo_Scalo,ArrayList<Date> Date_Inizio_Servizio,
                             ArrayList<Date> Date_Fine_Servizio,ArrayList<String> Giorni_Servizio_Attivo, ArrayList<Float> Sconti_residente, ArrayList<Float> Prezzi_interi, ArrayList<Float> Prezzi_ridotti,
                             ArrayList<Float> Sovr_Veicoli, ArrayList<Float> Sovr_Bagagli, ArrayList<Float> Sovr_Prenotazioni, ArrayList<Integer> id_natanti, ArrayList<String> Nomi_natanti,
                             ArrayList<String> Trasporti, ArrayList<String> Tipi_natanti, ArrayList<Integer> id_porti_partenza, ArrayList<String> Indirizzi_porti_partenza,
                             ArrayList<String> Comuni_porti_partenza, ArrayList<String> Telefoni_porti_partenza, ArrayList<Integer> id_porti_arrivo, ArrayList<String> Indirizzi_porti_arrivo,
                             ArrayList<String> Comuni_porti_arrivo, ArrayList<String> Telefoni_porti_arrivo, ArrayList<Integer> id_porti_scalo, ArrayList<String> Indirizzi_porti_scalo,
                             ArrayList<String> Comuni_porti_scalo, ArrayList<String> Telefoni_porti_scalo, ArrayList<Integer> id_compagnie, ArrayList<String> Telefoni_compagnie,
                             ArrayList<String> Mail_compagnie, ArrayList<String> Siti_web_compagnie, ArrayList<String> login_compagnie, ArrayList<String> password_compagnie,
                             ArrayList<String> nomi_compagnie, ArrayList<String> Motivazioni_ritardi, ArrayList<Time> Tempi_ritardi, ArrayList<String> Motivazioni_annullamenti,
                             ArrayList<Float> Rimborsi, ArrayList<Integer> Prossimi){


        corsaDao.filtra_corse(id_corsa,id_corse,portoPartenza,portoArrivo,dataPartenza,orarioPartenza,prezzo,tipoNatante,Orari_Partenza,Orari_Arrivo,Orari_Partenza_Scalo,Orari_Arrivo_Scalo,Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,
                Sconti_residente,Prezzi_interi ,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,
                id_natanti,Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,
                id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,
                id_porti_arrivo,Indirizzi_porti_arrivo,Comuni_porti_arrivo,Telefoni_porti_arrivo,id_compagnie,Telefoni_compagnie,Mail_compagnie,
                Siti_web_compagnie,login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,
                Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);

        //riempio i dati transienti in memoria

        corseFiltrate.removeAll(corseFiltrate);

        for(int i=0;i<Orari_Partenza.size();i++){

            boolean[] H =stringToBoolArray(Giorni_Servizio_Attivo.get(i));


            Natante natante=new Natante(id_natanti.get(i),Nomi_natanti.get(i),Trasporti.get(i),Tipi_natanti.get(i));

            Porto porto_partenza=new Porto(id_porti_partenza.get(i),Indirizzi_porti_partenza.get(i),Comuni_porti_partenza.get(i),Telefoni_porti_partenza.get(i));
            Porto porto_scalo=new Porto(id_porti_scalo.get(i),Indirizzi_porti_scalo.get(i),Comuni_porti_scalo.get(i),Telefoni_porti_scalo.get(i));
            Porto porto_arrivo=new Porto(id_porti_arrivo.get(i),Indirizzi_porti_arrivo.get(i), Comuni_porti_arrivo.get(i), Telefoni_porti_arrivo.get(i));

            Compagnia compagnia=new Compagnia(id_compagnie.get(i),Telefoni_compagnie.get(i),Mail_compagnie.get(i),Siti_web_compagnie.get(i),
                    login_compagnie.get(i),password_compagnie.get(i), nomi_compagnie.get(i));
            Ritardo ritardo=null;
            Annullamento annullamento=null;
            if(Motivazioni_ritardi.get(i)!=null){
                ritardo=new Ritardo(Motivazioni_ritardi.get(i),Tempi_ritardi.get(i));
            }
            if(Motivazioni_annullamenti.get(i)!=null){
                annullamento=new Annullamento(Motivazioni_annullamenti.get(i), Rimborsi.get(i),Prossimi.get(i));
            }
            Corsa corsa=new Corsa(id_corse.get(i),Orari_Partenza.get(i),Orari_Partenza_Scalo.get(i),Orari_Arrivo_Scalo.get(i),Orari_Arrivo.get(i),Date_Inizio_Servizio.get(i),Date_Fine_Servizio.get(i),H,
                    Sconti_residente.get(i),Prezzi_interi.get(i),Prezzi_ridotti.get(i),Sovr_Veicoli.get(i),
                    Sovr_Bagagli.get(i),Sovr_Prenotazioni.get(i),natante,porto_partenza,porto_arrivo,porto_scalo,compagnia,ritardo,annullamento
            );
            corseFiltrate.add(corsa);

        }
    }
    /**
     * metodo che decide a seconda del parametro update se aggiornare i dati tramite
     * updateDataCorse o prendere i dati transienti dal model.
     * Riempe gli ArrayList con i dati serializzati da restituire alla gui.
     *
     * @param id_corsa l'id della corsa per cui filtrare
     * @param portoPartenza il porto partenza per cui filtrare
     * @param portoArrivo il porto arrivo per cui filtrare
     * @param dataPartenza la data per cui per cui filtrare
     * @param orarioPartenza l orario per cui filtrare
     * @param prezzo il prezzo per cui filtrare
     * @param tipoNatante il tipo natante per cui filtrare
     * */
    public void filtraCorse(Integer id_corsa,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza,Float prezzo ,String tipoNatante,boolean update,ArrayList<Integer> id_corse,
                       ArrayList<Time> Orari_Partenza,ArrayList<Time> Orari_Arrivo,ArrayList<Time> Orari_Partenza_Scalo,ArrayList<Time> Orari_Arrivo_Scalo,ArrayList<Date> Date_Inizio_Servizio,
                       ArrayList<Date> Date_Fine_Servizio,ArrayList<String> Giorni_Servizio_Attivo, ArrayList<Float> Sconti_residente, ArrayList<Float> Prezzi_interi, ArrayList<Float> Prezzi_ridotti,
                       ArrayList<Float> Sovr_Veicoli, ArrayList<Float> Sovr_Bagagli, ArrayList<Float> Sovr_Prenotazioni, ArrayList<Integer> id_natanti, ArrayList<String> Nomi_natanti,
                       ArrayList<String> Trasporti, ArrayList<String> Tipi_natanti, ArrayList<Integer> id_porti_partenza, ArrayList<String> Indirizzi_porti_partenza,
                       ArrayList<String> Comuni_porti_partenza, ArrayList<String> Telefoni_porti_partenza, ArrayList<Integer> id_porti_arrivo, ArrayList<String> Indirizzi_porti_arrivo,
                       ArrayList<String> Comuni_porti_arrivo, ArrayList<String> Telefoni_porti_arrivo, ArrayList<Integer> id_porti_scalo, ArrayList<String> Indirizzi_porti_scalo,
                       ArrayList<String> Comuni_porti_scalo, ArrayList<String> Telefoni_porti_scalo, ArrayList<Integer> id_compagnie, ArrayList<String> Telefoni_compagnie,
                       ArrayList<String> Mail_compagnie, ArrayList<String> Siti_web_compagnie, ArrayList<String> login_compagnie, ArrayList<String> password_compagnie,
                       ArrayList<String> nomi_compagnie, ArrayList<String> Motivazioni_ritardi, ArrayList<Time> Tempi_ritardi, ArrayList<String> Motivazioni_annullamenti,
                       ArrayList<Float> Rimborsi, ArrayList<Integer> Prossimi){
        if(update){
            updateDataCorse(id_corsa,portoPartenza,portoArrivo ,dataPartenza,orarioPartenza,prezzo ,tipoNatante, id_corse,
                 Orari_Partenza, Orari_Arrivo, Orari_Partenza_Scalo,Orari_Arrivo_Scalo,Date_Inizio_Servizio,
                 Date_Fine_Servizio, Giorni_Servizio_Attivo,  Sconti_residente,  Prezzi_interi,  Prezzi_ridotti,
                 Sovr_Veicoli, Sovr_Bagagli,  Sovr_Prenotazioni,  id_natanti,  Nomi_natanti,
                 Trasporti,  Tipi_natanti,  id_porti_partenza,  Indirizzi_porti_partenza,
                 Comuni_porti_partenza,  Telefoni_porti_partenza,  id_porti_arrivo,  Indirizzi_porti_arrivo,
                 Comuni_porti_arrivo,  Telefoni_porti_arrivo,  id_porti_scalo, Indirizzi_porti_scalo,
                 Comuni_porti_scalo,  Telefoni_porti_scalo,  id_compagnie,  Telefoni_compagnie,
                 Mail_compagnie,  Siti_web_compagnie,  login_compagnie,  password_compagnie,
                nomi_compagnie,  Motivazioni_ritardi, Tempi_ritardi,  Motivazioni_annullamenti,
                Rimborsi, Prossimi);
        }
        else{
            for(Corsa c: corseFiltrate){
                id_corse.add(c.getId_corsa());
                Orari_Partenza.add(c.getOrario_Partenza());
                Orari_Arrivo.add(c.getOrario_Arrivo());
                Orari_Partenza_Scalo.add(c.getOrarioPartenzaScalo());
                Orari_Arrivo_Scalo.add(c.getOrarioArrivoScalo());
                Date_Inizio_Servizio.add(c.getDataInizioServizio());
                Date_Fine_Servizio.add(c.getDataFineServizio());
                Giorni_Servizio_Attivo.add(boolArrayToString(c.getGiorniServizioAttivo()));
                Sconti_residente.add(c.getScontoResidente());
                Prezzi_interi.add(c.getPrezzoIntero());
                Prezzi_ridotti.add(c.getPrezzoRidotto());
                Sovr_Veicoli.add(c.getSovrVeicolo());
                Sovr_Bagagli.add(c.getSovrBagagli());
                Sovr_Prenotazioni.add(c.getSovrPrenotazione());
                id_natanti.add(c.getNatante().getId());
                Nomi_natanti.add(c.getNatante().getNome());
                Trasporti.add(c.getNatante().getTrasporta());
                Tipi_natanti.add(c.getNatante().getTipo());
                id_porti_partenza.add(c.getPorto_Partenza().getId());
                id_porti_arrivo.add(c.getPorto_Arrivo().getId());
                Indirizzi_porti_partenza.add(c.getPorto_Partenza().getIndirizzo());
                Comuni_porti_partenza.add(c.getPorto_Partenza().getComune());
                Indirizzi_porti_arrivo.add(c.getPorto_Arrivo().getIndirizzo());
                Comuni_porti_arrivo.add(c.getPorto_Arrivo().getComune());
                Telefoni_porti_partenza.add(c.getPorto_Partenza().getTelefono());
                Telefoni_porti_arrivo.add(c.getPorto_Arrivo().getTelefono());
                id_porti_scalo.add(c.getPorto_Scalo().getId());
                Indirizzi_porti_scalo.add(c.getPorto_Scalo().getIndirizzo());
                Comuni_porti_scalo.add(c.getPorto_Scalo().getComune());
                Telefoni_porti_scalo.add(c.getPorto_Scalo().getTelefono());
                id_compagnie.add(c.getCompagnia().getId());
                Telefoni_compagnie.add(c.getCompagnia().getTelefono());
                Mail_compagnie.add(c.getCompagnia().getMail());
                Siti_web_compagnie.add(c.getCompagnia().getSitoWeb());
                login_compagnie.add(c.getCompagnia().getLogin());
                password_compagnie.add(c.getCompagnia().getPassword());
                nomi_compagnie.add(c.getCompagnia().getNome());
                Motivazioni_ritardi.add((c.getRitardo()!=null) ? c.getRitardo().getMotivazione():null);
                Tempi_ritardi.add((c.getRitardo()!=null)?c.getRitardo().getTempo():null);
                Motivazioni_annullamenti.add((c.getAnnullamento()!=null)?c.getAnnullamento().getMotivazione():null);
                Rimborsi.add((c.getAnnullamento()!=null)?c.getAnnullamento().getRimborso():null);
                Prossimi.add((c.getAnnullamento()!=null)?c.getAnnullamento().getProssimo():null);
                }
        }


    }

    /**
     * metodo che converte un array booleano in una stringa binaria.
     * @param bools array booleano da convertire
     * @return stringa binaria
     * */
    public String boolArrayToString(boolean[] bools){
        String s="";
        for(int i=0;i<bools.length;i++){
            s=s.concat((bools[i])? "1":"0");
        }

        return s;
    }

    /**
     * metodo che converte una stringa binaria in un array booleano.
     * @param bools stringa binaria da convertire
     * @return array booleano
     * */
    public boolean[] stringToBoolArray(String bools){
        boolean[] b=new boolean[7];
        char[] c=bools.toCharArray();
        for(int k=0;k<7;k++){
            b[k]=(c[k]=='1');
        }
        return b;
    }

    /**
     * metodo che prende i comuni dei porti di partenza delle corse filtrate precedentemente.
     * @return ArrayList di stringhe contenente i comuni dei porti
     * */
    public ArrayList<String> getComuniPortiPartenzaCorseFiltrate(){
        ArrayList<String> comuniPartenza=new ArrayList<>();
        for (Corsa c: corseFiltrate){
            comuniPartenza.add(c.getPorto_Partenza().getComune());
        }

        return comuniPartenza;
    }

    /**
     * metodo che prende i comuni dei porti di arrivo delle corse filtrate precedentemente.
     * @return ArrayList di stringhe contenente i comuni dei porti
     * */
    public ArrayList<String> getComuniPortiArrivoCorseFiltrate(){
        ArrayList<String> comuniArrivo=new ArrayList<>();
        for (Corsa c: corseFiltrate){
            comuniArrivo.add(c.getPorto_Arrivo().getComune());
        }

        return comuniArrivo;
    }


    /**
     * metodo che permette al passeggero di eseguire il login.
     * @param loginIn la login inserita dall utente
     * @param passwordIn la password inserita dall utente
     * @return Passeggero se l'operazione va a buon fine, null altrimenti.
     * */

    public Passeggero loginUtente(String loginIn,String passwordIn){


        ArrayList<Integer> id_passeggero=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<Integer> eta=new ArrayList<>();

        utenteDao.login_passeggero(id_passeggero,nome,login,password,eta,loginIn,passwordIn);

        if(id_passeggero.isEmpty()){
            return null;
        }


        return new Passeggero(id_passeggero.get(0),nome.get(0),login.get(0),password.get(0),eta.get(0));

    }

    /**
     * metodo che permette alla compagnia di eseguire il login.
     * @param loginIn la login inserita dall utente
     * @param passwordIn la password inserita dall utente
     * @return Compagnia se l'operazione va a buon fine, null altrimenti.
     * */
    public Compagnia loginCompagnia(String loginIn,String passwordIn){

        ArrayList<Integer> id_compagnia=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<String> telefono=new ArrayList<>();
        ArrayList<String> mail=new ArrayList<>();
        ArrayList<String> sitoWeb=new ArrayList<>();

        utenteDao.login_compagnia(id_compagnia,nome,login,password,telefono,mail,sitoWeb,loginIn,passwordIn);

        if(id_compagnia.isEmpty()){
            return null;
        }


        return new Compagnia(id_compagnia.getFirst(), telefono.getFirst(),mail.getFirst(),sitoWeb.getFirst(),login.getFirst(),password.getFirst(),nome.getFirst());

    }

    /**
     * metodo che permette di registarsi alla piattaforma come passeggero.
     * @param nome l username inserito dall utente
     * @param login la login inserita dall utente
     * @param password la password inserita dall utente
     * @param eta l'eta inserita dall utente
     * */

    public void register_passeggero(String nome,String login,String password,Integer eta)throws SQLException{
        utenteDao.register_passeggero(nome,login,password,eta);
    }

    /**
     * metodo che permette alla compagnia di modificare o creare una nuova corsa nel database.
     *
     * @param idCorsa id della corsa
     * @param orarioPartenza orarioPartenza della corsa
     * @param orarioArrivo orarioArrivo della corsa
     * @param orarioPartenzaScalo orarioPartenzaScalo della corsa
     * @param orarioArrivoScalo orarioArrivoScalo della corsa
     * @param dataInizioServizio dataInizioServizio della corsa
     * @param dataFineServizio dataFineServizio della corsa
     * @param giorniServizioAttivo giorniServizioAttivo della corsa
     * @param sovrPrenotazione sovrPrenotazione della corsa
     * @param sovrBagaglio sovrBagaglio della corsa
     * @param sovrVeicolo sovrVeicolo della corsa
     * @param prezzoIntero prezzoIntero della corsa
     * @param prezzoRidotto prezzoRidotto della corsa
     * @param scontoResidente scontoResidente della corsa
     * @param portoPartenza portoPartenza della corsa
     * @param portoArrivo portoArrivo della corsa
     * @param portoScalo portoScalo della corsa
     * @param compagnia compagnia della corsa
     * @param natante natante della corsa
     * */
    public void create_update_corsa(Integer idCorsa ,Time orarioPartenza , Time orarioArrivo ,
                                    Time orarioPartenzaScalo , Time orarioArrivoScalo ,
                                    Date dataInizioServizio , Date dataFineServizio ,
                                    boolean[] giorniServizioAttivo , Float sovrPrenotazione ,
                                    Float sovrBagaglio , Float sovrVeicolo , Float prezzoIntero ,
                                    Float prezzoRidotto , Float scontoResidente , Integer portoPartenza ,
                                    Integer portoArrivo ,Integer portoScalo, Integer compagnia , Integer natante )throws SQLException{


        corsaDao.create_update_corsa(idCorsa, orarioPartenza, orarioArrivo,orarioPartenzaScalo,orarioArrivoScalo, dataInizioServizio, dataFineServizio, boolArrayToString(giorniServizioAttivo), sovrPrenotazione, sovrBagaglio, sovrVeicolo, prezzoIntero, prezzoRidotto, scontoResidente, portoPartenza, portoArrivo,portoScalo, compagnia, natante);
    }
    /**
     * metodo che riempe il model con i dati dei biglietti interi di un passeggero riempendo gli array e deserializzandoli.
     * @param idPasseggero l' id del passeggero di cui prendere i biglietti
     * */
    public void updateBigliettiInteri(Integer idPasseggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,
                                          ArrayList<String> veicolo,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa){

        bigliettoDao.retrieve_biglietti_interi(idPasseggero,importo_totale,Sovrapprezzo_tot,n_bagagli,veicolo,prenotazione,corsa);

        bigliettiUtente.removeAll(bigliettiUtente);
        for(int i=0;i<importo_totale.size();i++){
            Biglietto_Intero b=new Biglietto_Intero(importo_totale.get(i),Sovrapprezzo_tot.get(i),n_bagagli.get(i),veicolo.get(i),prenotazione.get(i),corsa.get(i));
            bigliettiUtente.add(b);
        }


    }
    /**
     * metodo che riempe 
     * */
    public void retrieve_biglietti_interi(Integer idPasseggero,boolean update,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,
                                          ArrayList<String> veicolo,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa){
        if(update){
            updateBigliettiInteri(idPasseggero, importo_totale, Sovrapprezzo_tot, n_bagagli, veicolo, prenotazione, corsa);
        }else{
            for(Biglietto b:bigliettiUtente){
                importo_totale.add(b.getImporto());
                Sovrapprezzo_tot.add(b.getSovr());
                n_bagagli.add(b.getNBagagli());
                prenotazione.add(b.getPrenotazione());
                corsa.add(b.getIdCorsa());
                veicolo.add(((Biglietto_Intero)b).getVeicolo());
            }
        }
    }
    /**
     * metodo che riempe il model con i dati dei biglietti ridotti di un passeggero riempendo gli array e deserializzandoli.
     * @param idPasseggero l' id del passeggero di cui prendere i biglietti
     * */
    public void updateBigliettiRidotti(Integer idPasseggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli
                                           ,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore){


        bigliettoDao.retrieve_biglietti_ridotti(idPasseggero,importo_totale,Sovrapprezzo_tot,n_bagagli,prenotazione,corsa,accompagnatore);
        bigliettiUtente.removeAll(bigliettiUtente);
        for(int i=0;i<importo_totale.size();i++){
            Biglietto_Ridotto b=new Biglietto_Ridotto(importo_totale.get(i),Sovrapprezzo_tot.get(i),n_bagagli.get(i),prenotazione.get(i),corsa.get(i),accompagnatore.get(i));
            bigliettiUtente.add(b);
        }
    }

    public void retrieve_biglietti_ridotti(Integer id_passeggero,boolean update,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli
            ,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore){
        if(update){
            updateBigliettiRidotti(id_passeggero, importo_totale, Sovrapprezzo_tot, n_bagagli, prenotazione, corsa, accompagnatore);
        }else{
            for (Biglietto b:bigliettiUtente){
                importo_totale.add(b.getImporto());
                Sovrapprezzo_tot.add(b.getSovr());
                n_bagagli.add(b.getNBagagli());
                prenotazione.add(b.getPrenotazione());
                corsa.add(b.getIdCorsa());
                accompagnatore.add(((Biglietto_Ridotto)b).getAccompagnatore());
            }
        }

    }

    public void add_ritardo(String motivazione,Time ritardo,Integer id_corsa){
        ritardoDao.add_ritardo(motivazione, ritardo, id_corsa);
    }

    public void add_annullamento(String motivazione,Float rimborso,Integer id_corsa,Integer prossimo){
        annullamentoDao.add_annullamento(motivazione,rimborso,id_corsa,prossimo);
    }
    public void updateCorseCompagnia(Compagnia compagnia,ArrayList<Integer> id_corsa,
                                         ArrayList<Time> orario_partenza,ArrayList<Time> orario_arrivo,
                                         ArrayList<Time> orario_partenza_scalo,ArrayList<Time> orario_arrivo_scalo,
                                         ArrayList<Date> data_inizio_servizio, ArrayList<Date> data_fine_servizio,
                                         ArrayList<String> giorni_servizio_attivo, ArrayList<Float> sovr_prenotazione, ArrayList<Float> sovr_bagaglio,
                                         ArrayList<Float> sovr_veicolo, ArrayList<Float> prezzo_intero, ArrayList<Float> prezzo_ridotto,
                                         ArrayList<Float> sconto_residente, ArrayList<Integer> porto_partenza, ArrayList<Integer> porto_arrivo,
                                         ArrayList<Integer> porto_scalo, ArrayList<Integer> natante, ArrayList<String> p1_indirizzo,
                                         ArrayList<String> p1_comune, ArrayList<String> p1_tel_info, ArrayList<String> p2_indirizzo,
                                         ArrayList<String> p2_comune, ArrayList<String> p2_tel_info, ArrayList<String> ps_indirizzo,
                                         ArrayList<String> ps_comune, ArrayList<String> ps_tel_info, ArrayList<String> nome_natante,
                                         ArrayList<String> trasporta, ArrayList<String> tipo_natante
    ){


        corsaDao.retrieve_corse_compagnia(compagnia.getId(),id_corsa,orario_partenza,orario_arrivo,
                orario_partenza_scalo, orario_arrivo_scalo,data_inizio_servizio,
                data_fine_servizio,giorni_servizio_attivo,sovr_prenotazione,sovr_bagaglio,sovr_veicolo,
                prezzo_intero,prezzo_ridotto,sconto_residente,porto_partenza,porto_arrivo,porto_scalo,natante,
                p1_indirizzo,p1_comune,p1_tel_info,p2_indirizzo,p2_comune,p2_tel_info,ps_indirizzo,
                ps_comune,ps_tel_info,nome_natante,trasporta,tipo_natante);

        for(int i=0;i<id_corsa.size();i++){

            boolean[] array_giorni_servizio_attivo =stringToBoolArray(giorni_servizio_attivo.get(i));


            Porto porto_part=new Porto(porto_partenza.get(i),p1_indirizzo.get(i),p1_comune.get(i),p1_tel_info.get(i));
            Porto porto_scal=new Porto(porto_scalo.get(i),ps_indirizzo.get(i),ps_comune.get(i),ps_tel_info.get(i));
            Porto porto_arri=new Porto(porto_arrivo.get(i),p2_indirizzo.get(i),p2_comune.get(i),p2_tel_info.get(i));

            Natante nat=new Natante(natante.get(i),nome_natante.get(i),trasporta.get(i),tipo_natante.get(i));

            Corsa corsa=new Corsa(id_corsa.get(i),orario_partenza.get(i),orario_arrivo.get(i),orario_partenza_scalo.get(i),orario_arrivo_scalo.get(i),data_inizio_servizio.get(i),data_fine_servizio.get(i),array_giorni_servizio_attivo,
                    sconto_residente.get(i),prezzo_intero.get(i),prezzo_ridotto.get(i),sovr_veicolo.get(i),sovr_bagaglio.get(i),sovr_prenotazione.get(i),
                    nat,porto_part,porto_arri,porto_scal,compagnia,null,null);

            corseCompagnia.add(corsa);
        }




    }

    public void retrieve_corse_compagnia(Compagnia compagnia,boolean update,ArrayList<Integer> id_corsa,
                                         ArrayList<Time> orario_partenza,ArrayList<Time> orario_arrivo,
                                         ArrayList<Time> orario_partenza_scalo,ArrayList<Time> orario_arrivo_scalo,
                                         ArrayList<Date> data_inizio_servizio, ArrayList<Date> data_fine_servizio,
                                         ArrayList<String> giorni_servizio_attivo, ArrayList<Float> sovr_prenotazione, ArrayList<Float> sovr_bagaglio,
                                         ArrayList<Float> sovr_veicolo, ArrayList<Float> prezzo_intero, ArrayList<Float> prezzo_ridotto,
                                         ArrayList<Float> sconto_residente, ArrayList<Integer> porto_partenza, ArrayList<Integer> porto_arrivo,
                                         ArrayList<Integer> porto_scalo, ArrayList<Integer> natante, ArrayList<String> p1_indirizzo,
                                         ArrayList<String> p1_comune, ArrayList<String> p1_tel_info, ArrayList<String> p2_indirizzo,
                                         ArrayList<String> p2_comune, ArrayList<String> p2_tel_info, ArrayList<String> ps_indirizzo,
                                         ArrayList<String> ps_comune, ArrayList<String> ps_tel_info, ArrayList<String> nome_natante,
                                         ArrayList<String> trasporta, ArrayList<String> tipo_natante){

        if(update){
            updateCorseCompagnia(compagnia, id_corsa, orario_partenza, orario_arrivo, orario_partenza_scalo, orario_arrivo_scalo,data_inizio_servizio, data_fine_servizio, giorni_servizio_attivo, sovr_prenotazione, sovr_bagaglio, sovr_veicolo, prezzo_intero, prezzo_ridotto, sconto_residente, porto_partenza, porto_arrivo, porto_scalo, natante, p1_indirizzo, p1_comune, p1_tel_info, p2_indirizzo, p2_comune, p2_tel_info, ps_indirizzo, ps_comune, ps_tel_info, nome_natante, trasporta, tipo_natante);
        }else{
            for(Corsa c: corseCompagnia){
                id_corsa.add(c.getId_corsa());
                orario_partenza.add(c.getOrario_Partenza());
                orario_arrivo.add(c.getOrario_Arrivo());
                orario_partenza_scalo.add(c.getOrarioPartenzaScalo());
                orario_arrivo_scalo.add(c.getOrarioArrivoScalo());
                data_inizio_servizio.add(c.getDataInizioServizio());
                data_fine_servizio.add(c.getDataFineServizio());
                giorni_servizio_attivo.add(boolArrayToString(c.getGiorniServizioAttivo()));
                sconto_residente.add(c.getScontoResidente());
                prezzo_intero.add(c.getPrezzoIntero());
                prezzo_ridotto.add(c.getPrezzoRidotto());
                sovr_veicolo.add(c.getSovrVeicolo());
                sovr_bagaglio.add(c.getSovrBagagli());
                sovr_prenotazione.add(c.getSovrPrenotazione());
                natante.add(c.getNatante().getId());
                nome_natante.add(c.getNatante().getNome());
                trasporta.add(c.getNatante().getTrasporta());
                tipo_natante.add(c.getNatante().getTipo());
                porto_partenza.add(c.getPorto_Partenza().getId());
                porto_arrivo.add(c.getPorto_Arrivo().getId());
                p1_indirizzo.add(c.getPorto_Partenza().getIndirizzo());
                p1_comune.add(c.getPorto_Partenza().getComune());
                p2_indirizzo.add(c.getPorto_Arrivo().getIndirizzo());
                p2_comune.add(c.getPorto_Arrivo().getComune());
                p1_tel_info.add(c.getPorto_Partenza().getTelefono());
                p2_tel_info.add(c.getPorto_Arrivo().getTelefono());
                porto_scalo.add(c.getPorto_Scalo().getId());
                ps_indirizzo.add(c.getPorto_Scalo().getIndirizzo());
                ps_comune.add(c.getPorto_Scalo().getComune());
                ps_tel_info.add(c.getPorto_Scalo().getTelefono());

            }
        }

    }

    public void updateAccompagnatori(ArrayList<Integer> id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){

        utenteDao.retrieve_accompagnatori(id_passeggero,nome,login,password,eta);

        for (int i = 0; i < id_passeggero.size(); i++) {
            Passeggero p=new Passeggero(id_passeggero.get(i), nome.get(i),login.get(i),password.get(i),eta.get(i));
            accompagnatori.add(p);
        }

    }

    public void retrieve_accompagnatori(boolean update,ArrayList<Integer> id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        if (update){
            updateAccompagnatori(id_passeggero, nome, login, password, eta);
        }else{
            for(Passeggero p:accompagnatori){
                id_passeggero.add(p.getId());
                nome.add(p.getNome());
                login.add(p.getLogin());
                password.add(p.getPassword());
                eta.add(p.getEta());
            }
        }
    }

    private void updatePorti(ArrayList<Integer> id_porto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> tel_info){

        portoDao.retrieve_porti(id_porto,indirizzo,comune,tel_info);

        for (int i = 0; i < id_porto.size(); i++) {
            Porto p = new Porto(id_porto.get(i), indirizzo.get(i), comune.get(i), tel_info.get(i));
            porti.add(p);
        }


    }
    public void updatePorti(){
        ArrayList<String> comuni_porti=new ArrayList<>();
        ArrayList<Integer> id_porti=new ArrayList<>();
        ArrayList<String> tel_porti=new ArrayList<>();
        ArrayList<String> indirizzo_porti=new ArrayList<>();

        updatePorti(id_porti,indirizzo_porti,comuni_porti,tel_porti);

    }
    public void retrieve_porti(boolean update,ArrayList<Integer> id_porto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> tel_info){
        if(update){
            updatePorti(id_porto, indirizzo, comune, tel_info);
        }else{
            for(Porto p:porti){
                id_porto.add(p.getId());
                indirizzo.add(p.getIndirizzo());
                comune.add(p.getComune());
                tel_info.add(p.getTelefono());
            }
        }
    }

    public void updateNatanti(ArrayList<Integer> id_natante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo){

        natanteDao.retrieve_natanti(id_natante,nome,trasporta,tipo);

        for (int i = 0; i < id_natante.size(); i++) {
            Natante nat=new Natante(id_natante.get(i),nome.get(i),trasporta.get(i),tipo.get(i));
            natanti.add(nat);
        }

    }

    public void updateNatanti() {
        ArrayList<Integer> id_natante=new ArrayList<>();
        ArrayList<String> nome_natante=new ArrayList<>();
        ArrayList<String> trasporta=new ArrayList<>();
        ArrayList<String> tipo=new ArrayList<>();

        updateNatanti(id_natante,nome_natante,trasporta,tipo);
    }

    public void retrieve_natanti(boolean update,ArrayList<Integer> id_natante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo){
        if(update){
            updateNatanti(id_natante, nome, trasporta, tipo);
        }else{
            for(Natante n:natanti){
                id_natante.add(n.getId());
                nome.add(n.getNome());
                trasporta.add(n.getTrasporta());
                tipo.add(n.getTipo());
            }
        }
    }

    public void delete_corsa(Integer id_corsa){
        corsaDao.delete_corsa(id_corsa);
    }

    public void add_biglietto_intero(float importo_totale,float sovrapprezzo_tot,int n_bagagli,String veicolo,Timestamp prenotazione,int corsa,int passeggero){
        bigliettoDao.add_biglietto_intero(importo_totale, sovrapprezzo_tot, n_bagagli, veicolo, prenotazione, corsa, passeggero);
    }

    public void add_biglietto_ridotto(float importo_totale,float sovrapprezzo_tot,int n_bagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore){
        bigliettoDao.add_biglietto_ridotto(importo_totale, sovrapprezzo_tot, n_bagagli, prenotazione, corsa, passeggero, accompagnatore);
    }

    public void change_password(String login,String new_password,String old_password)throws SQLException{
        utenteDao.change_password(login, new_password, old_password);
    }

    public void change_login(String old_login,String new_login,String password) throws SQLException{
        utenteDao.change_login(old_login, new_login, password);
    }

    public void updateSocial(int id_compagnia,ArrayList<String> nome_social,ArrayList<String> indirizzo_social){
        socialDao.retrieve_social(id_compagnia,nome_social,indirizzo_social);

        for (int i = 0; i < nome_social.size(); i++) {
            socialsCompagnia.add(new Social(nome_social.get(i),indirizzo_social.get(i)));
        }
    }

    public void retrieve_social(boolean update,int id_compagnia,ArrayList<String> nome_social,ArrayList<String> indirizzo_social){
        if (update){
            updateSocial(id_compagnia, nome_social, indirizzo_social);
        }else{
            for (Social s:socialsCompagnia){
                nome_social.add(s.getNome());
                indirizzo_social.add(s.getIndirizzo());
            }
        }
    }

    public String getNome(Utente compagnia) {
        return compagnia.getNome();
    }


    public int getIdUtente() {
        return utente.getId();
    }

    public int getEtaPasseggero(){
        return ((Passeggero)utente).getEta();
    }

    public void setTratte(ArrayList<String> tratte) {
        this.tratte=tratte;
    }

    public ArrayList<String> getTratte(){
        return this.tratte;
    }

    public void setUtenteLoggato(Utente utente) {
        this.utente=utente;
    }


    public Utente getUtente() {
        return this.utente;
    }


}
