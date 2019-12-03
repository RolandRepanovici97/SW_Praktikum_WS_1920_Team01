package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.swprakt.cinemates.client.gui.Details;
import de.hdm.swprakt.cinemates.client.gui.GruppenverwaltungForm;
import de.hdm.swprakt.cinemates.client.gui.HeaderfürKinoAdministration;
import de.hdm.swprakt.cinemates.client.gui.HeaderfürKinobesuchsplanung;
import de.hdm.swprakt.cinemates.client.gui.Navigator;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KinobesuchsplanungEntry implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub.
		
		/* Das ist nur ein Test!.
		 * 
		 */
		
		HeaderfürKinobesuchsplanung headerPanel = new HeaderfürKinobesuchsplanung();
		Navigator navigator = new Navigator();
		Details detailsPanel = new Details();
		
		RootPanel.get("Header").add(headerPanel);
		RootPanel.get("Navigator").add(navigator);
		RootPanel.get("Details").add(detailsPanel);
	
		
		
	
	}
	
}