/**
 * 
 */
package de.hdm.swprakt.cinemates.client.gui;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.swprakt.cinemates.shared.KinoAdministrationAsync;
import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;

/**
 * Diese Implementierung des TreeViewModels sorgt für die Anzeige einer baumartigen Struktur, welche der Verwaltung
 * der Kinos, Kinoketten, Spielzeiten und Spielplänen dienen soll.
 * @author alina
 *
 */
public class KinoTreeViewModel implements TreeViewModel {

	private KinoForm kinoform;
	private FilmForm filmform;
	private Spielplanform spielplanform;
	private Spielzeitform spielzeitform;
	
	private Kino selectedKino = null;
	private Film selectedFilm = null;
	private Spielplan selectedSpielplan= null;
	private Spielzeit selectedSpielzeit = null;
	
	private KinoAdministrationAsync kinoAdministration = null;
	private ListDataProvider<Kino> kinoDataProvider = null;
	private ListDataProvider<Film> filmDataProvider = null;
	private ListDataProvider<Spielplan> spielplanDataProvider = null;
	private ListDataProvider <Spielzeit> spielzeitDataProvider = null;
}
