/**
 * Diese Klasse erweitert das VerticalPanel und wird verwendet, um die Abstimmung einer
 * Umfrage zu realisieren. Die Umfrage wurde in einem vorherigen Schritt selektiert.
 * 
 * @author alina
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;

/**
 * @author alina
 *
 */
public class AbstimmenForm extends VerticalPanel {

	// Benötigte Klassenvariable, stellt die selektierte Umfrage dar
	private Umfrage gewählteUmfrage;

	// Getter & Setter für die Variable Umfrage: Wird benötigt, wenn Umfrage
	// selektiert wurde

	/**
	 * @return the gewählteUmfrage
	 */
	public Umfrage getGewählteUmfrage() {
		return gewählteUmfrage;
	}

	/**
	 * @param gewählteUmfrage the gewählteUmfrage to set
	 */
	public void setGewählteUmfrage(Umfrage gewählteUmfrage) {
		this.gewählteUmfrage = gewählteUmfrage;
	}

	// Initialisierung der benötigten Widgets
	private Label titel;
	// Erzeugen der Widgets
	private Label beschreibungLabel = new Label("Beschreibung: ");
	private Label beschreibung;
	private FlexTable einträge;
	private Label film;
	private Label datum;
	private Label filmbeschreibung;
	private Label filmdetails;
	private HorizontalPanel horizontalPanel;

	// Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

	public void onLoad() {
		super.onLoad();

		titel = new Label("Abstimmung zur Umfrage: " + gewählteUmfrage.getUmfragenname());
		titel.getElement().setId("TitelElemente");

		horizontalPanel = new HorizontalPanel();

		// Tag soll angezeigt werden
		datum = new Label("Geplanter Tag: \n" + gewählteUmfrage.getDatum().toString());

		// Film soll angezeigt werden
		int filmID = gewählteUmfrage.getFilmID();

		kinoadministration.getFilmByID(filmID, new FilmCallback());

		film = new Label();
		filmbeschreibung = new Label();
		filmdetails = new Label();

		// Wir stellen die Beschreibung der Umfrage dar
		beschreibung = new Label(gewählteUmfrage.getBeschreibung());

		einträge = new FlexTable();

		// Hier erhalten wir ein Callback Vector <Umfrageeintrag>, welcher alle
		// Umfrageeinträge der gewählten Umfrage bereitstellt
		kinobesuchsplanung.showUmfrageeinträgeofUmfrage(gewählteUmfrage, new UmfrageeintragCallback());

		// Hinzufügen der Widgets zu unserem Panel
		horizontalPanel.add(beschreibungLabel);
		horizontalPanel.add(beschreibung);
		this.add(titel);
		this.add(horizontalPanel);
		this.add(film);
		this.add(filmbeschreibung);
		this.add(filmdetails);
		this.add(datum);
		this.add(einträge);

	}

	/**
	 * Diese Nested-Class implementiert das Interface AsyncCallback und wird
	 * benötigt, um die Umfrageeinträge der gewählten Umfrage zurückzugeben.
	 * 
	 * @author alina
	 *
	 */
	class UmfrageeintragCallback implements AsyncCallback<Vector<Umfrageeintrag>> {

		String spielzeitstring;
		String kinostring;

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Die Umfrageeinträge konnten nicht geladen werden.");
		}

		@Override
		public void onSuccess(Vector<Umfrageeintrag> result) {

			ClientSideSettings.getLogger().severe("Die Umfrageeinträge wurden geladen.");
			int rowCount = 0;

			for (Umfrageeintrag eintrag : result) {

				kinoadministration.getSpielzeitByID(eintrag.getKinoID(), new SpielzeitCallback());
				kinoadministration.getKinoByID(eintrag.getKinoID(), new Kinocallback());

				einträge.setText(rowCount, 0, "Uhrzeit: \n " + spielzeitstring + "\n Kino: " + kinostring);
				einträge.setWidget(rowCount, 1, new JaBox());
				einträge.setWidget(rowCount, 2, new NeinBox());
				rowCount++;

			}
		}

		class SpielzeitCallback implements AsyncCallback<Spielzeit> {

			@Override
			public void onFailure(Throwable caught) {

				/*
				 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
				 */
				ClientSideSettings.getLogger().severe("Die Spielzeit konnte nicht geladen werden.");

			}

			@Override
			public void onSuccess(Spielzeit result) {
				spielzeitstring = result.toString();

			}

		}

		class Kinocallback implements AsyncCallback<Kino> {

			@Override
			public void onFailure(Throwable caught) {
				/*
				 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
				 */
				ClientSideSettings.getLogger().severe("Das Kino konnte nicht geladen werden.");

			}

			@Override
			public void onSuccess(Kino result) {
				// kinostring = result.getKinoname() + "/n" + result.getAdresse();

			}

		}
	}

	class FilmCallback implements AsyncCallback<Film> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Der Film konnte nicht geladen werden.");

		}

		@Override
		public void onSuccess(Film result) {

			ClientSideSettings.getLogger().severe("Der Film wurde geladen");
			film.setText("Film: \n" + result.getFilmtitel());
			filmbeschreibung.setText("Filmbeschreibung: \n " + result.getBeschreibung());
			filmdetails.setText("Filmdetails: \n" + result.getDetails());
		}

	}
	
	/**
	 * Diese Klasse erweitert das Widget CheckBox und dient zur Darstellung
	 * der Ja-Checkbox.
	 * @author alina
	 *
	 */

	class JaBox extends CheckBox {
		public JaBox() {
			super();
			this.setHTML("<i class=\"far fa-check-circle\"></i>");
		}
	}

	/**
	 * Diese Klasse erweitert das Widget CheckBox und dient zur Darstellung
	 * der Nein-Checkbox.
	 * @author alina
	 *
	 */
	class NeinBox extends CheckBox {

		public NeinBox() {
			super();
			this.setHTML("<i class=\"far fa-times-circle\"></i>");
		}
	}
}
