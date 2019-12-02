package de.hdm.swprakt.cinemates.client.gui;


import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Details extends VerticalPanel{

public void onLoad() {
		
		super.onLoad();
	
		/**
		 * Forms
		 */
		GruppenverwaltungForm gf = new GruppenverwaltungForm();
	
		/*
		 * VerticalPanel für die Details-Sicht
		 */
		VerticalPanel detailsPanel = new VerticalPanel();
		detailsPanel.add(gf);
		RootPanel.get("Details").add(detailsPanel);
		
		}

}
