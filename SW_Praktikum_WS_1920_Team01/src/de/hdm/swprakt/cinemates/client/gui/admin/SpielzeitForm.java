package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;

/**
 * Diese Klasse erweitert das HorizontalPanel und wird benötigt, um eine neue
 * Spielzeit anzulegen.
 * 
 * @author ömer
 *
 */

public class SpielzeitForm extends HorizontalPanel {

	//Erzeugen der Widgets
	
	ListBox kinolistbox = new ListBox();
	ListBox filmlistbox = new ListBox();
	DateBox datebox = new DateBox();
	IntegerBox hourbox = new IntegerBox();
	IntegerBox minutebox = new IntegerBox();
	Button speichernButton = new Button("Spielzeit speichern");
	Kinokette kinokette = new Kinokette();

	VerticalPanel detailsPanel = new VerticalPanel();

	// Setzen des asynchronen Interfaces
	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	public SpielzeitForm() {

	}

	public void onLoad() {

		super.onLoad();
		
		// Instanttierung der Widgets
		Grid spielzeitGrid = new Grid(9, 9);

		Label name = new Label("Kino");
		spielzeitGrid.setWidget(0, 1, kinolistbox);
		spielzeitGrid.setWidget(0, 0, name);

		Label film = new Label("Film");
		spielzeitGrid.setWidget(1, 1, filmlistbox);
		spielzeitGrid.setWidget(1, 0, film);

		Label spieldatum = new Label("Datum");
		spielzeitGrid.setWidget(1, 3, spieldatum);
		spielzeitGrid.setWidget(1, 4, datebox);
		
		Label zeit = new Label("Zeit  Stunde");
		spielzeitGrid.setWidget(2, 3, zeit);
		spielzeitGrid.setWidget(2, 4, hourbox);
		
		Label minute = new Label("Minute");
		spielzeitGrid.setWidget(2, 5, minute);
		spielzeitGrid.setWidget(2, 6, minutebox);
		

		spielzeitGrid.setWidget(4, 4, speichernButton);
		
		// Hinzufügen unserer Widgets zur Tabelle
		detailsPanel.add(spielzeitGrid);

		RootPanel.get("DetailsPanel").add(detailsPanel);
		this.add(detailsPanel);

		/*
		 * Formatierung des Datumformats in den deutschen Standard
		 */
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		datebox.setFormat(new DateBox.DefaultFormat(dateFormat));
		datebox.getDatePicker().setYearArrowsVisible(true);

		
	
	
		/*
		 * Uhrzeit fehllt hier noch..
		 */
		
		
		// Aufruf um CallbackObjekte KinoCallback und FilmCallback zu erhalten
		kinoAdministration.getKinosOfKinokette(kinokette, new Kinocallback());
		
		kinoAdministration.getAllKinos(new Kinocallback()); // Hier muss AllKinoofKinokette implmentiert werden.aktuell
		// nur test
		kinoAdministration.getAllFilme(new Filmcallback());

		// Hinzufügen des ClickHandlers zum Erstellen Button
		speichernButton.addClickHandler(new SpielzeitSpeichernClickHandler());
	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallBack und ermöglicht
	 * die Rückgabe der Kinos.
	 * 
	 */

	class Kinocallback implements AsyncCallback<Vector<Kino>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Kinos konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(Vector<Kino> result) {
			ClientSideSettings.getLogger().severe("Die Kinos wurden geladen.");
			for (Kino kino : result) {

				kinolistbox.addItem(kino.getKinoname());

			}

		}
	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallBack und ermöglicht
	 * die Rückgabe der Kinos.
	 * 
	 */

	class Filmcallback implements AsyncCallback<Vector<Film>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Filme konnten nicht geladen werden.");

		}

		@Override
		public void onSuccess(Vector<Film> result) {
			ClientSideSettings.getLogger().severe("Die Filme wurden geladen.");
			for (Film film : result) {

				filmlistbox.addItem(film.getFilmtitel());

			}
		}
	}

	private class SpielzeitSpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// Prüfen ob alle Anngaben ausgefüllt sind
			if (kinolistbox != null && filmlistbox != null && datebox != null) {

				kinoAdministration.getFilmByTitel(filmlistbox.getSelectedItemText(), new SelektierterFilmCallback());

			}
			// Falls Angaben gefehlt haben,wird folgendes ausgegeben:
			else {
				Window.alert("Bitte geben Sie alle Informationen an.");
				Window.alert("GWT");

			}
		}
		
		/**
		 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
		 * die Rückgabe eines Spielzeitobjekts.
		 * 
		 * @author ömer
		 */

		class SelektierterFilmCallback implements AsyncCallback<Film> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Film result) {
				ClientSideSettings.getLogger().severe("Der selektierte Film wurde gefunden.");
				kinoAdministration.getKinoByName(kinolistbox.getSelectedItemText(),
						new SelektiertesKinoCallBack(result));

			}
		}
		
		/**
		 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
		 * die Rückgabe eines Spielzeitobjekts.
		 * 
		 * @author ömer
		 */

		class SelektiertesKinoCallBack implements AsyncCallback<Kino> {

			Film film;

			SelektiertesKinoCallBack(Film film) {

				this.film = film;

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Kino result) {
				
				Date date = new Date();
				date = datebox.getValue();
				date.setHours(hourbox.getValue());
				date.setMinutes(minutebox.getValue());
				

				kinoAdministration.createSpielzeit(result.getSpielplanID(), film.getID(), date,
						new SpielzeitErstellenCallback());

			}

		}

	}
	
	/**
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht in
	 * Interaktion mit dem Nutzer: Wenn der Nutzer die Felder ausgfüllt hat, dann
	 * wird eine neue Spielzeit erstellt. Die Eingaben des Nutzers stellen die
	 * Argumente dar. Dann wird die Spielzeit in der Datenbank gespeichert.
	 * 
	 * @author ömer
	 */

	class SpielzeitErstellenCallback implements AsyncCallback<Spielzeit> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Spielzeit konnte nicht erstellt werden");

		}

		@Override
		public void onSuccess(Spielzeit result) {
			// Wir informieren den Nutzer über den positiven Ausgang
			Window.alert("Die Spielzeit wurde erstellt.");

		}

	}

}
