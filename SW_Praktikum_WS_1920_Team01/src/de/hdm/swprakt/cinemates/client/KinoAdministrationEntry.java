package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.swprakt.cinemates.client.gui.admin.HeaderfürKinoAdministration;
import de.hdm.swprakt.cinemates.client.gui.admin.NavigatorfürKinoAdministration;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KinoAdministrationEntry implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
	
		
		HeaderfürKinoAdministration headerPanel = new HeaderfürKinoAdministration();
		RootPanel.get("HeaderPanelKinoAdministration").add(headerPanel);
		
		NavigatorfürKinoAdministration navigator = new NavigatorfürKinoAdministration();
		RootPanel.get("NavigationPanel").add(navigator);
		
	}
	
}