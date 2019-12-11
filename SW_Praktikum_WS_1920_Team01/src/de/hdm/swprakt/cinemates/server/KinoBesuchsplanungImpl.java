/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.GruppeMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.OwnedBusinessObjectMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageeintragMapper;
import de.hdm.swprakt.cinemates.server.db.VotumMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;
/**
 * Diese Klasse stellt die Implementierungsklasse des Interface {@link KinoBesuchsplanung} dar. 
 * Sie beinhaltet die komplette Applikationslogik, welche zur Planung eines Kinobesuchs benötigt wird. 
 * (Anlage und Verwaltung von Gruppen, Umfragen, etc.) Sie benötigt Zugriff auf die  <code> KinoAdministration </code>,
 * da diese Methoden bereitstellt, welche für die Kinobesuchsplanung relevant sind. 
 * 
 * @author alina
 * @version 1.0
 *
 */
public class KinoBesuchsplanungImpl extends RemoteServiceServlet implements KinoBesuchsplanung {
	/** Zugriff auf KinoAdministration
	 * 
	 */

	private KinoAdministration administration = null;

	/** Der Kinobesucher benötigt Zuriff auf die Daten rund um eine Umfrage, Gruppe etc.
	 *  Dieser Zugriff wird über die jeweiligen Mapper realisiert. 
	 *  */
	private NutzerMapper nutzerMapper = null;
	private GruppeMapper gruppeMapper = null;
	private UmfrageMapper umfrageMapper = null;
	private UmfrageeintragMapper umfrageeintragMapper = null;
	private VotumMapper votumMapper = null;
	private OwnedBusinessObjectMapper ownedBusinessObjectMapper = null;


	public KinoBesuchsplanungImpl() throws IllegalArgumentException {

	}


	/* Initalisierung der Variablen, welche die Referenzen auf die Mapeprklassen darstellen. 
	 * Wir initialisieren diese durch den Aufruf des protected-Konstruktors. Dieser 
	 * ermöglicht uns, dass jeweils nur eine Instanz dieser Klasse erzeugt werden kann.
	 * */
	public void init() throws IllegalArgumentException {

		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.gruppeMapper = GruppeMapper.gruppeMapper();
		this.umfrageMapper = UmfrageMapper.umfrageMapper();
		this.umfrageeintragMapper = UmfrageeintragMapper.umfrageeintragMapper();
		this.votumMapper = VotumMapper.votumMapper();
		this.ownedBusinessObjectMapper = OwnedBusinessObjectMapper.ownedBusinessObjectMapper();

		KinoAdministrationImpl kinoAdministrationImpl = new KinoAdministrationImpl();
		kinoAdministrationImpl.init();
		this.administration = kinoAdministrationImpl;
	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn wir ein Nutzerobjekt anhand seiner E-Mail finden möchten.
	 * @author alina
	 */
	public Nutzer findNutzerByEmail(String email) throws IllegalArgumentException {

		Nutzer nutzer= nutzerMapper.findByEmail(email);
		return nutzer;
	}

	/**
	 * Diese Methode wird aufgerufen wenn ein neuer Nutzer erstellt wird.
	 * @author roland
	 * 
	 */
	public Nutzer createNutzer(String email, String nutzername) throws IllegalArgumentException {

		Nutzer nutzer = new Nutzer();
		nutzer.setEmail(email);
		nutzer.setNutzername(nutzername);
		nutzerMapper.insert(nutzer);


		return nutzer;
	}


	/**
	 * Diese Methode wird aufgerufen, wenn eine Gruppe erstellt wird. Diese Realisierung ist nicht besonders
	 * elegant, aber das Attribut gruppenmitglieder erwartet Integer-Werte, welche die IDs der einzelnen
	 * Nutzerobjekte darstellen.
	 * @author alina
	 */
	public Gruppe createGruppe(String gruppenname, Vector <Nutzer> gruppenmitglieder) throws IllegalArgumentException {

		// Erstellen der des neuen Gruppenobjekts
		Gruppe gruppe = new Gruppe();

		//Setzen des Namens
		gruppe.setGruppenname(gruppenname);

		/**Erstellung eines leeren Vectors mit Integer Objekten, in welchem später die
		 * IDs der Gruppenmitglieder gespeichert werden
		 */
		Vector <Integer> gruppenmitgliederids = new Vector <Integer>();

		//Zunächst Prüfung, ob Vector nicht leer ist
		if(gruppenmitglieder!= null) {

			//Iteration durch den Vector, um IDs zu bestimmen
			for(Nutzer n: gruppenmitglieder) {
				int id= n.getID();

				//Hinzufügen der IDs zum Zielvector, welcher später das Argument für das Attribut gruppenmitglieder wird
				gruppenmitgliederids.add(id);

			}

		}
		//Setzen des Attributs gruppenmitglieder
		gruppe.setGruppenmitglieder(gruppenmitgliederids);

		//Einfügen des Gruppenobjekts in die Datenbank
		gruppe = gruppeMapper.insert(gruppe);

		//Zurückgeben des Gruppenobjekts
		return gruppe;
	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Umfrage-Objekte
	 * ***************************************************************************
	 */



	/**
	 * Diese Methode wird aufgerufen, wenn wir alle in der Datenbank gespeicherten Umfragen ausgeben möchten.
	 * @author alina
	 */
	public Vector <Umfrage> showAllUmfrage() {

		return umfrageMapper.findAllUmfrage();


	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Nutzers ausgeben möchten. Das heißt wir suchen
	 * nach den Gruppen des Nutzers und hier wiederum nach den Umfragen, welche zu den Gruppen
	 * gehören und geben diese aus.
	 * @author alina
	 */


	public Vector <Umfrage> showAllUmfrageOfNutzer(Nutzer n) {

		Vector <Umfrage> ergebnisvector = new Vector <Umfrage>();
		Vector <Gruppe> gruppevector = gruppeMapper.getGruppenOf(n);
		for(Gruppe g: gruppevector) {
			ergebnisvector.addAll(umfrageMapper.findByGruppename(g.getGruppenname()));
		}
		return ergebnisvector;

	}
	
	

	/**Diese Methode wird aufgerufen, wenn eine neue Umfrage erstellt wird. Es wird hier lediglich der Umfragenname übergeben, da wir diesen benötigen um ein
	 * Umfrageeobjekt initial lebensfähig zu machen. Alle anderen Attribute können wir später vergeben.
	 * @author alina
	 */

	public Umfrage createUmfrage(String umfragenname) throws IllegalArgumentException { 

		Umfrage umfrage = new Umfrage();
		umfrage.setUmfragenname(umfragenname);
		umfrageMapper.insert(umfrage);

		return umfrage;

	}

	/**Diese Methode wird aufgerufen, wenn alle Vota zu einem Umfrageeintrag angezeigt werden sollen. Hierzu wird die Mapper Methode @link findVotumByUmfrageeintrag
	 * aufgerufen, welche uns einen Vector von Objekten der Klasse <Votum> zurückgibt.
	 * @author alina
	 */

	public Vector <Votum> showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException{
		return this.votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);

	}


	/**Diese Methode wird aufgerufen, wenn eine Umfrage bearbeitet wird. Hier kann der Name und die Beschreibung geändert werden.
	 * Es wird die zu bearbeitende Umfrage übergeben. Diese wird in der DB gesucht und entsprechend geändert.
	 * 
	 * @author alina
	 */

	public Umfrage editUmfrage(Umfrage umfrage, String umfragenname, String beschreibung) throws IllegalArgumentException { 
		int umfrageID= umfrage.getID();
		Umfrage dbumfrage = umfrageMapper.findByID(umfrageID);
		umfrage.setUmfragenname(umfragenname);
		umfrage.setBeschreibung(beschreibung);
		umfrageMapper.update(dbumfrage);

		return umfrage;

	}

	/*Diese Methode realisiert das Löschen einer Umfrage. Hier wird auch die Löschweitergabe realisiert. Unserer Logik nach
	 * besteht eine Umfrage aus Umfrageeinträgen. Votum-Objekte können wiederum Umgfrageeinträgen zugehörig sein.
	 * Wird eine Umfrage gelöscht, so müssen auch die Umfrageeinträge und deren
	 * Votum-Objekte gelöscht werden.
	 * @author alina
	 */

	public void deleteUmfrage(Umfrage umfrage) throws IllegalArgumentException{ 

		// Wir suchen alle Umfrageeinträge, die zu dieser Umfrage ghehören und speichern diese in einem Zwischenvector
		Vector <Umfrageeintrag> vectorumfrageeinträge = umfrageeintragMapper.findByUmfrage(umfrage);

		/**Wenn es Umfrageeinträge gab, suchen wir für diese Umfrageeinträge die zugehörigen Votum-Objekte und speichern diese wieder in einer
		 * Zwischenvariable vom Typ Vector. Wir iterieren anschließend durch diesen Vector und löschen alle Votum-Objekte in der Datenbank
		 */


		if(vectorumfrageeinträge!= null) {
			for(Umfrageeintrag umfrageeintrag: vectorumfrageeinträge) {
				Vector <Votum> vectorvotum = votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);
				if(vectorvotum!= null) {

					for(Votum votum: vectorvotum) {
						votumMapper.delete(votum);

					}
					//Wir iterieren durch den Vector mit Umfrageeinträgen und löschen diese in der Datenbank
					umfrageeintragMapper.delete(umfrageeintrag);



				}}
			// Zuletzt löschen wir unsere übergebene Umfrage aus der Datenbank
			umfrageMapper.delete(umfrage);
		}

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Votum und Umfrageeintrag -Objekte
	 * ***************************************************************************
	 */


	/**Diese Methode wird aufgerufen, wenn ein Nutzer für einen Umfrageeintrag abstimmt, das heißt ein Votum abgibt. 
	 * 
	 * @author alina
	 */
	public Votum abstimmen(Umfrageeintrag umfrageeintrag, Boolean istMöglicherTermin) throws IllegalArgumentException {
		Votum votum = new Votum();
		votum.setUmfrageeintragID(umfrageeintrag.getID());
		votum.setIstMöglicherTermin(istMöglicherTermin);
		votumMapper.update(votum);
		umfrageeintragMapper.update(umfrageeintrag);

		return votum;

	}

	/**Diese Methode wird aufgerufen, wenn wir alle Umfrageeinträge und deren Vota anzeigen möchten. Wir übergeben eine Umfrage, deren Ergebnisse wir darstellen möchten.
	 * Wir erhalten die Umfrageeinträge der Umfrage zurück. 
	 * 
	 * @author alina
	 */
	public Vector <Umfrageeintrag> umfrageergebnisseAnzeigen(Umfrage umfrage) throws IllegalArgumentException {
		// Zunächst erstellen wir einen leeren Vector
		Vector <Umfrageeintrag> umfrageeinträge = new Vector <Umfrageeintrag>();
		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal =  0;
		//Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage
		umfrageeinträge = umfrageeintragMapper.findByUmfrage(umfrage);
		//Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag: umfrageeinträge) {
			//Diese speichern wir wieder in einem Vector
			Vector <Votum> zwischenvector = votumMapper.findVotumByUmfrageeintrag(eintrag);
			/** Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob das Votum positiv, negativ oder neutral war.
			 * Diese Ergebnisse speichern wir uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen. 
			 * 
			 * 
			 */
			for(Votum votum: zwischenvector) {
				if(votum.getIstMöglicherTermin()== true) {
					positiv +=1;

				}
				else if(votum.getIstMöglicherTermin() == false) {
					negativ+=1;
				}

				else if(votum.getIstMöglicherTermin()==null) {
					egal+=1;
				}
			}


		}
		return umfrageeinträge;
	}

	/**Diese Methode wird aufgerufen, wenn wir den bestmöglichen Termin einer Umfrage anzeigen möchten. Wir übergeben eine Umfrage, deren Ergebnisse wir darstellen möchten.
	 * Wir erhalten den bestmöglichen Termin zurück. 
	 * 
	 * @author alina
	 */
	public Umfrageeintrag bestesErgebnisErmitteln(Umfrage umfrage) throws IllegalArgumentException {

		// Zunächst erstellen wir einen leeren Vector
		Vector <Umfrageeintrag> umfrageeinträge = new Vector <Umfrageeintrag>();

		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal =  0;

		//Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage

		umfrageeinträge = umfrageeintragMapper.findByUmfrage(umfrage);

		//Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag: umfrageeinträge) {

			//Diese speichern wir wieder in einem Vector
			Vector <Votum> zwischenvector = votumMapper.findVotumByUmfrageeintrag(eintrag);

			/** Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob das Votum positiv, negativ oder neutral war.
			 * Diese Ergebnisse speichern wir uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen. 
			 * 
			 * 
			 */
			for(Votum votum: zwischenvector) {
				if(votum.getIstMöglicherTermin()== true) {
					positiv +=1;
					eintrag.setPositiveAbstimmungen(positiv);

				}
				else if(votum.getIstMöglicherTermin() == false) {
					negativ+=1;
				}

				else if(votum.getIstMöglicherTermin()==null) {
					egal+=1;
				}
			}
			for (Umfrageeintrag eintrag2: umfrageeinträge) {
				Integer abst= eintrag2.getPositiveAbstimmungen();
				Vector <Integer> einträge = new Vector<Integer>();
				einträge.add(abst);
				int max = 0;
				for(int i = 0; i < einträge.size(); i++) {    
					if(einträge[0] > max)
						max = einträge[i];
				}
				return max;


			}


		}

	}

}
