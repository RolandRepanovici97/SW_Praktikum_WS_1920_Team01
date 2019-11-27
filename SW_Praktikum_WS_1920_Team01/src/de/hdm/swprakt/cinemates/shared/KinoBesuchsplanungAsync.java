/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Das asynchrone Gegenstück des Interface {@link KinoBesuchsplanung}
 * @author alina 
 * @version 1.0
 *
 */
public interface KinoBesuchsplanungAsync {

	public void findNutzerByEmail(String email, AsyncCallback<Nutzer> callback);
	
	public void createNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);
	
	public void createGruppe(Gruppe gruppe, AsyncCallback<Gruppe> callback);
}
