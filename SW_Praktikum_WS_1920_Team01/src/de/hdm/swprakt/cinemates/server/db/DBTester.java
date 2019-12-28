package de.hdm.swprakt.cinemates.server.db;
import java.sql.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

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
		
//		System.out.println("\nfindAllGruppe()");
//		Vector <Gruppe> gruppen = gruppeMapper.findAllGruppe();
//		for(Gruppe g : gruppen) {
//			System.out.println(g.toString());
//		}
//		
//		System.out.println("\nfindById(2)");
//		Gruppe testGruppeId = gruppeMapper.findByID(2);
//		System.out.println(testGruppeId.toString());
//		
//		System.out.println("\nfindByGruppenname(Gruppe5)");
//		Gruppe testGruppeGruppenname = gruppeMapper.findByGruppenname("Gruppe5");
//		System.out.println(testGruppeGruppenname.toString());
//		
//		System.out.println("\ninsert()");
//		Gruppe gruppeInsert = new Gruppe();
//		gruppeInsert.setOwnerID(3);
//		gruppeInsert.setGruppenname("TestGruppe Insert");
//		System.out.println(gruppeInsert.toString());
//		gruppeInsert = gruppeMapper.insert(gruppeInsert);
//		System.out.println(gruppeInsert.toString());
//		
//		System.out.println("\nupdate()");
//		Gruppe gruppeUpdate = gruppeMapper.findByGruppenname("TestGruppe Insert");
//		System.out.println(gruppeUpdate.toString());
//		gruppeUpdate.setGruppenname("TestGruppeUpdate");
//		System.out.println(gruppeUpdate.toString());
//		gruppeUpdate = gruppeMapper.update(gruppeUpdate);
//		System.out.println(gruppeUpdate.toString());
//		
//		System.out.println("\ndelete()");
//		Gruppe gruppeDelete = gruppeMapper.findByGruppenname("TestGruppeUpdate");
//		System.out.println(gruppeDelete.toString());
//		gruppeMapper.delete(gruppeDelete);
//		gruppeDelete = gruppeMapper.findByGruppenname("TestGruppe Insert");
//		if(gruppeDelete == null) System.out.println("Die Gruppe wurde gelöscht");
//		
//		System.out.println("\ninsertGruppenzugehörigkeit(8,3)");
//		gruppeMapper.insertGruppenzugehörigkeit(8,3);
//		Gruppe testGruppe = gruppeMapper.findByID(3);
//		Vector <Nutzer> gruppenmitgliederTest = nutzerMapper.getGruppenmitgliederOf(testGruppe);
//		for(Nutzer n : gruppenmitgliederTest) {
//			System.out.println(n.toString());
//		}
//		
//		System.out.println("\ndeleteGruppenmitglieder(3)");
//		
//		
//		System.out.println("\ngetGruppenOf(Nutzer9)");
//		Nutzer nutzer9 = nutzerMapper.findByID(9);
//		Vector<Gruppe> gruppenOfNutzer = gruppeMapper.getGruppenOf(nutzer9);
//		for(Gruppe g : gruppenOfNutzer) {
//			System.out.println(g.toString());
//		}
//		
//		
//		System.out.println("\ngetGruppenOf(Umfrage 4)");
//		Umfrage umfrage4 = umfrageMapper.findByID(4);
//		System.out.println(umfrage4.toString());
//		Vector<Gruppe> gruppenOfUmfrage = gruppeMapper.getGruppenOf(umfrage4);
//		for(Gruppe g : gruppenOfUmfrage) {
//			System.out.println(g.toString());
//		}
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Film-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
//		System.out.println("\nfindAll()");
//		Vector<Film> filme = filmMapper.findAll();
//		for (Film f : filme) {
//			System.out.println(f.toString());
//		}
		
		
//		System.out.println("\nfindById(33)");
//		Film testfilmId = filmMapper.findByID(33);
//		System.out.println(testfilmId.toString());
//		
//		
//		System.out.println("\ninsert()");
//		Film testInsertFilm = new Film();
//		testInsertFilm.setFilmtitel("TestFilm Insert");
//		testInsertFilm.setBeschreibung("Testbeschreibung TestFilm");
//		testInsertFilm.setDetails("paar Minuten");
//		System.out.println(testInsertFilm.toString());
//		testInsertFilm = filmMapper.insert(testInsertFilm);
//		System.out.println(testInsertFilm.toString());
//		
//		
//		System.out.println("\nupdate()");
//		Film testUpdateFilm = filmMapper.findByID(510);
//		System.out.println(testInsertFilm.toString());
//		testUpdateFilm.setBeschreibung("Testbeschreibung nach Update");
//		testUpdateFilm.setFilmtitel("TestFilmTitle nach  Update");
//		testUpdateFilm = filmMapper.update(testUpdateFilm);
//		System.out.println(testUpdateFilm.toString());
//		
//		
//		System.out.println("\ndelete()");
//		Film testdeleteFilm = filmMapper.findByID(510);
//		filmMapper.delete(testdeleteFilm);
//		testdeleteFilm = filmMapper.findByID(510);
//		if(testdeleteFilm == null) System.out.println("Der Film wurde gelöscht");
//		
//		
//		System.out.println("\nfindAllFilmeByKinokette(1)");
//		Kinokette kinokette1 = kinoketteMapper.findByID(1);
//		Vector<Film> filmeinKinokette = filmMapper.findFilmeByKinokette(kinokette1);
//		for(Film f : filmeinKinokette) {
//			
//			System.out.println(f.toString());
//			
//		}
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Umfrage-Mapper Methoden
		 * ***************************************************************************
		 */
		
//		System.out.println("\nfindById(2)");
//		Umfrage umfrage2 = umfrageMapper.findByID(2);
//		System.out.println(umfrage2.toString());
//		
//		
//		System.out.println("\nfindAllUmfrage()");
//		Vector<Umfrage> umfragen = umfrageMapper.findAllUmfrage();
//		for(Umfrage u : umfragen) {
//			System.out.println(u.toString());
//		}
//		
//		
//		System.out.println("\nfindByErsteller(Nutzer7)");
//		Nutzer nutzer7 = nutzerMapper.findByID(7);
//		Vector<Umfrage> umfragenVonNutzer7 = umfrageMapper.findByErsteller(nutzer7);
//		for(Umfrage u : umfragenVonNutzer7) {
//			System.out.println(u.toString());
//		}
//		
//		
//		System.out.println("\nfindByUmfragename(Umfrage 4)");
//		Umfrage testUmfrageName = umfrageMapper.findByUmfragenname("Umfrage4");
//		System.out.println(testUmfrageName.toString());
//		
//		
//		System.out.println("\ninsert()");
//		Umfrage testUmfrageInsert = new Umfrage();
//		testUmfrageInsert.setUmfragenname("TestUmfrage Insert");
//		testUmfrageInsert.setBeschreibung("TestBeschreibung Umfrage Insert");
//		testUmfrageInsert.setFilmID(33);
//		java.util.Date date = new java.util.Date();
//		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DAY_OF_MONTH,17);
//		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
//		cal.set(Calendar.YEAR, 2020);
//		date = cal.getTime();
//		testUmfrageInsert.setDatum(date);
//		testUmfrageInsert.setOwnerID(3);
//		System.out.println(testUmfrageInsert.toString());
//		testUmfrageInsert = umfrageMapper.insert(testUmfrageInsert);
//		System.out.println(testUmfrageInsert.toString());
//		
//		
//		System.out.println("\nupdate()");
//		Umfrage testUmfrageUpdate = umfrageMapper.findByUmfragenname("TestUmfrage Insert");
//		System.out.println(testUmfrageUpdate.toString());
//		testUmfrageUpdate.setUmfragenname("TestUmfrage nach Update");
//		testUmfrageUpdate.setBeschreibung("TestUmfrage nach Update Beschreibung");
//		testUmfrageUpdate = umfrageMapper.update(testUmfrageUpdate);
//		System.out.println(testUmfrageUpdate.toString());
//		
//		
//		System.out.println("\ndelete()");
//		Umfrage testdeleteUmfrage = umfrageMapper.findByUmfragenname("TestUmfrage nach Update");
//		System.out.println(testdeleteUmfrage.toString());
//		umfrageMapper.delete(testdeleteUmfrage);
//		testdeleteUmfrage = umfrageMapper.findByUmfragenname("TestUmfrage nach Update");
//		if(testdeleteUmfrage == null) System.out.println("Die Umfrage wurde gelöscht");
//		
//		
//		System.out.println("\nfindByGruppe(Gruppe2)");
//		Gruppe testGruppe = gruppeMapper.findByID(2);
//		Vector<Umfrage> testUmfrageGruppe = umfrageMapper.findByGruppe(testGruppe);
//		
//		for(Umfrage u :testUmfrageGruppe) {
//			System.out.println(u.toString());
//		}
		
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Umfrageeintrag-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
//		System.out.println("\nfindByID(5)");
//		Umfrageeintrag testUmfrageeintragId = umfrageeintragMapper.findByID(5);
//		System.out.println(testUmfrageeintragId.toString());
//		
//		System.out.println("\nfindAllUmfrageeintrag()");
//		Vector<Umfrageeintrag> testvectorUmfrageeintrag = umfrageeintragMapper.findAllUmfrageeintrag();
//		
//		for(Umfrageeintrag ue : testvectorUmfrageeintrag) {
//			System.out.println(ue.toString());
//		}
//		
//		
//		System.out.println("\nfindByUmfrage(Umfrage 1)");
//		Umfrage testUmfrage = umfrageMapper.findByID(1);
//		Vector<Umfrageeintrag> testvectorUmfrageeinträge = umfrageeintragMapper.findByUmfrage(testUmfrage);
//		
//		for(Umfrageeintrag ue : testvectorUmfrageeinträge) {
//			System.out.println(ue.toString());
//		}
//		
//		
//		System.out.println("\nfindBySpielzeit(5)");
//		Spielzeit testSpielzeit = spielzeitMapper.findByID(5);
//		Vector<Umfrageeintrag> testvectorUmfrageeinträgeSpielzeiten = umfrageeintragMapper.findBySpielzeit(testSpielzeit);
//		
//		for(Umfrageeintrag ue : testvectorUmfrageeinträgeSpielzeiten) {
//			System.out.println(ue.toString());
//		}
//		
//		
//		
//		System.out.println("\nfindByKino(Traumpalast Waiblingen)");
//		Kino testKino = kinoMapper.findByKinoname("Traumpalast Waiblingen");
//		Vector<Umfrageeintrag> testvectorUmfrageeinträgeKinos = umfrageeintragMapper.findByKino(testKino);
//		
//		for(Umfrageeintrag ue : testvectorUmfrageeinträgeKinos) {
//			System.out.println(ue.toString());
//		}
//		
//		
//		System.out.println("\ninsert()");
//		Umfrageeintrag testInsertUmfrageeintrag = new Umfrageeintrag();
//		testInsertUmfrageeintrag.setKinoID(3);
//		testInsertUmfrageeintrag.setSpielzeitID(23);
//		testInsertUmfrageeintrag.setUmfrageID(2);
//		System.out.println(testInsertUmfrageeintrag.toString());
//		testInsertUmfrageeintrag = umfrageeintragMapper.insert(testInsertUmfrageeintrag);
//		System.out.println(testInsertUmfrageeintrag.toString());
//		
//		
//		System.out.println("\nupdate()");
//		Umfrageeintrag testUmfrageeintragUpdate = umfrageeintragMapper.findByID(73);
//		System.out.println(testUmfrageeintragUpdate.toString());
//		testUmfrageeintragUpdate.setKinoID(144);
//		testUmfrageeintragUpdate.setUmfrageID(12222);
//		testUmfrageeintragUpdate = umfrageeintragMapper.update(testUmfrageeintragUpdate);
//		System.out.println(testUmfrageeintragUpdate.toString());
//		
//		
//		System.out.println("\ndelete()");
//		Umfrageeintrag testUmfrageeintragDelete = umfrageeintragMapper.findByID(73);
//		System.out.println(testUmfrageeintragDelete.toString());
//		umfrageeintragMapper.delete(testUmfrageeintragDelete);
//		testUmfrageeintragDelete = umfrageeintragMapper.findByID(73);
//		if(testUmfrageeintragDelete == null) System.out.println("Der Umfrageeintrag wurde gelöscht");
//		
//		
//		System.out.println("\nfindUmfrageeintragOhneVotum(Nutzer 2)");
//		Nutzer testNutzer = nutzerMapper.findByID(2);
//		Vector<Umfrageeintrag> nutzerOhneVotum = umfrageeintragMapper.findUmfrageeintragOhneVotum(testNutzer);
//		for(Umfrageeintrag ue : nutzerOhneVotum) {
//			System.out.println(ue.toString());
//		}
		
		
		/*
		 * ***************************************************************************
		 * Testen der Kino-Mapper Methoden
		 * ***************************************************************************
		 */
		
//		System.out.println("\nfindAllKino()");
//		Vector<Kino> testVectorKino = kinoMapper.findAllKino();
//		for(Kino k : testVectorKino) {
//			System.out.println(k.toString());
//		}
//		
//		
//		System.out.println("\nfindById(3)");
//		Kino testKinoId = kinoMapper.findByID(3);
//		System.out.println(testKinoId.toString());
//		
//		
//		System.out.println("\nfindByKinoname()");
//		Kino testKinoName = kinoMapper.findByKinoname("Cinestar Hannover");
//		System.out.println(testKinoName.toString());
//		
//		
//		System.out.println("\nfindByKinokette(Cinestar)");
//		Kinokette cinestar = kinoketteMapper.findByKinokettenname("Cinestar");
//		Vector<Kino> kinosInKinoketten = kinoMapper.findByKinokette(cinestar);
//		for(Kino k : kinosInKinoketten) {
//			System.out.println(k.toString());
//		}
//		
//		
//		System.out.println("\nfindBySpielplan(1)");
//		Spielplan testspielplan = spielplanMapper.findByID(1);
//		Kino kinosZuSpielplan = kinoMapper.findBySpielplan(testspielplan);
//		System.out.println(kinosZuSpielplan.toString());
//		
//		
//		System.out.println("\ninsert()");
//		Kino testKinoInsert = new Kino();
//		testKinoInsert.setAdresse("Testadressse");
//		testKinoInsert.setBeschreibung("Testbeschreibung Kino");
//		testKinoInsert.setKinoketteID(2);
//		testKinoInsert.setKinoname("Testkino");
//		testKinoInsert.setSpielplanID(2);
//		System.out.println(testKinoInsert.toString());
//		testKinoInsert = kinoMapper.insert(testKinoInsert);
//		System.out.println(testKinoInsert);
//		
//		
//		System.out.println("\nupdate()");
//		Kino testKinoUpdate = kinoMapper.findByKinoname("Testkino");
//		System.out.println(testKinoUpdate.toString());
//		testKinoUpdate.setBeschreibung("Update Beschreibung");
//		testKinoUpdate.setAdresse(testKinoUpdate.getAdresse() + " und ein Update");
//		testKinoUpdate = kinoMapper.update(testKinoUpdate);
//		System.out.println(testKinoUpdate.toString());
//		
//		
//		System.out.println("\ndelete()");
//		Kino testKinoDelete = kinoMapper.findByKinoname("Testkino");
//		System.out.println(testKinoDelete.toString());
//		kinoMapper.delete(testKinoDelete);
//		testKinoDelete = kinoMapper.findByKinoname("Testkino");
//		if(testKinoDelete == null) System.out.println("Das Kino wurde gelöscht");
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Kinokette-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
//		System.out.println("\nfindAllKinoketten()");
//		Vector<Kinokette> kinoketten = kinoketteMapper.findAllKinoketten();
//		for(Kinokette kk : kinoketten) {
//			System.out.println(kk.toString());
//		}
//			
//		System.out.println("\nfindById()");
//		Kinokette testKKId = kinoketteMapper.findByID(2);
//		System.out.println(testKKId.toString());
//		
//		
//		System.out.println("\nfindByKinokettenname()");
//		Kinokette testKKName = kinoketteMapper.findByKinokettenname("Cinemaxx");
//		System.out.println(testKKName.toString());
//		
//		
//		System.out.println("\ninsert()");
//		Kinokette testKKInsert = new Kinokette();
//		testKKInsert.setBeschreibung("TestINsert Kinokette");
//		testKKInsert.setKinokettenname("TestKinokette");
//		System.out.println(testKKInsert.toString());
//		testKKInsert = kinoketteMapper.insert(testKKInsert);
//		System.out.println(testKKInsert.toString());
//		
//		System.out.println("\nupdate()");
//		Kinokette testKKUpdate = kinoketteMapper.findByKinokettenname("TestKinokette");
//		System.out.println(testKKUpdate.toString());
//		testKKUpdate.setBeschreibung("kleines Update");
//		testKKUpdate = kinoketteMapper.update(testKKUpdate);
//		System.out.println(testKKUpdate.toString());
//		
//		
//		System.out.println("\ndelete()");
//		Kinokette testKKDelete = kinoketteMapper.findByKinokettenname("TestKinokette");
//		System.out.println(testKKDelete.toString());
//		kinoketteMapper.delete(testKKDelete);
//		testKKDelete = kinoketteMapper.findByKinokettenname("TestKinokette");
//		if(testKKDelete == null) System.out.println("Die Kinokette wurde gelöscht");
//		
//		
//		System.out.println("\nfindKinoketteByOwner()");
//		Nutzer testKKOwnerNutzer = nutzerMapper.findByID(14);
//		Kinokette testKKOwner = kinoketteMapper.findKinoketteByOwner(testKKOwnerNutzer);
//		System.out.println(testKKOwner.toString());
		
		
		
		/*
		 * ***************************************************************************
		 * Testen der Spielplan-Mapper Methoden
		 * ***************************************************************************
		 */
		
//		System.out.println("\nfindAllSpielplan()");
//		Vector <Spielplan> spielpläne = spielplanMapper.findAllSpielplan();
//		for(Spielplan sp : spielpläne) {
//			System.out.println(sp.toString());
//		}
//		
//		System.out.println("\nfindById()");
//		Spielplan testSpId = spielplanMapper.findByID(2);
//		System.out.println(testSpId.toString());
//		
//		System.out.println("\ninsert()");
//		Spielplan testSpielplaninsert = new Spielplan();
//		testSpielplaninsert.setOwnerID(14);
//		testSpielplaninsert.setSpielplanname("TestSpielplan");
//		System.out.println(testSpielplaninsert.toString());
//		testSpielplaninsert = spielplanMapper.insert(testSpielplaninsert);
//		System.out.println(testSpielplaninsert);
//		
//		System.out.println("\nupdate()");
//		Spielplan testSpielplanUpdate = spielplanMapper.findByID(4);
//		System.out.println(testSpielplanUpdate.toString());
//		testSpielplanUpdate.setSpielplanname("Spielplanname nach Update");
//		testSpielplanUpdate = spielplanMapper.update(testSpielplanUpdate);
//		System.out.println(testSpielplanUpdate.toString());
//		
//		System.out.println("\ndelete()");
//		Spielplan testSpielplanDelete = spielplanMapper.findByID(4);
//		System.out.println(testSpielplanDelete.toString());
//		spielplanMapper.delete(testSpielplanDelete);
//		testSpielplanDelete = spielplanMapper.findByID(4);
//		if(testSpielplanDelete == null) System.out.println("Der Spielplan wurde gelöscht");
		
		/*
		 * ***************************************************************************
		 * Testen der Spielzeit-Mapper Methoden
		 * ***************************************************************************
		 */
		
		
//			System.out.println("\nfindById()");
//			Spielzeit testspielzeitId = spielzeitMapper.findByID(2);
//			System.out.println(testspielzeitId.toString());
			
//			System.out.println("\nfindAllSpielzeit()");
//			Vector<Spielzeit> spielzeiten = spielzeitMapper.findAllSpielzeit();
//			for(Spielzeit sp : spielzeiten) {
//				System.out.println(sp.toString());
//								
//			}
//			
//			
//			System.out.println("\ninsert()");
//			Spielzeit testspielzeit = new Spielzeit();
//			testspielzeit.setFilmID(25);
//			testspielzeit.setOwnerID(800);
//			java.util.Date zeitpunkt = new java.util.Date();
//			Calendar cal = Calendar.getInstance();
//			cal.set(Calendar.DAY_OF_MONTH,17);
//			cal.set(Calendar.MONTH, Calendar.FEBRUARY);
//			cal.set(Calendar.YEAR, 2020);
//			cal.set(Calendar.HOUR, 18);
//			cal.set(Calendar.MINUTE, 45);
//			zeitpunkt = cal.getTime();
//			testspielzeit.setZeitpunkt(zeitpunkt);
//			System.out.println(testspielzeit.toString());
//			testspielzeit = spielzeitMapper.insert(testspielzeit);
//			System.out.println(testspielzeit.toString());
//			
//			System.out.println("\nupdate()");
//			Spielzeit testSpielzeitDelete = spielzeitMapper.findByID(31);
//			System.out.println(testSpielzeitDelete);
//			spielzeitMapper.delete(testSpielzeitDelete);
//			testSpielzeitDelete = spielzeitMapper.findByID(31);
//			if(testSpielzeitDelete == null) System.out.println("Die Spielzeit wurde gelöscht");
//				
//			System.out.println("\nfindSpielzeitenBySpielplan()");
//			Spielplan testSpielplan = spielplanMapper.findByID(2);
//			Vector<Spielzeit> spielzeitenOfSpielplan = spielzeitMapper.findSpielzeitenBySpielplan(testSpielplan);
//			for(Spielzeit sp : spielzeitenOfSpielplan) {
//				System.out.println(sp.toString());
//			}
//		
//		
//			System.out.println("\nfindSpielzeitenByFilmAndDate()");
//			java.util.Date date = new java.util.Date();
//			Calendar cal = Calendar.getInstance();
//			cal.set(Calendar.DAY_OF_MONTH,14);
//			cal.set(Calendar.MONTH, Calendar.DECEMBER);
//			date = cal.getTime();
//			Film testFilm = filmMapper.findByID(84);
//			
//			Vector<Spielzeit> spielzeitenZuUmfrage = spielzeitMapper.findSpielzeitenByFilmAndByDate(testFilm, date);
//			for(Spielzeit sp : spielzeitenZuUmfrage) {
//				System.out.println(sp.toString());
//			}
			
		
		/*
		 * ****************************3***********************************************
		 * Testen der Votum-Mapper Methoden
		 * ***************************************************************************
		 */

//		System.out.println("\nfindById()");
//		Votum testVotumId = votumMapper.findByID(10);
//		System.out.println(testVotumId.toString());
//		
//		
//		System.out.println("\nfindAllVotum()");
//		Vector<Votum> votums = votumMapper.findAllVotum();
//		for(Votum v : votums) {
//			System.out.println(v.toString());
//		}
//		
//		System.out.println("\nfindVotumByUmfrageeintrag()");
//		Umfrageeintrag testUe = umfrageeintragMapper.findByID(1);
//		Vector<Votum> votumsFromUmfrageeintrag = votumMapper.findVotumByUmfrageeintrag(testUe);
//		for(Votum v : votumsFromUmfrageeintrag) {
//			System.out.println(v.toString());
//		}
//		
//		
//		System.out.println("\ninsert()");
//		Votum testInsert = new Votum();
//		testInsert.setIstMöglicherTermin(true);
//		testInsert.setUmfrageeintragID(2);
//		testInsert.setOwnerID(14);
//		System.out.println(testInsert.toString());
//		testInsert = votumMapper.insert(testInsert);
//		System.out.println(testInsert.toString());
//		
//		System.out.println("\nupdate()");
//		Votum testUpdate = votumMapper.findByID(67);
//		System.out.println(testUpdate.toString());
//		testUpdate.setIstMöglicherTermin(false);
//		System.out.println(testUpdate.toString());
//		testUpdate = votumMapper.update(testUpdate);
//		System.out.println(testUpdate.toString());
//		
//		
//		System.out.println("\ndelete()");
//		Votum testDeleteVotum = votumMapper.findByID(67);
//		System.out.println(testDeleteVotum.toString());
//		votumMapper.delete(testDeleteVotum);
//		testDeleteVotum = testDeleteVotum = votumMapper.findByID(67);
//		if(testDeleteVotum == null) System.out.println("Das Votum wurde gelöscht");
		
		
		/*
		 * ***************************************************************************
		 * Testen der OwnedBusinessObject-Mapper Methoden
		 * ***************************************************************************
		 */
		
	}
}
