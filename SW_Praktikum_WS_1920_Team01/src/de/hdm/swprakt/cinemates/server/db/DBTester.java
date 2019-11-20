package de.hdm.swprakt.cinemates.server.db;
import java.sql.*;

public class DBTester {
	
	private static Connection con = DBConnection.connection();
	
	public static void main(String[] args) {
		
		try {
			
			Statement myStmt = con.createStatement();
			ResultSet rs = myStmt.executeQuery("SELECT * FROM film");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int rowcount = 0;
			
			
			while (rs.next()) {
				if(rs.getString("Filmname")!= null) {
				System.out.println(rs.getString("Filmname"));
				rowcount++;
				}
				
			}
			
			System.out.println("\n" + rsmd.getColumnCount() + " Columns and " + rowcount + " Rows!");
			
			rs.close();
			myStmt.close();
			con.close();
		
		
	}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	
	
}
}
