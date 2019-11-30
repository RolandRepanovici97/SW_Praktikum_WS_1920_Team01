package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
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
public class SpielzeitMapper extends OwnedBusinessObjectMapper {

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



	public Vector<Spielzeit> findAllSpielzeit() {

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
	public Spielzeit findByID(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM `spielzeit` LEFT JOIN `ownedbusinessobject` ON `spielzeit`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE spielzeit_id = \" + id + \" ORDER BY `spielzeit_id`");

			if (rs.next()) {
				Spielzeit sz = new Spielzeit();
				sz.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				sz.setID(rs.getInt("spielzeit_id"));
				sz.setOwnerID(rs.getInt("owner_id"));
				sz.setZeitpunkt(rs.getDate("Uhrzeit"));

				return sz;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Spielzeit insert(Spielzeit spielzeit) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(spielzeit_id) AS `maxid` FROM `spielzeit`");
			if (rs.next()) {
				spielzeit.setID(rs.getInt("maxid") + 1);
			}
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO `spielplan` (`spielzeit_id`, `bo_id`, `Datum` , `Uhrzeit`) VALUES (?, ?, ?, ?) ");
			pstmt.setInt(1, spielzeit.getID());
			pstmt.setInt(2, obo.getID());
			pstmt.setDate(3,dc.convertJavaDateToSQLDate(spielzeit.getZeitpunkt()));
			pstmt.setTime(4, dc.convertJavaDateToSQLTime(spielzeit.getZeitpunkt()));
			pstmt.executeUpdate();
			super.insert(spielzeit);
			return spielzeit;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}}


	public Spielzeit update(Spielzeit spielzeit) {

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con
					.prepareStatement("UPDATE `spielzeit` SET `Datum` = ?, `Uhrzeit` = ? WHERE `spielzeit_id` = ?");
			pstmt.setDate(1, dc.convertJavaDateToSQLDate(spielzeit.getZeitpunkt()));
			pstmt.setTime(2, dc.convertJavaDateToSQLTime(spielzeit.getZeitpunkt()));
			pstmt.executeUpdate();
			return spielzeit;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

	}

	public void delete (Spielzeit spielzeit) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `spielzeit` WHERE (`spielzeit_id` = " + spielzeit.getID() + ")");
			super.delete(spielzeit);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector<Spielzeit> findSpielzeitenBySpielplan (Spielplan spielplan) {

		// Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();
		// Neuen Vector instantiieren, in welchem Film-Objekte gespeichert werden.

		Vector<Spielzeit> vectorspielzeit = new Vector<Spielzeit>();
		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM `Spielplan_Spielzeit`LEFT JOIN `spielzeit`ON `Spielplan_Spielzeit`.`spielzeit_id`= `Spielzeit`.`spielzeit_id` LEFT JOIN `ownedbusinessobject` ON `spielzeit`.`bo_id` = `ownedbusinessobject`.`bo_id`  WHERE (`spielplan_id` = " + spielplan.getID() + " ) ORDER BY `spielzeit_id`");

			/*
			 * Befüllen des result sets
			 */
			while (rs.next()) {
				// Es werden für jedes Umfrageeintrag-Objekt die nötigen Attribute gesetzt
				Spielzeit sz = new Spielzeit();
				sz.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				sz.setID(rs.getInt("spielzeit_id"));
				sz.setID(rs.getInt("film_id"));
				sz.setID(rs.getInt("bo_id"));
				sz.setZeitpunkt( dc.convertDatumUndUhrzeitToDate(rs.getDate("Datum"),rs.getTime("Uhrzeit")));

				vectorspielzeit.add(sz);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vectorspielzeit;
	}



}


