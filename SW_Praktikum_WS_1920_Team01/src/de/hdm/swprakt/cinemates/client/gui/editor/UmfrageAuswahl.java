/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

/**
 * Diese Klasse erweitert die Basisklasse Widget. 
 * Sie wird benötigt, um die Interaktion mit einzelnen Umfrageobjekten ermöglichen zu können.
 * Dazu muss das Interface HasClickHandlers implementieren. Dieses ermöglicht uns, 
 * dass sich unser UmfrageAuswahlObjekt wie eines verhält, das mit einem ClickEvent umgehen kann.
 * 
 * @author alina
 *
 */
public class UmfrageAuswahl extends Widget implements HasClickHandlers {

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		// TODO Auto-generated method stub
		return addDomHandler(handler, ClickEvent.getType());
	}


}
