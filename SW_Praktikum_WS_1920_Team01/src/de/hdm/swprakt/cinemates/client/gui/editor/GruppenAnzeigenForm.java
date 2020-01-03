package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;


/**
 * Diese Klasse erweitert das VerticalPanel und stellt den Body der GruppenVerwaltungForm "Anzeigen" Tab dar.
 * @author roland
 */

public class GruppenAnzeigenForm extends HorizontalPanel {

	/**
	 **************************************************************************************
	 *ABSCHNITT: Hier werden alle Gruppen des angemeldeten Users angezeigt. Er hat hier 
	 *die Möglichkeit,durch Klicken, auf die Gruppeobjekte, diese anzeigen zu lassen
	 **************************************************************************************
	 */




	private Label titel = new Label("Meine Gruppenverwaltung");
	private Button neueGruppe = new Button("Neue Gruppe");
	
	@SuppressWarnings("unused")
	private Button zurückButton = new Button ("Zurück");

	private FlexTable tabellefürgruppen;
	private VerticalPanel panelfürgruppen = new VerticalPanel ();
	
	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();


	public void onLoad() {
		super.onLoad();

		neueGruppe.setHTML("<i class=\"fas fa-plus\"></i>");
		titel.getElement().setId("TitelElem:");

		//neueGruppe.addClickHandler(new NeueGruppeClickHandler());

		tabellefürgruppen = new FlexTable();

		kinobesuchsplanung.getAllGruppen(new GruppenAnzeigenCallback());

		//  kinobesuchsplanung.getAllGruppenOfNutzernutzer(new GruppenAnzeigenCallback());


		panelfürgruppen.add(titel);
		panelfürgruppen.add(tabellefürgruppen);
		panelfürgruppen.add(neueGruppe);
		this.add(panelfürgruppen);


	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der erste Teil der GruppenVerwaltungSeite implementiert.
	 * Hier werden alle Gruppen des angemeldeten Nutzers angezeigt. Er hat hier die
	 * Möglichkeit, durch Klicken auf die Gruppenobjekte, diese anzeigen zu lassen.
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT Nested Classes
	 * ***************************************************************************
	 */

	/**
	 * Diese Nested Class wird als Callback für das Anzeigen der Gruppeobjekte
	 * benötigt.
	 * 
	 * @author roland
	 */

	class GruppenAnzeigenCallback implements AsyncCallback<Vector<Gruppe>> {

		@Override
		public void onFailure(Throwable caught) {
			/*
			 * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
			 */
			ClientSideSettings.getLogger().severe("Ihre Gruppen konnten nicht geladen werden");
		}

		@Override
		public void onSuccess(Vector<Gruppe> result) {

			ClientSideSettings.getLogger().severe("Ihre Gruppen wurden geladen. ");

			int rowCount = 0;

			for (Gruppe g : result) {
				
				tabellefürgruppen.setText(rowCount, 0, g.getGruppenname());
				Button gruppeEditieren = new Button("Editieren");
				gruppeEditieren.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("DetailsPanel").clear();
						GruppeEditierenForm editiere = new GruppeEditierenForm();
						editiere.setGewählteGruppe(g);
						RootPanel.get("DetailsPanel").add(editiere);

					}

				});
				tabellefürgruppen.setWidget(rowCount, 1, gruppeEditieren);
				rowCount++;
			}


		}

	}

}

/**
 * Dieses Nested Class implementiert das Interface ClickHandler und ermöglicht die Interaktion
 * zur Weiterletiung auf die Möglichkeit zur Erstellung einer neue Gruppe
 * 
 * @author roland
 */
//class NeueGruppeClickHandler implements ClickHandler {
//
//	public void onClick(ClickEvent event) {
//
//		RootPanel.get("DetailsPanel").clear();
//		GruppeErstellenForm neueGruppe = new GruppeErstellenForm();
//		RootPanel.get("DetailsPanel").add(neueGruppe);
//	}
//}



