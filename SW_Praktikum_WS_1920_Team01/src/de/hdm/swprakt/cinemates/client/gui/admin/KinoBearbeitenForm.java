package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.gui.admin.KinoketteForm.LöschenCallback;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;


public class KinoBearbeitenForm extends HorizontalPanel {

	private Label titel;
	private Kino gewähltesKino;
	
	private Label kinoname = new Label("Neuer Kinoname: ");
	private TextBox kinonameText = new TextBox();
	
	private Button kinoLöschen = new Button("Kino löschen");
	private Button kinoBearbeiten = new Button("Kino bearbeiten");
	private Button ja = new Button("JA");
	private Button nein = new Button("NEIN");
	
	private HorizontalPanel horizontalPanel;
	


	
	
	// Getter & Setter für die Variable kino: Wird benötigt, wenn Kino selektiert wurde

		/**
		 * @return gewähltesKino
		 */
		public KinoBearbeitenForm (Kino gewähltesKino) {
			this.gewähltesKino=gewähltesKino;
		}

		
		public Kino getGewähltesKino() {
			return gewähltesKino;
		}
		/**
		 * @param gewählteUmfrage the gewählteUmfrage to set
		 */
		public void setGewähltesKino(Kino gewähltesKino) {
			this.gewähltesKino = gewähltesKino;
		}

	

	// Erstellen eines data providers, für das Anlegen des Kinos.
	private ListDataProvider<Kino> dataProvider = new ListDataProvider<Kino>();

	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	public void onLoad() {

		super.onLoad();
		
		titel = new Label("Kino: " + gewähltesKino.getKinoname() + " bearbeiten");
		titel.getElement().setId("TitelElemente");
		kinonameText.setText(gewähltesKino.getKinoname());
		
		
		Grid kinoGrid = new Grid(4, 4);

		kinoGrid.setWidget(1, 1, kinoname);
		kinoGrid.setWidget(1, 1, kinonameText);

		kinoGrid.setWidget(3, 2, kinoBearbeiten);
		kinoBearbeiten.addClickHandler(new KinoBearbeitenClickHandler());
		kinoGrid.setWidget(3, 3, kinoLöschen);
		kinoLöschen.addClickHandler(new löschenClickHandler());

		this.add(titel);
		this.add(kinoGrid);
	
		
		

	}

	/**
	 * Sobald die Textfelder ausgefüllt wurden, wird ein neues Kino nach dem Klicken
	 * des Kinoanlegen Buttons erstellt.
	 */
	private class KinoBearbeitenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			gewähltesKino.setKinoname(kinonameText.getText());
			
			kinoAdministration.save(gewähltesKino, new KinoBearbeitenCallback());

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
	 * die Rückgabe des editierten Kinoobjekts.
	 */
	 class KinoBearbeitenCallback implements AsyncCallback<Kino> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Kino konnte nicht bearbeitet werden");

		}

		@Override
		public void onSuccess(Kino result) {
			Window.alert("Das Kino wurde erfolgreich editiert!");
		}
	}

	private class löschenClickHandler extends DialogBox implements ClickHandler {

		public löschenClickHandler() {
			setText("Möchten Sie das Kino wirklich löschen?");
			Grid jaNein = new Grid(3, 3);
			jaNein.setWidget(0, 1, ja);
			jaNein.setWidget(0, 2, nein);
			nein.addClickHandler(new neinClickHandler());
			ja.addClickHandler(new LöschenClickHandler2());
			setAnimationEnabled(false);
			setGlassEnabled(false);
			this.add(jaNein);
		}
		
		public void onClick(ClickEvent event) {
			new löschenClickHandler().show();
		}

		private class neinClickHandler implements ClickHandler {
			
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
			}
		}
		
		
		
	}
		/**
		 * Diese Nested Class implementiert das Interface AsyncCallback und das Löschen
		 * eines Kinos.
		 * 
		 */
		class LöschenCallback implements AsyncCallback <Void>{

			@Override
			public void onFailure(Throwable caught) {
				
				ClientSideSettings.getLogger().severe("Es konnten keine Kinos gelöscht werden");
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Das Kino wurde erfolgreich gelöscht!");
				RootPanel.get().clear();
				//KinoBearbeitenForm kbf = new KinoBearbeitenForm(gewähltesKino);
			//	RootPanel.get("DetailsPanel").add(kbf);
		

			}

		}
		
		

		/**
		 * Diese Nested Class implementiert das Interface ClickHandler.
		 * Klickt der Nutzer diessen Button an, so wird das Kino gelöscht.
		 * 
		 * 
		 */

		private class LöschenClickHandler2 implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				kinoAdministration.deleteKino(gewähltesKino, new LöschenCallback());
				RootPanel.get("DetailsPanel").clear();

			}

		}
		


	}
	
