package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;

/**
 * Diese Mapperklasse bildet <code>Spielzeit</code> Objekte auf eine
 * relationale Datenbank ab. 
 * Sie beinhalte Methoden, um Filme anzeigen, anlegen, editieren und löschen zu können.
 * Es können hier sowohl Objekte der Klasse <code> Spielzeit </code> in Datenbankstrukturen,
 * als auch Datenbankstrukturen in Objekte der Klasse <code> Spielzeit</code>  umgewandelt werden.
 * 
 * @author Ömer Degirmenci 
 * @version 1.0
 *
 */
public class SpielzeitMapper {
	
	/**
	 * Die Klasse <code>SpielzeitMapper</code> wird wie jede andere Mapperklasse nur ein
	 * einziges mal instantiiert. Die Variable spielzeitMapper speichert diese Instanz.
	 * Wir stellen die einmalige Initalisierung durch den Bezeichner
	 * <code>static</code> sicher.
	 */
	
	private static SpielzeitMapper spielzeitMapper = null;
	private static DateConverter dc = new DateConverter();

	/**
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>SpielzeitMapper</code> zu erzeugen.
	 * 
	 */
	protected SpielzeitMapper() {

	}
	
	/**
	 * Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse
	 * <code>SpielzeitMapper</code> erzeugt werden. Die Methode beinhaltet die Prüfung,
	 * ob bereits ein SpielzeitMapper-Objekt erzeugt ist, dadurch wird sichergestellt,
	 * dass immer nur ein Objekt dieser Klasse erzeugt ist. Ist bereits ein
	 * <code>SpielzeitMapper</code> - Objekt erzeugt worden, so geben wir die Referenz
	 * auf dieses zurück. (Singleton Objekt)
	 * 
	 * @return das einzige <code>SpielzeitMapper</code> - Objekt
	 */

	public static SpielzeitMapper spielzeitMapper() {
		if (spielzeitMapper == null) {
			spielzeitMapper = new SpielzeitMapper();
		}

		return spielzeitMapper;
	}
	
	
	
	public Vector<Spielzeit> findAll() {
		
		Connection con = DBConnection.connection();
		Vector<Spielzeit> spielzeit = new Vector<Spielzeit>();
		
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `spielzeit`LEFT JOIN `ownedbusinessobject` ON `spielzeit`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `spielzeit_id`;");
			
			while(rs.next()) {
				Spielzeit sz = new Spielzeit();
				sz.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				sz.setID(rs.getInt("spielzeit_id"));
				sz.setZeitpunkt(rs.getDate("Uhrzeit"));
				spielzeit.add(sz);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return spielzeit;
		
		
		
	}
}

