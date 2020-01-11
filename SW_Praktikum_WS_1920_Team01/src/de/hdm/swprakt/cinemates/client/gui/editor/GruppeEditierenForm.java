package de.hdm.swprakt.cinemates.client.gui.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;

/**
 * Diese Klasse erweitert das VerticalPanel. Hier wird die Möglichkeit zur Bearbeitung einer Gruppe geboten.
 * @author roland
 *
 */


public class GruppeEditierenForm extends HorizontalPanel {

	//Benötigte Klassenvariable, stellt die selektierte Gruppe dar
	private Gruppe gewählteGruppe;

	//Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

	//Initialisierung der benötigten Widgets
	private Label titel;
	private Grid tabelle;
	private Label gruppename = new Label("Gruppename: ");
	private TextBox gruppenameText = new TextBox();
	private Button speichernButton = new Button("Gruppe speichern");
	private Button löschenButton = new Button();
	private Label mitgliedern = new Label("Aktuelle Mitgliedern: ");
	private Button mitgliedlöschen = new Button("Mitglied löschen");
	private Button neuesmitglied = new Button ("Neues Mitglied hinfügen");
	private TextBox neuesmitgliedText = new TextBox();
	private HorizontalPanel horizontalPanel;


	// Getter & Setter für die Variable Gruppe: Wird benötigt, wenn eine Gruppe selektiert wurde

	/**
	 * @return the gewählteGruppe
	 */
	public Gruppe getGewählteGruppe() {
		return gewählteGruppe;
	}

	/**
	 * @param gewählteGruppe the gewählteGruppe to set
	 */
	public void setGewählteGruppe(Gruppe gewählteGruppe) {
		this.gewählteGruppe = gewählteGruppe;
	}

	public void onLoad() {
		super.onLoad();

		löschenButton.setHTML("<i class=\"far fa-trash-alt\"> <br> Gruppe löschen</i>");

		//Panel um zwei Button nebeneinander anzuordnen

		horizontalPanel = new HorizontalPanel();

		titel = new Label("Gruppe: " + gewählteGruppe.getGruppenname() + "bearbeiten");
		titel.getElement().setId("TitelElemente");
		gruppenameText.setText(gewählteGruppe.getGruppenname());

		//kinobesuchsplanung.getAllNutzerOfGruppe(gewählteGruppe, new MitgliederAnzeigenCallback());


		//Befüllen der Tabelle

		tabelle = new Grid(6,3);

		tabelle.setWidget(1, 1, gruppename);
		tabelle.setWidget(1, 2, gruppenameText);
		tabelle.setWidget(2, 1, mitgliedern);


	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der zweite Teil der GruppenVerwaltungSeite implementiert.
	 * Hier werde die aktuelle gewählte Gruppe des angemeldeten Nutzers angezeigt. 
	 * Er hat hier die Möglichkeit, den Gruppenname zu editieren, aktuelle Mitglieder
	 * zu löschen und neue hinzüfügen. Der Nutzer kann auch die Gruppe löschen oder
	 * alle seine Änderungen speichern.
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	
	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
	 * die Rückgabe des editierten Gruppeobjekts
	 * 
	 * @author roland
	 */
	class GruppeCallback implements AsyncCallback<Gruppe> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Gruppe konnte nicht geladen werden");
		}

		@Override
		public void onSuccess(Gruppe result) {
			Window.alert("Die Gruppe wurde erfolgreich editiert!");
		}
	}

	
	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und das Löschen
	 * einer selktierten Gruppe
	 * 
	 * @author roland
	 */
	class LoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Gruppe konnte nicht gelöscht werden");
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Die Gruppe wurde erfolgreich gelöscht!");
		}
	}

	
	/**
	 * Diese Nested Class implementiert das Interface ClickHandler.
	 * Klickt der Nutzer diesen Button an, so werden die Änderungen an der Gruppe gespeichert
	 * 
	 * @author Roland
	 */

	class SpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			gewählteGruppe.setGruppenname(gruppenameText.getText());
			//kinobesuchsplanung.save(gewählteGruppe, new GruppeCallback());
			
		}
			
	}
	
	/**
	 * Diese Nested Class implmentiert das Interface ClickHandler.
	 * Klickt der Nutzer diessen Button an, so wird die Gruppe gelöscht.
	 * 
	 * @author Roland
	 */

	class LoeschenClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			kinobesuchsplanung.deleteGruppe(gewählteGruppe, new LoeschenCallback());
		}
		
	}




















}
