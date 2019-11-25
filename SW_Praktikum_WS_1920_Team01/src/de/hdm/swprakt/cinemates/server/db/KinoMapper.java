package de.hdm.swprakt.cinemates.server.db;

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



}
