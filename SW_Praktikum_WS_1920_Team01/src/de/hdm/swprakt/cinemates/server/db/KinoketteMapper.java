package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
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
	private static DateConverter dc = new DateConverter();

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

	public Vector<Kinokette> findAllKinoketten(){

		Connection con = DBConnection.connection();
		Vector<Kinokette> kinoketten = new Vector<Kinokette>();


		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `kinokette` LEFT JOIN `ownedbusinessobject` ON `kinokette`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `kinokette_id`;");		

			while(rs.next()) {
				Kinokette k = new Kinokette();
				k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				k.setID(rs.getInt("kinokette_id"));
				k.setOwnerID(rs.getInt("owner_id"));
				k.setKinokettenname(rs.getString("Kinokettenname"));
				k.setBeschreibung(rs.getString("Beschreibung"));
				kinoketten.add(k);
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

		return kinoketten;

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `kinokette` LEFT JOIN `ownedbusinessobject` ON `kinokette`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`kinokette_id` = " + id + ")");
	
			if (rs.next()) {
				Kinokette k = new Kinokette();
				k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
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

	public Kinokette findByKinokettenname(String kinokettenname) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `kinokette` LEFT JOIN `ownedbusinessobject` ON `kinokette`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`kinokette`.`Kinokettenname` = '" + kinokettenname + "');");

			if (rs.next()) {
				Kinokette k = new Kinokette();
				k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
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
			
			con.setAutoCommit(false);
			
			int bo_id = super.insert(kinokette, con);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(kinokette_id) AS `maxid` FROM `kinokette`");

			if (rs.next()) {
				kinokette.setID(rs.getInt("maxid") + 1);
			}

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `kinokette`(`kinokette_id`, `bo_id`, `Kinokettenname`, `Beschreibung`) VALUES (?, ?, ?, ?) ");
			pstmt.setInt(1, kinokette.getID());
			pstmt.setInt(2, bo_id);
			pstmt.setString(3, kinokette.getKinokettenname());
			pstmt.setString(4, kinokette.getBeschreibung());
			pstmt.executeUpdate();

			con.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			if (con != null) {
	            try {
	             //  System.err.print("Transaktion wird nicht ausgeführt");
	                con.rollback();
	            } catch(SQLException exc) {
	            	exc.printStackTrace();
	            }
			
			return null;
		}
			
	} finally {
		
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		return kinokette;
	}
	

	/**
	 * Aktualisieren einer Kinokette in der Datenbank.
	 * @return Ein (überarbeitetes) Objekt der Klasse <Kinokette>
	 */

	public Kinokette update (Kinokette kinokette) {

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("UPDATE `kinokette` SET `Kinokettenname` = ?, `Beschreibung` = ? WHERE (`kinokette_id` = ?)");
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
			
			con.setAutoCommit(false);
			
			int bo_id = findBoIDOf(kinokette);

			super.delete(bo_id, con);
			
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `kinokette` WHERE (`kinokette_id` = " + kinokette.getID() + ")");
			
			con.commit();

		}catch(SQLException e) {
			e.printStackTrace();
			if (con != null) {
	            try {
	             //  System.err.print("Transaktion wird nicht ausgeführt");
	                con.rollback();
	            } catch(SQLException exc) {
	            	exc.printStackTrace();
	            }
			
		}
			
	} finally {
		
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	}
	
	
	
	private int findBoIDOf (Kinokette kinokette) throws SQLException {
		
		Connection con = DBConnection.connection();
		int bo_id = 0;
		
		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `bo_id` FROM `kinokette` WHERE (`kinokette_id` = " +  kinokette.getID() + ")");
			
			if(rs.next()) {
				bo_id = rs.getInt("bo_id");
			}
			
		
		
		return bo_id;
	}

//	public Kinokette findKinoketteByOwner(Nutzer nutzer) {
//
//		Connection con = DBConnection.connection();
//
//		try {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM `kinokette` LEFT JOIN `ownedbusinessobject` ON `kinokette`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`owner_id` = " + id + ")");
//	
//			if (rs.next()) {
//				Kinokette k = new Kinokette();
//				k.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
//				k.setID(rs.getInt("kinokette_id"));
//				k.setOwnerID(rs.getInt("owner_id"));
//				k.setKinokettenname(rs.getString("Kinokettenname"));
//				k.setBeschreibung(rs.getString("Beschreibung"));
//
//				return k;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//	}

}
