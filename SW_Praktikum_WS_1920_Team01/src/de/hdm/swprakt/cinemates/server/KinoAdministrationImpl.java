/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

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
	
private KinoBesuchsplanung kinobesuchsplanung = null;
	
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
    
    KinoBesuchsplanungImpl kbi = new KinoBesuchsplanungImpl();
    kbi.init();
    this.kinobesuchsplanung = kbi;
  }


}
