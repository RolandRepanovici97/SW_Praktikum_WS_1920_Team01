/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.KinobesuchsplanungEntry;
import de.hdm.swprakt.cinemates.client.KinobesuchsplanungEntry.AktuellerNutzer;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * Diese Klasse erweitert das HorizontalPanel und stellt den Body der Seite dar.
 * Die Klasse dient als Einstieg und zur Navigation innerhalb des
 * Editor-Clients. Von hier aus kann zu den Umfragen, zur Abstimmung und zur
 * Umfragenerstellung navigiert werden.
 * 
 * @author alina
 *
 */
public class StartseiteEditor extends HorizontalPanel {

	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der erste Teil der Startseite des Editors implementiert.
	 * Hier werden alle Umfragen des angemeldeten Nutzers angezeigt. Er hat hier die
	 * Möglichkeit, durch Klicken auf die Umfrageobjekte, diese anzeigen zu lassen.
	 * ***************************************************************************
	 */

	private Vector<Umfrage> umfragen = new Vector<Umfrage>();
	private Label label1 = new Label("Meine Umfragen");
	private Label label2 = new Label("Neue Umfragen");
	private Nutzer nutzer;
	private VerticalPanel panelfürumfragen = new VerticalPanel();
	private Button neueUmfrage = new Button();
	private FlexTable tabelle;

	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();

	public void onLoad() {
		super.onLoad();

		neueUmfrage.setHTML("<i class=\"fas fa-plus\"></i>");

		// neueUmfrage.addClickHandler(new NeueUmfrageClickHandler());

		KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
		kinobesuchsplanung.showAllUmfrageOfNutzer(nutzer, new UmfragenAnzeigenCallback());

		kinobesuchsplanung.showAllUmfrage(new UmfragenAnzeigenCallback());
		// kinobesuchsplanung.showAllUmfrageOfNutzer(nutzer, new
		// UmfragenAnzeigenCallback());
		tabelle = new FlexTable();

		panelfürumfragen.add(label1);
		panelfürumfragen.add(label2);
		panelfürumfragen.add(neueUmfrage);

		neueUmfrage.addClickHandler(new NeueUmfrageClickHandler());

		this.add(tabelle);

		this.add(panelfürumfragen);
		RootPanel.get("DetailsPanel").add(this);

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der erste Teil der Startseite des Editors implementiert.
	 * Hier werden alle Umfragen des angemeldeten Nutzers angezeigt. Er hat hier die
	 * Möglichkeit, durch Klicken auf die Umfrageobjekte, diese anzeigen zu lassen.
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	/**
	 * Diese Nested Class wird als Callback für das Anzeigen der Umfrageobjekte
	 * benötigt.
	 * 
	 * @author alina
	 */
	class UmfragenAnzeigenCallback implements AsyncCallback<Vector<Umfrage>> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Ihre Umfragen konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Umfrage> result) {

			ClientSideSettings.getLogger().severe("Ihre Umfragen wurden geladen.");

			int rowCount = 0;

			for (Umfrage u : result) {

				tabelle.setText(rowCount, 0, u.toString());
				rowCount += 1;

				// Wir instanttieren ein neues UmfrageAuswahl-Objekt und übergeben unsere
				// Umfrage
				UmfrageAuswahl auswahl = new UmfrageAuswahl(u);

				tabelle.add(auswahl);

				// Wir geben diesem Umfrage-Auswahl-Objekt einen ClickHandler, durch welchen die
				// Detailanzeige der Umfrage angezeigt wird
				auswahl.addClickHandler(new UmfrageAuswählenClickHandler());

			}
		}
	}

	/**
	 * Diese Nested Class wird als Callback für das Anzeigen neuer Umfrageobjekte
	 * benötigt.
	 * 
	 * @author alina
	 */

	class OffeneUmfragenAnzeigenCallback implements AsyncCallback<Vector<Umfrage>> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Ihre neuen Umfragen konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Umfrage> result) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht
	 * die Interaktion zur Auswahl eines Umfrageobjekts.
	 * 
	 * @author alina
	 */

	class UmfrageAuswählenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			UmfrageAnzeige anzeige = new UmfrageAnzeige();
			RootPanel.get("DeatilsPanel").add(anzeige);

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht
	 * die Interaktion zur Weiterleitung auf die Möglichkeit zur Erstellung einer
	 * neuen Umfrage,
	 * 
	 * @author alina
	 */
}

class NeueUmfrageClickHandler implements ClickHandler {

	public void onClick(ClickEvent event) {

		RootPanel.get("DetailsPanel").clear();
		UmfrageErstellenForm neueUmfrage = new UmfrageErstellenForm();
		RootPanel.get("DetailsPanel").add(neueUmfrage);
	}

}

class OffeneUmfragenAnzeigenCallback implements AsyncCallback<Vector<Umfrage>> {

	@Override
	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
		 */
		ClientSideSettings.getLogger().severe("Ihre neuen Umfragen konnten nicht geladen werden");
	}

	@Override
	public void onSuccess(Vector<Umfrage> result) {
		// TODO Auto-generated method stub

	}
}

/**
 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht
 * die Interaktion zur Auswahl eines Umfrageobjekts.
 * 
 * @author alina
 */

class UmfrageAuswählenClickHandler implements ClickHandler {

	public void onClick(ClickEvent event) {

		UmfrageAnzeige anzeige = new UmfrageAnzeige();
		RootPanel.get("DeatilsPanel").add(anzeige);

	}
}
