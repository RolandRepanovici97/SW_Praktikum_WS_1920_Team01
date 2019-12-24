package de.hdm.swprakt.cinemates.client.gui.admin;

import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Showcase extends VerticalPanel{

	
	public void onLoad() {
		super.onLoad();
		
		this.run();
	}

	protected abstract void run();
	
}
