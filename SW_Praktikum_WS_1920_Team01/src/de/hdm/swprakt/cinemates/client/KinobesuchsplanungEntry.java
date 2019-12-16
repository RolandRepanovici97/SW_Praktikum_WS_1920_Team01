package de.hdm.swprakt.cinemates.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.client.gui.admin.Navigator;
import de.hdm.swprakt.cinemates.client.gui.editor.HeaderfürKinobesuchsplanung;



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
		headerPanel.getElement().setId("headerPanelKinobesuchsplanung");
		Navigator navigator = new Navigator();
		
		RootPanel.get("Header").add(headerPanel);
		RootPanel.get("NavigationPanel").add(navigator);


	
	}
	
	
}