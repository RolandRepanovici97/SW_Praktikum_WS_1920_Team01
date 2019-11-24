/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.FilmMapper;
import de.hdm.swprakt.cinemates.server.db.KinoMapper;
import de.hdm.swprakt.cinemates.server.db.KinoketteMapper;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.SpielplanMapper;
import de.hdm.swprakt.cinemates.server.db.SpielzeitMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;

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
private NutzerMapper nutzerMapper = null;
}
