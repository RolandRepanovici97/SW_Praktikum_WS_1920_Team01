package de.hdm.swprakt.cinemates.client;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.swprakt.cinemates.client.gui.Footer;
import de.hdm.swprakt.cinemates.client.gui.admin.HeaderfürKinoAdministration;
import de.hdm.swprakt.cinemates.client.gui.editor.HeaderfürKinobesuchsplanung;
import de.hdm.swprakt.cinemates.client.gui.editor.NutzerkontoForm;
import de.hdm.swprakt.cinemates.client.gui.editor.StartseiteEditor;
import de.hdm.swprakt.cinemates.server.LoginServiceImpl;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.LoginService;
import de.hdm.swprakt.cinemates.shared.LoginServiceAsync;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KinobesuchsplanungEntry implements EntryPoint {

	/*
	 * ***************************************************************************
	 * ABSCHNITT LOGIN
	 * ***************************************************************************
	 */


	private Anchor signInLink = new Anchor("Sign In");
	private VerticalPanel loginPanel = new VerticalPanel();
	private StartseiteEditor startseite;
	private Label loginLabel = new Label(
			"Bitte melden Sie sich hier mit Ihrem Google-Konto an, um auf CineMates zugreifen zu können ");
	
	


	@Override
	public void onModuleLoad() {
		
		// Zugriff auf Instanz des asynchronen Interfaces für den Login
		LoginServiceAsync loginService = GWT.create(LoginService.class);
	loginService.login(GWT.getHostPageBaseURL(), new LoginServiceCallback());
	
//	loadStartseite();
		

	}

	/**
	 * Nutzung des Konzepts NestedClass für Rückgabe des LoginServiceCallbacks. Nach
	 * erfolgreichem Callback setzen wird den <code>AktuellerNutzer<code>
	 * Anschließend erfolt eine Abfrage ob der Nutzer bereits eingeloggt ist, falls
	 * dies zutreffend ist wird er zu Cinemates (Editor-Client) weitergeleitet.
	 * Falls nicht wird die Methode loadLogin() aufgerufen.
	 * 
	 * @author alina
	 *
	 */
	private class LoginServiceCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert(caught.toString());
		}

		@Override
		public void onSuccess(Nutzer nutzer) {

			AktuellerNutzer.setNutzer(nutzer);

			if (nutzer.isLoggedIn()) {
				
					loadStartseite();
					
			} else {
				loadLogin();

			}
		}

		/**
		 * Diese Methode wird aufgerufen, falls der Nutzer nicht bei CineMates
		 * eingeloggt ist In dieser wird die Google LoginMaske über den Button
		 * <code>loginButton </code> aufgerufen.
		 */
		private void loadLogin() {
			
			
			 signInLink.setHref(AktuellerNutzer.getNutzer().getLoginUrl());
			    loginPanel.add(loginLabel);
			    loginPanel.add(signInLink);
			    RootPanel.get("DetailsPanel").add(loginPanel);
			    

		}

		/**
		 * Durch einen Klick auf den loginButton wird der User auf die GoogleLoginMaske
		 * weitergeleitet
		 */
		private class LoginClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {

				Window.open(signInLink.getHref(), "_self", "");
			}
		}

		/**
		 * Die Klasse <code>AktuellerNutzer</code> repräsentiert den aktuell im System
		 * angemeldeten Nutzer. Da der Nutzer an weiteren Stellen nötig ist, muss er
		 * abrufbar sein.
		 */
	}
	
	
	private void loadStartseite() {
		
		
		HeaderfürKinobesuchsplanung headerPanel = new HeaderfürKinobesuchsplanung();
		headerPanel.getElement().setId("headerPanelKinobesuchsplanung");
		RootPanel.get("Header").add(headerPanel);
		startseite = new StartseiteEditor();
		RootPanel.get("DetailsPanel").add(startseite);
		Footer footer = new Footer();
		RootPanel.get("Footer").add(footer);
		
	}

	public static class AktuellerNutzer {

		private static Nutzer nutzer = null;

		public static Nutzer getNutzer() {
			return nutzer;
		}

		public static void setNutzer(Nutzer nutzer) {
			AktuellerNutzer.nutzer = nutzer;
		}
	}

	/*
	 * Das ist nur ein Test!
	 */

}
