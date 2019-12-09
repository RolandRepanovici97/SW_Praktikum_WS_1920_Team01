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
 * Applikationslogik, welche zur Verwaltung von Kinos, Kinoketten Filmen,
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
	  
	
	
	
	public Film editFilm(Film film) throws IllegalArgumentException {
		return this.filmMapper.update(film);
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
	 * ABSCHNITT, Beginn: Methoden für Kinokette-Objekte
	 * ***************************************************************************
	 */
	
	
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

		Vector<Kino> kinos = kinoMapper.findKinosByKinokette(kk);

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

	public Kinokette addKinoToKinokette(String kinokettenname, String beschreibung) throws IllegalArgumentException {
		Kinokette kk = new Kinokette();
		kk.setKinokettenname(kinokettenname);
		kk.setBeschreibung(beschreibung);

		/*
		 * Setzen einer vorläufigen Kinokette Nr. Der insert-Aufruf liefert dann ein
		 * Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		kk.setID(1);

		// Objekt in der DB speichern.
		return this.kinoketteMapper.insert(kk);
		// Kinookettennummer wird hier richtig eingesetzt
	}

	
	
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

	

	
	public void deleteKino(Kino kino) throws IllegalArgumentException {

		this.kinoMapper.delete(kino);
	}

	
	
	
	
	
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

	public Spielzeit createSpielzeit(Spielzeit spielzeit) throws IllegalArgumentException {

		// Objekt in der DB speichern.
		return this.spielzeitMapper.insert(spielzeit);

	}

	
	
	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Spielplan-Objekte
	 * ***************************************************************************
	 */
	
	
	/**
	 * Auslesen sämtliche Spielpläne dieses Systems.
	 */
	
	public Vector<Spielplan> getAllSpielplaene() throws IllegalArgumentException {
		return this.spielplanMapper.findAllSpielplan();
	}

	
	
	/**
	 * Löschen eines Spielplans
	 */
	
	public void deleteSpielplan(Spielplan spielplan) throws IllegalArgumentException {

		this.spielplanMapper.delete(spielplan);

	}
	
	
	
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************************************************************
	 */
	
	
	/**
	 * Bearbeiten des Nutzers
	 * 
	 * @param nutzer
	 * @return
	 * @throws IllegalArgumentException
	 */

	public Nutzer editNutzer(Nutzer nutzer) throws IllegalArgumentException {
		return this.nutzerMapper.update(nutzer);
	}

}
