package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.KinoAdministrationEntry;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

public class KinoAnlegenForm extends HorizontalPanel {

	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */

	TextBox kinoname = new TextBox();
	TextBox kinobeschreibung = new TextBox();
	TextBox kinoadresse = new TextBox();
	Button kinoAnlegen = new Button("Kino anlegen");
	Kinokette kinoketteEingeloggterBenutzer;

	HorizontalPanel detailsPanel = new HorizontalPanel();

	// Create a data provider.
	private ListDataProvider<Kino> dataProvider = new ListDataProvider<Kino>();
	
	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	public void onLoad() {

		super.onLoad();
		
		kinoAdministration.getKinoketteOf(KinoAdministrationEntry.AktuellerNutzer.getNutzer(), new KinoketteCallback());

		Grid kinoGrid = new Grid(4, 4);

		Label name = new Label("Kinoname");
		kinoGrid.setWidget(0, 1, kinoname);
		kinoGrid.setWidget(0, 0, name);

		Label beschreibung = new Label("Kinobeschreibung");
		kinoGrid.setWidget(1, 1, kinobeschreibung);
		kinoGrid.setWidget(1, 0, beschreibung);

		Label adresse = new Label("Adresse");
		kinoGrid.setWidget(2, 1, kinoadresse);
		kinoGrid.setWidget(2, 0, adresse);

		kinoGrid.setWidget(3, 2, kinoAnlegen);
		kinoAnlegen.addClickHandler(new KinoAnlegenClickHandler());

		detailsPanel.add(kinoGrid);
		RootPanel.get("DetailsPanel").add(detailsPanel);

	}

	/**
	 * Sobald die Textfelder ausgefüllt wurden, wird ein neues Kino nach dem Klicken
	 * des Kinoanlegen Buttons erstellt.
	 */
	private class KinoAnlegenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			kinoAdministration.createKino(kinoname.getText(), kinobeschreibung.getText(), kinoadresse.getText(),
					kinoketteEingeloggterBenutzer, new KinoAnlegenCallback());

		}

	}

	/**
	 * Callback wird benötigt, um das Kino zu erstellen
	 */
	private class KinoAnlegenCallback implements AsyncCallback<Kino> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Kino konnte nicht erstellt werden");

		}

		@Override
		public void onSuccess(Kino kino) {
			if (kino == null) {
				Window.alert("Das Kino existiert bereits");
			} else {
				Window.alert("Das Kino wurde erfolgreich erstellt");
				kinoname.setText("");
				dataProvider.getList().add(kino);
				dataProvider.refresh();
			}

		}
	}
	
	private class KinoketteCallback implements AsyncCallback<Kinokette> {

		@Override
		public void onFailure(Throwable caught) {
			ClientSideSettings.getLogger().severe("Der eingeloggte Nutzer wurde nicht gefunden");
			
		}

		@Override
		public void onSuccess(Kinokette result) {
			ClientSideSettings.getLogger().severe("Der eingeloggte Nutzer wurde gefunden");;
			kinoketteEingeloggterBenutzer = result;
			
		}
		
	}

	private class kinoentfernenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			// kinoAdministration.deleteKino(kino, callback);
		}
	}

	/**
	 * Callback wird benötigt, um das Kino zu löschen
	 */
	private class KinoDeleteCallback implements AsyncCallback<Kino> {

		Kinokette kinokette = null;

		KinoDeleteCallback(Kinokette kk) {
			kinokette = kk;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Kino konnte nicht enfernt werden");

		}

		@Override
		public void onSuccess(Kino kino) {

		}
	}

}
