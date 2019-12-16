package de.hdm.swprakt.cinemates.client.gui.admin;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.gui.editor.GruppenverwaltungForm;



public class Navigator extends FlowPanel{

	
	private ListBox gruppenverwaltung;
	private ListBox umfrageverwaltung;
	
	private VerticalPanel buttonPanel = new VerticalPanel();
	private VerticalPanel navPanel = new VerticalPanel();
	
	private Button anlegen = new Button("Gruppe anlegen");
	
	private GruppenverwaltungForm gf;
	
	public void onLoad() {
		
		super.onLoad();
	
	
		gruppenverwaltung = new ListBox(true);
		gruppenverwaltung.addClickHandler(new ShowGruppenverwaltungForm());
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
		
	/*	buttonPanel.add(anlegen);
		navPanel.add(buttonPanel);
		this.add(navPanel);
		anlegen.addClickHandler(new ShowGruppenverwaltungForm());*/
		
		}
	

	/*
	 * Die Klasse ShowGruppenverwaltungForm ermöglicht die Weiterletung zur
	 * GruppenverwaltungForm
	 */
	private class ShowGruppenverwaltungForm implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			gf = new GruppenverwaltungForm();
			RootPanel.get("DetailsPanel").add(gf);

		}
}
}