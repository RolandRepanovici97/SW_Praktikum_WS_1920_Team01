package de.hdm.swprakt.cinemates.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

public class NutzerMapper {
	
	private static NutzerMapper nutzerMapper = null;
	
	
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM cinemates.nutzer ORDER BY user_id");
		
		
		while(rs.next()) {
			Nutzer n = new Nutzer();
			int bo_id = rs.getInt("bo_id");
//			ResultSet rsbo = stmt.executeQuery("SELECT * FROM ownedbusinessobject WHERE bo_id = " + bo_id);
//			n.setErstellungszeitpunkt(rsbo.getTimestamp("Erstellungszeitpunkt"));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzer WHERE user_id = " + id);

			if (rs.next()) {
				Nutzer n = new Nutzer();
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

 }
