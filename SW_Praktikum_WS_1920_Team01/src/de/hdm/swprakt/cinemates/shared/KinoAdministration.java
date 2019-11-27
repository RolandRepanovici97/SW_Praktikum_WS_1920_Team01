/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Kinos, Kinoketten
 * Filmen, Spielpläne und Spielzeiten.
 * @author alina
 * @version 1.0
 *
 */

@RemoteServiceRelativePath("kinoadministration")
public interface KinoAdministration extends RemoteService {

}
