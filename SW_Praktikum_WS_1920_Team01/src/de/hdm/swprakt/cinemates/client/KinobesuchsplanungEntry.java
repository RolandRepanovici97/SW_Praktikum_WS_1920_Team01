package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		GruppenverwaltungForm gf = new GruppenverwaltungForm();
		
		
		
		HeaderfürKinobesuchsplanung headerPanel = new HeaderfürKinobesuchsplanung();
		headerPanel.getElement().setId("headerPanelKinobesuchsplanung");
		Navigator navigator = new Navigator();
		
		
		RootPanel.get("Header").add(headerPanel);
		RootPanel.get("Navigator").add(navigator);
		


	
	}
	
	
}