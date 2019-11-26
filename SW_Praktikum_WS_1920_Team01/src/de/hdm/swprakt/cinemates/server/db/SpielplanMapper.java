package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;


/**
 * Diese Mapperklasse bildet <code>Spielplan</code> Objekte auf eine relationale
 * Datenbank ab. Sie beinhalte Methoden, um Spielpläne anzeigen, anlegen,
 * editieren und löschen zu können. Es können hier sowohl Objekte der Klasse
 * <code> Spielplan </code> in Datenbankstrukturen, als auch Datenbankstrukturen
 * in Objekte der Klasse <code> Spielplan </code> umgewandelt werden.
 * 
 * @author Ömer Degirmenci
 * @version 1.0
 *
 */

public class SpielplanMapper {

	/**
	 * Die Klasse <code>SpielplanMapper</code> wird wie jede andere Mapperklasse nur
	 * ein einziges mal instantiiert. Die Variable spielplanMapper speichert diese
	 * Instanz. Wir stellen die einmalige Initalisierung durch den Bezeichner
	 * <code>static</code> sicher.
	 */

	private static SpielplanMapper spielplanMapper = null;
	private static DateConverter dc = new DateConverter();

	/**
	 * Ein geschützter Konstruktor verhindert die Möglichkeit, mit <code>new</code>
	 * neue Instanzen der Klasse <code>SpielplanMapper</code> zu erzeugen.
	 * 
	 */
	protected SpielplanMapper() {

	}

	/**
	 * Mithilfe dieser statischen Methode kann die einzige Instanz der Klasse
	 * <code>SpielplanMapper</code> erzeugt werden. Die Methode beinhaltet die
	 * Prüfung, ob bereits ein SpielplanMapper-Objekt erzeugt ist, dadurch wird
	 * sichergestellt, dass immer nur ein Objekt dieser Klasse erzeugt ist. Ist
	 * bereits ein <code>SpielplanMapper</code> - Objekt erzeugt worden, so geben
	 * wir die Referenz auf dieses zurück. (Singleton Objekt)
	 * 
	 * @return das einzige <code>SpielplanMapper</code> - Objekt
	 */

	public static SpielplanMapper spielplanMapper() {
		if (spielplanMapper == null) {
			spielplanMapper = new SpielplanMapper();
		}

		return spielplanMapper;
	}

	public Vector<Spielplan> findAll() {

		Connection con = DBConnection.connection();
		Vector<Spielplan> spielplan = new Vector<Spielplan>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM `spielplan`LEFT JOIN `ownedbusinessobject` ON `spielplan`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `spielplan_id`;");

			while (rs.next()) {
				Spielplan sp = new Spielplan();
				sp.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				sp.setID(rs.getInt("spielplan_id"));
				sp.setOwnerID(rs.getInt("owner_id"));
				sp.setSpielplanname(rs.getString("Spielplanname"));

			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return spielplan;

	}

	public Spielplan findByID(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM `spielplan` LEFT JOIN `ownedbusinessobject` ON `spielplan`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE spielplan_id = \" + id + \" ORDER BY `spielplan_id`");

			if (rs.next()) {
				Spielplan sp = new Spielplan();
				sp.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				sp.setID(rs.getInt("spielplan_id"));
				sp.setOwnerID(rs.getInt("owner_id"));
				sp.setSpielplanname(rs.getString("Spielplanname"));

				return sp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Spielplan insert(Spielplan spielplan, OwnedBusinessObject obo) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(spielplan_id) AS `maxid` FROM `spielplan`");

			if (rs.next()) {
				spielplan.setID(rs.getInt("maxid") + 1);
			}

			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO `spielplan` (`spielplan_id`, `bo_id`, `Spielplanname`) VALUES (?, ?, ?) ");
			pstmt.setInt(1, spielplan.getID());
			pstmt.setInt(2, obo.getID());
			pstmt.setString(3, spielplan.getSpielplanname());

			pstmt.executeUpdate();
			return spielplan;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
