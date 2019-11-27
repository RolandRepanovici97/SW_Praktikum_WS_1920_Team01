/**
 * 
 */
package de.hdm.swprakt.cinemates.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.server.db.OwnedBusinessObjectMapper;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.LoginService;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
/**
 * Diese Klasse stellt die Implementierungsklasse des Interface {@link LoginService} dar. 
 * Hier wird der Login realisiert. Um den Login zu realisieren, bedienen wir uns der Google User Service API. 
 * @author alina
 * @version 1.0
 * @see LoginService, LoginServiceAsync
 *
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	// wird zur Serialisierung benötigt.

	private static final long serialVersionUID = 1L;
	
	//Zugriff auf die Methoden der Kinobesuchsplanung, e.g. für die Verwaltung des eingeloggten Nutzers
	private KinoBesuchsplanung kinobesuchsplanung = null;

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

	public LoginServiceImpl() throws IllegalArgumentException {
	  }
	
	
	public void init() throws IllegalArgumentException {
	    KinoBesuchsplanungImpl kbi = new KinoBesuchsplanungImpl();
	    kbi.init();
	    this.kinobesuchsplanung = kbi;
	  }
	
	
	public Nutzer login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		User googleUser = userService.getCurrentUser();
		Nutzer nutzer = new Nutzer();


		/**
		 * Es wird überprüft, ob der eingeloggte Nutzer unserem System CineMates bekannt ist.
		 */

		if(googleUser != null) {
			Nutzer bekannterNutzer = kinobesuchsplanung.findNutzerByEmail(googleUser.getEmail());

			/** Ist dieser Nutzer CineMates nun bekannt, so vermerken wir dass er eingeloggt ist. 
			 *  
			 */

			if(bekannterNutzer!= null) {
				
				nutzer = bekannterNutzer; 
				
				nutzer.setLoggedIn(true);
				nutzer.setLogoutUrl(userService.createLogoutURL(requestUri));

				
			}
			else {
				
				/**
				 *Falls der Nutzer unserem System allerdings nicht bekannt ist, so wird er in unsere
				 *Datenbank aufgenommen und ist nun auch Nutzer von CineMates.  
				 */
			
			nutzer.setEmail(googleUser.getEmail());
			nutzer.setNutzername(googleUser.getNickname());
			nutzer.setLoggedIn(true);
			nutzer.setLogoutUrl(userService.createLogoutURL(requestUri));
			
			//Einfügen des neuen Nutzers in die Datenbank
			
			nutzer = kinobesuchsplanung.createNutzer(nutzer);
			}

		}
		else {
			
			nutzer.setLoggedIn(false);
			nutzer.setLoginUrl(userService.createLoginURL(requestUri));
		}

		return nutzer;
	}
}
