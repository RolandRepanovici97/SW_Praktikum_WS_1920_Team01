package de.hdm.swprakt.cinemates.client.gui.admin;

import java.awt.event.MouseEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class KinoketteForm extends HorizontalPanel{

	private Button spielplanBearbeiten = new Button("Spielplan bearbeiten");
	private Button kinoBearbeiten = new Button("Kino bearbeiten");
	private Button kino = new Button("Kino löschen");
	private String kinoName = ("Kinoname 1"); //Als Array!
	private Button ja = new Button("JA");
	private Button nein = new Button("NEIN");
	private Button löschen = new Button("Löschen");

	
	private Image logo;

	
	 KinoverwaltungForm kf;
	 SpielplanForm sf;
	 KinoBearbeitenForm kbf;
	
	public void onLoad() {
		super.onLoad();
	
		
		logo = new Image("images/plus.jpg");
		logo.setWidth("40px");
		this.add(logo);
		logo.addClickHandler(new kinoAnlegenClickHandler());
		
		Grid kinoketteGrid = new Grid(10, 10);
		kinoketteGrid.setWidget(0, 3, spielplanBearbeiten);
		spielplanBearbeiten.addClickHandler(new spielplanBearbeitenClickHandler());
		kinoketteGrid.setText(0, 2, this.kinoName);
		kinoketteGrid.setWidget(0, 4, kinoBearbeiten);
		kinoBearbeiten.addClickHandler(new kinoBearbeitenClickHandler());
		kinoketteGrid.setWidget(0, 5, löschen);
		kinoketteGrid.setWidget(1, 7, logo);
		kinoketteGrid.setText(1, 8, "Neues Kino anlegen");
		löschen.addClickHandler(new löschenClickHandler());
		this.add(kinoketteGrid);

		RootPanel.get("DetailsPanel").add(kinoketteGrid);
		


	}
	
	
	private class kinoBearbeitenClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			kbf = new KinoBearbeitenForm();
			Window.Location.getParameter("");
			RootPanel.get("DetailsPanel").add(kbf);	
		}
	}
	
/*
 * Click handlers und abhängige AsyncCallback Klassen.
 */	
	
	private class spielplanBearbeitenClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			sf = new SpielplanForm();
			Window.Location.getParameter("");
			RootPanel.get("DetailsPanel").add(sf);
		}
		
	}
	
	private class kinoAnlegenClickHandler implements ClickHandler{

		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			kf = new KinoverwaltungForm();
			Window.Location.getParameter("");
			RootPanel.get("DetailsPanel").add(kf);
		}
		
	}	

private class löschenClickHandler extends DialogBox implements ClickHandler{
	
	public löschenClickHandler() {
		setText("Möchten Sie das Kino wirklich löschen?");
		Grid jaNein = new Grid(3,3);
		jaNein.setWidget(0, 1, ja);
		jaNein.setWidget(0, 2, nein);
		nein.addClickHandler(new neinClickHandler());
		setAnimationEnabled(false);
		setGlassEnabled(false);
		this.add(jaNein);
	}
	public void onClick(ClickEvent event) {
		new löschenClickHandler().show();
	}
	
private class neinClickHandler implements ClickHandler{
	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get().clear();	
	}	
}

}
}



