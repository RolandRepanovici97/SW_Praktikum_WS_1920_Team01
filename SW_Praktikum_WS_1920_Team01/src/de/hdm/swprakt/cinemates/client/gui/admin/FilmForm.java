package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;

public class FilmForm extends HorizontalPanel {

	TextBox filmname = new TextBox();
	TextBox filmbeschreibung = new TextBox();
	TextBox spiellange = new TextBox();
	Button filmanlegen = new Button("Film anlegen");

	HorizontalPanel detailsPanel = new HorizontalPanel();

	// Create a data provider.
	private ListDataProvider<Film> dataProvider = new ListDataProvider<Film>();

	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	public FilmForm() {

	}

	public void onLoad() {

		super.onLoad();

		Grid filmGrid = new Grid(4, 4);

		Label name = new Label("Filmname");
		filmGrid.setWidget(0, 1, filmname);
		filmGrid.setWidget(0, 0, name);

		Label beschreibung = new Label("Filmbeschreibung");
		filmGrid.setWidget(1, 1, filmbeschreibung);
		filmGrid.setWidget(1, 0, beschreibung);

		Label spiellänge = new Label("Spiellänge");
		filmGrid.setWidget(2, 1, spiellange);
		filmGrid.setWidget(2, 0, spiellänge);
		filmGrid.setWidget(2, 2, filmanlegen);

		filmGrid.setWidget(3, 2, filmanlegen);
		

		detailsPanel.add(filmGrid);
		RootPanel.get("DetailsPanel").add(detailsPanel);
		this.add(detailsPanel);

		class FilmAnlegenCallback implements AsyncCallback<Film> {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der Film konnte nicht erstellt werden");
			}

			@Override
			public void onSuccess(Film result) {
				// TODO Auto-generated method stub

			}

		}

		class FilmAnlegenClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
			
				kinoAdministration.createFilm(filmname.getText(), filmbeschreibung.getText(), spiellange.getText(), new FilmAnlegenCallback());
			}
		}
		filmanlegen.addClickHandler(new FilmAnlegenClickHandler());
	
	}
	
}
