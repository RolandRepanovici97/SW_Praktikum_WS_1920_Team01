package de.hdm.swprakt.cinemates.server.db;
import java.sql.*;
import java.util.Iterator;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

public abstract class DBTester {
	
	private static Connection con = DBConnection.connection();
	private static NutzerMapper nutzerMapper = NutzerMapper.nutzerMapper();
	private static GruppeMapper gruppeMapper = GruppeMapper.gruppeMapper();
	private static FilmMapper filmMapper = FilmMapper.filmMapper();
	private static KinoMapper kinoMapper = KinoMapper.kinoMapper();
	private static KinoketteMapper kinoketteMapper = KinoketteMapper.kinoketteMapper();
	private static SpielplanMapper spielplanMapper = SpielplanMapper.spielplanMapper();
	private static SpielzeitMapper spielzeitMapper = SpielzeitMapper.spielzeitMapper();
	private static UmfrageMapper umfrageMapper = UmfrageMapper.umfrageMapper();
	private static UmfrageeintragMapper umfrageeintragMapper = UmfrageeintragMapper.umfrageeintragMapper();
	private static VotumMapper votumMapper = VotumMapper.votumMapper();
	private static OwnedBusinessObjectMapper ownedBusinessObjectMapper = OwnedBusinessObjectMapper.ownedBusinessObjectMapper();
	
	
	public static void main(String[] args) {
		
		
		/*
		 * ***************************************************************************
		 * Testen der Nutzer-Mapper Methoden
		 * ***************************************************************************
		 */
		
		System.out.println("\nfindAllNutzer()");
		Vector<Nutzer> nutzer = nutzerMapper.findAllNutzer();
		Iterator i = nutzer.iterator();
		
		while(i.hasNext()) {
			Nutzer user = (Nutzer) i.next();
			System.out.println(user.toString());
		}
		
		
		System.out.println("\nfindByID(2)");
		Nutzer testnutzerID = nutzerMapper.findByID(2);
		System.out.println(testnutzerID.toString());
		
		
		System.out.println("\nfindByEmail(Nutzer8@gmail.com)");
		Nutzer testnutzerEmail = nutzerMapper.findByEmail("Nutzer8@gmail.com");
		System.out.println(testnutzerEmail.toString());
		
	
		
		/*
		 * ***************************************************************************
		 * Testen der Gruppe-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Film-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		/*
		 * ***************************************************************************
		 * Testen der Umfrage-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Umfrageeintrag-Mapper Methoden
		 * ***************************************************************************
		 */
		
		/*
		 * ***************************************************************************
		 * Testen der Kino-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Kinokette-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Spielplan-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Spielzeit-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Votum-Mapper Methoden
		 * ***************************************************************************
		 */


		
		/*
		 * ***************************************************************************
		 * Testen der OwnedBusinessObject-Mapper Methoden
		 * ***************************************************************************
		 */
		
	}
}
