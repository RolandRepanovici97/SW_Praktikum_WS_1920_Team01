/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.dom.client.TitleElement;
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
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * Diese Klasse erweitert das VerticalPanel und wird benötigt, um eine neue
 * Umfrage anzulegen.
 * 
 * @author alina
 *
 */

public class UmfrageErstellenForm extends HorizontalPanel {

	// Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

	// Nutzer nutzer = new Nutzer();

	// Erzeugen der einzelnen Widgets

	private Label titel;
	private Label umfragename = new Label("Umfragename: ");
	private TextBox umfragenametext;
	private Label beschreibungLabel = new Label("Beschreibung: ");
	private TextBox beschreibungtext = new TextBox();
	private Label gruppe = new Label("Gruppe: ");
	private ListBox gruppebox;
	private Label film = new Label("Film: ");
	private ListBox filmbox;
	private Label datum = new Label("Datum: ");
	private DateBox datebox;
	private Button erstellenButton = new Button("Umfrage erstellen");
	private VerticalPanel panelfürumfrage;
	private Grid tabelle;

	public void onLoad() {
		super.onLoad();

		// Instanttierung der Widgets
		panelfürumfrage = new VerticalPanel();
		tabelle = new Grid(6, 3);
		umfragenametext = new TextBox();
		gruppebox = new ListBox();
		filmbox = new ListBox();
		datebox = new DateBox();
		titel = new Label("Umfrage erstellen");
		titel.getElement().setId("TitelElemente");

		/*
		 * Formatierung des Datumformats in den deutschen Standard
		 */
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		datebox.setFormat(new DateBox.DefaultFormat(dateFormat));
		datebox.getDatePicker().setYearArrowsVisible(true);

		// Aufruf um CallbackObjekte GruppeCallback und FilmCallback zu erhalten
		kinobesuchsplanung.getAllGruppen(new GruppeCallback());
		kinoadministration.getAllFilme(new FilmCallback());
		//
		// Hinzufügen des ClickHandlers zum Erstellen Button
		erstellenButton.addClickHandler(new ErstellenClickHandler());

		// Hinzufügen unserer Widgets zur Tabelle
		tabelle.setWidget(1, 1, umfragename);
		tabelle.setWidget(1, 2, umfragenametext);
		tabelle.setWidget(2, 1, beschreibungLabel);
		tabelle.setWidget(2, 2, beschreibungtext);
		tabelle.setWidget(3, 1, gruppe);
		tabelle.setWidget(3, 2, gruppebox);
		tabelle.setWidget(4, 1, film);
		tabelle.setWidget(4, 2, filmbox);
		tabelle.setWidget(5, 1, datum);
		tabelle.setWidget(5, 2, datebox);
		panelfürumfrage.add(titel);
		panelfürumfrage.add(tabelle);
		panelfürumfrage.add(erstellenButton);
		this.add(panelfürumfrage);

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
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht in
	 * Interaktion mit dem Nutzer: Wenn der Nutzer die Felder ausgfüllt hat, dann
	 * wird eine neue Umfrage erstellt. Die Eingaben des Nutzers stellen die
	 * Argumente dar. Zuletzt wird die Umfrage in der Datenbank gespeichert.
	 * 
	 * @author alina
	 */

	private class ErstellenClickHandler implements ClickHandler {

		private Film selektierterFilm;
		private Gruppe selektierteGruppe;

		/**
		 * @return the selektierterFilm
		 */
		public Film getSelektierterFilm() {
			return selektierterFilm;
		}

		/**
		 * @param selektierterFilm the selektierterFilm to set
		 */
		public void setSelektierterFilm(Film selektierterFilm) {
			this.selektierterFilm = selektierterFilm;
		}

		/**
		 * @return the selektierteGruppe
		 */
		public Gruppe getSelektierteGruppe() {
			return selektierteGruppe;
		}

		/**
		 * @param selektierteGruppe the selektierteGruppe to set
		 */
		public void setSelektierteGruppe(Gruppe selektierteGruppe) {
			this.selektierteGruppe = selektierteGruppe;
		}

		@Override
		public void onClick(ClickEvent event) {
			// Prüfung, ob alle Angaben gemacht wurden
			if (umfragenametext != null && gruppebox != null && filmbox != null && datebox != null) {

				kinobesuchsplanung.findGruppeByName(gruppebox.getSelectedItemText(), new SelektierteGruppeCallback());

				

			}
			// Falls Angaben gefehlt haben, geben wir folgendes aus:
			else {
				Window.alert("Bitte geben Sie alle Informationen an.");
			}
		}

		/**
		 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
		 * die Rückgabe eines Umfrageobjekts.
		 * 
		 * @author alina
		 */
		class UmfrageCallback implements AsyncCallback<Umfrage> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Die Umfrage konnte nicht erstellt werden");

			}

			@Override
			public void onSuccess(Umfrage result) {

				// Wenn wir unsere Umfrage erstellt haben, so setzen wir auch das Attribut Beschreibung

				// Setzen des Attributs beschreibung
				result.setBeschreibung(beschreibungtext.getText());

				// Wir informieren den Nutzer über den positiven Ausgang
				Window.alert("Die Umfrage wurde erstellt. Die Teilnehmer wurden per E-Mail darüber informiert.");

			}

		}

		/**
		 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
		 * die Rückgabe eines Umfrageobjekts.
		 * 
		 * @author alina
		 */
		class SelektierterFilmCallback implements AsyncCallback<Film> {
			
			Gruppe gruppe;
			SelektierterFilmCallback(Gruppe gruppe){
				this.gruppe = gruppe;
				
				
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Film result) {

				ClientSideSettings.getLogger().severe("Der selektierte Film wurde gefunden.");
				setSelektierterFilm(result);
				

				// Aufruf der Methode createUmfrage: Hierdurch wird implizit das neue
				// Umfrageobjekt in der DB gespeichert
				kinobesuchsplanung.createUmfrage(umfragenametext.getText(), result,
						this.gruppe, datebox.getValue(), new UmfrageCallback());

			}

		}

		/**
		 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
		 * die Rückgabe eines Umfrageobjekts.
		 * 
		 * @author alina
		 */
		class SelektierteGruppeCallback implements AsyncCallback<Gruppe> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Gruppe result) {

				ClientSideSettings.getLogger().severe("Die selektierte Gruppe wurde gefunden.");
				kinoadministration.getFilmByTitel(filmbox.getSelectedItemText(), new SelektierterFilmCallback(result));
				setSelektierteGruppe(result);

			}

		}

	}
}
