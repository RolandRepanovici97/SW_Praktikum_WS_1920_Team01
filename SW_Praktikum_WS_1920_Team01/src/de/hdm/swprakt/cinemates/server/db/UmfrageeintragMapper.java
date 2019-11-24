/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.Statement;

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
	 * @return Kunden-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */

	
public Umfrageeintrag findByKey(int id) {
	/**
	 *  Verbindung zur Datenbank aufbauen.
	 */
	
	
Connection con = DBConnection.connection();

try {
	/* Leeres SQL-Statement (JDBC) anlegen
	 * 
	 */
	Statement stmt = con.createStatement();
	
	
}

}
}
