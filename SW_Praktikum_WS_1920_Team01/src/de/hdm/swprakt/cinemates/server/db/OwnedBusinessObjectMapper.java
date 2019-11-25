package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;


public class OwnedBusinessObjectMapper {
	
	private static OwnedBusinessObjectMapper ownedBusinessObjectMapper = null;
	private static TimestampManager tsm = new TimestampManager();
	
	
	protected OwnedBusinessObjectMapper() {
		
	}
	
	public static OwnedBusinessObjectMapper ownedBusinessObjectMapper() {
		
		if(ownedBusinessObjectMapper == null) {
			ownedBusinessObjectMapper = new OwnedBusinessObjectMapper();
		}
		
			return ownedBusinessObjectMapper;
		
	}
	
	public OwnedBusinessObject insert (OwnedBusinessObject obo) {
		
		Connection con = DBConnection.connection();
		
		try {
			
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(bo_id) AS `maxid` FROM `ownedbusinessobject`");

			if (rs.next()) {
				obo.setID(rs.getInt("maxid") + 1);
			}	
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `ownedbusinessobject` (`bo_id`, `owner_id`, `Erstellungszeitpunkt`) VALUES (?, ?, ?) ");
			pstmt.setInt(1, obo.getID());
			pstmt.setInt(2, obo.getOwnerID());
			pstmt.setTimestamp(3, tsm.aktuellerTimestamp());
			obo.setErstellungszeitpunkt(tsm.convertTimestampToDate(tsm.aktuellerTimestamp()));
			pstmt.executeUpdate();
			return obo;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return obo;
	
	}
	
	public OwnedBusinessObject update (OwnedBusinessObject obo) {
		
		Connection con = DBConnection.connection();

		try {
			
			PreparedStatement pstmt = con.prepareStatement("UPDATE `ownedbusinessobject` SET `owner_id` = ?, `Erstellungszeitpunkt` = ? WHERE `bo_id` = ?");
			pstmt.setInt(1, obo.getOwnerID());
			pstmt.setTimestamp(2, tsm.aktuellerTimestamp() );
			pstmt.setInt(3, obo.getID());
			pstmt.executeUpdate();
			return obo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete (OwnedBusinessObject obo) {
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `ownedbusinessobject` WHERE `bo_id` = " + obo.getID());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
	public int getOwnerOf (OwnedBusinessObject obo) {
		
		Connection con = DBConnection.connection();
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from `ownedbusinessobject` WHERE `bo_id` = " + obo.getID());
			
			if (rs.next()) {
				return rs.getInt("owner_id");
			}	
			
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	

}

