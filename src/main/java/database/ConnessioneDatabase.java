package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Questa classe esegue riceve e instaura una connessione con il database da restituire poi all applicativo
 *  tramite patten Singleton (il punto di accesso alla connessione è unico per tutte le classi).
 */
public class ConnessioneDatabase {

    private static ConnessioneDatabase instance;
    public Connection connection = null;
    private String nome = "riccpuggio";
    private String password = "owYA6Z4KklUN";
    private String url = "jdbc:postgresql://ep-holy-pine-64797515.eu-central-1.aws.neon.tech:5432/Arturo%27s%20Sea%20Travels?sslmode=require";
    private String driver = "org.postgresql.Driver";

    private ConnessioneDatabase(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);

        } catch (ClassNotFoundException ex) {
            System.out.println("Nessun driver manager trovato: " + ex.getMessage());
        }
        catch(SQLException ex){
            System.out.println("Impossibile connettersi al database: " + ex.getMessage());
        }


    }


    public static ConnessioneDatabase getInstance() {
        try {
            if (instance == null || instance.connection.isClosed()) {
                instance = new ConnessioneDatabase();
            }
        }
        catch(SQLException ex){
            System.out.println("Impossibile connettersi al database: " + ex.getMessage());
        }
        return instance;


    }

    public Connection getConnection() {
        return connection;
    }
}
