/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

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
}