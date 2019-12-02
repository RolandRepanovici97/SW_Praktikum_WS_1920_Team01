package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class KinoverwaltungForm extends VerticalPanel{

/*
 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
 */
	
	private ListBox kinoverwaltung;
	private ListBox kinoverwaltung2;
	
/*
 * Beim Anzeigen werden die Widgets erzeugt. Alle werden in einem
 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
 * Widgets bestimmt.
 */	
	
	public void onLoad() {
		
		super.onLoad();
		
		kinoverwaltung = new ListBox(true);
		kinoverwaltung2 = new ListBox(false);

		kinoverwaltung2.setTitle("Kinoverwaltung");
		kinoverwaltung2.addItem("--Bitte auswählen--");
		kinoverwaltung2.addItem("Kino anlegen");
		kinoverwaltung2.addItem("Kinokette anzeigen");
		kinoverwaltung.setVisibleItemCount(2);
		kinoverwaltung2.setVisibleItemCount(1);
		
		this.add(kinoverwaltung2);
}
}
