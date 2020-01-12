package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.KinobesuchsplanungEntry;
import de.hdm.swprakt.cinemates.client.KinobesuchsplanungEntry.AktuellerNutzer;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Diese Klasse erweitert das VerticalPanel und wird benötigt, um eine neue
 * Gruppe anzulegen.
 * 
 * @author Roland
 *
 */

public class GruppeErstellenForm extends HorizontalPanel {

	// Referenz auf den aktuellen Nutzer
	Nutzer nutzer;

	// Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

	// Erzeugen der einzelnen Widgets

	private Label titel = new Label("Gruppe erstellen");
	private Label gruppename = new Label("Gruppename: ");
	private TextBox gruppenametext;
	private Label mitglieder = new Label("Mitglieder: ");
	private NeuesMitglied neuesmitglied;
	private VerticalPanel panelfürgruppe;
	private HorizontalPanel panelfürbuttons;
	private FlexTable tabelle;
	private Button erstellenButton = new Button("Gruppe erstellen");
	private Vector<Nutzer> gruppenvectorid = new Vector<Nutzer>();
	private Button weiteresMitglied;
	private Vector<TextBox> mitgliederfelder;
	private int rowCount;
	public void onLoad() {
		super.onLoad();

		// Wir erhalten den aktuellen Nutzer
		nutzer = KinobesuchsplanungEntry.AktuellerNutzer.getNutzer();
		// Instanttierung der Widgets

		rowCount= 3;
		mitgliederfelder = new Vector<TextBox>();

		weiteresMitglied = new Button("Weiteres Mitglied hinzufügen");
		panelfürgruppe = new VerticalPanel();
		panelfürbuttons = new HorizontalPanel();
		tabelle = new FlexTable();
		gruppenametext = new TextBox();
		titel.getElement().setId("TitelElemente");
		neuesmitglied = new NeuesMitglied();

		// Hinzufügen des ClickHandler zum Erestellen Button

		erstellenButton.addClickHandler(new ErstellenClickHandler());
		weiteresMitglied.addClickHandler(new WeiteresMitgliedClickHandler());

		// Hinzufügen unserer Widgets zur Tabelle

		tabelle.setWidget(1, 1, gruppename);
		tabelle.setWidget(1, 2, gruppenametext);
		tabelle.setWidget(2, 1, mitglieder);
		tabelle.setWidget(2, 2, neuesmitglied);
		panelfürgruppe.add(titel);
		panelfürgruppe.add(tabelle);
		panelfürbuttons.add(erstellenButton);
		panelfürbuttons.add(weiteresMitglied);
		panelfürgruppe.add(panelfürbuttons);
		this.add(panelfürgruppe);

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	class ErstellenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			for (TextBox feld : mitgliederfelder) {

				kinobesuchsplanung.findNutzerByEmail(feld.getText(), new NutzerCallback());
			}

			kinobesuchsplanung.createGruppe(nutzer, gruppenametext.getText(), gruppenvectorid,
					new GruppeErstellenCallback());

		}
	}


	class WeiteresMitgliedClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			tabelle.setWidget(rowCount, 2, new NeuesMitglied());
			rowCount++;

		}
	}



	class NutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus. test
			 */
			ClientSideSettings.getLogger().severe("Der Nutzer konnte nicht gefunden werden");

		}

		@Override
		public void onSuccess(Nutzer result) {
			gruppenvectorid.add(result);

		}

	}

	class GruppeErstellenCallback implements AsyncCallback<Gruppe> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Die Gruppe konnte nicht erstellt werden");

		}

		@Override
		public void onSuccess(Gruppe result) {
			Window.alert("Die Gruppe wurde erfolgreich erstellt.");
			Window.Location.reload();

		}

	}

	class NeuesMitglied extends TextBox {

		public NeuesMitglied() {
			super();
			mitgliederfelder.add(this);

		}

		public void onLoad() {

			super.onLoad();
			this.getElement().setPropertyString("placeholder", "Emailadresse des Nutzers");

		}

	}
}
