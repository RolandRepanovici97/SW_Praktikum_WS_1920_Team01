package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.swprakt.cinemates.client.gui.Header;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KinobesuchsplanungEntry implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		/* Das ist nur ein Test!
		 * 
		 */
		
		Header headerPanel = new Header();
		RootPanel.get("Header").add(headerPanel);
	}
	
}