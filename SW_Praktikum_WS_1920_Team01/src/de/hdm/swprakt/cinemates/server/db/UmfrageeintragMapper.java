/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;

/**
 * Diese Mapperklasse bildet <code>Umfrageeintrag</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhalt Methoden, um Umfrageeinträge anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Umfrageeintrag</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Umfrageeintrag</code>  umgewandelt werden.
 * 
 * @author alina
 * @version 1.0
 *
 */
public class UmfrageeintragMapper {

	/**Die Klasse <code>UmfrageeintragMapper</code> wird wie jede andere Mapperklasse 
	 * nur ein einziges mal instantiiert. Die Variable umfrageeintragMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
	 * 
	 */
	private static UmfrageeintragMapper umfrageeintragMapper = null;
	private static DateConverter dc = new DateConverter();


	/** 
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>UmfrageeintragMapper</code> zu erzeugen.
	 * 
	 */
	protected UmfrageeintragMapper() {

	}
	/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>UmfrageeintragMapper</code>
	 * erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein UmfrageeintragMapper-Objekt erzeugt ist,
	 * dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
	 * Ist bereits ein <code>UmfrageeintragMapper</code> - Objekt erzeugt worden, so geben wir die Referenz auf dieses zurück.
	 * 
	 * @return das einzige <code>umfrageeintragMapper</code> - Objekt
	 */

	public static UmfrageeintragMapper umfrageeintragMapper() {
		if (umfrageeintragMapper == null) {
			umfrageeintragMapper = new UmfrageeintragMapper();
		}

		return umfrageeintragMapper;
	}

	/**
	 * Suchen eines Umfrageeintrags mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Objekt der Klasse <code >Umfrageeintrag </code>zurückgegeben. 
	 * 
	 * @param id (Siehe Primärschlüsselattribut der Tabelle Umfrageeintrag in der DB)
	 * @return Umfrageeintrag-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */

	public Umfrageeintrag findByID(int id) {

		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrageeintrag` WHERE (`umfrageeintrag_id` = " + id + ")");

			/* Da ID Primaerschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Umfrageeintrag umfrageeintrag = new Umfrageeintrag();
				umfrageeintrag.setID(rs.getInt("umfrageeintrag_id"));
				umfrageeintrag.setUmfrageID(rs.getInt("umfrage_id"));
				umfrageeintrag.setKinoID(rs.getInt("kino_id"));
				umfrageeintrag.setSpielzeitID(rs.getInt("spielzeit_id"));


				return umfrageeintrag;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Suchen aller Objekte der Klasse <code> Umfrageeintrag </code>
	 * @return Vector <Umfrageeintrag>, welcher alle Umfrageeinträge beinhaltet
	 */


	public Vector <Umfrageeintrag> findAllUmfrageeintrag(){
		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();
		//Neuen Vector instantiieren, in welchem Umfrageeintrag-Objekte gespeichert werden.

		Vector <Umfrageeintrag> vectorumfrageeintrag = new Vector <Umfrageeintrag>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrageeintrag` ORDER BY `umfrageeintrag_id`");

			/* Befüllen des result sets
			 */
			while (rs.next()) {
				// Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt
				Umfrageeintrag umfrageeintrag = new Umfrageeintrag();
				umfrageeintrag.setID(rs.getInt("umfrageeintrag_id"));
				umfrageeintrag.setKinoID(rs.getInt("kino_id"));
				umfrageeintrag.setUmfrageID(rs.getInt("umfrage_id"));
				umfrageeintrag.setSpielzeitID(rs.getInt("spielzeit_id"));
				vectorumfrageeintrag.add(umfrageeintrag);

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrageeintrag;
	}

	/**
	 * Suchen aller Objekte der Klasse <code> Umfrageeintrag </code>, welche zu der übergebenen Umfrage gehören
	 * @param Objekt der Klasse <Umfrage>
	 * @return Vector <Umfrageeintrag>, welcher alle Umfrageeinträge beinhaltet, welche der übergebenen Umfrage gehören
	 */


	public Vector <Umfrageeintrag> findByUmfrage(Umfrage umfrage) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		//Neuen Vector instantiieren, in welchem Umfrageeintrag-Objekte gespeichert werden.

		Vector <Umfrageeintrag> vectorumfrageeintrag = new Vector <Umfrageeintrag>();
		try {


			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrageeintrag` WHERE (`umfrage_id` = " + umfrage.getID() + " ) ORDER BY `umfrageeintrag_id`");

			/* Befüllen des result sets
			 */
			while (rs.next()) {
				/** Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt.  Dazu wird ein Objekt der Klasse <code>Umfrageeintrag</code> angelegt und dessen Attribute werden gesetzt. 
				Dies wird wiederholt. Das Ergebnis wird jeweils einem Vector hinzugefügt, welchen wir am Ende zurückgeben.
				 */
				Umfrageeintrag umfrageeintrag = new Umfrageeintrag();
				umfrageeintrag.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrageeintrag.setID(rs.getInt("umfrageeintrag_id"));
				umfrageeintrag.setKinoID(rs.getInt("kino_id"));
				umfrageeintrag.setUmfrageID(rs.getInt("umfrage_id"));
				umfrageeintrag.setSpielzeitID(rs.getInt("spielzeit_id"));
				vectorumfrageeintrag.add(umfrageeintrag);


			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrageeintrag;
	}

	/**
	 * Suchen aller Objekte der Klasse <code> Umfrageeintrag </code>, welche die übergebene Spielzeit enthalten
	 * @param Objekt der Klasse <Spielzeit>
	 * @return Vector <Umfrageeintrag>, welcher alle Umfrageeinträge beinhaltet, welche die übergebene Spielzeit beinhalten
	 */


	public Vector<Umfrageeintrag> findBySpielzeit(Spielzeit spielzeit) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		//Neuen Vector instantiieren, in welchem Umfrageeintrag-Objekte gespeichert werden.

		Vector <Umfrageeintrag> vectorumfrageeintrag = new Vector <Umfrageeintrag>();
		try {


			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrageeintrag` WHERE (`spielzeit_id` =" + spielzeit.getID() + " ) ORDER BY `umfrageeintrag_id`");

			/* Befüllen des result sets
			 */
			while (rs.next()) {
				// Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt. 
				Umfrageeintrag umfrageeintrag = new Umfrageeintrag();
				umfrageeintrag.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrageeintrag.setID(rs.getInt("umfrageeintrag_id"));
				umfrageeintrag.setKinoID(rs.getInt("kino_id"));
				umfrageeintrag.setUmfrageID(rs.getInt("umfrage_id"));
				umfrageeintrag.setSpielzeitID(rs.getInt("spielzeit_id"));
				vectorumfrageeintrag.add(umfrageeintrag);

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrageeintrag;
	}

	/**
	 * Suchen aller Objekte der Klasse <code> Umfrageeintrag </code>, welche ein übergebenes Kino enthalten
	 * @param Objekt der Klasse <Spielzeit>
	 * @return Vector <Umfrageeintrag>, welcher alle Umfrageeinträge beinhaltet, welche die übergebene Spielzeit beinhalten
	 */


	public Vector<Umfrageeintrag> findByKino(Kino kino) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		//Neuen Vector instantiieren, in welchem Umfrageeintrag-Objekte gespeichert werden.

		Vector <Umfrageeintrag> vectorumfrageeintrag = new Vector <Umfrageeintrag>();
		try {


			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrageeintrag` WHERE (`kino_id` =" + kino.getID() + " ) ORDER BY `umfrageeintrag_id`");

			/* Befüllen des result sets
			 */
			while (rs.next()) {
				/** Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt.  Dazu wird ein Objekt der Klasse <code>Umfrageeintrag</code> angelegt und dessen Attribute werden gesetzt. 
						Dies wird wiederholt. Das Ergebnis wird jeweils einem Vector hinzugefügt, welchen wir am Ende zurückgeben.
				 */

				Umfrageeintrag umfrageeintrag = new Umfrageeintrag();
				umfrageeintrag.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrageeintrag.setID(rs.getInt("umfrageeintrag_id"));
				umfrageeintrag.setKinoID(rs.getInt("kino_id"));
				umfrageeintrag.setUmfrageID(rs.getInt("umfrage_id"));
				umfrageeintrag.setSpielzeitID(rs.getInt("spielzeit_id"));
				vectorumfrageeintrag.add(umfrageeintrag);

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrageeintrag;
	}
	/**
	 * Einfügen eines Umfrageeintrags in die Datenbank.
	 * @return Ein Objekt der Klasse <Umfrageeintrag>
	 */

	public Umfrageeintrag insert (Umfrageeintrag umfrageeintrag) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(umfrageeintrag_id) AS `maxid` FROM `umfrageeintrag`");

			if (rs.next()) {
				umfrageeintrag.setID(rs.getInt("maxid") + 1);
			}	

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `umfrageeintrag` (`umfrageeintrag_id`, `umfrage_id`, `spielzeit_id`, `kino_id`, `Erstellungszeitpunkt`) VALUES (?, ?, ?, ?, ?) ");
			pstmt.setInt(1, umfrageeintrag.getID());
			pstmt.setInt(2, umfrageeintrag.getUmfrageID());
			pstmt.setInt(3, umfrageeintrag.getSpielzeitID());
			pstmt.setInt(4, umfrageeintrag.getKinoID());
			pstmt.setTimestamp(5, dc.convertJavaDateToSqlTimestamp(umfrageeintrag.getErstellungszeitpunkt()));
			pstmt.executeUpdate();
			return umfrageeintrag;

		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}


	}
	/**
	 * Aktualisieren eines Umfrageeintrags in der Datenbank.Achtung: Es können nicht alle Attribute aktualisiert werden.
	 * Es wurde auf weniger sinnvolle Möglichkeiten verzichtet.
	 * @return Ein (überarbeitetes) Objekt der Klasse <Umfrageeintrag>
	 */

	public Umfrageeintrag update (Umfrageeintrag umfrageeintrag) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("UPDATE `umfrageeintrag` SET `umfrage_id` = ?, `spielzeit_id` = ?, `kino_id` = ?  WHERE (`umfrageeintrag_id` = ? )");
			pstmt.setInt(1, umfrageeintrag.getUmfrageID());
			pstmt.setInt(2, umfrageeintrag.getSpielzeitID());
			pstmt.setInt(3, umfrageeintrag.getKinoID());
			pstmt.setInt(4, umfrageeintrag.getID());
			pstmt.executeUpdate();
			return umfrageeintrag;

		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}


	}
	/**
	 * Löschen eines Umfrageeintrags aus der Datenbank.
	 */

	public void delete (Umfrageeintrag umfrageeintrag) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `umfrageeintrag` WHERE (`umfrageeintrag_id` = " + umfrageeintrag.getID() + ")");



		}
		catch(SQLException e) {
			e.printStackTrace();
		}


	}
	
	public Vector <Umfrageeintrag> findUmfrageeintragOhneVotum (Nutzer nutzer) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		Vector <Umfrageeintrag> vectorumfrageeintrag = new Vector <Umfrageeintrag>();
		try {


			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `votum` LEFT JOIN `umfrageeintrag` ON `votum`.`umfrageeintrag_id` = `umfrageeintrag`.`umfrageeintrag_id` LEFT JOIN `ownedbusinessobject` ON `votum`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`istMöglicherTermin` = NULL AND `ownedbusinessobject`.`owner_id` = " + nutzer.getID() + ") ORDER BY umfrageeintrag_id");
			while (rs.next()) {
				/** Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt.  Dazu wird ein Objekt der Klasse <code>Umfrageeintrag</code> angelegt und dessen Attribute werden gesetzt. 
						Dies wird wiederholt. Das Ergebnis wird jeweils einem Vector hinzugefügt, welchen wir am Ende zurückgeben.
				 */

				Umfrageeintrag umfrageeintrag = new Umfrageeintrag();
				umfrageeintrag.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrageeintrag.setID(rs.getInt("umfrageeintrag_id"));
				umfrageeintrag.setKinoID(rs.getInt("kino_id"));
				umfrageeintrag.setUmfrageID(rs.getInt("umfrage_id"));
				umfrageeintrag.setSpielzeitID(rs.getInt("spielzeit_id"));
				vectorumfrageeintrag.add(umfrageeintrag);
			}
		}

		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return vectorumfrageeintrag;
	}
}
