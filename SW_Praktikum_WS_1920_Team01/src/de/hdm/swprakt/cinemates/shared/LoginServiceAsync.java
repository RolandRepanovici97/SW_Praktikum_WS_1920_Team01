/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Das asynchrone Gegenstück des Interface {@link LoginService} zur Realisierung des Logins.
 * Für die konkrete Dokumentation der einzelnen Methoden, empfiehlt es sich in das synchrone
 * Interface @link LoginService und in dessen Implementationsklasse @link LoginServiceImpl zu sehen.
 * 
 * @author alina
 * @version 1.0
 * @see LoginService, LoginServiceImpl
 *
 */
public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<Nutzer> asyncCallback);

	void init(AsyncCallback<Void> callback);
}
