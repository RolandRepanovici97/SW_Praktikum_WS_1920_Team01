package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.gui.admin.FilmForm.AlleFilmeCallback;
import de.hdm.swprakt.cinemates.client.gui.admin.FilmForm.FilmAnlegenCallback;
import de.hdm.swprakt.cinemates.client.gui.admin.FilmForm.FilmAnlegenClickHandler;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;

public class FilmCellForm extends VerticalPanel {

	// Erzeugen der Widgets
	TextBox filmname = new TextBox();
	TextBox filmbeschreibung = new TextBox();
	TextBox spiellange = new TextBox();
	Button filmanlegen = new Button("Film anlegen");

	VerticalPanel detailsPanel = new VerticalPanel();

	/**
	 * A simple data type that represents a contact.
	 */
	private static class Film1 {
		private final String name;
		private final String beschreibung;
		private final String details;

		public Film1(String name, String beschreibung, String details) {
			this.name = name;
			this.beschreibung = beschreibung;
			this.details = details;
		}
	}

	/**
	 * The list of data to display.
	 */
	private static final List<Film1> FILMS = Arrays.asList(new Film1("John", "Hallo", "123 Fourth Avenue"),
			new Film1("Joe", "Test", "22 Lance Ln"), new Film1("George", "HAashahh", "1600 Pennsylvania Avenue"));

	// Ein ListDataProvider wird erstellt um die Daten auszugeebn
	private ListDataProvider<Film1> dataProvider = new ListDataProvider<Film1>();

	// Setzen der asynchronen Interfaces
	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	// Die Liste der Daten anzeigen
//	private static List<Film> FILMS = Arrays.asList();

	/**
	 * The list of data to display.
	 */
	private static final List<Film1> FILMS1 = Arrays.asList(new Film1("John", "sxad", "sd"));

	/**
	 * onload()-Methode
	 */
	/**
	 *
	 */
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
		// filmGrid.setWidget(2, 2, filmanlegen);

		filmGrid.setWidget(3, 2, filmanlegen);

		detailsPanel.add(filmGrid);

		this.add(detailsPanel);

		kinoAdministration.getAllFilme(new AlleFilmeCallback());

		CellTable<Film1> table = new CellTable<Film1>(100);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		dataProvider.addDataDisplay(table);

		// Add a text column to show the name.
		TextColumn<Film1> nameColumn = new TextColumn<Film1>() {
			@Override
			public String getValue(Film1 film) {
				return film.name;
			}
		};
		table.addColumn(nameColumn, "Film");

		// Add a text column to show the name.
		TextColumn<Film1> beschreibungColumn = new TextColumn<Film1>() {
			@Override
			public String getValue(Film1 film) {
				return film.beschreibung;
			}
		};
		table.addColumn(beschreibungColumn, "Beschreibung");

		// Add a text column to show the name.
		TextColumn<Film1> spiellangeColumn = new TextColumn<Film1>() {
			@Override
			public String getValue(Film1 film) {
				return film.details;
			}
		};
		table.addColumn(spiellangeColumn, "Beschreibung");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Film1> selectionModel = new SingleSelectionModel<Film1>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Film1 selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected.name);
				}
			}
		});
		// Set the total row count. This isn't strictly necessary, but it affects
		// paging calculations, so its good habit to keep the row count up to date.
		table.setRowCount(FILMS.size(), true);

		// Push the data into the widget.
		table.setRowData(0, FILMS);

		this.add(table);

		filmanlegen.addClickHandler(new FilmAnlegenClickHandler());

	}

	class FilmAnlegenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			kinoAdministration.createFilm(filmname.getText(), filmbeschreibung.getText(), spiellange.getText(),
					new FilmAnlegenCallback());
			// addRow(flexTable);

		}
	}

	class FilmAnlegenCallback implements AsyncCallback<Film> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Film konnte nicht erstellt werden");
		}

		@Override
		public void onSuccess(Film result) {

			Window.alert("Der Film wurde erstellt");

			kinoAdministration.getAllFilme(new AlleFilmeCallback());

		}

	}

	class AlleFilmeCallback implements AsyncCallback<Vector<Film>> {

		@Override
		public void onFailure(Throwable result) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Vector<Film> result) {
			Film1 filmzelle = null;
			for (Film f : result) {

				filmzelle = new Film1(f.getFilmtitel(), f.getBeschreibung(), f.getDetails());
				List<Film1> list = dataProvider.getList();
				list.add(filmzelle);
			}

		}

	}
}
