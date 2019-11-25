package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

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
				n.setEmail(rs.getString("Email"));
				n.setNutzername(rs.getString("Nutzername"));
				
				return n;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
/** @Jon: Nicht sauer ist sein, ich habe diese Methode nur für den Login benötigt,
 * 
 */

	public Nutzer findByEmail(String email) {
		return null;
		
	}
	
	/** @Jon: Nicht sauer ist sein, ich habe diese Methode nur für den Login benötigt,
	 * 
	 */

public void insert(Nutzer nutzer) {
	// TODO Auto-generated method stub
	
}
 }
