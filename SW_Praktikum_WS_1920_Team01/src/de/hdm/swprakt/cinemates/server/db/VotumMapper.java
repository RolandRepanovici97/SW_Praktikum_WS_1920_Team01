/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Diese Mapperklasse bildet <code>Votum</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhaltet Methoden, um Vota anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Votum</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Votum</code>  umgewandelt werden.
 * 
 * @author alina
 * @version 1.0
 *
 */
public class VotumMapper {
	/**Die Klasse <code>VotumMapper</code> wird wie jede andere Mapperklasse 
	 * nur ein einziges mal instantiiert. Die Variable votumMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
	 * 
	 */
	private static VotumMapper votumMapper = null;
	private static TimestampManager tsm = new TimestampManager();

	/** 
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>VotumMapper</code> zu erzeugen.
	 * 
	 */
	protected VotumMapper() {

	}

	/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>VotumMapper</code>
	 * erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein VotumMapper-Objekt erzeugt ist,
	 * dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
	 * Ist bereits ein <code>VotumMapper</code> - Objekt erzeugt worden, so geben wir die Referenz auf dieses zurück.
	 * 
	 * @return das einzige <code>VotumMapper</code> - Objekt
	 */

	public static VotumMapper votumMapper() {
		if (votumMapper == null) {
			votumMapper = new VotumMapper();
		}

		return votumMapper;
	}

	/**
	 * Suchen eines Umfrageeintrags mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Objekt der Klasse <code >Umfrageeintrag </code>zurückgegeben. 
	 * 
	 * @param id (Siehe Primärschlüsselattribut der Tabelle Votum in der DB)
	 * @return Votum-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */

	public Votum findByID(int id) {

		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM votum LEFT JOIN ownedbusinessobject ON nutzer.bo_id = ownedbusinessobject.bo_Id WHERE votum_id=" + id + "ORDER BY votum_id");

			/* Da ID Primaerschlüssel ist, kann max. nur ein Tupel zurückgegeben werden.
			 * Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Votum votum = new Votum();
				votum.setID(rs.getInt("votum_id"));
				votum.setErstellungszeitpunkt(tsm.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				votum.setOwnerID(rs.getInt("bo_id"));
				votum.setIstMöglicherTermin(rs.getBoolean("istMöglicherTermin"));
				votum.setUmfrageeintragID(rs.getInt("umfrageeintrag_id"));



				return votum;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public Vector <Votum> findAll() {
		//Verbindung zur Datenbank aufbauen.
		Connection con = DBConnection.connection();
		// Neuen Vector instantiieren, in welchem unsere Votum-Objekte gespeichert werden
		Vector <Votum> vectorvotum = new Vector <Votum>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM votum LEFT JOIN ownedbusinessobject ON nutzer.bo_id = ownedbusinessobject.bo_Id ORDER BY votum_id");

			/* Befüllen des result sets
			 */
			if (rs.next()) {
				// Es werden für jedes Votum-Objekt die nötigen Attribute gesetzt
				Votum votum = new Votum();
				votum.setID(rs.getInt("votum_id"));
				votum.setErstellungszeitpunkt(tsm.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				votum.setOwnerID(rs.getInt("bo_id"));
				votum.setIstMöglicherTermin(rs.getBoolean("istMöglicherTermin"));
				votum.setUmfrageeintragID(rs.getInt("umfrageeintrag_id"));
				vectorvotum.add(votum);



				return vectorvotum;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}