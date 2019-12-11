/**
 * 
 */
package de.hdm.swprakt.cinemates.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.swprakt.cinemates.shared.bo.Film;
import de.hdm.swprakt.cinemates.shared.bo.Gruppe;
import de.hdm.swprakt.cinemates.shared.bo.Kino;
import de.hdm.swprakt.cinemates.shared.bo.Kinokette;
import de.hdm.swprakt.cinemates.shared.bo.Nutzer;
import de.hdm.swprakt.cinemates.shared.bo.Spielplan;
import de.hdm.swprakt.cinemates.shared.bo.Spielzeit;
import de.hdm.swprakt.cinemates.shared.bo.Umfrage;
import de.hdm.swprakt.cinemates.shared.bo.Umfrageeintrag;
import de.hdm.swprakt.cinemates.shared.bo.Votum;

/**
 * Das asynchrone Gegenstück des Interface {@link KinoAdministration}
 * @author alina
 * @version 1.o
 *
 */
public interface KinoAdministrationAsync {

	void createFilm(String filmtitel, String beschreibung, String details, AsyncCallback<Film> callback);

	void createKino(String kinoname, String adresse, String beschreibung, AsyncCallback<Kino> callback);

	void editFilm(Film film, AsyncCallback<Film> callback);

	void editNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);

	void editSpielplan(Spielplan spielplan, AsyncCallback<Void> callback);

	void editSpielzeit(Spielzeit spielzeit, AsyncCallback<Void> callback);

	void deleteFilm(Film film, AsyncCallback<Void> callback);

	void deleteKino(Kino kino, AsyncCallback<Void> callback);

	void deleteSpielplan(Spielplan spielplan, AsyncCallback<Void> callback);

	void getAllFilme(AsyncCallback<Vector<Film>> callback);

	void findAllSpielplan(AsyncCallback<Vector<Spielplan>> callback);

	

	

	

}
