package org.example;


import MODEL.Corsa;
import controller.Controller;
import dao.DaoManager;
import gui.*;
import impl_dao_postgres.impl_CorsaDAO;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //ArrayList<Corsa> corse=DaoManager.getCorsaDAO().leggi_corse();
        ArrayList<Corsa> corse= new ArrayList<>();
        //corse= new Controller().leggi_corse();
        //System.out.println(corse.size());
        //for(Corsa c:corse){
            //System.out.println(c.getOrario_Partenza().toString());
        //}
        LoginFrame login=new LoginFrame(); //decommenta quando FramePasseggero pronto
        //new FramePasseggero();
        //new FrameCompagnia();
        //new RegisterFrame();

        //new ModCorsa();

        //new LoginFrame();
        //new ChangePasswordFrame();
        //new ChangeUserFrame();

        //new FrameTabellone();

        //new FrameTabellone();

        //new FrameProfilo();
    }
}