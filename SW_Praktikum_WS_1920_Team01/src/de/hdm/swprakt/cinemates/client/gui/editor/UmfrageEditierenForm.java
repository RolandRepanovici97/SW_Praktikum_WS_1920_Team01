/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * Diese Klasse erweitert das VerticalPanel. Hier wird die Möglichkeit zur
 * Bearbeitung einer Umfrage geboten.
 * 
 * @author alina
 *
 */
public class UmfrageEditierenForm extends VerticalPanel {

	// Benötigte Klassenvariable, stellt die selektierte Umfrage dar
	private Umfrage gewählteUmfrage;

	// Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

	// Initialisierung der benötigten Widgets
	private Label titel;
	private Grid tabelle;
	private Label umfragename = new Label("Neuer Umfragename: ");
	private TextBox umfragennameText = new TextBox();
	private Label beschreibungLabel = new Label("Neue Beschreibung: ");
	private TextBox neueBeschreibungText = new TextBox();
	private Button speichernButton = new Button("Umfrage speichern");
	private Button löschenButton = new Button();
	private Label neueGruppe = new Label("Neue Gruppe: ");
	private ListBox gruppebox;
	private Label neuerFilm = new Label("Neuer Film: ");
	private ListBox filmbox;
	private Label datum = new Label("Neues Datum: ");
	private DateBox datebox;
	private HorizontalPanel horizontalPanel;

	// Getter & Setter für die Variable Umfrage: Wird benötigt, wenn Umfrage
	// selektiert wurde

	/**
	 * @return the gewählteUmfrage
	 */

	public UmfrageEditierenForm(Umfrage gewählteumfrage) {
		this.gewählteUmfrage = gewählteumfrage;
	}

	public Umfrage getGewählteUmfrage() {
		return gewählteUmfrage;
	}

	/**
	 * @param gewählteUmfrage the gewählteUmfrage to set
	 */
	public void setGewählteUmfrage(Umfrage gewählteUmfrage) {
		this.gewählteUmfrage = gewählteUmfrage;
	}

	public void onLoad() {
		super.onLoad();

		löschenButton.setHTML("<i class=\"far fa-trash-alt\"> <br> Umfrage löschen</i>");

		// Panel um zwei Buttons nebeneinander anzuordnen
		horizontalPanel = new HorizontalPanel();
		gruppebox = new ListBox();
		filmbox = new ListBox();
		datebox = new DateBox();

		titel = new Label("Umfrage: " + gewählteUmfrage.getUmfragenname() + " bearbeiten");
		titel.getElement().setId("TitelElemente");
		umfragennameText.setText(gewählteUmfrage.getUmfragenname());

		neueBeschreibungText.setText(gewählteUmfrage.getBeschreibung());
		/*
		 * Formatierung des Datumformats in den deutschen Standard
		 */
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		datebox.setFormat(new DateBox.DefaultFormat(dateFormat));
		datebox.getDatePicker().setYearArrowsVisible(true);

		// Setzen des selektierten Werts auf das ursprüngliche Datum der Umfrage
		datebox.setValue(gewählteUmfrage.getDatum());

		// Aufruf um CallbackObjekte GruppeCallback und FilmCallback zu erhalten
		kinoadministration.getAllFilme(new FilmCallback());
		kinobesuchsplanung.getAllGruppen(new GruppeCallback());

		// Befüllen der Tabelle
		tabelle = new Grid(6, 3);
		tabelle.setWidget(1, 1, umfragename);
		tabelle.setWidget(1, 2, umfragennameText);
		tabelle.setWidget(2, 1, beschreibungLabel);
		tabelle.setWidget(2, 2, neueBeschreibungText);
		tabelle.setWidget(3, 1, neueGruppe);
		tabelle.setWidget(3, 2, gruppebox);
		tabelle.setWidget(4, 1, neuerFilm);
		tabelle.setWidget(4, 2, filmbox);
		tabelle.setWidget(5, 1, datum);
		tabelle.setWidget(5, 2, datebox);

		// Hinzufügen des ClickHandlers zum Speichern Button
		speichernButton.addClickHandler(new SpeichernClickHandler());

		// Hinzufügen des ClickHandlers zum Löschen Button
		löschenButton.addClickHandler(new LoeschenClickHandler());

		// Hinzufügen der Widgets zu den Panels

		this.add(titel);
		this.add(tabelle);
		horizontalPanel.add(speichernButton);
		horizontalPanel.add(löschenButton);
		this.add(horizontalPanel);

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallBack und ermöglicht
	 * die Rückgabe der Filmobjekte.
	 * 
	 * @author alina
	 */
	class FilmCallback implements AsyncCallback<Vector<Film>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Filme konnten nicht geladen werden");

		}

		@Override
		public void onSuccess(Vector<Film> result) {
			ClientSideSettings.getLogger().severe("Die Filme wurden geladen.");
			for (Film film : result) {

				filmbox.addItem(film.getFilmtitel());

			}
		}

	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
	 * die Rückgabe der Gruppenobjekte eines Nutzers.
	 *
	 * @author alina
	 */
	class GruppeCallback implements AsyncCallback<Vector<Gruppe>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Gruppen konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Gruppe> result) {
			ClientSideSettings.getLogger().severe("Ihre Gruppen wurden geladen.");

			for (Gruppe gruppe : result) {
				gruppebox.addItem(gruppe.getGruppenname());

			}

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
	 * die Rückgabe des editierten Umfrageobjekts.
	 *
	 * @author alina
	 */
	class UmfrageCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Umfrage konnte nicht geladen werden");
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Die Umfrage wurde erfolgreich editiert!");
			Window.Location.reload();

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und das Löschen
	 * einer selektierten Umfrage.
	 * 
	 * @author alina
	 */
	class LoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Umfrage konnte nicht gelöscht werden");

		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Die Umfrage wurde erfolgreich gelöscht!");

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface ClickHandler. Klickt der
	 * Nutzer diesen Button an, so werden die Änderungen an der Umfrage gespeichert.
	 * 
	 * @author alina
	 */
	class SpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			gewählteUmfrage.setUmfragenname(umfragennameText.getText());
			gewählteUmfrage.setBeschreibung(neueBeschreibungText.getText());
			// gewählteUmfrage.setGruppenIDs(gruppebox.getValue());
			// gewählteUmfrage.setFilmID(filmbox.getValue());
			gewählteUmfrage.setDatum(datebox.getValue());
			kinobesuchsplanung.save(gewählteUmfrage, new UmfrageCallback());

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface ClickHandler. Klickt der
	 * Nutzer diessen Button an, so wird die Umfrage gelöscht.
	 * 
	 * @author alina
	 */

	class LoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			kinobesuchsplanung.deleteUmfrage(gewählteUmfrage, new LoeschenCallback());

		}

	}

}
