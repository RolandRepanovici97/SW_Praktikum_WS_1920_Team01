package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;


/**
 * Diese Mapperklasse bildet <code>Kino</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhalt Methoden, um Kinos anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Kino</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Kino</code>  umgewandelt werden.
 * 
 * @author Sinan
 * @version 1.0
 *
 */

public class KinoMapper {

/**Die Klasse <code>KinoMapper</code> wird wie jede andere Mapperklasse 
 * nur ein einziges mal instantiiert. 
 * Die Variable kinoMapper speichert diese Instanz.
 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
 * 
 */
		private static KinoMapper kinoMapper = null;
		
		private static DateConverter dc = new DateConverter();
/** 
 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
 * neue Instanzen der Klasse <code>KinoMapper</code> zu erzeugen.
 * 
 */
		protected KinoMapper() {

		}
		
/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>KinoMapper</code>
 * erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein KinoMapper-Objekt erzeugt ist,
 * dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
 * Ist bereits ein <code>KinoMapper</code> - Objekt erzeugt worden, 
 * so geben wir die Referenz auf dieses zurück.
 * 
 * @return das einzige <code>kinoMapper</code> - Objekt
 */

		public static KinoMapper kinoMapper() {
			if (kinoMapper == null) {
				kinoMapper = new KinoMapper();
			}

			return kinoMapper;
		}


/**
 * Suchen aller Objekte der Klasse <code> Kino </code>
 * @return Vector <Kino>, welcher alle Kinoeinträge beinhaltet
 */

		public Vector<Kino> findAllKino() {

			Connection con = DBConnection.connection();
			Vector<Kino> kino = new Vector<Kino>();

			try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM `kino` ORDER BY `kino_id`");

				while (rs.next()) {
					Kino k = new Kino();
					k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
					k.setID(rs.getInt("kino_id"));
					k.setKinoketteID(rs.getInt("kinokette_id"));
					k.setSpielplanID(rs.getInt("spielplan_id"));
					k.setKinoname(rs.getString("Kinoname"));
					k.setAdresse(rs.getString("Adresse")); 
					k.setBeschreibung(rs.getString("Beschreibung"));
					kino.add(k);
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			return kino;
		}		
		
/**
 * Suchen eines Kinos mithilfe seiner ID. Die ID ist eindeutig, es wird genau
 * ein Objekt der Klasse <code>Kino</code>zurückgegeben. 
 * 
 * @param id (Siehe Primärschlüsselattribut der Tabelle Kino in der DB)
 * @return Kino-Objekt, das dem übergebenen Schlüssel entspricht. 
 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
 */

		public Kino findByID(int id) {

			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM `kino` WHERE (`kino_id` = " + id + ")");

				if (rs.next()) {
					Kino k = new Kino();
					k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
					k.setID(rs.getInt("kino_id"));
					k.setKinoketteID(rs.getInt("kinokette_id"));
					k.setSpielplanID(rs.getInt("spielplan_id"));
					k.setKinoname(rs.getString("Kinoname"));
					k.setAdresse(rs.getString("Adresse")); 
					k.setBeschreibung(rs.getString("Beschreibung"));
					return k;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}	
		
/**
 * Suchen eines Kinos mithilfe seines Namens. 
 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
 */	
		public Kino findByKinoname(String kinoname) {

			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM `kino` WHERE (`Kinoname` = '" + kinoname + "')");

				if (rs.next()) {
					Kino k = new Kino();
					k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
					k.setID(rs.getInt("kino_id"));
					k.setKinoketteID(rs.getInt("kinokette_id"));
					k.setSpielplanID(rs.getInt("spielplan_id"));
					k.setKinoname(rs.getString("Kinoname"));
					k.setAdresse(rs.getString("Adresse")); 
					k.setBeschreibung(rs.getString("Beschreibung"));
					
					return k;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}	
		
		
/**
 * Suchen eines Kinos innerhalb der Kinokette
 */

		public Vector<Kino> findByKinokette (Kinokette kinokette){
			
			Connection con = DBConnection.connection();
			Vector<Kino> kino = new Vector<Kino>();
			try {
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from `kino` WHERE (`kinokette_id` = " + kinokette.getID() + ")");
				
				while (rs.next()) {
					Kino k = new Kino();
					k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
					k.setID(rs.getInt("kino_id"));
					k.setKinoketteID(rs.getInt("kinokette_id"));
					k.setSpielplanID(rs.getInt("spielplan_id"));
					k.setKinoname(rs.getString("Kinoname"));
					k.setAdresse(rs.getString("Adresse")); 
					k.setBeschreibung(rs.getString("Beschreibung"));
					kino.add(k);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return kino;	
		}

		
/**
 * Suchen eines Kinos innerhalb des Spielplans
 */

		public Vector<Kino> findBySpielplan (Spielplan spielplan){
			
			Connection con = DBConnection.connection();
			Vector<Kino> kinos = new Vector<Kino>();
			try {
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * from `kino` WHERE (`spielplan_id` = " + spielplan.getID() + ")");
				
				while (rs.next()) {
					Kino k = new Kino();
					k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
					k.setID(rs.getInt("kino_id"));
					k.setKinoketteID(rs.getInt("kinokette_id"));
					k.setSpielplanID(rs.getInt("spielplan_id"));
					k.setKinoname(rs.getString("Kinoname"));
					k.setAdresse(rs.getString("Adresse")); 
					k.setBeschreibung(rs.getString("Beschreibung"));
					kinos.add(k);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
				return kinos;
		}
		
/**
 * Einfügen eines Kinos in die Datenbank.
 * @return Ein Objekt der Klasse <Kino>
 */
		
		public Kino insert(Kino kino) {

			Connection con = DBConnection.connection();

			try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MAX(kino_id) AS `maxid` FROM kino");

				if (rs.next()) {
					kino.setID(rs.getInt("maxid") + 1);
				}

				PreparedStatement pstmt = con.prepareStatement("INSERT INTO `kino` (`kino_id`, `kinokette_id`, `spielplan_id`,  `Kinoname`, `Adresse`, `Beschreibung`, `Erstellungszeitpunkt`) VALUES (?, ?, ?, ?, ?, ?, ?) ");
				pstmt.setInt(1, kino.getID());
				pstmt.setInt(2, kino.getKinoketteID());
				pstmt.setInt(3, kino.getSpielplanID());
				pstmt.setString(4, kino.getKinoname());
				pstmt.setString(5, kino.getAdresse());
				pstmt.setString(6, kino.getBeschreibung());
				pstmt.setTimestamp(7, dc.convertJavaDateToSqlTimestamp(kino.getErstellungszeitpunkt()));
				
				pstmt.executeUpdate();
				return kino;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

/**
 * Aktualisieren eines Kinos in der Datenbank.
 * @return Ein (überarbeitetes) Objekt der Klasse <Kino>
 */

		public Kino update (Kino kino) {
			
			Connection con = DBConnection.connection();
			
			try { 

				PreparedStatement pstmt = con.prepareStatement("UPDATE `kino` SET `spielplan_id` = ?, `Kinoname` = ?, `Adresse` = ?, `Beschreibung` = ? WHERE (`kino_id` = ?)");
				pstmt.setInt(1, kino.getSpielplanID());
				pstmt.setString(2, kino.getKinoname());
				pstmt.setString(3, kino.getAdresse());
				pstmt.setString(4, kino.getBeschreibung());
				pstmt.setInt(5, kino.getID());
				
				pstmt.executeUpdate();
				
				return kino;
			
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}	
		}

/**
 * Löschen eines Kinos in der Datenbank.
 */
		public void delete (Kino kino) {
			 
			//Verbindung zur Datenbank aufbauen.
			
			Connection con = DBConnection.connection();
			
			try {
				// Leeres SQL-Statement (JDBC) anlegen
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM `kino` WHERE (`kino_id`=" + kino.getID() + ")");
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
/**
 * Löschen eines Kinos in der Kinokette	
 */
		public void deleteKinosOf (Kinokette kinokette) {
			
			//Verbindung zur Datenbank aufbauen.
			
			Connection con = DBConnection.connection();
			
			try {
				// Leeres SQL-Statement (JDBC) anlegen
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM `kino` WHERE (`kinokette_id` = " + kinokette.getID() + ")");
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
}
 