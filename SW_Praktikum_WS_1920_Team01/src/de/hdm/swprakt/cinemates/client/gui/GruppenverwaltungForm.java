package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GruppenverwaltungForm extends VerticalPanel{

	//Noch nicht fertig
	
	Label überschrift = new Label("Gruppe anlegen");
	TextBox inhalt1 = new TextBox();
	TextBox inhalt2 = new TextBox();
	Button anlegen = new Button("anlegen");
	VerticalPanel detailsPanel = new VerticalPanel();
	
	public GruppenverwaltungForm() {
		
	}
	
public void onLoad() {
		
	
		
		Grid gruppenGrid = new Grid(2, 2);
		
		Label name = new Label("Gruppenname");
		gruppenGrid.setWidget(0, 1, inhalt1);
		gruppenGrid.setWidget(0, 0, name);
		
		Label mitglieder = new Label("Gruppenmitglieder");
		gruppenGrid.setWidget(1, 1, inhalt2);
		gruppenGrid.setWidget(1, 0, mitglieder);
		
		detailsPanel.add(gruppenGrid);
		RootPanel.get("details").add(gruppenGrid);
}
}