package dao;

import java.util.ArrayList;

public class DaoManager {
    static private CorsaDAO corsaDAO=null;

    static public void registerCorsaDAO(CorsaDAO d){
        corsaDAO= d;
    }
    static public CorsaDAO getCorsaDAO(){
        System.out.println("daomanager");
        System.out.println(corsaDAO);
        return corsaDAO;
    }

}
