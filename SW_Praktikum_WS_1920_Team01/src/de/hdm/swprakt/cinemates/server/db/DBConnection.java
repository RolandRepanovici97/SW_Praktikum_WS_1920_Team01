package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    
    private static Connection con = null;

   
//    private static String googleUrl = "";
    private static String localUrl = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "cinemates" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";

    
    public static Connection connection() {
        // Wenn es bisher keine Conncetion zur DB gab, ...
        if (con == null) {
            String url = null;
            try {
            	
//            	con = DriverManager.getConnection("jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "bankproject" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin", "root", "root");
                
//            	if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//                   // Load the class that provides the new
//                   // "jdbc:google:mysql://" prefix.
//                   Class.forName("com.mysql.jdbc.GoogleDriver");
//                    url = googleUrl;
//                } else {
                    // Local MySQL instance to use during development.
//                   Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
//                    System.out.println("connection successfull");
//                }
                /*
                 * Dann erst kann uns der DriverManager eine Verbindung mit den
                 * oben in der Variable url angegebenen Verbindungsinformationen
                 * aufbauen.
                 * 
                 * Diese Verbindung wird dann in der statischen Variable con
                 * abgespeichert und fortan verwendet.
                 */
                con = DriverManager.getConnection(url, "root", "root");
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        // Zurückgegeben der Verbindung
        return con;
    }

}
