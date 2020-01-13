/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * @author alina
 * @author roland
 *
 */
public class GruppeDetailsForm extends VerticalPanel {

	// Benötigte Klassenvariable
	private Gruppe gewählteGruppe;

	//Erzeugen der Widgets
	private Label titel;
	private Label gruppename;
	private Label mitgliedern = new Label("Aktuelle Mitglidern: ");
	private HorizontalPanel horizontalPanel;
	CellTable <Nutzer> tabelle;
	List <Nutzer> liste;

	//Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

	//Gettetr & Setter für die Variable Gruppe: Wird benötigt, wenn Gruppe aus Liste selektiert wird
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

	/**
	 * onLoad() Methode: Hier wird das Gruppeobjekt übergeben!
	 * 
	 * @param Gruppeobjekt, zu welchem uns die Details interessieren.
	 * @author roland
	 */
	public void onLoad() {
		super.onLoad();

		horizontalPanel = new HorizontalPanel();

		tabelle = new CellTable<Nutzer>();

		titel = new Label("Gruppe: " + gewählteGruppe.getGruppenname());
		titel.getElement().setId("TitelElemente");


		//Callback Vector <Nutzer>, welcher alle Nutzer der gewählte Gruppe bereitstellt

		kinobesuchsplanung.getAllNutzerOfGruppe(gewählteGruppe, new NutzerCallback());



		//Hinzufügen der Widgets zu unserem Panel

		horizontalPanel.add(titel);
		horizontalPanel.add(gruppename);
		horizontalPanel.add(mitgliedern);


		this.add(horizontalPanel);
		this.add(tabelle);


	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	/**
	 * Diese Nested-Class implementiert das Interface AsyncCallback und wird
	 * benötigt, uzm die Gruppenmitglieder der gewählte Gruppe zurückzugeben.
	 * 
	 * @author roland
	 */
	class NutzerCallback implements AsyncCallback<Vector<Nutzer>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Gruppe konnte nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Nutzer> result) {

			ListDataProvider <Nutzer> model = new ListDataProvider<Nutzer>();

			TextColumn <Nutzer> name = new TextColumn<Nutzer>() {
				@Override
				public String getValue(Nutzer nutzer) {
					return nutzer.getNutzername();
				}
			};

			tabelle.addColumn(name, "Name");

			for(Nutzer nutzer : result) {

				model.addDataDisplay((HasData<Nutzer>) result);
			}


		}

	}



}

