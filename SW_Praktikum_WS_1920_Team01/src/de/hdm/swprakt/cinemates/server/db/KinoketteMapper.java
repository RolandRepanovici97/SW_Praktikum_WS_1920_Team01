package de.hdm.swprakt.cinemates.server.db;

/**
 * Diese Mapperklasse bildet <code>Kinokette</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhalt Methoden, um Kinoketten anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code>Kinokette</code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code>Kinokette</code>  umgewandelt werden.
 * 
 * @author Sinan
 * @version 1.0
 *
 */

public class KinoketteMapper {

/**Die Klasse <code>KinoketteMapper</code> wird wie jede andere Mapperklasse 
 * nur ein einziges mal instantiiert. 
 * Die Variable kinoketteMapper speichert diese Instanz.
 * Wir stellen die einmalige Initalisierung durch den Bezeichner <code>static</code> sicher. 
 * 
 */
	private static KinoketteMapper kinoketteMapper = null;
	
/** 
 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
 * neue Instanzen der Klasse <code>KinoketteMapper</code> zu erzeugen.
 * 
 */
	
	protected KinoketteMapper() {

	}
	
/** Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse <code>KinoketteMapper</code>
* erzeugt werden. Die Methode beinhaltet die Prüfung, ob bereits ein KinoketteMapper-Objekt erzeugt ist,
* dadurch wird sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist.
* Ist bereits ein <code>KinoketteMapper</code> - Objekt erzeugt worden, 
* so geben wir die Referenz auf dieses zurück.
* 
* @return das einzige <code>kinoketteMapper</code> - Objekt
*/

	public static KinoketteMapper kinoketteMapper() {
		if (kinoketteMapper == null) {
			kinoketteMapper = new KinoketteMapper();
		}

		return kinoketteMapper;
	}

}
