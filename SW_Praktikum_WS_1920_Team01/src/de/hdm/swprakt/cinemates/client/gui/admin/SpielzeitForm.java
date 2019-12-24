package de.hdm.swprakt.cinemates.client.gui.admin;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;

public class SpielzeitForm extends HorizontalPanel {

	ListBox kinolistbox = new ListBox();
	TextBox filmm = new TextBox();
	DatePicker datePicker = new DatePicker();
	Button speichern = new Button("Speichern");
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
		spielzeitGrid.setWidget(1, 1, film);
		spielzeitGrid.setWidget(1, 0, film);

		Label spielzeit = new Label("Datum und Uhrzeit");
		spielzeitGrid.setWidget(1, 2, spielzeit);
		spielzeitGrid.setWidget(1, 3, datePicker);

		spielzeitGrid.setWidget(2, 2, speichern);

		detailsPanel.add(spielzeitGrid);

		RootPanel.get("DetailsPanel").add(detailsPanel);
		this.add(detailsPanel);

		kinoAdministration.getAllKinoOfKinokette(kinokette, new Kinocallback());

	}

	class Kinocallback implements AsyncCallback<Vector<Kino>> {

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(Vector<Kino> result) {
			for (Kino kino : result) {

			}

		}
	}
}