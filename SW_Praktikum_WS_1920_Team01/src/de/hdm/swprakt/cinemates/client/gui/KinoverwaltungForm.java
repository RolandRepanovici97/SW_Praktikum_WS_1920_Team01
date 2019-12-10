package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.swprakt.cinemates.client.ClientSideSettings;
import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Kino;

public class KinoverwaltungForm extends VerticalPanel{

/*
 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
 */
	
	TextBox kinoname = new TextBox();
	TextBox kinobeschreibung = new TextBox();
	TextBox kinoadresse = new TextBox();
	ListBox spielplanbox = new ListBox();
	Button anlegen = new Button("Kino anlegen");
	
	// Create a data provider.
		private ListDataProvider<Kino> dataProvider = new ListDataProvider<Kino>();
	
	VerticalPanel detailsPanel = new VerticalPanel();
	
	KinoAdministrationAsync kaa = ClientSideSettings.getkinoAdministration();
	
	
	public KinoverwaltungForm() {
		
	}
/*
 * Beim Anzeigen werden die Widgets erzeugt. Alle werden in einem
 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
 * Widgets bestimmt.
 */	
	
	public void onLoad() {
		
		super.onLoad();
		
		Grid kinoGrid = new Grid(4, 4);
		
		
		Label name = new Label("Kinoname");
		kinoGrid.setWidget(0, 1, kinoname);
		kinoGrid.setWidget(0, 0, name);
		
		Label beschreibung = new Label("Kinobeschreibung");
		kinoGrid.setWidget(1, 1, kinobeschreibung);
		kinoGrid.setWidget(1, 0, beschreibung);
		
		Label adresse = new Label("Adresse");
		kinoGrid.setWidget(2, 1, kinoadresse);
		kinoGrid.setWidget(2, 0, adresse);
		
		Label spielplan = new Label("Spielplan");
		kinoGrid.setWidget(3, 1, spielplanbox); //Spielplanform noch nicht fertig
		kinoGrid.setWidget(3, 0, spielplan);
		kinoGrid.setWidget(3, 2, anlegen);
		anlegen.addClickHandler(new kinoanlegenClickHandler());
		
		detailsPanel.add(kinoGrid);
		RootPanel.get("DetailsPanel").add(kinoGrid);
}
	/*
	 * Click handlers und abhängige AsyncCallback Klassen.
	 */	
	
	
	/**
	 * Callback wird benötigt, um das Kino zu erstellen
	 */
	private class KinoCreationCallback implements AsyncCallback<Kino> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Kino konnte nicht erstellt werden");

		}

		@Override
		public void onSuccess(Kino kino) {
			if (kino == null) {
				Window.alert("Das Kino existiert bereits");
			} else {
				Window.alert("Das Kino wurde erfolgreich erstellt");
				kinoname.setText("");
				dataProvider.getList().add(kino);
				dataProvider.refresh();
			}

		}
	}

/**
 * Sobald die Textfelder ausgefüllt wurden, wird ein neues Kino nach dem
 * Klicken des Kinoanlegen Buttons erstellt.
 */
	private class kinoanlegenClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			kaa.createKino(kinoname.getValue(), kinobeschreibung.getValue(), kinoadresse.getValue(), new KinoCreationCallback());
			
		}
		
	}
	
}
