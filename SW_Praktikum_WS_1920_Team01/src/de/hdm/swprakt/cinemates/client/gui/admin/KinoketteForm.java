package de.hdm.swprakt.cinemates.client.gui.admin;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.server.db.NutzerMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.client.gui.admin.KinoBearbeitenForm;

public class KinoketteForm extends HorizontalPanel {

	// Nur zum Test
	// Später ersetzen mit dem eingeloggten Benutzer

	private VerticalPanel verticalpanel = new VerticalPanel();

	private Kino kino;
	private Button neuesKino = new Button();
	private Label titel = new Label();

	Kinokette kinoketteEingeloggterBenutzer;

	FlexTable kinos = new FlexTable();

	KinoAdministrationAsync kinoAdministration = ClientSideSettings.getKinoAdministration();

	KinoverwaltungForm kf;
	SpielplanForm sf;

	SpielplanverwaltungForm spvf;

	KinoBearbeitenForm kbf;

	AlleKinosEinerKinokette akek;

	public void onLoad() {
		super.onLoad();

		neuesKino.setHTML("<i class=\"fas fa-plus\"></i>");
		kinos.addStyleName("flexTable");
		kinos.setCellPadding(10);
		titel.setText("Meine Kinokette");
		titel.getElement().setId("TitelElemente");
		neuesKino.addClickHandler(new kinoAnlegenClickHandler());

		// kinoAdministration.findNutzerByEmail("Kinobetreiber2@gmail.com", new
		// AsyncCallback<Nutzer>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// ClientSideSettings.getLogger().severe("Der eingeloggte Benutzer wurde nicht
		// gefunden");
		//
		// }
		//
		// @Override
		// public void onSuccess(Nutzer result) {
		// ClientSideSettings.getLogger().severe("Der eingeloggte Benutzer wurde
		// gefunden");
		// ClientSideSettings.getLogger().severe(result.getNutzername());
		// eingeloggterNutzer = result;
		//
		// }
		//
		// });
		//
		// kinoAdministration.getKinoketteOf(eingeloggterNutzer, new
		// AsyncCallback<Kinokette>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// ClientSideSettings.getLogger().severe("Die Kinokette des eingeloggten
		// Benutzers wurde nicht gefunden");
		//
		// }
		//
		// @Override
		// public void onSuccess(Kinokette result) {
		// ClientSideSettings.getLogger().severe("Die Kinokette des eingeloggten
		// Benutzers wurde gefunden");
		//
		// kinoketteEingeloggterBenutzer = result;
		//
		// }
		//
		// });
		//
		// kinoAdministration.getKinosOfKinokette(kinoketteEingeloggterBenutzer, new
		// AsyncCallback<Vector<Kino>>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// ClientSideSettings.getLogger().severe("Es wurden keine Kinos gefunden");
		//
		// }
		//
		// @Override
		// public void onSuccess(Vector<Kino> result) {
		// // Add the data to the data provider, which automatically pushes it to the
		// // widget.
		//
		// ClientSideSettings.getLogger().severe("Es wurden Kinos gefunden");
		// for (Kino kino : result) {
		// System.out.println(kino.toString());
		//
		// }
		// }
		//
		// });
		//
		//

		kinoAdministration.getAllKinos(new AsyncCallback<Vector<Kino>>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Es wurden keine Kinos in der getAllKinos() gefunden");

			}

			@Override
			public void onSuccess(Vector<Kino> result) {
				ClientSideSettings.getLogger().severe("Es wurden Kinos gefunden");

				int rowcount = 0;

				for (Kino kino : result) {

					Button spielplanBearbeiten = new Button("Spielplan bearbeiten");
					Button kinoBearbeiten = new Button("Kino bearbeiten");
					spielplanBearbeiten.addClickHandler(new spielplanBearbeitenClickHandler(kino));
					kinoBearbeiten.addClickHandler(new kinoBearbeitenClickHandler(kino));

					kinos.setText(rowcount, 0, kino.toString());

					kinos.setWidget(rowcount, 1, spielplanBearbeiten);
					kinos.setWidget(rowcount, 2, kinoBearbeiten);

					rowcount++;

				}
			}

		});

		// this.add(logo);
		// logo.addClickHandler(new kinoAnlegenClickHandler());

		/*
		 * Grid kinoketteGrid = new Grid(10, 10); kinoketteGrid.setWidget(0, 3,
		 * spielplanBearbeiten); spielplanBearbeiten.addClickHandler(new
		 * spielplanBearbeitenClickHandler()); kinoketteGrid.setText(0, 2,
		 * this.kinoName); kinoketteGrid.setWidget(0, 4, kinoBearbeiten);
		 * kinoBearbeiten.addClickHandler(new kinoBearbeitenClickHandler());
		 * kinoketteGrid.setWidget(0, 5, löschen); kinoketteGrid.setWidget(1, 7, logo);
		 * kinoketteGrid.setText(1, 8, "Neues Kino anlegen");
		 * löschen.addClickHandler(new löschenClickHandler()); this.add(kinoketteGrid);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * RootPanel.get("DetailsPanel").add(kinoketteGrid);
		 */
		verticalpanel.add(titel);
		verticalpanel.add(kinos);
		verticalpanel.add(neuesKino);
		this.add(verticalpanel);
		// RootPanel.get("DetailsPanel").add(kinos);

	}

	/*
	 * Click handlers und abhängige AsyncCallback Klassen.
	 */

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallback und das Löschen
	 * eines Kinos.
	 * 
	 */
	class BearbeitenCallback implements AsyncCallback<Kino> {

		@Override
		public void onFailure(Throwable caught) {

			ClientSideSettings.getLogger().severe("Es konnten keine Kinos b werden");
		}

		@Override
		public void onSuccess(Kino result) {
			RootPanel.get("DetailsPanel").clear();
			KinoBearbeitenForm kbf = new KinoBearbeitenForm(kino);
			RootPanel.get("DetailsPanel").add(kbf);

		}

	}

	private class kinoBearbeitenClickHandler implements ClickHandler {

		Kino übergabekino;

		kinoBearbeitenClickHandler(Kino kino) {
			this.übergabekino = kino;
		}

		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			KinoBearbeitenForm kbf = new KinoBearbeitenForm(übergabekino);
			RootPanel.get("DetailsPanel").add(kbf);
		}
	}

	private class spielplanBearbeitenClickHandler implements ClickHandler {

		Kino übergabekino;

		spielplanBearbeitenClickHandler(Kino kino) {
			this.übergabekino = kino;
		}

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			spvf = new SpielplanverwaltungForm(übergabekino);
			RootPanel.get("DetailsPanel").add(spvf);

		}

	}

	private class kinoAnlegenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			RootPanel.get("DetailsPanel").clear();
			KinoAnlegenForm kf = new KinoAnlegenForm();
			Window.Location.getParameter("");
			RootPanel.get("DetailsPanel").add(kf);
		}

	}



}







