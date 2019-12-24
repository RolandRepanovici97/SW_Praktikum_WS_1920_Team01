package de.hdm.swprakt.cinemates.client.gui.admin;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;

public class KinoverwaltungForm extends TabPanel{

/*
 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
 */
	
	 TextBox kinoname = new TextBox();
	 TextBox kinobeschreibung = new TextBox();
	 TextBox kinoadresse = new TextBox();
	 Button kinoAnlegen = new Button("Kino anlegen");
	


	HorizontalPanel detailsPanel = new HorizontalPanel();
	
	// Create a data provider.
	private ListDataProvider<Kino> dataProvider = new ListDataProvider<Kino>();
	
	
	
	KinoAnlegenForm kaf = new KinoAnlegenForm();
	//KinoBearbeitenForm kbf = new KinoBearbeitenForm();
	
	//KinoverwaltungForm kvf = new KinoverwaltungForm();
/*
 * Beim Anzeigen werden die Widgets erzeugt. Alle werden in einem
 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
 * Widgets bestimmt.
 */	
	
	public void onLoad() {
		
		super.onLoad();
			
		this.add(kaf, "Kino anlegen");
		//this.add(kbf, "Kino bearbeiten");
	//	this.setPixelSize(1000, 500);
		this.selectTab(1);
				
}

	}
	

