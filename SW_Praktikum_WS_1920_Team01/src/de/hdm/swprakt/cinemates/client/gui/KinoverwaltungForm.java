package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class KinoverwaltungForm extends VerticalPanel{

/*
 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
 */
	
	TextBox inhalt1 = new TextBox();
	TextBox inhalt2 = new TextBox();
	TextBox inhalt3 = new TextBox();
	ListBox spielplanbox = new ListBox();
	Button anlegen = new Button("Kino anlegen");
	
	VerticalPanel detailsPanel = new VerticalPanel();
	
	
	public KinoverwaltungForm() {
		
	}
/*
 * Beim Anzeigen werden die Widgets erzeugt. Alle werden in einem
 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
 * Widgets bestimmt.
 */	
	
	public void onLoad() {
		
		super.onLoad();
		
		Grid kinoGrid = new Grid(4, 4);
		
		
		Label name = new Label("Kinoname");
		kinoGrid.setWidget(0, 1, inhalt1);
		kinoGrid.setWidget(0, 0, name);
		
		Label beschreibung = new Label("Kinobeschreibung");
		kinoGrid.setWidget(1, 1, inhalt2);
		kinoGrid.setWidget(1, 0, beschreibung);
		
		Label adresse = new Label("Adresse");
		kinoGrid.setWidget(2, 1, inhalt3);
		kinoGrid.setWidget(2, 0, adresse);
		
		Label spielplan = new Label("Spielplan");
		kinoGrid.setWidget(3, 1, spielplanbox);
		kinoGrid.setWidget(3, 0, spielplan);
		kinoGrid.setWidget(3, 2, anlegen);
		
		detailsPanel.add(kinoGrid);
		RootPanel.get("DetailsPanel").add(kinoGrid);
}
	
	
}
