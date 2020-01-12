/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
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
public class StartseiteEditor extends VerticalPanel {

	public StartseiteEditor() {
		super();
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der erste Teil der Startseite des Editors implementiert.
	 * Hier werden alle Umfragen des angemeldeten Nutzers angezeigt. Er hat hier die
	 * Möglichkeit, durch Klicken auf die Umfrageobjekte, diese anzeigen zu lassen.
	 * ***************************************************************************
	 */

	private Label label1 = new Label("Meine Umfragen");
	private Button neueUmfrage = new Button();
	FlowPanel panel;
	HorizontalPanel hpanel1;
	HorizontalPanel hpanel2;

	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();

	public void onLoad() {
		super.onLoad();

		panel = new FlowPanel();
		// panel.getElement().getStyle().setDisplay(Display.FLEX);
		hpanel1 = new HorizontalPanel();
		hpanel2 = new HorizontalPanel();

		neueUmfrage.setHTML("<i class=\"fas fa-plus\"></i>");
		label1.getElement().setId("TitelElemente");

		neueUmfrage.addClickHandler(new NeueUmfrageClickHandler());

		kinobesuchsplanung.showAllUmfrage(new UmfragenAnzeigenCallback());

		// kinobesuchsplanung.showAllUmfrageOfNutzer(nutzer, new
		// UmfragenAnzeigenCallback());

		hpanel1.add(label1);
		hpanel2.add(neueUmfrage);
		this.add(hpanel1);
		this.add(panel);
		this.add(hpanel2);

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

			for (Umfrage u : result) {

				// für jede erhaltene Umgfrage erstellen wir ein neues UmfragePanel und fügen
				// des dem FlowPanel hinzu

				panel.add(new UmfragePanel(u));

			}
		}
	}

	/**
	 * Diese Nested Class wird zur Repräsentation der Umfrage benötigt benötigt.
	 * 
	 * @author alina
	 */
	class UmfragePanel extends VerticalPanel {

		Umfrage umfrage;
		HorizontalPanel horizontalPanel;

		// Die Klasse benötigt die zurückgegebene Umfrage

		UmfragePanel(Umfrage umfrage) {
			this.umfrage = umfrage;
		}

		public void onLoad() {
			super.onLoad();

			// Hinzufügen des Styles
			this.addStyleName("UmfragePanel");

			horizontalPanel = new HorizontalPanel();
			Label umfragename = new Label();
			// Setzen des Umfragenamens
			umfragename.setText(umfrage.getUmfragenname());

			// Erzeugen der Buttons
			Button umfrageAnzeigen = new Button("Anzeigen");
			Button abstimmungenAnsehen = new Button("Abstimmungen ansehen");

			// Klickt der Nutzer auf "Anzeigen" gelangt er zu den Details der gewählten
			// Umfrage
			umfrageAnzeigen.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					RootPanel.get("DetailsPanel").clear();
					UmfrageAnzeige anzeige = new UmfrageAnzeige();
					anzeige.setGewählteUmfrage(umfrage);
					RootPanel.get("DetailsPanel").add(anzeige);
				}

			});

			// Hinzufügen der Widgets zum Panel
			this.add(umfragename);
			horizontalPanel.add(umfrageAnzeigen);
			horizontalPanel.add(abstimmungenAnsehen);
			this.add(horizontalPanel);

		}

	}

	//
	// /**
	// * Diese Nested Class wird als Callback für das Anzeigen neuer Umfrageobjekte
	// * benötigt.
	// *
	// * @author alina
	// */
	//
	// class OffeneUmfragenAnzeigenCallback implements
	// AsyncCallback<Vector<Umfrage>> {
	//
	// @Override
	// public void onFailure(Throwable caught) {
	// /*
	// * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
	// */
	// ClientSideSettings.getLogger().severe("Ihre neuen Umfragen konnten nicht
	// geladen werden");
	// }
	//
	// @Override
	// public void onSuccess(Vector<Umfrage> result) {
	// // TODO Auto-generated method stub
	//
	// }
}
//
//	/**
//	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht
//	 * die Interaktion zur Auswahl eines Umfrageobjekts.
//	 * 
//	 * @author alina
//	 */
//
//	class UmfrageAuswählenClickHandler implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//			UmfrageAnzeige anzeige = new UmfrageAnzeige();
//			RootPanel.get("DeatilsPanel").add(anzeige);

//
//		}
//
//	}
//
//	/**
//	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht
//	 * die Interaktion zur Weiterleitung auf die Möglichkeit zur Erstellung einer
//	 * neuen Umfrage,
//	 * 
//	 * @author alina
//	 */
//}
//
class NeueUmfrageClickHandler implements ClickHandler {

	public void onClick(ClickEvent event) {

		RootPanel.get("DetailsPanel").clear();
		UmfrageErstellenForm neueUmfrage = new UmfrageErstellenForm();
		RootPanel.get("DetailsPanel").add(neueUmfrage);
	}

}
//
//class OffeneUmfragenAnzeigenCallback implements AsyncCallback<Vector<Umfrage>> {
//
//	@Override
//	public void onFailure(Throwable caught) {
//		/*
//		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
//		 */
//		ClientSideSettings.getLogger().severe("Ihre neuen Umfragen konnten nicht geladen werden");
//	}
//
//	@Override
//	public void onSuccess(Vector<Umfrage> result) {
//		// TODO Auto-generated method stub
//
//	}
//}
//
///**
// * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht
// * die Interaktion zur Auswahl eines Umfrageobjekts.
// * 
// * @author alina
// */
//
//class UmfrageAuswählenClickHandler implements ClickHandler {
//
//	public void onClick(ClickEvent event) {
//
//		UmfrageAnzeige anzeige = new UmfrageAnzeige();
//		RootPanel.get("DeatilsPanel").add(anzeige);
//
//	}
//}

class UmfrageBearbeitenCallback implements AsyncCallback<Umfrage> {

	@Override
	public void onFailure(Throwable caught) {

	}

	@Override
	public void onSuccess(Umfrage result) {
		// TODO Auto-generated method stub

	}

}
