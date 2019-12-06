package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpielplanForm extends HorizontalPanel{

	
	TextBox spielplanName = new TextBox();
	ListBox filmAuswählen = new ListBox();
	
	HorizontalPanel detailsPanel = new HorizontalPanel();
	
	public void onLoad() {
		
		super.onLoad();
		
		Grid spielplanGrid = new Grid(4, 4);
		spielplanGrid.setWidget(0, 0, filmAuswählen);
		
		detailsPanel.add(spielplanGrid);
		RootPanel.get("DetailsPanel").add(spielplanGrid);
		
	}
}
