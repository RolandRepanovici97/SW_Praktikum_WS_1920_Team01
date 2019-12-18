/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.KinobesuchsplanungEntry.AktuellerNutzer;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;

/**
 * Diese Klasse erweitert das HorizontalPanel und stellt den Body der Seite dar. Diese Klasse dient der
 * Navigation der Applikation. Von hier aus kann zu den Umfragen, zur Abstimmung und zur Umfragenerstellung navigiert werden.
 * 
 * @author alina
 *
 */
public class StartseiteEditor extends HorizontalPanel {

	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der erste Teil der Startseite des Editors 
	 * implementiert. Hier werden alle Umfragen des angemeldeten Nutzers
	 * angezeigt. Er hat hier die Möglichkeit, durch Klicken auf die 
	 * Umfrageobjekte, diese anzeigen zu lassen. 
	 * ***************************************************************************
	 */


	private Label label1 = new Label("Meine Umfragen");
	private Label label2 = new Label("Neue Umfragen");


	protected void run() { 

		AktuellerNutzer nutzer = new AktuellerNutzer();

		KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
		kinobesuchsplanung.showAllUmfrageOfNutzer(nutzer, new UmfragenAnzeigenCallback());
		kinobesuchsplanung.showAllUmfrageOfNutzerOhneErgebnis(nutzer, new OffeneUmfragenAnzeigenCallback());

		this.add(label1);
		this.add(label2);



	}

}




/*
 * ***************************************************************************
 * ABSCHNITT: Hier wird der erste Teil der Startseite des Editors 
 * implementiert. Hier werden alle Umfragen des angemeldeten Nutzers
 * angezeigt. Er hat hier die Möglichkeit, durch Klicken auf die 
 * Umfrageobjekte, diese anzeigen zu lassen. 
 * ***************************************************************************
 */





/*
 * ***************************************************************************
 * ABSCHNITT Nested Classes
 * ***************************************************************************
 */


/**
 * Diese Nested Class wird als Callback für das Anzeigen der Umfrageobjekte benötigt.
 * 
 * @author alina
 */
class UmfragenAnzeigenCallback implements AsyncCallback<Vector<Umfrage>> {

	@Override
	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
		 */
		ClientSideSettings.getLogger().severe("Ihre Umfragen konnten nicht geladen werden");
	}


	@Override
	public void onSuccess(Vector<Umfrage> result) {
		// TODO Auto-generated method stub

	}


}

/**
 * Diese Nested Class wird als Callback für das Anzeigen neuer Umfrageobjekte benötigt.
 * 
 * @author alina
 */



class OffeneUmfragenAnzeigenCallback implements AsyncCallback<Vector<Umfrage>> {

	@Override
	public void onFailure(Throwable caught) {
		/*
		 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
		 */
		ClientSideSettings.getLogger().severe("Ihre neuen Umfragen konnten nicht geladen werden");
	}


	@Override
	public void onSuccess(Vector<Umfrage> result) {
		// TODO Auto-generated method stub

	}
}

