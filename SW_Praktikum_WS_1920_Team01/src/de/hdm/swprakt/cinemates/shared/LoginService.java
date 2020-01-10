/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * 
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Realisierung des Logins.
 *  
 * @author alina
 * @version 1.0
 * @see LoginServiceAsync, LoginServiceImpl
 * 
 *
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

	/**
	 * Diese Methode realisiert den Login, es wird geprüft, ob ein <code>Nutzer</code> unserem System
	 * bekannt ist. Ist das Nutzerobjekt bekannt, werden seine Attribute gesetzt. Loggt er sich, zum ersten mal 
	 * bei CineMates ein, so wird ein neues Nutzerobjekt erstellt und in der Datenbank gespeichert.
	 * Der Nutzer ist dann auch eingeloggt.
	 * Ist der Nutzer nicht eingeloggt, wird ein LoginURL zurückgegeben, über welchen er sich 
	 * mit seinem Google-Konto einloggen kann. 
	 * @param requestUri
	 * @return neues oder eingeloggtes Objekt der Klasse <code>Nutzer</code>
	 */

	public void init() throws IllegalArgumentException;
	public Nutzer login(String requestUri);


}

