/**
 * 
 */
package de.hdm.swprakt.cinemates.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.LoginService;
import de.hdm.swprakt.cinemates.shared.LoginServiceAsync;

/**
 * Diese Klasse beinhaltet Eigenschaften, die für alle clientseitigen Klassen
 * relevant sind. Subklasse von <code>CommonSettings</code>
 * 
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
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>LoginService</code>.
	 */

	private static LoginServiceAsync loginService = null;

	/**
	 * Name des Client-seitigen Loggers.
	 */
	private static final String LOGGER_NAME = "Cinemates Web Client";

	/**
	 * Instanz des Client-seitigen Loggers.
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	/**
	 * Auslesen des applikationsweiten clientseitig zentralen Loggers.
	 * 
	 * @return Logger-Instanz
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * <p>
	 * Anlegen und Auslesen der applikationsweiten eindeutigen Kinoadministration.
	 * Diese Methode erstellt die Kinoadministration, sofern diese noch nicht
	 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	 * zuvor angelegte Objekt zurückgegeben.
	 * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	 * <code>KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration()</code>
	 * </p>
	 * 
	 * @return eindeutige Instanz des Typs <code>KinoAdministrationAsync</code>
	 * @author alina
	 */
	public static KinoAdministrationAsync getKinoAdministration() {

		/** Wir prüfen, ob es bisher eine Instanz der KinoAdministration gab, wenn nicht, dann
		 * erstellen wir diese... 
		 * 
		 */

		if (kinoAdministration == null) {

			// Instantiierung der Kinoadministration

			kinoAdministration = GWT.create(KinoAdministration.class);


			final AsyncCallback<Void> initKinoAdministrationCallBack = new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {

					ClientSideSettings.getLogger().severe(
							"Die Kinoadministration konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientSideSettings.getLogger().info(
							"Die Kinoadministration wurde initialisiert.");


				}};
				kinoAdministration.init(initKinoAdministrationCallBack);



		}
		// Hier geben wir die Kinoadministration zurück
		return kinoAdministration;}

	/**
	 * <p>
	 * Anlegen und Auslesen der applikationsweiten eindeutigen Kinobesuchsplanung.
	 * Diese Methode erstellt die Kinobesuchsplanung, sofern diese noch nicht
	 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	 * zuvor angelegte Objekt zurückgegeben. Der Aufruf dieser Methode erfolgt im
	 * Client z.B. durch
	 * <code>KinoBesuchsplanungAsync kinoBesuchsplanung = ClientSideSettings.getKinobesuchsplanung()</code>
	 * </p>
	 * 
	 * @return eindeutige Instanz des Typs <code>KinoBesuchsplanungAsync</code>
	 * @author alina
	 */
	public static KinoBesuchsplanungAsync getKinobesuchsplanung() {

		/**
		 * Wir prüfen, ob es bisher eine Instanz der Kinobesuchsplanung gab, wenn nicht,
		 * dann erstellen wir diese...
		 * 
		 */
		if (kinoBesuchsplanung == null) {

			// Instantiierung der Kinobesuchplanung

			kinoBesuchsplanung = GWT.create(KinoBesuchsplanung.class);

			final AsyncCallback<Void> initKinobesuchsplanungCallBack = new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {

					ClientSideSettings.getLogger().severe("Die Kinobesuchsplanung konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientSideSettings.getLogger().info("Die Kinobesuchsplanung wurde initialisiert.");
				}
			};

			kinoBesuchsplanung.init(initKinobesuchsplanungCallBack);
		}

		// Hier geben wir Kinobesuchsplanung zurück
		return kinoBesuchsplanung;
	}

	/**
	 * <p>
	 * Anlegen und Auslesen des applikationsweiten eindeutigen Login-Services. Diese
	 * Methode erstellt den Login-Service, sofern dieser noch nicht existiert. Bei
	 * wiederholtem Aufruf dieser Methode wird stets das bereits zuvor angelegte
	 * Objekt zurückgegeben. Der Aufruf dieser Methode erfolgt im Client z.B. durch
	 * <code>LoginServiceAsync loginService = ClientSideSettings.getLoginService()</code>
	 * </p>
	 * 
	 * @return eindeutige Instanz des Typs <code>LoginServiceAsync</code>
	 * @author alina
	 */

	public static LoginServiceAsync getLoginService() {

		/**
		 * Wir prüfen, ob es bisher eine Instanz des Loginservices gab, wenn nicht, dann
		 * erstellen wir diese...
		 * 
		 */
		if (loginService == null) {

			// Instantiierung des Login-Services

			loginService = GWT.create(LoginService.class);

			final AsyncCallback<Void> initLoginServiceCallBack = new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {

					ClientSideSettings.getLogger().severe("Der Login-Service konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientSideSettings.getLogger().info("Der Login-Service wurde initialisiert.");
				}
			};

			loginService.init(initLoginServiceCallBack);
		}

		// Hier geben wir den Login-Service zurück
		return loginService;

	}
}
