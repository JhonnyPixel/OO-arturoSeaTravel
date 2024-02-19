package controller;

import model.*;
import dao.*;
import impl_dao_postgres.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * La classe controller racchiude tutta la business logic della nostra applicazione:
 * è la classe che si occupa di controllare , manipolare ed elaborare i dati e fa da
 * tramite nella nostra architettura fra il model e la gui.
 * */

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
    private ArrayList<Corsa> corse=new ArrayList<>();
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
     * @param idCorsa l'id della corsa per cui filtrare
     * @param portoPartenza il porto partenza per cui filtrare
     * @param portoArrivo il porto arrivo per cui filtrare
     * @param dataPartenza la data per cui per cui filtrare
     * @param orarioPartenza l orario per cui filtrare
     * @param prezzo il prezzo per cui filtrare
     * @param tipoNatante il tipo natante per cui filtrare
     * */

    public void updateDataCorse(Integer idCorsa,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza,Float prezzo ,String tipoNatante,ArrayList<Integer> idCorse,
                             ArrayList<Time> orariPartenza,ArrayList<Time> orariArrivo,ArrayList<Time> orariPartenzaScalo,ArrayList<Time> orariArrivoScalo,ArrayList<Date> dateInizioServizio,
                             ArrayList<Date> dateFineServizio,ArrayList<String> giorniServizioAttivo, ArrayList<Float> scontiResidente, ArrayList<Float> prezziInteri, ArrayList<Float> prezziRidotti,
                             ArrayList<Float> sovrVeicoli, ArrayList<Float> sovrBagagli, ArrayList<Float> sovrPrenotazioni, ArrayList<Integer> idNatanti, ArrayList<String> nomiNatanti,
                             ArrayList<String> trasporti, ArrayList<String> tipiNatanti, ArrayList<Integer> idPortiPartenza, ArrayList<String> indirizziPortiPartenza,
                             ArrayList<String> comuniPortiPartenza, ArrayList<String> telefoniPortiPartenza, ArrayList<Integer> idPortiArrivo, ArrayList<String> indirizziPortiArrivo,
                             ArrayList<String> comuniPortiArrivo, ArrayList<String> telefoniPortiArrivo, ArrayList<Integer> idPortiScalo, ArrayList<String> indirizziPortiScalo,
                             ArrayList<String> comuniPortiScalo, ArrayList<String> telefoniPortiScalo, ArrayList<Integer> idCompagnie, ArrayList<String> telefoniCompagnie,
                             ArrayList<String> mailCompagnie, ArrayList<String> sitiWebCompagnie, ArrayList<String> loginCompagnie, ArrayList<String> passwordCompagnie,
                             ArrayList<String> nomiCompagnie, ArrayList<String> motivazioniRitardi, ArrayList<Time> tempiRitardi, ArrayList<String> motivazioniAnnullamenti,
                             ArrayList<Float> rimborsi, ArrayList<Integer> prossimi){


        corsaDao.filtraCorse(idCorsa,idCorse,portoPartenza,portoArrivo,dataPartenza,orarioPartenza,prezzo,tipoNatante,orariPartenza,orariArrivo,orariPartenzaScalo,orariArrivoScalo,dateInizioServizio,dateFineServizio,giorniServizioAttivo,
                scontiResidente,prezziInteri ,prezziRidotti,sovrVeicoli,sovrBagagli,sovrPrenotazioni,
                idNatanti,nomiNatanti,trasporti,tipiNatanti,idPortiPartenza,indirizziPortiPartenza,comuniPortiPartenza,telefoniPortiPartenza,
                idPortiScalo,indirizziPortiScalo,comuniPortiScalo,telefoniPortiScalo,
                idPortiArrivo,indirizziPortiArrivo,comuniPortiArrivo,telefoniPortiArrivo,idCompagnie,telefoniCompagnie,mailCompagnie,
                sitiWebCompagnie,loginCompagnie,passwordCompagnie,nomiCompagnie,motivazioniRitardi,
                tempiRitardi,motivazioniAnnullamenti,rimborsi,prossimi);

        //riempio i dati transienti in memoria

        corseFiltrate.clear();

        for(int i=0;i<orariPartenza.size();i++){

            boolean[] boolArray =stringToBoolArray(giorniServizioAttivo.get(i));


            Natante natante=new Natante(idNatanti.get(i),nomiNatanti.get(i),trasporti.get(i),tipiNatanti.get(i));

            Porto porto_partenza=new Porto(idPortiPartenza.get(i),indirizziPortiPartenza.get(i),comuniPortiPartenza.get(i),telefoniPortiPartenza.get(i));
            Porto porto_scalo=new Porto(idPortiScalo.get(i),indirizziPortiScalo.get(i),comuniPortiScalo.get(i),telefoniPortiScalo.get(i));
            Porto porto_arrivo=new Porto(idPortiArrivo.get(i),indirizziPortiArrivo.get(i), comuniPortiArrivo.get(i), telefoniPortiArrivo.get(i));

            Compagnia compagnia=new Compagnia(idCompagnie.get(i),telefoniCompagnie.get(i),mailCompagnie.get(i),sitiWebCompagnie.get(i),
                    loginCompagnie.get(i),passwordCompagnie.get(i), nomiCompagnie.get(i));
            Ritardo ritardo=null;
            Annullamento annullamento=null;
            if(motivazioniRitardi.get(i)!=null){
                ritardo=new Ritardo(motivazioniRitardi.get(i),tempiRitardi.get(i));
            }
            if(motivazioniAnnullamenti.get(i)!=null){
                annullamento=new Annullamento(motivazioniAnnullamenti.get(i), rimborsi.get(i),prossimi.get(i));
            }
            Corsa corsa=new Corsa(idCorse.get(i),orariPartenza.get(i),orariPartenzaScalo.get(i),orariArrivoScalo.get(i),orariArrivo.get(i),dateInizioServizio.get(i),dateFineServizio.get(i),boolArray,
                    scontiResidente.get(i),prezziInteri.get(i),prezziRidotti.get(i),sovrVeicoli.get(i),
                    sovrBagagli.get(i),sovrPrenotazioni.get(i),natante,porto_partenza,porto_arrivo,porto_scalo,compagnia,ritardo,annullamento
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
                id_corse.add(c.getIdCorsa());
                Orari_Partenza.add(c.getOrarioPartenza());
                Orari_Arrivo.add(c.getOrarioArrivo());
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
                id_porti_partenza.add(c.getPortoPartenza().getId());
                id_porti_arrivo.add(c.getPortoArrivo().getId());
                Indirizzi_porti_partenza.add(c.getPortoPartenza().getIndirizzo());
                Comuni_porti_partenza.add(c.getPortoPartenza().getComune());
                Indirizzi_porti_arrivo.add(c.getPortoArrivo().getIndirizzo());
                Comuni_porti_arrivo.add(c.getPortoArrivo().getComune());
                Telefoni_porti_partenza.add(c.getPortoPartenza().getTelefono());
                Telefoni_porti_arrivo.add(c.getPortoArrivo().getTelefono());
                id_porti_scalo.add(c.getPortoScalo().getId());
                Indirizzi_porti_scalo.add(c.getPortoScalo().getIndirizzo());
                Comuni_porti_scalo.add(c.getPortoScalo().getComune());
                Telefoni_porti_scalo.add(c.getPortoScalo().getTelefono());
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
            comuniPartenza.add(c.getPortoPartenza().getComune());
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
            comuniArrivo.add(c.getPortoArrivo().getComune());
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


        ArrayList<Integer> idPasseggero=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<Integer> eta=new ArrayList<>();

        utenteDao.loginPasseggero(idPasseggero,nome,login,password,eta,loginIn,passwordIn);

        if(idPasseggero.isEmpty()){
            return null;
        }


        return new Passeggero(idPasseggero.getFirst(),nome.getFirst(),login.getFirst(),password.getFirst(),eta.getFirst());

    }

    /**
     * metodo che permette alla compagnia di eseguire il login.
     * @param loginIn la login inserita dall utente
     * @param passwordIn la password inserita dall utente
     * @return Compagnia se l'operazione va a buon fine, null altrimenti.
     * */
    public Compagnia loginCompagnia(String loginIn,String passwordIn){

        ArrayList<Integer> idCompagnia=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<String> telefono=new ArrayList<>();
        ArrayList<String> mail=new ArrayList<>();
        ArrayList<String> sitoWeb=new ArrayList<>();

        utenteDao.loginCompagnia(idCompagnia,nome,login,password,telefono,mail,sitoWeb,loginIn,passwordIn);

        if(idCompagnia.isEmpty()){
            return null;
        }


        return new Compagnia(idCompagnia.getFirst(), telefono.getFirst(),mail.getFirst(),sitoWeb.getFirst(),login.getFirst(),password.getFirst(),nome.getFirst());

    }

    /**
     * metodo che permette di registarsi alla piattaforma come passeggero.
     * @param nome l username inserito dall utente
     * @param login la login inserita dall utente
     * @param password la password inserita dall utente
     * @param eta l'eta inserita dall utente
     * */

    public void registerPasseggero(String nome, String login, String password, Integer eta)throws SQLException{
        utenteDao.registerPasseggero(nome,login,password,eta);
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
    public void createUpdateCorsa(Integer idCorsa , Time orarioPartenza , Time orarioArrivo ,
                                  Time orarioPartenzaScalo , Time orarioArrivoScalo ,
                                  Date dataInizioServizio , Date dataFineServizio ,
                                  boolean[] giorniServizioAttivo , Float sovrPrenotazione ,
                                  Float sovrBagaglio , Float sovrVeicolo , Float prezzoIntero ,
                                  Float prezzoRidotto , Float scontoResidente , Integer portoPartenza ,
                                  Integer portoArrivo , Integer portoScalo, Integer compagnia , Integer natante )throws SQLException{


        corsaDao.createUpdateCorsa(idCorsa, orarioPartenza, orarioArrivo,orarioPartenzaScalo,orarioArrivoScalo, dataInizioServizio, dataFineServizio, boolArrayToString(giorniServizioAttivo), sovrPrenotazione, sovrBagaglio, sovrVeicolo, prezzoIntero, prezzoRidotto, scontoResidente, portoPartenza, portoArrivo,portoScalo, compagnia, natante);
    }
    /**
     * metodo che riempe il model con i dati dei biglietti interi di un passeggero riempendo gli array e deserializzandoli.
     * @param idPasseggero l' id del passeggero di cui prendere i biglietti
     * */
    public void updateBigliettiInteri(Integer idPasseggero,ArrayList<Float> importoTotale,ArrayList<Float> sovrapprezzoTot,ArrayList<Integer> nBagagli,
                                          ArrayList<String> veicolo,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa){

        bigliettoDao.retrieveBigliettiInteri(idPasseggero,importoTotale,sovrapprezzoTot,nBagagli,veicolo,prenotazione,corsa);

        bigliettiUtente.clear();
        for(int i=0;i<importoTotale.size();i++){
            BigliettoIntero b=new BigliettoIntero(importoTotale.get(i),sovrapprezzoTot.get(i),nBagagli.get(i),veicolo.get(i),prenotazione.get(i),getCorsa(corsa.get(i)));
            bigliettiUtente.add(b);
        }


    }
    /**
     * metodo che decide sulla base del paramtero update se aggiornare
     * i dati nel model richiamando updateBigliettiInteri o se prendere i dati nel model.
     * Serializza i dati e li manda alla gui.
     * @param idPasseggero il passeggero di cui prendere i biglietti interi
     * @param update attributo booleano che stabilisce se richiamare updateBigliettiInteri oppure no
     * */
    public void retrieveBigliettiInteri(Integer idPasseggero, boolean update, ArrayList<Float> importoTotale, ArrayList<Float> sovrapprezzoTot, ArrayList<Integer> nBagagli,
                                        ArrayList<String> veicolo, ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa){
        if(update){
            updateBigliettiInteri(idPasseggero, importoTotale, sovrapprezzoTot, nBagagli, veicolo, prenotazione, corsa);
        }else{
            for(Biglietto b:bigliettiUtente){
                importoTotale.add(b.getImporto());
                sovrapprezzoTot.add(b.getSovr());
                nBagagli.add(b.getNBagagli());
                prenotazione.add(b.getPrenotazione());
                corsa.add(b.getCorsa().getIdCorsa());
                veicolo.add(((BigliettoIntero)b).getVeicolo());
            }
        }
    }
    /**
     * metodo che riempe il model con i dati dei biglietti ridotti di un passeggero riempendo gli array e deserializzandoli.
     * @param idPasseggero l' id del passeggero di cui prendere i biglietti
     * */
    public void updateBigliettiRidotti(Integer idPasseggero,ArrayList<Float> importoTotale,ArrayList<Float> sovrapprezzoTot,ArrayList<Integer> nBagagli
                                           ,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore){


        bigliettoDao.retrieveBigliettiRidotti(idPasseggero,importoTotale,sovrapprezzoTot,nBagagli,prenotazione,corsa,accompagnatore);
        bigliettiUtente.clear();
        for(int i=0;i<importoTotale.size();i++){
            BigliettoRidotto b=new BigliettoRidotto(importoTotale.get(i),sovrapprezzoTot.get(i),nBagagli.get(i),prenotazione.get(i),getCorsa(corsa.get(i)),getAccompagnatore(accompagnatore.get(i)));
            bigliettiUtente.add(b);
        }
    }

    /**
     * metodo che decide sulla base del parametro update se aggiornare
     * i dati nel model richiamando updateBigliettiRidotti o se prendere i dati nel model.
     * Serializza i dati e li manda alla gui.
     * @param idPasseggero il passeggero di cui prendere i biglietti interi
     * @param update attributo booleano che stabilisce se richiamare updateBigliettiInteri oppure no
     * */

    public void retrieveBigliettiRidotti(Integer idPasseggero, boolean update, ArrayList<Float> importoTotale, ArrayList<Float> sovrapprezzoTot, ArrayList<Integer> nBagagli
            , ArrayList<Timestamp> prenotazione, ArrayList<Integer> corsa, ArrayList<Integer> accompagnatore){
        if(update){
            updateBigliettiRidotti(idPasseggero, importoTotale, sovrapprezzoTot, nBagagli, prenotazione, corsa, accompagnatore);
        }else{
            for (Biglietto b:bigliettiUtente){
                importoTotale.add(b.getImporto());
                sovrapprezzoTot.add(b.getSovr());
                nBagagli.add(b.getNBagagli());
                prenotazione.add(b.getPrenotazione());
                corsa.add(b.getCorsa().getIdCorsa());
                accompagnatore.add(((BigliettoRidotto)b).getAccompagnatore().getId());
            }
        }

    }

    /**
     * metodo che permette alla compagnia di aggiungere un ritardo alla corsa
     * @param motivazione motivazione del ritardo
     * @param ritardo tempo del ritardo
     * @param idCorsa l' id della corsa a cui aggiungere il ritardo
     * */
    public void addRitardo(String motivazione, Time ritardo, Integer idCorsa){
        ritardoDao.addRitardo(motivazione, ritardo, idCorsa);
    }

    /**
     * metodo che permette alla compagnia di aggiungere un annullamento alla corsa
     * @param motivazione motivazione dell annullamento
     * @param rimborso eventuale ammontare del rimborso
     * @param idCorsa l' id della corsa a cui aggiungere l'annullamento
     * @param prossimo l'id di eventuale corsa sostitutiva
     * */
    public void addAnnullamento(String motivazione, Float rimborso, Integer idCorsa, Integer prossimo){
        annullamentoDao.addAnnullamento(motivazione,rimborso,idCorsa,prossimo);
    }

    /**
     * metodo che richiama l implementazioneDao e costruisce
     * oggetti del model riempendo gli arraylist e
     * deserializzandoli
     *
     * @param compagnia la compagnia da cui prendere le corse per aggiornare il model
     * */
    public void updateCorseCompagnia(Compagnia compagnia,ArrayList<Integer> idCorsa,
                                         ArrayList<Time> orarioPartenza,ArrayList<Time> orarioArrivo,
                                         ArrayList<Time> orarioPartenzaScalo,ArrayList<Time> orarioArrivoScalo,
                                         ArrayList<Date> dataInizioServizio, ArrayList<Date> dataFineServizio,
                                         ArrayList<String> giorniServizioAttivo, ArrayList<Float> sovrPrenotazione, ArrayList<Float> sovrBagaglio,
                                         ArrayList<Float> sovrVeicolo, ArrayList<Float> prezzoIntero, ArrayList<Float> prezzoRidotto,
                                         ArrayList<Float> scontoResidente, ArrayList<Integer> portoPartenza, ArrayList<Integer> portoArrivo,
                                         ArrayList<Integer> portoScalo, ArrayList<Integer> natante, ArrayList<String> p1Indirizzo,
                                         ArrayList<String> p1Comune, ArrayList<String> p1TelInfo, ArrayList<String> p2Indirizzo,
                                         ArrayList<String> p2Comune, ArrayList<String> p2TelInfo, ArrayList<String> psIndirizzo,
                                         ArrayList<String> psComune, ArrayList<String> psTelInfo, ArrayList<String> nomeNatante,
                                         ArrayList<String> trasporta, ArrayList<String> tipoNatante
    ){


        corsaDao.retrieveCorseCompagnia(compagnia.getId(),idCorsa,orarioPartenza,orarioArrivo,
                orarioPartenzaScalo, orarioArrivoScalo,dataInizioServizio,
                dataFineServizio,giorniServizioAttivo,sovrPrenotazione,sovrBagaglio,sovrVeicolo,
                prezzoIntero,prezzoRidotto,scontoResidente,portoPartenza,portoArrivo,portoScalo,natante,
                p1Indirizzo,p1Comune,p1TelInfo,p2Indirizzo,p2Comune,p2TelInfo,psIndirizzo,
                psComune,psTelInfo,nomeNatante,trasporta,tipoNatante);

        for(int i=0;i<idCorsa.size();i++){

            boolean[] arrayGiorniServizioAttivo =stringToBoolArray(giorniServizioAttivo.get(i));


            Porto porto_part=new Porto(portoPartenza.get(i),p1Indirizzo.get(i),p1Comune.get(i),p1TelInfo.get(i));
            Porto porto_scal=new Porto(portoScalo.get(i),psIndirizzo.get(i),psComune.get(i),psTelInfo.get(i));
            Porto porto_arri=new Porto(portoArrivo.get(i),p2Indirizzo.get(i),p2Comune.get(i),p2TelInfo.get(i));

            Natante nat=new Natante(natante.get(i),nomeNatante.get(i),trasporta.get(i),tipoNatante.get(i));

            Corsa corsa=new Corsa(idCorsa.get(i),orarioPartenza.get(i),orarioArrivo.get(i),orarioPartenzaScalo.get(i),orarioArrivoScalo.get(i),dataInizioServizio.get(i),dataFineServizio.get(i),arrayGiorniServizioAttivo,
                    scontoResidente.get(i),prezzoIntero.get(i),prezzoRidotto.get(i),sovrVeicolo.get(i),sovrBagaglio.get(i),sovrPrenotazione.get(i),
                    nat,porto_part,porto_arri,porto_scal,compagnia,null,null);

            corseCompagnia.add(corsa);
        }




    }

    /**
     * metodo che decide sulla base del parametro update se aggiornare
     * i dati nel model richiamando updateCorseCompagnia o se prendere i dati nel model.
     * Serializza i dati e li manda alla gui.
     *
     * @param compagnia la compagnia da cui prendere le corse
     * @param update attributo booleano che stabilisce se richiamare updateCorseCompagnia oppure no
     * */
    public void retrieveCorseCompagnia(Compagnia compagnia, boolean update, ArrayList<Integer> id_corsa,
                                       ArrayList<Time> orario_partenza, ArrayList<Time> orario_arrivo,
                                       ArrayList<Time> orario_partenza_scalo, ArrayList<Time> orario_arrivo_scalo,
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
                id_corsa.add(c.getIdCorsa());
                orario_partenza.add(c.getOrarioPartenza());
                orario_arrivo.add(c.getOrarioArrivo());
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
                porto_partenza.add(c.getPortoPartenza().getId());
                porto_arrivo.add(c.getPortoArrivo().getId());
                p1_indirizzo.add(c.getPortoPartenza().getIndirizzo());
                p1_comune.add(c.getPortoPartenza().getComune());
                p2_indirizzo.add(c.getPortoArrivo().getIndirizzo());
                p2_comune.add(c.getPortoArrivo().getComune());
                p1_tel_info.add(c.getPortoPartenza().getTelefono());
                p2_tel_info.add(c.getPortoArrivo().getTelefono());
                porto_scalo.add(c.getPortoScalo().getId());
                ps_indirizzo.add(c.getPortoScalo().getIndirizzo());
                ps_comune.add(c.getPortoScalo().getComune());
                ps_tel_info.add(c.getPortoScalo().getTelefono());

            }
        }

    }


    /**
     * metodo che riempe il model con i dati dei passeggeri di maggiore eta(16 anni o piu) riempendo gli array e deserializzandoli.
     * */
    public void updateAccompagnatori(ArrayList<Integer> idPasseggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){

        utenteDao.retrieveAccompagnatori(idPasseggero,nome,login,password,eta);

        for (int i = 0; i < idPasseggero.size(); i++) {
            Passeggero p=new Passeggero(idPasseggero.get(i), nome.get(i),login.get(i),password.get(i),eta.get(i));
            accompagnatori.add(p);
        }

    }

    /**
     * metodo che decide sulla base del parametro update se aggiornare
     * i dati nel model richiamando updateAccompagnatori o se prendere i dati nel model.
     * Serializza i dati e li manda alla gui.
     *
     * @param update attributo booleano che stabilisce se richiamare updateAccompagnatori oppure no
     * */

    public void retrieve_accompagnatori(boolean update,ArrayList<Integer> idPasseggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        if (update){
            updateAccompagnatori(idPasseggero, nome, login, password, eta);
        }else{
            for(Passeggero p:accompagnatori){
                idPasseggero.add(p.getId());
                nome.add(p.getNome());
                login.add(p.getLogin());
                password.add(p.getPassword());
                eta.add(p.getEta());
            }
        }
    }

    /**
     * metodo che riempe il model con i dati dei porti riempendo e deserializzando gli ArrayList.
     * Restituisce poi i dati serializzati a retrieve porti che poi li restituisce alla gui.
     */


    private void updatePorti(ArrayList<Integer> idPorto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> telInfo){

        portoDao.retrievePorti(idPorto,indirizzo,comune,telInfo);

        for (int i = 0; i < idPorto.size(); i++) {
            Porto p = new Porto(idPorto.get(i), indirizzo.get(i), comune.get(i), telInfo.get(i));
            porti.add(p);
        }


    }
    /**
     * overloading di updatePorti che riempe solo il model senza restituire
     * i dati serializzati.
     * */
    public void updatePorti(){
        ArrayList<String> comuniPorti=new ArrayList<>();
        ArrayList<Integer> idPorti=new ArrayList<>();
        ArrayList<String> telPorti=new ArrayList<>();
        ArrayList<String> indirizzoPorti=new ArrayList<>();

        updatePorti(idPorti,indirizzoPorti,comuniPorti,telPorti);

    }

    /**
     * metodo che decide sulla base del parametro update se chiamare updatePorti oppure no.
     * Restituisce poi i dati serializzati alla gui.
     *
     * @param update parametro booleano che stabilisce se chiamare updatePorti oppure prendere i dati dal model.
     * */
    public void retrieve_porti(boolean update,ArrayList<Integer> idPorto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> telInfo){
        if(update){
            updatePorti(idPorto, indirizzo, comune, telInfo);
        }else{
            for(Porto p:porti){
                idPorto.add(p.getId());
                indirizzo.add(p.getIndirizzo());
                comune.add(p.getComune());
                telInfo.add(p.getTelefono());
            }
        }
    }

    /**
     * metodo che riempe il model con i dati dei natanti riempendo e deserializzando gli ArrayList.
     * Restituisce poi i dati serializzati a retrieve natanti che poi li restituisce alla gui.
     */

    public void updateNatanti(ArrayList<Integer> idNatante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo){

        natanteDao.retrieveNatanti(idNatante,nome,trasporta,tipo);

        for (int i = 0; i < idNatante.size(); i++) {
            Natante nat=new Natante(idNatante.get(i),nome.get(i),trasporta.get(i),tipo.get(i));
            natanti.add(nat);
        }

    }

    /**
     * overloading di updateNatanti che riempe solo il model senza restituire
     * i dati serializzati.
     * */

    public void updateNatanti() {
        ArrayList<Integer> idNatante=new ArrayList<>();
        ArrayList<String> nomeNatante=new ArrayList<>();
        ArrayList<String> trasporta=new ArrayList<>();
        ArrayList<String> tipo=new ArrayList<>();

        updateNatanti(idNatante,nomeNatante,trasporta,tipo);
    }

    /**
     * metodo che decide sulla base del parametro update se chiamare updateNatanti oppure no.
     * Restituisce poi i dati serializzati alla gui.
     *
     * @param update parametro booleano che stabilisce se chiamare updateNatanti oppure prendere i dati dal model.
     * */

    public void retrieve_natanti(boolean update,ArrayList<Integer> idNatante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo){
        if(update){
            updateNatanti(idNatante, nome, trasporta, tipo);
        }else{
            for(Natante n:natanti){
                idNatante.add(n.getId());
                nome.add(n.getNome());
                trasporta.add(n.getTrasporta());
                tipo.add(n.getTipo());
            }
        }
    }

    /**
     * metodo che permette alla compagnia di eliminare una corsa
     * */
    public void delete_corsa(Integer idCorsa){
        corsaDao.deleteCorsa(idCorsa);
    }

    /**
     * metodo che permette al passeggero di acquistare un biglietto intero (aggiungere un biglietto sul db).
     *
     * @param importoTotale importoTotale del biglietto
     * @param sovrapprezzoTot sovrapprezzoTot del biglietto
     * @param nBagagli numero di bagagli del passeggero
     * @param veicolo veicolo portato dal passeggero
     * @param prenotazione prenotazione del biglietto
     * @param corsa corsa del biglietto
     * @param passeggero passeggero del biglietto
     * */

    public void add_biglietto_intero(float importoTotale,float sovrapprezzoTot,int nBagagli,String veicolo,Timestamp prenotazione,int corsa,int passeggero){
        bigliettoDao.addBigliettoIntero(importoTotale, sovrapprezzoTot, nBagagli, veicolo, prenotazione, corsa, passeggero);
    }

    /**
     * metodo che permette al passeggero di acquistare un biglietto intero (aggiungere un biglietto sul db).
     *
     * @param importoTotale importoTotale del biglietto
     * @param sovrapprezzoTot sovrapprezzoTot del biglietto
     * @param nBagagli numero di bagagli del passeggero
     * @param prenotazione prenotazione del biglietto
     * @param corsa corsa del biglietto
     * @param passeggero passeggero del biglietto
     * @param accompagnatore accompagnatore del passeggero
     * */
    public void add_biglietto_ridotto(float importoTotale,float sovrapprezzoTot,int nBagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore){
        bigliettoDao.addBigliettoRidotto(importoTotale, sovrapprezzoTot, nBagagli, prenotazione, corsa, passeggero, accompagnatore);
    }

    /**
     *metodo che permette al passeggero di modificare la sua password
     *
     * @param login la login del passeggero
     * @param newPassword la nuova password del passeggero
     * @param oldPassword la vecchia password del passeggero
     * */

    public void change_password(String login,String newPassword,String oldPassword)throws SQLException{
        utenteDao.changePassword(login, newPassword, oldPassword);
    }

    /**
     *metodo che permette al passeggero di modificare la sua password
     *
     * @param oldLogin la vecchia login del passeggero
     * @param newLogin la nuova login del passeggero
     * @param password la password del passeggero
     * */

    public void change_login(String oldLogin,String newLogin,String password) throws SQLException{
        utenteDao.changeLogin(oldLogin, newLogin, password);
    }

    /**
     * metodo che riempe il model con i dati dei social della compagnia loggata riempendo e deserializzando
     * gli ArrayList.
     *
     * @param idCompagnia l'id della compagnia di cui prendere i social
     * */

    public void updateSocial(int idCompagnia,ArrayList<String> nomeSocial,ArrayList<String> indirizzoSocial){
        socialDao.retrieveSocial(idCompagnia,nomeSocial,indirizzoSocial);

        for (int i = 0; i < nomeSocial.size(); i++) {
            socialsCompagnia.add(new Social(nomeSocial.get(i),indirizzoSocial.get(i)));
        }
    }

    /**
     * metodo che stabilisce sulla base del parametro update se aggiornare i dati
     * del model richiamando updateSocial oppure prendere i dati dal model.
     * Restituisce poi i dati serializzati.
     *
     * @param update parametro booleano che stabilisce se richiamare updateSocial oppure no
     * @param idCompagnia l'id della compagnia di cui prendere i social
     * */

    public void retrieveSocial(boolean update, int idCompagnia, ArrayList<String> nomeSocial, ArrayList<String> indirizzoSocial){
        if (update){
            updateSocial(idCompagnia, nomeSocial, indirizzoSocial);
        }else{
            for (Social s:socialsCompagnia){
                nomeSocial.add(s.getNome());
                indirizzoSocial.add(s.getIndirizzo());
            }
        }
    }



    /**
     * metodo che restituisce il nome dell compagnia passata in input.
     * @param compagnia compagnia di cui avere il nome
     * @return stringa contenente il nome della compagnia
     * */
    public String getNome(Utente compagnia) {
        return compagnia.getNome();
    }

    /**
     * metodo che restituisce l id dell utente loggato
     * */
    public int getIdUtente() {
        return utente.getId();
    }

    /**
     * metodo che restituisce l eta del passeggero loggato
     * */
    public int getEtaPasseggero(){
        return ((Passeggero)utente).getEta();
    }

    /**
     * metodo che setta le tratte disponibili
     * @param tratte ArrayList di tratte da riempire
     * */

    public void setTratte(ArrayList<String> tratte) {
        this.tratte=tratte;
    }

    /**
     * metodo che restituisce le tratte disponibili
     * @return ArrayList di stringhe delle tratte disponibili
     * */

    public ArrayList<String> getTratte(){
        return this.tratte;
    }

    /**
     * metodo che setta l' utente loggato
     * @param utente l'utente da settare
     * */

    public void setUtenteLoggato(Utente utente) {
        this.utente=utente;
    }

    /**
     * metodo che restituisce l utente loggato
     * @return restituisce l' utente loggato
     * */
    public Utente getUtente() {
        return this.utente;
    }

    /**
     * metodo per settare le corse totali
     * */
    public void setCorse(){
        this.corse= (ArrayList<Corsa>) this.corseFiltrate.clone();
    }

    /**
     * metodo privato per ottenere una corsa del database dal suo id
     * @param idCorsa l'id della corsa da recuperare
     * @return la corsa con il relativo id oppure null
     * */
    private Corsa getCorsa(int idCorsa){
        for (Corsa c:corse){
            if(c.getIdCorsa()==idCorsa){
                return c;
            }
        }
        return null;
    }

    /**
     * metodo privato per ottenere un accompagnatore del database dal suo id
     * @param idPasseggero l'id del passeggero da recuperare
     * @return l'accompagnatore con il relativo id oppure null
     * */
    private Passeggero getAccompagnatore(int idPasseggero){
        for(Passeggero p:accompagnatori){
            if(p.getId()==idPasseggero){
                return p;
            }
        }
        return null;
    }


}
