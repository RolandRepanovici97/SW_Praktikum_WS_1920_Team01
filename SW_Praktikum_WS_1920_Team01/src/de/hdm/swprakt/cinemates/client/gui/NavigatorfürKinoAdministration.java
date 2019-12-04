package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class NavigatorfürKinoAdministration extends VerticalPanel{

	
	private ListBox kinoverwaltung;
	private ListBox spielplanverwaltung;
	
	private KinoverwaltungForm kf;
	
public void onLoad() {
	
	super.onLoad();
	
	kinoverwaltung = new ListBox(false);
//	kinoverwaltung.addClickHandler(new ShowGruppenverwaltungForm());
	kinoverwaltung.addItem("Kinoverwaltung");
	kinoverwaltung.addItem("Kino anlegen");
	kinoverwaltung.addItem("Kinokette anzeigen");
	kinoverwaltung.setVisibleItemCount(1);
	this.add(kinoverwaltung);
	

	
	spielplanverwaltung = new ListBox(false);
	spielplanverwaltung.addItem("Spielplanverwaltung");
	spielplanverwaltung.addItem("Film hinzufügen");
	spielplanverwaltung.addItem("Spielplan anlegen");
	spielplanverwaltung.addItem("Spielpläne anzeigen");
	spielplanverwaltung.setVisibleItemCount(1);
	this.add(spielplanverwaltung);
		
}
}
