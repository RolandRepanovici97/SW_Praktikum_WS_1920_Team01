package de.hdm.swprakt.cinemates.client.gui.editor;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;



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

	// Nutzer nutzer = new Nutzer();

	//Erzeugen der einzelnen Widgets

	private Label titel = new Label("Gruppe erstellen");
	private Label gruppename = new Label("Gruppename: ");
	private TextBox gruppenametext;
	private Label mitglieder = new Label("Mitglieder: ");
	private TextBox neuesmitglied;
	private VerticalPanel panelfürgruppe;
	private Grid tabelle;
	private Button erstellenButton = new Button("Gruppe erstellen");

	public void onLoad() {
		super.onLoad();

		// Instanttierung der Widgets

		panelfürgruppe = new VerticalPanel();
		tabelle = new Grid(3, 3);
		gruppenametext = new TextBox();
		titel.getElement().setId("TitelElemente");

		// Hinzufügen des ClickHandler zum Erestellen Button

		//erstellenButton.addClickHandler(new ErstellenClickHandler());

		// Hinzufügen unserer Widgets zur Tabelle

		tabelle.setWidget(1, 1, gruppename);
		tabelle.setWidget(1, 2, gruppenametext);
		tabelle.setWidget(2, 1, mitglieder);
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








}
