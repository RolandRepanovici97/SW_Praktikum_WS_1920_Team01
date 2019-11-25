package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

public class NutzerMapper {
	
	private static NutzerMapper nutzerMapper = null;
	private static TimestampManager tsm = new TimestampManager();
	
	
	protected NutzerMapper() {
		
	}
	
	public static NutzerMapper nutzerMapper() {
		
		if(nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		
			return nutzerMapper;
		
	}
	
	public Vector<Nutzer> findAll(){
		
		Connection con = DBConnection.connection();
		Vector<Nutzer> nutzer = new Vector<Nutzer>();
		
		
		try {
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `user_id`;");
		
		
		while(rs.next()) {
			Nutzer n = new Nutzer();
			n.setErstellungszeitpunkt(tsm.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
			n.setID(rs.getInt("user_id"));
			n.setOwnerID(rs.getInt("owner_id"));
			n.setEmail(rs.getString("Email"));
			n.setNutzername(rs.getString("Nutzername"));
			nutzer.add(n);
		}
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return nutzer;
		
	}
	
	public Nutzer findByID(int id) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE user_id = " + id + " ORDER BY `user_id`");

			if (rs.next()) {
				Nutzer n = new Nutzer();
				n.setErstellungszeitpunkt(tsm.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				n.setID(rs.getInt("user_id"));
				n.setOwnerID(rs.getInt("owner_id"));
				n.setEmail(rs.getString("Email"));
				n.setNutzername(rs.getString("Nutzername"));
				
				return n;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	


	public Nutzer findByEmail(String email) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE Email = '" + email + "' ORDER BY `email`");

			if (rs.next()) {
				Nutzer n = new Nutzer();
				n.setErstellungszeitpunkt(tsm.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				n.setID(rs.getInt("user_id"));
				n.setOwnerID(rs.getInt("owner_id"));
				n.setEmail(rs.getString("Email"));
				n.setNutzername(rs.getString("Nutzername"));
				
				return n;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
		
		
		
	}
	

	public Nutzer insert (Nutzer nutzer, OwnedBusinessObject obo) {
	
		Connection con = DBConnection.connection();
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(user_id) AS `maxid` FROM `nutzer`");

			if (rs.next()) {
				nutzer.setID(rs.getInt("maxid") + 1);
			}	
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `nutzer` (`user_id`, `bo_id`, `Email`, `Nutzername`) VALUES (?, ?, ?, ?) ");
			pstmt.setInt(1, nutzer.getID());
			pstmt.setInt(2, obo.getID());
			pstmt.setString(3, nutzer.getEmail());
			pstmt.setString(4, nutzer.getNutzername());
			pstmt.executeUpdate();
			return nutzer;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	
	}
	
	public Nutzer update (Nutzer nutzer) {
		
		Connection con = DBConnection.connection();

		try {
			
			PreparedStatement pstmt = con.prepareStatement("UPDATE `nutzer` SET `Email` = ?, `Nutzername` = ? WHERE `user_id` = ?");
			pstmt.setString(1, nutzer.getEmail());
			pstmt.setString(2, nutzer.getNutzername());
			pstmt.setInt(3, nutzer.getID());
			pstmt.executeUpdate();
			return nutzer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete (Nutzer nutzer) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `nutzer` WHERE id = " + nutzer.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGruppenzugehörigkeit(int nutzerid) {
		
	}
	
	public Vector<Nutzer> getGruppenmitgliederOf (Gruppe gruppe){
		
		return null;
		
	}
	
	public Vector<Nutzer> getUmfragemitgliederOf (Umfrage umfrage){
		
		return null;
		
	}
	
	public Nutzer getKinobetreiberOf (Kinokette kinokette) {
		return null;
		
	}
	
	public Nutzer getGruppenadminOf (Gruppe gruppe) {
		
		return null;
		
	}
	
	public Nutzer getUmfrageerstellerOf (Umfrage umfrage) {
		
		return null;
		
	}
	
	public Nutzer getVotumgeberOf (Votum votum) {
		
		return null;
		
	}
	
	public Nutzer getSpielplanerstellerOf (Spielplan spielplan) {
		
		return null;
		
	}
	
	
	public Nutzer getSpielzeiterstellerOf (Spielzeit spielzeit) {
		
		return null;
		
	}
	
	

 }
