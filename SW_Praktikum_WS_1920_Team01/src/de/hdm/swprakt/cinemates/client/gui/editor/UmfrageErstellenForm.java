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
public class UmfrageErstellenForm extends VerticalPanel {

	Nutzer nutzer = new Nutzer();

	// Erzeugen der einzelnen Widgets
	private Label umfragename = new Label("Umfragename: ");
	private TextBox umfragenametext;
	private ListBox gruppebox;
	private ListBox filmbox;
	private DatePicker datumwähler;
	private Button erstellenButton = new Button("Umfrage erstellen");

	public void onLoad() {

		// Setzen der asynchronen Interfaces
		KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
		KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();

		// Instanttierung der Widgets
		umfragenametext = new TextBox();
		gruppebox = new ListBox();
		filmbox = new ListBox();
		datumwähler = new DatePicker();

		// Aufruf um CallbackObjekte GruppeCallback und FilmCallback zu erhalten
		kinobesuchsplanung.getAllGruppenOfNutzer(nutzer, new GruppeCallback());
		kinoadministration.getAllFilme(new FilmCallback());

		// Hinzufügen des ClickHandlers zum Erstellen Button
		erstellenButton.addClickHandler(new ErstellenClickHandler());
		
		this.add(umfragename);

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

		@Override
		public void onClick(ClickEvent event) {
			// Prüfung, ob alle Angaben gemacht wurden
			if (umfragenametext != null && gruppebox != null && filmbox != null && datumwähler != null) {

				// Neues Objekt der Klasse Umfrage wird erstellt
				Umfrage umfrage = new Umfrage();

				// Setzen der Attribute
				umfrage.setUmfragenname(umfragenametext.getText());
				umfrage.setDatum(datumwähler.getValue());
				umfrage.setOwnerID(nutzer.getID());
				// Aufruf des asynchronen Kinobesuchsplanung Interface
				KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
				// Aufruf der Methode createUmfrage: Hierdurch wird implizit das neue
				// Umfrageobjekt in der DB gespeichert
				kinobesuchsplanung.createUmfrage(umfragenametext.getText(), new UmfrageCallback());
				// Wir informieren den Nutzer über den positiven Ausgang
				Window.alert("Die Umfrage wurde erstellt. Die Teilnehmer wurden per E-Mail darüber informiert.");

			}
			// Falls Angaben gefehlt haben, geben wir folgendes aus:
			else {
				Window.alert("Bitte geben Sie alle Informationen an.");
			}

		}
	}

}
