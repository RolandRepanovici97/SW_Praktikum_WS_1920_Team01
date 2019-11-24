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
 * @see LoginServiceAsync
 * 
 *
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {


	public Nutzer login(String requestUri);


}

