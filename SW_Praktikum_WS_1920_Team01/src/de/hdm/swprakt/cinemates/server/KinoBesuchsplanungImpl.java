/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.GruppeMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.OwnedBusinessObjectMapper;
import de.hdm.swprakt.cinemates.server.db.SpielzeitMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageeintragMapper;
import de.hdm.swprakt.cinemates.server.db.VotumMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Diese Klasse stellt die Implementierungsklasse des Interface
 * {@link KinoBesuchsplanung} dar. Sie beinhaltet die komplette
 * Applikationslogik, welche zur Planung eines Kinobesuchs benötigt wird.
 * (Anlage und Verwaltung von Gruppen, Umfragen, etc.) Sie benötigt Zugriff auf
 * die <code> KinoAdministration </code>, da diese Methoden bereitstellt, welche
 * für die Kinobesuchsplanung relevant sind.
 * 
 * @author alina
 * @version 1.0
 * @see KinoBesuchsplanung, KinoBesuchsplanungAsync
 *
 */
public class KinoBesuchsplanungImpl extends RemoteServiceServlet implements KinoBesuchsplanung {
	/**
	 * Zur Serialisierung benötigt
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Zugriff auf KinoAdministration
	 * 
	 */

	private KinoAdministration administration = null;

	/**
	 * Referenz auf einen Nutzer. Aus Sicht dieses Nutzers werden Umfragen erstellt
	 * etc.
	 */

	private Nutzer nutzer;

	/**
	 * Der Kinobesucher benötigt Zuriff auf die Daten rund um eine Umfrage, Gruppe
	 * etc. Dieser Zugriff wird über die jeweiligen Mapper realisiert.
	 */

	/**
	 * Referenz auf den NutzerMapper
	 * 
	 * @link NutzerMapper
	 */
	private NutzerMapper nutzerMapper = null;

	/**
	 * Referenz auf den GruppeMapper
	 * 
	 * @link GruppeMapper
	 */
	private GruppeMapper gruppeMapper = null;

	/**
	 * Referenz auf den UmfrageMapper
	 * 
	 * @link UmfrageMapper
	 */
	private UmfrageMapper umfrageMapper = null;

	/**
	 * Referenz auf den UmfrageeintragMapper
	 * 
	 * @link UmfrageeintragMapper
	 */
	private UmfrageeintragMapper umfrageeintragMapper = null;

	/**
	 * Referenz auf den VotumMapper
	 * 
	 * @link VotumMapper
	 */
	private VotumMapper votumMapper = null;

	/**
	 * Referenz auf den SpielzeitMapper
	 * 
	 * @link SpielzeitMapper
	 */

	private SpielzeitMapper spielzeitMapper = null;

	/**
	 * Referenz auf den OwnedBusinessObjectMapper
	 * 
	 * @link OwnedBusinessObjectMapper
	 */
	private OwnedBusinessObjectMapper ownedBusinessObjectMapper = null;

	/**
	 * Default-Konstruktor
	 * 
	 * @throws IllegalArgumentException
	 */

	public KinoBesuchsplanungImpl() throws IllegalArgumentException {

	}

	/*
	 * Initalisierung der Variablen, welche die Referenzen auf die Mapeprklassen
	 * darstellen. Wir initialisieren diese durch den Aufruf des
	 * protected-Konstruktors. Dieser ermöglicht uns, dass jeweils nur eine Instanz
	 * dieser Klasse erzeugt werden kann.
	 */

	@Override
	public void init() throws IllegalArgumentException {

		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.gruppeMapper = GruppeMapper.gruppeMapper();
		this.umfrageMapper = UmfrageMapper.umfrageMapper();
		this.umfrageeintragMapper = UmfrageeintragMapper.umfrageeintragMapper();
		this.votumMapper = VotumMapper.votumMapper();
		this.ownedBusinessObjectMapper = OwnedBusinessObjectMapper.ownedBusinessObjectMapper();

		//		KinoAdministrationImpl kinoAdministrationImpl = new KinoAdministrationImpl();
		//		kinoAdministrationImpl.init();
		//		this.administration = kinoAdministrationImpl;
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn wir ein Nutzerobjekt anhand seiner E-Mail
	 * finden möchten.
	 * 
	 * @param Email des Nutzers der gefunden werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Nutzer findNutzerByEmail(String email) throws IllegalArgumentException {

		return this.nutzerMapper.findByEmail(email);
	}
	
	public Vector<Nutzer> findAllNutzer() throws IllegalArgumentException {
		return this.nutzerMapper.findAllNutzer();
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Nutzer seinen Nutzernamen setzen
	 * möchten. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst
	 * die Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Nutzername, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarNutzer(String nutzername) throws IllegalArgumentException {
		if (this.nutzerMapper.findByName(nutzername) == null) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Diese Methode wird aufgerufen wenn ein neuer Nutzer erstellt wird.
	 * 
	 * @param Email und Nutzername des neu zu erstellenden Nutzers
	 * @throws IllegalArgumentException
	 * @author roland
	 * @author alina
	 * 
	 */

	public Nutzer createNutzer(String email, String nutzername) throws IllegalArgumentException {
		// Erzeugen eines neuen Nutzerobjekts
		Nutzer nutzer = new Nutzer();
		nutzer.setEmail(email);
		// Wenn der Name verfügbar ist...
		if (nameVerfügbarNutzer(nutzername)) {
			nutzer.setNutzername(nutzername);
		}

		this.nutzerMapper.insert(nutzer);

		return nutzer;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Nutzerobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Nutzerobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Nutzer nutzer) throws IllegalArgumentException {
		this.nutzerMapper.update(nutzer);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Gruppe-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn ein Gruppenobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Gruppenobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Gruppe gruppe) throws IllegalArgumentException {
		this.gruppeMapper.update(gruppe);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppen angezeigt werden sollen.
	 * 
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Gruppe> getAllGruppen() throws IllegalArgumentException {
		return this.gruppeMapper.findAllGruppe();
	}


	/**
	 * Diese Methode wird aufgerufen, wenn eine Gruppe durch ihren Namens
	 * gefunden werden soll.
	 * 
	 * @param Name der Gruppe 
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Gruppe findGruppeByName(String gruppenname) throws IllegalArgumentException {
		return this.gruppeMapper.findByGruppenname(gruppenname);
	}
	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppen eines Nutzers angezeigt
	 * werden sollen.
	 * 
	 * @param Nutzerobjekt dessen Gruppen gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Gruppe> getAllGruppenOfNutzer(Nutzer nutzer) throws IllegalArgumentException {
		return this.gruppeMapper.getGruppenOf(nutzer);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppenmitglieder einer Gruppe
	 * angezeigt werden sollen.
	 * 
	 * @param Gruppenobjekt, dessen Nutzer gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Nutzer> getAllNutzerOfGruppe(Gruppe gruppe) throws IllegalArgumentException {
		return this.nutzerMapper.getGruppenmitgliederOf(gruppe);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Gruppen angezeigt werden sollen, die
	 * zu einer Umfrage gehören.
	 * 
	 * @param Umfrageobjekt dessen zugehörige Gruppen gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Gruppe> getAllGruppenOfUmfrage(Umfrage umfrage) throws IllegalArgumentException {
		return this.gruppeMapper.getGruppenOf(umfrage);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Owner einer Gruppe ermittelt werden
	 * soll.
	 * 
	 * @param Gruppenobjekt zu welchem der zugehörige Besitzer ermittelt werrden
	 *                      soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Nutzer getOwnerOfGruppe(Gruppe gruppe) throws IllegalArgumentException {
		int ownerid = this.gruppeMapper.getOwnerOf(gruppe.getID());
		Nutzer nutzer = this.nutzerMapper.findByID(ownerid);
		return nutzer;

	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Mitglied aus einer Gruppe entfernt
	 * werden soll.
	 * 
	 * @param Nutzer und Gruppe, aus welcher der Nutzer gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public void mitgliedEntfernen(Nutzer nutzer, Gruppe gruppe) throws IllegalArgumentException {
		this.gruppeMapper.deleteGruppenzugehörigkeit(nutzer.getID(), gruppe.getID());

	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Mitglied einer Gruppe
	 * hinzugefügt werden soll.
	 * 
	 * @param Nutzer und Gruppe, zu welcher der Nutzer hinzugeügt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Gruppe mitgliedHinzufügen(Nutzer nutzer, Gruppe gruppe) throws IllegalArgumentException {
		this.gruppeMapper.insertGruppenzugehörigkeit(nutzer.getID(), gruppe.getID());
		return gruppe;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Name einer Gruppe gesetzt werden
	 * soll. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Gruppenname, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarGruppe(String gruppenname) throws IllegalArgumentException {
		if (this.gruppeMapper.findByGruppenname(gruppenname) == null) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Gruppe erstellt wird. Diese
	 * Realisierung ist nicht besonders elegant, aber das Attribut gruppenmitglieder
	 * erwartet Integer-Werte, welche die IDs der einzelnen Nutzerobjekte
	 * darstellen.
	 * 
	 * @param Nuter, welcher als Owner der Gruppe hinterlegt wird; gewünschter
	 *               Gruppenname und ein Vector aus Nutzerobjekten, welche Mitglied
	 *               der Gruppe werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Gruppe createGruppe(Nutzer nutzer, String gruppenname, Vector<Nutzer> gruppenmitglieder)
			throws IllegalArgumentException {

		// Erstellen der des neuen Gruppenobjekts
		Gruppe gruppe = new Gruppe();

		// Realisierung des Konzept OwnedBusinessObject durch Setzen der OwnerID

		gruppe.setOwnerID(nutzer.getID());

		// Wenn der Name verfügbar ist
		if (nameVerfügbarGruppe(gruppenname)) {

			// Setzen des Namens der Gruppe
			gruppe.setGruppenname(gruppenname);
		}
		/**
		 * Erstellung eines leeren Vectors mit Integer Objekten, in welchem später die
		 * IDs der Gruppenmitglieder gespeichert werden
		 */
		Vector<Integer> gruppenmitgliederids = new Vector<Integer>();

		// Zunächst Prüfung, ob Vector nicht leer ist
		if (gruppenmitglieder != null) {

			// Iteration durch den Vector, um IDs zu bestimmen
			for (Nutzer n : gruppenmitglieder) {
				int id = n.getID();
				

				// Hinzufügen der IDs zum Zielvector, welcher später das Argument für das
				// Attribut gruppenmitglieder wird
				gruppenmitgliederids.add(id);
				

			}

		}
		// Setzen des Attributs gruppenmitglieder
		gruppe.setGruppenmitglieder(gruppenmitgliederids);

		// Einfügen des Gruppenobjekts in die Datenbank

		this.gruppeMapper.insert(gruppe);

		
		//Einfügen der Mitgliedschaft der anderen Gruppenmitglieder
		for (int id: gruppenmitgliederids) {
			this.gruppeMapper.insertGruppenzugehörigkeit(id, gruppe.getID());
		}
		
		// Der Nutzer, welcher die Gruppe anlegt, ist natürlich auch Gruppenmitglied
		// dieser Gruppe

		this.gruppeMapper.insertGruppenzugehörigkeit(nutzer.getID(), gruppe.getID());

	
		// Zurückgeben des Gruppenobjekts
		return this.gruppeMapper.findByID(gruppe.getID());

	}

	/*
	 * Diese Methode realisiert das Löschen einer Gruppe. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach gehören Umfragen zu Grupepen.
	 * Wird nun eine Gruppe gelöscht, so müssen auch deren Umfragen gelöscht werden.
	 * 
	 * @param Gruppenonbjekt, welches gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */

	public void deleteGruppe(Gruppe gruppe) throws IllegalArgumentException {

		// Wir suchen alle Umfragen, die zu dieser Gruppe ghehören und speichern
		// diese in einem Zwischenvector

		Vector<Umfrage> umfragevector = this.umfrageMapper.findByGruppe(gruppe);

		/**
		 * Wir iterieren durch den Vector mit Umfragen und rufen für jede die Methode
		 * deleteUmfrage() auf.
		 */

		if (umfragevector != null) {
			for (Umfrage umfrage : umfragevector) {

				deleteUmfrage(umfrage);

			}
		}
		// Zuletzt löschen wir unsere übergebene Gruppe aus der Datenbank
		this.gruppeMapper.delete(gruppe);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Umfrage-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn eine Umfrage in der Datenbank gespeichert
	 * wird.
	 * 
	 * @param Umfrageobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Umfrage umfrage) throws IllegalArgumentException {

		this.umfrageMapper.update(umfrage);

	}

	/**
	 * Diese Methode wird aufgerufen, wenn nach einer Umfrage mittels ihrer ID
	 * gesucht werden soll.
	 * 
	 * @param Umfrageobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Umfrage findUmfrageByID(int id) throws IllegalArgumentException {
		return this.umfrageMapper.findByID(id);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Name einer Umfrage gesetzt werden
	 * soll. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Umfragenname, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarUmfrage(String umfragenname) throws IllegalArgumentException {
		if (this.umfrageMapper.findByUmfragenname(umfragenname) == null) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine neue Umfrage erstellt wird. 
	 * 
	 * @param Name der Umfrage, Film, Gruppe und Datum an dem der Film gesehen werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Umfrage createUmfrage(String umfragenname, Film film, Gruppe gruppe, Date datum) throws IllegalArgumentException {
		// Erzeugen eines neuen Umfrageobjekts
		Umfrage umfrage = new Umfrage();

		// Wenn der Name verfügbar ist...
		if (nameVerfügbarUmfrage(umfragenname)) {

			umfrage.setUmfragenname(umfragenname);

			//			umfrage.setOwnerID(nutzer.getID());
			umfrage.setFilmID(film.getID());
			umfrage.setGruppenID(gruppe.getID());
			umfrage.setDatum(datum);
			this.umfrageMapper.insert(umfrage);

			//Aufruf der Methode createUmfrageeinträge, um dieser Umfrage Einträge hinzuzufügen
			//			createUmfrageeinträge(umfrage, film, datum);

		}
		return umfrage;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle in der Datenbank gespeicherten
	 * Umfragen ausgeben möchten.
	 * 
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Umfrage> showAllUmfrage() throws IllegalArgumentException {

		return this.umfrageMapper.findAllUmfrage();

	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Owner einer Umfrage ermittelt werden
	 * soll.
	 * 
	 * @param Umfrageobjekt zu welchem der zugehörige Besitzer ermittelt werrden
	 *                      soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Nutzer getOwnerOfUmfrage(Umfrage umfrage) throws IllegalArgumentException {
		int ownerid = this.umfrageMapper.getOwnerOf(umfrage.getID());
		Nutzer nutzer = this.nutzerMapper.findByID(ownerid);
		return nutzer;

	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Nutzers ausgeben
	 * möchten. Das heißt wir suchen nach den Gruppen des Nutzers und hier wiederum
	 * nach den Umfragen, welche zu den Gruppen gehören und geben diese aus.
	 * 
	 * @param Nutzer dessen Umfragen ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfrageOfNutzer(Nutzer nutzer) throws IllegalArgumentException {

		Vector<Umfrage> ergebnisvector = new Vector<Umfrage>();
		Vector<Gruppe> gruppevector = this.gruppeMapper.getGruppenOf(nutzer);
		for (Gruppe g : gruppevector) {
			ergebnisvector.addAll(this.umfrageMapper.findByGruppe(g));
		}
		return ergebnisvector;

	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Gruppe ausgeben
	 * möchten.
	 * 
	 * @param Gruppe deren Umfragen ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfragenOfGruppe(Gruppe gruppe) throws IllegalArgumentException {

		return this.umfrageMapper.findByGruppe(gruppe);

	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen zu einem Film ausgeben
	 * möchten.
	 * 
	 * @param Film zu welchem die Umfragen ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Umfrage> showAllUmfragenOFilm(Film film) throws IllegalArgumentException {

		Vector<Umfrage> ergebnis = new Vector<Umfrage>();
		Vector<Umfrage> alleUmfragen = umfrageMapper.findAllUmfrage();
		for (Umfrage umfrage : alleUmfragen) {
			if (umfrage.getFilmID() == film.getID()) {
				ergebnis.add(umfrage);

			}
		}

		return ergebnis;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfragen eines Nutzers, welche
	 * noch kein Ergebnis haben, ausgeben möchten. Das heißt wir suchen nach den
	 * Gruppen des Nutzers und hier wiederum nach den Umfragen, welche zu den
	 * Gruppen gehören. Wir iterieren durch die Umfrageeinträge durch und schauen ob
	 * einer der Einträge als finales Ergebnis markiert ist. Ist kein Eintrag als
	 * final markiert, so geben wir all diese Umfragen zurück.
	 * 
	 * @param Nutzer dessen Umfragen angezeigt werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrage> showAllUmfrageOfNutzerOhneErgebnis(Nutzer nutzer) throws IllegalArgumentException {
		Vector<Umfrage> ergebnisvector = new Vector<Umfrage>();
		Vector<Gruppe> gruppevector = gruppeMapper.getGruppenOf(nutzer);
		for (Gruppe g : gruppevector) {
			ergebnisvector.addAll(this.umfrageMapper.findByGruppe(g));
		}
		for (Umfrage u : ergebnisvector) {
			Vector<Umfrageeintrag> umfrageeinträge = this.umfrageeintragMapper.findByUmfrage(u);
			for (Umfrageeintrag eintrag : umfrageeinträge) {
				if (eintrag.getFinalesErgebnis() == null) {

				}
			}
		}
		return ergebnisvector;
	}

	/*
	 * Diese Methode realisiert das Löschen einer Umfrage. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach besteht eine Umfrage aus
	 * Umfrageeinträgen. Votum-Objekte können wiederum Umgfrageeinträgen zugehörig
	 * sein. Wird eine Umfrage gelöscht, so müssen auch die Umfrageeinträge und
	 * deren Votum-Objekte gelöscht werden.
	 * 
	 * @param Umfrageobjekt, welches gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */

	public void deleteUmfrage(Umfrage umfrage) throws IllegalArgumentException {

		// Wir suchen alle Umfrageeinträge, die zu dieser Umfrage ghehören und speichern
		// diese in einem Zwischenvector

		Vector<Umfrageeintrag> vectorumfrageeinträge = umfrageeintragMapper.findByUmfrage(umfrage);

		/**
		 * Wir iterieren durch den Vector mit Umfrageeinträgen und rufen für jeden die
		 * Methode deleteUmfrageeintrag() auf.
		 */

		if (vectorumfrageeinträge != null) {
			for (Umfrageeintrag umfrageeintrag : vectorumfrageeinträge) {

				deleteUmfrageeintrag(umfrageeintrag);

			}
		}
		// Zuletzt löschen wir unsere übergebene Umfrage aus der Datenbank
		umfrageMapper.delete(umfrage);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Umfrageeintrag-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen wird dann aufgerufen, wenn eine neue Umfrage
	 * erstellt wird. Ziel jeder Umfrage ist es, einen passenden Termin zu finden um
	 * mit einer bestimmten Gruppe zu einem bestimmten Tag in einen bestimmten Film
	 * zu gehen. Lediglich das Kino und die konkrete Uhrzeit sind abzustimmen. Um
	 * die Umfrage mit Einträgen zu befüllen, wird diese Methode aufgerufen. Es
	 * werden die Argumente umfrage, film und datum übergeben. Film und Datum werden
	 * vom Umfrageersteller bereitgestellt. Die Verbindung zur Umfrage geschieht
	 * dann "automatisch".
	 * 
	 * 
	 * @param Umfrage zu welcher die Einträge gehören, Film welcher angeschaut
	 *                werden soll und Datum zu welchem der Film gesehen werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Umfrageeintrag> createUmfrageeinträge(Umfrage umfrage, Film film, Date datum)
			throws IllegalArgumentException {

		// Wir erstellen einen Zielvector, in welchem wir die finalen Ergebnisse
		// speichern

		Vector<Umfrageeintrag> umfrageeinträge = new Vector<Umfrageeintrag>();

		/**
		 * Wir suchen alle Spielzeiten, die eine Vorstellung des gewünschten Films am
		 * gewünschten Datum bereitstellen. Wir speichern die Ergebnisse wieder
		 * zwischen.
		 */

		Vector<Spielzeit> spielzeiten = this.spielzeitMapper.findSpielzeitenByFilmAndByDate(film, datum);

		// Wir iterieren durch die Ergebnisse und erstellen zu jeder Spielzeit einen
		// neuen Umfrageeintrag
		for (Spielzeit spielzeit : spielzeiten) {
			Umfrageeintrag umfrageeintrag = new Umfrageeintrag();

			// Wir setzen die Beziehung zur Spielzeit
			umfrageeintrag.setSpielzeitID(spielzeit.getID());

			// Und fügen diesen Eintrag dem Zielvector hinzu
			umfrageeinträge.add(umfrageeintrag);

			// Außerdem setzen wir die Beziehung jedes Umfrageeintrags zur erstellten
			// Umfrage
			for (Umfrageeintrag u : umfrageeinträge) {
				u.setUmfrageID(umfrage.getID());

				// Wir fügen die Umfrageeinträge auch in die Datenbank ein

				this.umfrageeintragMapper.insert(u);

			}

		}
		// Zuletzt geben wir unseren Zielvector zurück
		return umfrageeinträge;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Umfrageeintrag-Objekt in der
	 * Datenbank gespeichert werden soll.
	 * 
	 * @param Umfrageeintrag, welcher gespeichert werden soll soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException {
		this.umfrageeintragMapper.update(umfrageeintrag);
	}

	/*
	 * Diese Methode wird benötgt, um alle Umfrageeinträge einer Umfrage zu finden.
	 * 
	 * @param Umfrage, zu welcher die Umfrageeinträge gefunden werden sollen
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */

	public Vector<Umfrageeintrag> showUmfrageeinträgeofUmfrage(Umfrage umfrage) throws IllegalArgumentException {

		return this.umfrageeintragMapper.findByUmfrage(umfrage);

	}

	/*
	 * Diese Methode realisiert das Löschen eines Umfrageeeintrags. Hier wird auch
	 * die Löschweitergabe betrachtet. Unserer Logik nach besteht eine Umfrage aus
	 * Umfrageeinträgen. Votum-Objekte können wiederum Umgfrageeinträgen zugehörig
	 * sein. Wird ein Umfrageeintrag gelöscht, so müssen auch deren Votum-Objekte
	 * gelöscht werden.
	 * 
	 * @param Umfrageeintrag welcher gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */

	public void deleteUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException {
		/**
		 * Wir suchen alle Votum-Objekte zu diesem Umfrageeintrag und speicehern diese
		 * in einem Vector. Anschließend iterieren wir durch diesen und rufen für jedes
		 * Votum-Objekt die deleteVotum()-Methode auf, welche dieses löscht,
		 */
		Vector<Votum> votumvector = this.votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);
		if (votumvector != null) {
			for (Votum votum : votumvector) {
				deleteVotum(votum);
			}
			// Zuletzt löschen wir den übergebenen Umfrageeintrag aus der Datenbank
			this.umfrageeintragMapper.delete(umfrageeintrag);
		}

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Votum-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Votum-Objekt erzeugt werden
	 * soll
	 * 
	 * @param Umfrageeintrag zu welchem das Votum gesetzt werden soll, Boolean-Wert
	 *                       welcher entweder ein Ja-Votum oder ein Nein-Votum
	 *                       repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Votum createVotum(Umfrageeintrag umfrageeintrag, Boolean istMöglicherTermin)
			throws IllegalArgumentException {
		Votum votum = new Votum();
		votum.setUmfrageeintragID(umfrageeintrag.getID());
		votum.setIstMöglicherTermin(istMöglicherTermin);
		votum.setOwnerID(nutzer.getID());
		this.votumMapper.insert(votum);

		return votum;
	}

	/**
	 * Diese Methode realisiert das Löschen eines Votumobjekts.
	 * 
	 * @param Votumobjekt, welches gelöscht werden soll
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author alina
	 */

	public void deleteVotum(Votum votum) throws IllegalArgumentException {

		// Hier wird das übergebene Votum-Objekt aus der Datenbank gelöscht.
		this.votumMapper.delete(votum);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Votum-Objekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Votumobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Votum votum) throws IllegalArgumentException {
		this.votumMapper.update(votum);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Vota zu einem Umfrageeintrag
	 * angezeigt werden sollen. Hierzu wird die Mapper Methode @link
	 * findVotumByUmfrageeintrag aufgerufen, welche uns einen Vector von Objekten
	 * der Klasse <Votum> zurückgibt.
	 * 
	 * @param Umfrageeintrag, zu welchem die Votum-Objekte ausgegeben werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Votum> showVotumOfUmfrageeintrag(Umfrageeintrag umfrageeintrag) throws IllegalArgumentException {
		return this.votumMapper.findVotumByUmfrageeintrag(umfrageeintrag);

	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Votum-Objekte gefunden werden
	 * sollen.
	 * 
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Vector<Votum> showAllVotum() throws IllegalArgumentException {
		return this.votumMapper.findAllVotum();

	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein bestimmtes von dem die ID bekannt ist
	 * Votum-Objekt gefunden werden soll.
	 * 
	 * @param VotumID
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Votum findVotumByID(int votumid) throws IllegalArgumentException {
		return this.votumMapper.findByID(votumid);

	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Owner eines Votums ermittelt werden
	 * soll.
	 * 
	 * @param Votumobjekt zu welchem der Owner ermittelt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Nutzer findOwnerOfVotum(Votum votum) throws IllegalArgumentException {
		int ownerid = this.ownedBusinessObjectMapper.getOwnerOf(votum.getID());
		Nutzer nutzer = this.nutzerMapper.findByID(ownerid);
		return nutzer;
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden zur Realisierung des Abstimmens und der
	 * Bestimmung der Umfrageergebnisse
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle Umfrageeinträge und deren Vota
	 * anzeigen möchten. Wir übergeben eine Umfrage, deren Ergebnisse wir darstellen
	 * möchten. Wir erhalten die Umfrageeinträge der Umfrage zurück.
	 * 
	 * @param Umfrage deren Ergebnisse angezeigt werden sollen
	 * @author alina
	 */
	public Vector<Umfrageeintrag> umfrageergebnisseAnzeigen(Umfrage umfrage) throws IllegalArgumentException {
		// Zunächst erstellen wir einen leeren Vector
		Vector<Umfrageeintrag> umfrageeinträge = new Vector<Umfrageeintrag>();
		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der
		// verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal = 0;
		// Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage
		umfrageeinträge = this.umfrageeintragMapper.findByUmfrage(umfrage);
		// Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag : umfrageeinträge) {
			// Diese speichern wir wieder in einem Vector
			Vector<Votum> zwischenvector = this.votumMapper.findVotumByUmfrageeintrag(eintrag);
			/**
			 * Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob
			 * das Votum positiv, negativ oder neutral war. Diese Ergebnisse speichern wir
			 * uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen.
			 * 
			 * 
			 */
			for (Votum votum : zwischenvector) {
				if (votum.getIstMöglicherTermin() == true) {
					positiv += 1;

				} else if (votum.getIstMöglicherTermin() == false) {
					negativ += 1;
				}

				else if (votum.getIstMöglicherTermin() == null) {
					egal += 1;
				}
			}

		}
		return umfrageeinträge;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir überprüfen wollen, ob zu einer
	 * Umfrage ein passendes Ergebnis gefunden wurde. Sollte eines gefunden worden
	 * sein, so geben wir true zurück.
	 * 
	 * @param Umfrage deren Ergebnis ermittelt werden soll
	 * @author alina
	 */
	public Boolean ergebnisFinden(Umfrage umfrage) throws IllegalArgumentException {

		// Zunächst erstellen wir einen leeren Vector
		Vector<Umfrageeintrag> umfrageeinträge = new Vector<Umfrageeintrag>();

		// Diese Variable repräsentiert den Eintrag mit den meisten positiven
		// Abstimmungen
		Umfrageeintrag max = null;

		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der
		// verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal = 0;

		// Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage

		umfrageeinträge = this.umfrageeintragMapper.findByUmfrage(umfrage);

		// Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag : umfrageeinträge) {

			// Diese speichern wir wieder in einem Vector
			Vector<Votum> zwischenvector = this.votumMapper.findVotumByUmfrageeintrag(eintrag);

			/**
			 * Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob
			 * das Votum positiv, negativ oder neutral war. Diese Ergebnisse speichern wir
			 * uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen.
			 * 
			 * 
			 */
			for (Votum votum : zwischenvector) {
				if (votum.getIstMöglicherTermin() == true) {
					positiv += 1;
					eintrag.setPositiveAbstimmungen(positiv);

				} else if (votum.getIstMöglicherTermin() == false) {
					negativ += 1;
				}

				else if (votum.getIstMöglicherTermin() == null) {
					egal += 1;
				}

			}
			// Suche nach dem Eintrag, welcher die meisten positiven Abstimmungen hat
			if (max == null) {
				max = eintrag;

			} else if (max.getPositiveAbstimmungen() < eintrag.getPositiveAbstimmungen()) {

				max = eintrag;

			}

		}
		/**
		 * Sollte sich das Ergebnis doppeln, so geben wir false zurück und es konnte
		 * kein eindeutiges Ergebnis gefunden werden. Nun müssen wir das Ergebnis auf
		 * einem anderen Weg bestimmen.
		 */

		for (Umfrageeintrag eintrag : umfrageeinträge) {
			if (max.getPositiveAbstimmungen() == eintrag.getPositiveAbstimmungen()) {
				return false;
			}

		}

		return true;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir den Umfrageeintrag einer Umfrage mit
	 * den meisten Stimmen ermitteln wollen.
	 * 
	 * @param Umfrage deren Ergebnis ermittelt werden soll
	 * @author alina
	 */
	public Umfrageeintrag bestesErgebnisErmitteln(Umfrage umfrage) throws IllegalArgumentException {

		// Zunächst erstellen wir einen leeren Vector
		Vector<Umfrageeintrag> umfrageeinträge = new Vector<Umfrageeintrag>();

		// Diese Variable repräsentiert den Eintrag mit den meisten positiven
		// Abstimmungen
		Umfrageeintrag max = null;

		// In den folgenden drei Variablen positiv, negativ und egal wird die Anzahl der
		// verschiedenen Vota auf einen Umfrageeintrag festgehalten.
		int positiv = 0;
		int negativ = 0;
		int egal = 0;

		// Nun suchen wir alle Umfrageeinträge der übergegebenen Umfrage

		umfrageeinträge = this.umfrageeintragMapper.findByUmfrage(umfrage);

		// Wir iterieren durch die Umfrageeinträge durch und suchen die Votum-Objekte
		for (Umfrageeintrag eintrag : umfrageeinträge) {

			// Diese speichern wir wieder in einem Vector
			Vector<Votum> zwischenvector = this.votumMapper.findVotumByUmfrageeintrag(eintrag);

			/**
			 * Wir iterieren durch den Vector mit Votum-Objekten und ermitteln jeweils, ob
			 * das Votum positiv, negativ oder neutral war. Diese Ergebnisse speichern wir
			 * uns. Wir möchten die Ergebnisse neben dem jeweiligen Umfrageeintrag anzeigen.
			 * 
			 * 
			 */
			for (Votum votum : zwischenvector) {
				if (votum.getIstMöglicherTermin() == true) {
					positiv += 1;
					eintrag.setPositiveAbstimmungen(positiv);

				} else if (votum.getIstMöglicherTermin() == false) {
					negativ += 1;
				}

				else if (votum.getIstMöglicherTermin() == null) {
					egal += 1;
				}

			}
			// Suche nach dem Eintrag, welcher die meisten positiven Abstimmungen hat
			if (max == null) {
				max = eintrag;

			} else if (max.getPositiveAbstimmungen() < eintrag.getPositiveAbstimmungen()) {

				max = eintrag;

			}

		}
		// Rückgabe des Umfrageeintrags mit den meisten positiven Abstimmungen

		return max;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn es kein eindeutiges Ergebnis einer
	 * Umfrage gegeben hat und nun der Owner der Umfrage über deren Ausgang
	 * entscheidet. Das finale Ergebnis wird zurückgegeben.
	 * 
	 * @param Umfrage deren Ergebnis bestimmt werden soll und Umfrageeintrag der als
	 *                Ergebnis markiert wird
	 * @author alina
	 */
	public Umfrageeintrag finalesErgebnisBestimmen(Umfrage umfrage, Umfrageeintrag umfrageeintrag)
			throws IllegalArgumentException {
		// Nur der Owner der Umfrage soll die Möglichkeit bekommen, das finale Ergebnis
		// einer Umfrage zu bestimmen
		if (getOwnerOfUmfrage(umfrage) == nutzer) {
			umfrageeintrag.setFinalesErgebnis(true);

		}
		return umfrageeintrag;
	}

	public String sendMail(String from, String to, String replyTo, String subject, String message) {
		String output=null;
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from, "Cinemates Admin"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to, "Cinemates User"));
			msg.setSubject(subject);
			msg.setText(message);
			msg.setReplyTo(new InternetAddress[]{new InternetAddress(replyTo)});
			Transport.send(msg);

		} catch (Exception e) {
			output=e.toString();                
		}   
		return output;
	}
}
