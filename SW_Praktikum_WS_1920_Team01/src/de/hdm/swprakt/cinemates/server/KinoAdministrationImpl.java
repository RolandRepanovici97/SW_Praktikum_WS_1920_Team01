/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import java.util.Date;

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
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;


/**
 * Diese Klasse stellt die Implementierungsklasse des Interface {@link KinoAdministration} dar. 
 * Sie beinhaltet die komplette Applikationslogik, welche zur Verwaltung von Kinos, Kinoketten
 * Filmen, Spielpläne und Spielzeiten benötigt wird. 
 *  
 * @author alina
 * @version 1.0
 */
public class KinoAdministrationImpl extends RemoteServiceServlet implements KinoAdministration{

/** Der Kinobetreiber benötigt Zuriff auf die Daten rund um die Verwaltung eines Kinos.
 *  Dieser Zugriff wird über die jeweiligen Mapper realisiert. 
 * 
 */
	
private KinoMapper kinoMapper = null;
private KinoketteMapper kinoketteMapper = null;
private FilmMapper filmMapper = null;
private SpielzeitMapper spielzeitMapper = null;
private SpielplanMapper spielplanMapper = null;



public KinoAdministrationImpl() throws IllegalArgumentException {
    
}

public void init() throws IllegalArgumentException {
    
    this.kinoMapper = KinoMapper.kinoMapper();
    this.kinoketteMapper = KinoketteMapper.kinoketteMapper();
    this.filmMapper = FilmMapper.filmMapper();
    this.spielplanMapper = SpielplanMapper.spielplanMapper();
    
    KinoBesuchsplanungImpl kinoBesuchsplanungImpl = new KinoBesuchsplanungImpl();
    kinoBesuchsplanungImpl.init();
  }
/*
 * ***************************************************************************
 * ABSCHNITT, Beginn: Methoden für Film-Objekte
 * ***************************************************************************
 */
/**
 * <p>
 * Anlegen eines neuen Films. Dies führt implizit zu einem Speichern des
 * neuen Films in der Datenbank.
 * </p>
 * 
 * 
 * 
 * @see saveFilm(Film f) ????
 */

	public Film createFilm(String filmtitel,String beschreibung, String details)
			throws IllegalArgumentException {
		Film f = new Film();
		f.setFilmtitel(filmtitel);
		f.setBeschreibung(beschreibung);
		f.setDetails(details);
	    /*
	     * Setzen einer vorläufigen Filmnr. Der insert-Aufruf liefert dann ein
	     * Objekt, dessen Nummer mit der Datenbank konsistent ist.
	     */
	    f.setID(1);

	    // Objekt in der DB speichern.
	    return this.filmMapper.insert(f);
	    		 //Filmnummer wird hier richtig eingesetzt 
	  }
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Kinokette-Objekte
	 * ***************************************************************************
	 */
	
	
	
	
	
	
	public Kinokette addKinoToKinokette(String kinokettenname,String beschreibung)
			throws IllegalArgumentException {
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
	    		 //Kinookettennummer wird hier richtig eingesetzt 
	  }
	
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Kino-Objekte
	 * ***************************************************************************
	 */
	
	public Kino createKino(Kino kino)
			throws IllegalArgumentException {
		
	
		/*
	     * Setzen einer vorläufigen KinoNr. Der insert-Aufruf liefert dann ein
	     * Objekt, dessen Nummer mit der Datenbank konsistent ist.
	     */
	 

	    // Objekt in der DB speichern.
	    return this.kinoMapper.insert(kino);
	    		 //Kinonummer wird hier richtig eingesetzt 
	  }
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Spielzeit-Objekte
	 * ***************************************************************************
	 */
	

	public Spielzeit createSpielzeit(Spielzeit spielzeit)
			throws IllegalArgumentException {
		
	

	    // Objekt in der DB speichern.
	    return this.spielzeitMapper.insert(spielzeit);
	    
	    		 
	  }
	
	
	
	
}


