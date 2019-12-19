
package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.swprakt.cinemates.shared.bo.Nutzer;

/**
 * Diese Klasse stellt eine Erweiterung des HorizontalPanels dar und wird verwendet, um dem angemeldeten Nutzer die Möglichkeit zu bieten,
 * sein Nutzerkonto anzusehen, sowie seinen Nutzernamen zu bearbeiten.
 * @author alina
 *
 */
public class NutzerkontoForm extends HorizontalPanel {
	



	private Label titel = new Label("Mein Nutzerkonto");
	private Label nutzernamelabel = new Label("Nutzername:");
	private Label emaillabel = new Label("E-Mail");
	private Grid tabelle = new Grid(2,2);

	public void onLoad() {

		super.onLoad();

		tabelle.add(nutzernamelabel);

		this.add(titel);
		this.add(tabelle);

	}





}
