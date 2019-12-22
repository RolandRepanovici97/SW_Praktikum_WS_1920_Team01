/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.server.db.KinoMapper;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
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

	//Erzeugen der Widgets
	private Label titel;
	private Label beschreibung;
	private Button editierenButton = new Button("Editieren");
	private Button abstimmenButton = new Button("Abstimmen");
	private Button abtimmungenAnsehenButton =  new Button("Abstimmungen ansehen");
	private CellTable einträge;

	//In diesem Vector werden die Umfrageeinträge der gewählten Umfrage festgehalten
	private Vector <Umfrageeintrag> einträgevector = new Vector <Umfrageeintrag>();


	/**
	 * onLoad() Methode: Hier wird das Umfrageobjekt übergeben!
	 * @param Umfrageobjekt, zu welchem uns die Details interessieren
	 * @author alina
	 */
	public void onLoad(Umfrage umfrage) {

		//Wir stellen den Namen der Umfrage dar
		titel = new Label(umfrage.getUmfragenname());

		//Wir stellen die BEschreibung der Umfrage dar
		beschreibung = new Label(umfrage.getBeschreibung());

		einträge = new CellTable();
		//Hier erhalten wir das asynchrone Kinobesuchsplanung- Interface zurück 
		KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();

		//Hier erhalten wir ein Callback Vector <Umfrageeintrag>, welcher alle Umfrageeinträge der gewählten Umfrage bereitstellt
		kinobesuchsplanung.showUmfrageeinträgeofUmfrage(umfrage, new UmfrageeintragCallback());

		for(Umfrageeintrag eintrag: einträgevector) {
	
			
		}

	}


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

	@Override
	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
		 */
		ClientSideSettings.getLogger().severe("Die Umfrageeinträge konnten nicht geladen werden.");
	}


	@Override
	public void onSuccess(Vector<Umfrageeintrag> result) {


	}
}



