package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
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

/**
 * Diese Klasse erweitert das HorizontalPanel und wird benötigt, um eine neue
 * Spielzeit anzulegen.
 * 
 * @author ömer
 *
 */
public class SpielzeitForm extends HorizontalPanel {

	ListBox kinolistbox = new ListBox();
	ListBox filmlistbox = new ListBox();
	Label datum = new Label("Datum und Uhrzeit");
	DateBox datebox = new DateBox();
	Button speichern = new Button("Spielzeit speichern");
	Kinokette kinokette = new Kinokette();

	VerticalPanel detailsPanel = new VerticalPanel();

	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	public SpielzeitForm() {

	}

	public void onLoad() {

		super.onLoad();

		Grid spielzeitGrid = new Grid(4, 4);

		Label name = new Label("Kino");
		spielzeitGrid.setWidget(0, 1, kinolistbox);
		spielzeitGrid.setWidget(0, 0, name);

		Label film = new Label("Film");
		spielzeitGrid.setWidget(1, 1, filmlistbox);
		spielzeitGrid.setWidget(1, 0, film);

		Label spielzeit = new Label("Datum und Uhrzeit");
		spielzeitGrid.setWidget(1, 2, spielzeit);
		spielzeitGrid.setWidget(1, 3, datebox);

		spielzeitGrid.setWidget(2, 2, speichern);

		detailsPanel.add(spielzeitGrid);

		RootPanel.get("DetailsPanel").add(detailsPanel);
		this.add(detailsPanel);

		kinoAdministration.getAllKinoOfKinokette(kinokette, new Kinocallback());
		// Aufruf um CallbackObjekte KinoCallback und FilmCallback zu erhalten
		kinoAdministration.getAllKinos(new Kinocallback());   //Hier muss AllKinoofKinokette implmentiert werden.aktuell nur test
		kinoAdministration.getAllFilme(new Filmcallback());	
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
}