package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class NavigatorfürKinoAdministration extends VerticalPanel{

	
	private ListBox kinoverwaltung;
	private ListBox spielplanverwaltung;
	private ListBox filmhinzufügen;
	
	private KinoverwaltungForm kf;
	private FilmForm ff;
	private SpielplanForm sf;
	
public void onLoad() {
	
	super.onLoad();
	
	kinoverwaltung = new ListBox(false);
	kinoverwaltung.addClickHandler(new ShowKinoverwaltungForm());
	kinoverwaltung.addItem("Kinoverwaltung");
	kinoverwaltung.addItem("- Kino anlegen");
	kinoverwaltung.addItem("- Kinokette anzeigen");
	kinoverwaltung.setVisibleItemCount(3);
	this.add(kinoverwaltung);
	
//ClickHandler in Bearbeitung
	
	spielplanverwaltung = new ListBox(false);
	spielplanverwaltung.addItem("Spielplanverwaltung");
	spielplanverwaltung.addClickHandler(new ShowFilmForm());
	spielplanverwaltung.addItem("- Film hinzufügen");
	spielplanverwaltung.addItem("- Spielplan anlegen");
//	spielplanverwaltung.addClickHandler(new ShowSpielplanForm());
	spielplanverwaltung.addItem("- Spielpläne anzeigen");
	spielplanverwaltung.setVisibleItemCount(4);
	this.add(spielplanverwaltung);
	
	
		
}

/*
 * Die Klasse ShowKinoverwaltungForm ermöglicht die Weiterletung zur
 * KinoverwaltungForm
 */
private class ShowKinoverwaltungForm implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get("DetailsPanel").clear();
		kf = new KinoverwaltungForm();
		RootPanel.get("DetailsPanel").add(kf);

	}
}

private class ShowFilmForm implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get("DetailsPanel").clear();
		ff = new FilmForm();
		RootPanel.get("DetailsPanel").add(ff);
	}	
}

private class ShowSpielplanForm implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get("DetailsPanel").clear();
		sf = new SpielplanForm();
		RootPanel.get("DetailsPanel").add(sf);
	}	
}

}