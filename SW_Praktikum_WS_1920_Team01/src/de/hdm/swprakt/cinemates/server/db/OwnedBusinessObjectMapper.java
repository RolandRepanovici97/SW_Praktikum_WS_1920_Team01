package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.OwnedBusinessObject;


public class OwnedBusinessObjectMapper {
	
	private static OwnedBusinessObjectMapper ownedBusinessObjectMapper = null;
	private static DateConverter dc = new DateConverter();
	
	
	protected OwnedBusinessObjectMapper() {
		
	}
	
	public static OwnedBusinessObjectMapper ownedBusinessObjectMapper() {
		
		if(ownedBusinessObjectMapper == null) {
			ownedBusinessObjectMapper = new OwnedBusinessObjectMapper();
		}
		
			return ownedBusinessObjectMapper;
		
	}
	
	public Vector<OwnedBusinessObject> findAll() {
		
		Connection con = DBConnection.connection();
		Vector<OwnedBusinessObject> obos = new Vector<OwnedBusinessObject>();
		
		try {
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `ownedbusinessobject`");
		
		
		while(rs.next()) {
			OwnedBusinessObject obo = new OwnedBusinessObject();
			obo.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
			obo.setID(rs.getInt("bo_id"));
			obo.setOwnerID(rs.getInt("owner_id"));
			obos.add(obo);
		}
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return obos;
	}
	
	public Vector<OwnedBusinessObject> findByOwner(Nutzer nutzer){
		
		Connection con = DBConnection.connection();
		Vector<OwnedBusinessObject> obos = new Vector<OwnedBusinessObject>();
		
		try {
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `ownedbusinessobject` WHERE (`owner_id` ) " + nutzer.getID() + ")");
		
		
		while(rs.next()) {
			OwnedBusinessObject obo = new OwnedBusinessObject();
			obo.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
			obo.setID(rs.getInt("bo_id"));
			obo.setOwnerID(rs.getInt("owner_id"));
			obos.add(obo);
		}
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return obos;
	}
	
	public OwnedBusinessObject findByID(int id) {
		
		Connection con = DBConnection.connection();
		
		
		try {
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `ownedbusinessobject` WHERE `bo_id` = + " + id + " ORDER BY `bo_id`");
		
		
		if(rs.next()) {
			OwnedBusinessObject obo = new OwnedBusinessObject();
			obo.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
			obo.setID(rs.getInt("bo_id"));
			obo.setOwnerID(rs.getInt("owner_id"));
		
			return obo;
		}
		}
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
	}
	
	public int insert (OwnedBusinessObject obo, Connection con) throws SQLException {
		
		int bo_id = 0;
		
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(bo_id) AS `maxid` FROM `ownedbusinessobject`");

			if (rs.next()) {
				bo_id = rs.getInt("maxid") + 1;
			}	
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `ownedbusinessobject` (`bo_id`, `owner_id`, `Erstellungszeitpunkt`) VALUES (?, ?, ?) ");
			pstmt.setInt(1, bo_id);
			pstmt.setInt(2, obo.getOwnerID());
			pstmt.setTimestamp(3, dc.convertJavaDateToSqlTimestamp(obo.getErstellungszeitpunkt()));
			pstmt.executeUpdate();
		
		return bo_id;
	
	}
	
	/** Bin unsicher, ob diese Methode benötigt wird
	 * 
	public OwnedBusinessObject update (OwnedBusinessObject obo) {
		
		Connection con = DBConnection.connection();

		try {
			
			PreparedStatement pstmt = con.prepareStatement("UPDATE `ownedbusinessobject` SET `owner_id` = ?, `Erstellungszeitpunkt` = ? WHERE `bo_id` = ?");
			pstmt.setInt(1, obo.getOwnerID());
			pstmt.setTimestamp(2, dc.aktuellerTimestamp() );
			pstmt.setInt(3, obo.getID());
			pstmt.executeUpdate();
			return obo;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	*/
	public void delete (int bo_id, Connection con) throws SQLException {
		
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `ownedbusinessobject` WHERE `bo_id` = " + bo_id);

	}
		
		
	
	public int getOwnerIDOf (OwnedBusinessObject obo) {
		
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

