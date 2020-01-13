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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
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
 * Diese Klasse erweitert das <code>HorizontalPanel</code> und wird benötigt, um eine neue
 * Gruppe anzulegen.
 * 
 * @author Roland
 * @author alina 
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


	private Button weiteresMitglied;
	private Vector<SuggestBox> mitgliederfelder = new Vector<SuggestBox>();;
	private int rowCount;
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private Vector<Nutzer> nutzerCinemates = new Vector<Nutzer>();
	   
	public void onLoad() {
		super.onLoad();
		
		kinobesuchsplanung.findAllNutzer(new AllNutzerCallback());

		// Wir erhalten den aktuellen Nutzer
		nutzer = KinobesuchsplanungEntry.AktuellerNutzer.getNutzer();
		
		//Initiales Setzen des RowCount auf 3, hier fangen wir an neue Zeilen hinzuzufügen
		rowCount= 3;
		
		/**
		 *  Instanttierung der Widgets
		 */
		

		weiteresMitglied = new Button("Weiteres Mitglied hinzufügen");
		panelfürgruppe = new VerticalPanel();
		panelfürbuttons = new HorizontalPanel();
		tabelle = new FlexTable();
		gruppenametext = new TextBox();
		titel.getElement().setId("TitelElemente");
		//Ein Mitglied muss auf jeden Fall hinzugefügt werden, weitere sind optional
		neuesmitglied = new NeuesMitglied(oracle);

		// Hinzufügen der ClickHandler

		erstellenButton.addClickHandler(new ErstellenClickHandler());
		weiteresMitglied.addClickHandler(new WeiteresMitgliedClickHandler());

		// Hinzufügen unserer Widgets zur Tabelle

		tabelle.setWidget(1, 1, gruppename);
		tabelle.setWidget(1, 2, gruppenametext);
		tabelle.setWidget(2, 1, mitglieder);
		tabelle.setWidget(2, 2, neuesmitglied);
		
		//Hinzufügen der Widgets zu den Panels
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

	
	/**
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht in
	 * Interaktion mit dem Nutzer: Wenn der Nutzer die Felder ausgfüllt hat, dann
	 * wird eine neue Gruppe erstellt. Die Eingaben des Nutzers stellen die
	 * Argumente dar.
	 * 
	 * @author alina
	 */

	class ErstellenClickHandler implements ClickHandler {

		
		public void onClick(ClickEvent event) {
		
			
			Vector<Nutzer> gruppenmitglieder = new Vector<Nutzer>();
			
			for (SuggestBox feld : mitgliederfelder) {

				kinobesuchsplanung.findNutzerByEmail(feld.getText().trim(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientSideSettings.getLogger().severe("Der Nutzer konnte nicht gefunden werden");
						
					}

					@Override
					public void onSuccess(Nutzer result) {
						
						gruppenmitglieder.add(result);
						ClientSideSettings.getLogger().severe(result.toString());
						
					}
					
				});
			}
			
			
			ClientSideSettings.getLogger().severe(gruppenmitglieder.toString());
			kinobesuchsplanung.createGruppe(nutzer, gruppenametext.getText(), gruppenmitglieder,
					new GruppeErstellenCallback());

		}
	}
	


	/**
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht in
	 * Interaktion mit dem Nutzer: Wenn der Nutzer darauf klickt, wird der FlexTable ein neues TextFeld
	 * hinzugefügt, in welches er einen weiteren Nutzer einfügen kann.
	 * 
	 * @author alina
	 */

	class WeiteresMitgliedClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			tabelle.setWidget(rowCount, 2, new NeuesMitglied(oracle));
			rowCount++;

		}
	}



	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback.
	 * Hier erhalten wir den Nutzer der durch findByEmail gefunden wurde.
	 * Wir fügen den Nutzer unserem gruppenvectorid hinzu. Dieser sammelt alle Nutzerobjekte
	 * und stellt letztlich das Argument für createGruppe dar.
	 * 
	 * @author alina
	 */


	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback.
	 * Wird die Gruppe ordnungsgemäß erstellt, lassen wir dies den Nutzer wissen.
	 * @author alina
	 */

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
			ClientSideSettings.getLogger().severe(result.toString());
			Window.alert("Die Gruppe wurde erfolgreich erstellt.");
	
			Window.Location.reload();

		}

	}
	
	class AllNutzerCallback implements AsyncCallback<Vector<Nutzer>> {

		@Override
		public void onFailure(Throwable caught) {
			
			ClientSideSettings.getLogger().severe("Keine Nutzer gefunden");
			
		}

		@Override
		public void onSuccess(Vector<Nutzer> result) {
		
			for(Nutzer n : result) {
				oracle.add(n.getEmail());
			}
			
		}
		
	}
	/**
	 * Diese Nested Class implementiert das Widget TextBox.
	 * Da wir immer wieder ein solches Objekt erstellen möchten,
	 * ist es sinnvoll, seine Funktionen in einer Klasse zu kapseln. 
	 * 
	 * @author alina
	 */
	class NeuesMitglied extends SuggestBox {

		//Bei jeder Instanziierung wird die Textbox unserem Vector hinzugefügt.
		public NeuesMitglied(SuggestOracle oracle) {
			super(oracle);
			mitgliederfelder.add(this);

		}

		public void onLoad() {

			super.onLoad();
			this.getElement().setPropertyString("placeholder", "Emailadresse des Nutzers");

		}

	}
	
}
