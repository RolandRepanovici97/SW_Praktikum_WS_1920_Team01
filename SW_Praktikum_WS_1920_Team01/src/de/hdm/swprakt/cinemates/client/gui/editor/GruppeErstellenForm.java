package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
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

	// Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();


	//Erzeugen der einzelnen Widgets

	Nutzer nutzer;
	private Label titel = new Label("Gruppe erstellen");
	private Label gruppename = new Label("Gruppename: ");
	private TextBox gruppenametext;
	private Label mitglieder = new Label("Mitglieder: ");
	private TextBox neuesmitglied;
	private VerticalPanel panelfürgruppe;
	private Grid tabelle;
	private Button erstellenButton = new Button("Gruppe erstellen");
	private Vector<Nutzer> gruppenvectorid = new Vector <Nutzer>();

	public void onLoad() {
		super.onLoad();

		// Instanttierung der Widgets

		nutzer = new Nutzer();
		panelfürgruppe = new VerticalPanel();
		tabelle = new Grid(3, 3);
		gruppenametext = new TextBox();
		titel.getElement().setId("TitelElemente");
		neuesmitglied = new TextBox();

		// Hinzufügen des ClickHandler zum Erestellen Button

		erstellenButton.addClickHandler(new ErstellenClickHandler());

		// Hinzufügen unserer Widgets zur Tabelle

		tabelle.setWidget(1, 1, gruppename);
		tabelle.setWidget(1, 2, gruppenametext);
		tabelle.setWidget(2, 1, mitglieder);
		tabelle.setWidget(2, 2, neuesmitglied);
		panelfürgruppe.add(titel);
		panelfürgruppe.add(tabelle);
		panelfürgruppe.add(erstellenButton);
		this.add(panelfürgruppe);


	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */




	class ErstellenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			Window.alert("test");
			// Prüfung, ob alle Angaben gemacht wurden test
			if (gruppenametext != null && neuesmitglied != null) {


				kinobesuchsplanung.findNutzerByEmail(neuesmitglied.getText(), new NutzerCallback());


			}
		}


	}

	class NutzerCallback implements AsyncCallback <Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus. test
			 */
			ClientSideSettings.getLogger().severe("Der Nutzer konnte nicht erstellt werden");

		}

		@Override
		public void onSuccess(Nutzer result) {
			gruppenvectorid.add(result);
			kinobesuchsplanung.createGruppe(nutzer, gruppenametext.getText(), gruppenvectorid, new GruppeErstellenCallback());

		}

	}

	class GruppeErstellenCallback implements AsyncCallback <Gruppe>{

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

		}

	}


}





