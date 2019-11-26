/**
 * 
 */
package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * @author
 *
 */
public class GruppeMapper {

	private static GruppeMapper gruppeMapper = null;
	private static DateConverter dc = new DateConverter();
	
	protected GruppeMapper() {
		
	}
	
	public static GruppeMapper gruppeMapper() {
		
		if(gruppeMapper == null) {
			gruppeMapper = new GruppeMapper();
		}
		
			return gruppeMapper;
		
	}
	
	public Vector<Gruppe> findAll(){
		
		Connection con = DBConnection.connection();
		Vector<Gruppe> gruppen = new Vector<Gruppe>();
		
		
		try {
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `gruppe` LEFT JOIN `ownedbusinessobject` ON `gruppe`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `gruppen_id`;");
		
		
		while(rs.next()) {
			Gruppe g = new Gruppe();
			g.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
			g.setID(rs.getInt("gruppen_id"));
			g.setOwnerID(rs.getInt("owner_id"));
			g.setGruppenname(rs.getString("Gruppenname"));
			gruppen.add(g);
		}
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return gruppen;
	}
	
	public Gruppe findByID(int id) {
		
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `gruppe` LEFT JOIN `ownedbusinessobject` ON `gruppe`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE gruppen_id = " + id + " ORDER BY `gruppen_id`");

			if (rs.next()) {
				Gruppe g = new Gruppe();
				g.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				g.setID(rs.getInt("gruppen_id"));
				g.setOwnerID(rs.getInt("owner_id"));
				g.setGruppenname(rs.getString("Gruppenname"));
				
				return g;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Gruppe findByGruppenname (String gruppenname) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `gruppe` LEFT JOIN `ownedbusinessobject` ON `gruppe`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE `Gruppenname` = '" + gruppenname + "'");

			if (rs.next()) {
				Gruppe g = new Gruppe();
				g.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				g.setID(rs.getInt("gruppen_id"));
				g.setOwnerID(rs.getInt("owner_id"));
				g.setGruppenname(rs.getString("Gruppenname"));
				
				return g;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public Gruppe insert (Gruppe gruppe, OwnedBusinessObject obo) {
		
		Connection con = DBConnection.connection();
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(gruppen_id) AS `maxid` FROM `gruppe`");

			if (rs.next()) {
				gruppe.setID(rs.getInt("maxid") + 1);
			}	
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `gruppe` (`gruppen_id`, `bo_id`, `Gruppenname`) VALUES (?, ?, ?) ");
			pstmt.setInt(1, gruppe.getID());
			pstmt.setInt(2, obo.getID());
			pstmt.setString(3, gruppe.getGruppenname());
			pstmt.executeUpdate();
			gruppe.setErstellungszeitpunkt(obo.getErstellungszeitpunkt());
			gruppe.setOwnerID(obo.getOwnerID());
			return gruppe;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public Gruppe update (Gruppe gruppe) {
		
		Connection con = DBConnection.connection();

		try {
			
			PreparedStatement pstmt = con.prepareStatement("UPDATE `gruppe` SET `Gruppenname` = ? WHERE `gruppen_id` = ?");
			pstmt.setString(1, gruppe.getGruppenname());
			pstmt.setInt(2, gruppe.getID());
			pstmt.executeUpdate();
			return gruppe;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void delete (Gruppe gruppe) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `gruppe` WHERE `gruppen_id` = " + gruppe.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertGruppenzugehörigkeit (int userid, int gruppenid) {
		
		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `gruppe_mitglied` (`gruppen_id`, `user_id`) VALUES (?, ?)");

			pstmt.setInt(1, gruppenid);
			pstmt.setInt(2, userid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteGruppenzugehörigkeit (int userid, int gruppenid) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `gruppe_mitglied` WHERE (`user_id` = " + userid + " AND `usergroup_id` = " + gruppenid);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteGruppenmitglieder (Gruppe gruppe) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `gruppe_mitglied` WHERE `gruppen_id` = " + gruppe.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Gruppe> getGruppenOf (Nutzer nutzer){
		
		Connection con = DBConnection.connection();
		Vector<Gruppe> gruppen = new Vector<Gruppe>();
		
		try {
			Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `gruppe_mitglied` LEFT JOIN `gruppe` ON `gruppe_mitglied`.`gruppen_id` = `gruppe`.`gruppen_id` LEFT JOIN `ownedbusinessobject` ON `gruppe`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE `user_id` = " + nutzer.getID());
		
		while (rs.next()) {
			Gruppe g = new Gruppe();
			g.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
			g.setID(rs.getInt("gruppen_id"));
			g.setOwnerID(rs.getInt("owner_id"));
			g.setGruppenname(rs.getString("Gruppenname"));
			gruppen.add(g);
		}
		}
		catch(SQLException e) {
			
		}
		
		return gruppen;
		
	}
	
	public Vector<Gruppe> getGruppenOf (Umfrage umfrage){
		
		Connection con = DBConnection.connection();
		Vector<Gruppe> gruppen = new Vector<Gruppe>();
		
		try {
		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `umfrage_gruppe` LEFT JOIN `gruppe` ON `umfrage_gruppe`.`gruppen_id` = `gruppe`.`gruppen_id` LEFT JOIN `ownedbusinessobject` ON `gruppe`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE `umfrage_id` = " + umfrage.getID());
			

			while (rs.next()) {
				Gruppe g = new Gruppe();
				g.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				g.setID(rs.getInt("gruppen_id"));
				g.setOwnerID(rs.getInt("owner_id"));
				g.setGruppenname(rs.getString("Gruppenname"));
				gruppen.add(g);
			}
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return gruppen;
		
	}
}
