/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.FilmMapper;
import de.hdm.swprakt.cinemates.server.db.KinoMapper;
import de.hdm.swprakt.cinemates.server.db.KinoketteMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.OwnedBusinessObjectMapper;
import de.hdm.swprakt.cinemates.server.db.SpielplanMapper;
import de.hdm.swprakt.cinemates.server.db.SpielzeitMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * Diese Klasse stellt die Implementierungsklasse des Interface
 * {@link KinoAdministration} dar. Sie beinhaltet die komplette
 * Applikationslogik, welche zur Verwaltung von Kinos, Kinoketten, Filmen,
 * Spielpläne und Spielzeiten benötigt wird.
 * 
 * @author Ömer
 * @author alina
 * @version 1.0
 * @see KinoAdministration, KinoAdministrationAsync
 */
public class KinoAdministrationImpl extends RemoteServiceServlet implements KinoAdministration {

	/**
	 * Zur Serialisierung benötigt
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Referenz auf die Kinobesuchsplanung
	 */

	private KinoBesuchsplanungImpl kinobesuchsplanung;

	/**
	 * Referenz auf einen Nutzer
	 */

	private Nutzer nutzer;

	/**
	 * Der Kinobetreiber benötigt Zuriff auf die Daten rund um die Verwaltung eines
	 * Kinos. Dieser Zugriff wird über die jeweiligen Mapper realisiert.
	 * 
	 */

	/**
	 * Referenz auf den KinoMapper
	 * 
	 * @link KinoMapper
	 */
	private KinoMapper kinoMapper = null;
	/**
	 * Referenz auf den KinoketteMapper
	 * 
	 * @link KinoketteMapper
	 */
	private KinoketteMapper kinoketteMapper = null;
	/**
	 * Referenz auf den FilmMapper
	 * 
	 * @link FilmMapper
	 */
	private FilmMapper filmMapper = null;
	/**
	 * Referenz auf den SpielzeitMapper
	 * 
	 * @link SpielzeitMapper
	 */
	private SpielzeitMapper spielzeitMapper = null;
	/**
	 * Referenz auf den SpielplanMapper
	 * 
	 * @link SpielplanMapper
	 */
	private SpielplanMapper spielplanMapper = null;

	/**
	 * Referenz auf den UmfrageMapper
	 * 
	 * @link UmfrageMapper
	 */

	private UmfrageMapper umfrageMapper = null;
	/**
	 * Referenz auf den NutzerMapper
	 * 
	 * @link NutzerMapper
	 */
	private NutzerMapper nutzerMapper = null;
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

	public KinoAdministrationImpl() throws IllegalArgumentException {

	}

	/*
	 * Initalisierung der Variablen, welche die Referenzen auf die Mapeprklassen
	 * darstellen. Wir initialisieren diese durch den Aufruf des
	 * protected-Konstruktors. Dieser ermöglicht uns, dass jeweils nur eine Instanz
	 * dieser Klasse erzeugt werden kann.
	 */

	@Override
	public void init() throws IllegalArgumentException {

		this.kinoMapper = KinoMapper.kinoMapper();
		this.kinoketteMapper = KinoketteMapper.kinoketteMapper();
		this.filmMapper = FilmMapper.filmMapper();
		this.spielplanMapper = SpielplanMapper.spielplanMapper();
		this.spielzeitMapper = SpielzeitMapper.spielzeitMapper();
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.umfrageMapper = UmfrageMapper.umfrageMapper();
		this.ownedBusinessObjectMapper = OwnedBusinessObjectMapper.ownedBusinessObjectMapper();

		// Referenz auf die Kinobesuchsplanung
		kinobesuchsplanung = new KinoBesuchsplanungImpl();
//		kinobesuchsplanung.init();

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Film-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn ein Film anhand seiner ID gefunden werden
	 * soll
	 * 
	 * @param Integer Wert, welcher die ID des Films repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Film getFilmByID(int id) throws IllegalArgumentException {
		return this.filmMapper.findByID(id);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Film anhand seines Titels gefunden
	 * werden soll
	 * 
	 * @param Filmtitel
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public Film getFilmByTitel(String filmtitel) throws IllegalArgumentException {
		return this.filmMapper.findByFilmtitel(filmtitel);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn wir alle in der Datenbank gespeicherten
	 * Filme erhalten möchten.
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public Vector<Film> getAllFilme() throws IllegalArgumentException {
		return this.filmMapper.findAllFilme();
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Filmobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Filmobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void save(Film film) throws IllegalArgumentException {
		this.filmMapper.update(film);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Film erstellt wird.
	 * 
	 * @param Filmtitel, Filmbeschreibung und Details (Die Details repräsentieren im
	 *                   Grunde lediglich die Länge des Films)
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 */

	public Film createFilm(String filmtitel, String beschreibung, String details) throws IllegalArgumentException {

		// Erstellen der des neuen Gruppenobjekts
		Film film = new Film();

		/**
		 * Setzen der Attribute des Filmobjekts
		 */

		// Wenn der Filmtitel verfügbar ist...

		if (nameVerfügbarFilm(filmtitel)) {

			film.setFilmtitel(filmtitel);
		}
		film.setBeschreibung(beschreibung);
		film.setDetails(details);

		/*
		 * Setzen einer vorläufigen Filmnr. Der insert-Aufruf liefert dann ein Objekt,
		 * dessen Nummer mit der Datenbank konsistent ist.
		 */
		film.setID(1);

		// Objekt in der DB speichern.
		this.filmMapper.insert(film);
		// Filmnummer wird hier richtig eingesetzt

		// Rückgabe des erstellten Filmobjekts
		return film;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Titel eines Films gesetzt werden
	 * soll. Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Filmtitel, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarFilm(String filmtitel) throws IllegalArgumentException {
		if (this.filmMapper.findByFilmtitel(filmtitel) == null) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Diese Methode realisiert das Löschen eines Films. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach gehören Spielzeiten zu Filmen.
	 * Wird ein Film gelöscht, so müssen auch alle Spielzeiten, zu denen dieser Film
	 * angeboten wird gelöscht werden. Außerdem gehört eine Umfrage zu einem Film.
	 * Wird der Film gelöscht, so müssen auch alle Umfragen gelöscht werden, welche
	 * zu diesem Film gehören. Um dies zu realisieren bedienen wir uns der Klasse,
	 * welche die Applikationslogik für Umfragen bereitstellt und rufen die dort
	 * definierte Löschmethode für Umfragen auf.
	 * 
	 * @param Filmobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */
	public void deleteFilm(Film film) throws IllegalArgumentException {

		// Wir suchen alle Spielzeiten, die zu diesem Film gehören und speichern diese
		// in einem Zwischenvector

		Vector<Spielzeit> spielzeitvector = this.spielzeitMapper.findSpielzeitenByFilm(film);

		// Wir suchen alle Umfragen zu diesem Film und speichern diese in einem
		// Zwischenvector
		Vector<Umfrage> umfragevector = this.umfrageMapper.findByFilm(film);

		/**
		 * Wir iterieren durch den Vector mit Spielzeiten und rufen für jede die Methode
		 * deleteSpielzeit() auf.
		 */

		// Zunächst prüfen wir, ob es überhaupt Spielzeiten zu diesem Film gibt

		if (spielzeitvector != null) {
			for (Spielzeit spielzeit : spielzeitvector) {

				deleteSpielzeit(spielzeit);
			}

		}

		/**
		 * Wir iterieren durch den Vector mit Umfragen und rufen für jede die Methode
		 * deleteUmfrage auf.
		 */

		// Zunächst prüfen wir, ob es überhaupt Umfragen zu diesem Film gibt

		if (umfragevector != null) {
			for (Umfrage umfrage : umfragevector) {
				this.kinobesuchsplanung.deleteUmfrage(umfrage);
			}
		}

		// Zuletzt löschen wir unseren übergebenen Film aus der Datenbank

		this.filmMapper.delete(film);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Kinokette-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn eine Kinokette anhand ihrer ID gefunden
	 * werden soll
	 * 
	 * @param Integer Wert, welcher die ID der Kinokette repräsentiert
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public Kinokette findKinoketteByID(int id) throws IllegalArgumentException {
		return this.kinoketteMapper.findByID(id);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Kinokette anhand ihres Namens
	 * gefunden werden soll
	 * 
	 * @param Kinokettenname
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public Kinokette findKinoketteByName(String kinokettenname) throws IllegalArgumentException {
		return this.kinoketteMapper.findByKinokettenname(kinokettenname);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Kinoketten, welche dem System
	 * bekannt sind, ausgegeben werden sollen
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public Vector<Kinokette> getAllKinokette() throws IllegalArgumentException {
		return this.kinoketteMapper.findAllKinoketten();
	}

	/**
	 * Diese Methode wird aufgerufen, wenn sich ein Kinobetreiber das erste mal auf
	 * dem CineMates Admin-Client einloggt. Unserer Logik nach gehört einem
	 * Kinobetreiber eine Kinokette, welche mehrere Kinos enthalten kann. Loggt er
	 * sich zum ersten Mal auf dem Admin-Client ein, so wird neben dem Nutzerobjekt
	 * auch als default eine Kinokette angelegt, welche zunächst leer ist, aber der
	 * er später Kinos hinzufügen kann.
	 * 
	 * @param Aktueller Nutzer: Kinobetreiber
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Kinokette createKinokette(Nutzer nutzer) throws IllegalArgumentException {
		// Erstellen einer neuen Kinokette
		Kinokette kinokette = new Kinokette();
		// Setzen des Owners auf den aktuellen Nutzer
		kinokette.setOwnerID(nutzer.getID());
		// Einfügen der Kinokette in die Datenbank
		this.kinoketteMapper.insert(kinokette);

		// Wir geben unser Kinoketteobjekt zurück
		return kinokette;
	}

	/**
	 * Diese Methode realisiert das Löschen einer Kinokette. Hier wird auch die
	 * Löschweitergabe betrachtet. Unserer Logik nach gehören Kinos zu Kinoketten.
	 * Wird eine Kinokette gelöscht, so müssen auch alle Kinos, welche zu dieser
	 * Kinokette gehören, gelöscht werden.
	 * 
	 * @param Kinoketteobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public void deleteKinokette(Kinokette kinokette) throws IllegalArgumentException {

		// Wir suchen alle Spielzeiten, die zu diesem Film gehören und speichern diese
		// in einem Zwischenvector

		Vector<Kino> kinovector = this.kinoMapper.findByKinokette(kinokette);

		/**
		 * Wir iterieren durch den Vector mit Spielzeiten und rufen für jede die Methode
		 * deleteSpielzeit() auf.
		 */

		// Zunächst prüfen wir, ob es überhaupt Kinos zu dieser Kinokette gibt

		if (kinovector != null) {
			for (Kino kino : kinovector)
				deleteKino(kino);
		}
		// Zuletzt löschen wir die übergebene Kinokette aus der Datenbank
		this.kinoketteMapper.delete(kinokette);

	}

	/**
	 * Diese Methode wird aufgerufen, wenn die Kinokette eines Nutzers zurückgegeben
	 * werden soll.
	 * 
	 * @param Nutzer dessen Kinokette benötigt wird
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Kinokette getKinoketteOf(Nutzer nutzer) throws IllegalArgumentException {

		return kinoketteMapper.findKinoketteByOwner(nutzer);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kinoketteobjekt aktualisiert werden
	 * soll.
	 * 
	 * @param Kinoketteobjekt, welches aktualisiert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Kinokette kinokette) throws IllegalArgumentException {
		this.kinoketteMapper.update(kinokette);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Kino-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kino anhand seiner ID gefunden werden
	 * soll
	 * 
	 * @param Integerwert, welcher die ID des Kinos repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Kino getKinoByID(int id) throws IllegalArgumentException {
		return this.kinoMapper.findByID(id);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kino anhand seines Namens gefunden
	 * werden soll
	 * 
	 * @param Name des Kinos
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Kino getKinoByName(String kinoname) throws IllegalArgumentException {
		return this.kinoMapper.findByKinoname(kinoname);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Kinos einer Kinokette ausgegeben
	 * werden sollen
	 * 
	 * @param Kinokette, deren Kinos gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Kino> getKinosOfKinokette(Kinokette kinokette) throws IllegalArgumentException {
		return this.kinoMapper.findByKinokette(kinokette);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Kinos, welche CineMates bekannt
	 * sind, ausgegeben werden sollen.
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public Vector<Kino> getAllKinos() throws IllegalArgumentException {
		return this.kinoMapper.findAllKino();
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Kinoobjekt erstellt wird.
	 * 
	 * @param Kinoname, Adresse, Beschreibung und Kinokette des aktuellen Nutzers
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 */
	public Kino createKino(String kinoname, String adresse, String beschreibung, Kinokette kinokette)
			throws IllegalArgumentException {
		// Erstellen eines neuen Kinoobjekts
		Kino kino = new Kino();

		// Wenn der Name verfügbar ist, dann...

		if (nameVerfügbarKino(kinoname)) {

			kino.setKinoname(kinoname);
			kino.setAdresse(adresse);
			kino.setBeschreibung(beschreibung);

		}

		/*
		 * Setzen einer vorläufigen KinoNr. Der insert-Aufruf liefert dann ein Objekt,
		 * dessen Nummer mit der Datenbank konsistent ist.
		 */
		kino.setID(1);

		// Kinoobjekt in der Datenbank speichern.
		this.kinoMapper.insert(kino);

		// Kinonummer wird hier richtig eingesetzt

		// Setzen der Zugehörigkeit zwischen Kino und Kinkette
		addKinoToKinokette(kino, kinokette);

		// Erstellung des Spielplans des Kinos
		createSpielplan(kino);

		// Zurückgeben des Kinoobjekts
		return kino;

	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Name eines Kinos gesetzt werden soll.
	 * Damit dieser nicht doppelt vergeben werden kann, erfolgt zunächst die
	 * Prüfung, ob dieser bereits vergeben ist.
	 * 
	 * @param Der Kinoname, welcher gesetzt werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Boolean nameVerfügbarKino(String kinoname) throws IllegalArgumentException {
		if (this.kinoMapper.findByKinoname(kinoname) == null) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein neues Kino angelegt wird.
	 * 
	 * @param Kino, Kinokette
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public void addKinoToKinokette(Kino kino, Kinokette kinokette) throws IllegalArgumentException {

		kino.setKinoketteID(kinokette.getID());
		this.kinoMapper.update(kino);

	}

	/**
	 * Diese Methode realisiert das Löschen eines Kinos. Hier wird die
	 * Löschweitergabe realisiert. Unserer Logik nach besitzt ein Kino einen
	 * Spielplan, welcher wiederum aus Spielzeit besteht. Wird ein Kino gelöscht, so
	 * muss auch der dazugehörige Spielplan gelöscht werden. Durch den Aufruf der
	 * Löschmethode des Spieplans werden anschließend alle Spielzeiten dieses
	 * Spielplans gelöscht.
	 * 
	 * @param Kinoobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 * 
	 */
	public void deleteKino(Kino kino) throws IllegalArgumentException {

		// Wir suchen den Spielplan des zu löschenden Kinos
		Spielplan spielplan = this.getSpielplanOfKino(kino);

		// Wir prüfen, ob das Kino einen Spielplan hat (sollte aber default schon einen
		// haben...)
		if (spielplan != null) {
			// Wir löschen diesen Spielplan
			deleteSpielplan(spielplan);
		}
		// Zuletzt löschen wir das Kino aus der Datenbank
		this.kinoMapper.delete(kino);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Kinoobjekt aktualisiert werden soll.
	 * 
	 * @param Spielplanobjekt, welches aktualisiert werden soll
	 * @throws IllegalArgumentException
	 */

	public void save(Kino kino) throws IllegalArgumentException {
		this.kinoMapper.update(kino);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Spielplan-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn der Spielplan eines übergebenen Kinos
	 * gefunden werden soll.
	 * 
	 * @param Kino dessen Spielplan gefunden werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Spielplan getSpielplanOfKino(Kino kino) throws IllegalArgumentException {
		return this.spielplanMapper.findByID(kino.getSpielplanID());
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Spielpläne, welche CineMates bekannt
	 * sind, ausgegeben werden sollen
	 * 
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Vector<Spielplan> getAllSpielplan() throws IllegalArgumentException {
		return this.spielplanMapper.findAllSpielplan();
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Spielplan eines übergebenen Kinos
	 * gefunden werden soll.
	 * 
	 * @param Integerwert, welcher die ID des Spielplans repräsentiert
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Spielplan getSpielplanByID(int id) throws IllegalArgumentException {
		return this.spielplanMapper.findByID(id);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Spielplanobjekt aktualisiert werden
	 * soll.
	 * 
	 * @param Spielplanobjekt, welches aktualisiert werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void save(Spielplan spielplan) throws IllegalArgumentException {
		this.spielplanMapper.update(spielplan);
	}

	/**
	 * Diese Methode wird immer dann aufgerufen, wenn ein neues Kino erstellt wird.
	 * Unserer Logik nach besitzt jedes Kino einen Spielplan. Wird ein neues Kino
	 * erstellt, so muss für dieses auch ein Spielplan erstellt werden. Dieser ist
	 * standardmäßig leer, kann aber durch hinzufügen von Spielzeiten befüllt
	 * werden. Hier geht es nur darum, ein lebensfähiges Spielplanobjekt zu
	 * erstellen.
	 * 
	 * @param Kino
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Spielplan createSpielplan(Kino kino) throws IllegalArgumentException {
		// Erstellen einer neuen Spielplans
		Spielplan spielplan = new Spielplan();

		// Setzen der Verbindung zum Kino
		kino.setSpielplanID(spielplan.getID());

		// Einfügen des Spielplans in die Datenbank

		this.spielplanMapper.insert(spielplan);

		// Wir geben unseren erstellten Spielplan zurück
		return spielplan;
	}

	/**
	 * Diese Methode realisiert das Löschen eines Kinos. Hier wird die
	 * Löschweitergabe realisiert. Unserer Logik nach besteht ein Spielplan aus
	 * Spielzeiten. Wird ein Spielplan gelöscht, so werden alle dort enthaltenen
	 * Spielzeiten gelöscht.
	 * 
	 * @param Spielplanobjekt, welches gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * 
	 */
	public void deleteSpielplan(Spielplan spielplan) throws IllegalArgumentException {

		// Wir suchen alle Spielzeiten, die zu diesem Spielplan gehören und speichern
		// diese in einem Zwischenvector

		Vector<Spielzeit> spielzeitvector = this.spielzeitMapper.findSpielzeitenBySpielplan(spielplan);
		// Wir prüfen, ob dieser Spielplan überhaupt Spielzeiten hat
		if (spielzeitvector != null) {
			for (Spielzeit spielzeit : spielzeitvector) {
				// Wir rufen für jedes Spielzeitobjekt die Methode deleteSpielzeit() auf
				deleteSpielzeit(spielzeit);
			}
		}
		// Zuletzt löschen wir unseren übergebenen Spielplan aus der Datenbank
		this.spielplanMapper.delete(spielplan);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Spielzeit-Objekte
	 * ***************************************************************************
	 */

	/**
	 * Diese Methode wird aufgerufen, wenn alle CineMates bekannten Spielpläne
	 * ausgegeben werden sollen
	 * 
	 * @param Integerwert, welcher die ID der Spielzeit repräsentiert
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */
	public Vector<Spielzeit> getAllSpielzeiten() throws IllegalArgumentException {
		return this.spielzeitMapper.findAllSpielzeit();
	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Spielzeitobjekt anhand seiner ID
	 * gefunden werden soll
	 * 
	 * @param Integerwert, welcher die ID der Spielzeit repräsentiert
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public Spielzeit getSpielzeitByID(int id) throws IllegalArgumentException {
		return this.spielzeitMapper.findByID(id);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn alle Spielzeiten eines Films gefunden
	 * werden sollen
	 * 
	 * @param Film dessen Spielzeiten gefunden werden sollen
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public Vector<Spielzeit> getSpielzeitenByFilm(Film film) throws IllegalArgumentException {
		return this.spielzeitMapper.findSpielzeitenByFilm(film);
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine neue Spielzeit erstellt werden soll.
	 * 
	 * @param Spielplan, filmID (Referenz auf den Film) und Zeitpunkt, zu welchem
	 *                   der Film vorgeführt werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 * @author alina
	 */
	public Spielzeit createSpielzeit(Spielplan spielplan, int filmID, Date zeitpunkt) throws IllegalArgumentException {

		// Erstellen eines neuen Spielplanobejkts
		Spielzeit spielzeit = new Spielzeit();

		spielzeit.setSpielplanID(spielplan.getID());
		spielzeit.setFilmID(filmID);
		spielzeit.setZeitpunkt(zeitpunkt);

		/**
		 * Setzen einer vorläufigen Filmnr. Der insert-Aufruf liefert dann ein Objekt,
		 * dessen Nummer mit der Datenbank konsistent ist.
		 */
		spielzeit.setID(1);

		// Objekt in der DB speichern.
		this.spielzeitMapper.insert(spielzeit);
		// Filmnummer wird hier richtig eingesetzt

		// Wir geben den erstellten Spielplan zurück
		return spielzeit;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Spielzeit gelöscht werden soll
	 * 
	 * @param Spielzeit welche gelöscht werden soll
	 * @throws IllegalArgumentException
	 * @author Ömer
	 */

	public void deleteSpielzeit(Spielzeit spielzeit) throws IllegalArgumentException {
		// Zunächst löschen wir die Spielzeit aus der Spielplan_Spielzeit Tabelle
		this.spielzeitMapper.deleteSpielzeitFromSpielplan(spielzeit.getID(), spielzeit.getSpielplanID());
		// Danach löschen wir die Spielzeit
		this.spielzeitMapper.delete(spielzeit);

	}

	/**
	 * Diese Methode wird aufgerufen, wenn ein Spielzeitobjekt in der Datenbank
	 * gespeichert werden soll.
	 * 
	 * @param Spielzeitobjekt, welches gespeichert werden soll
	 * @throws IllegalArgumentException
	 * @author alina
	 */

	public void save(Spielzeit spielzeit) throws IllegalArgumentException {
		this.spielzeitMapper.update(spielzeit);
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
		// Wenn der Name verügbar ist...
		if (nameVerfügbarNutzer(nutzername)) {

			nutzer.setNutzername(nutzername);
		}
		this.nutzerMapper.insert(nutzer);

		// Erstellene einer Kinokette für den Nutzer
		createKinokette(nutzer);

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

}
