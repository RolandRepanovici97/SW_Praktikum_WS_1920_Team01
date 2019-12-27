/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.server.db.KinoMapper;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;

/**
 * Diese Klasse erweitert das VerticalPanel und wird verwendet, um Details einer Umfrage anzuzeigen.
 * Die Umgfrage wurde in einem vorherigen Schritt selektiert und kann nun detaillierter angesehen werden.
 * Von hier aus kann zur Bearbeitung der Umfrage oder zur Abstimmung navigiert werden. 
 * @author alina
 *
 */
public class UmfrageAnzeige extends VerticalPanel {

	//Benötigte Klassenvariable
	private Umfrage gewählteUmfrage;
	private String filmname;

	//Erzeugen der Widgets
	private Label umfragename;
	private Label beschreibung;
	private Button editierenButton = new Button("Editieren");
	private Button abstimmenButton = new Button("Abstimmen");
	private Button abtimmungenAnsehenButton =  new Button("Abstimmungen ansehen");
	private FlexTable einträge;
	private Label film;
	private Label datum;


	// Setzen der asynchronen Interfaces
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
	KinoAdministrationAsync kinoadministration = ClientSideSettings.getKinoAdministration();



	//Getter & Setter für die Variable Umfrage: Wird benötigt, wenn Umfrage aus Liste selektiert wird
	/**
	 * @return the gewählteUmfrage
	 */
	public Umfrage getGewählteUmfrage() {
		return gewählteUmfrage;
	}


	/**
	 * @param gewählteUmfrage the gewählteUmfrage to set
	 */
	public void setGewählteUmfrage(Umfrage gewählteUmfrage) {
		this.gewählteUmfrage = gewählteUmfrage;
	}




	/**
	 * onLoad() Methode: Hier wird das Umfrageobjekt übergeben!
	 * @param Umfrageobjekt, zu welchem uns die Details interessieren
	 * @author alina
	 */
	public void onLoad() {


		//Tag soll angezeigt werden
		datum = new Label("Geplanter Tag: \n" + gewählteUmfrage.getDatum().toString());


		//Film soll angezeigt werden
		int filmID = gewählteUmfrage.getFilmID();

		kinoadministration.getFilmByID(filmID, new FilmCallback());

		film = new Label("Film: \n" + filmname);




		//Wir stellen den Namen der Umfrage dar
		umfragename = new Label(gewählteUmfrage.getUmfragenname());
		umfragename.getElement().setId("Umfragename");

		//Wir stellen die Beschreibung der Umfrage dar
		beschreibung = new Label(gewählteUmfrage.getBeschreibung());

		einträge = new FlexTable();


		//Hier erhalten wir ein Callback Vector <Umfrageeintrag>, welcher alle Umfrageeinträge der gewählten Umfrage bereitstellt
		kinobesuchsplanung.showUmfrageeinträgeofUmfrage(gewählteUmfrage, new UmfrageeintragCallback());







		//Hinzufügen der Widgets zu unserem Panel
		this.add(umfragename);
		this.add(film);
		this.add(beschreibung);
		this.add(datum);
		this.add(einträge);


	}





	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	/** 
	 * Diese Nested-Class implementiert das Interface ClickHandler und wird benötigt,
	 * um die Interaktion des Nutzers mit dem "Editieren" Button zu gewährleisten. 
	 * Klickt er auf diesen Button, so wird er auf die Form zur Bearbeitung der Umfrage geleitet.
	 * 
	 * @author alina
	 *
	 */

	class EditierenClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			UmfrageEditierenForm editierenForm = new UmfrageEditierenForm();
			RootPanel.get("Details").add(editierenForm);		
		}

	}


	/** 
	 * Diese Nested-Class implementiert das Interface AsyncCallback und wird benötigt,
	 * um die Umfrageeinträge der gewählten Umfrage zurückzugeben.
	 * @author alina
	 *
	 */
	class UmfrageeintragCallback implements AsyncCallback <Vector<Umfrageeintrag>> {

		String spielzeitstring;
		String kinostring;

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Die Umfrageeinträge konnten nicht geladen werden.");
		}


		@Override
		public void onSuccess(Vector<Umfrageeintrag> result) {

			int rowCount = 0;

			for(Umfrageeintrag eintrag: result) {


				kinoadministration.getSpielzeitByID(eintrag.getSpielzeitID(), new SpielzeitCallback());
				kinoadministration.getKinoByID(eintrag.getKinoID(), new Kinocallback());

				einträge.setText(rowCount, 0, "Uhrzeit: \n " + spielzeitstring + "\n Kino: " + kinostring);

				rowCount++;



			}
		}

		class SpielzeitCallback implements AsyncCallback <Spielzeit>{

			@Override
			public void onFailure(Throwable caught) {

				/*
				 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
				 */
				ClientSideSettings.getLogger().severe("Die Spielzeit konnte nicht geladen werden.");


			}

			@Override
			public void onSuccess(Spielzeit result) {
				spielzeitstring = result.toString();


			}

		}

		class Kinocallback implements AsyncCallback <Kino>{

			@Override
			public void onFailure(Throwable caught) {
				/*
				 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
				 */
				ClientSideSettings.getLogger().severe("Das Kino konnte nicht geladen werden.");

			}

			@Override
			public void onSuccess(Kino result) {
				kinostring = result.getKinoname()+ "/n" + result.getAdresse();

			}


		}
	}
	class FilmCallback implements AsyncCallback <Film>{

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Der Film konnte nicht geladen werden.");

		}

		@Override
		public void onSuccess(Film result) {

			ClientSideSettings.getLogger().severe("Der Film wurde geladen");

			filmname= result.getFilmtitel();


		}

	}
}


