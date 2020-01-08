package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;

import de.hdm.swprakt.cinemates.shared.KinoAdministration;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;

/**
 * Die Klasse erweitert das HorizontalPanel.Die Klasse wird benötigt um neue
 * Film zu erstellen und diese dann weiter unten als Tabelle auszugeben. Hier
 * werden alle Filme aus Datenbank angezeigt.
 * 
 * @author oemer
 *
 */

public class FilmForm extends HorizontalPanel {

	// Erzeugen der Widgets
	TextBox filmname = new TextBox();
	TextBox filmbeschreibung = new TextBox();
	TextBox spiellange = new TextBox();
	Button filmanlegen = new Button("Film anlegen");
	private Button ja = new Button("JA");
	private Button nein = new Button("NEIN");
	private FlexTable flexTable;

	VerticalPanel detailsPanel = new VerticalPanel();

	// Ein ListDataProvider wird erstellt um die Daten auszugeebn
	private ListDataProvider<Film> dataProvider = new ListDataProvider<Film>();

	// Setzen der asynchronen Interfaces
	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	// bisher leerer Konstruktor 
	public FilmForm() {

	}

	// Die Liste der Daten anzeigen
	// private static List<Film> FILMS = Arrays.asList();

	/**
	 * onload()-Methode
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
		RootPanel.get("DetailsPanel").add(detailsPanel);
		this.add(detailsPanel);

		flexTable = new FlexTable();

		flexTable.setText(0, 1, "Film");
		flexTable.setText(0, 2, "Beschreibung");
		flexTable.setText(0, 3, "Spiellänge");

		filmanlegen.addClickHandler(new FilmAnlegenClickHandler());
		// dataProvider.addDataDisplay(flexTable);
		detailsPanel.add(flexTable);

		//		Button addRowButton = new Button("Add a Row"); 
		//      addRowButton.addClickHandler(new ClickHandler() {
		//	         @Override
		//         public void onClick(ClickEvent event) {
		//	            addRow(flexTable);
		//         }
		//	      });
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
			//
			//			filmname.setText("");
			//			filmbeschreibung.setText("");
			//			spiellange.setText("");
			//
			//			flexTable.setText(1, 1, result.getFilmtitel());
			//			flexTable.setText(1, 2, result.getBeschreibung());
			//			flexTable.setText(1, 3, result.getDetails());


		}

	}


	class AlleFilmeCallback implements AsyncCallback<Vector<Film>> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Der Film konnte nicht .....");
		}

		@Override
		public void onSuccess(Vector<Film> result) {
			// TODO Auto-generated method stub
			Window.alert("Die Filme.... ");
			int rowCount = 1;
			for (Film f: result) {

				flexTable.setText(rowCount, 1, f.getFilmtitel());
				flexTable.setText(rowCount, 2, f.getBeschreibung());
				flexTable.setText(rowCount, 3, f.getDetails());
				rowCount++;


			}
		}

	}
}
//class AlleFilmeClickHandler implements ClickHandler {
//	public void onClick(ClickEvent event) {
//
//		kinoAdministration.getAllFilme(new AlleFilmeCallback());
//
//	}
//}

//class FilmLöschenClickHandler extends DialogBox implements ClickHandler {
//
//	public FilmLöschenClickHandler() {
//		setText("Möchten Sie das Kino wirklich löschen?");
//		Grid jaNein = new Grid(3, 3);
//		jaNein.setWidget(0, 1, ja);
//		jaNein.setWidget(0, 2, nein);
//		nein.addClickHandler(new neinClickHandler());
//		setAnimationEnabled(false);
//		setGlassEnabled(false);
//		this.add(jaNein);
//	}
//
//	public void onClick(ClickEvent event) {
//		new FilmLöschenClickHandler().show();
//	}
//
//	private class neinClickHandler implements ClickHandler {
//		@Override
//		public void onClick(ClickEvent event) {
//			RootPanel.get().clear();
//		}
//	}
//}
//
////	
////		/**
////		  * Add a row to the flex table.
////		 */
////	private void addRow(FlexTable flexTable) {
//
//int numRows = flexTable.getRowCount();
//flexTable.setWidget(numRows, 1, new HTML(filmname.getText()));
//flexTable.setWidget(numRows, 2, new HTML(filmbeschreibung.getText()));
//flexTable.setWidget(numRows, 3, new HTML(spiellange.getText()));
////		flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows +1);
//
//
////		     
//
//}}
