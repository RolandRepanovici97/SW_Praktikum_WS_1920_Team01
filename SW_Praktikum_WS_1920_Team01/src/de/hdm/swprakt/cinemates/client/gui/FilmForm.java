package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class FilmForm extends VerticalPanel{

	TextBox inhalt1 = new TextBox();
	TextBox inhalt2 = new TextBox();
	TextBox inhalt3 = new TextBox();
	Button anlegen = new Button("Film anlegen");
	
	VerticalPanel detailsPanel = new VerticalPanel();
	
	public FilmForm() {
		
	}
	
	public void onLoad() {
		
		super.onLoad();
		
		Grid gruppenGrid = new Grid(4, 4);
		Label name = new Label("Filmname");
		gruppenGrid.setWidget(0, 1, inhalt1);
		gruppenGrid.setWidget(0, 0, name);
		
		Label beschreibung = new Label("Filmbeschreibung");
		gruppenGrid.setWidget(1, 1, inhalt2);
		gruppenGrid.setWidget(1, 0, beschreibung);
		
		Label spiellänge = new Label("Spiellänge");
		gruppenGrid.setWidget(2, 1, inhalt3);
		gruppenGrid.setWidget(2, 0, spiellänge);
		gruppenGrid.setWidget(2, 2, anlegen);
		
		detailsPanel.add(gruppenGrid);
		RootPanel.get("DetailsPanel").add(gruppenGrid);
	}
	
	}

