package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class NavigatorfürKinoAdministration extends FlowPanel{

	
	private ListBox kinoverwaltung;
	private ListBox spielplanverwaltung;
	
	private Button kinoAnlegen;
	TabPanel kinoketteBearbeiten;
	TabPanel kinoanlegen;
	private Image logo;
	
	private KinoverwaltungForm kf;
	private KinoketteForm kkf;
	
	private FilmForm ff;
	private SpielplanForm sf;
	

	
public void onLoad() {
	
	super.onLoad();
	
	logo = new Image("images/Menu.jpg");
	logo.setWidth("40px");
	this.add(logo);
	
	
	logo.addClickHandler(new ShowKinoverwaltung());

	kinoverwaltung = new ListBox(true);
	
	kinoverwaltung.addClickHandler(new ShowKinoketteTab());
	kinoverwaltung.addClickHandler(new ShowKinoanlegenTab());
	
	kinoverwaltung.addItem("Kinoverwaltung");
	//kinoverwaltung.addItem("Spielplanverwaltung");
	kinoverwaltung.setVisibleItemCount(1);
	
	spielplanverwaltung = new ListBox(false);
	spielplanverwaltung.addItem("Spielplanverwaltung");
	spielplanverwaltung.addClickHandler(new ShowFilmForm());
	spielplanverwaltung.addItem("- Film hinzufügen");
	spielplanverwaltung.addItem("- Spielplan anlegen");
//	spielplanverwaltung.addClickHandler(new ShowSpielplanForm());
	spielplanverwaltung.addItem("- Spielpläne anzeigen");
	spielplanverwaltung.setVisibleItemCount(4);
	//this.add(spielplanverwaltung);
	
	}



/*
 * Die Klasse ShowKinoverwaltungForm ermöglicht die Weiterletung zur
 * KinoverwaltungForm
 */

private class ShowKinoverwaltung implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		ff = new FilmForm();
		RootPanel.get("NavigationPanel").add(kinoverwaltung);
	}	
}
private class ShowKinoketteTab implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get("DetailsPanel").clear();
		kinoketteBearbeiten = new TabPanel();
		HorizontalPanel kinokette = new HorizontalPanel();
		kinoketteBearbeiten.add(new HTML(),"Kinokette bearbeiten");
		kinoketteBearbeiten.selectTab(0);
	
		kinokette.add(kinoketteBearbeiten);
		RootPanel.get("DetailsPanel").add(kinoketteBearbeiten);
		kinoketteBearbeiten.addSelectionHandler(new SelectionHandler<Integer>() {
			
			public void onSelection(SelectionEvent<Integer> event) {
				RootPanel.get("DetailsPanel").clear();
				if(event.getSelectedItem()==0) {	
				//	RootPanel.get("DetailsPanel").clear();
					RootPanel.get("DetailsPanel").add(kinokette);
					kkf = new KinoketteForm();
					RootPanel.get("DetailsPanel").add(kkf);
				} 
					
			
			}
		});

	}	
}

private class ShowKinoanlegenTab implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		kinoanlegen = new TabPanel();
		HorizontalPanel kino = new HorizontalPanel();
		kinoanlegen.add(new HTML(),"Kino anlegen");
		kinoanlegen.selectTab(0);
		kino.add(kinoanlegen);
		RootPanel.get("DetailsPanel").add(kinoanlegen);
		kinoanlegen.addSelectionHandler(new SelectionHandler<Integer>() {
			
			public void onSelection(SelectionEvent<Integer> event) {
				if(event.getSelectedItem()==0) {	
					RootPanel.get("DetailsPanel").clear();
					RootPanel.get("DetailsPanel").add(kino);
					kf = new KinoverwaltungForm();
					RootPanel.get("DetailsPanel").add(kf);
					
				}
			}
		});
		
		
	

	}	
}





private class ShowKinoketteForm implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get("DetailsPanel").clear();
		kkf = new KinoketteForm();
		RootPanel.get("DetailsPanel").add(kkf);

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