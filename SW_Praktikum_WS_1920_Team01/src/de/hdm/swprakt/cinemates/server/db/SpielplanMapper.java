package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
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

public class SpielplanMapper extends OwnedBusinessObjectMapper {

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

	public Vector<Spielplan> findAllSpielplan() {

		Connection con = DBConnection.connection();
		Vector<Spielplan> spielplan = new Vector<Spielplan>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `spielplan` LEFT JOIN `ownedbusinessobject` ON `spielplan`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `spielplan_id`;");

			while (rs.next()) {
				Spielplan sp = new Spielplan();
				sp.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				sp.setID(rs.getInt("spielplan_id"));
				sp.setOwnerID(rs.getInt("owner_id"));
				sp.setSpielplanname(rs.getString("Spielplanname"));
				spielplan.add(sp);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return spielplan;

	}
	
	/**
	 * Suchen eines Spielplans mithilfe seiner ID. Die ID ist eindeutig, es wird genau
	 * ein Spielplan der Klasse <code >SPielplan</code>zurückgegeben. 
	 */

	public Spielplan findByID(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `spielplan`  LEFT JOIN `ownedbusinessobject` ON `spielplan`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`spielplan_id` = " + id + ") ORDER BY `spielplan_id`");

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

	public Spielplan insert(Spielplan spielplan) {

		Connection con = DBConnection.connection();

		try {
			con.setAutoCommit(false);
			
			int bo_id = super.insert(spielplan, con);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(spielplan_id) AS `maxid` FROM `spielplan`");

			if (rs.next()) {
				spielplan.setID(rs.getInt("maxid") + 1);
			}


				PreparedStatement pstmt = con.prepareStatement("INSERT INTO `spielplan` (`spielplan_id`, `bo_id`, `Spielplanname`) VALUES (?, ?, ?) ");
				pstmt.setInt(1, spielplan.getID());
				pstmt.setInt(2, bo_id);
				pstmt.setString(3, spielplan.getSpielplanname());

				pstmt.executeUpdate();
				
				con.commit();
			

		} 
		
		catch(SQLException e) {
			e.printStackTrace();
			if (con != null) {
	            try {
	               // System.err.print("Transaktion wird nicht ausgeführt");
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
		return spielplan;
	}

	
	/**Aktualisieren eines Spielplans in der Datenbank. 
	 * @param spielplan
	 * @return Ein aktualisiertes Objekt der Klasse <Spielplan>
	 */
	public Spielplan update(Spielplan spielplan) {

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE `spielplan` SET `Spielplanname` = ? WHERE `spielplan_id` = ?");

			pstmt.setString(1, spielplan.getSpielplanname());
			pstmt.setInt(2, spielplan.getID());
			pstmt.executeUpdate();
			
			return spielplan;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/** Löschen  eines Spielplans in der Datenbank. 
	 * @param spielplan
	 * 
	 */

	public void delete (Spielplan spielplan) {

		Connection con = DBConnection.connection();

		try {
			
			con.setAutoCommit(false);
			
			int bo_id = findBoIDOf(spielplan);

			super.delete(bo_id, con);

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `spielplan` WHERE (`spielplan_id` = " + spielplan.getID() + ")");
			
			con.commit();

		} catch(SQLException e) {
			e.printStackTrace();
			if (con != null) {
	            try {
	               // System.err.print("Transaktion wird nicht ausgeführt");
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
	
	public void deleteSpielzeitenFromSpielplan (Spielplan spielplan) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `spielplan_spielzeit` WHERE `spielplan_id` = " + spielplan.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private int findBoIDOf (Spielplan spielplan) throws SQLException {
		
		Connection con = DBConnection.connection();
		int bo_id = 0;

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `bo_id` FROM `spielplan` WHERE (`spielplan_id` = " +  spielplan.getID() + ")");
			
			if(rs.next()) {
				bo_id = rs.getInt("bo_id");
			}
			
		
		return bo_id;
	}
	


}
