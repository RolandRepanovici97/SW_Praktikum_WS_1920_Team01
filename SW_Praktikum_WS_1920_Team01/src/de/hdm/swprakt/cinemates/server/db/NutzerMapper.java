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

public class NutzerMapper extends OwnedBusinessObjectMapper{

	private static NutzerMapper nutzerMapper = null;
	private static DateConverter dc = new DateConverter();


	protected NutzerMapper() {

	}

	public static NutzerMapper nutzerMapper() {

		if(nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}

		return nutzerMapper;

	}


	public Vector <Nutzer> findAllNutzer(){

		Connection con = DBConnection.connection();
		Vector<Nutzer> nutzer = new Vector<Nutzer>();


		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` ORDER BY `user_id`;");


			while(rs.next()) {
				Nutzer n = new Nutzer();
				n.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`user_id` = " + id + ")ORDER BY `user_id`");

			if (rs.next()) {
				Nutzer n = new Nutzer();
				n.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`Email` = '" + email + "') ORDER BY `email`");

			if (rs.next()) {
				Nutzer n = new Nutzer();
				n.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
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


	public Nutzer insert (Nutzer nutzer) {

		Connection con = DBConnection.connection();

		try {
			con.setAutoCommit(false);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(user_id) AS `maxid` FROM `nutzer`");

			if (rs.next()) {
				nutzer.setID(rs.getInt("maxid") + 1);
				nutzer.setOwnerID(nutzer.getID());
			}	
			
			int bo_id = super.insert(nutzer, con);

			PreparedStatement pstmt = con.prepareStatement("INSERT INTO `nutzer` (`user_id`,  `bo_id`, `Email`, `Nutzername`) VALUES (?, ?, ?, ?) ");
			pstmt.setInt(1, nutzer.getID());
			pstmt.setInt(2, bo_id);
			pstmt.setString(3, nutzer.getEmail());
			pstmt.setString(4, nutzer.getNutzername());
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
		return nutzer;
	}
	

	public Nutzer update (Nutzer nutzer) {

		Connection con = DBConnection.connection();

		try {

			PreparedStatement pstmt = con.prepareStatement("UPDATE `nutzer` SET `Email` = ?, `Nutzername` = ? WHERE (`user_id` = ?)");
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
			con.setAutoCommit(false);
			
			int bo_id = findBoIdOf(nutzer);

			super.delete(bo_id, con);
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `nutzer` WHERE (`user_id` = " + nutzer.getID() + ")");
			
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
	

	public void deleteGruppenzugehörigkeiten(int nutzerid) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `gruppe_mitglied` WHERE (`user_id` = " + nutzerid + ")");

		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	public Vector<Nutzer> getGruppenmitgliederOf (Gruppe gruppe){

		Connection con = DBConnection.connection();
		Vector<Nutzer> nutzer = new Vector<Nutzer>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from `gruppe_mitglied` LEFT JOIN `nutzer` ON `gruppe_mitglied`.`user_id` = `nutzer`.`user_id` LEFT JOIN `ownedbusinessobject` ON `nutzer`.`bo_id` = `ownedbusinessobject`.`bo_id` WHERE (`gruppen_id` = " + gruppe.getID() + ")");

			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setErstellungszeitpunkt(dc.convertTimestampToDate(rs.getTimestamp("Erstellungszeitpunkt")));
				n.setID(rs.getInt("user_id"));
				n.setOwnerID(rs.getInt("owner_id"));
				n.setEmail(rs.getString("Email"));
				n.setNutzername(rs.getString("Nutzername"));
				nutzer.add(n);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return nutzer;

	}
	
	private int findBoIdOf (Nutzer nutzer) {
		
		Connection con = DBConnection.connection();
		int bo_id = 0;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT `bo_id` FROM `nutzer` WHERE (`user_id` = " +  nutzer.getID() + ")");
			
			if(rs.next()) {
				bo_id = rs.getInt("bo_id");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bo_id;
	}

}
