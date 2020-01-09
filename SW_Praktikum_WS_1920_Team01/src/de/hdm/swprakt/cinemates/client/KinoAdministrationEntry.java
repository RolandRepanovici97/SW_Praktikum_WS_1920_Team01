package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.swprakt.cinemates.client.KinobesuchsplanungEntry.AktuellerNutzer;
import de.hdm.swprakt.cinemates.client.gui.Footer;
import de.hdm.swprakt.cinemates.client.gui.admin.AlleKinosEinerKinokette;
import de.hdm.swprakt.cinemates.client.gui.admin.FilmForm;
import de.hdm.swprakt.cinemates.client.gui.admin.HeaderfürKinoAdministration;
import de.hdm.swprakt.cinemates.client.gui.admin.KinoketteForm;
import de.hdm.swprakt.cinemates.client.gui.admin.SpielplanForm;
import de.hdm.swprakt.cinemates.client.gui.admin.SpielplanverwaltungForm;
import de.hdm.swprakt.cinemates.shared.LoginService;
import de.hdm.swprakt.cinemates.shared.LoginServiceAsync;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KinoAdministrationEntry implements EntryPoint {

	LoginServiceAsync loginService = ClientSideSettings.getLoginService();
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
	
		
		loginService.login(GWT.getHostPageBaseURL(), new LoginServiceCallback());
		
		
		
	//	SpielplanverwaltungForm spvf = new SpielplanverwaltungForm();
	//	RootPanel.get("DetailsPanel").add(spvf);
	}
	
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
				Window.Location.assign("Kinobesuchsplanung.html");

			}
		}
	}
	
	private void loadStartseite() {
		
		HeaderfürKinoAdministration headerPanel = new HeaderfürKinoAdministration();
		headerPanel.getElement().setId("headerPanelKinoadministration");
		RootPanel.get("Header").add(headerPanel);
		
		KinoketteForm kf = new KinoketteForm();
		RootPanel.get("DetailsPanel").add(kf);
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
	
	
}