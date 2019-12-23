package de.hdm.swprakt.cinemates.server.db;
import java.sql.*;
import java.util.Iterator;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
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
		
//		System.out.println("\nfindAllNutzer()");
//		Vector<Nutzer> nutzer = nutzerMapper.findAllNutzer();
//		Iterator i = nutzer.iterator();
//		
//		while(i.hasNext()) {
//			Nutzer user = (Nutzer) i.next();
//			System.out.println(user.toString());
//		}
//		
//		
//		System.out.println("\nfindByID(2)");
//		Nutzer testnutzerID = nutzerMapper.findByID(2);
//		System.out.println(testnutzerID.toString());
//		
//		
//		System.out.println("\nfindByEmail(Nutzer8@gmail.com)");
//		Nutzer testnutzerEmail = nutzerMapper.findByEmail("Nutzer8@gmail.com");
//		System.out.println(testnutzerEmail.toString());
//		
//		
//		System.out.println("\ninsert()");
//		Nutzer insertTest = new Nutzer();
//		insertTest.setEmail("insertTest@gmail.com");
//		insertTest.setNutzername("InsertTestNutzer");
//		System.out.println(insertTest.toString());
//		insertTest = nutzerMapper.insert(insertTest);
//		System.out.println(insertTest.toString());
//		
//		System.out.println("\nupdate()");
//		Nutzer updateTest = nutzerMapper.findByEmail("insertTest@gmail.com");
//		System.out.println(updateTest.toString());
//		updateTest.setEmail("updateTest@gmail.com");
//		updateTest.setNutzername("UpdateTest");
//		updateTest = nutzerMapper.update(updateTest);
//		System.out.println(updateTest.toString());
//		
//		System.out.println("\ndelete()");
//		Nutzer deleteTest = nutzerMapper.findByEmail("updateTest@gmail.com");
//		System.out.println(deleteTest.toString());
//		nutzerMapper.delete(deleteTest);
//		deleteTest = nutzerMapper.findByEmail("updateTest@gmail.com");
//		if(deleteTest == null) System.out.println("Der Nutzer wurde gelöscht");
//		
//		
//		System.out.println("\ndeleteGruppenzugehörigkeiten()");
//		
//		
//		System.out.println("\ngetGruppenmitgliederOf()");
//		Gruppe gruppe2 = gruppeMapper.findByID(2);
//		Vector<Nutzer> gruppenmitglieder = nutzerMapper.getGruppenmitgliederOf(gruppe2);
//		Nutzer gruppenadmin = nutzerMapper.findByID(gruppe2.getOwnerID());
//		gruppenmitglieder.add(gruppenadmin);
//		for(Nutzer n : gruppenmitglieder) {
//			System.out.println(n.toString());
//		}
		
		
		/*
		 * ***************************************************************************
		 * Testen der Gruppe-Mapper Methoden
		 * ***************************************************************************
		 */
		
		System.out.println("\nfindAllGruppe()");
		Vector <Gruppe> gruppen = gruppeMapper.findAllGruppe();
		for(Gruppe g : gruppen) {
			System.out.println(g.toString());
		}
		
		System.out.println("\nfindById(2)");
		Gruppe testGruppeId = gruppeMapper.findByID(2);
		System.out.println(testGruppeId.toString());
		
		System.out.println("\nfindByGruppenname(Gruppe5)");
		Gruppe testGruppeGruppenname = gruppeMapper.findByGruppenname("Gruppe5");
		System.out.println(testGruppeGruppenname.toString());
		
		System.out.println("\ninsert()");
		Gruppe gruppeInsert = new Gruppe();
		gruppeInsert.setOwnerID(3);
		gruppeInsert.setGruppenname("TestGruppe Insert");
		System.out.println(gruppeInsert.toString());
		gruppeInsert = gruppeMapper.insert(gruppeInsert);
		System.out.println(gruppeInsert.toString());
		
		System.out.println("\nupdate()");
		Gruppe gruppeUpdate = gruppeMapper.findByGruppenname("TestGruppe Insert");
		System.out.println(gruppeUpdate.toString());
		gruppeUpdate.setGruppenname("TestGruppeUpdate");
		System.out.println(gruppeUpdate.toString());
		gruppeUpdate = gruppeMapper.update(gruppeUpdate);
		System.out.println(gruppeUpdate.toString());
		
		System.out.println("\ndelete()");
		Gruppe gruppeDelete = gruppeMapper.findByGruppenname("TestGruppeUpdate");
		System.out.println(gruppeDelete.toString());
		gruppeMapper.delete(gruppeDelete);
		gruppeDelete = gruppeMapper.findByGruppenname("TestGruppe Insert");
		if(gruppeDelete == null) System.out.println("Die Gruppe wurde gelöscht");
		
		
		
		
		
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
