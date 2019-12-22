package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanung;
import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;


/**
 * Diese Klasse stellt eine Erweiterung des VerticalPanels dar und wird verwendet, um dem angemeldeten Nutzer die Möglichkeit
 * zu bieten Gruppen und ihre Mitglieder anzusehen, neue Gruppen zu erstellen
 * und zu bearbeiten
 * @author roland
 *
 */

public class GruppenverwaltungForm extends HorizontalPanel{

	/*
	 * ***************************************************************************
	 * ABSCHNITT: Hier wird der erste Teil der GruppenverwaltungForm implementiert.
	 * Hier werden alle Gruppen des angemeldeten Nutzers angezeigt. Der Nutzer hat
	 * hier die Möglichkeit durch Klicken alle Gruppen zu zeigen wo er angemeldet
	 * ist, eine neue Gruppe zu erstellen und zu editieren. Ein "Zurück" Button 
	 * ermöglicht hier auch zu den letzten besuchten Funktion zuzugreifen. 
	 * ***************************************************************************
	 */

	

	/**
	 * Attribute: GruppenverwaltungsForm Widgets
	 */


	private Gruppe gruppe = new Gruppe();
	private Label titel = new Label("Gruppenverwaltung");
	private Vector <Gruppe> gruppen = new Vector<Gruppe>();
	//private TabLayoutPanel panelGruppenverwaltung = new TabLayoutPanel();
	private VerticalPanel panelFürGruppen = new VerticalPanel();
	private PushButton neueGruppe = new PushButton("Neue Gruppe");
	private PushButton zurückButton = new PushButton("Zurück");
	private Nutzer nutzer;



	public void onLoad() {


		KinoBesuchsplanungAsync kinobesuchsplanung = ClientSideSettings.getKinobesuchsplanung();
		//kinobesuchsplanung.getAllGruppenOfNutzer(nutzer, new GruppenAnzeigenCallback());
		
		
		TabBar gruppenVerwaltungBar = new TabBar();
		gruppenVerwaltungBar.addTab("Anzeigen");
		gruppenVerwaltungBar.addTab("Erstellen");
		gruppenVerwaltungBar.addTab("Editieren");

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT: ClickHandlers...
	 * ***************************************************************************
	 */


	private class anlegenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {

			Window.alert("Gruppe erfolgreich angelegt");
		}

	}
}
