package controller;

import MODEL.*;
import dao.CorsaDAO;
import dao.DaoManager;
import database.ConnessioneDatabase;
import impl_dao_postgres.impl_CorsaDAO;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class Controller {
    private CorsaDAO CorsaDao;
    private static Controller controller;

    ArrayList<Corsa> corseFiltrate=new ArrayList<>();
    ArrayList<Passeggero> accompagnatori=new ArrayList<>();

    ArrayList<Corsa> corseCompagnia=new ArrayList<>();

    ArrayList<Porto> porti=new ArrayList<>();

    ArrayList<Natante> natanti=new ArrayList<>();

    ArrayList<Social> socialsCompagnia=new ArrayList<>();

    ArrayList<String> tratte=new ArrayList<>();
    private Utente utente;
    public Controller(){
        CorsaDao=new impl_CorsaDAO();


    }

    public static Controller getController(){
        if(controller==null){
            controller=new Controller();
        }
            return controller;
    }

    public void filtra_corse(Integer id_corsa,String portoPartenza,String portoArrivo ,Date dataPartenza,Time orarioPartenza,Float prezzo ,String tipo_natante,ArrayList<Integer> id_corse,
                             ArrayList<Time> Orari_Partenza,ArrayList<Time> Orari_Arrivo,ArrayList<Date> Date_Inizio_Servizio,
                             ArrayList<Date> Date_Fine_Servizio,ArrayList<String> Giorni_Servizio_Attivo, ArrayList<Float> Sconti_residente, ArrayList<Float> Prezzi_interi, ArrayList<Float> Prezzi_ridotti,
                             ArrayList<Float> Sovr_Veicoli, ArrayList<Float> Sovr_Bagagli, ArrayList<Float> Sovr_Prenotazioni, ArrayList<Integer> id_natanti, ArrayList<String> Nomi_natanti,
                             ArrayList<String> Trasporti, ArrayList<String> Tipi_natanti, ArrayList<Integer> id_porti_partenza, ArrayList<String> Indirizzi_porti_partenza,
                             ArrayList<String> Comuni_porti_partenza, ArrayList<String> Telefoni_porti_partenza, ArrayList<Integer> id_porti_arrivo, ArrayList<String> Indirizzi_porti_arrivo,
                             ArrayList<String> Comuni_porti_arrivo, ArrayList<String> Telefoni_porti_arrivo, ArrayList<Integer> id_porti_scalo, ArrayList<String> Indirizzi_porti_scalo,
                             ArrayList<String> Comuni_porti_scalo, ArrayList<String> Telefoni_porti_scalo, ArrayList<Integer> id_compagnie, ArrayList<String> Telefoni_compagnie,
                             ArrayList<String> Mail_compagnie, ArrayList<String> Siti_web_compagnie, ArrayList<String> login_compagnie, ArrayList<String> password_compagnie,
                             ArrayList<String> nomi_compagnie, ArrayList<String> Motivazioni_ritardi, ArrayList<Time> Tempi_ritardi, ArrayList<String> Motivazioni_annullamenti,
                             ArrayList<Float> Rimborsi, ArrayList<Integer> Prossimi){

        /* codice nella gui da qualche parte
        ArrayList<Corsa> corse=new ArrayList<>();

        ArrayList<Integer> id_corse=new ArrayList<>();
        ArrayList<Time> Orari_Partenza=new ArrayList<>();
        ArrayList<Time> Orari_Arrivo=new ArrayList<>();
        ArrayList<Date> Date_Inizio_Servizio=new ArrayList<>();
        ArrayList<Date> Date_Fine_Servizio=new ArrayList<>();
        ArrayList<String> Giorni_Servizio_Attivo=new ArrayList<>();
        ArrayList<Float> Sconti_residente = new ArrayList<>();
        ArrayList<Float> Prezzi_interi= new ArrayList<>();
        ArrayList<Float> Prezzi_ridotti = new ArrayList<>();
        ArrayList<Float> Sovr_Veicoli=new ArrayList<>();
        ArrayList<Float> Sovr_Bagagli=new ArrayList<>();
        ArrayList<Float> Sovr_Prenotazioni=new ArrayList<>();

        ArrayList<Integer> id_natanti=new ArrayList<>();
        ArrayList<String> Nomi_natanti = new ArrayList<>();
        ArrayList<String> Trasporti = new ArrayList<>();
        ArrayList<String> Tipi_natanti=new ArrayList<>();

        ArrayList<Integer> id_porti_partenza=new ArrayList<>();
        ArrayList<String> Indirizzi_porti_partenza=new ArrayList<>();
        ArrayList<String> Comuni_porti_partenza=new ArrayList<>();
        ArrayList<String> Telefoni_porti_partenza=new ArrayList<>();

        ArrayList<Integer> id_porti_arrivo=new ArrayList<>();
        ArrayList<String> Indirizzi_porti_arrivo=new ArrayList<>();
        ArrayList<String> Comuni_porti_arrivo=new ArrayList<>();
        ArrayList<String> Telefoni_porti_arrivo=new ArrayList<>();

        ArrayList<Integer> id_porti_scalo=new ArrayList<>();
        ArrayList<String> Indirizzi_porti_scalo=new ArrayList<>();
        ArrayList<String> Comuni_porti_scalo=new ArrayList<>();
        ArrayList<String> Telefoni_porti_scalo=new ArrayList<>();

        ArrayList<Integer> id_compagnie=new ArrayList<>();
        ArrayList<String> Telefoni_compagnie=new ArrayList<>();
        ArrayList<String> Mail_compagnie=new ArrayList<>();
        ArrayList<String> Siti_web_compagnie=new ArrayList<>();
        ArrayList<String> login_compagnie=new ArrayList<>();
        ArrayList<String> password_compagnie=new ArrayList<>();
        ArrayList<String> nomi_compagnie=new ArrayList<>();
        ArrayList<String> Motivazioni_ritardi=new ArrayList<>();
        ArrayList<Time> Tempi_ritardi=new ArrayList<>();
        ArrayList<String> Motivazioni_annullamenti=new ArrayList<>();
        ArrayList<Float> Rimborsi = new ArrayList<>();
        ArrayList<Integer> Prossimi=new ArrayList<>();

        CorsaDao.filtra_corse(id_corsa,id_corse,portoPartenza,portoArrivo,dataPartenza,orarioPartenza,prezzo,tipo_natante,Orari_Partenza,Orari_Arrivo,Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,
                Sconti_residente,Prezzi_interi ,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,
                id_natanti,Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,
                id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,
                id_porti_arrivo,Indirizzi_porti_arrivo,Comuni_porti_arrivo,Telefoni_porti_arrivo,id_compagnie,Telefoni_compagnie,Mail_compagnie,
                Siti_web_compagnie,login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,
                Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);

        for(int i=0;i<Orari_Partenza.size();i++){
            boolean[] H =new boolean[7];
            char[] c=Giorni_Servizio_Attivo.get(i).toCharArray();
            for(int k=0;k<7;k++){
                H[k]=(c[k]=='1');
            }

            Natante natante=new Natante(id_natanti.get(i),Nomi_natanti.get(i),Trasporti.get(i),Tipi_natanti.get(i));

            Porto porto_partenza=new Porto(id_porti_partenza.get(i),Indirizzi_porti_partenza.get(i),Comuni_porti_partenza.get(i),Telefoni_porti_partenza.get(i));
            Porto porto_scalo=new Porto(id_porti_scalo.get(i),Indirizzi_porti_scalo.get(i),Comuni_porti_scalo.get(i),Telefoni_porti_scalo.get(i));
            Porto porto_arrivo=new Porto(id_porti_arrivo.get(i),Indirizzi_porti_arrivo.get(i), Comuni_porti_arrivo.get(i), Telefoni_porti_arrivo.get(i));

            Compagnia compagnia=new Compagnia(id_compagnie.get(i),Telefoni_compagnie.get(i),Mail_compagnie.get(i),Siti_web_compagnie.get(i),null,
                    login_compagnie.get(i),password_compagnie.get(i), nomi_compagnie.get(i));
            Ritardo ritardo=null;
            Annullamento annullamento=null;
            if(Motivazioni_ritardi.get(i)!=null){
                ritardo=new Ritardo(Motivazioni_ritardi.get(i),Tempi_ritardi.get(i));
            }
            if(Motivazioni_annullamenti.get(i)!=null){
                annullamento=new Annullamento(Motivazioni_annullamenti.get(i), Rimborsi.get(i),Prossimi.get(i));
            }
            Corsa corsa=new Corsa(id_corse.get(i),Orari_Partenza.get(i),Orari_Arrivo.get(i),Date_Inizio_Servizio.get(i),Date_Fine_Servizio.get(i),H,
                    Sconti_residente.get(i),Prezzi_interi.get(i),Prezzi_ridotti.get(i),Sovr_Veicoli.get(i),
                    Sovr_Bagagli.get(i),Sovr_Prenotazioni.get(i),natante,porto_partenza,porto_arrivo,porto_scalo,compagnia,ritardo,annullamento
                    );
            corse.add(corsa);
        }

        return corse;

         */
        CorsaDao.filtra_corse(id_corsa,id_corse,portoPartenza,portoArrivo,dataPartenza,orarioPartenza,prezzo,tipo_natante,Orari_Partenza,Orari_Arrivo,Date_Inizio_Servizio,Date_Fine_Servizio,Giorni_Servizio_Attivo,
                Sconti_residente,Prezzi_interi ,Prezzi_ridotti,Sovr_Veicoli,Sovr_Bagagli,Sovr_Prenotazioni,
                id_natanti,Nomi_natanti,Trasporti,Tipi_natanti,id_porti_partenza,Indirizzi_porti_partenza,Comuni_porti_partenza,Telefoni_porti_partenza,
                id_porti_scalo,Indirizzi_porti_scalo,Comuni_porti_scalo,Telefoni_porti_scalo,
                id_porti_arrivo,Indirizzi_porti_arrivo,Comuni_porti_arrivo,Telefoni_porti_arrivo,id_compagnie,Telefoni_compagnie,Mail_compagnie,
                Siti_web_compagnie,login_compagnie,password_compagnie,nomi_compagnie,Motivazioni_ritardi,
                Tempi_ritardi,Motivazioni_annullamenti,Rimborsi,Prossimi);

        //riempio i dati transienti in memoria

        for(int i=0;i<Orari_Partenza.size();i++){
            boolean[] H =new boolean[7];
            char[] c=Giorni_Servizio_Attivo.get(i).toCharArray();
            for(int k=0;k<7;k++){
                H[k]=(c[k]=='1');
            }



            Natante natante=new Natante(id_natanti.get(i),Nomi_natanti.get(i),Trasporti.get(i),Tipi_natanti.get(i));

            Porto porto_partenza=new Porto(id_porti_partenza.get(i),Indirizzi_porti_partenza.get(i),Comuni_porti_partenza.get(i),Telefoni_porti_partenza.get(i));
            Porto porto_scalo=new Porto(id_porti_scalo.get(i),Indirizzi_porti_scalo.get(i),Comuni_porti_scalo.get(i),Telefoni_porti_scalo.get(i));
            Porto porto_arrivo=new Porto(id_porti_arrivo.get(i),Indirizzi_porti_arrivo.get(i), Comuni_porti_arrivo.get(i), Telefoni_porti_arrivo.get(i));

            Compagnia compagnia=new Compagnia(id_compagnie.get(i),Telefoni_compagnie.get(i),Mail_compagnie.get(i),Siti_web_compagnie.get(i),null,
                    login_compagnie.get(i),password_compagnie.get(i), nomi_compagnie.get(i));
            Ritardo ritardo=null;
            Annullamento annullamento=null;
            if(Motivazioni_ritardi.get(i)!=null){
                ritardo=new Ritardo(Motivazioni_ritardi.get(i),Tempi_ritardi.get(i));
            }
            if(Motivazioni_annullamenti.get(i)!=null){
                annullamento=new Annullamento(Motivazioni_annullamenti.get(i), Rimborsi.get(i),Prossimi.get(i));
            }
            Corsa corsa=new Corsa(id_corse.get(i),Orari_Partenza.get(i),Orari_Arrivo.get(i),Date_Inizio_Servizio.get(i),Date_Fine_Servizio.get(i),H,
                    Sconti_residente.get(i),Prezzi_interi.get(i),Prezzi_ridotti.get(i),Sovr_Veicoli.get(i),
                    Sovr_Bagagli.get(i),Sovr_Prenotazioni.get(i),natante,porto_partenza,porto_arrivo,porto_scalo,compagnia,ritardo,annullamento
            );
            corseFiltrate.add(corsa);

            System.out.println("++++"+Comuni_porti_arrivo.get(i));
        }
    }

    public ArrayList<String> getComuniPortiPartenzaCorseFiltrate(){
        ArrayList<String> comuni_partenza=new ArrayList<>();
        for (Corsa c: corseFiltrate){
            comuni_partenza.add(c.getPorto_Partenza().getComune());
        }

        return comuni_partenza;
    }

    public ArrayList<String> getComuniPortiArrivoCorseFiltrate(){
        ArrayList<String> comuni_arrivo=new ArrayList<>();
        for (Corsa c: corseFiltrate){
            comuni_arrivo.add(c.getPorto_Arrivo().getComune());
        }

        return comuni_arrivo;
    }

    public Passeggero loginUtente(String login_in,String password_in){


        ArrayList<Integer> id_passeggero=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<Integer> eta=new ArrayList<>();

        CorsaDao.login_passeggero(id_passeggero,nome,login,password,eta,login_in,password_in);

        if(id_passeggero.size()<=0){
            return null;
        }

        Passeggero p=new Passeggero(id_passeggero.get(0),nome.get(0),login.get(0),password.get(0),eta.get(0));

        return p;

    }

    public Compagnia loginCompagnia(String login_in,String password_in){

        ArrayList<Integer> id_compagnia=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<String> telefono=new ArrayList<>();
        ArrayList<String> mail=new ArrayList<>();
        ArrayList<String> sito_web=new ArrayList<>();

        CorsaDao.login_compagnia(id_compagnia,nome,login,password,telefono,mail,sito_web,login_in,password_in);

        if(id_compagnia.size()<=0){
            return null;
        }

        Compagnia c=new Compagnia(id_compagnia.get(0), telefono.get(0),mail.get(0),sito_web.get(0), null,login.get(0),password.get(0),nome.get(0));

        return c;
    }

    public void register_passeggero(String nome,String login,String password){
        CorsaDao.register_passeggero(nome,login,password);
    }

    public void create_update_corsa(Integer Id_corsa ,Time Orario_partenza , Time Orario_arrivo ,
                                    Date Data_inizio_servizio , Date Data_fine_servizio ,
                                    boolean[] Giorni_Servizio_Attivo , Float Sovr_prenotazione ,
                                    Float Sovr_bagaglio , Float Sovr_veicolo , Float Prezzo_intero ,
                                    Float Prezzo_ridotto , Float Sconto_residente , Integer Porto_partenza ,
                                    Integer Porto_arrivo , Integer Compagnia , Integer Natante ){
        BitSet bits = new BitSet(Giorni_Servizio_Attivo.length);
        for (int i = 0; i < Giorni_Servizio_Attivo.length; i++) {
            if (Giorni_Servizio_Attivo[i]) {
                bits.set(i);
            }
        }

        byte[] bytes = bits.toByteArray();

        CorsaDao.create_update_corsa(Id_corsa, Orario_partenza, Orario_arrivo, Data_inizio_servizio, Data_fine_servizio, bytes, Sovr_prenotazione, Sovr_bagaglio, Sovr_veicolo, Prezzo_intero, Prezzo_ridotto, Sconto_residente, Porto_partenza, Porto_arrivo, Compagnia, Natante);
    }
    public void retrieve_passeggero(Integer id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        /*
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<Integer> eta=new ArrayList<>();

        CorsaDao.retrieve_passeggero(id_passeggero,nome,login,password,eta);

        if(nome.size()<=0){
            return null;
        }
        Passeggero p=new Passeggero(id_passeggero,nome.get(0),login.get(0),password.get(0),eta.get(0));

        return  p;

         */

        CorsaDao.retrieve_passeggero(id_passeggero,nome,login,password,eta);

    }
    public void retrieve_biglietti_interi(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli,
                                          ArrayList<String> veicolo,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa){

        /* questo codice verra messo nella gui da qualche parte

        ArrayList<Float> importo_totale=new ArrayList<>();
        ArrayList<Float> Sovrapprezzo_tot=new ArrayList<>();
        ArrayList<Integer> n_bagagli=new ArrayList<>();
        ArrayList<String> veicolo=new ArrayList<>();
        ArrayList<Timestamp> prenotazione=new ArrayList<>();
        ArrayList<Integer> corsa=new ArrayList<>();

        ArrayList<Biglietto_Intero> biglietti=new ArrayList<>();

        CorsaDao.retrieve_biglietti_interi(id_passeggero,importo_totale,Sovrapprezzo_tot,n_bagagli,veicolo,prenotazione,corsa);

        for(int i=0;i<importo_totale.size();i++){
            Corsa c=filtra_corse(corsa.get(i),null,null,null,null,null,null).get(0);
            Biglietto_Intero b=new Biglietto_Intero(importo_totale.get(i),Sovrapprezzo_tot.get(i),n_bagagli.get(i),veicolo.get(i),prenotazione.get(i),c);
            biglietti.add(b);
        }
        return biglietti;
         */

        CorsaDao.retrieve_biglietti_interi(id_passeggero,importo_totale,Sovrapprezzo_tot,n_bagagli,veicolo,prenotazione,corsa);



    }

    public void retrieve_biglietti_ridotti(Integer id_passeggero,ArrayList<Float> importo_totale,ArrayList<Float> Sovrapprezzo_tot,ArrayList<Integer> n_bagagli
                                           ,ArrayList<Timestamp> prenotazione,ArrayList<Integer> corsa,ArrayList<Integer> accompagnatore){

        /* codice andrà nella gui
        ArrayList<Float> importo_totale=new ArrayList<>();
        ArrayList<Float> Sovrapprezzo_tot=new ArrayList<>();
        ArrayList<Integer> n_bagagli=new ArrayList<>();
        ArrayList<Timestamp> prenotazione=new ArrayList<>();
        ArrayList<Integer> corsa=new ArrayList<>();
        ArrayList<Integer> accompagnatore=new ArrayList<>();

        ArrayList<Biglietto_Ridotto> biglietti=new ArrayList<>();

        CorsaDao.retrieve_biglietti_ridotti(id_passeggero,importo_totale,Sovrapprezzo_tot,n_bagagli,prenotazione,corsa,accompagnatore);

        for(int i=0;i<importo_totale.size();i++){
            Corsa c=filtra_corse(corsa.get(i),null,null,null,null,null,null).get(0);
            Passeggero accomp=retrieve_passeggero(accompagnatore.get(i));
            Biglietto_Ridotto b=new Biglietto_Ridotto(importo_totale.get(i),Sovrapprezzo_tot.get(i),n_bagagli.get(i),veicolo.get(i),prenotazione.get(i),c,accomp);
            biglietti.add(b);
        }
        return biglietti;

         */

        CorsaDao.retrieve_biglietti_ridotti(id_passeggero,importo_totale,Sovrapprezzo_tot,n_bagagli,prenotazione,corsa,accompagnatore);

    }

    public void add_ritardo(String motivazione,Time ritardo,Integer id_corsa){
        CorsaDao.add_ritardo(motivazione, ritardo, id_corsa);
    }

    public void add_annullamento(String motivazione,Float rimborso,Integer id_corsa,Integer prossimo){
        CorsaDao.add_annullamento(motivazione,rimborso,id_corsa,prossimo);
    }
    public void retrieve_corse_compagnia(Compagnia compagnia,ArrayList<Integer> id_corsa,
                                         ArrayList<Time> orario_partenza,ArrayList<Time> orario_arrivo,
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
        /* codice in gui da qualche parte
        ArrayList<Integer> id_corsa=new ArrayList<>();
        ArrayList<Time> orario_partenza=new ArrayList<>();
        ArrayList<Time> orario_arrivo=new ArrayList<>();
        ArrayList<Date> data_inizio_servizio=new ArrayList<>();
        ArrayList<Date> data_fine_servizio=new ArrayList<>();
        ArrayList<String> giorni_servizio_attivo=new ArrayList<>();
        ArrayList<Float> sovr_prenotazione=new ArrayList<>();
        ArrayList<Float> sovr_bagaglio=new ArrayList<>();
        ArrayList<Float> sovr_veicolo=new ArrayList<>();
        ArrayList<Float> prezzo_intero=new ArrayList<>();
        ArrayList<Float> prezzo_ridotto=new ArrayList<>();
        ArrayList<Float> sconto_residente=new ArrayList<>();
        ArrayList<Integer> porto_partenza=new ArrayList<>();
        ArrayList<Integer> porto_arrivo=new ArrayList<>();
        ArrayList<Integer> porto_scalo=new ArrayList<>();
        ArrayList<Integer> natante=new ArrayList<>();
        ArrayList<String> p1_indirizzo=new ArrayList<>();
        ArrayList<String> p1_comune=new ArrayList<>();
        ArrayList<String> p1_tel_info=new ArrayList<>();
        ArrayList<String> p2_indirizzo=new ArrayList<>();
        ArrayList<String> p2_comune=new ArrayList<>();
        ArrayList<String> p2_tel_info=new ArrayList<>();
        ArrayList<String> ps_indirizzo=new ArrayList<>();
        ArrayList<String> ps_comune=new ArrayList<>();
        ArrayList<String> ps_tel_info=new ArrayList<>();
        ArrayList<String> nome_natante=new ArrayList<>();
        ArrayList<String> trasporta=new ArrayList<>();
        ArrayList<String> tipo_natante=new ArrayList<>();


        ArrayList<Corsa> corse=new ArrayList<Corsa>();

        CorsaDao.retrieve_corse_compagnia(compagnia.getId(),id_corsa,orario_partenza,orario_arrivo,data_inizio_servizio,
                data_fine_servizio,giorni_servizio_attivo,sovr_prenotazione,sovr_bagaglio,sovr_veicolo,
                prezzo_intero,prezzo_ridotto,sconto_residente,porto_partenza,porto_arrivo,porto_scalo,natante,
                p1_indirizzo,p1_comune,p1_tel_info,p2_indirizzo,p2_comune,p2_tel_info,ps_indirizzo,
                ps_comune,ps_tel_info,nome_natante,trasporta,tipo_natante);

        for(int i=0;i<id_corsa.size();i++){
            boolean[] array_giorni_servizio_attivo =new boolean[7];
            char[] c=giorni_servizio_attivo.get(i).toCharArray();
            for(int k=0;k<7;k++){
                array_giorni_servizio_attivo[k]=(c[k]=='1');
            }

            Porto porto_part=new Porto(porto_partenza.get(i),p1_indirizzo.get(i),p1_comune.get(i),p1_tel_info.get(i));
            Porto porto_scal=new Porto(porto_scalo.get(i),ps_indirizzo.get(i),ps_comune.get(i),ps_tel_info.get(i));
            Porto porto_arri=new Porto(porto_arrivo.get(i),p2_indirizzo.get(i),p2_comune.get(i),p2_tel_info.get(i));

            Natante nat=new Natante(natante.get(i),nome_natante.get(i),trasporta.get(i),tipo_natante.get(i));

            Corsa corsa=new Corsa(id_corsa.get(i),orario_partenza.get(i),orario_arrivo.get(i),data_inizio_servizio.get(i),data_fine_servizio.get(i),array_giorni_servizio_attivo,
                    sconto_residente.get(i),prezzo_intero.get(i),prezzo_ridotto.get(i),sovr_veicolo.get(i),sovr_bagaglio.get(i),sovr_prenotazione.get(i),
                    nat,porto_part,porto_arri,porto_scal,compagnia,null,null);

            corse.add(corsa);
        }

        return corse;

         */

        CorsaDao.retrieve_corse_compagnia(compagnia.getId(),id_corsa,orario_partenza,orario_arrivo,data_inizio_servizio,
                data_fine_servizio,giorni_servizio_attivo,sovr_prenotazione,sovr_bagaglio,sovr_veicolo,
                prezzo_intero,prezzo_ridotto,sconto_residente,porto_partenza,porto_arrivo,porto_scalo,natante,
                p1_indirizzo,p1_comune,p1_tel_info,p2_indirizzo,p2_comune,p2_tel_info,ps_indirizzo,
                ps_comune,ps_tel_info,nome_natante,trasporta,tipo_natante);

        for(int i=0;i<id_corsa.size();i++){
            boolean[] array_giorni_servizio_attivo =new boolean[7];
            char[] c=giorni_servizio_attivo.get(i).toCharArray();
            for(int k=0;k<7;k++){
                array_giorni_servizio_attivo[k]=(c[k]=='1');
            }

            Porto porto_part=new Porto(porto_partenza.get(i),p1_indirizzo.get(i),p1_comune.get(i),p1_tel_info.get(i));
            Porto porto_scal=new Porto(porto_scalo.get(i),ps_indirizzo.get(i),ps_comune.get(i),ps_tel_info.get(i));
            Porto porto_arri=new Porto(porto_arrivo.get(i),p2_indirizzo.get(i),p2_comune.get(i),p2_tel_info.get(i));

            Natante nat=new Natante(natante.get(i),nome_natante.get(i),trasporta.get(i),tipo_natante.get(i));

            Corsa corsa=new Corsa(id_corsa.get(i),orario_partenza.get(i),orario_arrivo.get(i),data_inizio_servizio.get(i),data_fine_servizio.get(i),array_giorni_servizio_attivo,
                    sconto_residente.get(i),prezzo_intero.get(i),prezzo_ridotto.get(i),sovr_veicolo.get(i),sovr_bagaglio.get(i),sovr_prenotazione.get(i),
                    nat,porto_part,porto_arri,porto_scal,compagnia,null,null);

            corseCompagnia.add(corsa);
        }




    }

    public void retrieve_accompagnatori(ArrayList<Integer> id_passeggero,ArrayList<String> nome,ArrayList<String> login,ArrayList<String> password,ArrayList<Integer> eta){
        /*
        ArrayList<Integer> id_passeggero=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> login=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<Integer> eta=new ArrayList<>();
        CorsaDao.retrieve_accompagnatori(id_passeggero,nome,login,password,eta);

        ArrayList<Passeggero> accompagnatori=new ArrayList<>();

        for (int i = 0; i < id_passeggero.size(); i++) {
            Passeggero p=new Passeggero(id_passeggero.get(i), nome.get(i),login.get(i),password.get(i),eta.get(i));
            accompagnatori.add(p);
        }
        return accompagnatori;

         */

        CorsaDao.retrieve_accompagnatori(id_passeggero,nome,login,password,eta);

        for (int i = 0; i < id_passeggero.size(); i++) {
            Passeggero p=new Passeggero(id_passeggero.get(i), nome.get(i),login.get(i),password.get(i),eta.get(i));
            accompagnatori.add(p);
        }

    }

    public void retrieve_porti(ArrayList<Integer> id_porto,ArrayList<String> indirizzo,ArrayList<String> comune,ArrayList<String> tel_info){

        /*
        ArrayList<Integer> id_porto=new ArrayList<>();
        ArrayList<String> indirizzo=new ArrayList<>();
        ArrayList<String> comune=new ArrayList<>();
        ArrayList<String> tel_info=new ArrayList<>();

        ArrayList<Porto> porti=new ArrayList<>();

        CorsaDao.retrieve_porti(id_porto,indirizzo,comune,tel_info);

        for (int i = 0; i < id_porto.size(); i++) {
            Porto p=new Porto(id_porto.get(i),indirizzo.get(i),comune.get(i),tel_info.get(i));
        }
        return porti;

         */

        CorsaDao.retrieve_porti(id_porto,indirizzo,comune,tel_info);

        for (int i = 0; i < id_porto.size(); i++) {
            Porto p = new Porto(id_porto.get(i), indirizzo.get(i), comune.get(i), tel_info.get(i));
            porti.add(p);
        }


    }

    public void retrieve_natanti(ArrayList<Integer> id_natante,ArrayList<String> nome,ArrayList<String> trasporta,ArrayList<String> tipo){
        /*
        ArrayList<Integer> id_natante=new ArrayList<>();
        ArrayList<String> nome=new ArrayList<>();
        ArrayList<String> trasporta=new ArrayList<>();
        ArrayList<String> tipo=new ArrayList<>();

        ArrayList<Natante> natanti=new ArrayList<>();

        CorsaDao.retrieve_natanti(id_natante,nome,trasporta,tipo);

        for (int i = 0; i < id_natante.size(); i++) {
            Natante nat=new Natante(id_natante.get(i),nome.get(i),trasporta.get(i),tipo.get(i));
            natanti.add(nat);
        }
        return natanti;

         */
        CorsaDao.retrieve_natanti(id_natante,nome,trasporta,tipo);

        for (int i = 0; i < id_natante.size(); i++) {
            Natante nat=new Natante(id_natante.get(i),nome.get(i),trasporta.get(i),tipo.get(i));
            natanti.add(nat);
        }

    }

    public void delete_corsa(Integer id_corsa){
        CorsaDao.delete_corsa(id_corsa);
    }

    public void add_biglietto_intero(float importo_totale,float sovrapprezzo_tot,int n_bagagli,String veicolo,Timestamp prenotazione,int corsa,int passeggero){
        CorsaDao.add_biglietto_intero(importo_totale, sovrapprezzo_tot, n_bagagli, veicolo, prenotazione, corsa, passeggero);
    }

    public void add_biglietto_ridotto(float importo_totale,float sovrapprezzo_tot,int n_bagagli,Timestamp prenotazione,int corsa,int passeggero,int accompagnatore){
        CorsaDao.add_biglietto_ridotto(importo_totale, sovrapprezzo_tot, n_bagagli, prenotazione, corsa, passeggero, accompagnatore);
    }

    public void change_password(String login,String new_password,String old_password)throws SQLException{
        CorsaDao.change_password(login, new_password, old_password);
    }
    public void change_login(String old_login,String new_login,String password) throws SQLException{
        CorsaDao.change_login(old_login, new_login, password);
    }

    public void retrieve_social(int id_compagnia,ArrayList<String> nome_social,ArrayList<String> indirizzo_social){
        /*
        ArrayList<String> nome_social=new ArrayList<>();
        ArrayList<String> indirizzo_social=new ArrayList<>();

        CorsaDao.retrieve_social(id_compagnia,nome_social,indirizzo_social);

        ArrayList<Social> socials=new ArrayList<>();

        for (int i = 0; i < nome_social.size(); i++) {
            socials.add(new Social(nome_social.get(i),indirizzo_social.get(i)));
        }

        return socials;

         */
        CorsaDao.retrieve_social(id_compagnia,nome_social,indirizzo_social);

        for (int i = 0; i < nome_social.size(); i++) {
            socialsCompagnia.add(new Social(nome_social.get(i),indirizzo_social.get(i)));
        }
    }


    public boolean[] getGiorniServizioAttivo(Corsa c) {
        return c.getGiorniServizioAttivo();
    }

    public Porto getPorto_Partenza(Corsa c) {
        return c.getPorto_Partenza();
    }

    public Porto getPorto_Arrivo(Corsa c) {
        return c.getPorto_Arrivo();
    }

    public Utente getCompagnia(Corsa c) {
        return c.getCompagnia();
    }

    public Time getOrario_Partenza(Corsa c) {
        return c.getOrario_Partenza();
    }

    public float getPrezzoIntero(Corsa c){
        return c.getPrezzoIntero();
    }
    public float getPrezzoRidotto(Corsa c){
        return c.getPrezzoRidotto();
    }

    public Time getOrario_Arrivo(Corsa c) {
        return c.getOrario_Arrivo();
    }

    public String getComune(Porto portoPartenza) {
        return portoPartenza.getComune();
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
