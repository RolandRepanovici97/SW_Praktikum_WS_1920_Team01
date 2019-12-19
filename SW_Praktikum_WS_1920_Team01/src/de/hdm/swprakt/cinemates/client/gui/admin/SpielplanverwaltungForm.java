package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpielplanverwaltungForm extends TabPanel{

	
	FilmForm ffm = new FilmForm();
	SpielplanForm spf = new SpielplanForm();
	
	public void onLoad() {
		
		super.onLoad();
		/**
		 * Erstellung der Tabs.
		 */

		
		this.add(spf, "Spielplan bearbeiten");
		this.add(ffm, "Filme verwalten");
		this.add(new HTML("Baz"), "Spielzeit anlegen");

		this.selectTab(1);
		
		
}
}