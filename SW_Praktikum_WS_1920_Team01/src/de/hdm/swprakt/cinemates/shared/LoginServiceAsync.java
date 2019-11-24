/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Das asynchrone Gegenstück des Interface {@link LoginService} zur Realisierung des Logins.
 * @author alina
 * @version 1.0
 * @see LoginService
 *
 */
public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<Nutzer> asyncCallback);

}
