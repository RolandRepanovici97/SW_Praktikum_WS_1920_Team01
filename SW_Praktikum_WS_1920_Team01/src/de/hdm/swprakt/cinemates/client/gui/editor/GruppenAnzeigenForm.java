package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.client.gui.editor.StartseiteEditor.UmfragePanel;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;


/**
 * Diese Klasse erweitert das VerticalPanel und stellt den Body der GruppenVerwaltungForm "Anzeigen" Tab dar.
 * @author roland
 */

public class GruppenAnzeigenForm extends VerticalPanel {

	/**
	 **************************************************************************************
	 *ABSCHNITT: Hier werden alle Gruppen des angemeldeten Users angezeigt. Er hat hier 
	 *die Möglichkeit,durch Klicken, auf die Gruppeobjekte, diese anzeigen zu lassen
	 **************************************************************************************
	 */




	private Label titel = new Label("Meine Gruppen");
	private Button neueGruppe = new Button("Neue Gruppe");
	FlowPanel panel;



	KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();


	public void onLoad() {
		super.onLoad();

		neueGruppe.setHTML("<i class=\"fas fa-plus\"></i>");
		titel.getElement().setId("TitelElemente");

		panel = new FlowPanel();

		neueGruppe.addClickHandler(new NeueGruppeClickHandler());


		kinobesuchsplanung.getAllGruppen(new GruppenAnzeigenCallback());

		//  kinobesuchsplanung.getAllGruppenOfNutzernutzer(new GruppenAnzeigenCallback());

		this.add(titel);
		this.add(panel);
		this.add(neueGruppe);


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

			for (Gruppe gruppe : result) {
				panel.add(new GruppePanel(gruppe));

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
class NeueGruppeClickHandler implements ClickHandler {

	public void onClick(ClickEvent event) {

		RootPanel.get("DetailsPanel").clear();
		GruppeErstellenForm neueGruppe = new GruppeErstellenForm();
		RootPanel.get("DetailsPanel").add(neueGruppe);
	}

}

/**
 * Diese Nested Class wird zur Repräsentation der Gruppe benötigt benötigt.
 * 
 * @author alina
 */
class GruppePanel extends VerticalPanel{
	Gruppe gruppe;
	HorizontalPanel horizontalPanel;
	
	// Die Klasse benötigt die zurückgegebene Gruppe

	public GruppePanel(Gruppe gruppe) {
		this.gruppe= gruppe;

	}

	public void onLoad() {
		super.onLoad();

		// Hinzufügen des Styles
		this.addStyleName("GruppePanel");

		horizontalPanel = new HorizontalPanel();
		Label gruppenname = new Label();
		// Setzen des Umfragenamens
		gruppenname.setText(gruppe.getGruppenname());

		// Erzeugen der Buttons
		Button gruppeAnzeigen = new Button("Anzeigen");
		Button gruppeEditieren = new Button("Editieren");

		// Klickt der Nutzer auf "Anzeigen" gelangt er zu den Details der gewählten
		// Gruppe
		gruppeAnzeigen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("DetailsPanel").clear();
				GruppeDetailsForm anzeige = new GruppeDetailsForm();
				anzeige.setGewählteGruppe(gruppe);
				RootPanel.get("DetailsPanel").add(anzeige);
			}

		});
		gruppeEditieren.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("DetailsPanel").clear();
				GruppeEditierenForm editiere = new GruppeEditierenForm();
				editiere.setGewählteGruppe(gruppe);
				RootPanel.get("DetailsPanel").add(editiere);

			}

		});

		// Hinzufügen der Widgets zum Panel
		this.add(gruppenname);
		horizontalPanel.add(gruppeAnzeigen);
		horizontalPanel.add(gruppeEditieren);
		this.add(horizontalPanel);
	}
}


