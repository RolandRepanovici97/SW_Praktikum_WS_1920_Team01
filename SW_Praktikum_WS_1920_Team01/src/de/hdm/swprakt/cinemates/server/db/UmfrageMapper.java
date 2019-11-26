/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * Diese Mapperklasse bildet <code>Umfrage</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhalt Methoden, um Umfrageobjekte anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Umfrage</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Umfrage</code> umgewandelt werden.
 * 
 * @author alina
 * @version 1.0
 *
 */
public class UmfrageMapper {

	/**Die Klasse <code>Umfrage</code> wird wie jede andere Mapperklasse 
	 * nur ein einziges mal instantiiert. Die Variable umfrageMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
	 * 
	 */
	private static UmfrageMapper umfrageMapper = null;
	private static DateConverter dc = new DateConverter();

	/** 
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>UmfrageMapper</code> zu erzeugen.
	 * 
	 */
	protected UmfrageMapper(){

	}
	/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>UmfrageMapper</code>
	 * erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein UmfrageMapper-Objekt erzeugt ist,
	 * dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
	 * Ist bereits ein <code>UmfrageMapper</code> - Objekt erzeugt worden, so geben wir die Referenz auf dieses zurück.
	 * 
	 * @return das einzige <code>UmfrageMapper</code> - Objekt
	 */

	public static UmfrageMapper umfrageMapper() {
		if (umfrageMapper == null) {
			umfrageMapper = new UmfrageMapper();
		}

		return umfrageMapper;
	}

	/**
	 * Suchen einer Umfrage mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Objekt der Klasse <code >Umfrage</code>zurückgegeben. 
	 * 
	 * @param id (Siehe Primärschlüsselattribut der Tabelle Umfrage in der DB)
	 * @return Umfrage-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */



	public Umfrage findByID(int id) {

		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrage` LEFT JOIN `ownedbusinessobject` ON `umfrage`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`umfrage_id`= " + id + ") ORDER BY `umfrage_id`");

			/* Da ID Primaerschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Umfrage umfrage = new Umfrage();
				umfrage.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setFilmID(rs.getInt("film_id"));
				umfrage.setUmfragenname(rs.getString("Umfragename"));
				umfrage.setDatum(dc.convertSQLDateToJavaDate(rs.getDate("Datum")));

				return umfrage;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public Vector<Umfrage> findAll() {
		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();
		Vector <Umfrage> vectorumfrage = new Vector <Umfrage>();
		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM umfrage LEFT JOIN ownedbusinessobject ON nutzer.bo_id = ownedbusinessobject.bo_Id ORDER BY umfrage_id");

			/* Befüllen des result sets
			 */
			while (rs.next()) {
				// Es werden für jedes Umfrage-Objekt die nötigen Attribute gesetzt
				Umfrage umfrage = new Umfrage();
				umfrage.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				umfrage.setOwnerID(rs.getInt("owner_id"));
				umfrage.setID(rs.getInt("umfrage_id"));
				umfrage.setBeschreibung(rs.getString("beschreibung"));
				umfrage.setFilmID(rs.getInt("film_id"));
				umfrage.setUmfragenname(rs.getString("Umfragename"));
				umfrage.setDatum(dc.convertSQLDateToJavaDate(rs.getDate("Datum")));
				vectorumfrage.add(umfrage);
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorumfrage;
	}

}
