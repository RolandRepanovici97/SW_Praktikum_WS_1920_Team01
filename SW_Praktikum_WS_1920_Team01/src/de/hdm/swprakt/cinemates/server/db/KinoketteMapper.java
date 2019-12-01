package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import de.hdm.swprakt.cinemates.shared.bo.Kinokette;

import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;

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

public class KinoketteMapper extends OwnedBusinessObjectMapper {

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

	/**
	 * Suchen aller Objekte der Klasse <code> Kinokette </code>
	 * @return Vector <Kinokette>, welche alle Kinos unterschiedlicher Standorte beinhaltet
	 */

	public Vector<Kinokette> findAllKinokette(){

		Connection con = DBConnection.connection();
		Vector<Kinokette> kinokette = new Vector<Kinokette>();


		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM kinokette LEFT JOIN ownedbusinessobject ON kinokette.bo_id = ownedbusinessobject.bo_id ORDER BY kinokette_id;");
			//Erstellungszeitpunkt?			

			while(rs.next()) {
				Kinokette k = new Kinokette();
				k.setID(rs.getInt("kinokette_id"));
				k.setOwnerID(rs.getInt("bo_id"));
				k.setKinokettenname(rs.getString("Kinokettenname"));
				k.setBeschreibung("Beschreibung");
				kinokette.add(k);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

		return kinokette;

	}


	/**
	 * Suchen einer Kinokette mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Objekt der Klasse <code>Kinokette</code>zurückgegeben. 
	 * 
	 * @param id (Siehe Primärschlüsselattribut der Tabelle Kinokette in der DB)
	 * @return Kinokette-Objekt, das dem übergebenen Schlüssel entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */

	public Kinokette findByID(int id) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `kinokette` LEFT JOIN `ownedbusinessobject` ON `kinokette`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`kinokette_id` = " + id + ") ORDER BY `kinokette_id`");
			//Muss man alle Attribute angeben?
			if (rs.next()) {
				Kinokette k = new Kinokette();
				k.setID(rs.getInt("kinokette_id"));
				k.setOwnerID(rs.getInt("owner_id"));
				k.setKinokettenname(rs.getString("Kinokettenname"));
				k.setBeschreibung(rs.getString("Beschreibung"));

				return k;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Suchen einer Kinokette mithilfe seines Names. Der Name ist eindeutig, es werden mehrere Kinos
	 * der Klasse <code>Kinokette</code>zurückgegeben. 
	 * 
	 * @return Kinokette-Objekt, das dem übergebenen Namen entspricht. 
	 * Ist kein entsprechender Tupel in der DB vorhanden, so geben wir null zurück.
	 */

	public Kinokette findByKinokettename(String kinokettenname) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM kinokette LEFT JOIN ownedbusinessobject ON kinokette.bo_id = ownedbusinessobject.bo_id WHERE kinokette Kinokettenname = " + kinokettenname + " ORDER BY Kinokettenname");

			if (rs.next()) {
				Kinokette k = new Kinokette();
				k.setID(rs.getInt("kinokette_id"));
				k.setOwnerID(rs.getInt("owner_id"));
				k.setKinokettenname(rs.getString("Kinokettenname"));
				k.setBeschreibung(rs.getString("Beschreibung"));

				return k;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return null;


	}




	/**
	 * Einfügen einer Kinokette in die Datenbank.
	 * @return Ein Objekt der Klasse <Kinokette>
	 */

	public Kinokette insert(Kinokette kinokette) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(kinokette_id) AS maxid FROM kinokette");

			if (rs.next()) {
				kinokette.setID(rs.getInt("maxid") + 1);
			}
			//bo_id??
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO kino (kinokette_id, kinokettenname, beschreibung) VALUES (?, ?, ?) ");
			pstmt.setInt(1, kinokette.getID());
			pstmt.setString(2, kinokette.getKinokettenname());
			pstmt.setString(3, kinokette.getBeschreibung());
			super.insert(kinokette);
			return kinokette;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Aktualisieren einer Kinokette in der Datenbank.
	 * @return Ein (überarbeitetes) Objekt der Klasse <Kinokette>
	 */

	public Kinokette update (Kinokette kinokette) {

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("UPDATE kinokette SET kinokettenname = ?, beschreibung = ? WHERE kinokette_id = ?");
			pstmt.setString(1, kinokette.getKinokettenname());
			pstmt.setString(2, kinokette.getBeschreibung());
			pstmt.setInt(3, kinokette.getID());
			pstmt.executeUpdate();
			return kinokette;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}		

	/**
	 * Löschen einer Kinokette in der Datenbank.
	 */
	public void delete (Kinokette kinokette) {

		//Verbindung zur Datenbank aufbauen.

		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("DELETE FROM kinokette WHERE kinokette_id=" + kinokette.getID());
			super.delete(kinokette);

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}


}
