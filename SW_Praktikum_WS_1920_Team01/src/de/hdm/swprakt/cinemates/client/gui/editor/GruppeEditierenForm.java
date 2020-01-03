package de.hdm.swprakt.cinemates.client.gui.editor;

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
	private Label gruppename = new Label("Neuer Gruppename: ");
	private TextBox gruppennameText = new TextBox();
	private Button speichernButton = new Button("Speichern");
	private Button löschenButton = new Button();
	private Label mitgliedern = new Label("Aktuelle Mitgliedern: ");
	private Button mitgliedlöschen = new Button("Mitglied löschen");
	private Button neuesmitglied = new Button ("Neues Mitglied hinfügen");
	private TextBox neuesmitgliedeingeben = new TextBox();
	
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
	}
	
}
