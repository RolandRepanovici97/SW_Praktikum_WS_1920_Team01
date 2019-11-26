package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import de.hdm.swprakt.cinemates.shared.bo.Kino;


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

		public Vector<Kino> findAll() {

			Connection con = DBConnection.connection();
			Vector<Kino> kino = new Vector<Kino>();

			try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM kino");

				while (rs.next()) {
					Kino k = new Kino();
					k.setID(rs.getInt("kino_id"));
					k.setBeschreibung(rs.getString("Kino Beschreibung"));
					k.setName(rs.getString("Kinoname"));
					k.setOrt(rs.getString("Adresse"));
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM kino WHERE kino_id = " + id + "ORDER BY kino_id ");
//Muss man alle Attribute angeben?
				if (rs.next()) {
					Kino k = new Kino();
					k.setID(rs.getInt("kino_id"));
					k.setID(rs.getInt("kinokette_id"));
					k.setID(rs.getInt("spielplan_id"));
					k.setName(rs.getString("Kinoname"));
					k.setOrt(rs.getString("Adresse"));
					k.setBeschreibung(rs.getString("Kino Beschreibung"));
				
					return k;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}	
		
/**
 * Einfügen eines Kinos in die Datenbank.
 * @return Ein Objekt der Klasse <Kino>
 */
		
		public Kino insert(Kino kino) {

			Connection con = DBConnection.connection();

			try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MAX(kino_id) AS maxid FROM kino");

				if (rs.next()) {
					kino.setID(rs.getInt("maxid") + 1);
				}
//bo_id??
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO kino (kino_id, bo_id, Kinoname, Adresse, Kino Beschreibung) VALUES (?, ?, ?, ?,?) ");
				pstmt.setInt(1, kino.getID());
				pstmt.setString(2, kino.getName() );
				pstmt.setString(3, kino.getOrt());
				pstmt.setString(4, kino.getBeschreibung());
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
//Name, Ort, Beschreibung ausreichend?
				PreparedStatement pstmt = con.prepareStatement("UPDATE kino SET Kinoname = ?, Adresse = ?, Kino Beschreibung = ? WHERE kino_id = ?");
				pstmt.setString(1, kino.getName());
				pstmt.setString(2, kino.getOrt());
				pstmt.setString(3, kino.getBeschreibung());
				pstmt.setInt(4, kino.getID());
				
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
				ResultSet rs = stmt.executeQuery("DELET FROM kino WHERE kino_id=" + kino.getID());
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
}
