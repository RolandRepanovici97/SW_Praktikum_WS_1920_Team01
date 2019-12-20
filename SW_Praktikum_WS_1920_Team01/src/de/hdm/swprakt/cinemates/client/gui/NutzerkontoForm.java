
package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Diese Klasse stellt eine Erweiterung des VerticalPanels dar und wird verwendet, um dem angemeldeten Nutzer die Möglichkeit zu bieten,
 * sein Nutzerkonto anzusehen, sowie seinen Nutzernamen zu bearbeiten.
 * @author alina
 *
 */
public class NutzerkontoForm extends VerticalPanel {

	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();


	//Erzeugen der einzelnen Widgets

	private Nutzer nutzer = new Nutzer();
	private Label titel = new Label("Mein Nutzerkonto");
	private Label nutzernamelabel = new Label("Nutzername:");
	private TextBox nutzernametext;
	private Label emaillabel = new Label("E-Mail");
	private Label emailtext;
	private Button speichernButton;


	public void onLoad() {


		super.onLoad();


		nutzernametext = new TextBox();
		emailtext = new Label(nutzer.getEmail());
		speichernButton = new Button("Speichern");

		speichernButton.addClickHandler(new SpeichernClickHandler());

	}



	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */



	/**
	 * Diese Nested Class implementiert das Interface ClickHandler und ermöglicht die
	 * Interaktion mit dem Nutzer zur Aktualisierung seines Nutzerkontos.
	 * @author alina
	 */

	class SpeichernClickHandler implements ClickHandler{


		@Override
		public void onClick(ClickEvent event) {
			//Wenn die Textbox zum Nutzernamen befüllt ist, dann...

			if(nutzernametext!= null) {
				//Update des Nutzerobjekts. Implizit wird das Nutzerobjekt in der Datenbank aktualisiert.
				kinobesuchsplanung.save(nutzer, new NutzerCallback());

			}

			//Andernfalls hat der Nutzers nichts geändert...
			else {
				Window.alert("Sie haben keine Änderungen ausgeführt");
			}

		}

	}

	/**
	 * Diese Nested Class implementiert das Interface AsyncCallBack und ermöglicht die Rückgabe des Nutzerobjekts.
	 * @author alina
	 */
	class NutzerCallback implements AsyncCallback <Void>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihr Nutzerkonto konnte nicht aktualisiert werden");

		}


		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub

		}

	}

}
