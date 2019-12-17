package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.swprakt.cinemates.client.gui.admin.HeaderfürKinoAdministration;
import de.hdm.swprakt.cinemates.client.gui.admin.KinoketteForm;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KinoAdministrationEntry implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
	
		
		HeaderfürKinoAdministration headerPanel = new HeaderfürKinoAdministration();
		RootPanel.get("HeaderPanelKinoAdministration").add(headerPanel);
		
		KinoketteForm kf = new KinoketteForm();
		RootPanel.get("DetailsPanel").add(kf);
		
	}
	
}