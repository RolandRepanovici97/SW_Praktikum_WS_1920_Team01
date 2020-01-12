/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui.editor;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;

/**
 * @author alina
 *
 */
public class GruppeDetailsForm extends VerticalPanel {

	private Gruppe gewählteGruppe;

	/**
	 * @return the gewählteGruppe
	 */
	public Gruppe getGewählteGruppe() {
		return gewählteGruppe;
	}

	/**
	 * @param gewählteGruppe the gewählteGruppe to set
	 */
	public void setGewählteGruppe(Gruppe gewählteGruppe) {
		this.gewählteGruppe = gewählteGruppe;
	}
}
