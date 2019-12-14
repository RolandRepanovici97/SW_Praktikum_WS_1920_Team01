/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.FilmMapper;
import de.hdm.swprakt.cinemates.server.db.GruppeMapper;
import de.hdm.swprakt.cinemates.server.db.KinoMapper;
import de.hdm.swprakt.cinemates.server.db.KinoketteMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.SpielplanMapper;
import de.hdm.swprakt.cinemates.server.db.SpielzeitMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageMapper;
import de.hdm.swprakt.cinemates.server.db.UmfrageeintragMapper;
import de.hdm.swprakt.cinemates.server.db.VotumMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;



/**
 * Diese Klasse stellt die Implementierungsklasse des Interface
 * {@link KinoAdministration} dar. Sie beinhaltet die komplette
 * Applikationslogik, welche zur Verwaltung von Kinos, Kinoketten, Filmen,
 * Spielpläne und Spielzeiten benötigt wird.
 * 
 * @author Ömer
 * @version 1.0
 */
public class KinoAdministrationImpl extends RemoteServiceServlet implements KinoAdministration {

	/**
	 * Der Kinobetreiber benötigt Zuriff auf die Daten rund um die Verwaltung eines
	 * Kinos. Dieser Zugriff wird über die jeweiligen Mapper realisiert.
	 * 
	 */

	private KinoMapper kinoMapper = null;
	private KinoketteMapper kinoketteMapper = null;
	private FilmMapper filmMapper = null;
	private SpielzeitMapper spielzeitMapper = null;
	private SpielplanMapper spielplanMapper = null;
	private NutzerMapper nutzerMapper = null;

	public KinoAdministrationImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {

		this.kinoMapper = KinoMapper.kinoMapper();
		this.kinoketteMapper = KinoketteMapper.kinoketteMapper();
		this.filmMapper = FilmMapper.filmMapper();
		this.spielplanMapper = SpielplanMapper.spielplanMapper();
		this.nutzerMapper = NutzerMapper.nutzerMapper();

		KinoBesuchsplanungImpl kinoBesuchsplanungImpl = new KinoBesuchsplanungImpl();
		kinoBesuchsplanungImpl.init();
	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Film-Objekte
	 * ***************************************************************************
	 */


	/**
	 * Auslesen sämtliche Filme dieses Systems.
	 */

	public Vector<Film> getAllFilme() throws IllegalArgumentException {
		return this.filmMapper.findAll();
	}



	/**
	 * Speichern eines Films.
	 */
	public void saveFilm(Film f) throws IllegalArgumentException {
		filmMapper.update(f);
	}



	/**
	 * <p>
	 * Anlegen eines neuen Films. Dies führt implizit zu einem Speichern des neuen
	 * Films in der Datenbank.
	 * </p>
	 * 
	 * 
	 * 
	 * @see saveFilm(Film f) ????
	 */

	public Film createFilm(String filmtitel, String beschreibung, String details) throws IllegalArgumentException {
		
		
		Film f = new Film();
		f.setFilmtitel(filmtitel);
		f.setBeschreibung(beschreibung);
		f.setDetails(details);   // Detail anstatt Spiellänge

		/*
		 * Setzen einer vorläufigen Filmnr. Der insert-Aufruf liefert dann ein Objekt,
		 * dessen Nummer mit der Datenbank konsistent ist.
		 */
		f.setID(1);

		// Objekt in der DB speichern.
		return this.filmMapper.insert(f);
		// Filmnummer wird hier richtig eingesetzt
	}

	/**
	 * Löschen eines Films 
	 */

	public void deleteFilm(Film f) throws IllegalArgumentException{
		this.filmMapper.delete(f);
	}
	
	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Film-Objekte
	 * ***************************************************************************
	 */
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Kinokette-Objekte
	 * ***************************************************************************
	 */

	
	/**
	 * Auslesen aller Kinos der Kinokette
	 * Diese Methode wird bei delete Kinokette verwendet
	 */ 
	public Vector<Kino> getKinosOf(Kinokette kk) throws IllegalArgumentException {
		return this.kinoMapper.findKinosByKinokette(kk);
	}
	
	
	/**
	 * Auslesen sämtliche Kinoketten dieses Systems.
	 */
	public Vector<Kinokette> getAllKinokette() throws IllegalArgumentException {
		return this.kinoketteMapper.findAllKinoketten();
	}


	/**
	 * Löschen einer Kinokette
	 */

	public void deleteKinokette(Kinokette kk) throws IllegalArgumentException {

		Vector<Kino> kinos = this.getKinosOf(kk);

		if (kinos != null) { // defensives Porgrammieren
			for (Kino k : kinos) {
				this.deleteKino(k);
			}
		}

		this.kinoketteMapper.delete(kk);

	}

	/**
	 * Hinzufügen eines Kinos zur Kinokette
	 */

	public Kino addKinoToKinokette(Kinokette kinokette) throws IllegalArgumentException {
		Kino kino = new Kino(); 
		kinoMapper.insert(kino);
		kino.setKinoketteID(kinokette.getID());

		/*
		 * Setzen einer vorläufigen Kinokette Nr. Der insert-Aufruf liefert dann ein
		 * Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		kino.setID(1);

		// Objekt in der DB speichern.
		return this.kinoMapper.insert(kino);
		// Kinookettennummer wird hier richtig eingesetzt

	}

	// neue Methode Kinokette vom curent User anzeigen.
	//
	//

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Kinokette-Objekte
	 * ***************************************************************************
	 */


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Kino-Objekte
	 * ***************************************************************************
	 */



	/**
	 * Auslesen sämtliche Kinos dieses Systems.
	 */

	public Vector<Kino> getAllKinos() throws IllegalArgumentException {
		return this.kinoMapper.findAll();
	}

	/**
	 * Hinzfügen eines Kinos dieses Systems.
	 */
	
	public Kino createKino(String kinoname, String adresse, String beschreibung) throws IllegalArgumentException {
		Kino k = new Kino();
		k.setKinoname(kinoname);
		k.setAdresse(adresse);
		k.setBeschreibung(beschreibung);


		/*
		 * Setzen einer vorläufigen KinoNr. Der insert-Aufruf liefert dann ein Objekt,
		 * dessen Nummer mit der Datenbank konsistent ist.
		 */
		k.setID(1);

		// Objekt in der DB speichern.
		return this.kinoMapper.insert(k);
		// Kinonummer wird hier richtig eingesetzt
	}
	
	
	/**
	 * Auslesen des Spielplans des Kinos Diese Methode wird bei deleteKino verwendet
	 */

	public Spielplan getSpielplanOf(Kino k) throws IllegalArgumentException {
		return this.spielplanMapper.findByKino(k); 
	}
	
	
	/**
	 * Auslesen der Spielzeiten des Spielplans Diese Methode wird bei
	 * deleteSpielplan verwendet.
	 */
	public Vector<Spielzeit> getSpielzeitOf(Spielplan sp) throws IllegalArgumentException {
		return this.spielzeitMapper.findSpielzeitenBySpielplan(sp);

	}


	/** Diese Methode realisiert das Löschen eines Kinos. Hier wird die Löschweitergabe realisiert. Unserer Logik nach
	 * besitzt ein Kino einen Spielplan, welcher wiederum aus Spielzeit besteht. Wird ein Kino gelöscht, so
	 * muss auch der dazugehörige Spielplan gelöscht werden und wird dieser gelöscht müssen alle darin enthalten Spielzeiten
	 * gelöscht.
	 */
//	public void deleteKino(Kino kino) throws IllegalArgumentException {
//		
//		// Wir suchen den Spielplan des zu löschenden Kinos
//		Spielplan kinoSpielplan = spielplanMapper.findByKino(kino);
//		// Wir suchen alle Spielzeiten dieses Spielplans
//		Vector<Spielzeit> vectorspielzeiten = spielzeitMapper.findSpielzeitenBySpielplan(kinoSpielplan);
//		// Wir löschen den Spielplan aus der Datebank
//		spielplanMapper.delete(kinoSpielplan);
//		//Wir iterieren durch den Vector der Spielzeiten und löschen alle dort enhaltenen Spielzeiten
//		for (Spielzeit spielzeit : vectorspielzeiten) {
//			spielzeitMapper.delete(spielzeit);
//
//		}
//
//		// Zuletzt löschen wir das Kino aus der Datenbank
//		this.kinoMapper.delete(kino);
//	}

	public void deleteKino(Kino k) throws IllegalArgumentException {

		Spielplan spielplan = this.getSpielplanOf(k);

		// Vector<Spielzeit> spielzeiten = //this.getSpielzeitOf(k);

		if (spielplan != null) {
			this.deleteSpielplan(spielplan); // wenn kino gelöscht wird dann auch hier der Spielplan
		}

		this.kinoMapper.delete(k);
	}
	
	

	/**
	 * Löschen eines Spielplans
	 * Löschweitergabe: Die dazugehörigen Spielzeiten werden auch gelöscht.
	 */
	
	public void deleteSpielplan(Spielplan sp) throws IllegalArgumentException {
		
		Vector<Spielzeit> spielzeiten = this.getSpielzeitOf(sp);
		
		if (spielzeiten != null) {
			for(Spielzeit sz:spielzeiten) {
				this.deleteSpielzeit(sz);
			}
		}
		this.spielplanMapper.delete(sp);
	}

	/**Diese Methode wird aufgerufen,um alle Kinos die zu einer Kinokette gehört angezeigt werden sollen.
	 * Die Mapper Methode findKinosByKinokette wird aufgerufen.
	 */

	public Vector <Kino> getAllKinoOfKinokette(Kinokette kinokette) {
		
		return this.kinoMapper.findKinosByKinokette(kinokette);
	
	}

	
	/**
	 * Speichern eines Kinos.
	 */
	public void saveKino(Kino k) throws IllegalArgumentException {
		kinoMapper.update(k);
	}
	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Kino-Objekte
	 * ***************************************************************************
	 */


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Spielzeit-Objekte
	 * ***************************************************************************
	 */


	/**
	 * Auslesen sämtliche Spielzeiten dieses Systems.
	 */

	public Vector<Spielzeit> getAllSpielzeiten() throws IllegalArgumentException {
		return this.spielzeitMapper.findAllSpielzeit();
	}


	/**
	 * Hinzfügen einer Spielzeit
	 */

	public Spielzeit createSpielzeit(int filmID ,Date zeitpunkt) throws IllegalArgumentException {
			
		Spielzeit sz = new Spielzeit();
		sz.setFilmID(filmID);
		sz.setZeitpunkt(zeitpunkt);
		
		
		/**Setzen einer vorläufigen Filmnr. Der insert-Aufruf liefert dann ein Objekt,
		dessen Nummer mit der Datenbank konsistent ist.
		*/
			sz.setID(1);

				// Objekt in der DB speichern.
			return this.spielzeitMapper.insert(sz);
				// Filmnummer wird hier richtig eingesetzt
	}
		
	
	/**
	 * Löschen eines Spielzeits.
	 */

	public void deleteSpielzeit(Spielzeit sz) throws IllegalArgumentException {

		this.spielzeitMapper.delete(sz);

	}

	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Spielzeit-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Spielplan-Objekte
	 * ***************************************************************************
	 */


	/**
	 * Auslesen sämtliche Spielpläne dieses Systems.
	 */

	public Vector<Spielplan> getAllSpielplan() throws IllegalArgumentException {
		return this.spielplanMapper.findAllSpielplan();
	}



	/**
	 * Speichern eines Spielplans.
	 */
	
	public void saveSpielplan(Spielplan sp) throws IllegalArgumentException {
		spielplanMapper.update(sp);
	}



	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Spielplan-Objekte
	 * ***************************************************************************
	 */


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************************************************************
	 */


	/**
	 * Speichern des Nutzers
	 * 
	 * @param nutzer
	 * @return
	 * @throws IllegalArgumentException
	 */

	public Nutzer saveNutzer(Nutzer nutzer) throws IllegalArgumentException {  //Nutzername übergeben 
		return this.nutzerMapper.update(nutzer);
		
	}

	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nutzer-Objekte
	 * ***************************************************************************
	 */


}
