package de.hdm.swprakt.cinemates.client.gui.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class GruppenverwaltungForm extends FlowPanel{

	//Noch nicht fertig
	
	Label überschrift = new Label("Gruppe anlegen");
	TextBox inhalt1 = new TextBox();
	TextBox inhalt2 = new TextBox();
	Button anlegen = new Button("Gruppe anlegen");
	
	
	public GruppenverwaltungForm() {
		
	}
	
public void onLoad() {
		
	
		
		Grid gruppenGrid = new Grid(4, 4);
		Label name = new Label("Gruppenname");
		gruppenGrid.setWidget(0, 1, inhalt1);
		gruppenGrid.setWidget(0, 0, name);
		
		Label mitglieder = new Label("Gruppenmitglieder");
		anlegen.addClickHandler(new anlegenClickHandler());
		gruppenGrid.setWidget(1, 1, inhalt2);
		gruppenGrid.setWidget(1, 0, mitglieder);
		gruppenGrid.setWidget(1, 2, anlegen);
		
		this.add(gruppenGrid);
		RootPanel.get("DetailsPanel").add(gruppenGrid);
}

/*
 * Click handlers und abhängige AsyncCallback Klassen.
 */

private class anlegenClickHandler implements ClickHandler {
	@Override
	public void onClick(ClickEvent event) {

		Window.alert("Gruppe erfolgreich angelegt");
}
	
	}
}
