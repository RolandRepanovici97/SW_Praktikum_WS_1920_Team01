/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
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

	//	Nutzer nutzer = new Nutzer();

	// Erzeugen der einzelnen Widgets
	private Label umfragename = new Label("Umfragename: ");
	private TextBox umfragenametext;
	private Label gruppe = new Label("Gruppe: ");
	private ListBox gruppebox;
	private Label film = new Label("Film: ");
	private ListBox filmbox;
	private Label datum = new Label("Datum: ");
	private DatePicker datumwähler;
	private Button erstellenButton = new Button("Umfrage erstellen");
	private VerticalPanel panelfürumfrage;
	private Grid tabelle;

	public void onLoad() {
		super.onLoad();


		// Instanttierung der Widgets
		panelfürumfrage = new VerticalPanel();
		tabelle = new Grid(4,4);
		umfragenametext = new TextBox();
		gruppebox = new ListBox();
		filmbox = new ListBox();
		datumwähler = new DatePicker();

		// Aufruf um CallbackObjekte GruppeCallback und FilmCallback zu erhalten
		kinobesuchsplanung.getAllGruppen(new GruppeCallback());
		kinoadministration.getAllFilme(new FilmCallback());
		//
		//		// Hinzufügen des ClickHandlers zum Erstellen Button
		//		erstellenButton.addClickHandler(new ErstellenClickHandler());
		
		//Hinzufügen unserer Widgets zur Tabelle
		tabelle.setWidget(0,0, umfragename);
		tabelle.setWidget(0, 1, umfragenametext);
		tabelle.setWidget(1,1, gruppe);
		tabelle.setWidget(1,2, gruppebox);
		tabelle.setWidget(2, 1, film);
		tabelle.setWidget(2, 2, filmbox);
		tabelle.setWidget(3, 1, datum);
		tabelle.setWidget(3, 2, datumwähler);
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

			}}

	}
	//
	//	/**
	//	 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
	//	 * die Rückgabe der Gruppenobjekte eines Nutzers.
	//	 * 
	//	 * @author alina
	//	 */
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
		//
		//	}
		//
		//	/**
		//	 * Diese Nested Class implementiert das Interface AsyncCallback und ermöglicht
		//	 * die Rückgabe eines Umfrageobjekts.
		//	 * 
		//	 * @author alina
		//	 */
		//	class UmfrageCallback implements AsyncCallback<Umfrage> {
		//
		//		@Override
		//		public void onFailure(Throwable caught) {
		//			Window.alert("Die Umfrage konnte nicht erstellt werden");
		//
		//		}
		//
		//		@Override
		//		public void onSuccess(Umfrage result) {
		//
		//		}
		//
		//	}

		/**
		 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht in
		 * Interaktion mit dem Nutzer: Wenn der Nutzer die Felder ausgfüllt hat, dann
		 * wird eine neue Umfrage erstellt. Die Eingaben des Nutzers stellen die
		 * Argumente dar. Zuletzt wird die Umfrage in der Datenbank gespeichert.
		 * 
		 * @author alina
		 */
		//
		//	private class ErstellenClickHandler implements ClickHandler {
		//
		//		@Override
		//		public void onClick(ClickEvent event) {
		//			// Prüfung, ob alle Angaben gemacht wurden
		//			if (umfragenametext != null && gruppebox != null && filmbox != null && datumwähler != null) {
		//
		//				// Neues Objekt der Klasse Umfrage wird erstellt
		//				Umfrage umfrage = new Umfrage();
		//
		//				// Setzen der Attribute
		//				umfrage.setUmfragenname(umfragenametext.getText());
		//				umfrage.setDatum(datumwähler.getValue());
		//				umfrage.setOwnerID(nutzer.getID());
		//				// Aufruf des asynchronen Kinobesuchsplanung Interface
		//				KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
		//				// Aufruf der Methode createUmfrage: Hierdurch wird implizit das neue
		//				// Umfrageobjekt in der DB gespeichert
		//				kinobesuchsplanung.createUmfrage(umfragenametext.getText(), new UmfrageCallback());
		//				// Wir informieren den Nutzer über den positiven Ausgang
		//				Window.alert("Die Umfrage wurde erstellt. Die Teilnehmer wurden per E-Mail darüber informiert.");
		//
		//			}
		//			// Falls Angaben gefehlt haben, geben wir folgendes aus:
		//			else {
		//				Window.alert("Bitte geben Sie alle Informationen an.");
		//			}
		//
		//	}
	}
}
