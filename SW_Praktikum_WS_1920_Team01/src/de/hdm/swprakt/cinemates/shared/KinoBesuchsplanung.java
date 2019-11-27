/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Planung eines Kinobesuchs.(Anlage und Verwaltung
 * von Gruppen, Umfragen, etc.)
 * @author alina
 * @version 1.0
 *
 */

@RemoteServiceRelativePath("kinobesuchsplanung")
public interface KinoBesuchsplanung extends RemoteService {

	public Nutzer findNutzerByEmail(String email);

	public Nutzer createNutzer(Nutzer nutzer);
	
	public Gruppe createGruppe(Gruppe gruppe);

}
