package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GruppenverwaltungForm extends VerticalPanel{

	//Noch nicht fertig
	
	Label überschrift = new Label("Gruppe anlegen");
	TextBox inhalt = new TextBox();
	Button anlegen = new Button("anlegen");
	
public void onLoad() {
		
		super.onLoad();
		
		Grid accountGrid = new Grid(1, 1);
		Label name = new Label("Gruppenname");
		accountGrid.setWidget(0, 1, inhalt);
		accountGrid.setWidget(0, 0, name);
		
		Label mitglieder = new Label("Gruppenmitglieder");
		accountGrid.setWidget(1, 1, inhalt);
		accountGrid.setWidget(1, 0, mitglieder);
		
		this.add(accountGrid);
}
}