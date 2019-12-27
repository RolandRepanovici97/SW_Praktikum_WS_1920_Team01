package de.hdm.swprakt.cinemates.client.gui.admin;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;


public class KinoketteForm extends HorizontalPanel{

	//Nur zum Test
	//Später ersetzen mit dem eingeloggten Benutzer
	
	private Button spielplanBearbeiten = new Button("Spielplan bearbeiten");
	private Button kinoBearbeiten = new Button("Kino bearbeiten"); 
	private Button kinolöschen = new Button("Kino löschen");
	private String kinoName = ("Kinoname 1"); //Als Array!
	private Button ja = new Button("JA");
	private Button nein = new Button("NEIN");
	private Button löschen = new Button("Löschen");
	
	private Nutzer eingeloggterNutzer;
	
	private Vector<Kino> kinoVector = new Vector<Kino>();
	

	
	Kinokette kinoketteEingeloggterBenutzer;

	
	FlexTable kinos = new FlexTable();
	
	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();



	
	private Image logo;

	
	 KinoverwaltungForm kf;
	 SpielplanForm sf;

	 SpielplanverwaltungForm spvf;
	 
	 KinoBearbeitenForm kbf;

	 AlleKinosEinerKinokette akek;
	 
	 
	 
	
	public void onLoad() {
		super.onLoad();
		
		kinoAdministration.findNutzerByEmail("Kinobetreiber2@gmail.com", new AsyncCallback<Nutzer>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Der eingeloggte Benutzer wurde nicht gefunden");
				
			}

			@Override
			public void onSuccess(Nutzer result) {
				ClientSideSettings.getLogger().severe("Der eingeloggte Benutzer wurde gefunden");
				ClientSideSettings.getLogger().severe(result.getNutzername());
				eingeloggterNutzer = result;
				
			}
		 
	 });
		
		
		kinoAdministration.getKinoketteOf(eingeloggterNutzer, new AsyncCallback<Kinokette>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Die Kinokette des eingeloggten Benutzers wurde nicht gefunden");
				
			}

			@Override
			public void onSuccess(Kinokette result) {
				ClientSideSettings.getLogger().severe("Die Kinokette des eingeloggten Benutzers wurde gefunden");
				
				kinoketteEingeloggterBenutzer = result;
				
			}
			
		});
		
		kinoAdministration.getAllKinoOfKinokette(kinoketteEingeloggterBenutzer, new AsyncCallback<Vector<Kino>>() {
			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Es wurden keine Kinos gefunden");

			}

			@Override
			public void onSuccess(Vector<Kino> result) {
				// Add the data to the data provider, which automatically pushes it to the
				// widget.
				
				ClientSideSettings.getLogger().severe("Es wurden Kinos gefunden");
				for (Kino kino : result) {
					System.out.println(kino.toString());
					
					
				}
			}

		});
		
	
		kinoAdministration.getAllKinos(new AsyncCallback<Vector<Kino>>() {
		

			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Es wurden keine Kinos in der getAllKinos() gefunden");
				
			}

			@Override
			public void onSuccess(Vector<Kino> result) {
				ClientSideSettings.getLogger().severe("Es wurden Kinos gefunden");
				int rowcount = 0;
				Button spielplanBearbeiten = new Button("Spielplan bearbeiten");
				Button kinoBearbeiten = new Button("Kino bearbeiten"); 
				Button kinolöschen = new Button("Kino löschen");
				Image logo = new Image("images/plus.jpg");
		
				logo.setWidth("40px");
				for (Kino kino : result) {

					ClientSideSettings.getLogger().severe(kino.toString());
					spielplanBearbeiten = new Button("Spielplan bearbeiten");
					kinoBearbeiten = new Button("Kino bearbeiten"); 
					kinolöschen = new Button("Kino löschen");
					logo = new Image("images/plus.jpg");
					
					
					kinos.setText(rowcount, 0, kino.toString());
					kinos.setWidget(rowcount, 1, spielplanBearbeiten);
					spielplanBearbeiten.addClickHandler(new spielplanBearbeitenClickHandler());
					kinos.setWidget(rowcount, 2, kinoBearbeiten);
					kinoBearbeiten.addClickHandler(new kinoBearbeitenClickHandler());
					kinos.setWidget(rowcount, 3, kinolöschen);
					kinolöschen.addClickHandler(new löschenClickHandler());
					kinos.setWidget(rowcount, 4, logo);
					kinos.setText(rowcount, 5, "Neues Kino anlegen");
					logo.addClickHandler(new kinoAnlegenClickHandler());
					//
					rowcount++;
				}
				
			
				
			}
			
		});
		
		
		
		
	
		
		
		
		//this.add(logo);
		//logo.addClickHandler(new kinoAnlegenClickHandler());
		
	/*	Grid kinoketteGrid = new Grid(10, 10);
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
	
		
		
		
		
		RootPanel.get("DetailsPanel").add(kinoketteGrid);*/
		this.add(kinos);
		RootPanel.get("DetailsPanel").add(kinos);
	
		
		
	}
	
	/*
	 * Click handlers und abhängige AsyncCallback Klassen.
	 */	
	
	private class kinoBearbeitenClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			kbf = new KinoBearbeitenForm();
			Window.Location.getParameter("");
			RootPanel.get("DetailsPanel").add(kbf);	
		}
	}
	
	
	private class spielplanBearbeitenClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
	spvf = new SpielplanverwaltungForm();
			//		sf = new SpielplanForm();
			Window.Location.getParameter("");
			RootPanel.get("DetailsPanel").add(spvf);
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



