package de.hdm.swprakt.cinemates.server.db;
import java.sql.*;
import java.util.Iterator;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

public abstract class DBTester {
	
	private static Connection con = DBConnection.connection();
	
	public static void main(String[] args) {
		
		NutzerMapper nutzerMapper = NutzerMapper.nutzerMapper();
		
		Vector<Nutzer> nutzer = nutzerMapper.findAll();
		
		Nutzer testnutzer = nutzerMapper.findByEmail("Nutzer8@gmail.com");
		
		Iterator i = nutzer.iterator();
		
		while(i.hasNext()) {
			Nutzer user = (Nutzer) i.next();
			System.out.println(user.toString());
		}
		
		System.out.println(testnutzer.toString());
		
	
	}
}
