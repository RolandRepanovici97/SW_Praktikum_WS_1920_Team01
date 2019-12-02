package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Navigator extends VerticalPanel{

	
	private ListBox gruppenverwaltung;
	private ListBox umfrageverwaltung;
	
	public void onLoad() {
		
		super.onLoad();
	
	
		gruppenverwaltung = new ListBox(false);
		gruppenverwaltung.addItem("Gruppenverwaltung");
		gruppenverwaltung.addItem("Gruppe anlegen");
		gruppenverwaltung.addItem("Gruppen anzeigen");
		gruppenverwaltung.setVisibleItemCount(1);
		this.add(gruppenverwaltung);
		
		umfrageverwaltung = new ListBox(false);
		umfrageverwaltung.addItem("Umfrageverwaltung");
		umfrageverwaltung.addItem("Umfrage erstellen");
		umfrageverwaltung.addItem("Umfrage anzeigen");
		umfrageverwaltung.setVisibleItemCount(1);
		this.add(umfrageverwaltung);
		
	
		}
	
}
