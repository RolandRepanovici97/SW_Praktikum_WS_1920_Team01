/**
 * 
 */
package de.hdm.swprakt.cinemates.client;

import java.util.logging.Logger;

import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
/**
 * Diese Klasse beinhaltet Eigenschaften, die für alle clientseitigen Klassen relevant sind. 
 * Subklasse von <code>CommonSettings</code>
 * @author alina
 * @version 1.0
 *
 */
public class ClientSideSettings {
	
	  /**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>KinoAdminstration</code>.
	   */

	  private static KinoAdministrationAsync kinoAdministration = null;

	  /**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>Kinobesuchsplanung</code>.
	   */
	  private static KinoBesuchsplanungAsync kinoBesuchsplanung = null;

	  /**
	   * Name des Client-seitigen Loggers.
	   */
	  private static final String LOGGER_NAME = "Cinemates Web Client";
	  
	  /**
	   * Instanz des Client-seitigen Loggers.
	   */
	  private static final Logger log = Logger.getLogger(LOGGER_NAME);


}
