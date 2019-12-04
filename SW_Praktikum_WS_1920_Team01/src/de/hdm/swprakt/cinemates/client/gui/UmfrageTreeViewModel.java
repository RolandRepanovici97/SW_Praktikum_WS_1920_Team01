/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.swprakt.cinemates.shared.KinoBesuchsplanungAsync;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;


/**
 * Diese Implementierung des TreeViewModels sorgt für die Anzeige einer baumartigen Struktur, welche der Verwaltung
 * von Gruppen und Umfragen dienen soll.
 * @author alina
 *
 */
public class UmfrageTreeViewModel implements TreeViewModel {

	private GruppeForm gruppeForm;
	private UmfrageForm umfrageForm;

	private Gruppe selectedGruppe = null;
	private Umfrage selectedUmfrage = null;

	
	private KinoBesuchsplanungAsync kinobesuchsplanung = null;
	private ListDataProvider<Gruppe> gruppeDataProvider = null;
	private ListDataProvider<Umfrage> umfrageDataProvider = null;
}
