package de.hdm.swprakt.cinemates.client.gui.editor;

import java.util.Vector;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;


/**
 * Diese Klasse erweitert das HorizontalPanel und stellt den Body der GruppenVerwaltungForm "Anzeigen" Tab dar.
 * Die Klasse dient als Einstieg
 *
 * @author roland
 *
 */

public class GruppenAnzeigenForm extends HorizontalPanel {
	
	
	private Gruppe gruppe = new Gruppe();
	private Nutzer nutzer;
	
	private Vector <Gruppe> gruppen = new Vector<Gruppe>();
	private Label titel = new Label("Meine Gruppenverwaltung");
	private PushButton neueGruppe = new PushButton("Neue Gruppe");
	private PushButton zurückButton = new PushButton ("Zurück");
	
	
	
	

}
