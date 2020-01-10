package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;

public class SpielplanverwaltungForm extends TabPanel{
	
	Kino gewähltesKino;
	
	
	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	public SpielplanverwaltungForm(Kino kino) {
		this.gewähltesKino= kino;
	}


	FilmForm ffm = new FilmForm();
	SpielplanForm spf = new SpielplanForm();
	SpielzeitForm szf = new SpielzeitForm();


	public void onLoad() {

		super.onLoad();
		/**
		 * Erstellung der Tabs.
		 */


		this.add(spf, "Spielplan bearbeiten");
		this.add(ffm, "Filme verwalten");
		this.add(szf, "Spielzeit anlegen");

		this.selectTab(1);


	}
}